package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DVoitRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DVoitRow getDVoitRowSample1() {
        return new DVoitRow().id(1L).vnom("vnom1").vime("vime1").bgime("bgime1").bgadres("bgadres1");
    }

    public static DVoitRow getDVoitRowSample2() {
        return new DVoitRow().id(2L).vnom("vnom2").vime("vime2").bgime("bgime2").bgadres("bgadres2");
    }

    public static DVoitRow getDVoitRowRandomSampleGenerator() {
        return new DVoitRow()
            .id(longCount.incrementAndGet())
            .vnom(UUID.randomUUID().toString())
            .vime(UUID.randomUUID().toString())
            .bgime(UUID.randomUUID().toString())
            .bgadres(UUID.randomUUID().toString());
    }
}
