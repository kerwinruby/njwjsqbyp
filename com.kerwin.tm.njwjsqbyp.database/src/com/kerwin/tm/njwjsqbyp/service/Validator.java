package com.kerwin.tm.njwjsqbyp.service;

public interface Validator {

	/**
	 * 判断字符串是否为空。字符串为null或空白符均视为空。
	 * 
	 * @param string
	 *            要进行判断字符串。
	 * @return 如果为空则返回true；否则返回false。
	 */
	boolean isBlank(String value);

}
