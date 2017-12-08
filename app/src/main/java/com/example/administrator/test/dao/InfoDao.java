package com.example.administrator.test.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.test.domain.InfoBean;

import java.util.ArrayList;

/**
 * Created by My on 2017/12/8.
 */

public class InfoDao {

    private MySqliteOpenHelper mSqliteOpenHelper;

    public InfoDao(Context context) {
        mSqliteOpenHelper = new MySqliteOpenHelper(context);
    }

    public boolean insert(InfoBean infoBean) {
        SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", infoBean.name);
        values.put("phone", infoBean.phone);
        long insert = db.insert("info", null, values);
        db.close();
        return insert != -1;
    }

    public int delete() {
        SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        int delete = db.delete("info", null, null);
        db.close();
        return delete;
    }

    public int update(InfoBean infoBean) {
        SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", infoBean.phone);
        int update = db.update("info", values, "name = ?", new String[]{infoBean.name});
        db.close();
        return update;
    }

    public ArrayList<InfoBean> query() {
        ArrayList<InfoBean> list = new ArrayList<>();
        InfoBean infoBean = null;
        SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                infoBean = new InfoBean();
                infoBean._id = cursor.getInt(0);
                infoBean.name = cursor.getString(1);
                infoBean.phone = cursor.getString(2);
                list.add(infoBean);
            }
            cursor.close();
        }
        db.close();
        return list;
    }
}
