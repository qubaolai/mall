package com.almond.coupon.dao;

import com.almond.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:56:35
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}
