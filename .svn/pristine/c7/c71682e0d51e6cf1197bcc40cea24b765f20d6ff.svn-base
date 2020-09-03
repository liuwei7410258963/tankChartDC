package com.oket.station.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oket.common.base.Status;
import com.oket.config.exception.CommonJsonException;
import com.oket.delivery.BzDelivery;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.station.HandOver;
import com.oket.station.dao.HandOverDao;
import com.oket.station.service.HandOverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.util.AirUtils;
import com.oket.util.ExcelUtil;
import com.oket.util.TimeUtils;
import com.oket.util.constants.ErrorEnum;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-08-19
 */
@Service
public class HandOverServiceImpl extends ServiceImpl<HandOverDao, HandOver> implements HandOverService{

    @Override
    public IPage<HandOver> query(JSONObject req, boolean isPage) {
        IPage<HandOver> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("tank_no",req.getString("tankNo"));
        if (req.get("startTime") != null && req.get("endTime") != null) {
            try {
                wrapper.ge("time", TimeUtils.parseToSecond(req.getString("startTime")));
                wrapper.le("time", TimeUtils.parseToSecond(req.getString("endTime")));
            } catch (ParseException e) {
                log.error("解析时间失败", e);
                throw new CommonJsonException(ErrorEnum.E_70015);
            }
        }
        wrapper.eq("status",1);
        wrapper.orderByDesc("time");
        if (isPage) {
            return baseMapper.selectPage(page, wrapper);
        } else {
            final List<HandOver> bzDeliveries = baseMapper.selectList(wrapper);
            page.setRecords(bzDeliveries);
            return page;
        }
    }

