package com.kosa.mycompany;

import org.aspectj.lang.ProceedingJoinPoint;

// ��Ӿȹް� POJO(Plain Old and Java Object)
public class AdviceUsingXML {
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint)throws Throwable {
		// ProceedingJoinPoint : �Ű����� Ÿ��
		System.out.println("arround start");
		
		// �Լ��� ��ġ�ؿ�. JoinPoint�� �Լ��� ���� ��� ������ ����
		// proceed �Լ��� �̿��� ������ �Լ� ȣ��
		String classname = joinPoint.getTarget().getClass().getSimpleName();
		String methodname = joinPoint.getSignature().getName();
		
		System.out.println("Ŭ������ : " + classname);
		System.out.println("�Լ��� : " + methodname);
		
		long time1 = System.currentTimeMillis(); // ���۽ð��� ������
		Object retVal = joinPoint.proceed(); // ������ �Լ� ȣ��
		long time2 = System.currentTimeMillis(); // ����ð��� ������
		
		System.out.println("����ð� : " + (time2 - time1)+"�и���");
		System.out.println("arround end");
		
		return retVal; // �Լ��� ��ȯ���� ����
		
	}
}
