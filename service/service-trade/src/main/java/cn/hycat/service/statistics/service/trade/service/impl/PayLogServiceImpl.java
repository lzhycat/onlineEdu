package cn.hycat.service.statistics.service.trade.service.impl;

import cn.hycat.service.statistics.service.trade.domain.PayLog;
import cn.hycat.service.statistics.service.trade.mapper.TradePayLogMapper;
import cn.hycat.service.statistics.service.trade.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-04-18
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<TradePayLogMapper, PayLog> implements PayLogService {

}
