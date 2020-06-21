package com.almond.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.almond.member.feign.RemoteCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.almond.member.entity.MemberEntity;
import com.almond.member.service.MemberService;
import com.almond.common.utils.PageUtils;
import com.almond.common.utils.R;



/**
 * 会员
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:59:58
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private RemoteCouponService remoteCouponService;
    /**
     * 测试远程服务调用
     */
    @PostMapping("/coupons")
    public R getCouponByMember(){
        R coupons = remoteCouponService.memberCoupons();
        return coupons;
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
