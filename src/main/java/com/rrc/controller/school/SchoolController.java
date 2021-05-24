package com.rrc.controller.school;

import com.rrc.dto.base.ResultDto;
import com.rrc.util.AppUtil;
import com.rrc.vo.SchoolVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("v1")
@Slf4j
public class SchoolController {

    @GetMapping("/school/{schoolId}")
    public ResultDto querySchool(@NotBlank(message = "schoolId不能为空") @PathVariable("schoolId") String schoolId) {

        return AppUtil.resultSucc();
    }

    @PostMapping("/school")
    public ResultDto postSchool(@Valid @RequestBody SchoolVo schoolVo) {

        return AppUtil.resultSucc();
    }

    @DeleteMapping("/school/{schoolId}")
    public ResultDto deleteSchool(@NotBlank(message = "schoolId不能为空") @PathVariable("schoolId") String schoolId) {

        return AppUtil.resultSucc();
    }
}
