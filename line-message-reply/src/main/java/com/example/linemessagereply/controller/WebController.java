package com.example.linemessagereply.controller;

import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.entity.WaterQuality;
import com.example.linemessagereply.reppository.WaterQualityRepository;
import com.example.linemessagereply.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/web", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
public class WebController {
    @Autowired
    private WaterQualityRepository WaterQualityrepo;

    @Autowired
    private MemberService memberService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        String message ="";
        for(WaterQuality i :WaterQualityrepo.findAll()){
            message += i.getNO() + i.getDATETIME() + i.getTDSvalue() + "\n";
        }
        return new ResponseEntity<String>("Hello J A V A"+message, HttpStatus.OK);

    }

//    @PostMapping("/register")
//    public String register(@RequestBody Member request){
//        if(memberService.insert(request)) {
//            return "success";
//        }
//        else{
//            return "false";
//        }
//    }

    @PostMapping("/login")
    public boolean login(@RequestBody Member request){
        return memberService.login(request);
    }
}
