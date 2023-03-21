package com.kosa.mycompany;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 자동으로 객체를 만들어야 함
// Aspect 설정
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
		
		System.out.println("클래스명 : " + classname);
		System.out.println("함수명 : " + methodname);
		
		long time1 = System.currentTimeMillis(); 
		Object retVal = joinPoint.proceed();
		long time2 = System.currentTimeMillis(); 
		
		System.out.println("실행시간 : " + (time2 - time1)+"밀리초");
		System.out.println("arround end*********************");
		
		return retVal; 		
	}
	
}
