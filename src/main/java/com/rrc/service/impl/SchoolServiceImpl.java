package com.rrc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rrc.dto.SchoolDto;
import com.rrc.entity.School;
import com.rrc.mapper.SchoolMapper;
import com.rrc.service.ISchoolService;
import com.rrc.vo.SchoolVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    @Override
    public int postSchool(SchoolVo schoolVo) {
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

        return schoolMapper.insert(shool);
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
        //查询特定字段时的sql拼写
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("school_name", "school_address").eq("id", schoolId);
//        queryWrapper.select(School.class, info -> info.getColumn().equals("school_name")
//                || info.getColumn().equals("school_address"));
//        School school = schoolMapper.selectOne(queryWrapper);



        return Optional.ofNullable(school).map(obj -> SchoolDto.builder()
                .id(obj.getId())
                .schoolName(obj.getSchoolName())
                .schoolAddress(obj.getSchoolAddress())
                .schoolIcon(obj.getSchoolIcon())
                .schoolEstablish(obj.getSchoolEstablish()).build()).orElseGet(() -> null);
    }

    /**
     * @Author Wangql
     * @Description 删除学校信息
     * @Date 11:25 2021/5/26
     * @Param [schoolId]
     * @return void
     **/
    @Override
    public int deleteSchool(Long schoolId) {
        School school = schoolMapper.selectById(schoolId);

        if (Objects.nonNull(school)) {
            schoolMapper.deleteById(schoolId);
        }

        return 0;
    }

    @Override
    public IPage<SchoolDto> querySchoolList(Page<School> page, SchoolVo schoolVo) {
        Wrapper<School> queryWrapper = new QueryWrapper<School>().lambda()
                .eq(StringUtils.isBlank(schoolVo.getSchoolName()), School::getSchoolName, schoolVo.getSchoolName())
                .eq(StringUtils.isBlank(schoolVo.getSchoolAddress()), School::getSchoolAddress,schoolVo.getSchoolAddress())
                .eq(StringUtils.isBlank(schoolVo.getSchoolIcon()), School::getSchoolIcon,schoolVo.getSchoolIcon())
                .eq(Objects.nonNull(schoolVo.getSchoolEstablish()), School::getSchoolEstablish,schoolVo.getSchoolEstablish());

        Page<School> listPage = schoolMapper.selectPage(page, queryWrapper);


        return  listPage.convert(obj -> SchoolDto.builder()
                .id(obj.getId())
                .schoolName(obj.getSchoolName())
                .schoolAddress(obj.getSchoolAddress())
                .schoolIcon(obj.getSchoolIcon())
                .schoolEstablish(obj.getSchoolEstablish())
                .build());
    }
}
