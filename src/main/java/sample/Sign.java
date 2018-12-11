package main.test.sample;

import com.google.gson.GsonBuilder;

@SuppressWarnings("unused")
public class Sign {
    private final static String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlW3DpnLdkKUAeJ8KYeIQFLfkPRovOCd2MB75XPbSbRP8jQPGpJ+XM26Cx7iIG6mx7MnxfkiDb8GHci7pbCk74ZfW0PUh6xi2DGT9UJM8CYDjNsqeBhVSp8ulh0HaAeVgwWYLNdHXyTFybaF/+LoLsb+wdrj6cb0Tk9KLcFKQL1l9HiqQQgZCQySi973MAwPpnuBWFxK6nBYgCUape2SgPijlwhORS3sGHVz+9tTiEUpzLIActxqFVS403N2gf7NYeXW3z0sazWdUTdUCm9szDMoQsGJQxTyvHrHkdtpyFgOaNMLtxh1xmxRQEpgU69inM5Ebx0vJBthr4OMxlFZYVQIDAQAB";
    private final static String privateKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCVbcOmct2QpQB4"
        + "nwph4hAUt+Q9Gi84J3YwHvlc9tJtE/yNA8akn5czboLHuIgbqbHsyfF+SINvwYdy"
        + "LulsKTvhl9bQ9SHrGLYMZP1QkzwJgOM2yp4GFVKny6WHQdoB5WDBZgs10dfJMXJt"
        + "oX/4uguxv7B2uPpxvROT0otwUpAvWX0eKpBCBkJDJKL3vcwDA+me4FYXErqcFiAJ"
        + "Rql7ZKA+KOXCE5FLewYdXP721OIRSnMsgBy3GoVVLjTc3aB/s1h5dbfPSxrNZ1RN"
        + "1QKb2zMMyhCwYlDFPK8eseR22nIWA5o0wu3GHXGbFFASmBTr2KczkRvHS8kG2Gvg"
        + "4zGUVlhVAgMBAAECggEANd+ZBk2rbBbREKvmRlwGiDG0bAMguxIfHkJMjSO/wevO"
        + "xdhyqa+QB87z/6WcDL0llMVJ98mU+ZnAh346UfCb0j24CSYX0HbcS6OtYHt0zchW"
        + "Is7yIidV4YEecKFhRAW62H89022Qas0VAEmVpcsNpEwEBwmx4YfVoxnpg9mAelEw"
        + "LmCuU3SDMy9O/gTKHRJ0RB7dh0lkO5+2B3zogbi612T+dry4zVj4D0rsIB38nIAD"
        + "rb/pzrPv8yRW4DcpwUq4SBRHFivQGACoZh0wdy53fkK2dqRGX01aQLDempMoPbpI"
        + "tfmkznRpZztklmKlDtd31ewJtJC/SUfrMxzB//h9gQKBgQDEpAuoj73ZDFLh+UJm"
        + "xP7mp3A6Kbqg/oLjrDuLjtzhmsralc4X632n7YiiGFQ39zeH/ReM3Y8TTZZ7PTmC"
        + "KmWxNwLEALqUhR97hZt/IPN7ou/VyoEryprNq4JV73HftC5ajSMHySKpucvo2kRr"
        + "zpPuxlyn1u/DUqnWnLJELCFuOQKBgQDCiUeisz+27FY3QA4uZiURSJ7ERga3OfDU"
        + "L8QsBhYmCtFj7tOK3HtWYhHdxJtPR+tOMcDNwIUAqpXW9StFB4yQTZ/REPiONxxJ"
        + "j94VvkO8/wrvQfy9crnfkHLec3Qn7vV3TGpCNhYg/NZhvXiBGMyDlP2tWxNNUfxZ"
        + "QM+p9u+6/QKBgEF5EXytZ5vmM46UDNzlCK5J398t17sVIwWlaiochkUD4jkHmhhy"
        + "y2Lnfw/ho+ECilXYGkhH2QSnV8xu5UAG19c6pswWJgmfjYz2PoEEuq1W4h5AD+RB"
        + "0GtciMgOMs82UhzKU3ibKtRyTYeZbSCsJqthFqa3tRtG4c/YWq7CNttZAoGBAKCT"
        + "UF4sN1t2mXPhm9MQ3Wf22ZaJduGnIfMDupncLEwagdxLNrJYbDZRnQ3BS7GVW9Wf"
        + "m9Y6I475P9W/aYgBQzWZ3WFk8MXzF5Zg+qBX/PH0U8JKZaS7HlI7mZWlFywfQt7G"
        + "ZK9ilcGE8jCbhOyK28WBJiQCGVypX/MMXF2fRyLBAoGAC3lu2vEpGb2Nu0LtudR9"
        + "mL61v2ar+Gv2ygNNeEQ4ZuOPLyeeTIYzOYQFJK4IToN6hMeSywwJW0ysRyxT9E/s"
        + "ICbBGppYWbWwWlyiohZqImIK/ACqGHG+CWP6CEEJKLOfM17OQR3QJ3DOiwUPcHVU"
        + "v9MetYvpA3tjbx5rBmsaLwU=";

    public static void main(String[] args) {
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        gb.create();
        EncodeUtils encodeUtils = EncodeUtils.getInstance(Sign.publicKeyString,
            Sign.privateKeyString, 1001);

        int[] load_id;
        load_id = new int[2];
        load_id[0] = 2;
        load_id[1] = 1;

        User user = new User();
        user.loan_id = load_id;
//        user.gender = "man";
//        user.phone = "18801020304";
//        user.name = "Cgnfex";
        //封装方法
        System.out.println(encodeUtils.getRequest(user, "{\"loan_id\":[2,1]}"));
    }

    public static class User {
        int[] loan_id;
        String name;
        String phone;
        String gender;
    }
}
