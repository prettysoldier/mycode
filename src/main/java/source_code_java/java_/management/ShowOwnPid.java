package source_code_java.java_.management;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 获取当前进程的PID
 */
public class ShowOwnPid {
    public static void main(String[] args) throws Exception {
        int pid = ShowOwnPid.getPid();
        System.out.println("pid: " + pid);
        System.in.read(); // block the program so that we can do some probing on it
    }

    private static int getPid() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        // format: "pid@hostname"
        System.out.println("runtimeMXBean.getName() : " + runtimeMXBean.getName());
        System.out.println("runtimeMXBean.getBootClassPath()" + runtimeMXBean.getBootClassPath());
        System.out.println("runtimeMXBean.getClassPath() : " + runtimeMXBean.getClassPath());
        System.out.println("runtimeMXBean.getLibraryPath() : " + runtimeMXBean.getLibraryPath());
        System.out.println("runtimeMXBean.getManagementSpecVersion() : " + runtimeMXBean.getManagementSpecVersion());
        System.out.println("runtimeMXBean.getSpecName() : " + runtimeMXBean.getSpecName());
        System.out.println("runtimeMXBean.getSpecVendor(); : " + runtimeMXBean.getSpecVendor());
        System.out.println("runtimeMXBean.getSpecVersion() : " + runtimeMXBean.getSpecVersion());
        System.out.println("runtimeMXBean.getVmName() : " + runtimeMXBean.getVmName());
        System.out.println("runtimeMXBean.getVmVendor() : " + runtimeMXBean.getVmVendor());
        System.out.println("runtimeMXBean.getVmVersion() : " + runtimeMXBean.getVmVersion());
        System.out.println("runtimeMXBean.getInputArguments() : " + runtimeMXBean.getInputArguments());
        System.out.println("runtimeMXBean.getObjectName() : " + runtimeMXBean.getObjectName());
        System.out.println("runtimeMXBean.getStartTime() : " + runtimeMXBean.getStartTime());
        System.out.println("runtimeMXBean.getUptime() : " + runtimeMXBean.getUptime());
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
}