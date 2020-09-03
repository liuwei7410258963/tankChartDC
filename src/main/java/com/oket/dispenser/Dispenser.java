package com.oket.dispenser;

import com.oket.device.DeviceItem;
import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
public class Dispenser {
	/**
	 * @see DeviceItem#getId()
	 */
	protected long dispenserID;
	protected String name;
	protected String position;
	private final Set<Nozzle> nozzles = Collections
			.synchronizedSet(new HashSet<Nozzle>());

	public Dispenser(long id, String name, String position) {
		this.dispenserID = id;
		this.name = name;
		this.position = position;
	}

	public boolean addHose(Nozzle nozzle) {
		//以下需要做null检查，且做失败后返回修改
		nozzles.add(nozzle);
		return true;
	}
}
