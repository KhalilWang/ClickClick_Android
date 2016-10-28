package com.xaut.khalil.clickclick;

/**
 * Created by Khalil on 2016/10/28.
 */
public class CourseData {
    private String Cnum;
    private String Ctime;
    private String Cname;
    private String Cstunum;

    public CourseData(String cnum, String cname, String ctime, String cstunum) {
        Cnum = cnum;
        Ctime = ctime;
        Cstunum = cstunum;
        Cname = cname;
    }

    public String getCnum() {
        return Cnum;
    }

    public String getCstunum() {
        return Cstunum;
    }

    public String getCname() {
        return Cname;
    }

    public String getCtime() {
        return Ctime;
    }
}
