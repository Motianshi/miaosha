package com.anqi.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anqi.miaosha.domain.User;
import com.anqi.miaosha.rabbitmq.MQSender;
import com.anqi.miaosha.redis.RedisService;
import com.anqi.miaosha.redis.UserKey;
import com.anqi.miaosha.result.CodeMsg;
import com.anqi.miaosha.result.Result;
import com.anqi.miaosha.service.UserService;

@Controller
@RequestMapping("/demo")
public class SampleController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;

	@Autowired
	MQSender sender;
	
//	@RequestMapping("/mq/header")
//    @ResponseBody
//    public Result<String> header() {
//		sender.sendHeader("hello,anqi");
//        return Result.success("Hello，world");
//    }
//	
//	@RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result<String> fanout() {
//		sender.sendFanout("hello,anqi");
//        return Result.success("Hello，world");
//    }
//	
//	@RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> topic() {
//		sender.sendTopic("hello,anqi");
//        return Result.success("Hello，world");
//    }
//	
//	@RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq() {
//		sender.send("hello,anqi");
//        return Result.success("Hello，world");
//    }
	
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> home() {
        return Result.success("Hello，world");
    }
    
    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SESSION_ERROR);
    }
    
    @RequestMapping("/hello/themaleaf")
    public String themaleaf(Model model) {
        model.addAttribute("name", "Joshua");
        return "hello";
    }
    
    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
    	User user = userService.getById(1);
        return Result.success(user);
    }
    
    
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
    	userService.tx();
        return Result.success(true);
    }
    
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
    	User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }
    
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
    	User user  = new User();
    	user.setId(1);
    	user.setName("1111");
    	redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return Result.success(true);
    }
    
    
}
