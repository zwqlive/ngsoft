package org.ngsoft.core.auth;

/**
 * Created by will on 2015-3-11.
 */
public class DefaultPowerValidator implements IPowerValidate{

    @Override
    public boolean checkPower(long roleId) {
        return false;
    }

    @Override
    public AuthInfo getAuthInfo(AuthToken token) {
        return null;
    }
}
