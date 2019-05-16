package com.spz.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spz.dao.RolesMapper;
import com.spz.entity.Fenye;
import com.spz.entity.Roles;
import com.spz.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService {

	@Autowired private RolesMapper rolesMapper;
	
	@Override
	public String selectRolesByRole(Roles roles) {
		Fenye<Roles> fy=new Fenye<Roles>();
		roles.setPage((roles.getPage()-1)*roles.getRows());
		fy.setRows(rolesMapper.selectRolesByRole(roles));
		fy.setTotal(rolesMapper.selectRolesByRoleCount(roles));
		return new Gson().toJson(fy);
	}
	
	@Override
	public Integer insertRoles(Roles roles) {
		return rolesMapper.insertRoles(roles);
	}

	@Override
	public Integer updateRoles(Roles roles) {
		return rolesMapper.updateRoles(roles);
	}

	@Override
	public Integer deleteRoles(Integer r_id) {
		return rolesMapper.deleteRoles(r_id);
	}

	@Override
	public Roles selectRolesByName(Roles roles) {
		return rolesMapper.selectRolesByName(roles);
	}

}
