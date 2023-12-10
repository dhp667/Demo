package com.example.demo.test.react;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FluxTest {
    private static final String COMPLETE = "complete";

    public static void main(String[] args) throws Exception {
        testCold();
    }

    // 映射
    static void test_map() throws InterruptedException {
        FluxSink<Integer>[] sinks = new FluxSink[1];
        Flux<Integer> flux = Flux.create(sink -> sinks[0] = sink);
        flux.map(i -> i * 2).subscribe(System.out::println);
        sinks[0].next(1);
        flux.index().map(tuple -> Tuples.of(tuple.getT1(), tuple.getT2() * 2)).subscribe(System.out::println);
        sinks[0].next(2);
        flux.timestamp().map(tuple -> Tuples.of(tuple.getT1(), tuple.getT2() * 2)).subscribe(System.out::println);
        sinks[0].next(3);
    }

    // 过滤
    static void test_filter() throws InterruptedException {
//        DirectProcessor<Integer> processor = DirectProcessor.create();
//        FluxSink<Integer> sink = processor.sink();
//        Flux<Integer> flux = processor.onBackpressureBuffer();
        Flux.interval(Duration.ofMillis(500))
                .map(item -> "flux receive " + item)
                .doOnNext(System.out::print)
                .skipUntilOther(Mono.just("start").delayElement(Duration.ofSeconds(4)))
                .takeUntilOther(Mono.just("end").delayElement(Duration.ofSeconds(6)))
                .subscribe(item -> System.out.print(System.lineSeparator() + "curr flux: " + item + System.lineSeparator()));
        Thread.sleep(10 * 1000);


    }

    static void test_multiSubscribe() throws InterruptedException {
        FluxSink<Integer>[] sinks = new FluxSink[1];
        Flux<Integer> flux = Flux.create(sink -> sinks[0] = sink);
        System.out.println("filter: 只接收predicate为true的元素");
        flux.filter(i -> i % 2 == 0).subscribe(printSub());
        sinks[0].next(1).next(2).next(3).next(4);
        printComplete();
        System.out.println("take: 只接收n个元素, 后面元素忽略");
        flux.take(3).subscribe(printSub());
        sinks[0].next(1).next(2).next(3).next(4);
        printComplete();
        System.out.println("takeWhile: 有元素不符合就终止");
        flux.takeWhile(i -> i == 3).subscribe(printSub());
        sinks[0].next(3).next(2).next(3).next(4);
        printComplete();
        System.out.println("takeUntil: 有元素符合就终止");
        flux.takeUntil(i -> i == 3).subscribe(printSub());
        sinks[0].next(1).next(2).next(3).next(4);
        printComplete();
    }

    private static void testCollect() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        // collect: 类似stream中的collect操作. 当flux complete时会将订阅的元素进行collect操作
        flux.collect(Collectors.toList()).subscribe(System.out::println);
        // collect: 当flux complete时会将订阅的元素进行collect操作
        flux.collect(Collectors.toMap(Function.identity(), i -> IntStream.rangeClosed(0, i).boxed().collect(Collectors.toList()))).subscribe(System.out::println);
        // collectMap: 等于collect和collector.toMap的结合简化写法
        flux.collectMap(Function.identity(), i -> IntStream.rangeClosed(0, i).boxed().collect(Collectors.toList())).subscribe(System.out::println);
        // 相当于在collectMap中, 用自己的map替换默认生成的map
        flux.collectMultimap(Function.identity(),
                i -> IntStream.rangeClosed(0, i).boxed().collect(Collectors.toList()),
                () -> {
                    Map map = new HashMap<Integer, List<Integer>>();
                    map.put(6, Collections.singletonList(6));
                    map.put(7, Collections.singletonList(7));
                    map.put(8, Collections.singletonList(8));
                    return map;
                }).subscribe(System.out::println);
    }

    private static void testDistinct() {
        Flux<Integer> flux = Flux.just(1, 2, 2, 3, 4, 5, 2, 4, 6, 7);
        flux.distinct().subscribe(FluxTest::printNext);
        System.out.println();
        flux.distinctUntilChanged().subscribe(FluxTest::printNext);

    }

    private static void testCheck() {
        Flux.just(1, 2, 3).any(item -> item % 2 == 0).subscribe(System.out::println);
        Flux.just(1, 3).any(item -> item % 2 == 0).subscribe(System.out::println);
        // 检查是否包含至少1个元素, 短路逻辑
        Flux.just(1).hasElements().subscribe(System.out::println);
        // 检查是否包含某个元素
        Flux.just(1, 3).hasElement(3).subscribe(System.out::println);

    }

    private static void testReduce() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.reduce(-1, (o1, o2) -> {
            System.out.printf("o1: %s, o2: %s, o1+o2: %s\n", o1, o2, o1 + o2);
            return o1 + o2;
        }).subscribe(System.out::println);
        // 类似reduce, 但是能够将中间结果传递给下游
        Flux.just(1, 2, 3);
        flux.scan(-1, (o1, o2) -> {
            System.out.printf("o1: %s, o2: %s, o1+o2: %s\n", o1, o2, o1 + o2);
            return o1 + o2;
        }).subscribe(System.out::println);
    }

    private static void testCombine() throws InterruptedException {
        // concat 会按照顺序消费并发射元素到新流中
//        Flux.concat(
//                Flux.range(10,5).delayElements(Duration.ofMillis(100)).doOnSubscribe(subscription -> System.out.println("订阅第一个流")),
//                Flux.range(100,5).delayElements(Duration.ofMillis(10)).doOnSubscribe(subscription -> System.out.println("订阅第二个流"))
//        ).subscribe(FluxTest::printNext);
//        Thread.sleep(10*1000);
//        Flux.merge(
//                Flux.range(10,5).delayElements(Duration.ofMillis(100)).doOnSubscribe(subscription -> System.out.println("订阅第一个流")),
//                Flux.range(100,5).delayElements(Duration.ofMillis(10)).doOnSubscribe(subscription -> System.out.println("订阅第二个流"))
//        ).subscribe(FluxTest::printNext);
//        Thread.sleep(10*1000);
//        Flux<String> flux1 = Flux.just("a", "b", "c");
//        Flux<Integer> flux2 = Flux.just(1, 2);
//        Flux<String> flux3 = Flux.just("x", "y", "z");
//        // 将多个流的元素组合成一个tuple
//        Flux.zip(flux1, flux2, flux3).subscribe(FluxTest::printNext);
//        System.out.println();
//        // 两个流可以提供一个组合函数
//        Flux.zip(flux1, flux2, (s, i) -> s + i).subscribe(FluxTest::printNext);
//        System.out.println();
//        // 超过两个流只能通过map将tuple的结果进行映射
//        Flux.zip(flux1, flux2, flux3)
//                .map(tuple -> tuple.getT1() + tuple.getT2() + tuple.getT3()).subscribe(FluxTest::printNext);
//        // 将多个流的元素组合成一个tuple
//        Flux.zip(flux1, flux2, flux3).subscribe(FluxTest::printNext);
//        System.out.println();
        // 与zip的区别在于, 只要有一个流发射了新元素, 就会触发一次组合, 此时会把所有流的最新发射的一个元素进行组合, 所以会产生更多个结果元素
        Flux<String> flux1 = Flux.just("a", "b", "c", "d").delayElements(Duration.ofMillis(50));
        Flux<Integer> flux2 = Flux.just(1, 2, 3).delayElements(Duration.ofMillis(120));
        Flux.combineLatest(flux1, flux2, (s, i) -> s + i).subscribe(FluxTest::printNext);
        Thread.sleep(10000);
    }

    private static void testBatch() throws InterruptedException {
        // buffer等一个集合收集完毕以后才会发射, 发射的是一个集合, 可以看到下游收到的是一个个集合
//        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofMillis(1000));
//        flux.buffer(2).subscribe(collect -> {
//            System.out.println("接收到了事件");
//            System.out.println(collect);
//        });
        // window根据条件将元素进行分组, 分组成一个个flux, 可以根据结果看出一旦接收到元素会立刻发射, 而buffer要等一个集合收集完才会发射
//        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofMillis(1000));
//        flux.window(2).subscribe(subFlux -> {
//            System.out.println("接收到了事件");
//            subFlux.collectList().subscribe(System.out::println);
//        });
//        // groupby 通常根据提供的函数进行分组, 以函数的结果为key进行分组, 而window通常根据数量或者时间间隔分组, 并且window操作符不能访问分组的键, 而group可以根据key获取
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofMillis(1000));
        flux.groupBy(i -> i / 2).subscribe(groupFlux -> {
            System.out.println("key: " + groupFlux.key());
            groupFlux.collectList().subscribe(System.out::println);
        });
        Thread.sleep(10000);

    }

    private static void testFlat() throws InterruptedException {
        // 可以看出flatMap是一组一组接收到元素的, 然后会保证组内的发射顺序, 最后平展开发射到下游
//        Random random = new Random();
//        Flux.just(Arrays.asList(1,2,3),Arrays.asList("a","b","c","d"),Arrays.asList(7,8,9))
//                .doOnNext(System.out::println)
//                .flatMap(item -> Flux.fromIterable(item)
//                        .doOnSubscribe(subscription -> {
//                            System.out.println("已经订阅");
//                        })// 我们增加一个延时，订阅后延时一段时间再发送
//                        .delayElements(Duration.ofMillis(random.nextInt(100) + 100))
//                ).subscribe(System.out::println);
//        Thread.sleep(10*1000);
        // 可以看出和flatMap的区别在于, 严格保证第一个组发射完才会消费第二组元素.
//        Random random = new Random();
//        Flux.just(Arrays.asList(1,2,3),Arrays.asList("a","b","c","d"),Arrays.asList(7,8,9))
//                .doOnNext(System.out::println)
//                .concatMap(item -> Flux.fromIterable(item)
//                        .doOnSubscribe(subscription -> {
//                            System.out.println("已经订阅");
//                        })// 我们增加一个延时，订阅后延时一段时间再发送
//                        .delayElements(Duration.ofMillis(random.nextInt(100) + 100))
//                ).subscribe(System.out::println);
//        Thread.sleep(10*1000);
        Random random = new Random();
        Flux.just(Arrays.asList(1, 2, 3), Arrays.asList("a", "b", "c", "d"), Arrays.asList(7, 8, 9))
                .doOnNext(System.out::println)
                .flatMapSequential(item -> Flux.fromIterable(item)
                        .doOnSubscribe(subscription -> {
                            System.out.println("已经订阅");
                        })// 我们增加一个延时，订阅后延时一段时间再发送
                        .delayElements(Duration.ofMillis(random.nextInt(100) + 100))
                ).subscribe(System.out::println);

        Thread.sleep(10 * 1000);
    }

    private static void testView() throws InterruptedException {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7).delayElements(Duration.ofMillis(500))
                .doOnNext(i -> System.out.printf("doOnNext: %s%n", i))
                .doOnComplete(() -> System.out.printf("doOnComplete%n"))
                .doOnSubscribe(i -> System.out.printf("doOnSubscribe: %s%n", i))
                .doOnEach(i -> System.out.printf("doOnEach: %s%n", i));
        Thread.sleep(2000);
        flux.subscribe(i -> System.out.printf("subscribe: %s%n", i));
        Thread.sleep(5000);
    }

    private static void testCreate() throws InterruptedException {
        // using接收一个resource, 一个将resource转换成flux的function, 一个将resource关闭的方法
        final Flux<String> using = Flux.using(
                () -> new BufferedReader(new InputStreamReader(System.in)),
                reader -> Flux.fromStream(reader.lines()),
                reader -> {
                    try {
                        System.out.println("stop");
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        using.subscribe(System.out::println);
    }

    static FluxSink outSink;

    private static void testError() throws Exception {
        //多线程调用push方法
        Flux<String> f = Flux.push(sink -> {
            outSink = sink;
        });
//        f.onErrorResume(e -> Flux.just("1", "2"))
//        f.onErrorMap(Exception.class, new RuntimeException("包装后的异常"))
        f.onErrorReturn("6")
                .subscribe(System.out::println);
        //下发元素
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            if (i == 5) {
                outSink.error(new Exception("抛出异常5"));
            }
            outSink.next("我来了" + finalI);
        }
        Thread.sleep(1000);
    }

    private static void testCold() throws InterruptedException {
        //多线程调用push方法
        Flux<String> f = Flux.push(sink -> {
            outSink = sink;
        });
        //下发元素
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                outSink.next("我来了" + finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        f = f.publish();
        f.subscribe(item -> System.out.println("[subscriber 1] onNext:" + item));
        f.subscribe(item -> System.out.println("[subscriber 2] onNext:" + item));

        Thread.sleep(10000);
    }


    private static Consumer<Integer> printSub() {
        return i -> System.out.printf("subscribe: %s%n", i);
    }

    static <T> void printNext(T o) {
        System.out.printf("%s ", o);
    }

    private static void printComplete() throws InterruptedException {
        System.out.println("示例结束");
    }


}

class A {
    public int i;

    public int outAndIncreate() {
        i = i + 1;
        return i;
    }
}