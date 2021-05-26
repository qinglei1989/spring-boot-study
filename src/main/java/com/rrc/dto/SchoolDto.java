package com.rrc.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学校类DTO
 * @Author Wangql
 * @create 2021/05/20
 */
@Data
@Builder
public class SchoolDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 校园名称
     */
    private String schoolName;

    /**
     * 校园地址
     */
    private String schoolAddress;

    /**
     * 校园图标
     */
    private String schoolIcon;

    /**
     * 成立日期
     */
    private Date schoolEstablish;
}
