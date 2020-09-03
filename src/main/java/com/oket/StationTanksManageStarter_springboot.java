package com.oket;
/**
 * springboot启动器
 *
 * @author 王恒
 * @since 2020-04-08
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class StationTanksManageStarter_springboot {
	private StationTanksManage tanksManage;

	public StationTanksManageStarter_springboot() {
		System.out.println("StationTanksManageStarter_springboot");
	}

	@PostConstruct
	public void init() {
		try {
			tanksManage = new StationTanksManage();
			System.out.println("StationTanksManageStarter_springboot init");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
