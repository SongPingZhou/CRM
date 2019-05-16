package com.spz.service;

import java.util.List;
import java.util.Map;

import com.spz.entity.Roles;
import com.spz.entity.Users;

public interface UsersService {
	/**
	 * 多条件分页查询
	 * @param users
	 * @return
	 */
	String selectUsersByUsers(Users users);
	
	/**
	 * 添加员工
	 * @param users
	 * @return
	 */
	Integer insertUsers(Users users);
	
	/**
	 * 修改员工
	 * @param users
	 * @return
	 */
	Integer updateUsers(Users users);	
	
	/**
	 * 删除员工
	 * @param u_id
	 * @return
	 */
	Integer deleteUsers(Integer u_id);
	
	/**
	 * 登陆时(注册时)使用查询员工
	 * @param users
	 * @return
	 */
	Users selectUserBylogin(Users users);
	
	/**
	 * 查询登录的用户的模块
	 * @param u_id
	 * @return
	 */
	List<Map<String, Object>> selectUserModuls(Integer u_id);
	
	/**
	 * 查询全部角色and用户现在具有的角色
	 * @return
	 */
	List<Roles> selectRolesAll();
	
	/**
	 * 查询用户现在具有的角色
	 * @return
	 */
	List<Roles> selectRolesAllbyU_id(Integer u_id);
	
}
