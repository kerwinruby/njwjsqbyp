package com.kerwin.tm.njwjsqbyp.service.impl;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import com.kerwin.tm.njwjsqbyp.service.Validator;

@Singleton
@Creatable
public class ValidatorService implements Validator {
	@Override
	public boolean isBlank(String value) {
		return value == null || value.trim().length() == 0;
	}
}
