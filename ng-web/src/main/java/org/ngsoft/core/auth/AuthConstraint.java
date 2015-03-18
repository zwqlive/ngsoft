package org.ngsoft.core.auth;

/**
 * Created by will on 2015-3-11.
 */

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface AuthConstraint {
    Class<? extends IPowerValidate> value();
}
