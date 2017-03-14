package com.xaut.khalil.clickclick;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Khalil on 2016/7/28.
 */
public class MainMenu extends ListActivity {

    AppData data;
    private List<Map<String, Object>> mData;

    private ArrayList<String> course_name_msg = new ArrayList<>();
    private ArrayList<String> course_id_msg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();

        setContentView(R.layout.main_menu);

        data = Login.data;


        // Init title bar tv
        TextView titlebar_tv = (TextView) findViewById(R.id.mainmeun_title_bar_tv);
        titlebar_tv.setText("您好," + Login.data.get_username());

/*



             //测试BTn的内容
            Log.d("233", "today Btn Clicked");

            Map<String, String> params = new HashMap<String, String>();
            params.put("tname", data.get_username());

            try {
                Log.d("233", "Start today_data request");
                String rst = webRequest.submitPostData(data.todayDataUrl, params, "utf-8");

                Toast.makeText(MainMenu.this, rst, Toast.LENGTH_SHORT);

                Log.d("233", rst);

                Intent intent  = new Intent(MainMenu.this, ShowData.class);

                intent.putExtra("data", rst);

                startActivity(intent);
z
            } catch (MalformedURLException e) {
                Log.d("233", "Today:" + e.getMessage());
                Toast.makeText(MainMenu.this, "啊哦，出现问题了", Toast.LENGTH_SHORT).show();
            }
*/


        /**
            取消了直接开始点名的按钮 替代为listviewBtn
         *
         */

        // Init start call roll btn

        /**
         *
        Button start_call_roll_btn = (Button) findViewById(R.id.mainmeun_start_callroll_btn);
        start_call_roll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent call_roll = new Intent(MainMenu.this, CallRoll.class);
                //startActivity(call_roll);

                Map<String, String> params = new HashMap<String, String>();
                params.put("cid", "2");
                webRequest rq = new webRequest();

                try {
                    Log.d("233", "Ready to post cid :" + data.getStuUrl);
                    String rst = rq.submitPostData(data.getStuUrl, params, "utf-8");
                    Log.d("233", "cstudentRst:" + rst);
                    data.handler = new JsonHandler(rst);
                    if(data.handler.isStudentCorrect()){
                        Intent call_roll = new Intent(MainMenu.this, CallRoll.class);
                        startActivity(call_roll);
                    }else{
                        Toast.makeText(MainMenu.this, data.handler.loginFailedMsg, Toast.LENGTH_SHORT);
                    }
                } catch (MalformedURLException e) {
                    Log.d("233", "Cid:" + e.getMessage());
                }


            }
        });
         */

