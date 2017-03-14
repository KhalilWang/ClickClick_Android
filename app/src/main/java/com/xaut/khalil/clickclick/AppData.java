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

    enum show_data_opts
    {
        TODAY,
        CLASS,
        STUDENT
    }

    final String url = "http://192.168.43.184:5000/";

    static String tgtUrl;
    static String loginUrl;
    static String getStuUrl;
    static String postAttendanceUrl;
    static String todayDataUrl;
    static String courseDataUrl;

    static JsonHandler handler;
    static String presentCourseId;
    static String presentCourseName;

    static String loginRst;

    static String username;
    static call_roll_opts call_roll_option;


    AppData(String user)
    {
        tgtUrl = url;
        loginUrl = tgtUrl + "login";
        getStuUrl = tgtUrl + "get_cstudents";
        postAttendanceUrl = tgtUrl + "post_attendances";
        todayDataUrl = tgtUrl + "attendances/today";
        courseDataUrl = tgtUrl + "attendances/course";

        username = user;
        call_roll_option = call_roll_opts.ALL;
    }

    AppData()
    {   tgtUrl = url;
        loginUrl = tgtUrl + "login";
        getStuUrl = tgtUrl + "get_cstudents";
        postAttendanceUrl = tgtUrl + "post_attendances";
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
