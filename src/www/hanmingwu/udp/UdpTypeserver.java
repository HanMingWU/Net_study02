package www.hanmingwu.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 基本类型: 接收端
 * Address already in use: Cannot bind  同一个协议下端口不允许冲突
 * 1、使用DatagramSocket  指定端口 创建接收端
 * 2、准备容器 封装成DatagramPacket 包裹
 * 3、阻塞式接收包裹receive​(DatagramPacket p)
 * 4、分析数据    将字节数组还原为对应的类型
 *    byte[]  getData​()
 *                getLength​()
 * 5、释放资源
 * @author 裴新 QQ:3401997271
 *
 */
public class UdpTypeserver {

    public static void main(String[] args) throws Exception {
        //创建接口
        DatagramSocket server =new DatagramSocket(6666);
        //准备容器
        byte[] container=new byte[1024*6];
        DatagramPacket packet =new DatagramPacket(container,0,container.length);
        server.receive(packet);
        //分析数据
        byte[] datas=packet.getData();
        int len=datas.length;
        //读取数据流
        DataInputStream dis=new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        int age =dis.readInt();
        boolean flag= dis.readBoolean();
        String msg= dis.readUTF();
        char ch=dis.readChar();
        System.out.println(msg+flag+age);

        dis.close();






    }
}
