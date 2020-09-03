package com.oket.tank4station.datagetter;

import com.oket.tank4station.TankInventory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TankInventoryFileGetterTest {

    String regEx="inventory4FileGetterData";
    String path=System.getProperty("user.dir")+ File.separator+"TestSetData\\TankInventory\\tank01_3721\\data\\";
    TankInventoryFileGetter fileGetter=new TankInventoryFileGetter(path,regEx);

    @BeforeEach
    void setUp() { }

    @AfterEach
    void tearDown() {
    }

    @Test
    void load() {
        List<TankInventory> datas=fileGetter.load();
        assert(datas.size()==22);
    }
}