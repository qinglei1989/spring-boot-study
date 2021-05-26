package com.rrc.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rrc.entry.base.BasePO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学校表
 * </p>
 *
 * @author Wangql
 * @since 2021-05-25
 */
@Data
@Builder
@TableName("sys_school")
public class School extends BasePO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 是否删除 0已删除  1未删除
     */
    @TableLogic(value = "1",delval = "0")
    private Integer isDelete;


}
