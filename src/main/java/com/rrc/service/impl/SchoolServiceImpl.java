package com.rrc.service.impl;

import com.rrc.entity.School;
import com.rrc.mapper.SchoolMapper;
import com.rrc.service.ISchoolService;
import com.rrc.util.DateUtil;
import com.rrc.vo.SchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
