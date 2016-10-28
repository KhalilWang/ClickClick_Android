package com.xaut.khalil.clickclick;

import android.util.Log;

import java.util.Random;

/**
 * Created by Khalil on 2016/10/21.
 */
public class DataAdapter {
    public StudentData[] datas;
    private int nowIn;
    private int nowOut;
    private enum OPTS{
        ALL,
        HALF,
        ONETHIRD,
        ONEFIFTH
    };
    private OPTS mode;
    public StudentData[] realDatas;
    private int stuQuatity;

    DataAdapter(int studentQuantity, String opt){
        datas = new StudentData[studentQuantity];
        stuQuatity = studentQuantity;
        nowIn = 0;
        nowOut = -1;
        setMode(opt);
    }

    public boolean insertData(String name, String num, String picUrl, String cla){
        try{
            datas[nowIn] = new StudentData(num, name, picUrl, cla);
            nowIn++;
        }catch(IndexOutOfBoundsException e){
            Log.d("Error", e.getMessage());
            return false;
        }
        return true;
    }

    private void setMode(String opt){
        switch(opt){
            case "20%": mode = OPTS.ONEFIFTH;   break;
            case "50%": mode = OPTS.HALF;       break;
            case "30%": mode = OPTS.ONETHIRD;   break;
            default:    mode = OPTS.ALL;        break;
        }
    }


    //别忘了使用之前要先init()！
    public void init() {
        switch(mode){
            case ALL:
                realDatas = datas; break;
            case HALF:
                genRealDatas(0.5); break;
            case ONEFIFTH:
                genRealDatas(0.2); break;
            case ONETHIRD:
                genRealDatas(0.3); break;
        }
    }

    private void genRealDatas(double quantity){

        boolean[] mark = new boolean[stuQuatity];

        for(int i = 0; i < stuQuatity; i++){
            mark[i] = false;
        }

        double total = stuQuatity * quantity;
        Log.d("ERROR", "index :" + Double.toString(total));

        Random rand = new Random();
        realDatas = new StudentData[(int)total + 1];
        int index = 0;

        for(int i = 0; i < total; i++){
            int num;
            do{
                num = rand.nextInt(stuQuatity);

            }while(mark[num]);

            mark[num] = true;

            Log.d("ERROR", "index :" + Integer.toString(index));
            Log.d("ERROR", "num :" + Integer.toString(index));
            realDatas[index] = datas[num];

            index++;
        }
    }

    public StudentData getOneData() {
        try {
            nowOut++;
            return realDatas[nowOut];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
