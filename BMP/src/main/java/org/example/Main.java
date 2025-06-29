package org.example;

import static java.lang.System.currentTimeMillis;

public class Main {
    public static void main(String[] args) {
        ImageHandler image = new ImageHandler();
        image.load("src/main/resources/image.jpg");

        long startTime = 0;
        long stopTime = 0;

//        startTime = System.currentTimeMillis();
//        image.increaseBrightness(50);
//        stopTime = System.currentTimeMillis();
//        image.write("src/main/resources/image_1.jpg");
//        System.out.println("Execution time without the usage of threads: "+(stopTime-startTime));
//
//        startTime = System.currentTimeMillis();
//        image.threadedIncreaseBrightness(50);
//        stopTime = System.currentTimeMillis();
//        image.write("src/main/resources/image_1.jpg");
//        System.out.println("Execution time with the usage of one thread per core: "+(stopTime-startTime));
//
//        startTime = System.currentTimeMillis();
//        image.pooledIncreaseBrightness(50);
//        stopTime = System.currentTimeMillis();
//        image.write("src/main/resources/image_1.jpg");
//        System.out.println("Execution time with the usage of thread pool: "+(stopTime-startTime));
//
//        startTime = System.currentTimeMillis();
//        image.pooledIncreaseWithExecutorService(50);
//        stopTime = System.currentTimeMillis();
//        image.write("src/main/resources/image_1.jpg");
//        System.out.println("Execution time with the usage of executor service: "+(stopTime-startTime));

        image.blueChannelHistogram();
    }
}