package com.hoyoung.springbootTIL.config;

import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/*
      공통기능을 Aspect라고 하고 Advice는 Aspect의 기능 자체
      Aspect나 Advice나 비슷한 말임
      JoinPoint는 핵심 기능
      PointCut은 조인포인트의 앞이 될지 뒤가 될지 둘다 넣을지 정하면 됨
      Aspect는 공통기능
      */

// 메모리에 띄우는 방법 @Controller, @RestController, @Component, @Configuration 등등
// 함수를 찾아야지 동작하도록 하고 싶음. 즉 {@Controller, @RestController}가 메모리에 뜬 뛰에 떠도 상관없음.
// Component나 Configuration 으로 띄우면 됨
// Component는 Controller 이후에 뜸

// @Configuration -> {@Controller, @Service, @Repository} -> @Component
@Component
@Aspect
public class BindingAdvice {

      // PointCut을 어디로 설정할지
      // 앞 뒤 : 다 관리  @Around
      // 앞 :  제데로 입력안되면 내가 입력 @Before
      // 뒤 : 응답만 관리 @After

      @Around("execution(* com.hoyoung.springbootTIL.controller..*Controller.*(..))")
      // .. 모든파일 안에 *Controller.메서드명
      // Around 정규 표현식은 필요할 때 찾아서 검색
      // Object만 리턴할 수 있음.
      public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
            String method = proceedingJoinPoint.getSignature().getName();

            System.out.println("type: "+type);
            System.out.println("method: " +method);

            Object [] args = proceedingJoinPoint.getArgs();
            for(Object arg : args) {
                  if(arg instanceof BindingResult) {
                        BindingResult bindingResult = (BindingResult) arg;
                        if(bindingResult.hasErrors()) {
                              Map<String, String> errorMap = new HashMap<>();
                              for(FieldError error : bindingResult.getFieldErrors()) {
                                    errorMap.put(error.getField(), error.getDefaultMessage());
                              }

                              return new CommonResponseDto<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                      errorMap);
                        }
                  }
            }

            return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라.
      }



      @Before("execution(* com.hoyoung.springbootTIL.controller..*Controller.*(..))")
      // Before 어노테이션에는 ProceedingJoinPoint를 사용하지 못함. 컴파일 에러 발생
      // return은 의미가 없음.
      public void testBeforeCheck() {
            System.out.println("로그를 남기겠습니다.");
      }
}
