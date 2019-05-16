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
		/*System.out.println(k+"����֤��");
		System.out.println(yzm+"ǰ��֤��"); */
		if(yzm==null) {
			//ǰ��������֤��������
			if(users2 != null) {
				if(users2.getU_isLockout()==2) {
					return Result.toClient(false, "�û��ѱ�������");
				}
				if(users.getU_pwd()!=null) {
					if(users.getU_pwd().equals(users2.getU_pwd())) {
						session.setAttribute("u_id", users2.getU_id());
						users2.setU_lastLoginTime(lastLoginTime());
						usersService.updateUsers(users2);
						//���û������ӵ�cookie(��Ϊcookie���ܴ����ģ��ô���һ��);
						String name=URLEncoder.encode(users2.getU_name(),"UTF-8");
						insertCookie(resp,name,users2.getU_pwd());
					}
				}
			}
		}
		if(yzm!=null){
			//�ж���֤���Ƿ���ȷ����ȷ������֤�������ȷ���ѯ
			/*Object a=Integer.parseInt(k)!=yzm ? Result.toClient(false, "��֤�����") : (users2 == null ? null : request.getSession().setAttribute("u_id", users2.getU_id()));*/
			if(Integer.parseInt(k)==yzm) {
				//��֤����ȷ��ѯ
				if(users2 != null) {
					session.setAttribute("u_id", users2.getU_id());
					if(users2.getU_isLockout()==2) {
						return Result.toClient(false, "�û��ѱ�����");
					}
					if(users2.getU_isLockout()==1) {
						//���û������ӵ�cookie
						insertCookie(resp,users2.getU_name(),users2.getU_pwd());
						users2.setU_lastLoginTime(lastLoginTime());
						usersService.updateUsers(users2);
					}
				}
			}
			if(Integer.parseInt(k)!=yzm){
				//��֤���ȥ����
				return Result.toClient(false, "��֤�����","1");
			}
		}
		//����õ��ĵ�¼�û��Ƿ�����״̬==1�������ʾ
		//String count=users2.getU_isLockout()==1 ? "�û������� ������ϵ����Ա�������½ " : "�������";
		/*if(users2 !=null) {
			if(users2.getU_isLockout()==1) {
				return Result.toClient(false, "�û��ѱ�����");
			}
		}*/
        if(users2 ==null){
			if(users.getU_pwd()==null) {
				return Result.toClient(false, "�û�������");
			}
		}
		return Result.toClient(users2 !=null ? true : false, users2 !=null ? users2 : "�������");
	}
	@RequestMapping(value="locking",method=RequestMethod.POST)
	@ResponseBody
	public Integer locking(Users users) {
		//��¼ʧ�ܶ�������û�
		return usersService.updateUsers(users);
	}
	
	//������¼���û���������浽cookie��
	public void insertCookie(HttpServletResponse resp,String val1,String val2) {
		Cookie name=new Cookie("u_name", val1);
		//���ù���ʱ�䣨����Ϊ��λ��
		name.setMaxAge(60*60);
		//�������ӵ���·����
		name.setPath("/");
		//����Cookie
		resp.addCookie(name);
		
		Cookie pwds=new Cookie("u_pwd", val2);
		//���ù���ʱ�䣨����Ϊ��λ��
		pwds.setMaxAge(60*60);
		//�������ӵ���·����
		pwds.setPath("/");
		//����Cookie
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