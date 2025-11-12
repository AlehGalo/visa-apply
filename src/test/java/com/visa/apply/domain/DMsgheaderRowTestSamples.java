package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DMsgheaderRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DMsgheaderRow getDMsgheaderRowSample1() {
        return new DMsgheaderRow()
            .id(1L)
            .mhKscreated("mhKscreated1")
            .mhRegnom(1)
            .mhVfsrefno("mhVfsrefno1")
            .mhUsera("mhUsera1")
            .mhDatvav("mhDatvav1");
    }

    public static DMsgheaderRow getDMsgheaderRowSample2() {
        return new DMsgheaderRow()
            .id(2L)
            .mhKscreated("mhKscreated2")
            .mhRegnom(2)
            .mhVfsrefno("mhVfsrefno2")
            .mhUsera("mhUsera2")
            .mhDatvav("mhDatvav2");
    }

    public static DMsgheaderRow getDMsgheaderRowRandomSampleGenerator() {
        return new DMsgheaderRow()
            .id(longCount.incrementAndGet())
            .mhKscreated(UUID.randomUUID().toString())
            .mhRegnom(intCount.incrementAndGet())
            .mhVfsrefno(UUID.randomUUID().toString())
            .mhUsera(UUID.randomUUID().toString())
            .mhDatvav(UUID.randomUUID().toString());
    }
}
