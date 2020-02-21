package com.muyou.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbPermission;
import com.muyou.pojo.TbRole;
import com.muyou.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/adminList", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAdminList() {
		return adminService.getAdminList();
	}

	@RequestMapping(value = "/adminCount", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getAdminCount() {
		return new ResultUtil<Object>().setData(adminService.countAdmin());
	}

	@RequestMapping(value = "/delAdmin/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> delAdmin(@PathVariable Long[] ids) {
		for (Long id : ids) {
			adminService.deleteAdmin(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addAdmin(@ModelAttribute TbAdmin admin) {
		adminService.addAdmin(admin);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> stopAdmin(@PathVariable Long id) {
		adminService.changeAdminState(id, 0);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> startAdmin(@PathVariable Long id) {
		adminService.changeAdminState(id, 1);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateAdmin(@ModelAttribute TbAdmin admin) {
		adminService.updateAdmin(admin);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> changePass(@ModelAttribute TbAdmin admin) {
		adminService.changePassword(admin);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public boolean getAdminByName(@RequestParam("name") String username) throws UnsupportedEncodingException {
		return adminService.getAdminByName(new String(username.getBytes("iso8859-1"), "utf-8"));
	}

	@RequestMapping(value = "/phone", method = RequestMethod.GET)
	@ResponseBody
	public boolean getAdminByPhone(String phone) {
		return adminService.getAdminByPhone(phone);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	@ResponseBody
	public boolean getAdminByEmail(String email) {
		return adminService.getAdminByEmail(email);
	}

	@RequestMapping(value = "/edit/username/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean getAdminByEditName(@PathVariable Long id, @RequestParam("name") String username)
			throws UnsupportedEncodingException {
		return adminService.getAdminByEditName(id, new String(username.getBytes("iso8859-1"), "utf-8"));
	}

	@RequestMapping(value = "/edit/phone/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean getAdminByEditPhone(@PathVariable Long id, String phone) {
		return adminService.getAdminByEditPhone(id, phone);
	}

	@RequestMapping(value = "/edit/email/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean getAdminByEditEmail(@PathVariable Long id, String email) {
		return adminService.getAdminByEditEmail(id, email);
	}

	/** 角色 **/

	@RequestMapping(value = "/roleList", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getRoleList() {
		DataTablesResult result = adminService.getRoleList();
		return result;
	}

	@RequestMapping(value = "/delRole/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> delRole(@PathVariable int[] ids) {
		for (int id : ids) {
			int result = adminService.deleteRole(id);
			if (result == 0) {
				return new ResultUtil<Object>().setErrorMsg("id为" + id + "的角色被使用中，不能删除！");
			}
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/roleCount", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getRoleCount() {
		return new ResultUtil<Object>().setData(adminService.countRole());
	}

	@RequestMapping(value = "/roleName", method = RequestMethod.GET)
	@ResponseBody
	public boolean roleName(String name) {
		if (adminService.getRoleByRoleName(name) != null) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addRole(@ModelAttribute TbRole tbRole) {
		adminService.addRole(tbRole);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/edit/roleName/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean roleName(@PathVariable int id, String name) {
		return adminService.getRoleByEditName(id, name);
	}

	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateRole(@ModelAttribute TbRole tbRole) {
		adminService.updateRole(tbRole);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<TbRole>> getAllRoles() {
		List<TbRole> list = adminService.getAllRoles();
		return new ResultUtil<List<TbRole>>().setData(list);
	}

	/** 权限 **/

	@RequestMapping(value = "/permissionList", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getPermissionList() {
		return adminService.getPermissionList();
	}

	@RequestMapping(value = "/addPermission", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addPermission(@ModelAttribute TbPermission tbPermission) {
		adminService.addPermission(tbPermission);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/permissionCount", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getPermissionCount() {
		return new ResultUtil<Object>().setData(adminService.countPermission());
	}

	@RequestMapping(value = "/delPermission/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> delPermission(@PathVariable int[] ids) {
		for (int id : ids) {
			adminService.deletePermission(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updatePermission(@ModelAttribute TbPermission tbPermission) {
		adminService.updatePermission(tbPermission);
		return new ResultUtil<Object>().setData(null);
	}
}
