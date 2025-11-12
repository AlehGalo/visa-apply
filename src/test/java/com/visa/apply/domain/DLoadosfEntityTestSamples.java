package com.visa.apply.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DLoadosfEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DLoadosfEntity getDLoadosfEntitySample1() {
        return new DLoadosfEntity().id(1L);
    }

    public static DLoadosfEntity getDLoadosfEntitySample2() {
        return new DLoadosfEntity().id(2L);
    }

    public static DLoadosfEntity getDLoadosfEntityRandomSampleGenerator() {
        return new DLoadosfEntity().id(longCount.incrementAndGet());
    }
}
