package test.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author Shuaijun He
 */
public class IpUtils {

    /**
     * 检查ip是否在白名单规则范围内
     * <p>
     * 白名单特点： * 为true 为不检查，不满足ip格式的不限制
     * </p>
     *
     * @param ip
     * @param ipWhiteLists
     * @return
     */
    public static boolean inIpWhiteList(String ip, String[] ipWhiteLists) {
        for (String whiteList : ipWhiteLists) {
            whiteList = StringUtils.trimToEmpty(whiteList);
            if ("*".equals(whiteList)) {
                return true;
            }
            boolean success = IpUtils.inIpWhiteList(ip, whiteList);
            if (success) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查ip是否在黑名单规则范围内
     * <p>
     * 黑名单特点： * 返回false 为不限制，不满足ip格式返回false 不限制
     * </p>
     *
     * @param ip
     * @param ipBlackLists
     * @return
     */
    public static boolean inIpBlackList(String ip, String[] ipBlackLists) {
        for (String blackList : ipBlackLists) {
            blackList = StringUtils.trimToEmpty(blackList);
            if ("*".equals(blackList)) {
                // 返回false 不检查
                return false;
            }
            String[] ipWhiteSections = blackList.split("\\.");
            if (ipWhiteSections.length != 4) {
                // ip地址配错了，不检查，继续下一个ip
                continue;
            }
            boolean success = IpUtils.inIpWhiteList(ip, blackList);
            if (success) {
                // true 表示 ip是在黑名单中，要挡住
                return true;
            }
        }
        // 最终返回false，不检查
        return false;
    }

    private static boolean inIpWhiteList(String ip, String whiteList) {
        if ("*".equals(whiteList)) {
            return true;
        }
        String[] ipWhiteSections = whiteList.split("\\.");
        if (ipWhiteSections.length != 4) {
            System.out.println(String.format("规则设置错误({})，请检查。", whiteList));
            return true;
        }
        String[] ipSections = ip.split("\\.");
        for (int i = 0; i < ipWhiteSections.length; i++) {
            if ("*".equals(ipWhiteSections[i])) {
                continue;
            }
            if (ipWhiteSections[i].contains("-")) {
                String[] section = ipWhiteSections[i].split("\\-");
                int ipSection = Integer.parseInt(ipSections[i]);
                int max = Integer.parseInt(section[1]);
                int min = Integer.parseInt(section[0]);
                if (ipSection <= max && ipSection >= min) {
                    continue;
                }
                return false;
            }
            if (ipWhiteSections[i].equals(ipSections[i])) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证输入的ip白/黑名单是否符合规范
     *
     * @param ipWhiteLists
     * @return
     */
    public static boolean valid(String ipWhiteLists) {
        if (StringUtils.isEmpty(ipWhiteLists)) {
            return false;
        }
        if ("*".equals(ipWhiteLists)) {
            return true;
        }
        if (ipWhiteLists.contains(";")) {
            String[] ipWhiteList = ipWhiteLists.split(";");
            for (String ip : ipWhiteList) {
                if (!IpUtils.validIpWhiteList(ip)) {
                    return false;
                }
            }
            return true;
        } else {
            return IpUtils.validIpWhiteList(ipWhiteLists);
        }
    }

    private static boolean validIpWhiteList(String ipWhiteList) {
        String ipSection = "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])";
        ipSection = "(" + ipSection + "{1}(\\-" + ipSection + "){0,1})";
        String regEx = "^" + ipSection + "{1}" + "(\\.(" + ipSection
            + "|\\*)){3}$";
        Pattern pet = Pattern.compile(regEx);
        Matcher match = pet.matcher(ipWhiteList);
        return match.find();
    }

    public static void main(String[] args) {
        String ip = "172.16.0.0";
        String mask = "22";
        List<String> list = IpUtils.parseIpMaskRange(ip, mask);
        System.out.println(list.size());
        for (String ip1 : list) {
            System.out.println(ip1);
        }
    }

    private static List<String> parseIpMaskRange(String ip, String mask) {
        List<String> list = new ArrayList<>();
        if ("32".equals(mask)) {
            list.add(ip);
        } else {
            String startIp = IpUtils.getBeginIpStr(ip, mask);
            String endIp = IpUtils.getEndIpStr(ip, mask);
            if (!"31".equals(mask)) {
                String subStart = startIp.split("\\.")[0] + "."
                    + startIp.split("\\.")[1] + "." + startIp.split("\\.")[2]
                    + ".";
                String subEnd = endIp.split("\\.")[0] + "."
                    + endIp.split("\\.")[1] + "." + endIp.split("\\.")[2] + ".";
                startIp = subStart
                    + (Integer.valueOf(startIp.split("\\.")[3]) + 1);
                endIp = subEnd + (Integer.valueOf(endIp.split("\\.")[3]) - 1);
            }
            list = IpUtils.parseIpRange(startIp, endIp);
        }
        return list;
    }

    private static List<String> parseIpRange(String ipfrom, String ipto) {
        List<String> ips = new ArrayList<>();
        String[] ipfromd = ipfrom.split("\\.");
        String[] iptod = ipto.split("\\.");
        int[] int_ipf = new int[4];
        int[] int_ipt = new int[4];
        for (int i = 0; i < 4; i++) {
            int_ipf[i] = Integer.parseInt(ipfromd[i]);
            int_ipt[i] = Integer.parseInt(iptod[i]);
        }
        for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
            for (int B = A == int_ipf[0] ? int_ipf[1] : 0; B <= (A == int_ipt[0] ? int_ipt[1]
                : 255); B++) {
                for (int C = B == int_ipf[1] ? int_ipf[2] : 0; C <= (B == int_ipt[1] ? int_ipt[2]
                    : 255); C++) {
                    for (int D = C == int_ipf[2] ? int_ipf[3] : 0; D <= (C == int_ipt[2] ? int_ipt[3]
                        : 255); D++) {
                        ips.add(new String(A + "." + B + "." + C + "." + D));
                    }
                }
            }
        }
        return ips;
    }

    /**
     * 把long类型的Ip转为一般Ip类型：xx.xx.xx.xx
     *
     * @param ip
     * @return
     */
    public static String getIpFromLong(Long ip) {
        String s1 = String.valueOf((ip & 4278190080L) / 16777216L);
        String s2 = String.valueOf((ip & 16711680L) / 65536L);
        String s3 = String.valueOf((ip & 65280L) / 256L);
        String s4 = String.valueOf(ip & 255L);
        return s1 + "." + s2 + "." + s3 + "." + s4;
    }

    /**
     * 把xx.xx.xx.xx类型的转为long类型的
     *
     * @param ip
     * @return
     */
    public static Long getIpFromString(String ip) {
        Long ipLong = 0L;
        String ipTemp = ip;
        ipLong = ipLong * 256
            + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
            + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
            + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256 + Long.parseLong(ipTemp);
        return ipLong;
    }

    /**
     * 根据掩码位获取掩码
     *
     * @param maskBit
     *        掩码位数，如"28"、"30"
     * @return
     */
    public static String getMaskByMaskBit(String maskBit) {
        return StringUtils.isEmpty(maskBit) ? "error, maskBit is null !"
            : IpUtils.maskBitMap().get(maskBit);
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30
     *
     * @param ip
     *        给定的IP，如218.240.38.69
     * @param maskBit
     *        给定的掩码位，如30
     * @return 起始IP的字符串表示
     */
    public static String getBeginIpStr(String ip, String maskBit) {
        return IpUtils.getIpFromLong(IpUtils.getBeginIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30
     *
     * @param ip
     *        给定的IP，如218.240.38.69
     * @param maskBit
     *        给定的掩码位，如30
     * @return 起始IP的长整型表示
     */
    public static Long getBeginIpLong(String ip, String maskBit) {
        return IpUtils.getIpFromString(ip)
            & IpUtils.getIpFromString(IpUtils.getMaskByMaskBit(maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP 如 IP串 218.240.38.69/30
     *
     * @param ip
     *        给定的IP，如218.240.38.69
     * @param maskBit
     *        给定的掩码位，如30
     * @return 终止IP的字符串表示
     */
    public static String getEndIpStr(String ip, String maskBit) {
        return IpUtils.getIpFromLong(IpUtils.getEndIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP 如 IP串 218.240.38.69/30
     *
     * @param ip
     *        给定的IP，如218.240.38.69
     * @param maskBit
     *        给定的掩码位，如30
     * @return 终止IP的长整型表示
     */
    public static Long getEndIpLong(String ip, String maskBit) {
        return IpUtils.getBeginIpLong(ip, maskBit)
            + ~IpUtils.getIpFromString(IpUtils.getMaskByMaskBit(maskBit));
    }

    /**
     * 根据子网掩码转换为掩码位 如 255.255.255.252转换为掩码位 为 30
     *
     * @param netmarks
     * @return
     */
    public static int getNetMask(String netmarks) {
        StringBuffer sbf;
        String str;
        int inetmask = 0, count = 0;
        String[] ipList = netmarks.split("\\.");
        for (String element : ipList) {
            sbf = IpUtils.toBin(Integer.parseInt(element));
            str = sbf.reverse().toString();
            count = 0;
            for (int i = 0; i < str.length(); i++) {
                i = str.indexOf('1', i);
                if (i == -1) {
                    break;
                }
                count++;
            }
            inetmask += count;
        }
        return inetmask;
    }

    /**
     * 计算子网大小
     *
     * @param maskBit
     *        掩码位
     * @return
     */
    public static int getPoolMax(int maskBit) {
        if (maskBit <= 0 || maskBit >= 32) {
            return 0;
        }
        return (int) Math.pow(2, 32 - maskBit) - 2;
    }

    private static StringBuffer toBin(int x) {
        StringBuffer result = new StringBuffer();
        result.append(x % 2);
        x /= 2;
        while (x > 0) {
            result.append(x % 2);
            x /= 2;
        }
        return result;
    }

    /*
     * 存储着所有的掩码位及对应的掩码 key:掩码位 value:掩码（x.x.x.x）
     */
    private static Map<String, String> maskBitMap() {
        Map<String, String> maskBit = new HashMap<>();
        maskBit.put("1", "128.0.0.0");
        maskBit.put("2", "192.0.0.0");
        maskBit.put("3", "224.0.0.0");
        maskBit.put("4", "240.0.0.0");
        maskBit.put("5", "248.0.0.0");
        maskBit.put("6", "252.0.0.0");
        maskBit.put("7", "254.0.0.0");
        maskBit.put("8", "255.0.0.0");
        maskBit.put("9", "255.128.0.0");
        maskBit.put("10", "255.192.0.0");
        maskBit.put("11", "255.224.0.0");
        maskBit.put("12", "255.240.0.0");
        maskBit.put("13", "255.248.0.0");
        maskBit.put("14", "255.252.0.0");
        maskBit.put("15", "255.254.0.0");
        maskBit.put("16", "255.255.0.0");
        maskBit.put("17", "255.255.128.0");
        maskBit.put("18", "255.255.192.0");
        maskBit.put("19", "255.255.224.0");
        maskBit.put("20", "255.255.240.0");
        maskBit.put("21", "255.255.248.0");
        maskBit.put("22", "255.255.252.0");
        maskBit.put("23", "255.255.254.0");
        maskBit.put("24", "255.255.255.0");
        maskBit.put("25", "255.255.255.128");
        maskBit.put("26", "255.255.255.192");
        maskBit.put("27", "255.255.255.224");
        maskBit.put("28", "255.255.255.240");
        maskBit.put("29", "255.255.255.248");
        maskBit.put("30", "255.255.255.252");
        maskBit.put("31", "255.255.255.254");
        maskBit.put("32", "255.255.255.255");
        return maskBit;
    }

    /**
     * 根据掩码位获取掩码
     *
     * @param masks
     * @return
     */
    public static String getMaskByMaskBit(int masks) {
        String ret = "";
        if (masks == 1) {
            ret = "128.0.0.0";
        } else if (masks == 2) {
            ret = "192.0.0.0";
        } else if (masks == 3) {
            ret = "224.0.0.0";
        } else if (masks == 4) {
            ret = "240.0.0.0";
        } else if (masks == 5) {
            ret = "248.0.0.0";
        } else if (masks == 6) {
            ret = "252.0.0.0";
        } else if (masks == 7) {
            ret = "254.0.0.0";
        } else if (masks == 8) {
            ret = "255.0.0.0";
        } else if (masks == 9) {
            ret = "255.128.0.0";
        } else if (masks == 10) {
            ret = "255.192.0.0";
        } else if (masks == 11) {
            ret = "255.224.0.0";
        } else if (masks == 12) {
            ret = "255.240.0.0";
        } else if (masks == 13) {
            ret = "255.248.0.0";
        } else if (masks == 14) {
            ret = "255.252.0.0";
        } else if (masks == 15) {
            ret = "255.254.0.0";
        } else if (masks == 16) {
            ret = "255.255.0.0";
        } else if (masks == 17) {
            ret = "255.255.128.0";
        } else if (masks == 18) {
            ret = "255.255.192.0";
        } else if (masks == 19) {
            ret = "255.255.224.0";
        } else if (masks == 20) {
            ret = "255.255.240.0";
        } else if (masks == 21) {
            ret = "255.255.248.0";
        } else if (masks == 22) {
            ret = "255.255.252.0";
        } else if (masks == 23) {
            ret = "255.255.254.0";
        } else if (masks == 24) {
            ret = "255.255.255.0";
        } else if (masks == 25) {
            ret = "255.255.255.128";
        } else if (masks == 26) {
            ret = "255.255.255.192";
        } else if (masks == 27) {
            ret = "255.255.255.224";
        } else if (masks == 28) {
            ret = "255.255.255.240";
        } else if (masks == 29) {
            ret = "255.255.255.248";
        } else if (masks == 30) {
            ret = "255.255.255.252";
        } else if (masks == 31) {
            ret = "255.255.255.254";
        } else if (masks == 32) {
            ret = "255.255.255.255";
        }
        return ret;
    }

}
