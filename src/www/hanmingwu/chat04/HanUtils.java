package www.hanmingwu.chat04;

import java.io.Closeable;

/**
 * 用于释放资源
 */
public class HanUtils {
    public  static void close(Closeable... targets){
        for(Closeable target:targets){
            try {
                if(null!=target){
                    target.close();
                }
            } catch (Exception e) {

            }
        }
    }


}
