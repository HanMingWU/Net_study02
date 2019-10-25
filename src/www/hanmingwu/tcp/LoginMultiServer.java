package www.hanmingwu.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器、
 * 1.指点窗口，使用serversocket创建服务器
 * 2.阻塞式接受
 * 操作：输入输出流操作
 * 释放资源
 */
public class LoginMultiServer {
    public static void main(String[] args) throws IOException {
        System.out.println("---server---");
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        boolean isRunning=true;
        while (isRunning){
            Socket client= server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }


    }

    static class Channel implements Runnable{
        private  Socket client;
        //输入流
        private DataInputStream dis;
        //输出流
        private DataOutputStream dos;
        //构造器、


        public Channel(Socket client) {
            this.client = client;

            try {
                dis=new DataInputStream(client.getInputStream());
                dos=new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //接受数据
        private  String receive(){
            String datas="";
            try {
                datas =dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return datas;
        }

       //发送数据
        private void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //释放资源
        private void  release()
        {
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
        }




        @Override
        public void run() {
            String uname="";
            String uwpd="";
            String[] dataArray=receive().split("&");
            for(String info:dataArray){
                String[] useInfo=info.split("=");
                if(useInfo[0].equals("uname")){
                    System.out.println("你的用户名："+useInfo[1]);
                    uname=useInfo[1];
                }else if(useInfo[0].equals("upwd")){
                    System.out.println("你的密码："+useInfo[1]);
                    uwpd=useInfo[1];
                }
            }
            if (uname.equals("hanming")&&uwpd.equals("danting")){
                send("登录成功，欢迎回来");


            } else {
                send("用户名或密码错误");
            }

            release();
        }



    }





}
