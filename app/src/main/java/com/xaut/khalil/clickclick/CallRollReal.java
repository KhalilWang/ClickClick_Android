package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapDropDown;
import com.beardedhen.androidbootstrap.BootstrapText;
import com.beardedhen.androidbootstrap.api.view.BootstrapTextView;

import java.util.List;
import java.util.Random;

import static com.beardedhen.androidbootstrap.font.FontAwesome.FA_HEART;
import static com.beardedhen.androidbootstrap.font.FontAwesome.FA_TWITTER;
import static com.beardedhen.androidbootstrap.font.FontAwesome.FA_USERS;

/**
 * Created by Khalil on 2016/5/23.
 */


public class CallRollReal extends Activity{


    AppData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.call_roll_real);


        data = Login.data;

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

        /*     插入数据
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
        StudentData tmp = adapter.getOneData();
        flash_tv.setText(tmp.getScla() + tmp.getSname() + tmp.getSnum());
        flash_tv.startFlashing(true, AwesomeTextView.AnimationSpeed.FAST);

        //Set dropdown
        BootstrapDropDown option_dp = (BootstrapDropDown) findViewById(R.id.option_dp);
        option_dp.setBackgroundColor(0xFFFFFF);

        //Set Button
        Button ok_btn = (Button) findViewById(R.id.OK);
        Button ng_btn = (Button) findViewById(R.id.NG);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CallRollReal.this, "Ok is pressed!", Toast.LENGTH_SHORT).show();
                StudentData tmp = adapter.getOneData();
                if(tmp != null){
                    flash_tv.setText(tmp.getScla() + tmp.getSname() + tmp.getSnum());
                }else{
                    flash_tv.setText("点完啦");
                    new Handler().postDelayed(r, 2000);
                }

            }
        });

        ng_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CallRollReal.this, "No is pressed!", Toast.LENGTH_SHORT).show();
                StudentData tmp = adapter.getOneData();
                if(tmp != null){
                    flash_tv.setText(tmp.getScla() + tmp.getSname() + tmp.getSnum());
                }else{
                    flash_tv.setText("点完啦");
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
