package com.hoyoung.springbootTIL.config;

import com.hoyoung.springbootTIL.data.dto.CommonResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
      private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);
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

                        // 서비스 : 정상적인 화면 -> 사용자 요청
                        // html에서 요청한다면 에러가 발생하는 경우가 적음. 프론트에서 제한조건을 설정하면 되기 때문
                        //  Postman이나 HTTPURLConnection을 통해서 할때 에러가 주로 발생함
                        if(bindingResult.hasErrors()) {
                              Map<String, String> errorMap = new HashMap<>();
                              for(FieldError error : bindingResult.getFieldErrors()) {
                                    errorMap.put(error.getField(), error.getDefaultMessage());
                                    // .info .error .debug .warn 등등
                                    // 로그 레벨 error -> warn -> info -> debug
                                    // 로그 레벨 설정을 info로 하면 error, warn, info 만 뜸. 레벨이 높은것만 뜸.
                                    log.warn(type+"."+method+"() => 필드: "+ error.getField() +", 메시지: " + error.getDefaultMessage());

                                    // 로그를 파일로 남기기
                                    // 1. DB 연결해서 DB 남기기
                                    // 2. File file = new File(); -> 파일이 엄청 커질 수 있음.
                              }

                              return new CommonResponseDto<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMap);
                        }
                  }
            }

            return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라.
      }



      @Before("execution(* com.hoyoung.springbootTIL.controller..*Controller.*(..))")
      // Before 어노테이션에는 ProceedingJoinPoint를 사용하지 못함. 컴파일 에러 발생
      // RequestContextHolder를 사용할 수 있음
      // return은 의미가 없음.
      public void testBeforeCheck() {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            System.out.println("주소: " + request.getRequestURI());
            System.out.println("전처리 로그를 남기겠습니다.");
      }

      @After("execution(* com.hoyoung.springbootTIL.controller..*Controller.*(..))")
      public void testAfterCheck() {
            System.out.println("후처리 로그를 남기겠습니다.");
      }

}
