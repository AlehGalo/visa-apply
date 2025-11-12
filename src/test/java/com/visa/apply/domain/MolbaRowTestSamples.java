package com.visa.apply.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MolbaRowTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static MolbaRow getMolbaRowSample1() {
        return new MolbaRow()
            .id(1L)
            .vidvis("vidvis1")
            .brvl(1)
            .vidus("vidus1")
            .valvis("valvis1")
            .brdni(1)
            .cel(1)
            .gratis("gratis1")
            .imavisa("imavisa1")
            .cenamol(1)
            .cenacurr("cenacurr1")
            .maindest("maindest1")
            .maindestnm("maindestnm1")
            .gkppDarj("gkppDarj1")
            .gkppText("gkppText1")
            .textIni("textIni1");
    }

    public static MolbaRow getMolbaRowSample2() {
        return new MolbaRow()
            .id(2L)
            .vidvis("vidvis2")
            .brvl(2)
            .vidus("vidus2")
            .valvis("valvis2")
            .brdni(2)
            .cel(2)
            .gratis("gratis2")
            .imavisa("imavisa2")
            .cenamol(2)
            .cenacurr("cenacurr2")
            .maindest("maindest2")
            .maindestnm("maindestnm2")
            .gkppDarj("gkppDarj2")
            .gkppText("gkppText2")
            .textIni("textIni2");
    }

    public static MolbaRow getMolbaRowRandomSampleGenerator() {
        return new MolbaRow()
            .id(longCount.incrementAndGet())
            .vidvis(UUID.randomUUID().toString())
            .brvl(intCount.incrementAndGet())
            .vidus(UUID.randomUUID().toString())
            .valvis(UUID.randomUUID().toString())
            .brdni(intCount.incrementAndGet())
            .cel(intCount.incrementAndGet())
            .gratis(UUID.randomUUID().toString())
            .imavisa(UUID.randomUUID().toString())
            .cenamol(intCount.incrementAndGet())
            .cenacurr(UUID.randomUUID().toString())
            .maindest(UUID.randomUUID().toString())
            .maindestnm(UUID.randomUUID().toString())
            .gkppDarj(UUID.randomUUID().toString())
            .gkppText(UUID.randomUUID().toString())
            .textIni(UUID.randomUUID().toString());
    }
}
