package www.hanmingwu.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 基本流程: 发送端
 * 1、使用DatagramSocket  指定端口 创建发送端
 * 2、准备数据 一定转成字节数组
 * 3、 封装成DatagramPacket 包裹，需要指定目的地
 * 4、发送包裹send​(DatagramPacket p) *
 * 5、释放资源
 * @author 裴新 QQ:3401997271
 *
 */
public class UdpTalkclient {
    public static void main(String[] args) throws Exception {
        System.out.println("---client---");
        //创建发送端，DatagramSocket,指定端口
        DatagramSocket client=new DatagramSocket(9999);
        //准备字节数组
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // String data="talk is cheap,show me the code!";
            String data = reader.readLine();

            byte[] datas = data.getBytes();
            DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
                    /*创建端口，指定地址*/
                    new InetSocketAddress("localhost", 6666));
            client.send(packet);
            if (data.equals("bye")){
                break;
            }
        }
        client.close();


    }

}
