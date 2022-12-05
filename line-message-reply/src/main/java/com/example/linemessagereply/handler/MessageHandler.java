package com.example.linemessagereply.handler;

import com.example.linemessagereply.entity.Sensor;
import com.example.linemessagereply.ideachain.GetApi;
import com.example.linemessagereply.service.MemberService;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.linemessagereply.listener.MessageListener;

import line.robot.message_object.BaseMessage;
import line.robot.message_object.StickerMessage;
import line.robot.message_object.TextMessage;
import line.robot.message_object.api.ReplyMessage;
import line.robot.message_object.http.LineConnector;

@Component
public class MessageHandler {

	@Value("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
	private String LINE_TOKEN;

	@Autowired
	private MemberService MemberService;

	public void doAction(JSONObject event) {
		doAction(this.messageListener, event);
	}
	public void doAction(MessageListener messageListener, JSONObject event) {
//		System.out.println(event.getJSONObject("source").getString("userId"));

		switch (event.getJSONObject("message").getString("type")) {
		case "text":
			messageListener.text(event.getString("replyToken"), event.getJSONObject("message").getString("text"),event.getJSONObject("source").getString("userId"));
			break;
		case "image":
			messageListener.image(event.getString("replyToken"), event.getJSONObject("message").getString("id"));
			break;
		case "video":
			messageListener.video(event.getString("replyToken"), event.getJSONObject("message").getString("id"));			
			break;
		case "audio":
			messageListener.audio(event.getString("replyToken"), event.getJSONObject("message").getString("id"), event.getJSONObject("message").getLong("duration"));
			break;
		case "file":
			messageListener.file(event.getString("replyToken"), event.getJSONObject("message").getString("id"), event.getJSONObject("message").getString("fileName"), event.getJSONObject("message").getLong("fileSize"));
			break;
		case "location":
			messageListener.location(event.getString("replyToken"), event.getJSONObject("message").getString("title"), event.getJSONObject("message").getString("address"), event.getJSONObject("message").getDouble("latitude"), event.getJSONObject("message").getDouble("longitude"));
			break;
		case "sticker":
			messageListener.sticker(event.getString("replyToken"), event.getJSONObject("message").getString("packageId"), event.getJSONObject("message").getString("stickerId"));
			break;

		}
	}
	
	private MessageListener messageListener = new MessageListener() {
		
		@Override
		public void video(String replyToken, String id) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "video");
		}

		@Override
		public void text(String replyToken, String text, String UserId){
			/*test*/
			if(text.contains("綁定裝置")){
				String linkToken="";
				OkHttpClient client = new OkHttpClient().newBuilder()
						.build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
						.url("https://api.line.me/v2/bot/user/U3b03057f85dec9078836bf82eafade5f/linkToken")
						.method("POST", body)
						.addHeader("Authorization", "Bearer aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
						.build();
				try {
					Response response = client.newCall(request).execute();
					JSONObject object= new JSONObject(response.body().string());
					System.out.println("綁定用戶的linktoken"+object.get("linkToken"));
					linkToken = (String) object.get("linkToken");
				}
				catch (Exception e){

				}

				OkHttpClient client2 = new OkHttpClient().newBuilder()
						.build();
				MediaType mediaType2 = MediaType.parse("application/json");
				RequestBody body2 = RequestBody.create(mediaType2, "{\"to\": \""+UserId+"\",\"messages\": [{\"type\": \"template\",\"altText\": \"Account Link\",\"template\": {\"type\": \"buttons\",\"text\": \"Account Link\",\"actions\": [{\"type\": \"uri\",\"label\": \"Account Link\",\"uri\": \"https://young00857006.github.io/line-chatbot/inputdata.html?linkToken="+linkToken+"&UserId="+UserId+"\"}]}}]}");
				Request request2 = new Request.Builder()
						.url("https://api.line.me/v2/bot/message/push")
						.method("POST", body2)
						.addHeader("Content-Type", "application/json")
						.addHeader("Authorization", "Bearer aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
						.build();
				try {
					Response response2 = client2.newCall(request2).execute();
				}
				catch (Exception e){

				}
			}
			else if(MemberService.checkAccountLink(UserId)){//check if member is exist

				GetApi getApi = new GetApi(MemberService.getDeviceId(UserId));
				Sensor sensor = getApi.getSensor();
				String message = "";
				if(text.contains("水位")){
					message += "水位："+sensor.getLevel();
				}
				else if(text.contains("水質")){
					if(Float.parseFloat(sensor.getTds())>=500){
						message += "水質 混濁！！！";
					}
					else if(Float.parseFloat(sensor.getTds())<200){
						message += "水質 適中";
					}
					else{
						message += "水質 乾淨";
					}

				}
				else if(text.contains("溫度")){
					message += "溫度："+sensor.getTemp() + "ºc";
				}
				else if(text.contains("紫外線")){
					if(Float.parseFloat(sensor.getUVlevel())>795){
						message += "紫外光 危險！！！";
					}
					else if(Float.parseFloat(sensor.getUVlevel())<318){
						message += "紫外線 適中";
					}
					else{
						message += "紫外線 安全";
					}
				}
				else if(text.contains("濕度")){
					message += "濕度："+sensor.getHumd() + "%";
				}
				if(text.contains("網址")){
					message = MemberService.getUrl(UserId);
					LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[]{new TextMessage(message)}));
				}
				else {
					LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[]{new TextMessage(message), new TextMessage("檢測時間：" + sensor.getDate())}));//sensor.getNameValue()為訊息的內容
				}
			}

			/*test*/
//			else if(text.contains("水質")){
//				String message ="You haven't Login!!";
//				if(!Memberrepo.findByUserId(UserId).isEmpty()){
//					for(WaterQuality i :WaterQualityrepo.findAll()){
//						message = i.getNO() + i.getDATETIME() + i.getTDSvalue();
//					}
//					System.out.println(message);
//					LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[]{new TextMessage(message)}));
//				}
//				else {
//					LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[]{new TextMessage(message)}));
//				}
//			}
			/*test*/

		}

		@Override
		public void sticker(String replyToken, String packageId, String stickerId) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "sticker");
			LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[] {new TextMessage("看不懂~請在跟我說一次"), new StickerMessage("11538", "51626532")}));
		}
		
		@Override
		public void location(String replyToken, String title, String address, double latitude, double longitude) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "location");
		}
		
		@Override
		public void image(String replyToken, String id) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "image");
		}
		
		@Override
		public void file(String replyToken, String id, String fileName, long fileSize) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "file");
		}
		
		@Override
		public void audio(String replyToken, String id, long duration) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "file");
		}
	};
}
