package org.ngsoft.core.service;

import java.util.HashMap;
import java.util.Map;

import org.ngsoft.core.common.Loggers;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.message.Message;
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
	
	private static Map<Integer,Class<? extends Message>> messageClasses = new HashMap<Integer, Class<? extends Message>>();
	private static Map<Integer,Class<? extends IMessageHandler<? extends IMessage>>> handlerClasses = new HashMap<Integer, Class<? extends IMessageHandler<? extends IMessage>>>();
	private static Map<Integer,IMessageHandler<? extends IMessage>> handlers = new HashMap<Integer, IMessageHandler<? extends IMessage>>();
	/**
	 * 注册消息处理器
	 * @param messageId
	 * @param msgClass
	 * @param handlerClass
	 */
	public static void register(int messageId,Class<? extends Message> msgClass, Class<? extends IMessageHandler<? extends IMessage>>  handlerClass){
		messageClasses.put(messageId, msgClass);
		handlerClasses.put(messageId, handlerClass);
		try {
			handlers.put(messageId, handlerClass.newInstance());
		} catch (InstantiationException e) {
			logger.error("initial handler faild! ",e);
		} catch (IllegalAccessException e) {
			logger.error("initial handler faild! ",e);
		}
	}
	
	public static Message getMessage(int msgId){
		if(messageClasses.containsKey(msgId)){
			try {
				return messageClasses.get(msgId).newInstance();
			} catch (InstantiationException e) {
				logger.error("initial message faild! ",e);
			} catch (IllegalAccessException e) {
				logger.error("initial message faild! ",e);
			}
			return null;
		}else{
			return null;
		}
	}
	
	public static IMessageHandler<? extends IMessage> getHandler(int msgId){
		if(handlers.containsKey(msgId)){
			return handlers.get(msgId);
		}
		if(handlerClasses.containsKey(msgId)){
			Class<? extends IMessageHandler<? extends IMessage>> handlerClass = handlerClasses.get(msgId);
			try {
				handlers.put(msgId, handlerClass.newInstance());
			} catch (InstantiationException e) {
				logger.error("initial handler faild! ",e);
			} catch (IllegalAccessException e) {
				logger.error("initial handler faild! ",e);
			}
			return handlers.get(msgId);
		}else{
			return null;
		}
	}
}
