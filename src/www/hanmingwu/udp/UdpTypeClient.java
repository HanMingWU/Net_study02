package www.hanmingwu.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 基本类型: 发送端
 * 1、使用DatagramSocket  指定端口 创建发送端
 * 2、将基本类型  转成字节数组
 * 3、 封装成DatagramPacket 包裹，需要指定目的地
 * 4、发送包裹send​(DatagramPacket p) *
 * 5、释放资源
 * @author 裴新 QQ:3401997271
 *
 */
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
