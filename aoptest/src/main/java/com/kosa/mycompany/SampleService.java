package com.kosa.mycompany;

public interface SampleService {
	public void displayName();
	public void displayNumber();
	// �߿����
		// AOP���� ����� �޼���� ��ȯ���� Object���� ��
	public Object displayNumber(int limit);
	public void guguDan(int dan);
	
}
