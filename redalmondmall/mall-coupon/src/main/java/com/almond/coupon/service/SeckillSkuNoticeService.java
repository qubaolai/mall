package com.almond.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.coupon.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:56:35
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

