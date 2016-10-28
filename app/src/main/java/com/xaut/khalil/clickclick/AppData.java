package com.xaut.khalil.clickclick;

/**
 * Created by Khalil on 2016/7/28.
 */
public class AppData {

    enum call_roll_opts
    {
        ALL,
        HALF,
        ONE_THIRD,
        ONE_FIFTH
    }

    static String tgtUrl;
    static String loginUrl;
    static String getStuUrl;

    static JsonHandler handler;

    static String username;
    static call_roll_opts call_roll_option;

    AppData(String user)
    {
        tgtUrl = "http://192.168.3.138:5000/";
        loginUrl = tgtUrl + "login";
        getStuUrl = tgtUrl + "get_cstudents";
        username = user;
        call_roll_option = call_roll_opts.ALL;
    }

    AppData()
    {   tgtUrl = "http://192.168.3.138:5000/";
        loginUrl = tgtUrl + "login";
        getStuUrl = tgtUrl + "get_cstudents";
        username = "";
        call_roll_option = call_roll_opts.ALL;
    }

    public void set_call_roll(call_roll_opts opt)
    {
        call_roll_option = opt;
    }

    public String get_username()
    {
        return username;
    }
}
