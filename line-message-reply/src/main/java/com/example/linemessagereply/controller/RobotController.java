package com.example.linemessagereply.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.entity.WaterQuality;
import com.example.linemessagereply.reppository.WaterQualityRepository;
import com.example.linemessagereply.service.MemberService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.linemessagereply.handler.MessageHandler;
import org.springframework.web.servlet.view.RedirectView;


@RequestMapping("/robot")
@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
public class RobotController {
	@Value("722ec8c5cc39f700a5f3690df0a5741a")
	private String LINE_SECRET;
	@Autowired
	private MessageHandler messageHandler;
	@Autowired
	private WaterQualityRepository WaterQualityrepo;
	@Autowired
	private MemberService memberService;


	@PostMapping("/login")
	public String login(@RequestBody Member request,@RequestParam("linkToken") String linkToken, @RequestParam("UserId") String UserId){
		String url="";

		if(memberService.login(request)){
			System.out.println("綁定成功");
			Base64 base64 = new Base64();
			String encodedString = new String(base64.encode(UserId.getBytes()));
			memberService.lineAccountLink(request,UserId, encodedString);
			url = "https://access.line.me/dialog/bot/accountLink?linkToken="+linkToken+"&nonce="+encodedString;
		}
		else {
			url = "https://young00857006.github.io/line-chatbot/inputdata.html?linkToken="+linkToken+"&UserId="+UserId;
		}
		System.out.println(url);
		return url;
	}

//	@GetMapping ("/sign")
//	public RedirectView redirect(@RequestParam("linkToken") String linkToken, @RequestParam("UserId") String UserId){
//		Base64 base64 = new Base64();
//		String encodedString = new String(base64.encode(UserId.getBytes()));
//		String url = "https://access.line.me/dialog/bot/accountLink?linkToken="+linkToken+"&nonce="+encodedString;
//		return new RedirectView(url);
//	}

	@PostMapping("/messaging")
	public ResponseEntity<String> messagingAPI(@RequestHeader("x-line-signature") String X_Line_Signature, @RequestBody String requestBody) throws UnsupportedEncodingException, IOException{
        if(checkFromLine(requestBody, X_Line_Signature)) {
        	System.out.println("驗證通過");
        	JSONObject object = new JSONObject(requestBody);
        	for(int i=0; i<object.getJSONArray("events").length(); i++) {
        		if(object.getJSONArray("events").getJSONObject(i).getString("type").equals("message")) {
        			messageHandler.doAction(object.getJSONArray("events").getJSONObject(i));
        		}
        	}
            return new ResponseEntity<String>("OK", HttpStatus.OK);
        }
    	System.out.println("驗證不通過");
        return new ResponseEntity<String>("Not line platform", HttpStatus.BAD_GATEWAY);
	}
	
	public boolean checkFromLine(String requestBody, String X_Line_Signature) {
		SecretKeySpec key = new SecretKeySpec(LINE_SECRET.getBytes(), "HmacSHA256");
		Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(key);
			byte[] source = requestBody.getBytes("UTF-8");
			String signature = Base64.encodeBase64String(mac.doFinal(source));
			if(signature.equals(X_Line_Signature)) {
				return true;
			}
		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}



