package com.almond.coupon.dao;

import com.almond.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:56:35
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
