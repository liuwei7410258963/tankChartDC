package com.oket.tankchartdc.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;

/**
 * 可修改的ifsf，json端口ip地址service类
 * 现在仅保存和展示，将来再修改
 */
@Service
@Slf4j
public class ModifiablePortService{
    /**
     * 初始化可修改的ifsf，json端口ip地址service类
     */
    public Map<String, Object> loadProperties(String modifiablePortAndIpPath) throws Exception  {
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader(modifiablePortAndIpPath));
        properties.load(bufferedReader);
        // 获取key对应的value值
        Map<String, Object> portMap = new HashMap<>();
        portMap.put("ifsfPort", properties.getProperty("dit.emulator.ifsfPort"));
        portMap.put("jsonPort", properties.getProperty("dit.emulator.jsonPort"));
        portMap.put("ip", properties.getProperty("dit.emulator.connectIp"));
        return portMap;
    }

    public void changePortAndIp(ModifiablePort modifiablePort,String modifiablePortAndIpPath) {
        //1.先实例化一个Properties对象
        Properties properties = new Properties();
        try {
            //2.创建一个输出流对象,选择正确的目标文件路径(注意:该配置文件放在src目录下)
            FileOutputStream fos = new FileOutputStream(modifiablePortAndIpPath,false);//这里true表示追加,如果不设为true的话,会将原文件清空后,重新添加,切记!!!
            OutputStreamWriter opw = new OutputStreamWriter(fos,"utf-8");//引入Writer,可以明确该输出流的字符集,确保写入配置文件的中文编码正确

            //3.将需要写入的属性内容通过set方法,存入properties对象中
            properties.setProperty("dit.emulator.ifsfPort",modifiablePort.getModifiableIfsf());
            properties.setProperty("dit.emulator.jsonPort",modifiablePort.getModifiableJson());
            properties.setProperty("dit.emulator.connectIp",modifiablePort.getModifiableIp());

            //4.调用properties的存储方法
            properties.store(opw,"modifiablePortAndIp");

            //5.关闭资源
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
