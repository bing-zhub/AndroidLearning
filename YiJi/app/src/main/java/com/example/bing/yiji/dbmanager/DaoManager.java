package com.example.bing.yiji.dbmanager;

import android.content.Context;

import com.payment.DaoMaster;
import com.payment.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

public class DaoManager {
    private static final String DB_NAME = "db.sqlite";
    private volatile static DaoManager manager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;


    // 使用单例模式操作数据库对象
    public static DaoManager getInstance(){
        DaoManager instance = null;
        if(manager == null){
            synchronized (DaoManager.class){
                instance = new DaoManager();
                manager = instance;
            }
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
    }

    private DaoMaster getDaoMaster(){
            if(daoMaster == null){
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
                daoMaster = new DaoMaster(helper.getWritableDatabase());
            }
        return daoMaster;
    }

    public  DaoSession getDaoSession() {
        if(daoSession== null){
            if(daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public void closeConnection(){
        closeHelper();
        closeSession();
    }

    private void closeSession() {
        if(daoSession != null){
            daoSession.clear();
            daoSession = null;
        }
    }

    private void closeHelper() {
        if(helper!=null){
            helper.close();
            helper = null;
        }
    }

}
