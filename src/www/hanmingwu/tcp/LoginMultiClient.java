package www.hanmingwu.tcp;

import java.io.*;
import java.net.Socket;

public class LoginMultiClient {
    public static void main(String[] args) throws IOException {
        System.out.println("==client");
        //建立连接；Socket
        Socket client=new Socket("localhost",8888);
        //操作，输入输出流操作
        new Send(client).send();
        new Receive(client).receive();
        client.close();

    }
    //发送
    static class Send{
        //操作,创建了输出流对象
        private Socket client;
        private BufferedReader console;
        private String msg;
        private DataOutputStream dos;
        public Send(Socket client){
            console=new BufferedReader(new InputStreamReader(System.in));
            this.client=client;
            this.msg=init();

            try {
                dos=new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //
        private void send(){
            try {
                dos.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String init() {
            try {
                System.out.print("请输入用户名:");
                String uname =console.readLine();
                System.out.print("请输入密码:");
                String upwd =console.readLine();
                return "uname="+uname+"&"+"upwd="+upwd;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }



    }
    //接受
    static class Receive{
        private DataInputStream dis;
        private Socket client;

        public Receive(Socket client) {
            this.client = client;
            try {
                dis =new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void receive(){
            String result;
            try {
                result=dis.readUTF();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
