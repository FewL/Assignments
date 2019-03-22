package com.tank.newsreader;

import java.util.HashMap;
import java.util.Map;
public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    /*
    自己的id
    */
    private static final String APP_ID = "20190322000280145";
    private static final String SECURITY_KEY = "Q7c9pRKsjsBrvFugLMZ5";

    private String appid;
    private String securityKey;

    public TransApi() {
        this.appid = APP_ID;
        this.securityKey = SECURITY_KEY;
    }

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    //这里传入的是一个Map类
    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String,String> buildParams(String query,String from,String to){
        Map<String,String> params = new HashMap<String,String>();
        params.put("q",query);
        params.put("from",from);
        params.put("to",to);

        params.put("appid",appid);

        //随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt",salt);

        //签名
        String src = appid + query + salt +securityKey;//加密前的原文
        params.put("sign",MD5.md5(src));//搞了一个MD5的加密算法

        return params;
    }
}
