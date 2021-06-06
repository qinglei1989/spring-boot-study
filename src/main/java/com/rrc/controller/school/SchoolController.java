package com.rrc.controller.school;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rrc.dto.base.ResultDto;
import com.rrc.service.ISchoolService;
import com.rrc.util.AppUtil;
import com.rrc.vo.SchoolVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("v1")
@Slf4j
public class SchoolController {

    @Autowired
    private ISchoolService schoolService;

    @GetMapping("/school/{schoolId}")
    public ResultDto querySchool(@NotBlank(message = "schoolId不能为空") @PathVariable("schoolId") Long schoolId) {

        return AppUtil.resultSucc(schoolService.querySchool(schoolId));
    }

    @GetMapping("/school")
    public ResultDto querySchoolList(Page page, @Valid @RequestBody SchoolVo schoolVo) {

        return AppUtil.resultSucc(schoolService.querySchoolList(page, schoolVo));
    }

    @PostMapping("/school")
    public ResultDto postSchool(@Valid @RequestBody SchoolVo schoolVo) {

        schoolService.postSchool(schoolVo);
        return AppUtil.resultSucc();
    }

    @DeleteMapping("/school/{schoolId}")
    public ResultDto deleteSchool(@NotBlank(message = "schoolId不能为空") @PathVariable("schoolId") Long schoolId) {

        schoolService.deleteSchool(schoolId);
        return AppUtil.resultSucc();
    }
}
