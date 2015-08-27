package com.kerwin.tm.njwjsqbyp.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import com.kerwin.tm.njwjsqbyp.service.Http;
import com.kerwin.tm.njwjsqbyp.service.Io;
import com.kerwin.tm.njwjsqbyp.service.Logger;
import com.kerwin.tm.njwjsqbyp.service.Validator;

@Singleton
@Creatable
public class HttpService implements Http {

	private static final String POST = "POST";
	private int connectTimeout;
	private int readTimeout;

	private Validator validator;
	private Logger logger;
	private Io io;

	@Override
	public String postContent(String url, String content) {
		return postByContent(url, content, readTimeout);
	}

	@Override
	public String postByContent(String url, String content, int readTimeout) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setRequestMethod(POST);
			connection.setDoOutput(true);
			if (!validator.isBlank(content))
				connection.getOutputStream().write(content.getBytes());
			connection.getOutputStream().flush();
			connection.getOutputStream().close();

			OutputStream output = new ByteArrayOutputStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
			io.copy(input, output);
			input.close();
			input = null;
			output.close();

			return output.toString();
		} catch (Exception e) {
			logger.warn(e, "使用POST提交[" + url + "][" + content + "]请求时发生异常！");

			return null;
		}
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void unSetValidator(Validator validator) {
		if (this.validator == validator)
			this.validator = null;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setIo(Io io) {
		this.io = io;
	}

}
