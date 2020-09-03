package com.oket.tankchartdc.upload;

import lombok.Data;

/**
 * 可修改的ifsf，json端口ip地址类
 * 现在仅保存和展示，将来再修改
 */
@Data
public class ModifiablePort {
    private String modifiableIfsf;
    private String modifiableJson;
    private String modifiableIp;
}
