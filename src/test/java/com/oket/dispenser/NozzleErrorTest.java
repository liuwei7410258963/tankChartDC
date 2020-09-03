package com.oket.dispenser;

import com.oket.util4biz.SampleError;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangh
 */
class NozzleErrorTest {
    NozzleError error=new NozzleError(1);
    @Test
    void addError() {
        Date date=new Date();
        /**测试具备误差值单位的添加方法*/
        assert(error.addError(0.1,new Date("2019/11/21"),SampleError.ErrorUnit.PERCENT) );
        //这一步需要重写，测试放入是否准确，同时可以测试获取数据的方法
        assert(error.size()==1);
        /**测试添加误差值相对误差的非百分比表示法*/
        assert(error.addError(0.001,new Date("2019/11/27") ));
        assert(error.size()==2);
        /**添加测试日期在最后一条日期之前的添加方法功能*/
        assert(error.addError(0.001, new Date("2019/11/26") ));
        assert(error.size()==2);
    }
}