package org.ngsoft.core.handler;

import org.ngsoft.core.message.IMessage;

/**
 * Created by will on 2015-3-18.
 */
public interface IServerMessageHandler {

    void onMessageReceive(IMessage message);
}
