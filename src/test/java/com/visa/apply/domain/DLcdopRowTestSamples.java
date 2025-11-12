package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DLcdopRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DLcdopRow getDLcdopRowSample1() {
        return new DLcdopRow()
            .id(1L)
            .ldMrjdarj("ldMrjdarj1")
            .ldMrjnm("ldMrjnm1")
            .ldMrjgraj("ldMrjgraj1")
            .ldZenen("ldZenen1")
            .ldJitDarj("ldJitDarj1")
            .ldJitNm("ldJitNm1")
            .ldJitUl("ldJitUl1")
            .ldTel(1L)
            .ldRabota("ldRabota1")
            .ldProfkod("ldProfkod1")
            .ldProfesia("ldProfesia1")
            .ldSlDarj("ldSlDarj1")
            .ldSlNm("ldSlNm1")
            .ldSlUl("ldSlUl1");
    }

    public static DLcdopRow getDLcdopRowSample2() {
        return new DLcdopRow()
            .id(2L)
            .ldMrjdarj("ldMrjdarj2")
            .ldMrjnm("ldMrjnm2")
            .ldMrjgraj("ldMrjgraj2")
            .ldZenen("ldZenen2")
            .ldJitDarj("ldJitDarj2")
            .ldJitNm("ldJitNm2")
            .ldJitUl("ldJitUl2")
            .ldTel(2L)
            .ldRabota("ldRabota2")
            .ldProfkod("ldProfkod2")
            .ldProfesia("ldProfesia2")
            .ldSlDarj("ldSlDarj2")
            .ldSlNm("ldSlNm2")
            .ldSlUl("ldSlUl2");
    }

    public static DLcdopRow getDLcdopRowRandomSampleGenerator() {
        return new DLcdopRow()
            .id(longCount.incrementAndGet())
            .ldMrjdarj(UUID.randomUUID().toString())
            .ldMrjnm(UUID.randomUUID().toString())
            .ldMrjgraj(UUID.randomUUID().toString())
            .ldZenen(UUID.randomUUID().toString())
            .ldJitDarj(UUID.randomUUID().toString())
            .ldJitNm(UUID.randomUUID().toString())
            .ldJitUl(UUID.randomUUID().toString())
            .ldTel(longCount.incrementAndGet())
            .ldRabota(UUID.randomUUID().toString())
            .ldProfkod(UUID.randomUUID().toString())
            .ldProfesia(UUID.randomUUID().toString())
            .ldSlDarj(UUID.randomUUID().toString())
            .ldSlNm(UUID.randomUUID().toString())
            .ldSlUl(UUID.randomUUID().toString());
    }
}
