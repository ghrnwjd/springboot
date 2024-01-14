package com.ghrnwjd.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {

      private  Subscription s;
      private int bufferSize = 2;

      @Override
      public void onSubscribe(Subscription subscription) {
            System.out.println("구독자 : 구독 정보 잘 받았음");
            this.s = subscription;

            System.out.println("구독자 : 나 신문 " + bufferSize + " 개씩 받을게");
            s.request(bufferSize);
      }

      @Override
      public void onNext(Integer integer) {
            System.out.println("onNext() : " + integer);
            bufferSize--;
            if(bufferSize == 0) {
                  System.out.println("하루 지남");
                  bufferSize = 2;
                  s.request(2);
            }

      }

      @Override
      public void onError(Throwable throwable) {
            System.out.println("구독 중 에러");
      }

      @Override
      public void onComplete() {
            System.out.println("구독완료");
      }
}
