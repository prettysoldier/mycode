package test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import javax.management.ObjectName;

public class ShowOwnPid {
    public static void main(String[] args) throws Exception {
        int pid = ShowOwnPid.getPid();
        System.out.println("pid: " + pid);
        System.in.read(); // block the program so that we can do some probing on it
    }

    private static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        String name1 = runtime.getBootClassPath();
        String name2 = runtime.getClassPath();
        String name3 = runtime.getLibraryPath();
        String name4 = runtime.getManagementSpecVersion();
        String name5 = runtime.getSpecName();
        String name6 = runtime.getSpecVendor();
        String name7 = runtime.getSpecVersion();
        String name8 = runtime.getVmName();
        String name9 = runtime.getVmVendor();
        String name10 = runtime.getVmVersion();
        List<String> name11 = runtime.getInputArguments();
        ObjectName name12 = runtime.getObjectName();
        long name13 = runtime.getStartTime();
        long name14 = runtime.getUptime();
        System.out.println(name);
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(name4);
        System.out.println(name5);
        System.out.println(name6);
        System.out.println(name7);
        System.out.println(name8);
        System.out.println(name9);
        System.out.println(name10);
        System.out.println(name11);
        System.out.println(name12);
        System.out.println(name13);
        System.out.println(name14);
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
}