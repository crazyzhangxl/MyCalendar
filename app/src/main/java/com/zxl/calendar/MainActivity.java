package com.zxl.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mTvTime;
    private ImageView mIvLeft,mIvRight;
    private Calendar mNowCalendar;
    private List<Date> mDateList = new ArrayList<>();
    private DataAdapter mDataAdapter;
    private int maxCount = 6 * 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDataAdapter();
        initDate();
        initListener();
    }

    private void initListener() {
        mIvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNowCalendar.add(Calendar.MONTH,1);
                solidDateList();
            }
        });

        mIvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNowCalendar.add(Calendar.MONTH,-1);
                solidDateList();
            }
        });
    }

    private void initDate() {
        // 初始化数据为当前天...
        mNowCalendar = Calendar.getInstance();
        solidDateList();
    }

    private void setHeadTime(){
        int year = mNowCalendar.get(Calendar.YEAR);
        int month = mNowCalendar.get(Calendar.MONTH) + 1;
        mTvTime.setText(year+"/"+month);
    }

    private void solidDateList(){
        mDateList.clear();
        Calendar calendar= (Calendar) mNowCalendar.clone();
        // 设置当前日期为星期1
        calendar.set(Calendar.DAY_OF_MONTH,1);
        // 上一个月的前几天显示
        int prevDays= calendar.get(Calendar.DAY_OF_WEEK)-1;
        // 移动当前日期
        calendar.add(Calendar.DAY_OF_MONTH,-prevDays);
        // 显示这样的42个日期
        for (int i=0;i<maxCount;i++){
            mDateList.add(calendar.getTime());
            // 日期往后移动
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
        mDataAdapter.setCurrentMonth(mNowCalendar.get(Calendar.MONTH));
        if (mDataAdapter != null){
            mDataAdapter.notifyDataSetChanged();
        }
        setHeadTime();
    }

    private void initDataAdapter() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 7,LinearLayoutManager.VERTICAL,false));
        mDataAdapter = new DataAdapter(this,mDateList);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycleView);
        mTvTime = findViewById(R.id.tvTime);
        mIvLeft = findViewById(R.id.ivLeft);
        mIvRight = findViewById(R.id.ivRight);
    }
}
