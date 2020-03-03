package com.muyou.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.muyou.common.pojo.Result;
import com.muyou.common.util.FastDFSClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;

/**
 * 图片上传
 * 
 * @author 木友茶
 *
 */
@Controller
public class PictrueController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@RequestMapping(value = "/pic/upload")
	@ResponseBody
	public Result<Object> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
		// System.out.println("uploadFile"+uploadFile);
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			String originalFileName = uploadFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			url = IMAGE_SERVER_URL + url;
			
			System.out.println(url);
			
			return new ResultUtil<Object>().setData(url);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultUtil<Object>().setErrorMsg("图片上传失败");
		}

	}
}