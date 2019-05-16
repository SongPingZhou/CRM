package com.spz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spz.dao.RoleModulesMapper;
import com.spz.entity.RoleModules;
import com.spz.service.RolesModulesService;
@Service
public class RolesModulesServiceImpl implements RolesModulesService {
	
	@Autowired private RoleModulesMapper roleModulesMapper;

	@Override
	public Integer deleteRoleModules(Integer r_id) {
		return roleModulesMapper.deleteRoleModules(r_id);
	}

	@Override
	public Integer insertRoleModules(Integer r_id, Integer m_id) {
		return roleModulesMapper.insertRoleModules(r_id, m_id);
	}

}
