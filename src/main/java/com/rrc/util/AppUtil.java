package com.rrc.util;

import com.rrc.dto.base.ResultDto;
import com.rrc.enums.ResultEnum;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppUtil {

    public static ResultDto resultSucc(){
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(ResultEnum.RESULT_REQ_SUCC.getCode());
        resultDto.setMessage(ResultEnum.RESULT_REQ_SUCC.getMessage());

        return resultDto;
    }

    public static ResultDto resultSucc(Object obj){
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(ResultEnum.RESULT_REQ_SUCC.getCode());
        resultDto.setMessage(ResultEnum.RESULT_REQ_SUCC.getMessage());
        resultDto.setData(obj);

        return resultDto;
    }
}
