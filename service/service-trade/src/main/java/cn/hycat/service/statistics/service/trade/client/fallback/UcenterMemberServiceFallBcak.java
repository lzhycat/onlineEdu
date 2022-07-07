package cn.hycat.service.statistics.service.trade.client.fallback;

import cn.hycat.service.statistics.service.base.dto.MemberDto;
import cn.hycat.service.statistics.service.trade.client.UcenterClient;
import org.springframework.stereotype.Service;

/**
 * 熔断保护
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
public class UcenterMemberServiceFallBcak implements UcenterClient {
    @Override
    public MemberDto getMemberDtoById(String memberId) {
        return null;
    }
}
