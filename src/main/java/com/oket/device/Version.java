package com.oket.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "dev_version")
public class Version implements DeviceVersion {
	@TableId(type = IdType.AUTO)
	private Long id;
	private Integer deviceId;
	private String deviceVersion;
	private String deviceSoftVersion;
	private String copyright;

	private String versionDate;
	@JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
	private DeviceType deviceType;
}
