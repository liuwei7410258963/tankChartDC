package com.oket.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.oket.util.CommonUtil;
import com.oket.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author lw
 * @description: 统一异常拦截
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@ExceptionHandler(value = Exception.class)
	public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) {
		String errorPosition = "";
		//如果错误堆栈信息存在
		if (e.getStackTrace().length > 0) {
			StackTraceElement element = e.getStackTrace()[0];
			String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
			int lineNumber = element.getLineNumber();
			errorPosition = fileName + ":" + lineNumber;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ErrorEnum.E_400.getErrorCode());
		jsonObject.put("msg", ErrorEnum.E_400.getErrorMsg());
		JSONObject errorObject = new JSONObject();
		errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
		jsonObject.put("info", errorObject);
		logger.error("异常", e);
		return jsonObject;
	}


	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public JSONObject handleValidException(MethodArgumentNotValidException e) {
		//日志记录错误信息
		log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
		//将错误信息返回给前台
		return CommonUtil.error(ErrorEnum.E_1001.getErrorCode(),
				getErrorMessage(e)
		);
	}

	private String getErrorMessage(MethodArgumentNotValidException e) {
		final List<FieldError> fieldErrors = Objects.requireNonNull(e.getBindingResult().getFieldErrors());
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append("校验失败:{");
		for (FieldError fieldError : fieldErrors) {
			stringBuffer.append(fieldError.getDefaultMessage()).append(fieldError.getField()).append(";");
		}
		stringBuffer.deleteCharAt(stringBuffer.length() - 1);
		stringBuffer.append("}");
		return stringBuffer.toString();
	}

	/**
	 * GET/POST请求方法错误的拦截器
	 * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
	 * 所以定义了这个拦截器
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public JSONObject httpRequestMethodHandler() {
		return CommonUtil.errorJson(ErrorEnum.E_500);
	}

	/**
	 * 本系统自定义错误的拦截器
	 * 拦截到此错误之后,就返回这个类里面的json给前端
	 * 常见使用场景是参数校验失败,抛出此错,返回错误信息给前端
	 */
	@ExceptionHandler(CommonJsonException.class)
	public JSONObject commonJsonExceptionHandler(CommonJsonException commonJsonException) {
		return commonJsonException.getResultJson();
	}
}
