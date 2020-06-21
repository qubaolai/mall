package com.almond.product.common.exception;

import com.almond.common.exception.ErrorCodeEnum;
import com.almond.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * @author qubaolai
 * @version 1.0.0
 * @Description 自定义异常处理类
 * @createTime 2020年06月20日 10:29:00
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R handlerValidException(MethodArgumentNotValidException e) {
        log.error("发生异常{}", e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> errResult = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item -> {
            errResult.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(ErrorCodeEnum.VALID_EXCEPTION.getCode(), ErrorCodeEnum.VALID_EXCEPTION.getMessage()).put("data", errResult);
    }
}
