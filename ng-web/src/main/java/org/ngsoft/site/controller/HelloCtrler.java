package org.ngsoft.site.controller;

import com.jfinal.core.Controller;
import org.ngsoft.site.model.User;
import org.ngsoft.template.freemarker.methods.IndexOfMethod;

/**
 * Created by will on 2015-3-3.
 */
public class HelloCtrler extends Controller {

    public void index(){
        renderHtml("<h2>hello~</h2>");
    }

    public void login(){
        //放入model
        User user = new User();
        user.set("id",1000L);
        user.set("username","will");
        setAttr("user", user);
        //放入MethodModel
        IndexOfMethod method = new IndexOfMethod();
        setAttr("indexOf",method);
        render("login.html");
    }

}
