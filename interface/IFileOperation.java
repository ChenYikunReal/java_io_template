package com.xxx.xxx_interface.dao;

import java.util.List;

/**
 * 控制普通文件读取操作的类
 * 一般来讲是换行读取、字符流
 */
public interface IFileOperation<T> {
    
    /**
     * 增
     * @param object
     */
    void add(T object);
    
    /**
     * 删
     * @param object
     */
    void delete(T object);
    
    /**
     * 查
     * @return
     */
    List<T> getAll();
    
    /**
     * 改
     * @param object
     */
    void update(T object);

}
