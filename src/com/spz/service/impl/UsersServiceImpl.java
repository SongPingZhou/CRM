package com.spz.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spz.dao.RolesMapper;
import com.spz.dao.UsersMapper;
import com.spz.entity.Fenye;
import com.spz.entity.Modules;
import com.spz.entity.Roles;
import com.spz.entity.Users;
import com.spz.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired private UsersMapper usersMapper;
	
	@Autowired private RolesMapper rolesMapper;
	
	@Override
	public String selectUsersByUsers(Users users) {
		Fenye<Users> fy=new Fenye<Users>();
		users.setPage((users.getPage()-1)*users.getRows());
		fy.setTotal(usersMapper.selectUsersByUsersCount(users));
		fy.setRows(usersMapper.selectUsersByUsers(users));
		return new Gson().toJson(fy);
	}
	
	@Override
	public Integer insertUsers(Users users) {
		Integer insertUsers = usersMapper.insertUsers(users);
		return insertUsers;
	}

	@Override
	public Integer updateUsers(Users users) {
		return usersMapper.updateUsers(users);
	}

	@Override
	public Integer deleteUsers(Integer u_id) {
		return usersMapper.deleteUsers(u_id);
	}
	
	
	
	@Override
	public Users selectUserBylogin(Users users) {
		//通过用户名和密码去查询
		Users users2 = usersMapper.selectUserBylogin(users);
		return users2;
	}

	@Override
	public List<Map<String, Object>> selectUserModuls(Integer u_id) {
		List<Modules> listmoduls = usersMapper.selectUserModuls(u_id);
		List<Map<String, Object>> m=new ArrayList<Map<String, Object>>();
		if(listmoduls!=null) {
			for(int i=0;i<listmoduls.size();i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				if(listmoduls.get(i).getM_parentId()==0){
					map.put("id", listmoduls.get(i).getM_id());
					map.put("text", listmoduls.get(i).getM_name());
					map.put("url", listmoduls.get(i).getM_path());
					map.put("children",childre(listmoduls.get(i),u_id));
					m.add(map);
				}
			}
		}
		return m;
	}
	public List<Map<String, Object>> childre(Modules modules,Integer u_id){
		List<Modules> modulsByid = usersMapper.selectModulsByid(modules.getM_id(), u_id);
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			for(int i=0;i<modulsByid.size();i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				if(modulsByid.get(i).getM_parentId()==modules.getM_id()){
					map.put("id", modulsByid.get(i).getM_id());
					map.put("text", modulsByid.get(i).getM_name());
					map.put("url", modulsByid.get(i).getM_path());
					map.put("children",childre(modulsByid.get(i),u_id));
				    list.add(map);
				}
			}
		return list;
	}

	//这里传员工u_id就是查员工具有有的角色，前台不传为u_id则为null查询全部角色
	@Override
	public List<Roles> selectRolesAll() {
		return rolesMapper.selectRolesAll();
	}

	@Override
	public List<Roles> selectRolesAllbyU_id(Integer u_id) {
		return rolesMapper.selectRolesAllbyU_id(u_id);
	}

	

}
