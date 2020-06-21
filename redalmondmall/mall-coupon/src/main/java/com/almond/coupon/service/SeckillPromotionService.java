package com.almond.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:56:35
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

