package com.example.linemessagereply.handler;

import com.example.linemessagereply.entity.WaterQuality;
import com.example.linemessagereply.reppository.WaterQualityRepository;
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
	private WaterQualityRepository WaterQualityrepo;

	public void doAction(JSONObject event) {
		doAction(this.messageListener, event);
	}
	public void doAction(MessageListener messageListener, JSONObject event) {
		switch (event.getJSONObject("message").getString("type")) {
		case "text":
			messageListener.text(event.getString("replyToken"), event.getJSONObject("message").getString("text"));
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
		public void text(String replyToken, String text) {
			// TODO Auto-generated method stub
			System.out.printf("%s\t%s\n", replyToken, "text");
			/*
			String message = "gg";
			String url = "mongodb+srv://ntouiot:ntouiot@cluster0.i4m8j74.mongodb.net/?retryWrites=true&w=majority";

			if(text.contains("水質")){
				message = "";
				MongoClient mongoClient = MongoClients.create(url);
				MongoDatabase database = mongoClient.getDatabase("iot");
				MongoCollection<Document> collection = database.getCollection("waterquality");
				FindIterable<Document> findIterable = collection.find();
				MongoCursor<Document> mongoCursor = findIterable.iterator();
				while(mongoCursor.hasNext()){
					JSONObject ob = new JSONObject(mongoCursor.next().toJson());
					message += "日期："+ob.get("DATETIME")+"水質："+ob.get("TDSvalue") + "\n";
				}
			}
			*/
			if(text.contains("水質")){
				String message ="";
				for(WaterQuality i :WaterQualityrepo.findAll()){
					message += i.getNO() + i.getDATETIME() + i.getTDSvalue() + "\n";
				}
				LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[]{new TextMessage(message)}));
			}
			else {
				LineConnector.getInstance().replyMessage(LINE_TOKEN, new ReplyMessage(replyToken, new BaseMessage[]{new TextMessage(text)}));
			}
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