    @Override
    public List<HandOver> upload(MultipartFile file) throws IOException {
        Workbook wookbook;
        try {
             wookbook = ExcelUtil.createWorkbook(file);
        }
        catch (Exception e){
            return null;
        }
        // 第一个sheet
        Sheet sheet = wookbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();

        List<HandOver> exportLists = new ArrayList<>();
        String tankNo = "";
        int num = 0;
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            //设置excel内容为String
            for (int c = 0; c < row.getLastCellNum(); c++) {
                if (row.getCell(c) != null) {
                    row.getCell(c).setCellType(CellType.STRING);
                }
            }
            if(AirUtils.hv(ExcelUtil.getCellValue(row.getCell(6)).trim())){
                 tankNo = ExcelUtil.getCellValue(row.getCell(6)).trim();
                 num++;
            }
            if(AirUtils.hv(ExcelUtil.getCellValue(row.getCell(2)).trim())){
                String hour,second,type,originalNum,receivedNum,overflowNum,dispenserOutNum,backNum;
                String dispenserTrueOutNum,tankOutNum,lossNum,allHeight,waterHeight,oilVlume,temp;
                String experimentalDensity,standardDensity,oilTemp,vcf20,oilTrueVolume,lossSum;
                //第一页错开了一行
                if(num==1){
                    hour = ExcelUtil.getCellValue(row.getCell(2)).trim();
                    second = ExcelUtil.getCellValue(row.getCell(7)).trim();
                    type = ExcelUtil.getCellValue(row.getCell(10)).trim();
                    originalNum = ExcelUtil.getCellValue(row.getCell(13)).trim();
                    receivedNum = ExcelUtil.getCellValue(row.getCell(16)).trim();
                    overflowNum = ExcelUtil.getCellValue(row.getCell(18)).trim();
                    dispenserOutNum = ExcelUtil.getCellValue(row.getCell(20)).trim();
                    backNum = ExcelUtil.getCellValue(row.getCell(23)).trim();
                    dispenserTrueOutNum = ExcelUtil.getCellValue(row.getCell(26)).trim();
                    tankOutNum = ExcelUtil.getCellValue(row.getCell(30)).trim();
                    lossNum = ExcelUtil.getCellValue(row.getCell(33)).trim();
                    allHeight = ExcelUtil.getCellValue(row.getCell(35)).trim();
                    waterHeight = ExcelUtil.getCellValue(row.getCell(37)).trim();
                    oilVlume = ExcelUtil.getCellValue(row.getCell(39)).trim();
                    temp = ExcelUtil.getCellValue(row.getCell(41)).trim();
                    experimentalDensity = ExcelUtil.getCellValue(row.getCell(44)).trim();
                    standardDensity = ExcelUtil.getCellValue(row.getCell(46)).trim();
                    oilTemp = ExcelUtil.getCellValue(row.getCell(48)).trim();
                    vcf20 = ExcelUtil.getCellValue(row.getCell(50)).trim();
                    oilTrueVolume = ExcelUtil.getCellValue(row.getCell(52)).trim();
                    lossSum = ExcelUtil.getCellValue(row.getCell(54)).trim();
                }
                else {
                    hour = ExcelUtil.getCellValue(row.getCell(2)).trim();
                    second = ExcelUtil.getCellValue(row.getCell(7)).trim();
                    type = ExcelUtil.getCellValue(row.getCell(10)).trim();
                    originalNum = ExcelUtil.getCellValue(row.getCell(13)).trim();
                    receivedNum = ExcelUtil.getCellValue(row.getCell(16)).trim();
                    overflowNum = ExcelUtil.getCellValue(row.getCell(18)).trim();
                    dispenserOutNum = ExcelUtil.getCellValue(row.getCell(20)).trim();
                    backNum = ExcelUtil.getCellValue(row.getCell(23)).trim();
                    dispenserTrueOutNum = ExcelUtil.getCellValue(row.getCell(26)).trim();
                    tankOutNum = ExcelUtil.getCellValue(row.getCell(29)).trim();
                    lossNum = ExcelUtil.getCellValue(row.getCell(32)).trim();
                    allHeight = ExcelUtil.getCellValue(row.getCell(34)).trim();
                    waterHeight = ExcelUtil.getCellValue(row.getCell(36)).trim();
                    oilVlume = ExcelUtil.getCellValue(row.getCell(38)).trim();
                    temp = ExcelUtil.getCellValue(row.getCell(40)).trim();
                    experimentalDensity = ExcelUtil.getCellValue(row.getCell(43)).trim();
                    standardDensity = ExcelUtil.getCellValue(row.getCell(45)).trim();
                    oilTemp = ExcelUtil.getCellValue(row.getCell(47)).trim();
                    vcf20 = ExcelUtil.getCellValue(row.getCell(49)).trim();
                    oilTrueVolume = ExcelUtil.getCellValue(row.getCell(51)).trim();
                    lossSum = ExcelUtil.getCellValue(row.getCell(53)).trim();
                }
                HandOver handOver = new HandOver();
                handOver.setTankNo(tankNo);
                handOver.setTime(hour+" "+second);
                handOver.setType(type);
                handOver.setOriginalNum(Double.parseDouble(originalNum));
                handOver.setReceivedNum(Double.parseDouble(receivedNum));
                handOver.setOverflowNum(Double.parseDouble(overflowNum));
                handOver.setDispenserOutNum(Double.parseDouble(dispenserOutNum));
                handOver.setBackNum(Double.parseDouble(backNum));
                handOver.setDispenserTrueOutNum(Double.parseDouble(dispenserTrueOutNum));
                handOver.setTankOutNum(Double.parseDouble(tankOutNum));
                handOver.setLossNum(Double.parseDouble(lossNum));
                handOver.setAllHeight(Double.parseDouble(allHeight));
                handOver.setWaterHeight(Double.parseDouble(waterHeight));
                handOver.setOilVlume(Double.parseDouble(oilVlume));
                handOver.setTemp(Double.parseDouble(temp));
                handOver.setExperimentalDensity(Double.parseDouble(experimentalDensity));
                handOver.setStandardDensity(Double.parseDouble(standardDensity));
                handOver.setOilTemp(Double.parseDouble(oilTemp));
                handOver.setVcf20(Double.parseDouble(vcf20));
                handOver.setOilTrueVolume(Double.parseDouble(oilTrueVolume));
                handOver.setLossSum(Double.parseDouble(lossSum));
                handOver.setStatus(Status.ENABLE);
                exportLists.add(handOver);
            }
        }
        List<HandOver> dbLists = baseMapper.selectList(new QueryWrapper<>());
        List<HandOver> result = new ArrayList<>();
        if(AirUtils.hv(dbLists)) {
            //交集，被过滤的数据
            List<HandOver> needFilterLists = dbLists.stream().filter(item -> exportLists.stream().map(e -> e.getTime())
                    .collect(Collectors.toList()).contains(item.getTime())).collect(Collectors.toList());
            if(AirUtils.hv(needFilterLists)){
                exportLists.removeAll(needFilterLists);
                needFilterLists.stream().forEach(x -> x.setFlag(false));
                result.addAll(needFilterLists);
            }
        }
        exportLists.stream().forEach(x -> x.setFlag(true));
        result.addAll(exportLists);
        this.saveBatch(exportLists);
        return result;
    }

    @Override
    public void delete(JSONObject jsonObject) {
        List<String> lists =  jsonObject.getJSONArray("list").toJavaList(String.class);
        baseMapper.deleteBatchIds(lists);
    }
}
