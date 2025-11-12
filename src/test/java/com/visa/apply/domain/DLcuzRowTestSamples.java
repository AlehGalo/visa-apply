package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DLcuzRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DLcuzRow getDLcuzRowSample1() {
        return new DLcuzRow()
            .id(1L)
            .vidZp("vidZp1")
            .nacBel("nacBel1")
            .nacPasp(1)
            .graj("graj1")
            .famil("famil1")
            .imena("imena1")
            .datRaj("datRaj1")
            .pol("pol1");
    }

    public static DLcuzRow getDLcuzRowSample2() {
        return new DLcuzRow()
            .id(2L)
            .vidZp("vidZp2")
            .nacBel("nacBel2")
            .nacPasp(2)
            .graj("graj2")
            .famil("famil2")
            .imena("imena2")
            .datRaj("datRaj2")
            .pol("pol2");
    }

    public static DLcuzRow getDLcuzRowRandomSampleGenerator() {
        return new DLcuzRow()
            .id(longCount.incrementAndGet())
            .vidZp(UUID.randomUUID().toString())
            .nacBel(UUID.randomUUID().toString())
            .nacPasp(intCount.incrementAndGet())
            .graj(UUID.randomUUID().toString())
            .famil(UUID.randomUUID().toString())
            .imena(UUID.randomUUID().toString())
            .datRaj(UUID.randomUUID().toString())
            .pol(UUID.randomUUID().toString());
    }
}
