package com.example.fluxtest;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {


      private EventNotify eventNotify;
      public MyFilter(EventNotify eventNotify) {
            this.eventNotify = eventNotify;
      }

      @Override
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("필터 실행됨");

            HttpServletResponse servletResponse = (HttpServletResponse) response;

            servletResponse.setContentType("text/event-stream; charset=utf-8");
            PrintWriter out = servletResponse.getWriter();

            for(int i = 0; i < 5; i++) {
                  out.print("응답: " + i + "\n");
                  out.flush(); // 버퍼를 비우는 명령어.
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
            }

            // 5초 이후 한번에 응답. → text/plain 형식이기 때문에 웹브라우저에서 한번에 flush 함.
            /*
            servletResponse.setContentType("text/plain; charset=utf-8");
            PrintWriter out = servletResponse.getWriter();

            for(int i = 0; i < 5; i++) {
                  out.print("응답: " + i + "\n");
                  out.flush(); // 버퍼를 비우는 명령어.
                  try {
                        Thread.sleep(1000);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
            }
            */

            while (true) {
                  try {
                        if(eventNotify.getChange()) {
                              int index = eventNotify.getEvents().size() - 1;
                              out.print("응답 : " + eventNotify.getEvents().get(index) + "\n");
                              out.flush();
                              eventNotify.setChanged(false);
                        }
                        Thread.sleep(1);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
            }
      }
}
