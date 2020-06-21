package com.almond.order.dao;

import com.almond.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:06:09
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
