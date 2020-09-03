package com.oket.tank4station.datagetter;

import com.oket.tank4station.Inventory;
import com.oket.tank4station.TankInventory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TankInventoryFileGetter implements TankInventoryGetter {
    /**
     * <p>{@link #baseFilePath} 文件保存绝对路径</p>
     * <p>{@link #regExp} 文件名称匹配表达式</p>
     * */
    private String baseFilePath;
    private String regExp;


    public TankInventoryFileGetter(String baseFilePath, String regExp) {
        this.baseFilePath = baseFilePath;
        this.regExp = regExp;
    }
    @Override
    public List<TankInventory> load() {
        File file = new File(baseFilePath);
        String[] files = file.list();
        List<String> invnetoryFiles = new ArrayList<String>();
        for (String fileName : files) {
            if (fileName.startsWith(regExp)) {
                invnetoryFiles.add(baseFilePath +File.separator + fileName);
            }
        }
        try {
            return getInventory4Line(invnetoryFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<TankInventory>();
    }
    private List<TankInventory> getInventory4Line(List<String> fileNames) throws Exception {
        List<TankInventory> datas = new ArrayList<TankInventory>();
        long id=0;
        for (String f : fileNames) {

            File file = new File(f);
            InputStream is = null;
            Reader reader = null;
            BufferedReader bufferedReader = null;
            try {
                is = new FileInputStream(file);
                reader = new InputStreamReader(is);
                bufferedReader = new BufferedReader(reader);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    id++;
                    String  []tmps=line.split(",");
                    if(tmps.length==4){
                        TankInventory data = new Inventory();
                        data.setTankNo(Integer.valueOf(tmps[0]));
                        data.setLevel(Double.parseDouble(tmps[1].toString()));
                        data.setTime(new java.sql.Timestamp(java.sql.Timestamp.valueOf(tmps[2]).getTime()));
                        data.setTemperature(Double.parseDouble(tmps[3]));
                        datas.add(data);
                    }
                    System.out.println(id + "=" + line);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bufferedReader)
                        bufferedReader.close();
                    if (null != reader)
                        reader.close();
                    if (null != is)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



/*            FileReader reader = new java.io.FileReader(f);
            int c;
            StringBuffer sb = new StringBuffer();

            TankInventory data = new Inventory();
            int pos = 0;
            while ((c = reader.read()) != -1) {
                if (c == '\t' || c == ',' || c == '\n') {
                    if (pos == 0) {
                        String tankId = sb.toString().trim();
                        data.setTankID(Integer.valueOf(tankId));
                        data.setId(id);
                        datas.add(data);

                        id++;
                    } else if (pos == 1) {
                        data.setLevel(Double.parseDouble(sb.toString()));
                    } else if (pos ==2){
                        String milles=sb.toString();
                        data.setMilles(new java.sql.Timestamp(java.sql.Timestamp
                                .valueOf(milles).getMilles()));
                    }else if (pos ==3){
                        data.setTemperature(Double.parseDouble(sb.toString()));
                    }
                    sb = new StringBuffer();
                    pos++;

                    if (c == '\n') {
                        data = new Inventory();
                        pos = 0;
                        sb = new StringBuffer();
                        continue;
                    }

                    continue;
                }
                sb.append((char) c);
            }
            reader.close();
        }
*/
        return datas;
    }
/*    private List<TankInventory> getInventory(List<String> file) throws Exception {
        List<TankInventory> datas = new ArrayList<TankInventory>();
        long id=0;
        for (String f : file) {
            FileReader reader = new java.io.FileReader(f);
            int c;
            StringBuffer sb = new StringBuffer();

            TankInventory data = new Inventory();
            int pos = 0;
            while ((c = reader.read()) != -1) {
                if (c == '\t' || c == ',' || c == '\n') {
                    if (pos == 0) {
                        String tankId = sb.toString().trim();
                        data.setTankID(Integer.valueOf(tankId));
                        data.setId(id);
                        datas.add(data);
                        
                        id++;
                    } else if (pos == 1) {
                        data.setLevel(Double.parseDouble(sb.toString()));
                    } else if (pos ==2){
                        String milles=sb.toString();
                        data.setMilles(new java.sql.Timestamp(java.sql.Timestamp
                                .valueOf(milles).getMilles()));
                    }else if (pos ==3){
                        data.setTemperature(Double.parseDouble(sb.toString()));
                    }
                    sb = new StringBuffer();
                    pos++;

                    if (c == '\n') {
                        data = new Inventory();
                        pos = 0;
                        sb = new StringBuffer();
                        continue;
                    }

                    continue;
                }
                sb.append((char) c);
            }
            reader.close();
        }
        return datas;
    }

*/
}
