package com.spz.dao;


import com.spz.entity.UserRoles;

public interface UserRolesMapper {
	
	/**
	 * ���Ա���Ľ�ɫ
	 * @param r_id��ɫid
	 * @return
	 */
	Integer insertUserRoles(UserRoles usersRoles);
	
	/**
	 * ɾ��Ա���Ľ�ɫ
	 * @param u_id
	 * @return
	 */
	Integer deleteUserRoles(UserRoles usersRoles);
}
