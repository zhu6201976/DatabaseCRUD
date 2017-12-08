package com.example.administrator.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.dao.InfoDao;
import com.example.administrator.test.domain.InfoBean;

import java.util.ArrayList;

/**
 * 数据库增删改查
 * 2017年12月8日18:57:51
 */
public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private InfoDao mInfoDao;
    private ArrayList<InfoBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        lv = (ListView) findViewById(R.id.lv);
        mInfoDao = new InfoDao(this);
    }


    public void insert(View view) {
        InfoBean infoBean1 = new InfoBean();
        infoBean1.name = "Zhangsan";
        infoBean1.phone = "110";
        boolean insert1 = mInfoDao.insert(infoBean1);

        InfoBean infoBean2 = new InfoBean();
        infoBean2.name = "Lisi";
        infoBean2.phone = "120";
        boolean insert2 = mInfoDao.insert(infoBean2);

        if (insert1 && insert2) {
            Toast.makeText(this, "成功插入2条数据", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        int delete = mInfoDao.delete();
        Toast.makeText(this, "成功删除:" + delete + "条数据", Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        InfoBean infoBean = new InfoBean();
        infoBean.name = "Zhangsan";
        infoBean.phone = "13555555555";
        int update = mInfoDao.update(infoBean);
        Toast.makeText(this, "成功更新:" + update + "条数据", Toast.LENGTH_SHORT).show();
    }

    public void query(View view) {
        mList = mInfoDao.query();
        MyAdapter myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public InfoBean getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(getApplicationContext(), R.layout.listview_item, null);
                viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            InfoBean infoBean = getItem(position);
            viewHolder.tv_id.setText(infoBean._id + "");
            viewHolder.tv_name.setText(infoBean.name);
            viewHolder.tv_phone.setText(infoBean.phone);
            return convertView;
        }
    }

    private class ViewHolder {
        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_phone;
    }
}
