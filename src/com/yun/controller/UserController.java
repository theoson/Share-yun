package com.yun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yun.pojo.User;
import com.yun.service.FileService;
import com.yun.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	/**
	 * 登录
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, User user) {

		return "login";
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, User user){
        if(user.getUsername() == null || user.getPassword() == null||user.getUsername() == "" || user.getPassword() == ""){
            return "regist";
        }else{
            boolean isSuccess = userService.addUser(user);
            if(isSuccess){
            fileService.addNewNameSpace(request, user.getUsername());
            return "login";
        }else{
            request.setAttribute("msg", "注册失败");
            return "regist";
        }
    }
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/user/login.action";
	}

}
