package com.ghrnwjd.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;


// 구독 정보(구독자가 누구인지, 어떤 데이터를 구독할지)
public class MySubscription implements Subscription {

      private Subscriber s;
      private Iterator<Integer> it;

      public MySubscription(Subscriber<? super Integer> subscriber, Iterable<Integer> iterable) {
            this.s = subscriber;
            this.it = iterable.iterator();
      }

      @Override
      public void request(long l) {
            while ( l --> 0) {
                  if(it.hasNext()) {
                        s.onNext(it.next());
                  }
                  else {
                        s.onComplete(); // 더 이상 줄 데이터는 없어
                        break;
                  }
            }
      }

      @Override
      public void cancel() {

      }
}
