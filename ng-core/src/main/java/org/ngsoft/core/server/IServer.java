package org.ngsoft.core.server;

import org.ngsoft.core.message.IMessageResolver;

/**
 * Created by will on 2015-3-16.
 */
public interface IServer extends IMessageResolver {

    void init();

    void start();

    void stop();
}
