package com.xaut.khalil.clickclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Khalil on 2016/5/19.
 */
public class Login extends Activity{

    static AppData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TypefaceProvider.registerDefaultIconSets();

        data = new AppData();

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        final Button btn = (Button)findViewById(R.id.Login_btn_in_loginxml);

        final TextView err = (TextView) findViewById(R.id.login_err_tv);

        final webRequest request = new webRequest();


        Log.d("233", "Login OK");

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {

                if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(Login.this, "用户名或者密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> params = new HashMap<>();
                params.put("tname", username.getText().toString());
                params.put("password", password.getText().toString());

                try {
                    //Get Post Data

                    Log.d("233", "Ready to submit to" + data.loginUrl);
                    data.loginRst = request.submitPostData(data.loginUrl, params, "utf-8");

                Log.d("233", "loginRst:" + data.loginRst);
                //Toast.makeText(Login.this, data.loginRst, Toast.LENGTH_SHORT).show();
                AppData.handler = new JsonHandler(data.loginRst);


                //Check Login Message


                if (AppData.handler.isLoginCorrect()) {
                    Log.d("233", "Logined!");
                    data = new AppData(username.getText().toString());
                    Intent intent = new Intent(Login.this, MainMenu.class);
                    startActivity(intent);

                }else{
                    Log.d("233", "Login Failed!");
                    if(data.handler.loginFailedMsg.equals("")){
                        data.handler.loginFailedMsg = "NetWork";
                    }
                    Toast.makeText(Login.this, "登录失败 [ErrorData: " + AppData.handler.loginFailedMsg + "]" , Toast.LENGTH_SHORT).show();
                }



            } catch (MalformedURLException e) {
                Log.d("233", "Login:" + e.getMessage());
            }



                /*
                if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(Login.this, "用户名或者密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.getText().toString().equals(rst_username) && password.getText().toString().equals(rst_password)) {
                    data = new AppData(username.getText().toString());
                    Intent intent = new Intent(Login.this, MainMenu.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Login.this, "错误的用户名或者密码", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
    }


}
