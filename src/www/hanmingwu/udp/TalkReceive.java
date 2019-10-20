package www.hanmingwu.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 */
public class TalkReceive  implements Runnable{
    private DatagramSocket server ;
    public TalkReceive(int port){
        try {
            server=new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            //准备容器，封装成包裹
            byte[] container = new byte[1024 * 6];
            //准备容器 封装成DatagramPacket 包裹
            DatagramPacket packe = new DatagramPacket(container, 0, container.length);

            try {
                server.receive(packe);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] datas = packe.getData();
            int len = packe.getLength();
            String data=new String(datas, 0, len);

            System.out.println(data);

            if (data.equals("bye"))
            {
                break;
            }
        }
        server.close();
    }
}
