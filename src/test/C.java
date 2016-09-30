package test;

public class C {

    private static final String context = "您好：<br/><br/>您通过{}居间服务办理的借款已完成申请阶段，电子合同详见附件，请下载查阅，感谢您对{}的支持与信任，谢谢！<br/><br/><b>温馨提示：请按照合同中显示的金额每月按时还款。</b>";

    public static void main(String[] args) {
        String a = "a";
        C c = new C();
        c.a(a);
        System.out.println(a);

        System.out.println(C.context.replaceAll("\\{\\}", "人人金服"));
    }

    void a(String a) {
        a = "c";

    }
}
