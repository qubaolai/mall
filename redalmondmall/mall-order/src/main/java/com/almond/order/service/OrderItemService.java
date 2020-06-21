package com.almond.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:06:09
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

