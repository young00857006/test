package com.example.linemessagereply.ideachain;

import com.example.linemessagereply.entity.Sensor;
import okhttp3.*;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GetApi {
    private String jwtToken;
    private JSONObject requset;
    private List<Sensor> sensors;
    private Sensor sensor;
    public GetApi(String deviceId, String startTime, String endTime){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n \"username\":\"warren5606@gmail.com\",\r\n \"password\":\"warren00857020\"\r\n}");
        Request request = new Request.Builder()
                .url("https://iiot.ideaschain.com.tw/api/auth/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject t = new JSONObject(response.body().string());
            this.jwtToken = t.get("token").toString();
        }
        catch (Exception e){}

        BigInteger num;
        BigInteger t = new BigInteger("3600000");
        BigInteger st = new BigInteger(startTime);
        BigInteger et = new BigInteger(endTime);
        int limit = 100;
        num = et.subtract(st);
        num = num.divide(t);

        limit = num.intValue();


        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("text/plain");
        Request request2 = new Request.Builder()
                .url("https://iiot.ideaschain.com.tw/api/plugins/telemetry/DEVICE/"+deviceId+"/values/timeseries?keys=temp,tds,level,humd,UVlevel,random_2,random_1,millis&startTs="+startTime+"&endTs="+endTime+"&limit="+limit+"")
                .addHeader("X-Authorization", "Bearer "+jwtToken)
                .build();
        try {
            Response response2 = client2.newCall(request2).execute();
            JSONObject j = new JSONObject(response2.body().string());
            sensors = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                JSONObject level = new JSONObject(j.getJSONArray("level").get(i).toString());
                JSONObject tds = new JSONObject(j.getJSONArray("tds").get(i).toString());
                JSONObject temp = new JSONObject(j.getJSONArray("temp").get(i).toString());
                JSONObject UVlevel = new JSONObject(j.getJSONArray("UVlevel").get(i).toString());
                JSONObject humd = new JSONObject(j.getJSONArray("humd").get(i).toString());
                Object jsonOb_Time = level.get("ts");
                Sensor sensor = new Sensor(level.get("value").toString(), tds.get("value").toString(),temp.get("value").toString(),UVlevel.get("value").toString(),humd.get("value").toString(),jsonOb_Time.toString(),deviceId );
                System.out.println(sensor);
                sensors.add(sensor);
            }
        }
        catch (Exception e) {
                System.out.println(e);
            }
        }
    public GetApi(String deviceId){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n \"username\":\"warren5606@gmail.com\",\r\n \"password\":\"warren00857020\"\r\n}");
        Request request = new Request.Builder()
                .url("https://iiot.ideaschain.com.tw/api/auth/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject t = new JSONObject(response.body().string());
            this.jwtToken = t.get("token").toString();
        }
        catch (Exception e){}

        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("text/plain");
        Request request2 = new Request.Builder()
                .url("https://iiot.ideaschain.com.tw/api/plugins/telemetry/DEVICE/"+deviceId+"/values/timeseries?keys=temp,tds,level,humd,UVlevel,random_2,random_1,millis")
                .addHeader("X-Authorization", "Bearer "+jwtToken)
                .build();
        try {
            Response response2 = client.newCall(request2).execute();
            JSONObject j = new JSONObject(response2.body().string());
            this.requset = j;
            this.sensor = new Sensor(getValue("level"), getValue("tds"), getValue("temp"), getValue("UVlevel"), getValue("humd"), getTime(), deviceId);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
//***************************************
    public String getValue(String key){
        Object jsonOb = requset.getJSONArray(key).get(0);
        JSONObject j2 = new JSONObject(jsonOb.toString());
        Object jsonOb2 = j2.get("value");
        return jsonOb2.toString();
    }

    public String getTime(){
        Object jsonOb = requset.getJSONArray("tds").get(0);
        JSONObject j2 = new JSONObject(jsonOb.toString());
        Object jsonOb2 = j2.get("ts");
        return jsonOb2.toString();
    }

    public Sensor getSensor() {
        return sensor;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }
}
