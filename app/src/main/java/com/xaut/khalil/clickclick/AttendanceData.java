package com.xaut.khalil.clickclick;

/**
 * Created by Khalil on 2016/10/29.
 */
public class AttendanceData {
    private String Sid;
    private String Cid;
    private String Atime;
    private String Aresult;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getAtime() {
        return Atime;
    }

    public void setAtime(String atime) {
        Atime = atime;
    }

    public String getAresult() {
        return Aresult;
    }

    public void setAresult(String aresult) {
        Aresult = aresult;
    }

    public AttendanceData(String sid, String cid, String atime, String aresult) {
        Sid = sid;
        Cid = cid;
        Atime = atime;
        Aresult = aresult;
    }
}
