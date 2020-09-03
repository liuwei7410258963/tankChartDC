package com.oket.tankchartdc;

import com.oket.tank4station.AbstractLevelTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Observable;

/**
 * @description: 液位轨迹处理器
 * @author: SunBiaoLong
 * @create: 2020-04-14 15:53
 **/
@Service
@Scope(value = "singleton")
public class LevelTraceProcessor extends Observable {
	@Autowired
	private NozzleOutGroupProcessor nozzleOutGroupProcessor;

	@PostConstruct
	public void init() {
		//添加观察者
		addObserver(nozzleOutGroupProcessor);
	}

	/**
	 * 处理液位周期
	 *
	 * @param levelTrace
	 */
	public void processLevelTrace(AbstractLevelTrace levelTrace) {
		if (!levelTrace.isMatchFinished()){
			this.setChanged();
			notifyObservers(levelTrace);
		}
	}
}
