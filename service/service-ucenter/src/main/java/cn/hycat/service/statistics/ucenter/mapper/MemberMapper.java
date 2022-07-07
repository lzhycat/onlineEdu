package cn.hycat.service.statistics.ucenter.mapper;

import cn.hycat.service.statistics.ucenter.domain.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【ucenter_member(会员表)】的数据库操作Mapper
* @createDate 2022-03-27 12:44:17
* @Entity cn.hycat.domain.Member
*/
public interface MemberMapper extends BaseMapper<Member> {

    Integer registerQuery(String day);
}




