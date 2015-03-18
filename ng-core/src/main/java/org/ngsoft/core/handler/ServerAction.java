package org.ngsoft.core.handler;

import org.ngsoft.core.action.IAction;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.service.MessageRegistryService;

/**
 * Created by will on 2015-3-18.
 */
public class ServerAction implements IAction {

    public ServerAction(MessageHandler<IMessage> handler1){
        if(handler1==null){
            throw new NullPointerException("handler can not be null!");
        }
        this.handler = handler1;
    }

    public ServerAction(IMessage message){
        if(message==null){
            throw new NullPointerException("message can not be null!");
        }
        handler = MessageRegistryService.getHandler(message.getClass());
        if(handler==null){
            throw new NullPointerException("can not find the handler. message="+message);
        }
    }

    private MessageHandler<IMessage> handler;

    private IMessage message;

    @Override
    public void action() {
        handler.doHandle(message);
    }
}
