package top.auntie.cms.exception;

import lombok.Data;

/**
 * @author tay
 */
@Data
public class CommonException extends Exception {

    private String errorMessage;

    private String errorCode;

    public CommonException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CommonException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
