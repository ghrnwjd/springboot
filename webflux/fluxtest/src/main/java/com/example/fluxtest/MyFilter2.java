package com.example.fluxtest;

import jakarta.servlet.*;

import java.io.IOException;

public class MyFilter2 implements Filter {
      private EventNotify eventNotify;
      public MyFilter2(EventNotify eventNotify) {
            this.eventNotify = eventNotify;
      }
      @Override
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("필터2 실행됨");

            // 데이터를 하나 발생시켜 sse 밑에 반영시킬 수 있도록 함.
            eventNotify.add("새로운 데이터");
      }
}
