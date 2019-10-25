package www.hanmingwu.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 创建客户端
 * 1.建立连接：使用Socket创建客户端+服务地址和端口
 * 2.操作：输入输出流操作
 * 3释放资源
 *
 */

public class FileClient {
    public static void main(String[] args) throws IOException {
        System.out.println("--client---");
        //请求连接
        Socket client=new Socket("localhost",8888);
        //操作,文件拷贝
        InputStream is=new BufferedInputStream(new FileInputStream("src/ndl.png"));
        OutputStream os=new BufferedOutputStream(client.getOutputStream());
        byte[] flush=new byte[1024];
        int len=-1;
        while((len=is.read(flush))!=-1){
            os.write(flush,0,len);

        }


        os.flush();
        is.close();
        os.close();

        client.close();





    }
}
