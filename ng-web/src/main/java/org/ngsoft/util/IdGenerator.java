package org.ngsoft.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by will on 2015-3-9.
 * id生成器
 */
public class IdGenerator {
    private static Object syncObj = new Object();
    private static int increaseId=0;
    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static long getId(){
        long second = System.currentTimeMillis()/1000;
        long ranInt = 0;
        synchronized (syncObj){
            increaseId++;
            if(increaseId>0x7fff){
                increaseId=0;
            }
            ranInt=random.nextInt(0x7fff);
        }
        return second<<32 | ranInt<<16 | increaseId;
    }
    public static void main(String[] args){
        int i=0;
        while(i++<100){
            new Thread(()->{
                System.out.println(IdGenerator.getId());
            }).start();
        }
    }
}
