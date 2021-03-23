package cn.jasonhu.commons.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompareJsonResult {

    /**
     * 字段
     */
    private String key;

    /**
     * 原始数据
     */
    private String oldValue;

    /**
     * 更新后数据
     */
    private String newValue;
}
