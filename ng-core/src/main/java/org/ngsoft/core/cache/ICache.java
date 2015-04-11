package org.ngsoft.core.cache;

import java.io.Serializable;

/**
 * Created by will on 2015-3-19.
 */
public interface ICache<K, V> {

    /**
     * 缓存对象
     *
     * @param value
     * @param <T>
     */
    void put(K key, V value);

    /**
     * 获取缓存的对象
     *
     * @param key
     * @param <T>
     * @param <K>
     * @return
     */
    V get(K key);

    /**
     * 移除指定的缓存
     *
     * @param key
     * @param <K>
     */
    void remove(K key);

    /**
     * 返回缓存的最大容量
     *
     * @return
     */
    int maxsize();

    /**
     * 返回当前缓存key数量
     *
     * @return
     */
    int size();

    /**
     * 清空缓存
     */
    void clear();
}
