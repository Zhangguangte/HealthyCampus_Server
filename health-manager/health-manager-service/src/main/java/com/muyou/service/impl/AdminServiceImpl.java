package com.muyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.dto.RoleDto;
import com.muyou.mapper.TbAdminMapper;
import com.muyou.mapper.TbPermissionMapper;
import com.muyou.mapper.TbRoleMapper;
import com.muyou.mapper.TbRolePermMapper;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbAdminExample;
import com.muyou.pojo.TbPermission;
import com.muyou.pojo.TbPermissionExample;
import com.muyou.pojo.TbRole;
import com.muyou.pojo.TbRoleExample;
import com.muyou.pojo.TbRolePerm;
import com.muyou.pojo.TbRolePermExample;
import com.muyou.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TbAdminMapper adminMapper;

	@Autowired
	private TbRoleMapper roleMapper;

	@Autowired
	private TbPermissionMapper permissionMapper;

	@Autowired
	private TbRolePermMapper rolePermMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ADMIN}")
	private String ADMIN;

	@Value("${PERMISS}")
	private String PERMISS;

	@Value("${LIST}")
	private String LIST;

	@Value("${COUNT}")
	private String COUNT;

	@Value("${ROLE}")
	private String ROLE;

	@Value("${ROLE_PERMS}")
	private String ROLE_PERMS;

	@Value("${ROLE_EXPIRE}")
	private Integer ROLE_EXPIRE;

	@Value("${ADMIN_EXPIRE}")
	private Integer ADMIN_EXPIRE;

	@Override
	public DataTablesResult getAdminList() {
		try {
			String json = jedisClient.hget(ADMIN, LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DataTablesResult result = new DataTablesResult();
		TbAdminExample example = new TbAdminExample();
		List<TbAdmin> list = adminMapper.selectByExample(example);
		if (list == null) {
			throw new GlobalException("获取用户列表失败");
		}
		for (TbAdmin admin : list) {
			String names = "";
			Iterator<?> it = getRoles(admin.getName()).iterator();
			while (it.hasNext()) {
				names += it.next() + " ";
			}
			admin.setPassword("");
			admin.setRoleNames(names);
		}
		result.setData(list);

		try {
			jedisClient.hset(ADMIN, LIST, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int countAdmin() {
		try {
			String json = jedisClient.hget(ADMIN, COUNT);
			if (StringUtils.isNotBlank(json))
				return Integer.valueOf(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbAdminExample example = new TbAdminExample();
		int result = adminMapper.countByExample(example);
		if (-1 == result) {
			throw new GlobalException("统计用户数失败");
		}

		try {
			jedisClient.hset(ADMIN, COUNT, result + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int deleteAdmin(Long adminId) {
		if (adminMapper.deleteByPrimaryKey(adminId.intValue()) != 1) {
			throw new GlobalException("删除用户失败");
		}

		try {
			jedisClient.hdel(ADMIN, LIST);
			jedisClient.hdel(ADMIN, COUNT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int addAdmin(TbAdmin admin) {

		if (!getAdminByName(admin.getName())) {
			throw new GlobalException("用户名已存在");
		}
		if (!getAdminByPhone(admin.getPhone())) {
			throw new GlobalException("手机号已存在");
		}
		if (!getAdminByEmail(admin.getEmail())) {
			throw new GlobalException("邮箱已存在");
		}
		String md5Pass = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
		admin.setPassword(md5Pass);
		admin.setState(1);
		admin.setCreateTime(new Date());
		admin.setUpdateTime(new Date());
		if (adminMapper.insert(admin) != 1) {
			throw new GlobalException("添加用户失败");
		}

		try {
			jedisClient.hdel(ADMIN, LIST);
			jedisClient.hdel(ADMIN, COUNT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int changeAdminState(Long id, int state) {
		TbAdmin admin = adminMapper.selectByPrimaryKey(id.intValue());
		admin.setState(state);
		admin.setUpdateTime(new Date());
		if (adminMapper.updateByPrimaryKey(admin) != 1) {
			throw new GlobalException("更新用户状态失败");
		}

		try {
			jedisClient.hdel(ADMIN, LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateAdmin(TbAdmin admin) {
		TbAdmin old = adminMapper.selectByPrimaryKey(admin.getId());
		admin.setPassword(old.getPassword());
		admin.setState(old.getState());
		admin.setCreateTime(old.getCreateTime());
		admin.setUpdateTime(new Date());
		if (adminMapper.updateByPrimaryKey(admin) != 1) {
			throw new GlobalException("更新用户失败");
		}
		try {
			jedisClient.hdel(ADMIN, LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int changePassword(TbAdmin admin) {
		TbAdmin old = adminMapper.selectByPrimaryKey(admin.getId());
		old.setUpdateTime(new Date());
		String md5Pass = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
		old.setPassword(md5Pass);
		if (adminMapper.updateByPrimaryKey(old) != 1) {
			throw new GlobalException("修改用户密码失败");
		}
		try {
			jedisClient.hdel(ADMIN, LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public TbAdmin getAdminById(Long id) {

		TbAdmin result = adminMapper.selectByPrimaryKey(id.intValue());
		if (result == null) {
			throw new GlobalException("通过ID获取用户失败");
		}

		result.setPassword("");

		return result;
	}

	@Override
	public boolean getAdminByName(String username) {

		TbAdminExample example = new TbAdminExample();
		TbAdminExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		List<TbAdmin> list = adminMapper.selectByExample(example);

		boolean result = true;
		if (list.size() != 0) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean getAdminByPhone(String phone) {

		TbAdminExample example = new TbAdminExample();
		TbAdminExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<TbAdmin> list = adminMapper.selectByExample(example);

		boolean result = true;
		if (list.size() != 0) {
			result = false;
		}

		return result;
	}

	@Override
	public boolean getAdminByEmail(String email) {
		TbAdminExample example = new TbAdminExample();
		TbAdminExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		List<TbAdmin> list = adminMapper.selectByExample(example);
		boolean result = true;
		if (list.size() != 0) {
			result = false;
		}

		return result;
	}

	@Override
	public boolean getAdminByEditName(Long id, String username) {
		TbAdmin admin = getAdminById(id);
		boolean result = true;
		if (admin.getName() == null || !admin.getName().equals(username)) {
			result = getAdminByName(username);
		}
		return result;
	}

	@Override
	public boolean getAdminByEditPhone(Long id, String phone) {
		TbAdmin admin = getAdminById(id);
		boolean result = true;
		if (admin.getPhone() == null || !admin.getPhone().equals(phone)) {
			result = getAdminByPhone(phone);
		}
		return result;
	}

	@Override
	public boolean getAdminByEditEmail(Long id, String email) {
		TbAdmin admin = getAdminById(id);
		boolean result = true;
		if (admin.getEmail() == null || !admin.getEmail().equals(email)) {
			result = getAdminByEmail(email);
		}
		return result;
	}

	///////////////////// 角色 /////////////////////////////////

	@Override
	public DataTablesResult getRoleList() {
		try {
			String json = jedisClient.hget(ROLE, ROLE_PERMS);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DataTablesResult result = new DataTablesResult();

		// 获得所有的角色列表
		TbRoleExample example = new TbRoleExample();
		List<TbRole> list1 = roleMapper.selectByExample(example);
		if (null == list1) {
			result.setSuccess(false);
			result.setError("获取角色列表失败");
		}

		// 获得对应角色的所有权限
		List<RoleDto> list = new ArrayList<>();
		for (TbRole tbRole : list1) {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(tbRole.getId());
			roleDto.setName(tbRole.getName());
			roleDto.setDescription(tbRole.getDescription());

			List<String> permissions = adminMapper.getPermsByRoleId(tbRole.getId());
			String names = "";
			if (permissions.size() > 1) {
				names += permissions.get(0);
				for (int i = 1; i < permissions.size(); i++) {
					names += "|" + permissions.get(i);
				}
			} else if (permissions.size() == 1) {
				names += permissions.get(0);
			}
			roleDto.setPermissions(names);
			list.add(roleDto);
		}
		result.setData(list);

		try {
			jedisClient.hset(ROLE, ROLE_PERMS, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int deleteRole(int id) {

		List<String> list = roleMapper.getAdminRoles(id);
		if (list == null) {
			throw new GlobalException("查询用户角色失败");
		}
		if (list.size() > 0) {
			return 0;
		}
		if (roleMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除角色失败");
		}
		TbRolePermExample example = new TbRolePermExample();
		TbRolePermExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(id);
		List<TbRolePerm> list1 = rolePermMapper.selectByExample(example);
		if (list1 == null) {
			throw new GlobalException("查询角色权限失败");
		}
		for (TbRolePerm tbRolePerm : list1) {
			if (rolePermMapper.deleteByPrimaryKey(tbRolePerm.getId()) != 1) {
				throw new GlobalException("删除角色权限失败");
			}
		}

		try {
			jedisClient.hdel(ROLE, LIST);
			jedisClient.hdel(ROLE, COUNT);
			jedisClient.hdel(ROLE, ROLE_PERMS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int countRole() {
		try {
			String json = jedisClient.hget(ROLE, COUNT);
			if (StringUtils.isNotBlank(json))
				return Integer.valueOf(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbRoleExample example = new TbRoleExample();
		int result = roleMapper.countByExample(example);

		try {
			jedisClient.hset(ROLE, COUNT, result + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Set<String> getRoles(String name) {
		return adminMapper.getRoles(name);
	}

	@Override
	public TbRole getRoleByRoleName(String roleName) {
		try {
			String json = jedisClient.get(ROLE + ":" + roleName);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbRole.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbRoleExample example = new TbRoleExample();
		TbRoleExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(roleName);
		List<TbRole> list = new ArrayList<>();
		try {
			list = roleMapper.selectByExample(example);
		} catch (Exception e) {
			throw new GlobalException("通过角色名获取角色失败");
		}

		TbRole result = null;
		if (!list.isEmpty()) {
			result = list.get(0);
		}

		try {
			jedisClient.set(ROLE + ":" + roleName, JsonUtils.objectToJson(result));
			jedisClient.expire(ROLE + ":" + roleName, ROLE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int addRole(TbRole tbRole) {

		if (null != getRoleByRoleName(tbRole.getName())) {
			throw new GlobalException("该角色名已存在");
		}
		if (roleMapper.insert(tbRole) != 1) {
			throw new GlobalException("添加角色失败");
		}

		if (tbRole.getRoles() != null) {
			for (int i = 0; i < tbRole.getRoles().length; i++) {
				TbRolePerm tbRolePerm = new TbRolePerm();
				tbRolePerm.setRoleId(tbRole.getId());
				tbRolePerm.setPermissionId(tbRole.getRoles()[i]);
				if (rolePermMapper.insert(tbRolePerm) != 1) {
					throw new GlobalException("添加角色-权限失败");
				}
			}
		}

		try {
			jedisClient.hdel(ROLE, LIST);
			jedisClient.hdel(ROLE, COUNT);
			jedisClient.hdel(ROLE, ROLE_PERMS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public List<TbRole> getAllRoles() {
		try {
			String json = jedisClient.hget(ROLE, LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, TbRole.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbRoleExample example = new TbRoleExample();
		List<TbRole> result = roleMapper.selectByExample(example);
		if (result == null) {
			throw new GlobalException("获取所有角色列表失败");
		}

		try {
			jedisClient.hset(ROLE, LIST, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean getRoleByEditName(int id, String roleName) {
		TbRole tbRole = roleMapper.selectByPrimaryKey(id);
		if (tbRole == null) {
			throw new GlobalException("通过ID获取角色失败");
		}

		TbRole newRole = null;
		if (!tbRole.getName().equals(roleName)) {
			newRole = getRoleByRoleName(roleName);
		}

		if (newRole == null) {
			return true;
		}
		return false;
	}

	@Override
	public void updateRole(TbRole tbRole) {
		if (!getRoleByEditName(tbRole.getId(), tbRole.getName())) {
			throw new GlobalException("该角色名已存在");
		}
		if (roleMapper.updateByPrimaryKey(tbRole) != 1) {
			throw new GlobalException("更新角色失败");
		}
		if (tbRole.getRoles() != null) {
			// 删除已有角色-权限
			TbRolePermExample example = new TbRolePermExample();
			TbRolePermExample.Criteria criteria = example.createCriteria();
			criteria.andRoleIdEqualTo(tbRole.getId());
			List<TbRolePerm> list = rolePermMapper.selectByExample(example);
			if (list != null) {
				for (TbRolePerm tbRolePerm : list) {
					if (rolePermMapper.deleteByPrimaryKey(tbRolePerm.getId()) != 1) {
						throw new GlobalException("删除角色权限失败");
					}
				}
			}
			// 新增
			for (int i = 0; i < tbRole.getRoles().length; i++) {
				TbRolePerm tbRolePerm = new TbRolePerm();
				tbRolePerm.setRoleId(tbRole.getId());
				tbRolePerm.setPermissionId(tbRole.getRoles()[i]);

				if (rolePermMapper.insert(tbRolePerm) != 1) {
					throw new GlobalException("编辑角色-权限失败");
				}
			}
		} else {
			TbRolePermExample example = new TbRolePermExample();
			TbRolePermExample.Criteria criteria = example.createCriteria();
			criteria.andRoleIdEqualTo(tbRole.getId());
			List<TbRolePerm> list = rolePermMapper.selectByExample(example);
			if (list != null) {
				for (TbRolePerm tbRolePerm : list) {
					if (rolePermMapper.deleteByPrimaryKey(tbRolePerm.getId()) != 1) {
						throw new GlobalException("删除角色权限失败");
					}
				}
			}
		}

		try {
			jedisClient.hdel(ROLE, LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	///////////////////// 准许 /////////////////////////////////

	@Override
	public int countPermission() {
		try {
			String json = jedisClient.hget(PERMISS, COUNT);
			if (StringUtils.isNotBlank(json))
				return Integer.valueOf(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbPermissionExample example = new TbPermissionExample();
		int result = permissionMapper.countByExample(example);

		try {
			jedisClient.hset(PERMISS, COUNT, result + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getPermissionList() {
		try {
			String json = jedisClient.hget(PERMISS, LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbPermissionExample example = new TbPermissionExample();
		List<TbPermission> list = permissionMapper.selectByExample(example);
		if (null == list) {
			throw new GlobalException("获取权限列表失败");
		}

		DataTablesResult result = new DataTablesResult();
		result.setSuccess(true);
		result.setData(list);

		try {
			jedisClient.hset(PERMISS, LIST, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void addPermission(TbPermission tbPermission) {
		int result = permissionMapper.insert(tbPermission);
		if (1 == result)
			try {
				jedisClient.hdel(PERMISS, LIST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			throw new GlobalException("添加权限失败");
	}

	@Override
	public int deletePermission(int id) {
		if (permissionMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除权限失败");
		}

		TbRolePermExample example = new TbRolePermExample();
		TbRolePermExample.Criteria criteria = example.createCriteria();
		criteria.andPermissionIdEqualTo(id);
		rolePermMapper.deleteByExample(example);

		try {
			jedisClient.hdel(PERMISS, LIST);
			jedisClient.hdel(PERMISS, COUNT);
			jedisClient.hdel(ROLE, LIST);
			jedisClient.hdel(ROLE, COUNT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updatePermission(TbPermission tbPermission) {
		if (permissionMapper.updateByPrimaryKey(tbPermission) != 1) {
			throw new GlobalException("更新权限失败");
		} else {
			try {
				jedisClient.hdel(PERMISS, LIST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

}
