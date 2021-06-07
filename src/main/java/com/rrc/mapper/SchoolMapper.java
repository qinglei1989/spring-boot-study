package com.rrc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.rrc.dto.SchoolDto;
import com.rrc.entity.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 学校表 Mapper 接口
 * </p>
 *
 * @author Wangql
 * @since 2021-05-25
 */
public interface SchoolMapper extends BaseMapper<School> {
    @Select("select * from sys_school ${ew.customSqlSegment}")
    SchoolDto selectCustomById(@Param(Constants.WRAPPER) Wrapper wrapper);
}
