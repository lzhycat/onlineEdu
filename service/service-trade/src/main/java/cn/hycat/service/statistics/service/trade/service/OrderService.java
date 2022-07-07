package cn.hycat.service.statistics.service.trade.service;

import cn.hycat.service.statistics.service.trade.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author hy
 * @since 2022-04-18
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String memberId);

    Order getByOrderId(String orderId, String id);

    Boolean isBuyByCourseId(String courseId, String id);
}
