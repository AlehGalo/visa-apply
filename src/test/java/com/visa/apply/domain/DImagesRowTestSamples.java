package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DImagesRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DImagesRow getDImagesRowSample1() {
        return new DImagesRow().id(1L).imDevicetype(1).imWidth(1).imHeight(1).imImglen(1).imImage("imImage1");
    }

    public static DImagesRow getDImagesRowSample2() {
        return new DImagesRow().id(2L).imDevicetype(2).imWidth(2).imHeight(2).imImglen(2).imImage("imImage2");
    }

    public static DImagesRow getDImagesRowRandomSampleGenerator() {
        return new DImagesRow()
            .id(longCount.incrementAndGet())
            .imDevicetype(intCount.incrementAndGet())
            .imWidth(intCount.incrementAndGet())
            .imHeight(intCount.incrementAndGet())
            .imImglen(intCount.incrementAndGet())
            .imImage(UUID.randomUUID().toString());
    }
}
