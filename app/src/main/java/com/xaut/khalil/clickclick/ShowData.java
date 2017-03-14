package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Khalil on 2017/3/13.
 */
public class ShowData extends Activity{


    private List<AttendanceData_Shown> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_data);




        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        AppData.show_data_opts type = (AppData.show_data_opts)bundle.get("type");

        initList(type, bundle);

        RecyclerView rv = (RecyclerView)findViewById(R.id.show_data_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        ShowDataAdapter adapter = new ShowDataAdapter(dataList);
        rv.setAdapter(adapter);


    }

    private void initList(AppData.show_data_opts type, Bundle bundle){

        switch (type){
            case TODAY:
                TextView tv = (TextView)findViewById(R.id.show_data_tv);
                tv.setText("当天点名结果");
                try {
                    String data = bundle.getString("data");
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray tmp = jsonObject.getJSONArray("today");
                    JSONObject status = tmp.getJSONObject(0);

                    if(status.getString("status").equals("yes")){

                        for(int i = 1; i < tmp.length(); i++){
                            JSONObject oneDataJson = tmp.getJSONObject(i);

                            AttendanceData_Shown oneData = new AttendanceData_Shown(
                                    oneDataJson.getString("sname"),
                                    oneDataJson.getString("cname"),
                                    oneDataJson.getString("aresult"),
                                    oneDataJson.getString("sclass"),
                                    oneDataJson.getString("atime")
                                    );

                            dataList.add(oneData);
                        }
                    }else{
                        Toast.makeText(ShowData.this, "查询结果为无！", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(r, 2000);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case CLASS:

                ArrayList<String> name_list = new Gson().fromJson(bundle.getString("name"), ArrayList.class);
                ArrayList<String> id_list = new Gson().fromJson(bundle.getString("id"), ArrayList.class);


                Log.d("233", "id[0]:" + id_list.get(0));

                String[] name_arr = name_list.toArray(new String[name_list.size()]);

                showSingleChoiceDialog(name_arr);

                /*String cid = id_list.get(choice);

                Map<String,String> params = new HashMap<>();
                params.put("cid", cid);

                try {
                        String data = webRequest.submitPostData(AppData.courseDataUrl, params, "utf-8");

                        Log.d("233", data);
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray tmp = jsonObject.getJSONArray("course");
                        JSONObject status = tmp.getJSONObject(0);

                        if(status.getString("status").equals("yes")){

                            for(int i = 1; i < tmp.length(); i++){
                                JSONObject oneDataJson = tmp.getJSONObject(i);

                                AttendanceData_Shown oneData = new AttendanceData_Shown(
                                        oneDataJson.getString("sname"),
                                        oneDataJson.getString("cname"),
                                        oneDataJson.getString("aresult"),
                                        oneDataJson.getString("sclass")
                                );

                                dataList.add(oneData);
                            }
                        }else{
                            Toast.makeText(ShowData.this, "查询结果为无！", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(r, 2000);
                        }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }*/


                break;

            case STUDENT:

                ArrayList<String> name_list_ = new Gson().fromJson(bundle.getString("name"), ArrayList.class);
                ArrayList<String> id_list_ = new Gson().fromJson(bundle.getString("id"), ArrayList.class);


                Log.d("233", "id[0]:" + id_list_.get(0));

                String[] name_arr_ = name_list_.toArray(new String[name_list_.size()]);

                showSingleChoiceDialog_student(name_arr_);
                break;
        }

    }

    int yourChoice;
    private void showSingleChoiceDialog(final String[] items){
        yourChoice = 0;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(ShowData.this);
        singleChoiceDialog.setTitle("选择课程");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            Toast.makeText(ShowData.this,
                                    "你选择了" + items[yourChoice],
                                    Toast.LENGTH_SHORT).show();

                            Map<String,String> params = new HashMap<>();
                            params.put("cname", items[yourChoice]);
                            TextView tv = (TextView)findViewById(R.id.show_data_tv);
                            tv.setText(items[yourChoice] + "点名情况");

                            try {
                                String data = webRequest.submitPostData(AppData.courseDataUrl, params, "utf-8");

                                Log.d("233", data);
                                JSONObject jsonObject = new JSONObject(data);
                                JSONArray tmp = jsonObject.getJSONArray("course");
                                JSONObject status = tmp.getJSONObject(0);

                                if(status.getString("status").equals("yes")){

                                    for(int i = 1; i < tmp.length(); i++){
                                        JSONObject oneDataJson = tmp.getJSONObject(i);

                                        AttendanceData_Shown oneData = new AttendanceData_Shown(
                                                oneDataJson.getString("sname"),
                                                oneDataJson.getString("cname"),
                                                oneDataJson.getString("aresult"),
                                                oneDataJson.getString("sclass"),
                                                oneDataJson.getString("atime")
                                        );

                                        dataList.add(oneData);
                                    }

                                    //Another Init!
                                    RecyclerView rv = (RecyclerView)findViewById(R.id.show_data_list);
                                    ShowDataAdapter adapter = new ShowDataAdapter(dataList);
                                    rv.setAdapter(adapter);



                                }else{
                                    Toast.makeText(ShowData.this, "查询结果为无！", Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(r, 2000);
                                }


                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        singleChoiceDialog.show();
    }


    private void showSingleChoiceDialog_student(final String[] items){
        yourChoice = 0;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(ShowData.this);
        singleChoiceDialog.setTitle("选择课程");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            Toast.makeText(ShowData.this,
                                    "你选择了" + items[yourChoice],
                                    Toast.LENGTH_SHORT).show();
                            TextView tv = (TextView)findViewById(R.id.show_data_tv);
                            tv.setText(items[yourChoice] + "学生名单");

                            Map<String,String> params = new HashMap<>();
                            params.put("cname", items[yourChoice]);
                            params.put("cid", "");

                            try {
                                String data = webRequest.submitPostData(AppData.getStuUrl, params, "utf-8");

                                Log.d("233", data);
                                JSONObject jsonObject = new JSONObject(data);
                                JSONArray tmp = jsonObject.getJSONArray("cstudents");
                                JSONObject status = tmp.getJSONObject(0);

                                if(status.getString("status").equals("yes")){

                                    for(int i = 1; i < tmp.length(); i++){
                                        JSONObject oneDataJson = tmp.getJSONObject(i);

                                        AttendanceData_Shown oneData = new AttendanceData_Shown(
                                                oneDataJson.getString("sname"),
                                                oneDataJson.getString("cname"),
                                                "yes",
                                                oneDataJson.getString("sclass"),
                                                "N/A"
                                        );

                                        dataList.add(oneData);
                                    }

                                    //Another Init!
                                    RecyclerView rv = (RecyclerView)findViewById(R.id.show_data_list);
                                    ShowDataAdapter adapter = new ShowDataAdapter(dataList);
                                    rv.setAdapter(adapter);



                                }else{
                                    Toast.makeText(ShowData.this, status.getString("message"), Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(r, 2000);
                                }


                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(ShowData.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    };
}
