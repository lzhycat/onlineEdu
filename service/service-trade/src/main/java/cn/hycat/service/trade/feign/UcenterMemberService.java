package cn.hycat.service.trade.feign;

import cn.hycat.service.base.dto.MemberDto;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@FeignClient(value = "ucenter-service")
public interface UcenterMemberService {
    @GetMapping("/api/ucenter/member/inner/get-member-dto/{memberId}")
    MemberDto getMemberDtoById(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String memberId);
}
