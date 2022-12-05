package com.example.linemessagereply.listener;

import java.io.IOException;

public interface MessageListener{
	public void text(String replyToken, String text, String UserId);
	public void image(String replyToken, String id);
	public void video(String replyToken, String id);
	public void audio(String replyToken, String id, long duration);
	public void file(String replyToken, String id, String fileName, long fileSize);
	public void location(String replyToken, String title, String address, double latitude, double longitude);
	public void sticker(String replyToken, String packageId, String stickerId);
}
