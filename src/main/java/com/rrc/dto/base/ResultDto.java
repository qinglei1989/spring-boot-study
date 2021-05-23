package com.rrc.dto.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ResultDto
 * @Description: 返回前端结果Dto
 * @author houxiaolan
 * @date 2017年3月21日 下午4:07:45
 *
 */
@Data
public class ResultDto implements Serializable{

    private static final long serialVersionUID = 1L;
	/**
	 * 返回状态 0成功 1失败
	 */
	private String status;
    
    /**
     * 发生内部错误时，返回errCode
     */
    private String errCode ;

    /**
     * 返回信息
     */
    private String message;
    
    /**
     * 返回封装数据
     */
    private  Object  data;
}
