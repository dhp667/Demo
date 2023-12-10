package com.example.demo.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(* com.example.demo.test.*.* (..))")
    private void pointCutMethod() {
    }


    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("前置环绕");
        try {
            Object o = point.proceed();
            System.out.println("后置环绕");
            return o;

        } /*catch(InvocationTargetException e) {
            throw e.getTargetException();
        }*/ catch (Exception e) {
            throw e;
        }
    }

    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置");
    }

    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("后置");
    }

    @AfterReturning("pointCutMethod()")
    public void doAfterReturn() {
        System.out.println("返回");
    }
}
