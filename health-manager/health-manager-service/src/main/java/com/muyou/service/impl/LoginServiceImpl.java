package com.muyou.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.muyou.common.constant.HealthConstant;
import com.muyou.common.form.LoginForm;
import com.muyou.common.pojo.Result;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.mapper.TbAdminMapper;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbAdminExample;
import com.muyou.pojo.TbAdminExample.Criteria;
import com.muyou.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbAdminMapper adminMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public Result<Object> adminLogin(LoginForm dataForm) {
		// 账号查询
		TbAdminExample example = new TbAdminExample();
		TbAdminExample.Criteria criteria = example.createCriteria();

		System.out.println("getAccount:" + dataForm.getAccount());

		criteria.andAccountEqualTo(dataForm.getAccount());
		// 账号查询用户
		List<TbAdmin> list = adminMapper.selectByExample(example);
		// 用户是否存在
		if (null == list || list.size() == 0)
			return null;

		TbAdmin user = list.get(0);
		// 校验密码
		if (null == user || !DigestUtils.md5DigestAsHex(dataForm.getPassword().getBytes())
				.equals(user.getPassword().toLowerCase())) {
			return null;
		}
		// 密码设置为null
		user.setPassword(null);

		// 用户登录成功，设置令牌
		String token = UUID.randomUUID().toString();

		try {
			jedisClient.set(HealthConstant.ASESSION + ":" + token, JsonUtils.objectToJson(user));
			jedisClient.expire(HealthConstant.ASESSION + ":" +  token, HealthConstant.ASESSION_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// redis存储用户数据
		return new ResultUtil<Object>().setData(token);
	}

	@Override
	public int adminLogout(String token) {
		try {
			jedisClient.del(HealthConstant.ASESSION + ":" + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public Result<Object> unLock(String username, String password, String token) {
		// 用户名、密码查询
		TbAdminExample example = new TbAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()).toLowerCase());

		// 账号查询用户
		List<TbAdmin> list = adminMapper.selectByExample(example);
		if (null != list && list.size() == 1) {

			// 重置过期时间
			TbAdmin user = list.get(0);
			user.setPassword(null);
			try {
				jedisClient.set(HealthConstant.ASESSION + ":" + token, JsonUtils.objectToJson(user));
				jedisClient.expire(HealthConstant.ASESSION + ":" + token, HealthConstant.ASESSION_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResultUtil<>().setData(null);
		} else {
			return new ResultUtil<>().setErrorMsg("密码错误");
		}
	}

	@Override
	public Result<Object> getAdminByToken(String token) {

		String json = jedisClient.get(HealthConstant.ASESSION + ":" + token);
		if (StringUtils.isBlank(json))
			return new ResultUtil<>().setErrorMsg("登录过期，请重新登录");

		// 重置过期时间
		jedisClient.expire(HealthConstant.ASESSION + ":" + token, HealthConstant.ASESSION_EXPIRE);
		Result<Object> result = new ResultUtil<>().setData(JsonUtils.jsonToPojo(json, TbAdmin.class));

		SimpleDateFormat sdp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		result.setMessage(sdp1.format(((TbAdmin) result.getResult()).getCreateTime()));

		return result;
	}
}
