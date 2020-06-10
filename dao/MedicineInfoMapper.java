package com.xxx.dao;

import com.xxx.xxx_interface.dao.IFileSerialization;
import com.xxx.po.AllMedicinesMap;

import java.io.*;

import static com.xxx.util.Constant.ALL_MEDICINES_FILENAME;

/**
 * 查询各种药品的情况的Mapper
 * 数据结构课设的新增类
 * 主要是为了实现药物数量处理的功能
 */
public class MedicineInfoMapper implements IFileSerialization<AllMedicinesMap> {

    private MedicineInfoMapper() {}

    private static MedicineInfoMapper mapper;

    /**
     * 获取单例
     * @return 单例
     */
    public static MedicineInfoMapper getInstance() {
        if (mapper == null) {
             mapper = new MedicineInfoMapper();
        }
        return mapper;
    }

    /**
     * 反序列化从文件中读取PatientList的序列化对象
     */
    @Override
    public AllMedicinesMap readObject() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ALL_MEDICINES_FILENAME))) {
            AllMedicinesMap medicinesMap = (AllMedicinesMap)ois.readObject();
            return medicinesMap;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 序列化对象
     */
    @Override
    public void writeObject(AllMedicinesMap object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ALL_MEDICINES_FILENAME))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
