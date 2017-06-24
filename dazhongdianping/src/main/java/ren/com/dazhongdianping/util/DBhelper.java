package ren.com.dazhongdianping.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ren.com.dazhongdianping.entity.CitynameBean;

/**
 * Created by tarena on 2017/6/22.
 */

public class DBhelper extends OrmLiteSqliteOpenHelper {
    private static DBhelper INSTANCE;
    public static DBhelper getINSTANCE(Context context){
        if (INSTANCE==null){
            synchronized (DBhelper.class){
                if (INSTANCE==null){
                    INSTANCE=new DBhelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public DBhelper(Context context) {
        super(context,"city.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //在第一次创建city.db数据库时，该方法被调用
        //创建存储数据的数据表
        try {
            TableUtils.createTableIfNotExists(connectionSource, CitynameBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        //从数据库中删除表
        try {
            TableUtils.dropTable(connectionSource,CitynameBean.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
