package www.hanmingwu.chat02;
/**
 * 问题：只有当其他用户退出时才继续聊天
 * 使用多线程
 * 问题：客户端没有读写分开，不好维护
 *
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TMultiChat {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(()->{
                DataInputStream dis =null;
                DataOutputStream dos = null;
                try {
                    dis=new DataInputStream(client.getInputStream());
                    //返回消息

                    dos=new DataOutputStream(client.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //一直接受信息和发送信息
                boolean isRunning = true;
                while (isRunning) {
                    String msg ;
                    try {
                        msg = dis.readUTF();
                        dos.writeUTF(msg);
                        dos.flush();
                    } catch (IOException e) {
                        isRunning=false;
                    }
                   // System.out.println(msg);



                }

                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //接受信息

        }
    }
    class Channel{
        //接受消息
        //发送消息
        //释放资源
    }
}
