package com.example.bing.yiji.dbmanager;

import android.content.Context;

import com.payment.DaoMaster;
import com.payment.PaymentDao;
import com.payment.entity.Payment;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class CommonUtils {
    private DaoManager manager;

    public CommonUtils(Context context){
        manager = DaoManager.getInstance();
        manager.init(context);
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

    public boolean deltePayment(Payment payment){
        try{
            manager.getDaoSession().delete(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Payment> listAllPayments(){
        return manager.getDaoSession().loadAll(Payment.class);
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
