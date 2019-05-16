package com.spz.dao;

import java.util.List;

import com.spz.entity.Roles;

public interface RolesMapper {
	
	/**
	 * 查询全部角色
	 * @return
	 */
	List<Roles> selectRolesAll();
	
	/**
	 * 查询用户现在具有的角色
	 * @return
	 */
	List<Roles> selectRolesAllbyU_id(Integer u_id);
	
	//前面再 UsersService中
	
	/**
	 * 条件查询角色
	 * @return
	 */
	List<Roles> selectRolesByRole(Roles roles);
	
	/**
	 * 条件查询角色条数
	 * @return
	 */
	Integer selectRolesByRoleCount(Roles roles);
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
