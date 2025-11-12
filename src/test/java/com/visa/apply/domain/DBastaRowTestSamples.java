package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DBastaRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DBastaRow getDBastaRowSample1() {
        return new DBastaRow().id(1L).dcFamil("dcFamil1").dcImena("dcImena1").dcPol("dcPol1").dcGrad("dcGrad1").dcUlica("dcUlica1");
    }

    public static DBastaRow getDBastaRowSample2() {
        return new DBastaRow().id(2L).dcFamil("dcFamil2").dcImena("dcImena2").dcPol("dcPol2").dcGrad("dcGrad2").dcUlica("dcUlica2");
    }

    public static DBastaRow getDBastaRowRandomSampleGenerator() {
        return new DBastaRow()
            .id(longCount.incrementAndGet())
            .dcFamil(UUID.randomUUID().toString())
            .dcImena(UUID.randomUUID().toString())
            .dcPol(UUID.randomUUID().toString())
            .dcGrad(UUID.randomUUID().toString())
            .dcUlica(UUID.randomUUID().toString());
    }
}
