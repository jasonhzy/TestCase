package cn.jasonhu.commons.response;

import cn.jasonhu.commons.constants.ReturnCode;

/**
 * @author jason hu
 * @since 2021/3/23 17:13
 */
public class ResultRes {

    public static ReturnResult success() {
        return new ReturnResult().setCode(ReturnCode.SUCCESS.code)
                .setMsg(ReturnCode.SUCCESS.name());
    }

    public static ReturnResult success(Object data) {
        return new ReturnResult().setCode(ReturnCode.SUCCESS.code)
                .setMsg(ReturnCode.SUCCESS.name()).setData(data);
    }

    public static ReturnResult success(Object data, String message) {
        return new ReturnResult().setCode(ReturnCode.SUCCESS.code)
                .setMsg(message).setData(data);
    }

    public static ReturnResult error() {
        return new ReturnResult().setCode(ReturnCode.FAIL.code)
                .setMsg(ReturnCode.FAIL.name());
    }

    public static ReturnResult error(String message) {
        return new ReturnResult().setCode(ReturnCode.FAIL.code)
                .setMsg(message);
    }
}
