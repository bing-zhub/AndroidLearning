package com.example.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoMaker {
    public static void main(String[] args) {
        // 生成数据库实体类
        Schema schema = new Schema(2, "com.payment.entity");
        addPayment(schema);
        schema.setDefaultJavaPackageDao("com.payment");
        try {
            new DaoGenerator().generateAll(schema,"/Users/bing/Desktop/github repo/AndroidLearning/YiJi/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addPayment(Schema schema){
        Entity entity = schema.addEntity("Payment");
        entity.addIdProperty();
        entity.addStringProperty("type");
        entity.addIntProperty("num");
        entity.addDateProperty("date");
        entity.addStringProperty("description");
        entity.addStringProperty("location");
    }
}
