package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DFingersRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DFingersRow getDFingersRowSample1() {
        return new DFingersRow()
            .id(1L)
            .fnUsera("fnUsera1")
            .fnSid(1)
            .fnPnr(1)
            .fnVidmol("fnVidmol1")
            .fnDrugi("fnDrugi1")
            .fnDeviceid(1)
            .fnScanres(1)
            .fnWidth(1)
            .fnHeight(1)
            .fnPixeldepth(1)
            .fnCompressalgo(1)
            .fnFingerposition("fnFingerposition1")
            .fnImagequality(1)
            .fnImage("fnImage1")
            .fnImglen(1)
            .fnNottakenreason("fnNottakenreason1");
    }

    public static DFingersRow getDFingersRowSample2() {
        return new DFingersRow()
            .id(2L)
            .fnUsera("fnUsera2")
            .fnSid(2)
            .fnPnr(2)
            .fnVidmol("fnVidmol2")
            .fnDrugi("fnDrugi2")
            .fnDeviceid(2)
            .fnScanres(2)
            .fnWidth(2)
            .fnHeight(2)
            .fnPixeldepth(2)
            .fnCompressalgo(2)
            .fnFingerposition("fnFingerposition2")
            .fnImagequality(2)
            .fnImage("fnImage2")
            .fnImglen(2)
            .fnNottakenreason("fnNottakenreason2");
    }

    public static DFingersRow getDFingersRowRandomSampleGenerator() {
        return new DFingersRow()
            .id(longCount.incrementAndGet())
            .fnUsera(UUID.randomUUID().toString())
            .fnSid(intCount.incrementAndGet())
            .fnPnr(intCount.incrementAndGet())
            .fnVidmol(UUID.randomUUID().toString())
            .fnDrugi(UUID.randomUUID().toString())
            .fnDeviceid(intCount.incrementAndGet())
            .fnScanres(intCount.incrementAndGet())
            .fnWidth(intCount.incrementAndGet())
            .fnHeight(intCount.incrementAndGet())
            .fnPixeldepth(intCount.incrementAndGet())
            .fnCompressalgo(intCount.incrementAndGet())
            .fnFingerposition(UUID.randomUUID().toString())
            .fnImagequality(intCount.incrementAndGet())
            .fnImage(UUID.randomUUID().toString())
            .fnImglen(intCount.incrementAndGet())
            .fnNottakenreason(UUID.randomUUID().toString());
    }
}
