package www.hanmingwu.chat04;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 使用多线程封装:发送端
 * 1、发送消息
 * 2、从控制台获取消息
 * 3、释放资源
 * 4、重写run
 *
 *
 */

public class Send implements Runnable{
    private BufferedReader console ;
    private DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    private String name;

    public Send(Socket client,String name){
        this.client=client;
        this.isRunning=true;
        this.name=name;
        console=new BufferedReader(new InputStreamReader(System.in));
        try {
            dos=new DataOutputStream(client.getOutputStream());
            //管道建立好后立刻发名称过去
            send(name);
        } catch (IOException e) {
           this.release();

        }

    }

    //* 1、发送消息
    private void send(String msg){
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            System.out.println("===1===");
            release();
        }

    }

    // * 2、从控制台获取消息
    private String getSirFromConsole(){
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    //* 3、释放资源
    private void release(){
        this.isRunning=false;
        HanUtils.close(dos,client);

    }
    //* 4、重写run


    @Override
    public void run() {
        while (isRunning)
        {
            String msg= getSirFromConsole();
            if(!msg.equals("")){
                send(msg);
            }

        }


    }
}
