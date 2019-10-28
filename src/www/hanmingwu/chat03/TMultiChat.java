package www.hanmingwu.chat03;
/**
 * 问题：只有当其他用户退出时才继续聊天
 * 使用多线程
 * 问题：客户端没有读写分开，不好维护
 *
 */

import www.hanmingwu.tcp.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TMultiChat {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new channel(client)).start();
        }
    }


//一个客户用一个channel表示
    static  class channel implements  Runnable{
        private  DataInputStream dis;
        private  DataOutputStream dos;
        private Socket client;
        private boolean isRunning;
        public channel(Socket client){
            this.client=client;
            try {
                dis=new DataInputStream(client.getInputStream());
                dos=new DataOutputStream(client.getOutputStream());
                isRunning=true;
            } catch (IOException e) {
                System.out.println("---1---");
                release();
            }


        }

        //接收信息
        private String receive(){
            String msg="";
            try {
                msg=dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
            return msg;


        }

        //写出信息
        private  void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }

        }


        //释放资源
        private  void release(){
            this.isRunning=false;
            HanUtils.close(dis,dos,client);

        }

        @Override
        public void run() {
            while (isRunning){
                String msg=receive();
                if (!msg.equals(""))
                {
                    send(msg);
                }
            }

        }

    }
}
