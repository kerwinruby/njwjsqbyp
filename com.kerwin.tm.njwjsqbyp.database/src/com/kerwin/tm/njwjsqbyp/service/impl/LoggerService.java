package com.kerwin.tm.njwjsqbyp.service.impl;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import com.kerwin.tm.njwjsqbyp.service.Logger;

@Singleton
@Creatable
public class LoggerService implements Logger {

	private static final String[] THROWABLES = { "< ", " > ", "    " };

	@Override
	public void warn(Throwable throwable, Object... messages) {
		System.err.println(message(throwable, messages));
	}

	protected String message(Throwable throwable, Object[] messages) {
		StringBuilder message = new StringBuilder().append(Thread.currentThread().getStackTrace()[3].getClassName())
				.append(' ');

		if (throwable != null) {
			message.append(THROWABLES[0]).append(throwable.getClass().getName()).append(THROWABLES[1])
					.append(throwable.getMessage()).append('\n');
			for (StackTraceElement stackTrace : throwable.getStackTrace())
				message.append(THROWABLES[2]).append(stackTrace).append('\n');
		}

		if (messages != null && messages.length > 0)
			for (Object msg : messages)
				message.append(msg);

		return message.toString();
	}

}
