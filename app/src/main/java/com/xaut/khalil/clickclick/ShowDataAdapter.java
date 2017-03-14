package com.xaut.khalil.clickclick;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

import java.util.List;

/**
 * Created by Khalil on 2017/3/13.
 */
public  class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.ViewHolder>{
    private List<AttendanceData_Shown> dataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView cname;
        TextView sname;
        TextView sclas;
        AwesomeTextView aresult;
        TextView atime;

        public ViewHolder(View itemView) {
            super(itemView);

            cname = (TextView) itemView.findViewById(R.id.list_cname);
            sname = (TextView) itemView.findViewById(R.id.list_sname);
            sclas = (TextView) itemView.findViewById(R.id.list_sclas);
            atime = (TextView) itemView.findViewById(R.id.list_time);
            aresult = (AwesomeTextView) itemView.findViewById(R.id.list_aresult);
        }
    }

    public ShowDataAdapter(List<AttendanceData_Shown> datalist){
        dataList = datalist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AttendanceData_Shown data = dataList.get(position);
        holder.sname.setText(data.getSname());
        holder.cname.setText(data.getCname());
        holder.sclas.setText(data.getSclas());
        holder.atime.setText(data.getAtime());


        switch (data.getAresult()){
            case "yes": holder.aresult.setText("出勤");
                        holder.aresult.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);break;
            case "no":  holder.aresult.setText("缺勤");
                        holder.aresult.setBootstrapBrand(DefaultBootstrapBrand.DANGER);break;
            default:    holder.aresult.setText("default");
                        holder.aresult.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);break;
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}


