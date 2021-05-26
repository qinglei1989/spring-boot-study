package com.rrc.service.impl;

import com.rrc.dto.SchoolDto;
import com.rrc.entity.School;
import com.rrc.mapper.SchoolMapper;
import com.rrc.service.ISchoolService;
import com.rrc.util.DateUtil;
import com.rrc.vo.SchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName ISchoolService
 * @Description SchoolService接口类
 * @Author wang
 * @Date 2021/5/25 11:53
 * @Version 1.0
 **/
@Service
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * @Author Wangql
     * @Description 插入校园记录
     * @Date 11:16 2021/5/26
     * @Param [schoolVo]
     * @return void
     **/
    public void postSchool(SchoolVo schoolVo) {
        School shool = School.builder()
                .schoolName(schoolVo.getSchoolName())
                .schoolAddress(schoolVo.getSchoolAddress())
                .schoolIcon(schoolVo.getSchoolIcon())
                .schoolEstablish(schoolVo.getSchoolEstablish())
                .createDate(new Date())
                .createUser(1L)
                .updateDate(new Date())
                .updateUser(1L)
                .build();
        schoolMapper.insert(shool);
    }

    /**
     * @Author Wangql
     * @Description 查询校园记录
     * @Date 11:17 2021/5/26
     * @Param [schoolId]
     * @return com.rrc.dto.SchoolDto
     **/
    @Override
    public SchoolDto querySchool(Long schoolId) {

        School school = schoolMapper.selectById(schoolId);

        if (Objects.isNull(school)) {
            return null;
        }

        return SchoolDto.builder()
                .id(school.getId())
                .schoolName(school.getSchoolName())
                .schoolAddress(school.getSchoolAddress())
                .schoolIcon(school.getSchoolIcon())
                .schoolEstablish(school.getSchoolEstablish()).build();
    }

    /**
     * @Author Wangql
     * @Description 删除学校信息
     * @Date 11:25 2021/5/26
     * @Param [schoolId]
     * @return void
     **/
    @Override
    public void deleteSchool(Long schoolId) {
        School school = schoolMapper.selectById(schoolId);

        if (Objects.nonNull(school)) {
            schoolMapper.deleteById(schoolId);
        }
    }
}
