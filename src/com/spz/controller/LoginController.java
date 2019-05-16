package com.spz.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spz.entity.Users;
import com.spz.service.UsersService;
import com.spz.util.Result;

@Controller
public class LoginController {
	
	@Autowired UsersService usersService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public String login(Users users,HttpSession session,Integer yzm,HttpServletResponse resp) throws UnsupportedEncodingException {
		Users users2 = usersService.selectUserBylogin(users);
		String k = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		/*System.out.println(k+"后验证码");
		System.out.println(yzm+"前验证码"); */
		if(yzm==null) {
			//前三次无验证码正常查
			if(users2 != null) {
				if(users2.getU_isLockout()==2) {
					return Result.toClient(false, "用户已被锁定。");
				}
				if(users.getU_pwd()!=null) {
					if(users.getU_pwd().equals(users2.getU_pwd())) {
						session.setAttribute("u_id", users2.getU_id());
						users2.setU_lastLoginTime(lastLoginTime());
						usersService.updateUsers(users2);
						//把用户名添加到cookie(因为cookie不能存中文，得处理一下);
						String name=URLEncoder.encode(users2.getU_name(),"UTF-8");
						insertCookie(resp,name,users2.getU_pwd());
					}
				}
			}
		}
		if(yzm!=null){
			//判断验证码是否正确不正确返回验证码错误正确则查询
			/*Object a=Integer.parseInt(k)!=yzm ? Result.toClient(false, "验证码错误！") : (users2 == null ? null : request.getSession().setAttribute("u_id", users2.getU_id()));*/
			if(Integer.parseInt(k)==yzm) {
				//验证码正确查询
				if(users2 != null) {
					session.setAttribute("u_id", users2.getU_id());
					if(users2.getU_isLockout()==2) {
						return Result.toClient(false, "用户已被锁定");
					}
					if(users2.getU_isLockout()==1) {
						//把用户名添加到cookie
						insertCookie(resp,users2.getU_name(),users2.getU_pwd());
						users2.setU_lastLoginTime(lastLoginTime());
						usersService.updateUsers(users2);
					}
				}
			}
			if(Integer.parseInt(k)!=yzm){
				//验证码错去返回
				return Result.toClient(false, "验证码错误！","1");
			}
		}
		//如果得到的登录用户是否被锁定状态==1则给出提示
		//String count=users2.getU_isLockout()==1 ? "用户被锁定 ！请联系管理员解锁后登陆 " : "密码错误！";
		/*if(users2 !=null) {
			if(users2.getU_isLockout()==1) {
				return Result.toClient(false, "用户已被锁定");
			}
		}*/
        if(users2 ==null){
			if(users.getU_pwd()==null) {
				return Result.toClient(false, "用户不存在");
			}
		}
		return Result.toClient(users2 !=null ? true : false, users2 !=null ? users2 : "密码错误！");
	}
	@RequestMapping(value="locking",method=RequestMethod.POST)
	@ResponseBody
	public Integer locking(Users users) {
		//登录失败多次锁定用户
		return usersService.updateUsers(users);
	}
	
	//将所登录的用户名和密码存到cookie中
	public void insertCookie(HttpServletResponse resp,String val1,String val2) {
		Cookie name=new Cookie("u_name", val1);
		//设置过期时间（以秒为单位）
		name.setMaxAge(60*60);
		//设置添加到根路径下
		name.setPath("/");
		//添加Cookie
		resp.addCookie(name);
		
		Cookie pwds=new Cookie("u_pwd", val2);
		//设置过期时间（以秒为单位）
		pwds.setMaxAge(60*60);
		//设置添加到根路径下
		pwds.setPath("/");
		//添加Cookie
		resp.addCookie(pwds);
	}
	public static String lastLoginTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(new Date());
	}
	
	/*@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		return "xgmm";
	}*/
}
