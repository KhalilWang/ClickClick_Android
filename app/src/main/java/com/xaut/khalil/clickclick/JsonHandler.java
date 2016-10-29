package com.xaut.khalil.clickclick;

/**
 * Created by Khalil on 2016/10/28.

 ***/

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.List;


public class JsonHandler {

    public String jsonData;
    public String loginFailedMsg;

    JsonHandler(String src){
        jsonData = src;
        loginFailedMsg = "";
    }

    public boolean isLoginCorrect() {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("tcourses");
            JSONObject tmp = jsonArray.getJSONObject(0);

            Log.d("233", "Status:" + tmp.getString("status"));

            if(tmp.getString("status").equals("yes")){
                return true;
            }else{
                loginFailedMsg = tmp.getString("message");
                return false;
            }
        } catch (JSONException e) {
            Log.d("233", "isLoginCorrect:" + e.getMessage());
        }

        return false;


    }

    public boolean isStudentCorrect() {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("cstudents");
            JSONObject tmp = jsonArray.getJSONObject(0);

            Log.d("233", "Status:" + tmp.getString("status"));

            if(tmp.getString("status").equals("yes")){
                return true;
            }else{
                loginFailedMsg = tmp.getString("message");
                return false;
            }
        } catch (JSONException e) {
            Log.d("233", "isStudentCorrect:" + e.getMessage());
        }

        return false;


    }


    public List<CourseData> generateCourseData(){
        try {
            JSONObject jObject = new JSONObject(jsonData);
            JSONArray jArray =  jObject.getJSONArray("tcourses");
            List<CourseData> rst = new LinkedList<>();
            for (int i = 1; i < jArray.length();i++){
                JSONObject tmp =   jArray.getJSONObject(i);
                CourseData oneData = new CourseData(tmp.getString("cid"),
                                                    tmp.getString("cname"),
                                                    tmp.getString("ctime"),
                                                    tmp.getString("cstunum"));
                rst.add(oneData);
            }
            return rst;
        } catch (JSONException e) {
            Log.d("233", "GenerateCourseData:" + e.getMessage());
        }

        return null;
    }

    public List<StudentData> generateStudentData(){
        try {
            JSONObject jObject = new JSONObject(jsonData);
            JSONArray jArray =  jObject.getJSONArray("cstudents");
            List<StudentData> rst = new LinkedList<>();
            for (int i = 1; i < jArray.length(); i++){
                JSONObject tmp =   jArray.getJSONObject(i);
                StudentData oneData = new StudentData(tmp.getString("sid"),
                                                        tmp.getString("sname"),
                                                        tmp.getString("surl"),
                                                        tmp.getString("sclass"));
                rst.add(oneData);
            }
            return rst;
        } catch (JSONException e) {
            Log.d("233", "GenerateStudentData:" + e.getMessage());
        }

        return null;
    }

    public String generateAttendance(List<AttendanceData> src){

        JSONArray jsonArray = new JSONArray();

        JSONObject rst = new JSONObject();

        try {
            for (int i = 0; i < src.size(); i++) {
                JSONObject tmp = new JSONObject();
                tmp.put("sid", src.get(i).getSid());
                tmp.put("cid", src.get(i).getCid());
                tmp.put("atime", src.get(i).getAtime());
                tmp.put("aresult", src.get(i).getAresult());
                jsonArray.put(i, tmp);
            }

            rst.put("attendances", jsonArray);

        }catch (JSONException e){
            Log.d("233", "GenAttendance:" + e.getMessage());
        }

        return rst.toString();
    }


}

