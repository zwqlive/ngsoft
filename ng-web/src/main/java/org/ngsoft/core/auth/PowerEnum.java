package org.ngsoft.core.auth;

/**
 * Created by will on 2015-3-11.
 */
public enum PowerEnum {
    user_add(1,"添加用户"),
    user_edit(2,"编辑用户"),
    user_del(3,"删除用户"),
    ;
    private int id;
    private String desc;
    private PowerEnum(int pid, String pdesc){
        this.id=pid;
        this.desc=pdesc;
    }

    public boolean check(PowerEnum power){
        return false;
    }
}
