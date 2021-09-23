package com.lyl.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyl.demo.model.TestData;
import com.lyl.testobject.TestObjectBuildUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    RecyclerView recyclerView;
    List<TestData> mData = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullData(getWindow().getDecorView(), TestObjectBuildUtils.get(TestData.class));
        mData = TestObjectBuildUtils.getList(TestData.class, 100);
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                fullData(holder.itemView, mData.get(position));
            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
        });
    }

    public void setText(Activity activity, int id, String value) {
        setText(activity.getWindow().getDecorView(), id, value);
    }

    public void setText(View rootView, int id, String value) {
        View view = rootView.findViewById(id);
        if (view != null && view instanceof TextView) {
            TextView textView = (TextView) view;
            if (!TextUtils.isEmpty(value)) {
                textView.setText(value);
            }
        }
    }

    public void fullData(View rootView, TestData testData) {
        setText(rootView, R.id.tv_username, testData.getUserName());
        setText(rootView, R.id.tv_user_tel, testData.getUserTel());
        setText(rootView, R.id.tv_createTime, testData.getCreateTime());
        setText(rootView, R.id.tv_lat, testData.getSvrLatitude());
        setText(rootView, R.id.tv_lon, testData.getSvrLongitude());
    }
}