package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapText;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.beardedhen.androidbootstrap.font.FontAwesome.FA_USERS;

/**
 * Created by Khalil on 2016/5/23.
 */


public class CallRollReal extends Activity{


    AppData data;
    StudentData present_student_data;
    List<AttendanceData> rstList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.call_roll_real);


        data = Login.data;

        //Init List
        rstList = new LinkedList<>();


        //Set title
        Bundle bdl = getIntent().getExtras();
        String rst = bdl.getString("data");
        String opt = "";

        //Init Data
        int Length = 5;


        final DataAdapter adapter;

        if(rst.equals("随机点名")){
            opt = bdl.getString("rst");
            Toast.makeText(CallRollReal.this, opt + "点名", Toast.LENGTH_SHORT).show();

            adapter = new DataAdapter(Length, opt);
            // Init Random Module
            /*Random ran = new Random();
            switch(opt){
                case "20%":
            }*/

        }else{
            adapter = new DataAdapter(Length, "");
        }

        List<StudentData> list = data.handler.generateStudentData();

        for(int i = 0; i < list.size(); i++){
            adapter.insertData(list.get(i).getSname(),
                    list.get(i).getSnum(),
                    list.get(i).getSurl(),
                    list.get(i).getScla());
        }

        /*     测试数据
        adapter.insertData("张三", "140001", "", "物网1");
        adapter.insertData("李四", "140002", "", "物网1");
        adapter.insertData("王五", "140003", "", "物网1");
        adapter.insertData("赵六", "140004", "", "物网1");
        adapter.insertData("郑七", "140005", "", "物网1");
        */
        adapter.init();

        AwesomeTextView title_tv = (AwesomeTextView)findViewById(R.id.real_callroll_title);
        BootstrapText text = new BootstrapText.Builder(this)
                .addText(rst)
                .addFontAwesomeIcon(FA_USERS)
                .build();
        title_tv.setBootstrapText(text);

        //Set flashing text view
        final AwesomeTextView flash_tv =  (AwesomeTextView)findViewById(R.id.coll_roll_real_flashtv);
        final TextView secondtv = (TextView) findViewById(R.id.callrollreal_secondtv);
        StudentData first_stu = adapter.getOneData();
        present_student_data = first_stu;
        flash_tv.setText( first_stu.getSname());
        secondtv.setText(first_stu.getScla() + " 学号:" + first_stu.getSnum());
        flash_tv.startFlashing(true, AwesomeTextView.AnimationSpeed.FAST);


        //Set Button
        final Button ok_btn = (Button) findViewById(R.id.OK);
        final Button ng_btn = (Button) findViewById(R.id.NG);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CallRollReal.this, "Ok is pressed!", Toast.LENGTH_SHORT).show();
                String sid = present_student_data.getSnum();
                String cid = data.presentCourseId;
                String aresult = "yes";

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String atime = df.format(new Date());

                AttendanceData oneAData = new AttendanceData(sid, cid, atime, aresult);

                rstList.add(oneAData);

                present_student_data = adapter.getOneData();
                if(present_student_data != null){
                    flash_tv.setText( present_student_data.getSname());
                    secondtv.setText(present_student_data.getScla() + " 学号:" + present_student_data.getSnum());
                }else{
                    flash_tv.setText("点完啦");
                    secondtv.setText("");
                    ng_btn.setClickable(false);
                    ok_btn.setClickable(false);
                    ng_btn.setVisibility(View.INVISIBLE);
                    ok_btn.setVisibility(View.INVISIBLE);

                    //处理数据
                    String rstData = data.handler.generateAttendance(rstList);
                    Log.d("233", "GenAttendanceRst:" + rstData);
                    //发送Json数据
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("data", rstData);

                    webRequest rq= new webRequest();
                    try {
                        Toast.makeText(CallRollReal.this, rq.submitPostData(data.postAttendanceUrl, params, "utf-8"), Toast.LENGTH_SHORT);
                    } catch (MalformedURLException e) {
                        Log.d("233", "Postattendance" + e.getMessage());
                    }

                    new Handler().postDelayed(r, 2000);
                }

            }
        });

        ng_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CallRollReal.this, "Ok is pressed!", Toast.LENGTH_SHORT).show();
                String sid = present_student_data.getSnum();
                String cid = data.presentCourseId;
                String aresult = "no";

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String atime = df.format(new Date());

                AttendanceData oneAData = new AttendanceData(sid, cid, atime, aresult);

                rstList.add(oneAData);

                present_student_data = adapter.getOneData();
                if(present_student_data != null){
                    flash_tv.setText( present_student_data.getSname());
                    secondtv.setText(present_student_data.getScla() + " 学号:" + present_student_data.getSnum());
                }else{
                    flash_tv.setText("点完啦");
                    secondtv.setText("");
                    ng_btn.setClickable(false);
                    ok_btn.setClickable(false);
                    ng_btn.setVisibility(View.INVISIBLE);
                    ok_btn.setVisibility(View.INVISIBLE);

                    //处理数据
                    String rstData = data.handler.generateAttendance(rstList);
                    Log.d("233", "GenAttendanceRst:" + rstData);


                    //发送Json数据
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("data", rstData);

                    webRequest rq= new webRequest();
                    try {
                        Toast.makeText(CallRollReal.this, rq.submitPostData(data.postAttendanceUrl, params, "utf-8"), Toast.LENGTH_SHORT);
                    } catch (MalformedURLException e) {
                        Log.d("233", "Postattendance" + e.getMessage());
                    }

                    new Handler().postDelayed(r, 2000);
                }

            }
        });

    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };
}
