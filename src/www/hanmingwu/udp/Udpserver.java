package www.hanmingwu.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 基本流程: 接收端
 * 一切包裹为中心，不用写io流（传输）
 * Address already in use: Cannot bind  同一个协议下端口不允许冲突
 * 1、使用DatagramSocket  指定端口 创建接收端
 * 2、准备容器 封装成DatagramPacket 包裹
 * 3、阻塞式接收包裹receive​(DatagramPacket p)
 * 4、分析数据
 *    byte[]  getData​()
 *                getLength​()
 * 5、释放资源
 * @author 裴新 QQ:3401997271
 *
 */
public class Udpserver {
    public static void main(String[] args) throws Exception {
        //创建接收端，指定端口
        DatagramSocket server = new DatagramSocket(6666);
        //准备容器，封装成包裹
        byte[] container =new byte[1024*6];
        //准备容器 封装成DatagramPacket 包裹
        DatagramPacket packe =new DatagramPacket(container,0,container.length);

        server.receive(packe);
        byte[] datas=packe.getData();
        int len =packe.getLength();
        System.out.println(new String(datas,0,len));

        server.close();


    }
}
