package com.kerwin.tm.njwjsqbyp.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import com.kerwin.tm.njwjsqbyp.service.Io;

@Singleton
@Creatable
public class IoService implements Io {
	private static final int BUFFER_SIZE = 1024;

	@Override
	public void copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		for (int length = -1; (length = input.read(buffer)) > -1;)
			output.write(buffer, 0, length);
		output.flush();
	}

	@Override
	public void copy(BufferedReader input, OutputStream output) throws IOException {
		String line = "";
		StringBuilder builder = new StringBuilder();
		while (null != (line = input.readLine())) {
			builder.append(line);
		}
		output.write(builder.toString().getBytes());
	}

}
