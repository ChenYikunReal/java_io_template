package com.xxx.xxx_interface.dao;

/**
 * 控制序列化操作的接口
 */
public interface IFileSerialization<T> {
    
    /**
     * 反序列化
     * @return
     */
    T readObject();
    
    /**
     * 序列化
     * @param object
     */
    void writeObject(T object);

}
