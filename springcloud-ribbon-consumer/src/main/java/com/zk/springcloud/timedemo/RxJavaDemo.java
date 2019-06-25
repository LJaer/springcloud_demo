package com.zk.springcloud.timedemo;

import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * Reactive 实现 timeout
 */
public class RxJavaDemo {

    public static void main(String[] args) {
        Random random = new Random();
        Single.just("Hello World") // just 发布数据
                .subscribeOn(Schedulers.immediate()) //订阅的线程池 immediate = Thread.currentThread()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() { //正常结束流程
                        System.out.println("执行结束！");
                    }

                    @Override
                    public void onError(Throwable e) { //异常流程（结束）
                        System.out.println("熔断保护！");
                    }

                    @Override
                    public void onNext(String s) { // 数据消费 s = "Hello World"
                        int value = random.nextInt(200);
                        if(value > 100){
                            throw new RuntimeException("TimeOut!");
                        }

                        System.out.println("HelloWorld() costs " + value + " ms.");
                    }
                });
    }
}
