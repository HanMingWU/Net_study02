package www.hanmingwu.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpTypeClient {
    public static void main(String[] args) throws Exception {
        System.out.println("发送端启动中");
        DatagramSocket client =new DatagramSocket(8888);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(baos));
        dos.writeInt(1005);
        dos.writeBoolean(false);
        dos.writeUTF("talk is cheap,show me the code");
        dos.writeChar('t');
        dos.flush();
        byte[] datas=baos.toByteArray();
        DatagramPacket packet =new DatagramPacket(datas,datas.length,new InetSocketAddress("localhost",6666));
        client.send(packet);
        client.close();


    }
}
