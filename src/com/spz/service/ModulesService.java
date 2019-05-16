package com.spz.service;

import java.util.List;
import java.util.Map;

import com.spz.entity.Modules;


public interface ModulesService {
	/**
	 * 查询所有模块
	 * @return
	 */
	List<Map<String, Object>> selectModulesAll();
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
	 * 根据id查看单个模块
	 * @param m_id
	 * @return
	 */
	Modules selectModulesById(Integer m_id);
	
	/**
	 * 查询角色下的模块
	 * @param r_id
	 * @return
	 */
	List<Map<String, Object>> selectModulesByRolesId(Integer r_id);
}
