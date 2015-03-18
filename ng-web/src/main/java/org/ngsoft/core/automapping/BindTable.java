package org.ngsoft.core.automapping;

import java.lang.annotation.*;

/**
 * Created by will on 2015-3-9.
 * 映射数据库表
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindTable {
    /**
     * 表名
     * @return
     */
    String tableName();

    /**
     * 主键
     * @return
     */
    String pk() default "id";
}
