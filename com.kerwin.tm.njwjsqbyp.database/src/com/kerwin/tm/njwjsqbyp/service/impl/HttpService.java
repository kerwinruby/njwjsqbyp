package com.kerwin.tm.njwjsqbyp.service.impl;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import com.kerwin.tm.njwjsqbyp.service.Http;
import com.kerwin.tm.njwjsqbyp.service.Validator;

@Singleton
@Creatable
public class HttpService implements Http {

	private Validator validator;

	public String postContent(String url, String content) {
		if (validator == null) {
			System.out.println("not inject");
		} else {
			System.out.println("inject ok");
		}
		System.out.println("url : " + url + " content : " + content);
		return "";
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
}
