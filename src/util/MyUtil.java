package util;

/**
 * Created by Ace on 2017/6/4.
 */
public class MyUtil {
    public long getLong(String str){
        try {
            if (str!=null&&str.trim().length()>0){
                return Long.valueOf(str);
            }else
                return -1;
        }catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return -1;
    }
}
