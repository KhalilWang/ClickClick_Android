package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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

        //Bundle rst = intent.getExtras();
        //tv.setText(rst.getString("data"));

        tv.setText("点名结果");


        initList();

        RecyclerView rv = (RecyclerView)findViewById(R.id.show_data_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        ShowDataAdapter adapter = new ShowDataAdapter(dataList);
        rv.setAdapter(adapter);


    }

    private void initList(){
        AttendanceData_Shown heading = new AttendanceData_Shown("姓名", "课程", "情况", "班级");
        dataList.add(heading);
        AttendanceData_Shown wpj = new AttendanceData_Shown("王璞劼", "C语言", "yes", "物网141");
        dataList.add(wpj);
        AttendanceData_Shown wxy = new AttendanceData_Shown("王兴耀", "C语言", "no", "物网141");
        dataList.add(wxy);
        AttendanceData_Shown yqy = new AttendanceData_Shown("杨乔英", "C语言", "出勤", "物网141");
        dataList.add(yqy);
        AttendanceData_Shown sg = new AttendanceData_Shown("帅哥", "C语言", "出勤", "物网141");
        dataList.add(sg);
    }
}
