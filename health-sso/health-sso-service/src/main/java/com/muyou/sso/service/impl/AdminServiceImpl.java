package com.muyou.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.muyou.common.form.LoginForm;
import com.muyou.common.pojo.Result;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.mapper.TbAdminMapper;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbAdminExample;
import com.muyou.pojo.TbAdminExample.Criteria;
import com.muyou.sso.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TbAdminMapper adminMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ASESSION_EXPIRE}")
	private Integer ASESSION_EXPIRE;
	
	@Value("${ASESSION}")
	private String ASESSION;

	@Override
	public Result<Object> adminLogin(LoginForm dataForm) {
		// 账号查询
		TbAdminExample example = new TbAdminExample();
		TbAdminExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(dataForm.getAccount());
		// 账号查询用户
		List<TbAdmin> list = adminMapper.selectByExample(example);

		// 用户是否存在
		if (null == list || list.size() == 0)
			new ResultUtil<Object>().setErrorMsg("账号或者密码错误");

		TbAdmin user = list.get(0);

		// 校验密码
		if (null == user || !DigestUtils.md5DigestAsHex(dataForm.getPassword().getBytes())
				.equals(user.getPassword().toLowerCase())) {
			new ResultUtil<Object>().setErrorMsg("账号或者密码错误");
		}

		// 密码设置为null
		user.setPassword(null);

		// 用户登录成功，设置令牌
		String token = UUID.randomUUID().toString();

		// redis存储用户数据
		jedisClient.set(ASESSION + token, JsonUtils.objectToJson(user));
		jedisClient.expire(ASESSION + token, ASESSION_EXPIRE);
		return new ResultUtil<Object>().setData(token);
	}

	@Override
	public int adminLogout(String token) {
		jedisClient.del(ASESSION + token);
		return 1;
	}

	@Override
	public Result<Object> unLock(String username, String password,String token) {
		// 用户名、密码查询
		TbAdminExample example = new TbAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()).toLowerCase());

		// 账号查询用户
		List<TbAdmin> list = adminMapper.selectByExample(example);
		if (null != list && list.size() == 1) {
			
			//重置过期时间
			TbAdmin user = list.get(0);
			user.setPassword(null);
			jedisClient.set(ASESSION + token, JsonUtils.objectToJson(user));
			jedisClient.expire(ASESSION + token, ASESSION_EXPIRE);
			
			return new ResultUtil<>().setData(null);
		} else {
			return new ResultUtil<>().setErrorMsg("密码错误");
		}
	}

}
