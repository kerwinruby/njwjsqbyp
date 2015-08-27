package com.kerwin.tm.njwjsqbyp.service;

public interface Http {

	/**
	 * 
	 * @param url
	 * @param content
	 * @param readTimeout
	 * @return
	 */
	String postByContent(String url, String content, int readTimeout);

	/**
	 * 
	 * @param url
	 * @param content
	 * @return
	 */
	String postContent(String url, String content);

}
