package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.config.exception.CommonJsonException;
import com.oket.tankchartdc.upload.ReturnMessage;
import com.oket.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Service
@Slf4j
public class UploadService {
	public JSONObject logUpload(MultipartFile file, String parentPath) throws Exception {
		if (file == null || file.isEmpty()) {
			throw new CommonJsonException(ErrorEnum.E_1001);
		}

		File filePath = new File(parentPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		File uploadFile = new File(filePath.getAbsolutePath(), file.getOriginalFilename());
		if (uploadFile.exists()) {
			throw new CommonJsonException(ErrorEnum.E_90003);
		}

		try {
			file.transferTo(uploadFile);
			return ReturnMessage.success();
		} catch (IOException e) {
			log.error("",e);
			throw new CommonJsonException(ErrorEnum.E_90001);
		}
	}

	public JSONObject logUploads(HttpServletRequest request,String uploadFilePath) throws Exception {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

		for (MultipartFile file : files) {
			logUpload(file,uploadFilePath);
		}

		return ReturnMessage.success();
	}
}
