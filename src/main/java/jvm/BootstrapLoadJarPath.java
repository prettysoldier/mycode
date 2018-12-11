package jvm;

import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/28 19:07
 **/
public class BootstrapLoadJarPath {
    public static void main(String[] args) {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        Arrays.asList(urls).forEach(u->{
            System.out.println(u.toExternalForm());
//            System.out.println(u.getPath());
        });
    }
}
