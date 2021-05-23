package com.rrc.advice;

import com.rrc.dto.base.ResultDto;
import com.rrc.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 统一异常处理类
 * @Author: Wangql
 * @Date: Created in 20:46 2018/6/19
 * @Modified By:
 * @Version: V3.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDto jsonErrorHandler(HttpServletRequest req, Exception e){
        log.error("发生异常了{},异常信息如下：{}",e.getMessage(),e);

        ResultDto exceptionResult = new ResultDto();
        exceptionResult.setStatus(ResultEnum.RESULT_REQ_FAIL.getCode());
        exceptionResult.setMessage(ResultEnum.RESULT_REQ_FAIL.getMessage());

        return exceptionResult;
    }
}
