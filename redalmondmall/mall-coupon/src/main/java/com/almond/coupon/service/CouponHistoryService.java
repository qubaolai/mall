package com.almond.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.coupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:56:35
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

