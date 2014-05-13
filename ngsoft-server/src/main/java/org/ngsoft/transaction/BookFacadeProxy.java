package org.ngsoft.transaction;

import java.lang.reflect.Method;
import java.sql.Connection;

import org.ngsoft.db.ConnectionFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookFacadeProxy implements MethodInterceptor {

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> clazz) {

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}

	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		Object result = null;
		Connection conn = ConnectionFactory.createConnection();
		try {
			conn.setAutoCommit(false);
			result = arg3.invokeSuper(arg0, arg2);
			conn.commit();
			System.out.println("事物结束");
		} catch (Throwable t) {
			conn.rollback();
			conn.setAutoCommit(true);
			throw new Exception(t);
		}
		return result;
	}

	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		// Enhancer enhancer = new Enhancer();
		// enhancer.setSuperclass(BookFacadeImpl.class);
		// enhancer.setCallback(proxy);
		// BookFacadeImpl bookFacade = (BookFacadeImpl)enhancer.create();

		BookFacadeImpl bookFacade = (BookFacadeImpl) proxy
				.getInstance(BookFacadeImpl.class);
		bookFacade.addBook();

	}
}
