package fixdata;

import java.util.List;
import java.util.stream.Collectors;

import com.ucredit.base.comm.util.obj.ObjectMapperUtils;

/**
 * <pre>
 * 
 * 
 * Created by Edwin.Zhao on 2018/03/14
 * </pre>
 */
public class SQL_1 {
    public static class InputTicket {
        public String tid;
        public boolean valid;
        public long amount;
        public String idNo;
        public String status;
    }

    public List<InputTicket> records;

    public String _expTid;
    public Integer _lendId;
    public String _idNo;
    public long _sunAmount;

    public static SQL_1 create(String ticketListPackage, Integer lendId, String idNo, String expTid,
            long sunAmount) {
        SQL_1 all = ObjectMapperUtils.fromJSON(ticketListPackage, SQL_1.class);
        all._expTid = expTid;
        all._lendId = lendId;
        all._idNo = idNo;
        all._sunAmount = sunAmount;
        return all;
    }

    public String sql() {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "INSERT INTO lend_tickets (tid, idNo, valid, systemId, amount, loanAmount, state, createTime, lastUpdateTime) VALUES \n");

        List<InputTicket> ts = this.records.stream().filter(e -> !e.tid.endsWith(this._expTid))
                .collect(Collectors.toList());

        List<InputTicket> sunList = this.records.stream().filter(e -> e.amount == this._sunAmount)
                .collect(Collectors.toList());
        String sunTid = sunList.get(sunList.size() - 1).tid;
        for (int i = 0, len = ts.size(); i < len; i++) {
            InputTicket t = ts.get(i);
            if (t.tid.equals(sunTid)) {
                t.status = "LOAN_APPROVE_AWAIT";
            } else {
                t.status = "CANCELLED";
            }
        }

        for (int i = 0, len = ts.size(); i < len; i++) {
            InputTicket t = ts.get(i);
            sb.append("('" + t.tid + "','" + t.idNo + "', " + t.valid + ", 'THREAD', " + t.amount + ".00," + t.amount
                    + ".00," + "'" + t.status + "', now(), now())");
            if (i < len - 1) {
                sb.append(", \n");
            }
        }
        sb.append(";");
        return sb.toString();
    }

}
