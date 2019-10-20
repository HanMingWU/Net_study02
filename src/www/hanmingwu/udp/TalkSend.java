package www.hanmingwu.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端
 *
 */

public class TalkSend implements Runnable{

    private DatagramSocket client;
    private BufferedReader reader;
    private String toIP;
    private int toPort;
    public TalkSend(int port,String toIP,int toPort) {
        this.toIP=toIP;
        this.toPort=toPort;
        try {
            client=new DatagramSocket(port);
            reader =new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {

        }
    }
    @Override
    public void run() {
        while (true) {
            // String data="talk is cheap,show me the code!";
            String data;
            try {
                data = reader.readLine();
                byte[] datas = data.getBytes();
                DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
                        /*创建端口，指定地址*/
                        new InetSocketAddress(this.toIP, this.toPort));
                client.send(packet);
                if (data.equals("bye")){
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        client.close();
    }

}
