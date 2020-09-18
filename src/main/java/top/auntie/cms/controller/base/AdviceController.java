package top.auntie.cms.controller.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.model.ResultModel;

/**
 * @Description
 * @Author Mr Cui
 * @Date 2020/3/27 14:25
 * @Version 1.0
 */
@RestControllerAdvice
public class AdviceController {
    private static final Log log = LogFactory.getLog(AdviceController.class);

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResultModel handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("上传文件异常", e);
        return ResultModel.failed("上传文件失败");
    }

    @ExceptionHandler(Exception.class)
    public ResultModel handleSQLException(Exception e) {
        log.error("数据处理失败", e);
        return ResultModel.failed("数据处理失败");
    }

    @ExceptionHandler(CommonException.class)
    public ResultModel handleSQLException(CommonException e) {
        log.error(e.getErrorMessage(), e);
        return ResultModel.failed(e.getErrorMessage());
    }
}
