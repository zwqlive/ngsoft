package org.ngsoft.core.automapping;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;

import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Created by will on 2015-3-10.
 */
public class AutoMapTable {

    /**
     * 自动注册model与数据表的映射关系
     *
     * @param arPlugin
     * @param packages
     */

    @SuppressWarnings("unchecked")
    public static void mapTable(ActiveRecordPlugin arPlugin, String[] packages){
        Set<Class<?>> modelClasses = ClassScanner.getClasses(packages,Model.class);
        for(Class<?> modelClass : modelClasses){
            if(Modifier.isAbstract(modelClass.getModifiers())){
                //抽象类不作处理
                continue;
            }
            BindTable bindtable = modelClass.getAnnotation(BindTable.class);
            if(bindtable==null){
                arPlugin.addMapping(modelClass.getSimpleName(),(Class<Model<?>>)modelClass);
            }else{
                String tableName = bindtable.tableName();
                arPlugin.addMapping(tableName,(Class<Model<?>>)modelClass);
            }
        }
    }
}
