package com.almond.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:06:09
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

