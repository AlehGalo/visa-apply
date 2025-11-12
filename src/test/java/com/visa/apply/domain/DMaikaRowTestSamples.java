package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DMaikaRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DMaikaRow getDMaikaRowSample1() {
        return new DMaikaRow()
            .id(1L)
            .dcFamil("dcFamil1")
            .dcImena("dcImena1")
            .dcPol("dcPol1")
            .dcDarj("dcDarj1")
            .dcGrad("dcGrad1")
            .dcUlica("dcUlica1");
    }

    public static DMaikaRow getDMaikaRowSample2() {
        return new DMaikaRow()
            .id(2L)
            .dcFamil("dcFamil2")
            .dcImena("dcImena2")
            .dcPol("dcPol2")
            .dcDarj("dcDarj2")
            .dcGrad("dcGrad2")
            .dcUlica("dcUlica2");
    }

    public static DMaikaRow getDMaikaRowRandomSampleGenerator() {
        return new DMaikaRow()
            .id(longCount.incrementAndGet())
            .dcFamil(UUID.randomUUID().toString())
            .dcImena(UUID.randomUUID().toString())
            .dcPol(UUID.randomUUID().toString())
            .dcDarj(UUID.randomUUID().toString())
            .dcGrad(UUID.randomUUID().toString())
            .dcUlica(UUID.randomUUID().toString());
    }
}
