package org.ngsoft.core.service;

import java.util.HashMap;
import java.util.Map;

import org.ngsoft.core.common.Loggers;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.netty.handler.IMessageHandler;
import org.slf4j.Logger;

/**
 * 消息注册服务
 * 
 * @author will
 *
 */
public class MessageRegistryService {
	
	private static Logger logger = Loggers.MSG_LOGGER;
	private static Map<Integer,Class<? extends IMessage>> msgClasses = new HashMap<Integer, Class<? extends IMessage>>();
	private static Map<Class<? extends IMessage>, Class<? extends IMessageHandler<? extends IMessage>>> msgAndHandlerClasses = new HashMap<Class<? extends IMessage>, Class<? extends IMessageHandler<? extends IMessage>>>();
	private static Map<Class<? extends IMessage>, IMessageHandler<? extends IMessage>> handlers = new HashMap<Class<? extends IMessage>, IMessageHandler<? extends IMessage>>();


    /**
     * 注册消息处理器
     *
     * @param msg
     * @param handlerClass
     * @param <M>
     * @param <H>
     */
	public static <M extends IMessage, H extends IMessageHandler<M>> void register(
			M msg, Class<H> handlerClass) {
        //TODO: 检测msgId是否有冲突！
		Class<? extends IMessage> msgClass = msg.getClass();
		msgAndHandlerClasses.put(msgClass, handlerClass);
		msgClasses.put(msg.getId(), msgClass);
		try {
			handlers.put(msgClass, handlerClass.newInstance());
		} catch (InstantiationException e) {
			logger.error("initial handler faild! ",e);
		} catch (IllegalAccessException e) {
			logger.error("initial handler faild! ",e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <M extends IMessage> M getMessage(int msgId) {
		try {
			Class<? extends IMessage> msgClass = msgClasses.get(msgId);
			if(msgClass==null){
				return null;
			}
			return (M) msgClass.newInstance();
		} catch (InstantiationException e) {
			logger.error("initial message faild! ",e);
		} catch (IllegalAccessException e) {
			logger.error("initial message faild! ",e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <M extends IMessage, H extends IMessageHandler<M>> H getHandler(
			Class<M> msgClass) {
		if(handlers.containsKey(msgClass)){
			return (H) handlers.get(msgClass);
		}
		if(!msgAndHandlerClasses.containsKey(msgClass)){
			logger.error(msgClass.getName()+"未注册处理器。 ");
			return null;
		}
		try {
			IMessageHandler<? extends IMessage> handler = msgAndHandlerClasses.get(msgClass).newInstance();
			handlers.put(msgClass, handler);
			return (H) handler;
		} catch (InstantiationException e) {
			logger.error("initial handler faild! ",e);
		} catch (IllegalAccessException e) {
			logger.error("initial handler faild! ",e);
		}
		return null;
	}
	
	
}
