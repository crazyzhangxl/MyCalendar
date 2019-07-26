package com.zxl.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 2019-07-26.
 * description:
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context mContext;
    private List<Date> mDateList = new ArrayList<>();
    private int mCurrentMonth;

    public void setCurrentMonth(int currentMonth){
        mCurrentMonth = currentMonth;
    }

    public DataAdapter(Context context, List<Date> dateList) {
        mContext = context;
        mDateList = dateList;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_date, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int i) {
        Date date = mDateList.get(i);
        viewHolder.mTvDay.setText(String.valueOf(date.getDate()));
        if (date.getMonth() == mCurrentMonth){
            viewHolder.mTvDay.setTextColor(Color.BLACK);
        }else {
            viewHolder.mTvDay.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return mDateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvDay = itemView.findViewById(R.id.tvDateDes);
        }
    }
}
