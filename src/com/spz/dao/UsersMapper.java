package com.spz.dao;

import java.util.List;

import com.spz.entity.Modules;
import com.spz.entity.Users;

public interface UsersMapper {
	
	/**
	 * ��������ҳ��ѯ
	 * @param users
	 * @return
	 */
	List<Users> selectUsersByUsers(Users users);
	
	/**
	 *  ��������ҳ��ѯ������
	 * @param users
	 * @return
	 */
	Integer selectUsersByUsersCount(Users users);
	
	/**
	 * ����Ա��
	 * @param users
	 * @return
	 */
	Integer insertUsers(Users users);
	
	/**
	 * �޸�Ա��
	 * @param users
	 * @return
	 */
	Integer updateUsers(Users users);	
	
	/**
	 * ɾ��Ա��
	 * @param u_id
	 * @return
	 */
	Integer deleteUsers(Integer u_id);
	
	
	/**
	 * ��½ʱ��ѯʹ��
	 * @param users
	 * @return
	 */
	Users selectUserBylogin(Users users);
	
	
	/**
	 * ��ѯ��¼�û���ģ�� 
	 * @param u_id
	 * @return
	 */
	List<Modules> selectUserModuls(Integer u_id);
	
	/**
	 * ��id�����е���ģ��
	 * @return
	 */
	List<Modules> selectModulsByid(Integer m_id,Integer u_id);
}