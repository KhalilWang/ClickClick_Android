package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Khalil on 2016/5/22.
 */
public class CallRoll extends Activity{


    static String rst = "50%";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.call_roll);

        Button all_btn = (Button) findViewById(R.id.callroll_all_btn);
        Button ran_btn = (Button) findViewById(R.id.callroll_random_btn);

        RadioButton defaultrb = (RadioButton) findViewById(R.id.half_random);
        defaultrb.setChecked(true);

        RadioGroup random_select = (RadioGroup) findViewById(R.id.random_select);



        random_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.half_random:
                        rst = "50%";
                        Login.data.set_call_roll(AppData.call_roll_opts.HALF);
                        break;
                    case R.id.one_third_random:
                        rst = "30%";
                        Login.data.set_call_roll(AppData.call_roll_opts.ONE_THIRD);
                        break;
                    case R.id.one_fifth_random:
                        rst = "20%";
                        Login.data.set_call_roll(AppData.call_roll_opts.ONE_FIFTH);
                        break;
                }
            }
        });

        all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallRoll.this, CallRollReal.class);

                Bundle mBundle = new Bundle();
                mBundle.putString("data", "全部点名");
                intent.putExtras(mBundle);

                //Set appdata
                Login.data.set_call_roll(AppData.call_roll_opts.ALL);

                startActivity(intent);
            }
        });

        ran_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallRoll.this, CallRollReal.class);

                Bundle mBundle = new Bundle();
                mBundle.putString("data", "随机点名");
                mBundle.putString("rst", rst);
                intent.putExtras(mBundle);

                startActivity(intent);
            }
        });


    }
}