        // Init click img
        final DrawerLayout drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        ImageView leftimg = (ImageView) findViewById(R.id.leftmenu_img);
        leftimg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.LEFT);
            }
        });

        ImageView rightimg = (ImageView) findViewById(R.id.rightmenu_img);
        rightimg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.RIGHT);
            }
        });


        // Left Meun Init

        BootstrapButton today = (BootstrapButton)findViewById(R.id.mainmenu_left_today_data);
        today.setOnClickListener(new BootstrapButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("233", "today Btn Clicked");

                Map<String, String> params = new HashMap<String, String>();
                params.put("tname", data.get_username());

                try {
                    Log.d("233", "Start today_data request");
                    String rst = webRequest.submitPostData(data.todayDataUrl, params, "utf-8");

                    //Toast.makeText(MainMenu.this, rst, Toast.LENGTH_SHORT).show();

                    Log.d("233", rst);

                    Intent intent = new Intent(MainMenu.this, ShowData.class);

                    intent.putExtra("type", AppData.show_data_opts.TODAY);

                    intent.putExtra("data", rst);

                    startActivity(intent);

                } catch (MalformedURLException e) {
                    Log.d("233", "Today:" + e.getMessage());
                    Toast.makeText(MainMenu.this, "啊哦，出现问题了", Toast.LENGTH_SHORT).show();
                }


            }
        });

        BootstrapButton course = (BootstrapButton)findViewById(R.id.mainmenu_left_course_data);
        course.setOnClickListener(new BootstrapButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("233", "course Btn Clicked");

                Intent intent = new Intent(MainMenu.this, ShowData.class);

                intent.putExtra("type", AppData.show_data_opts.CLASS);


                intent.putExtra("name", new Gson().toJson(course_name_msg));
                intent.putExtra("id", new Gson().toJson(course_id_msg));

                startActivity(intent);


                /*Map<String, String> params = new HashMap<String, String>();
                params.put("tname", data.get_username());

                try {
                    Log.d("233", "Start today_data request");
                    String rst = webRequest.submitPostData(data.todayDataUrl, params, "utf-8");

                    //Toast.makeText(MainMenu.this, rst, Toast.LENGTH_SHORT).show();

                    Log.d("233", rst);

                    Intent intent = new Intent(MainMenu.this, ShowData.class);

                    intent.putExtra("type", AppData.show_data_opts.TODAY);

                    intent.putExtra("data", rst);

                    startActivity(intent);

                } catch (MalformedURLException e) {
                    Log.d("233", "Today:" + e.getMessage());
                    Toast.makeText(MainMenu.this, "啊哦，出现问题了", Toast.LENGTH_SHORT).show();
                }
            */

            }
        });

        BootstrapButton student = (BootstrapButton)findViewById(R.id.mainmenu_left_course_student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("233", "student Btn Clicked");

                Intent intent = new Intent(MainMenu.this, ShowData.class);

                intent.putExtra("type", AppData.show_data_opts.STUDENT);


                intent.putExtra("name", new Gson().toJson(course_name_msg));
                intent.putExtra("id", new Gson().toJson(course_id_msg));

                startActivity(intent);
            }
        });



        //List view

        /*
        ListView listview = (ListView) findViewById(R.id.listview_courses);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData()));
        */

        //List View2
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }

    /*private List<String> getData(){


        List<String> params = new ArrayList<String>();


        List<CourseData> datas = data.handler.generateCourseData();

        for(int i = 0; i < datas.size(); i++){
            params.add("<" + datas.get(i).getCname() + ">\t时间:周" +
                    datas.get(i).getCtime().toCharArray()[0] +
                    "\t第" + datas.get(i).getCtime().toCharArray()[1] + "节课" +
                    "\t人数:" + datas.get(i).getCstunum());
        }

        return params;
    }*/

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //List<CourseData> datas = data.handler.generateCourseData();
        List<CourseData> datas = new JsonHandler(data.loginRst).generateCourseData();

        Map<String, Object> map;

        for(int i = 0; i < datas.size(); i++){
            map= new HashMap<String, Object>();

            map.put("title", datas.get(i).getCname());
            map.put("listview_cid_tv", datas.get(i).getCnum());
            map.put("info", "时间:周" +
                    datas.get(i).getCtime().toCharArray()[0] +
                    "\t第" + datas.get(i).getCtime().toCharArray()[1] + "节课" +
                    "\t人数:" + datas.get(i).getCstunum());

            map.put("img", R.drawable.note);

            course_id_msg.add(datas.get(i).getCnum());
            course_name_msg.add(datas.get(i).getCname());

            list.add(map);
        }

        /* EXample
        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.menu_small);
        list.add(map);
        */

        return list;
    }



    public final class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView listview_cid_tv;
        public TextView info;
        public Button viewBtn;
    }


    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();

                convertView = mInflater.inflate(R.layout.vlist2, null);
                holder.img = (ImageView)convertView.findViewById(R.id.img);
                holder.title = (TextView)convertView.findViewById(R.id.title);
                holder.info = (TextView)convertView.findViewById(R.id.info);
                holder.listview_cid_tv = (TextView)convertView.findViewById(R.id.listview_cid_tv);
                holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("title"));
            holder.info.setText((String)mData.get(position).get("info"));
            holder.listview_cid_tv.setText((String)mData.get(position).get("listview_cid_tv"));

            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    /*
                    new AlertDialog.Builder(MainMenu.this)
                            .setTitle("More")
                            .setMessage("你选中了" + String.valueOf(position))
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    setTitle("点击了对话框上的确定按钮");
                                }
                            })
                            .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    setTitle("点击了对话框上的中立按钮");
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    setTitle("点击了对话框上的取消按钮");
                                }
                            })
                            .create()
                            .show();*/
                    Map<String, String> params = new HashMap<String, String>();
                    data.presentCourseId = (String) mData.get(position).get("listview_cid_tv");
                    data.presentCourseName = (String) mData.get(position).get("title");
                    params.put("cid", data.presentCourseId);
                    webRequest rq = new webRequest();

                    try {
                        Log.d("233", "Ready to post cid :" + data.getStuUrl);
                        String rst = rq.submitPostData(data.getStuUrl, params, "utf-8");
                        Log.d("233", "cstudentRst:" + rst);
                        data.handler = new JsonHandler(rst);
                        if(data.handler.isStudentCorrect()){
                            Intent call_roll = new Intent(MainMenu.this, CallRoll.class);
                            startActivity(call_roll);
                        }else{
                            Toast.makeText(MainMenu.this, data.handler.loginFailedMsg, Toast.LENGTH_SHORT).show();
                        }
                    } catch (MalformedURLException e) {
                        Log.d("233", "Cid:" + e.getMessage());
                    }
                }

            });


            return convertView;
        }

    }





}
