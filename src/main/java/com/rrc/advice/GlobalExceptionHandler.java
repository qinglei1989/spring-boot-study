package com.rrc.advice;

import com.rrc.dto.base.ResultDto;
import com.rrc.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;

/**
 * @Description: 统一异常处理类
 * @Author: Wangql
 * @Date: Created in 20:46 2018/6/19
 * @Modified By:
 * @Version: V3.0
 */
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @Author Wangql
     * @Description 数据类型校验异常类
     * @Date 11:20 2021/5/24
     * @Param [req, e]
     * @return com.rrc.dto.base.ResultDto
     **/
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultDto httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
        log.error("发生异常了{},异常信息如下", e.getMessage(), e);

        ResultDto exceptionResult = new ResultDto();
        exceptionResult.setStatus(ResultEnum.RESULT_REQ_FAIL.getCode());
        exceptionResult.setMessage("不支持的请求类型");
        return exceptionResult;
    }

    /**
     * @Author Wangql
     * @Description 数据类型校验异常类
     * @Date 11:20 2021/5/24
     * @Param [req, e]
     * @return com.rrc.dto.base.ResultDto
     **/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultDto unexpectedTypeExceptionHandler(MethodArgumentNotValidException e){
        log.error("发生异常了{},异常信息如下", e.getMessage(), e);

        ResultDto exceptionResult = new ResultDto();
        exceptionResult.setStatus(ResultEnum.RESULT_REQ_FAIL.getCode());
        exceptionResult.setMessage(e.getFieldErrors().get(0).getDefaultMessage());

        return exceptionResult;
    }


    /**
     * @Author Wangql
     * @Description 数据类型转换异常类
     * @Date 11:20 2021/5/24
     * @Param [req, e]
     * @return com.rrc.dto.base.ResultDto
     **/
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultDto httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e){
        log.error("发生异常了{},异常信息如下", e.getMessage(), e);

        ResultDto exceptionResult = new ResultDto();
        exceptionResult.setStatus(ResultEnum.RESULT_REQ_FAIL.getCode());
        exceptionResult.setMessage("类型转换异常");
        return exceptionResult;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDto exceptionHandler(HttpServletRequest req, Exception e){
        log.error("发生异常了{},异常信息如下：{}",e.getMessage(),e);

        ResultDto exceptionResult = new ResultDto();
        exceptionResult.setStatus(ResultEnum.RESULT_REQ_FAIL.getCode());
        exceptionResult.setMessage(ResultEnum.RESULT_REQ_FAIL.getMessage());

        return exceptionResult;
    }
}
