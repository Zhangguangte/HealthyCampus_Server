package com.muyou.service;

import java.util.List;
import java.util.Set;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbPermission;
import com.muyou.pojo.TbRole;

public interface AdminService {

	 /**
     * 获取管理员列表
     * @return
     */
    DataTablesResult getAdminList();
    
    /**
     * 统计管理员
     * @return
     */
    int countAdmin();
    
    /**
     * 删除管理员
     * @param adminId
     * @return
     */
    int deleteAdmin(Long adminId);
    
    /**
     * 添加管理员
     * @param user
     * @return
     */
    int addAdmin(TbAdmin user);

    /**
     * 更改状态
     * @param id
     * @param state
     * @return
     */
    int changeAdminState(Long id,int state);
    
    /**
     * 更新管理员
     * @param admin
     * @return
     */
    int updateAdmin(TbAdmin admin);
    
    /**
     * 修改密码
     * @param admin
     * @return
     */
    int changePassword(TbAdmin admin);
    
    /**
     * 通过id获取
     * @param id
     * @return
     */
    TbAdmin getAdminById(Long id);
    
    /**
     * 通过用户名获取
     * @param username
     * @return
     */
    boolean getAdminByName(String username);

    /**
     * 通过邮件获取
     * @param emaill
     * @return
     */
    boolean getAdminByEmail(String email);
    
    /**
     * 通过手机获取
     * @param phone
     * @return
     */
    boolean getAdminByPhone(String phone);
    
    /**
     * 判断编辑用户名
     * @param id
     * @param username
     * @return
     */
    boolean getAdminByEditName(Long id,String username);

    /**
     * 判断编辑手机
     * @param id
     * @param phone
     * @return
     */
    boolean getAdminByEditPhone(Long id,String phone);

    /**
     * 判断编辑邮箱
     * @param id
     * @param emaill
     * @return
     */
    boolean getAdminByEditEmail(Long id,String emaill);
    
	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	DataTablesResult getRoleList();

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	int deleteRole(int id);

	/**
	 * 统计角色数
	 * 
	 * @return
	 */
	int countRole();

	/**
     * 通过用户名获取角色
     * @param name
     * @return
     */
    Set<String> getRoles(String name);
    
	/**
	 * 获得所有权限列表
	 * 
	 * @return
	 */
	DataTablesResult getPermissionList();

	/**
     * 添加权限
     * @param tbPermission
     * @return
     */
	void addPermission(TbPermission tbPermission);
    
	/**
     * 统计权限
     * @return
     */
	int countPermission();
    
	/**
     * 删除权限
     * @param id
     * @return
     */
    int deletePermission(int id);
	
    /**
     * 更新权限
     * @param tbPermission
     * @return
     */
    int updatePermission(TbPermission tbPermission);
    
	/**
	 * 通过角色名获取角色
	 * 
	 * @param roleName
	 * @return
	 */
	TbRole getRoleByRoleName(String roleName);

	/**
	 * 添加角色
	 * 
	 * @param tbRole
	 * @return
	 */
	int addRole(TbRole tbRole);

	/**
	 * 判断角色编辑名是否已存在
	 * 
	 * @param id
	 * @param roleName
	 * @return
	 */
	boolean getRoleByEditName(int id, String roleName);

	 /**
     * 获取所有角色
     * @return
     */
    List<TbRole> getAllRoles();
    
	/**
	 * 更新角色
	 * 
	 * @param tbRole
	 * @return
	 */
	void updateRole(TbRole tbRole);
	
	
}
