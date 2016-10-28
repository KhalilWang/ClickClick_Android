package com.xaut.khalil.clickclick;

/**
 * Created by Khalil on 2016/10/21.
 */
public class StudentData {
    private String Sname;
    private String Snum;
    private String Surl;
    private String Scla;

    public String getSname() {
        return Sname;
    }

    public String getSnum() {
        return Snum;
    }

    public String getSurl() {
        return Surl;
    }

    public String getScla() {
        return Scla;
    }

    StudentData(String nu, String nam, String url, String clas){
        this.Sname   = nam;
        this.Snum    = nu;
        this.Surl    = url;
        this.Scla    = clas;
    }

}
