package com.oket.tankchartdc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oket.oil.cache.OilCacheManager;
import com.oket.tank4station.dao.DbInventorCycleDAO;
import com.oket.tank4station.dao.DbInventoryDAO;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tankchartdc.DbInventoryCycle;
import com.oket.tankchartdc.entity.*;
import com.oket.tankchartdc.service.CorrectSamplesService;
import com.oket.tankchartdc.service.DbInventoryCycleService;
import com.oket.tankchartdc.service.ExportService;
import com.oket.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ：lw
 * @date ：Created in 2020/5/26 13:56
 * @description：
 */
@Slf4j
@Service
public class ExportServiceImpl implements ExportService {

    @Value("${exportFilePath}")
    private String exportFilePath;
    @Value("${exportZipFilePath}")
    private String exportZipFilePath;

    @Autowired
    DbInventoryCycleService dbInventoryCycleService;
    @Autowired
    CorrectSamplesServiceImpl correctingService;
    @Autowired
    DbInventorCycleDAO dbInventorCycleDAO;
    @Override
    public IPage<DbInventoryCycle> select(JSONObject jsonObject) {
        Calendar cal = Calendar.getInstance();
        //查询数据库中的周期数据
        IPage<DbInventoryCycle> dbPage = selectDbInventoryCycleIPage(jsonObject);
        //检查导出路径，没有就新建
        ReadWriteFile.checkPathExist(exportFilePath);
        List<File> existFiles = ReadWriteFile.getFileSortEx(exportFilePath);
        for(DbInventoryCycle cycle:dbPage.getRecords()) {
            existFiles.stream().
                    filter(x -> ("tankNo" + cycle.getTankNo()).equals(ReadWriteFile.getParentFileNameWithoutPrefix(x)))
                    .filter(x -> ("deliveryId" + cycle.getDeliveryId()).equals(ReadWriteFile.getFileNameWithoutSuffix(x).substring(0, x.getName().indexOf("-"))))
                    .forEach(x -> {
                        long time = x.lastModified();
                        cal.setTimeInMillis(time);
                        cycle.setLastTime(cal.getTime().toLocaleString());
                        //已导出过
                        cycle.setFlag(true);
                    });
        }
        return dbPage;
    }

    /*
     * 查询数据库中符合条件的周期
     */
    private IPage<DbInventoryCycle> selectDbInventoryCycleIPage(JSONObject jsonObject) {
        IPage<DbInventoryCycle> page = new Page<>(jsonObject.getIntValue("pageNum"), jsonObject.getIntValue("pageRow"));
        QueryWrapper<DbInventoryCycle> queryWrapper = new QueryWrapper();
        queryWrapper.select("delivery_id", "start_time", "end_time", "tank_no");
        //查询条件
        if (jsonObject.getString("startTime") != null && jsonObject.getString("endTime") != null) {
            try {
                queryWrapper.lambda().ge(DbInventoryCycle::getStartTime, TimeUtils.parseToSecond(jsonObject.getString("startTime")));
                queryWrapper.lambda().and(w -> {
                            try {
                                w.le(DbInventoryCycle::getEndTime, TimeUtils.parseToSecond(jsonObject.getString("endTime")))
                                        .or().isNull(DbInventoryCycle::getEndTime);
                            } catch (ParseException e) {
                                log.error(e.getMessage(), e);
                            }
                        }
                );
            } catch (ParseException e) {
                log.error("", e);
            }
        }
        if (jsonObject.getString("tankNo") != null) {
            queryWrapper.lambda().eq(DbInventoryCycle::getTankNo, jsonObject.getString("tankNo"));
        }
        if (jsonObject.getString("start_delivery_id") != null) {
            queryWrapper.lambda().ge(DbInventoryCycle::getDeliveryId, jsonObject.getIntValue("start_delivery_id"));
        }
        if (jsonObject.getString("end_delivery_id") != null) {
            queryWrapper.lambda().le(DbInventoryCycle::getDeliveryId, jsonObject.getIntValue("end_delivery_id"));
        }
        queryWrapper.orderByAsc("tank_no,start_time");
        return dbInventorCycleDAO.selectPage(page, queryWrapper);
    }

