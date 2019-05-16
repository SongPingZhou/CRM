package com.spz.service;

import java.util.List;

import com.spz.entity.Roles;

public interface RolesService {
	/**
	 * 条件查询角色
	 * @return
	 */
	String selectRolesByRole(Roles roles);
	
	/**
	 * 添加角色
	 * @param roles
	 * @return
	 */
	Integer insertRoles(Roles roles);
	
	/**
	 * 修该角色
	 * @param roles
	 * @return
	 */
	Integer updateRoles(Roles roles);
	
	/**
	 * 删除角色
	 * @param r_id
	 * @return
	 */
	Integer deleteRoles(Integer r_id);
	
	/**
	 * 判断角色是否相同
	 * @param roles
	 * @return
	 */
	Roles selectRolesByName(Roles roles);
}
