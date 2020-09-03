package com.oket.util.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 〈加油站的实时的罐存、测漏、报警〉
 *
 * @author sunbiaolong
 * @create 2019/3/20
 * @since 1.0.0
 */
public class StationDto {

    /**
     * 实时罐存
     */
    private List<JSONObject> realInventories;
    /**
     * 加油站液位报警数据（KEY：高液位报警等类型，VALUE：数量）
     */
    private List<JSONObject> inventoryAlarmCount;
    /**
     * 传感器的报警及故障
     */
    private List<SensorAlarmOrFault> sensorAlarmList;
    /**
     * 加油站的基础数据
     */
    private JSONObject station;


    public List<JSONObject> getRealInventories() {
        return realInventories;
    }

    public void setRealInventories(List<JSONObject> realInventories) {
        this.realInventories = realInventories;
    }

    public List<JSONObject> getInventoryAlarmCount() {
        return inventoryAlarmCount;
    }

    public void setInventoryAlarmCount(List<JSONObject> inventoryAlarmCount) {
        this.inventoryAlarmCount = inventoryAlarmCount;
    }

    public List<SensorAlarmOrFault> getSensorAlarmList() {
        return sensorAlarmList;
    }

    public void setSensorAlarmList(List<SensorAlarmOrFault> sensorAlarmList) {
        this.sensorAlarmList = sensorAlarmList;
    }

    public JSONObject getStation() {
        return station;
    }

    public void setStation(JSONObject station) {
        this.station = station;
    }

    /**
     * 传感器的报警及故障
     */
    public static class SensorAlarmOrFault {
        /**
         * 报警的类型1：报警2：故障
         */
        private Integer type;
        /**
         * 加油站的名称
         */
        private String stationName;
        /**
         * 报警的位置点（油罐、加油机、管线、人孔井、加油站）
         */
        private String alarmPlace;
        /**
         * 报警的类型（高液位报警、短路等）
         */
        private String alarmType;
        /**
         * 报警的时间
         */
        private String alarmTime;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getAlarmPlace() {
            return alarmPlace;
        }

        public void setAlarmPlace(String alarmPlace) {
            this.alarmPlace = alarmPlace;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public String getAlarmTime() {
            return alarmTime;
        }

        public void setAlarmTime(String alarmTime) {
            this.alarmTime = alarmTime;
        }
    }
}
