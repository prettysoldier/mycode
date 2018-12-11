
package java8.time;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Shuaijun He
 */
public class Time {

    public static void main(String[] args) {
        Date date = new Date();

        System.out.println(TimeZone.getDefault());
        System.out.println(date);

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println(TimeZone.getDefault());
        System.out.println(date);
    }

    public void testTimeZone01() throws Exception {
        TimeZoneDto dto = new TimeZoneDto();
        Date date = new Date();
        dto.setLoanTime1(date);
        dto.setLoanTime2(date);
        dto.setLoanTime3(date);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));//UTC or GMT 默认值
//        mapper.setTimeZone(TimeZone.getDefault()); //本地系统是Asia/Shanghai所以和GMT+8等价
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println(mapper.writeValueAsString(dto));
    }
}

class TimeZoneDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date loanTime1;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loanTime2;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date loanTime3;

    public Date getLoanTime1() {
        return this.loanTime1;
    }

    public void setLoanTime1(Date loanTime1) {
        this.loanTime1 = loanTime1;
    }

    public Date getLoanTime2() {
        return this.loanTime2;
    }

    public void setLoanTime2(Date loanTime2) {
        this.loanTime2 = loanTime2;
    }

    public Date getLoanTime3() {
        return this.loanTime3;
    }

    public void setLoanTime3(Date loanTime3) {
        this.loanTime3 = loanTime3;
    }

}