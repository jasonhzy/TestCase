package cn.jasonhu.commons.response;

import cn.jasonhu.commons.constants.ReturnCode;
import lombok.Data;

/**
 * @author jason hu
 */
@Data
public class ResponseResult<T> {

    public ReturnCode code;

    private String message;

    private T data;

    public ResponseResult() {
    }

    public ResponseResult(ReturnCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(ReturnCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> fail() {
        ResponseResult<T> result = new ResponseResult();
        result.setCode(ReturnCode.FAIL);
        result.setMessage(ReturnCode.FAIL.name());
        return result;
    }

    public static <T> ResponseResult<T> fail(T data) {
        ResponseResult<T> result = new ResponseResult();
        result.setCode(ReturnCode.FAIL);
        result.setMessage(ReturnCode.FAIL.name());
        result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> result = new ResponseResult();
        result.setCode(ReturnCode.SUCCESS);
        result.setMessage(ReturnCode.SUCCESS.name());
        return result;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult();
        result.setCode(ReturnCode.SUCCESS);
        result.setMessage(ReturnCode.SUCCESS.name());
        result.setData(data);
        return result;
    }
}
