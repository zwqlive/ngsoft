package org.ngsoft.core.auth;

import java.lang.annotation.*;

/**
 * Created by will on 2015-3-10.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Repeatable(value=Powers.class)
public @interface Power {

    PowerEnum value();

    Class<? extends IPowerValidate> checkClass() default DefaultPowerValidator.class;

}
