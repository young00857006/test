package com.example.linemessagereply.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
public class Member {
    @Id
    private String id;
    private String deviceId;
    private String accessToken;
    private String url;
    private String address;
    private String nonce;
    private String userId;

    public Member(){}

    public Member(String id, String deviceId, String accessToken, String url,String address,String nonce,String userId){
        this.id = id;
        this.deviceId = deviceId;
        this.accessToken =accessToken;
        this.url = url;
        this.address = address;
        this.nonce = nonce;
        this.userId = userId;
    }

    public Member(String deviceId, String accessToken, String url,String address,String nonce,String userId){
        this.deviceId = deviceId;
        this.accessToken =accessToken;
        this.url = url;
        this.address = address;
        this.nonce = nonce;
        this.userId = userId;
    }



    public Member(String deviceId, String accessToken){
        this.deviceId = deviceId;
        this.accessToken = accessToken;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setdeviceId(String deviceId){
        this.deviceId = deviceId;
    }
    public void setaccessToken(String accessToken){
        this.accessToken =accessToken;
     }
     public void seturl(String url){
        this.url = url;
     }
    public void setaddress(String address) {
        this.address = address;
    }
    public void setnonce(String nonce){
        this.nonce = nonce;
     }
     public void  setuserId(String userId){
        this.userId = userId;
     }

    public String getId() {
        return id;
    }

    public String getdeviceId(){
        return deviceId;
     }
     public String getaccessToken(){
        return accessToken;
     }
     public String geturl(){
        return url;
     }
     public String getaddress(){return address;}
     public String getnonce(){
        return  nonce;
    }
     public String getuserId(){
        return userId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", url='" + url + '\'' +
                ", address='" + address + '\'' +
                ", nonce='" + nonce + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
