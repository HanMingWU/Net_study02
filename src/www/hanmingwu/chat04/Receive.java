package www.hanmingwu.chat04;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
    private DataInputStream dis;
    private Socket client;
    private  boolean isRunning;
    public Receive(Socket client){
        this.client=client;
        this.isRunning=true;
        try {
            dis=new DataInputStream(client.getInputStream());
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("===1===");
            release();
        }

    }
    //接受信息
    private String receive(){
        String msg="";
        try {
           msg= dis.readUTF();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("===2===");
            release();
        }
        return msg;
    }
    //释放资源
    private void release(){
        this.isRunning=false;
        HanUtils.close(dis,client);


    }
    @Override
    public void run() {
        while (isRunning)
        {
            String msg=receive();
            if(!msg.equals(""))
            {
                System.out.println(msg);
            }

        }

    }

}
