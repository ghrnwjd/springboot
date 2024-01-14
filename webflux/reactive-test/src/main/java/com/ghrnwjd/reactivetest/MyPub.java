package com.ghrnwjd.reactivetest;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class MyPub implements Publisher<Integer> {

      //퍼블리셔는 어떤 데이터를 들고있는지
      Iterable<Integer> iterable = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

      @Override
      public void subscribe(Subscriber<? super Integer> subscriber) {
            System.out.println("구독자 : 신문사야 나 너희 신문 볼게");
            System.out.println("출판자 : 알겠어 구독 정보 만들어서 줄테니 기다려");

            MySubscription subscription = new MySubscription(subscriber, iterable);
            System.out.println("출판자 : 구독 정보 생성 완료");

            subscriber.onSubscribe(subscription);
      }
}
