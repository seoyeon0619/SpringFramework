package com.kosa.myapp3.common;

public class StringUtil {
	// ������ private�� ���� ��ü �������ϰ�
	private StringUtil() {	}
	
	public static String nullToValue(Object obj, String value) {
		// ���޵� ��ü�� null�� ��� �� ��° ���ڰ� ������ ���� ��ȯ
		if(obj==null)
			return value;
		else 
			// null�� �ƴ� ��� obj�� toString�� ����
			return obj.toString();
		
		// String ��ü���� ��밡��
		// ��ü �ȸ���� �Լ� ����� �����ϰ� �ϱ����� static Ű���� ���
	}
}
