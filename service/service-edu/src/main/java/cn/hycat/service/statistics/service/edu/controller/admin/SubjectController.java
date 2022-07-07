package cn.hycat.service.statistics.service.edu.controller.admin;

import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import cn.hycat.service.statistics.service.edu.domain.vo.SubjectTree;
import cn.hycat.service.statistics.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags = "课程分类")
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @GetMapping("/listTree")
    @ApiOperation("课程分类列表(子属关系)")
    public ResponseResult listTree() {
        List<SubjectTree> list = subjectService.listTree();
        return ResponseResult.ok().data("element", list);
    }
}
