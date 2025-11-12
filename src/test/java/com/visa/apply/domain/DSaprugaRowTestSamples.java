package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DSaprugaRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DSaprugaRow getDSaprugaRowSample1() {
        return new DSaprugaRow().id(1L).spMrjdarj("spMrjdarj1");
    }

    public static DSaprugaRow getDSaprugaRowSample2() {
        return new DSaprugaRow().id(2L).spMrjdarj("spMrjdarj2");
    }

    public static DSaprugaRow getDSaprugaRowRandomSampleGenerator() {
        return new DSaprugaRow().id(longCount.incrementAndGet()).spMrjdarj(UUID.randomUUID().toString());
    }
}
