package com.almond.common.validation.validator;

import com.almond.common.annotation.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author qubaolai
 * @version 1.0.0
 * @Description 自定义校验注解的校验器
 * @createTime 2020年06月21日 15:05:00
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    private Set<Integer> valueSet = new HashSet<>();

    /**
     * 初始化
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] value = constraintAnnotation.value();
        if (value != null && 0 < value.length) {
            for (int val : value) {
                valueSet.add(val);
            }
        }
    }

    /**
     * 校验是否通过
     * @param integer
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return valueSet.contains(integer);
    }
}
