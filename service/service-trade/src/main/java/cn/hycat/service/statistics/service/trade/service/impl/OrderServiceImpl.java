package cn.hycat.service.statistics.service.trade.service.impl;

import cn.hycat.service.statistics.service.base.dto.CourseDto;
import cn.hycat.service.statistics.service.base.dto.MemberDto;
import cn.hycat.service.statistics.service.trade.domain.Order;
import cn.hycat.service.statistics.service.trade.client.EduClient;
import cn.hycat.service.statistics.service.trade.client.UcenterClient;
import cn.hycat.service.statistics.service.trade.mapper.TradeOrderMapper;
import cn.hycat.service.statistics.service.trade.service.OrderService;
import cn.hycat.service.statistics.service.trade.util.OrderNoUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.*;
import java.util.*;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-04-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<TradeOrderMapper, Order> implements OrderService {

    @Resource
    private EduClient eduCourseService;

    @Resource
    private UcenterClient ucenterMemberService;

    @Override
    public String saveOrder(String courseId, String memberId) {
        //查询订单是否已购买
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("course_id", courseId);
        orderQueryWrapper.eq("member_id", memberId);

        //订单已存在
        Order orderExist = getBaseMapper().selectOne(orderQueryWrapper);
        if(Objects.nonNull(orderExist))
            return orderExist.getId();

        //订单不存在
        Order order = new Order();
        CourseDto courseDto = eduCourseService.getCourseDtoByCourseId(courseId);
        MemberDto memberDto = ucenterMemberService.getMemberDtoById(memberId);
        BeanUtils.copyProperties(courseDto, order);
        BeanUtils.copyProperties(memberDto, order);
        order.setPayType(1);//1微信 2支付宝
        order.setStatus(0);//0未支付 1已支付
        order.setTotalFee(courseDto.getPrice().multiply(new BigDecimal(100)));//金额：分
        order.setOrderNo(OrderNoUtils.getOrderNo());
        return order.getId();
    }

    @Override
    public Order getByOrderId(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);
        Order order = baseMapper.selectOne(queryWrapper);
        return order;
    }
    @Override
    public Boolean isBuyByCourseId(String courseId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("member_id", memberId)
                .eq("course_id", courseId)
                .eq("status", 1);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count.intValue() > 0;
    }
}
