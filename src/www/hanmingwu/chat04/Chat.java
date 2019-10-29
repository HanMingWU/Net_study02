package www.hanmingwu.chat04;
/**
 * 问题：只有当其他用户退出时才继续聊天
 * 使用多线程
 * 问题：客户端没有读写分开，不好维护
 * 加入容器实现群聊
 *
 *
 */

import javax.swing.event.ChangeEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chat {
    private static CopyOnWriteArrayList<channel> all= new CopyOnWriteArrayList<channel>();

    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8888);
        //阻塞式接受
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            channel c=new channel(client);
            all.add(c);//交给容器来管理所有的成员
            new Thread(c).start();
        }
    }


//一个客户用一个channel表示
    static  class channel implements  Runnable{
        private  DataInputStream dis;
        private  DataOutputStream dos;
        private Socket client;
        private boolean isRunning;
        private  String name;
        public channel(Socket client){
            this.client=client;
            try {
                dis=new DataInputStream(client.getInputStream());
                dos=new DataOutputStream(client.getOutputStream());
                isRunning=true;
                //获取名称
                this.name=receive();
                //欢迎你的到来
                this.send("欢迎你进入聊天室");
                sendOthers(this.name+"进入了聊天室",true);
                //
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
        //群聊:获取信息发给其他人
        //私聊:约定数据模式
        private  void   sendOthers(String msg,boolean isSys) {
            boolean isPrivate=msg.startsWith("@");
            if(isPrivate){
                //私聊
                int index=msg.indexOf(":");
                String targetName=msg.substring(1,index);
                msg=msg.substring(index);
                for(channel other:all){
                    if(other.name.equals(targetName)){
                        other.send(this.name+"私聊你："+msg);
                    }
                }
            }else {
                for (channel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (!isSys) {

                        other.send(this.name + "说：" + msg);
                    } else {
                        other.send(msg);
                    }

                }
            }
        }
        //释放资源
        private  void release(){
            this.isRunning=false;
            HanUtils.close(dis,dos,client);
            //退出
            all.remove(this);
            sendOthers(this.name+"离开了聊天室",true);
        }

        @Override
        public void run() {
            while (isRunning){
                String msg=receive();
                if (!msg.equals(""))
                {
                    //send(msg);
                    sendOthers(msg,false);

                }
            }

        }

    }
}
