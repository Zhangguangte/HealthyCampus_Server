package com.muyou.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.muyou.common.annotation.SystemControllerLog;
import com.muyou.common.constant.HealthConstant;
import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.FastDFSClient;
import com.muyou.common.util.ResultUtil;
import com.muyou.common.util.StringUtil;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.UserService;

/**
 * 用户控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// 登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog(description = "用户登录系统")
	public UserVo userLogin(@RequestBody LoginForm form) throws ServiceException {
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.userLogin(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

	// 注册
	@RequestMapping("/register")
	@SystemControllerLog(description = "用户注册系统")
	@ResponseBody
	public UserVo createUser(@RequestBody(required = false) RegisterFrom form) throws ServiceException {
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.createUser(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return userVo;
	}

	// 注销
	@RequestMapping("/logout")
	@SystemControllerLog(description = "用户注销系统")
	@ResponseBody
	public ResponseBuilder userLogout(HttpServletRequest request) throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			log.error("用户注销失败");
		userService.userLogout(user);
		return ResponseBuilder.SUCCESS;
	}

	// 获得用户信息:账号
	@RequestMapping("/information/{account}")
	@ResponseBody
	public UserVo getUserInformation(@PathVariable("account") String account) throws ServiceException {
		if (StringUtil.isEmpty(account))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.getUserInformation(account);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

	// 查询用户:账号、电话
	@RequestMapping("/searchUser/{searchWords}")
	@ResponseBody
	public UserVo searchUser(@PathVariable("searchWords") String searchWords, HttpServletRequest request)
			throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(searchWords))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.searchUser(searchWords, user);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

	// 查询用户:根据主键
	@RequestMapping("/searchUser")
	@ResponseBody
	public UserVo searchUser(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo user = userService.searchUser(requestForm);
		if (null == user)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return user;
	}

	// 查询用户:根据电话
	@RequestMapping("/register/searchPhone")
	@ResponseBody
	public ResponseBuilder searchPhone(@RequestBody(required = false) RegisterFrom form) throws ServiceException {
		if (StringUtil.isEmpty(form.getPhone()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		int result = userService.searchPhone(form);
		if (0 != result)
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
		return ResponseBuilder.SUCCESS;
	}

	@RequestMapping("/updateUser")
	@ResponseBody
	public ResponseBuilder updateUser(HttpServletRequest request, @RequestBody UserVo userVo) throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (null == userVo || StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		userService.updateUser(user, userVo);
		return ResponseBuilder.SUCCESS;
	}

	@RequestMapping("/updateStudent")
	@ResponseBody
	public ResponseBuilder updateStudent(HttpServletRequest request, @RequestBody RequestForm form)
			throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (null == form.getMap() || StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setResult(userService.updateStudent(user, form));
		return responseBuilder;
	}

	@RequestMapping(value = "/updateHead")
	@ResponseBody
	public ResponseBuilder updateHead(HttpServletRequest request, @RequestParam("file") MultipartFile uploadFile) {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			return ResponseBuilder.ERROR_AUTHORIZATION_FAIL;
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			String originalFileName = uploadFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			url = HealthConstant.IMAGE_SERVER_URL + url;
			userService.updateUser(user, url);
			ResponseBuilder reBuilder = ResponseBuilder.SUCCESS;
			reBuilder.setResult(url);
			return reBuilder;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseBuilder(400, 99, "图片上传失败");
		}
	}

}
