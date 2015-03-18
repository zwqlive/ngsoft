package org.ngsoft.core.auth;

/**
 * Created by will on 2015-3-11.
 */
public interface IPowerValidate {

    /**
     * 检测权限
     * @param roleId
     * @return
     */
    boolean checkPower(long roleId);

    AuthInfo getAuthInfo(AuthToken token);
}
