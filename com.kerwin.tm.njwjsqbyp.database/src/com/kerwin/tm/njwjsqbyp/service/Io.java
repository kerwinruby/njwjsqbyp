package com.kerwin.tm.njwjsqbyp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Io {

	void copy(InputStream input, OutputStream output) throws IOException;

	void copy(BufferedReader input, OutputStream output) throws IOException;
}
