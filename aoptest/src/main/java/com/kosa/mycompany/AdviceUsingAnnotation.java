package com.kosa.mycompany;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// �ڵ����� ��ü�� ������ ��
// Aspect ����
@Component
@Aspect
public class AdviceUsingAnnotation {

	@Pointcut("execution(public * com.kosa.mycompany.*ServiceImpl.*(..))")
	public void publicTarget(){
	}
	
	@Around("publicTarget()")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint)throws Throwable {
		System.out.println("arround start*********************");
		
		String classname = joinPoint.getTarget().getClass().getSimpleName();
		String methodname = joinPoint.getSignature().getName();
		
		System.out.println("Ŭ������ : " + classname);
		System.out.println("�Լ��� : " + methodname);
		
		long time1 = System.currentTimeMillis(); 
		Object retVal = joinPoint.proceed();
		long time2 = System.currentTimeMillis(); 
		
		System.out.println("����ð� : " + (time2 - time1)+"�и���");
		System.out.println("arround end*********************");
		
		return retVal; 		
	}
	
}
