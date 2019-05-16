package com.spz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spz.entity.Modules;
import com.spz.service.ModulesService;
import com.spz.util.Result;

@Controller
public class ModulesController {
	
	@Autowired ModulesService modulesService;
	
	
	@RequestMapping(value="/selectModules",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> selectModules() {
		List<Map<String, Object>> modulesAll = modulesService.selectModulesAll();
		return modulesAll;
	}
	
	@RequestMapping(value="/addEditModules",method=RequestMethod.POST)
	@ResponseBody
	public String addEditModules(Modules modules) {
		
		System.out.println(modules.toString());
		Integer num =null;
		if(modules.getM_id()==null) {
			num= modulesService.insertModules(modules);
			return Result.toClient(num>0 ? true : false, num>0 ? "添加成功" : "添加失败");
		}else {
			num= modulesService.updateModules(modules);
			return Result.toClient(num>0 ? true : false, num>0 ? "修改成功" : "修改失败");
		}
	}
	
	@RequestMapping(value="/deleteModules",method=RequestMethod.POST)
	@ResponseBody
	public String deleteModules(Integer m_id) {
		Integer num = modulesService.deleteModules(m_id);
		if(num>0) {
			//删除夫模块同时删除子模块
			modulesService.deleteModulesByP_id(m_id);
		}
		return Result.toClient(num>0 ? true : false,num>0 ? "删除成功" : "删除失败");
	}
	
	@RequestMapping(value="/selectModulesByid",method=RequestMethod.POST)
	@ResponseBody
	public String selectModulesByid(Integer m_id) {
		Modules byId = modulesService.selectModulesById(m_id);
		return Result.toClient(byId != null ? true : false, byId != null ? new Gson().toJson(byId): null);
	}
	
	@RequestMapping(value="/selectRolesModules",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> selectRolesModules(Integer r_id){
		return modulesService.selectModulesByRolesId(r_id);
	}
}