    @Override
    public JSONObject export(List<JSONObject> queryLists) {
        List<String> fileNames = new ArrayList<>();
        Date now = new Date();
        String time = TimeUtils.date2String1(now);
        //一次导出一个罐
        for(JSONObject list:queryLists){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tankNo",list.getIntValue("tankNo"));
            List<Integer> ids = Arrays.stream(list.getString("deliveryIds").split(",")).map(Integer::new).collect(Collectors.toList());
            List<CorrectSamples> dbLists = correctingService.getResultCorrectSamples(jsonObject,ids);
            Samples samples = null;
            String deliveryId = "" ;
            //罐内的周期有数据
            List<SamplesValues> result = new ArrayList<>();
            if(dbLists.size()!=0) {
                for (CorrectSamples dto : dbLists) {
                    if (dto.getFlag()!=null && dto.getFlag()) {
                        //第一条数据
                        if (samples == null) {
                            samples = new Samples();
                            samples.setS_ID(dto.getCycleId());
                            addLists(result, dto);
                        } else {
                            samples.setValues(result);
                            //生成文件保存
                            wirteFile(fileNames, time, list.getIntValue("tankNo"), samples,deliveryId);
                            //清空samples实体
                            samples = new Samples();
                            samples.setS_ID(dto.getCycleId());
                            result.clear();
                            addLists(result, dto);
                        }
                    }
                    //周期的其他数据
                    else {
                        addLists(result, dto);
                        deliveryId = dto.getDeliveryId()+"";
                    }
                }
            }
            //罐内的周期没有数据，生成样本参数为空的文件
            else{
                String[] arrs = list.getString("deliveryIds").split(",");
                for(String arr:arrs) {
                    wirteFile(fileNames, time, list.getIntValue("tankNo"), "当前周期没有数据，请点击生成", arr);
                }
            }
            if(result.size()!=0) {
                //最后一个周期生成文件保存
                samples.setValues(result);
                wirteFile(fileNames, time, list.getIntValue("tankNo"), samples, deliveryId);
            }
        }
        return CommonUtil.successJson(fileNames);
    }

    private void addLists(List<SamplesValues> result, CorrectSamples dto) {
        SamplesValues samples1 = new SamplesValues();
        samples1.setL(dto.getLevel());
        samples1.setO_L(dto.getLevel());
        samples1.setT_T(dto.getTemp());
        samples1.setS_T(dto.getTemp());
        samples1.setI_V(dto.getOutNum());
        samples1.setL_V(dto.getVolume());
        result.add(samples1);
    }

    public void wirteFile(List<String> fileNames, String now, long tankNo, Object samples, String deliveryId) {
        ReadWriteFile.checkPathExist(exportFilePath + "/tankNo" + tankNo);
        ReadWriteFile.checkFileExist(exportFilePath + "/tankNo" + tankNo, "deliveryId" + deliveryId + "-" + now + ".json");

        String jsonString = JSON.toJSONString(samples, SerializerFeature.DisableCircularReferenceDetect);
        // 格式化json字符串
        jsonString = JsonFormatTool.formatJson2(jsonString);
        // 将格式化后的字符串写入文件
        try {
            String finallyPath = exportFilePath + "/tankNo" + tankNo + "/deliveryId" + deliveryId + "-" + now + ".json";
            Writer write = new OutputStreamWriter(new FileOutputStream(finallyPath), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
            fileNames.add(finallyPath);
        } catch (Exception e) {
            log.error("保存文件失败了，原因是:" + e);
            System.out.println(e);
        }
    }
    @Override
    public  void download(HttpServletResponse response,List<JSONObject> jsonObjectList){
        List<String> lists = new ArrayList<>();
        jsonObjectList.stream().forEach(x->{
            lists.add(x.getString("value"));
        });
        //存放--服务器上zip文件的目录
        String directory = exportZipFilePath;
        File directoryFile=new File(directory);
        if(!directoryFile.isDirectory() && !directoryFile.exists()){
            directoryFile.mkdirs();
        }
        //设置最终输出zip文件的目录+文件名
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String zipFileName = formatter.format(new Date())+".zip";
        String strZipPath = directory+"\\"+zipFileName;

        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        File zipFile = new File(strZipPath);
        try{
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (String x:lists){
                //解码获取真实路径与文件名
                String realFileName = java.net.URLDecoder.decode(x.substring(x.lastIndexOf("/")-7),"UTF-8");
                String realFilePath = java.net.URLDecoder.decode(x,"UTF-8");
                File file = new File(realFilePath);
                if(file.exists())
                {
                    zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                    /**
                     * 压缩条目不是具体独立的文件，而是压缩包文件列 表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                     * 文件名和之前的重复就会导致文件被覆盖
                     */
                    ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                    zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1)
                    {
                        zipStream.write(buf, 0, read);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if(null != bufferStream) bufferStream.close();
                if(null != zipStream){
                    zipStream.flush();
                    zipStream.close();
                }
                if(null != zipSource) zipSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断系统压缩文件是否存在：true-把该压缩文件通过流输出给客户端后删除该压缩文件  false-未处理
        if(zipFile.exists()){
            downImg(response,zipFileName,strZipPath);
            try {
              Thread.sleep(1000);
            }
            catch (Exception e){

            }
            zipFile.delete();
        }
    }

    public void downImg(HttpServletResponse response,String filename,String path ) {
        if (filename != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    response.setCharacterEncoding("UTF-8"); // 重点突出
                    response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
                    // inline在浏览器中直接显示，不提示用户下载
                    // attachment弹出对话框，提示用户进行下载保存本地
                    // 默认为inline方式
                    response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));

//                    response.setContentType("application/x-download");
//                    response.setHeader("Content-disposition",
//                            "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//                    //设置Headers
//                    response.setHeader("Content-Type", "application/octet-stream");
//                    //设置下载的文件的名称-该方式已解决中文乱码问题
//                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
                    is = new FileInputStream(file);
                    bs = new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024*1024];
                    int len = 0;
                    while ((len = bs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (bs != null) {
                        bs.close();
                    }
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
