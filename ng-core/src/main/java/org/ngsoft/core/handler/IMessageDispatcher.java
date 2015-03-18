package org.ngsoft.core.handler;

import org.ngsoft.core.action.IAction;

/**
 * Created by will on 2015-3-18.
 * 消息分发器，具体负责将消息分发到具体的线程执行
 */
public interface IMessageDispatcher {

    boolean dispatch(IAction action);
}
