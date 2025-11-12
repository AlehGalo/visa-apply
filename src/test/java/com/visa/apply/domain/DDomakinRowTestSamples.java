package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DDomakinRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DDomakinRow getDDomakinRowSample1() {
        return new DDomakinRow()
            .id(1L)
            .dmVid("dmVid1")
            .nomPok("nomPok1")
            .domGraj("domGraj1")
            .domFamil("domFamil1")
            .domIme("domIme1")
            .domDarj("domDarj1")
            .domNm(1)
            .domAdres("domAdres1")
            .vedDarj("vedDarj1")
            .vedNm("vedNm1");
    }

    public static DDomakinRow getDDomakinRowSample2() {
        return new DDomakinRow()
            .id(2L)
            .dmVid("dmVid2")
            .nomPok("nomPok2")
            .domGraj("domGraj2")
            .domFamil("domFamil2")
            .domIme("domIme2")
            .domDarj("domDarj2")
            .domNm(2)
            .domAdres("domAdres2")
            .vedDarj("vedDarj2")
            .vedNm("vedNm2");
    }

    public static DDomakinRow getDDomakinRowRandomSampleGenerator() {
        return new DDomakinRow()
            .id(longCount.incrementAndGet())
            .dmVid(UUID.randomUUID().toString())
            .nomPok(UUID.randomUUID().toString())
            .domGraj(UUID.randomUUID().toString())
            .domFamil(UUID.randomUUID().toString())
            .domIme(UUID.randomUUID().toString())
            .domDarj(UUID.randomUUID().toString())
            .domNm(intCount.incrementAndGet())
            .domAdres(UUID.randomUUID().toString())
            .vedDarj(UUID.randomUUID().toString())
            .vedNm(UUID.randomUUID().toString());
    }
}
