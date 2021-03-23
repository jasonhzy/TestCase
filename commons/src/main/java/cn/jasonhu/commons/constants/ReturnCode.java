package cn.jasonhu.commons.constants;

/**
 * @author jason hu
 * @since 2021/3/23 17:11
 */
public enum ReturnCode {

    // 成功
    SUCCESS(0),
    // 失败
    FAIL(-1)
    ;

    public int code;

    ReturnCode(int code) {
        this.code = code;
    }
}
