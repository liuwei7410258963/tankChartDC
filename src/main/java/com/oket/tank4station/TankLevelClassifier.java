package com.oket.tank4station;

public interface TankLevelClassifier<T> {

	LevelState analyze(LevelTrace historySample, T currentSamples);
}
