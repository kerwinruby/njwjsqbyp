package com.kerwin.tm.njwjsqbyp.service;

public interface Validator {

	/**
	 * �ж��ַ����Ƿ�Ϊ�ա��ַ���Ϊnull��հ׷�����Ϊ�ա�
	 * 
	 * @param string
	 *            Ҫ�����ж��ַ�����
	 * @return ���Ϊ���򷵻�true�����򷵻�false��
	 */
	boolean isBlank(String value);

}
