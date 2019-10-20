package www.hanmingwu.udp;

/**
 * 加入多线程，实现双向交流
 *
 */

public class TalkStudent {
    public static void main(String[] args) {
        new Thread(new TalkSend(9999,"localhost",6666)).start();//发送
        new Thread(new TalkReceive(8888)).start();//接受端口
    }
}
