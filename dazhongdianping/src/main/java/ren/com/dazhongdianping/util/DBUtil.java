package ren.com.dazhongdianping.util;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import ren.com.dazhongdianping.entity.CitynameBean;

/**
 * Created by tarena on 2017/6/22.
 */

public class DBUtil {
    DBhelper dBhelper;
    Dao<CitynameBean, String> dao;

    public DBUtil(Context context) throws SQLException {
        dBhelper = new DBhelper(context);
        dao = dBhelper.getDao(CitynameBean.class);
    }

    //往数据表中插入数据
    public void insert(CitynameBean citynameBean) {
        try {
            dao.createIfNotExists(citynameBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAll(List<CitynameBean> list) {
        for (CitynameBean bean : list) {
            insert(bean);
        }
    }

    //批量插入数据
    public void insertBatch(final List<CitynameBean> list) {
        //建立连接后，一次性将数据全部写入后在断开连接
        try {
            dao.callBatchTasks(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for (CitynameBean bean : list) {
                        insert(bean);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<CitynameBean> query() {
        try {
            List<CitynameBean> citynameBeanList = dao.queryForAll();
            return citynameBeanList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据库时出现异常");

        }
    }
}
