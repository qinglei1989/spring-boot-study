package com.rrc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @ClassName SchoolVo
 * @Description 校园类封装Dto
 * @Author wang
 * @Date 2021/5/24 10:50
 * @Version 1.0
 **/
@Data
public class SchoolVo {

    /**
     * 校园名称
     **/
    @NotBlank(message = "校园名称不能为空")
    @Size(max = 64,min = 1, message = "校园名称允许长度为1到64个字符之间")
    private String schoolName;

    /**
     * 校园地址
     **/
    @NotBlank(message = "校园地址不能为空")
    @Size(max = 128,min = 1, message = "校园名称允许长度为1到64个字符之间")
    private String schoolAddress;

    /**
     * 校园图标
     **/
    @NotBlank(message = "校园图标不能为空")
    @Size(max = 64,min = 1, message = "校园图标允许长度为1到128个字符之间")
    private String schoolIcon;

    /**
     * 成立日期
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date schoolEstablish;
}
