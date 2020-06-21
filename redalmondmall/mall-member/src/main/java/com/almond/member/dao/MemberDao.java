package com.almond.member.dao;

import com.almond.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:59:58
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
