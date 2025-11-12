package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DEurodaRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DEurodaRow getDEurodaRowSample1() {
        return new DEurodaRow().id(1L).euFamil("euFamil1").euImena("euImena1").euNacBel("euNacBel1").euRodstvo("euRodstvo1");
    }

    public static DEurodaRow getDEurodaRowSample2() {
        return new DEurodaRow().id(2L).euFamil("euFamil2").euImena("euImena2").euNacBel("euNacBel2").euRodstvo("euRodstvo2");
    }

    public static DEurodaRow getDEurodaRowRandomSampleGenerator() {
        return new DEurodaRow()
            .id(longCount.incrementAndGet())
            .euFamil(UUID.randomUUID().toString())
            .euImena(UUID.randomUUID().toString())
            .euNacBel(UUID.randomUUID().toString())
            .euRodstvo(UUID.randomUUID().toString());
    }
}
