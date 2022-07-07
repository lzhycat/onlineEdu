package cn.hycat.service.statistics.service.edu.controller.admin;


import cn.hycat.service.statistics.service.edu.domain.entity.Teacher;
import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import cn.hycat.service.statistics.service.edu.client.OssClient;
import cn.hycat.service.statistics.service.edu.domain.search.TeacherSearch;
import cn.hycat.service.statistics.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Api(tags = "讲师管")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    //TODO ：增加用save 复数json用elements 单数用element
    @Resource
    private TeacherService teacherService;
    @Resource
    private OssClient ossFeignService;

    @GetMapping("/list")
    @ApiOperation("讲师列表")
    public ResponseResult ListAll() {
        List<Teacher> list = teacherService.list();
        return ResponseResult.ok().data("element",list);
    }

    @GetMapping("/pageList/{pageNum}/{pageSize}")
    @ApiOperation("讲师分页列表+条件查询")
    public ResponseResult pageList(@ApiParam(value = "当前页码",required = true) @PathVariable Integer pageNum,
                                   @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize,
                                   TeacherSearch teacherSearch) {
        Page<Teacher> page = teacherService.pageList(pageNum, pageSize, teacherSearch);
        List<Teacher> records = page.getRecords();
        long total = page.getTotal();
        return ResponseResult.ok().data("element", records).data("total", total);
    }

    @GetMapping("/getNameList/{namePre}")
    @ApiOperation("姓名前缀提示搜索")
    public ResponseResult getNameList(@ApiParam(value = "姓名前缀") @PathVariable String namePre) {
        List<Map<String, Object>> nameList = teacherService.getNameList(namePre);

        return ResponseResult.ok().data("nameList",nameList);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据Id查询教师")
    public ResponseResult getTeacherById(@ApiParam("教师id") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return ResponseResult.ok().data("element",teacher);
    }

    @PostMapping("/add")
    @ApiOperation("增加讲师")
    public ResponseResult add(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return ResponseResult.ok();
    }

    //https://hycat-guliedu.oss-cn-beijing.aliyuncs.com/
    @DeleteMapping("/remove/{id}")
    @ApiOperation("根据id删除讲师")
    public ResponseResult removeById(@ApiParam(value = "讲师id", required = true) @PathVariable String id) {
        //远程调用oss删除阿里云头像
        Teacher teacher = teacherService.getById(id);
        String avatar = teacher.getAvatar();
        ossFeignService.removePhoto(avatar);

        teacherService.removeById(id);
        return ResponseResult.ok();
    }

    @PutMapping("/update")
    @ApiOperation("更新讲师")
    public ResponseResult update(@ApiParam("讲师信息") @RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return ResponseResult.ok();
    }

    @PutMapping("/redoAll")
    @ApiOperation("还原所有删除讲师")
    public ResponseResult redoAll() {
        teacherService.redoAll();
        return  ResponseResult.ok();
    }

    @DeleteMapping("/removeBatch")
    @ApiOperation("批量删除")
    public ResponseResult removeBatch(@ApiParam("id列表") @RequestBody List<String> list) {
        teacherService.removeByIds(list);

        return ResponseResult.ok();
    }

}

