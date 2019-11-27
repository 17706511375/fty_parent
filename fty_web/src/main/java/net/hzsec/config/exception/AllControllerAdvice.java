package net.hzsec.config.exception;

import lombok.extern.slf4j.Slf4j;
import net.hzsec.base.PublicResultConstant;
import net.hzsec.config.shiro.ServletUtil;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Controller统一异常处理
 * @author : xxh
 * @date : 2019/10/29
 */
@ControllerAdvice
@Slf4j
public class AllControllerAdvice {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity errorHandler(Exception ex) {
        ex.printStackTrace();
        log.error("接口出现严重异常：{}", ex.getMessage());
        return ResponseEntity.badRequest().body("接口出现异常" + ex.getMessage());
    }


    /**
     * 捕捉shiro的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseEntity handleShiroException(ShiroException e) {
        log.error("shiro认证错误-{}"+ e.getMessage());
        return ResponseEntity.status (HttpStatus.UNAUTHORIZED).body("shiro认证错误" + e.getMessage());
    }

    /**
     * 捕捉BusinessException自定义抛出的异常
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity handleBusinessException(BusinessException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    /**
     * 捕捉DataAccessException异常
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ResponseEntity handelDuplicateKeyException(Exception e) {
        if(e instanceof DuplicateKeyException) {
            log.error("添加数据异常，重复的数据：{}",e.getMessage());
            return ResponseEntity.badRequest().body("添加数据异常，重复的数据："+e.getMessage());
        }
        return ResponseEntity.badRequest().body("数据库异常-"+e.getMessage());
    }

    /**
     * 捕捉MethodArgumentNotValidException异常
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity handelMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError allError : bindingResult.getAllErrors()) {
            String defaultMessage = allError.getDefaultMessage();
            errorMessage.append(defaultMessage).append(" ");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    /**
     * 捕捉MethodArgumentNotValidException异常
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity handelMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        log.error("数据转换异常：{}", e.getMessage());
        Object value = e.getValue();
        return ResponseEntity.badRequest().body("数据转换异常-" + value);
    }





}