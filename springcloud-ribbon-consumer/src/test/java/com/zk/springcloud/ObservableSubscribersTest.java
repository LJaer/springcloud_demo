package com.zk.springcloud;


import rx.Observable;
import rx.Subscriber;

public class ObservableSubscribersTest {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");
                subscriber.onNext("I am dd");
                subscriber.onCompleted();
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber : " + s);
            }
        };
        observable.subscribe(subscriber);
    }
}
