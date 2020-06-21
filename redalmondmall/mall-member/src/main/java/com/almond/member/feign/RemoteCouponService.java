package com.almond.member.feign;

import com.almond.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("mall-coupon")
public interface RemoteCouponService {
    @GetMapping("/coupon/coupon/member/list")
    public R memberCoupons();
}
