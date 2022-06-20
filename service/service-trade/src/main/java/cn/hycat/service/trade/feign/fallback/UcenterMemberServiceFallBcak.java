package cn.hycat.service.trade.feign.fallback;

import cn.hycat.service.base.dto.MemberDto;
import cn.hycat.service.trade.feign.UcenterMemberService;
import org.springframework.stereotype.Service;

/**
 * 熔断保护
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
public class UcenterMemberServiceFallBcak implements UcenterMemberService {
    @Override
    public MemberDto getMemberDtoById(String memberId) {
        return null;
    }
}
