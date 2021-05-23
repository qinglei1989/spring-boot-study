package com.rrc.util;

import com.rrc.dto.base.ResultDto;
import com.rrc.enums.ResultEnum;

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
