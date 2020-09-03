package com.oket.dispenser;

import com.baomidou.mybatisplus.annotation.TableField;
import com.oket.common.base.Status;
import lombok.Data;

import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/4/14 10:31
 * @description：返回给前端的付油和付油组关系
 */
@Data
public class VoNozzleOutRelGroup {
	//付油组的id
	private int groupId;
	//付油组关联的液位组的id,展示用
	private int traceId;
	//关联id,展示用
	private int groupRelTraceId;
	private List<BzNozzleOut> bzNozzleOuts;
	private Status status;
}
