package com.spz.service;

import com.spz.entity.UserRoles;

public interface UserRolesService {
	/**
	 * 添加员工的角色
	 * @param r_id角色id
	 * @return
	 */
	Integer insertUserRoles(UserRoles usersRoles);
	
	/**
	 * 删除员工的角色
	 * @param u_id
	 * @return
	 */
	Integer deleteUserRoles(UserRoles usersRoles);
}
