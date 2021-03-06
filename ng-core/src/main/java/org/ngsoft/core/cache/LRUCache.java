package org.ngsoft.core.cache;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by will on 2015-3-19.
 */
public class LRUCache<K ,V> implements ICache<K,V> {

    private CacheMap<K,WeakReference<?>> cache;
    private ReentrantLock lock;

    public LRUCache(int maxSize){
        cache = new CacheMap<K,WeakReference<?>>(maxSize);
        lock=new ReentrantLock();
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, new WeakReference<V>(value));
    }

    @Override
    public V get(K key) {
        return (V)cache.get(key).get();
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public int maxsize() {
        return cache.maxSize;
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public void clear() {
        cache.clear();
    }

    private static class CacheMap<K,V> extends LinkedHashMap<K,V>{

        public CacheMap(int maxSize){
            this.maxSize=maxSize;
        }

        private int maxSize=32;

        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
            return this.size()>this.maxSize;
        }
    }
}
