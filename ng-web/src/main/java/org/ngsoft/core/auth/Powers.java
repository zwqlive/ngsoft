package org.ngsoft.core.auth;

/**
 * Created by will on 2015-3-18.
 */

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Powers {
    Power[] value();
}
