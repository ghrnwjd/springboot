package com.hoyoung.springbootTIL;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

      /*
      All 이 붙은 어노테이션은 static으로 설정해야 함.
       */
      @BeforeAll
      static void beforeAll() {
            System.out.println("## BeforeAll 어노테이션 호출 ##");
      }

      @AfterAll
      static void AfterAll() {
            System.out.println("## AfterAll 어노테이션 호출 ##");
      }

      @BeforeEach
      void beforeEach() {
            System.out.println("## BeforeEach 어노테이션 호출 ##");
      }

      @AfterEach
      void afterEach() {
            System.out.println("## AfterEach 어노테이션 호출 ##");
      }

      @Test
      void test1() {
            System.out.println("## Test 1 시작 ## ");
      }

      @Test
      @DisplayName("Test case 2")
      void test2() {
            System.out.println("## Test 2 시작 ## ");
      }

      @Test
      @Disabled
      void test3() {
            System.out.println("## Test 3 시작 ## ");
      }
}
