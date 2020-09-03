package com.oket.tank4station;

import java.util.List;

/**
 * 液位类型分类器生成器
 *
 * @author wangheng
 * @version 1.0.0
 * @since 2019-11-29
 */

public class TankLevelClassifierFactory {
    private TankLevelClassifier analyzer = null;

    public static TankLevelClassifier<List<TankInventory>> getAnalyzer(LevelSensorError error) {
        return new TankLevelSimpleClassifier(LevelSensorError.OKET);
    }

    static final class TankLevelSimpleClassifier implements TankLevelClassifier<List<TankInventory>> {
        private LevelSensorError levelSensorError;

        public TankLevelSimpleClassifier(LevelSensorError error) {
            this.levelSensorError = error;
        }

        /**
         * 液位分析：根据当前不同状态来确定不同的液位判断策略
         * 1，液位一致是稳定状态，液位在液位仪计量精度范围内变化则认为是稳定状态;
         *
         * @param historySample
         * @param currentSample
         * @return
         */

        @Override
        public LevelState analyze(LevelTrace historySample, List<TankInventory> currentSample) {
            LevelState ret = LevelState.NEW;
            switch (historySample.getLevelState()) {
                case NEW:
                    if (historySample.getLevelCount() == 0 && currentSample.size() > 0) {
                        ret = LevelState.NEW;
//                        historySample.addLevelInfo(currentSample.get(0));
                    } else {

                    }
                    break;

                case LEVEL_STABLE:
                    assert (currentSample != null);
                    assert (historySample != null);

                    if (currentSample.size() == 1) {
                        double delta = historySample.getLastLevel().getLevel() - currentSample.get(0).getLevel();
                        if (Math.abs(delta) <= levelSensorError.getErr()) {
                            return LevelState.LEVEL_STABLE;
                        }
                    } else {

                    }
                    break;

                case LEVEL_ASCENDING:
                    break;
                case LEVEL_DESCENDING:
                    break;
                default:
            }

            return ret;
        }


    }
}
