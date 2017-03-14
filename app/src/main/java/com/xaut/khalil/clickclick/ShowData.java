package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Khalil on 2017/3/13.
 */
public class ShowData extends Activity{


    private List<AttendanceData_Shown> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_data);

        TextView tv = (TextView)findViewById(R.id.show_data_tv);


        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        tv.setText("点名结果");

        AppData.show_data_opts type = (AppData.show_data_opts)bundle.get("type");
        String data = bundle.getString("data");

        initList(type, data);

        RecyclerView rv = (RecyclerView)findViewById(R.id.show_data_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        ShowDataAdapter adapter = new ShowDataAdapter(dataList);
        rv.setAdapter(adapter);


    }

    private void initList(AppData.show_data_opts type, String data){

        switch (type){
            case TODAY:
                try {
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
                                    oneDataJson.getString("sclass")
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

        }

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
