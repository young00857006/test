package com.example.linemessagereply.controller;

import com.example.linemessagereply.entity.Member;
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
    private MemberService memberService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        String message = "memberService.getAll().toString()";
        for(Member i :memberService.getAll()){
            message += i.toString() + "\n";
        }
        return new ResponseEntity<String>("Hello J A V A"+message, HttpStatus.OK);

    }

    @GetMapping("/member")
    public List<Member> getAllMember(){
        return memberService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deletMember(@PathVariable("id") String userId){
        memberService.deleteMember(userId);
    }

    @PostMapping("/supervisorlogin")
    public boolean supervisorloginlogin(@RequestParam String account, @RequestParam String password){
        System.out.println(account + password);
        return account.contentEquals("super")&&password.contentEquals("super") ? true : false;
    }

    @PostMapping("/update")
    public void replaceMember(@RequestBody Member member){
        memberService.replaceMember(member);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Member request){
        return memberService.login(request);
    }
}
