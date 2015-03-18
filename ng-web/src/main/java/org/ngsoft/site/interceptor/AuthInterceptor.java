package org.ngsoft.site.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ngsoft.core.auth.AuthConstraint;
import org.ngsoft.core.auth.IPowerValidate;
import org.ngsoft.core.auth.Power;

/**
 * Created by will on 2015-3-10.
 */
public class AuthInterceptor implements Interceptor {

    private static Logger log = LogManager.getLogger(AuthInterceptor.class);

    @Override
    public void intercept(ActionInvocation ai) {
        Power power = (Power)ai.getController().getClass().getAnnotation(Power.class);
        if(power==null){
            power=(Power)ai.getMethod().getAnnotation(Power.class);
        }
        if(power==null){
            ai.invoke();
        }else{
            if(power.checkClass()==null){
                ai.invoke();
            }else{
                try {
                    IPowerValidate validator = power.checkClass().newInstance();
//                    validator.checkPower();
                } catch (InstantiationException e) {
                    log.error(e);
                } catch (IllegalAccessException e) {
                    log.error(e);
                }
            }

        }
    }
}
