package com.muyou.sso.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbFriendshipMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbUser;
import com.muyou.pojo.TbUserExample;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private TbFriendshipMapper friendshipMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${USER_INFORMATION}")
	private String USER_INFORMATION;
	
	@Value("${USER_INFO_EXPIRE}")
	private int USER_INFO_EXPIRE;
	
	@Value("${USER_SEARCH}")
	private String USER_SEARCH;
	
	


	// 获得用户信息:账号
	@Override
	public UserVo getUserInformation(String account) {

		try {
			String json = jedisClient.hget(USER_INFORMATION + ":" + account, account);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, UserVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<TbUser> users = userMapper.selectByExample(example);
		if (null == users || users.size() == 0)
			return null;

		UserVo user = new UserVo(users.get(0));
		
		try {
			jedisClient.set(USER_INFORMATION + ":" + account, JsonUtils.objectToJson(user));
			jedisClient.expire(USER_INFORMATION + ":" + account,USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// 查询用户:账号、电话
	@Override
	public UserVo searchUser(String searchWords, String userid) {

		try {
			String json = jedisClient.hget(USER_SEARCH, searchWords);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, UserVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUser user = userMapper.findUserByAccountOrPhone(searchWords);
		if (null == user) {
			return null;
		}
		
		UserVo userVo = new UserVo(user,friendshipMapper.isFriendship(user.getId(), userid) > 0);

		try {
			jedisClient.hset(USER_SEARCH, searchWords, JsonUtils.objectToJson(userVo));
			jedisClient.expire(USER_SEARCH,USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userVo;
	}

	// 查询用户:根据主键
	@Override
	public UserVo searchUser(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(USER_SEARCH + "_ID", requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, UserVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUser user = userMapper.searchUser(requestForm.getQuest_id());

		if (null == user) {
			return null;
		}
		UserVo userVo = new UserVo(user);
		
		try {
			jedisClient.hset(USER_SEARCH + "_ID", requestForm.getQuest_id(), JsonUtils.objectToJson(userVo));
			jedisClient.expire(USER_SEARCH + "_ID", USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVo;
	}

//	//查询用户根据账号
//	public UserVo findByAccount(String account) {
//
//		try {
//			String json = jedisClient.hget(USER_SEARCH + "_ACCOUNT", account);
//			if (StringUtils.isNotBlank(json)) {
//				return JsonUtils.jsonToPojo(json, UserVo.class);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		TbUserExample example = new TbUserExample();
//		TbUserExample.Criteria criteria = example.createCriteria();
//		criteria.andAccountEqualTo(account);
//		List<TbUser> users = userMapper.selectByExample(example);
//		if (null == users || users.size() == 0)
//			return null;
//		UserVo userVo = new UserVo();
//		userVo.setId(users.get(0).getId());
////		userVo.setPassword(users.get(0).getPassword());
//		try {
//			jedisClient.hset(USER_SEARCH + "_ACCOUNT", account, JsonUtils.objectToJson(userVo));
//			jedisClient.expire(USER_SEARCH + "_ACCOUNT", USER_INFO_EXPIRE);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return userVo;
//	}

	//查询电话号码
	@Override
	public void searchPhone(RegisterFrom dataForm) throws ServiceException {

		try {
			String json = jedisClient.hget(USER_SEARCH, dataForm.getPhone());
			if (StringUtils.isNotBlank(json)) {
				throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(dataForm.getPhone());
		int count = userMapper.countByExample(example);
		if (0 != count)
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
	}


	
}
