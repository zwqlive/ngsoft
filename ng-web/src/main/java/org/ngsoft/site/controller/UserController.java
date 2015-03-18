package org.ngsoft.site.controller;

import com.jfinal.core.Controller;
import org.apache.log4j.Logger;
import org.ngsoft.core.auth.Power;
import org.ngsoft.core.auth.PowerEnum;
import org.ngsoft.site.model.User;
import org.ngsoft.util.IdGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2015-3-6.
 */
public class UserController extends Controller {

    private static Logger log = Logger.getLogger(UserController.class);

    public void index(){
        render("site/user.ftl");
    }

    /**
     * 添加用户
     */
    @Power(PowerEnum.user_add)
    @Power(PowerEnum.user_del)
    public void addUser() {
        User user = new User();
        user.set("id", IdGenerator.getId());
        setUserArr(user,
                new String[]{"username", "showname", "password"},
                new String[]{"username", "showname", "password"});
        user.save();
        Map<String,Object> json=new HashMap<String,Object>();
        boolean success=true;
        short statusCode=200;
        json.put("success",success);
        json.put("status",success?200:(statusCode==0?401:statusCode));
        json.put("msg","更新成功！");
        renderJson(json);
        log.error("addUser success!");
    }

    private User setUserArr(User user, String[] colNames, String[] paraNames) {
        if (colNames.length != paraNames.length) {
            throw new IllegalArgumentException("column length not match with param length");
        }
        for (int i = 0; i < colNames.length; i++) {
            user.set(colNames[i], getPara(paraNames[i]));
        }
        return user;
    }
}
