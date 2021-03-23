package cn.jasonhu.commons.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jason hu
 * @since 2021/3/23 17:13
 */
@Data
@Accessors(chain = true)
public class ReturnResult {

    public int code;

    private String msg;

    private Object data;
}
