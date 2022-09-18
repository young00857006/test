package com.example.linemessagereply.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
public class Member {

    private String deviceId;
    private String accessToken;
    private String url;
    private String address;
    private String nonce;
    private String userId;

    public Member(){}

    public Member(String DeviceId, String AccessToken, String url,String address,String nonce,String userId){
        this.deviceId = DeviceId;
        this.accessToken =AccessToken;
        this.url = url;
        this.address = address;
        this.nonce = nonce;
        this.userId = userId;
    }



    public Member(String deviceId, String accessToken){
        this.deviceId = deviceId;
        this.accessToken = accessToken;
    }



    public void setDeviceId(String DeviceId){
        this.deviceId = DeviceId;
    }
    public void setAccessToken(String AccessToken){
        this.accessToken =AccessToken;
     }
     public void seturl(String url){
        this.url = url;
     }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNonce(String nonce){
        this.nonce = nonce;
     }
     public void  setUserId(String userId){
        this.userId = userId;
     }


     public String getDeviceId(){
        return deviceId;
     }
     public String getAccessToken(){
        return accessToken;
     }
     public String geturl(){
        return url;
     }
     public String getAddress(){return address;}
     public String getNonce(){
        return  nonce;
    }
     public String getUserId(){
        return userId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "DeviceId='" + deviceId + '\'' +
                ", AccessToken='" + accessToken + '\'' +
                ", url='" + url + '\'' +
                ", address='" + address + '\'' +
                ", nonce='" + nonce + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
