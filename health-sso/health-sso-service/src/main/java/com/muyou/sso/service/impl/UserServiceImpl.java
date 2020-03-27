package com.muyou.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.DateUtil;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbFriendshipMapper;
import com.muyou.mapper.TbMessageListMapper;
import com.muyou.mapper.TbStudentMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbStudent;
import com.muyou.pojo.TbStudentExample;
import com.muyou.pojo.TbUser;
import com.muyou.pojo.TbUserExample;
import com.muyou.pojo.TbUserExample.Criteria;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private TbStudentMapper studentMapper;

	@Autowired
	private TbMessageListMapper messageListMapper;

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

	@Value("${USESSION}")
	private String USESSION;

	@Override
	public UserVo createUser(RegisterFrom dataForm) throws ServiceException {

		TbUser user = userMapper.findUserByAccountOrPhone(dataForm.getPhone());
		if (null != user) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
		}

		user.setPassword(DigestUtils.md5DigestAsHex(dataForm.getPassword().getBytes()).toLowerCase());
		user.setCreateTime(DateUtil.getStringDate());
		user.setLastModifyTime(DateUtil.getStringDate());
		user.setNickname(UUID.randomUUID().toString().substring(8).replace("-", "0"));
		user.setPhone(dataForm.getPhone());
		user.setInitials(user.getNickname().charAt(0) + "");
		user.setId("u_" + UUID.randomUUID().toString().substring(8));
		// 4、把用户信息插入到数据库中。
		userMapper.insert(user);
		return new UserVo(user);
	}

	@Override
	public UserVo userLogin(LoginForm dataForm) throws ServiceException {
		// 判断用户名密码是否正确。
		TbUser user = userMapper.findUserByAccountOrPhone(dataForm.getAccount());

		// 校验密码
		if (null == user || !DigestUtils.md5DigestAsHex(dataForm.getPassword().getBytes())
				.equals(user.getPassword().toLowerCase())) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		}

		// 密码设置为null
		user.setPassword(null);

		// 如果已经登陆，删除原有的令牌
		try {
			jedisClient.del(USESSION + ":" + user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 用户登录成功，设置令牌
		String token = UUID.randomUUID().toString().substring(0, 8);
		UserVo result = new UserVo(user);
		result.setToken(token);

		// 学生信息
		TbStudentExample example = new TbStudentExample();
		TbStudentExample.Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(user.getId());
		List<TbStudent> list = studentMapper.selectByExample(example);
		if (null != list && list.size() == 1) {
			result.setAuth(true);
			result.setNo(list.get(0).getNo());
			result.setSid("" + list.get(0).getId());
			result.setSname(list.get(0).getName());
			result.setMajor(list.get(0).getCname());
			result.setYear("" + list.get(0).getYear());
		} else {
			result.setAuth(false);
		}

		// redis存储用户数据
		try {
			jedisClient.hset(USESSION + ":" + result.getId(), token, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int userLogout(String id) {
		try {
			jedisClient.del(USESSION + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	// 获得用户信息:账号
	@Override
	public UserVo getUserInformation(String account) {

		try {
			String json = jedisClient.get(USER_INFORMATION + ":" + account);
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
			jedisClient.expire(USER_INFORMATION + ":" + account, USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// 查询用户:账号、电话
	@Override
	public UserVo searchUser(String searchWords, String userid) {

		try {
			String json = jedisClient.get(USER_SEARCH + ":WORD:" + searchWords);
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

		UserVo userVo = new UserVo(user, friendshipMapper.isFriendship(user.getId(), userid) > 0);

		try {
			jedisClient.set(USER_SEARCH + ":WORD:" + searchWords, JsonUtils.objectToJson(userVo));
			jedisClient.expire(USER_SEARCH + ":WORD:" + searchWords, USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userVo;
	}

	// 查询用户:根据主键
	@Override
	public UserVo searchUser(RequestForm requestForm) {

		try {
			String json = jedisClient.get(USER_SEARCH + ":ID:" + requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, UserVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(requestForm.getQuest_id());

		List<TbUser> list = userMapper.selectByExample(example);
		if (null == list || list.size() == 0) {
			return null;
		}

		UserVo userVo = new UserVo(list.get(0));

		try {
			jedisClient.set(USER_SEARCH + ":ID:" + requestForm.getQuest_id(), JsonUtils.objectToJson(userVo));
			jedisClient.expire(USER_SEARCH + ":ID:" + requestForm.getQuest_id(), USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVo;
	}

	// 查询电话号码
	@Override
	public int searchPhone(RegisterFrom dataForm) {

		try {
			String json = jedisClient.get(USER_SEARCH + ":PHONE:" + dataForm.getPhone());
			if (StringUtils.isNotBlank(json)) {
				return Integer.valueOf(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(dataForm.getPhone());

		int result = userMapper.countByExample(example);

		try {
			jedisClient.set(USER_SEARCH + ":PHONE:" + dataForm.getPhone(), result + "");
			jedisClient.expire(USER_SEARCH + ":PHONE:" + dataForm.getPhone(), USER_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public int updateUser(String id, UserVo userVo) throws ServiceException {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);

		List<TbUser> list = userMapper.selectByExample(example);
		if (null == list || list.size() < 1)
			throw new ServiceException(new ResponseBuilder(400, 99, "用户不存在"));
		TbUser tbUser = list.get(0);
		tbUser.setNickname(userVo.getNickname());
		tbUser.setDescription(userVo.getDescription());
		tbUser.setLocation(userVo.getLocation());
		tbUser.setSex(userVo.getSex());
		tbUser.setBirthday(userVo.getCreateTime());
		if (userMapper.updateByPrimaryKey(tbUser) < 1)
			throw new ServiceException(new ResponseBuilder(400, 99, "更新用户失败"));
		List<String> ids = messageListMapper.selectIdsByName(id);
		userMapper.updateUsername(ids != null && ids.size() > 0 ? "(" + String.join(",", ids) + ")" : "('')",
				userVo.getNickname());
		return 1;
	}

	@Override
	public int updateStudent(String id, RequestForm form) throws ServiceException {

		TbStudentExample example = new TbStudentExample();
		TbStudentExample.Criteria criteria = example.createCriteria();
		criteria.andNoEqualTo(form.getMap().get("no"));
		criteria.andNameEqualTo(form.getMap().get("name"));
		criteria.andYearEqualTo(Integer.valueOf(form.getMap().get("year")));

		List<TbStudent> list = studentMapper.selectByExample(example);

		if (null == list || list.size() < 1)
			throw new ServiceException(new ResponseBuilder(400, 99, "无该学生信息"));

		if (null != list.get(0).getuId())
			throw new ServiceException(new ResponseBuilder(400, 99, "学生已被认证"));

		TbStudent student = list.get(0);
		student.setuId(id);

		if (studentMapper.updateByPrimaryKey(student) < 1)
			throw new ServiceException(new ResponseBuilder(400, 99, "认证失败"));

		return student.getId();
	}

	@Override
	public int updateUser(String id, String url) throws ServiceException {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbUser> list = userMapper.selectByExample(example);

		if (null == list || list.size() < 1)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);

		TbUser tbUser = list.get(0);
		tbUser.setAvatar(url);
		
		if (userMapper.updateByPrimaryKey(tbUser) < 1)
				throw new ServiceException(new ResponseBuilder(400,99,"更新头像失败"));

		return 1;
	}

}
