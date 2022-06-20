package cn.hycat.service.trade.controller.api;


import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.util.util.JwtInfo;
import cn.hycat.service.util.util.JwtUtils;
import cn.hycat.service.trade.domain.Order;
import cn.hycat.service.trade.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author hy
 * @since 2022-04-18
 */
@Slf4j
@RestController
@Api("网站订单管理")
@RequestMapping("/grade/trade/order")
public class ApiOrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("save/{courseId}")
    @ApiOperation("添加订单")
    public ResponseResult save(@ApiParam(value = "课程id", required = true) @PathVariable String courseId,
                               HttpServletRequest request) {
        //请求头中获得memberId
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        String memberId = jwtInfo.getId();

        String orderId = orderService.saveOrder(courseId, memberId);
        return ResponseResult.ok().data("orderId", orderId);
    }

    @ApiOperation("获取订单")
    @GetMapping("auth/get/{orderId}")
    public ResponseResult get(@PathVariable String orderId, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        Order order = orderService.getByOrderId(orderId, jwtInfo.getId());
        return ResponseResult.ok().data("element", order);
    }

    @ApiOperation( "判断课程是否购买")
    @GetMapping("auth/is-buy/{courseId}")
    public ResponseResult isBuyByCourseId(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        Boolean isBuy = orderService.isBuyByCourseId(courseId, jwtInfo.getId());
        return ResponseResult.ok().data("isBuy", isBuy);
    }
}

