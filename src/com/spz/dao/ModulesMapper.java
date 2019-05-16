package com.spz.dao;

import java.util.List;

import com.spz.entity.Modules;

public interface ModulesMapper {
	
	/**
	 * 添加模块
	 * @param modules
	 * @return
	 */
	Integer insertModules(Modules modules);
	
	/**
	 * 修改模块
	 * @param modules
	 * @return
	 */
	Integer updateModules(Modules modules);
	
	/**
	 * 删除模块
	 * @param m_id 模块id
	 * @return
	 */
	Integer deleteModules(Integer m_id);
	/**
	 * 删除所有子模块
	 * @param m_parentId 
	 * @return
	 */
	Integer deleteModulesByP_id(Integer m_parentId);

	/**
	 * 查询所有模块
	 * @return
	 */
	List<Modules> selectModulesAll();
	
	/**
	 * 根据id查看
	 * @return
	 */
	Modules selectModulesById(Integer m_id);
	
	/**
	 * 查询角色下的模块
	 * @param r_id
	 * @return
	 */
	List<Modules> selectModulesByRolesId(Integer r_id);
}
