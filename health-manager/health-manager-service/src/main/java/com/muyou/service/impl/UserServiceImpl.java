package com.muyou.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RegisterFrom;
import com.muyou.common.form.RequestForm;
import com.muyou.common.util.DateUtil;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.common.vo.UserVo;
import com.muyou.mapper.TbFriendshipMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbUser;
import com.muyou.pojo.TbUserExample;
import com.muyou.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private TbFriendshipMapper friendshipMapper;

	// 登录
	@Override
	public UserVo login(LoginForm dataForm) throws ServiceException {
		TbUser user = userMapper.findUserByAccountOrPhone(dataForm.getAccount());
		if (null == user || !StringUtil.toMD5(user.getPassword()).equals(dataForm.getPassword())) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		}
		user.setDeviceId(dataForm.getDevice_id());
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(user.getId());
		userMapper.updateByExampleSelective(user, example);
		UserVo uservo = new UserVo();
		uservo.setId(user.getId());
		uservo.setAccount(user.getAccount());
		uservo.setNickname(user.getNickname());
		uservo.setAvatar(user.getAvatar());
		uservo.setDescription(user.getDescription());
		uservo.setLocation(user.getLocation());
		uservo.setSex(user.getSex());
		uservo.setPhone(user.getPhone());
		uservo.setCreateTime(user.getCreateTime());
		uservo.setLastmodifyTime(user.getLastModifyTime());
		return uservo;
	}

	// 注册
	@Override
	public UserVo register(RegisterFrom dataForm) throws ServiceException {
		TbUser user = userMapper.findUserByAccountOrPhone(dataForm.getPhone());
		if (null != user) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
		}
		//添加用户
		user = new TbUser();
		user.setId("s_" + UUID.randomUUID().toString().replace("-", "0"));
		user.setPassword(dataForm.getPassword());
		user.setCreateTime(DateUtil.getStringDate());
		user.setNickname(UUID.randomUUID().toString().substring(8).replace("-", "0"));
		user.setPhone(dataForm.getPhone());
		user.setInitials(user.getNickname().charAt(0) + "");
		userMapper.insert(user);
		
		UserVo uservo = new UserVo();
		uservo.setId(user.getId());
		uservo.setAccount(user.getAccount());
		uservo.setNickname(user.getNickname());
		uservo.setAvatar(user.getAvatar());
		uservo.setDescription(user.getDescription());
		uservo.setLocation(user.getLocation());
		uservo.setSex(user.getSex());
		uservo.setPhone(user.getPhone());
		uservo.setCreateTime(user.getCreateTime());
		uservo.setLastmodifyTime(user.getLastModifyTime());
		return uservo;
	}

	// 获得用户信息:账号
	@Override
	public UserVo getUserInformation(String account) {
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<TbUser> users = userMapper.selectByExample(example);
		if (null == users || users.size() == 0)
			return null;

		UserVo userVo = new UserVo();
		userVo.setId(users.get(0).getId());
		userVo.setAccount(users.get(0).getAccount());
		userVo.setNickname(users.get(0).getNickname());
		userVo.setAvatar(users.get(0).getAvatar());
		userVo.setDescription(users.get(0).getDescription());
		userVo.setLocation(users.get(0).getLocation());
		userVo.setSex(users.get(0).getSex());
		userVo.setPhone(users.get(0).getPhone());
		userVo.setInitials(users.get(0).getInitials());
		userVo.setCreateTime(users.get(0).getCreateTime());
		userVo.setLastmodifyTime(users.get(0).getLastModifyTime());

		return userVo;
	}

	// 查询用户:账号、电话
	@Override
	public UserVo searchUser(String searchWords, String userid) {
		TbUser user = userMapper.findUserByAccountOrPhone(searchWords);
		if (null == user) {
			return null;
		}
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setAccount(user.getAccount());
		userVo.setNickname(user.getNickname());
		userVo.setAvatar(user.getAvatar());
		userVo.setDescription(user.getDescription());
		userVo.setLocation(user.getLocation());
		userVo.setSex(user.getSex());
		userVo.setPhone(user.getPhone());
		userVo.setInitials(user.getInitials());
		userVo.setCreateTime(user.getCreateTime());
		userVo.setLastmodifyTime(user.getLastModifyTime());
		userVo.setIsfriends(friendshipMapper.isFriendship(user.getId(), userid) > 0);
		return userVo;
	}

	// 查询用户:根据主键
	@Override
	public UserVo searchUser(RequestForm requestForm) {
		System.out.println("requestForm.getQuest_id()"+requestForm.getQuest_id());
		TbUser user = userMapper.searchUser(requestForm.getQuest_id());

		if (null == user) {
			return null;
		}
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setAccount(user.getAccount());
		userVo.setNickname(user.getNickname());
		userVo.setAvatar(user.getAvatar());
		userVo.setDescription(user.getDescription());
		userVo.setLocation(user.getLocation());
		userVo.setSex(user.getSex());
		userVo.setPhone(user.getPhone());
		userVo.setInitials(user.getInitials());
		userVo.setCreateTime(user.getCreateTime());
		userVo.setLastmodifyTime(user.getLastModifyTime());

		return userVo;
	}

	public UserVo findByUsername(String account) {
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<TbUser> users = userMapper.selectByExample(example);
		if (null == users || users.size() == 0)
			return null;
		UserVo userVo = new UserVo();
		userVo.setId(users.get(0).getId());
		userVo.setPassword(users.get(0).getPassword());
		return userVo;
	}

	@Override
	public void searchPhone(RegisterFrom dataForm) throws  ServiceException{
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(dataForm.getPhone());
		int count = userMapper.countByExample(example);
		System.out.println(count);
		if (0 != count)
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
	}

}
