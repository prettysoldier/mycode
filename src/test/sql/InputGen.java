package ;

/**
 * <pre>
 *
 *
 * Created by Edwin.Zhao on 2018/03/14
 * </pre>
 */
public class InputGen {

    public static Integer lendId = 3274389;
    public static String idNo = "445222198610122737";
    public static long sunAmount = 125200L;
    public static String ticketListPackage = "{\"systemID\":\"THREAD\",\"timestamp\":1521021058853,\"count\":5,\"records\":[{\"tid\":\"771cf791-bf73-4011-8b96-d0c281f11367\",\"createTime\":1520317892000,\"valid\":false,\"amount\":125200.0,\"idNo\":\"445222198610122737\",\"restPrincipal\":0.0,\"reservedQuota\":null,\"restQuota\":null,\"status\":\"CANCELLED\"},{\"tid\":\"f36b09e4-797b-460c-b4e9-255855213606\",\"createTime\":1520311700000,\"valid\":false,\"amount\":200000.0,\"idNo\":\"445222198610122737\",\"restPrincipal\":0.0,\"reservedQuota\":null,\"restQuota\":null,\"status\":\"CANCELLED\"},{\"tid\":\"54aa82e3-8250-45ff-93f4-ea06ff6ec438\",\"createTime\":1520919990000,\"valid\":false,\"amount\":200000.0,\"idNo\":\"445222198610122737\",\"restPrincipal\":0.0,\"reservedQuota\":null,\"restQuota\":null,\"status\":\"CANCELLED\"},{\"tid\":\"bbb92b29-8522-49c0-a57a-8c5989b59c50\",\"createTime\":1521007323000,\"valid\":true,\"amount\":74800.0,\"idNo\":\"445222198610122737\",\"restPrincipal\":0.0,\"reservedQuota\":null,\"restQuota\":null,\"status\":null},{\"tid\":\"d9ca7cb7-4c3f-429c-9b5c-3c3c67f828b5\",\"createTime\":1521007293000,\"valid\":true,\"amount\":125200.0,\"idNo\":\"445222198610122737\",\"restPrincipal\":0.0,\"reservedQuota\":null,\"restQuota\":null,\"status\":null}]}";
    public static String expTid = "6ec438";

    public static void run() {
        SQL_1 input1 = SQL_1.create(InputGen.ticketListPackage, InputGen.lendId, InputGen.idNo, InputGen.expTid, InputGen.sunAmount);
        String sql1 = input1.sql();
        System.out.println(sql1);
    }

    public static void main(String[] args) {
        InputGen.run();

    }

}
