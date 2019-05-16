package com.spz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spz.dao.ModulesMapper;
import com.spz.entity.Modules;
import com.spz.service.ModulesService;
@Service
public class ModulesServiceImpl implements ModulesService {

	@Autowired private ModulesMapper modulesMapper; 
	@Override
	public List<Map<String, Object>> selectModulesAll() {
		List<Modules> listmoduls = modulesMapper.selectModulesAll();
		List<Map<String, Object>> m=new ArrayList<Map<String, Object>>();
		for(int i=0;i<listmoduls.size();i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if(listmoduls.get(i).getM_parentId()==0){
				map.put("id", listmoduls.get(i).getM_id());
				map.put("text", listmoduls.get(i).getM_name());
				map.put("url", listmoduls.get(i).getM_path());
				/*map.put("parentId", listmoduls.get(i).getM_parentId());*/
				map.put("children",childre(listmoduls.get(i)));
				m.add(map);
			}
		}
		return m;
	}
	public List<Map<String, Object>> childre(Modules modules){
		List<Modules> modulsByid = modulesMapper.selectModulesAll();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			for(int i=0;i<modulsByid.size();i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				if(modulsByid.get(i).getM_parentId()==modules.getM_id()){
					map.put("id", modulsByid.get(i).getM_id());
					map.put("text", modulsByid.get(i).getM_name());
					map.put("url", modulsByid.get(i).getM_path());
					/*map.put("parentId", modulsByid.get(i).getM_parentId());*/
					map.put("children",childre(modulsByid.get(i)));
				    list.add(map);
				}
			}
		return list;
	}
	@Override
	public Integer insertModules(Modules modules) {
		return modulesMapper.insertModules(modules);
	}
	@Override
	public Integer updateModules(Modules modules) {
		return modulesMapper.updateModules(modules);
	}
	@Override
	public Integer deleteModules(Integer m_id) {
		return modulesMapper.deleteModules(m_id);
	}
	@Override
	public Integer deleteModulesByP_id(Integer m_parentId) {
		return modulesMapper.deleteModulesByP_id(m_parentId);
	}
	@Override
	public Modules selectModulesById(Integer m_id) {
		return modulesMapper.selectModulesById(m_id);
	}
	@Override
	public List<Map<String, Object>> selectModulesByRolesId(Integer r_id) {
		//查询角色的模块
		//List<Modules> listModules = modulesMapper.selectModulesByRolesId(r_id);
		/*System.out.println("角色的模块："+listModules.size());
		for (Modules modules : listModules) {
			System.out.println("哈哈"+modules);
		}*/
		//全部模块
		List<Modules> modulsByid = modulesMapper.selectModulesAll();
		
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			for(int i=0;i<modulsByid.size();i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				if(modulsByid.get(i).getM_parentId()==0){
					map.put("id", modulsByid.get(i).getM_id());
					map.put("text", modulsByid.get(i).getM_name());
					map.put("url", modulsByid.get(i).getM_path());
					map.put("parentId", modulsByid.get(i).getM_parentId());
					map.put("checked", false);
					map.put("children",childreChecked(modulsByid.get(i),r_id));
				    list.add(map);
				}
			}
		return list;
	}
	public List<Map<String, Object>> childreChecked(Modules modules,Integer r_id){
		//查询角色的模块
		List<Modules> listModules = modulesMapper.selectModulesByRolesId(r_id);
		List<Modules> modulsByid = modulesMapper.selectModulesAll();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			for(int i=0;i<modulsByid.size();i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				if(modulsByid.get(i).getM_parentId()==modules.getM_id()){
					map.put("id", modulsByid.get(i).getM_id());
					map.put("text", modulsByid.get(i).getM_name());
					map.put("url", modulsByid.get(i).getM_path());
					map.put("parentId", modulsByid.get(i).getM_parentId());
					for(int j=0;j<listModules.size();j++) {
						if(modulsByid.get(i).getM_id()==listModules.get(j).getM_id()) {
							map.put("checked", true);
						}
					}
					map.put("children",childre(modulsByid.get(i)));
				    list.add(map);
				}
			}
		return list;
	}

}
