package com.example.linemessagereply.database;


import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;


public class WaterQualityDB {
    private MongoDatabase database;
    private ArrayList<String> waterquality;
    public WaterQualityDB()  {
        ArrayList<String> waterquality = new ArrayList<String>();

        String url = "mongodb+srv://ntouiot:ntouiot@cluster0.i4m8j74.mongodb.net/?retryWrites=true&w=majority";
//        try {
            MongoClient mongoClient = MongoClients.create(url);
            database = mongoClient.getDatabase("iot");
//        } catch (Exception e) {
//            throw new Exception();
//        }
        setDatabase();
    }

    public void setDatabase(){
        MongoCollection<Document> collection = database.getCollection("waterquality");

        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            JSONObject ob = new JSONObject(mongoCursor.next().toJson());
            System.out.println(ob.get("NO"));
            waterquality.add("日期："+ob.get("DATETIME")+"水質："+ob.get("TDSvalue"));
        }
    }
    public ArrayList<String> getWaterquality(){
        return waterquality;
    }

}
