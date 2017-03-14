package com.xaut.khalil.clickclick;

/**
 * Created by Khalil on 2017/3/13.
 */
public class AttendanceData_Shown {
    private String Sname;
    private String Cname;
    private String Sclas;
    private String Aresult;
    private String Atime;

    public String getAtime() {
        return Atime;
    }

    public void setAtime(String atime) {
        Atime = atime;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getSclas() {
        return Sclas;
    }

    public void setSclas(String sclas) {
        Sclas = sclas;
    }

    public String getAresult() {
        return Aresult;
    }

    public void setAresult(String aresult) {
        Aresult = aresult;
    }

    public AttendanceData_Shown(String sname, String cname, String aresult, String sclas, String atime) {
        Sname = sname;
        Cname = cname;
        Aresult = aresult;
        Sclas = sclas;
        Atime = atime;
    }
}
