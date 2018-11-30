package com.example.bing.yiji.dbmanager;

import android.content.Context;
import android.util.Log;

import com.example.bing.yiji.R;
import com.payment.DaoMaster;
import com.payment.PaymentDao;
import com.payment.entity.Payment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.greenrobot.dao.query.QueryBuilder;

public class CommonUtils {
    private DaoManager manager;
    public static Map<String, Integer> payment, income;
    private void setupMap(){
        payment.put("餐饮食品", R.drawable.food);
        payment.put("衣服饰品", R.drawable.clothe);
        payment.put("居家生活", R.drawable.home);
        payment.put("行车交通", R.drawable.bus);
        payment.put("休闲娱乐", R.drawable.joystick);
        payment.put("文化教育", R.drawable.book);
        payment.put("健康医疗", R.drawable.health);
        payment.put("投资支出", R.drawable.money);
        payment.put("其他支出", R.drawable.wallet);
        payment.put("设置", R.drawable.settings);
        income.put("空",R.drawable.add);
    }


    public CommonUtils(Context context){
        manager = DaoManager.getInstance();
        manager.init(context);
        payment = new HashMap<>();
        income = new HashMap<>();
        setupMap();
    }

    public boolean insertPayment(Payment payment){
        return manager.getDaoSession().insert(payment)!=-1;
    }

    public boolean insertMutiPayments(final List<Payment> payments){
        try{
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Payment p: payments){
                        manager.getDaoSession().insertOrReplace(p);
                    }
                }
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void dropAllData(){
        manager.dropAll();
    }

    public boolean updatePayment(Payment payment){
        try{
            manager.getDaoSession().update(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deletePayment(Payment payment){
        try{
            manager.getDaoSession().delete(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Payment> listAllPayments(){
        List<Payment> list = manager.getDaoSession().loadAll(Payment.class);
        return list;
    }

    public Payment findPayment(long key){
        return manager.getDaoSession().load(Payment.class, key);
    }

    public void Query1(){
        List<Payment> payments = manager.getDaoSession().queryRaw(Payment.class, "where type = ? and _id > ?", new String[]{"clothing", "100"});

    }

    public void query2(){
        QueryBuilder<Payment> builder = manager.getDaoSession().queryBuilder(Payment.class);
        builder.where((PaymentDao.Properties.Type.eq("clothing"))).list();
    }

    public List<Payment> findPaymentsByType(String type){
        try{
            QueryBuilder<Payment> builder = manager.getDaoSession().queryBuilder(Payment.class);
            List<Payment> payments = builder.where(PaymentDao.Properties.Type.eq(type)).list();
            return payments;
        }catch (Exception e){
            return null;
        }
    }






}
