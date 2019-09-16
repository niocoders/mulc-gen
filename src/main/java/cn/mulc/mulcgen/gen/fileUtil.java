package cn.mulc.mulcgen.gen;

import java.io.File;

/**
 * @ProjectName: mulc-gen
 * @Package: cn.mulc.mulcgen.util
 * @ClassName: fileUtil
 * @Author: llnlg
 * @Description:
 * @Date: 2019/9/10 16:08
 * @Version: 1.0
 */
public class fileUtil {
    //根据输入的模块名进行切割获得数组
    public static String[] getMoudleName(String moudleName) {
        String[] split = moudleName.split("\\.");
        return split;
    }

    //判断文件是否存在，如果没有则新建
    public static void mkdirsFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    //判断文件是否存在
    public static boolean isHave(String path){
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }
}
