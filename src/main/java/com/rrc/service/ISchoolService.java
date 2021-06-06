package com.rrc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rrc.dto.SchoolDto;
import com.rrc.entity.School;
import com.rrc.vo.SchoolVo;

/**
 * @ClassName ISchoolService
 * @Description SchoolService接口类
 * @Author wang
 * @Date 2021/5/25 11:53
 * @Version 1.0
 **/
public interface ISchoolService {
    int postSchool(SchoolVo schoolVo);

    SchoolDto querySchool(Long schoolId);

    int deleteSchool(Long schoolId);

    IPage<SchoolDto> querySchoolList(Page<School> page, SchoolVo schoolVo);
}
