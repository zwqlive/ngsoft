package org.ngsoft.config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import org.ngsoft.core.automapping.AutoMapTable;
import org.ngsoft.site.controller.HelloCtrler;
import org.ngsoft.site.controller.UserController;

/**
 * 项目配置
 * Created by will on 2015-3-3.
 */
public class AppConfig extends JFinalConfig{

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setViewType(ViewType.FREE_MARKER);
        constants.setFreeMarkerTemplateUpdateDelay(0);
        constants.setError404View("/404.html");
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/",HelloCtrler.class);
        routes.add("/hello", HelloCtrler.class,"/");
        routes.add("/user", UserController.class,"/");
    }

    @Override
    public void configPlugin(Plugins plugins) {
//        C3p0Plugin postgresDb = new C3p0Plugin("jdbc:postgresql://127.0.0.1:5432/triver","postgres","root","org.postgresql.Driver");
//        plugins.add(postgresDb);

        DruidPlugin druidPlugin = new DruidPlugin("jdbc:postgresql://127.0.0.1:5432/triver",
                "postgres","root","org.postgresql.Driver");

        plugins.add(druidPlugin);

        ActiveRecordPlugin arPlugin = new ActiveRecordPlugin("postgresql",druidPlugin);
        arPlugin.setCache(new EhCache());
        arPlugin.setDialect(new PostgreSqlDialect());
        plugins.add(arPlugin);
        //自动注册映射关系
        String[] modelPackages = new String[]{
                "triver.site.model"
        };
        AutoMapTable.mapTable(arPlugin, modelPackages);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
