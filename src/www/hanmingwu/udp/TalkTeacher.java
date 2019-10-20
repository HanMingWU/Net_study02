package www.hanmingwu.udp;

public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkReceive(6666)).start();//接受端口
        new Thread(new TalkSend(5555,"localhost",8888)).start();//发送
    }
}
