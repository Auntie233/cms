package top.auntie.cms.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tay
 */
@Data
public class ResultModel implements Serializable {

    private Boolean success = true;

    private Object result = null;

    private String errMsg = "";

    public ResultModel(String errMsg) {
        this.errMsg = errMsg;
        this.success = false;
    }

    public ResultModel(Object result, Boolean success) {
        this.result = result;
        this.success = success;
    }

    public ResultModel(Object result) {
        this.result = result;
    }

    public ResultModel() {
    }

    public static ResultModel valueOf(Object object){
        return (ResultModel) object;
    }

    public static ResultModel success(Object object) {
        return new ResultModel(object, true);
    }

    public static ResultModel success() {
        return new ResultModel();
    }

    public static ResultModel failed(String errMsg) {
        return new ResultModel(errMsg);
    }

}
