package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DFingersRowCriteriaTest {

    @Test
    void newDFingersRowCriteriaHasAllFiltersNullTest() {
        var dFingersRowCriteria = new DFingersRowCriteria();
        assertThat(dFingersRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dFingersRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dFingersRowCriteria = new DFingersRowCriteria();

        setAllFilters(dFingersRowCriteria);

        assertThat(dFingersRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dFingersRowCriteriaCopyCreatesNullFilterTest() {
        var dFingersRowCriteria = new DFingersRowCriteria();
        var copy = dFingersRowCriteria.copy();

        assertThat(dFingersRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dFingersRowCriteria)
        );
    }

    @Test
    void dFingersRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dFingersRowCriteria = new DFingersRowCriteria();
        setAllFilters(dFingersRowCriteria);

        var copy = dFingersRowCriteria.copy();

        assertThat(dFingersRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dFingersRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dFingersRowCriteria = new DFingersRowCriteria();

        assertThat(dFingersRowCriteria).hasToString("DFingersRowCriteria{}");
    }

    private static void setAllFilters(DFingersRowCriteria dFingersRowCriteria) {
        dFingersRowCriteria.id();
        dFingersRowCriteria.fnDatreg();
        dFingersRowCriteria.fnDatvav();
        dFingersRowCriteria.fnUsera();
        dFingersRowCriteria.fnSid();
        dFingersRowCriteria.fnPnr();
        dFingersRowCriteria.fnVidmol();
        dFingersRowCriteria.fnDrugi();
        dFingersRowCriteria.fnDeviceid();
        dFingersRowCriteria.fnScanres();
        dFingersRowCriteria.fnWidth();
        dFingersRowCriteria.fnHeight();
        dFingersRowCriteria.fnPixeldepth();
        dFingersRowCriteria.fnCompressalgo();
        dFingersRowCriteria.fnFingerposition();
        dFingersRowCriteria.fnImagequality();
        dFingersRowCriteria.fnImage();
        dFingersRowCriteria.fnImglen();
        dFingersRowCriteria.fnNottakenreason();
        dFingersRowCriteria.distinct();
    }

    private static Condition<DFingersRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getFnDatreg()) &&
                condition.apply(criteria.getFnDatvav()) &&
                condition.apply(criteria.getFnUsera()) &&
                condition.apply(criteria.getFnSid()) &&
                condition.apply(criteria.getFnPnr()) &&
                condition.apply(criteria.getFnVidmol()) &&
                condition.apply(criteria.getFnDrugi()) &&
                condition.apply(criteria.getFnDeviceid()) &&
                condition.apply(criteria.getFnScanres()) &&
                condition.apply(criteria.getFnWidth()) &&
                condition.apply(criteria.getFnHeight()) &&
                condition.apply(criteria.getFnPixeldepth()) &&
                condition.apply(criteria.getFnCompressalgo()) &&
                condition.apply(criteria.getFnFingerposition()) &&
                condition.apply(criteria.getFnImagequality()) &&
                condition.apply(criteria.getFnImage()) &&
                condition.apply(criteria.getFnImglen()) &&
                condition.apply(criteria.getFnNottakenreason()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DFingersRowCriteria> copyFiltersAre(DFingersRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getFnDatreg(), copy.getFnDatreg()) &&
                condition.apply(criteria.getFnDatvav(), copy.getFnDatvav()) &&
                condition.apply(criteria.getFnUsera(), copy.getFnUsera()) &&
                condition.apply(criteria.getFnSid(), copy.getFnSid()) &&
                condition.apply(criteria.getFnPnr(), copy.getFnPnr()) &&
                condition.apply(criteria.getFnVidmol(), copy.getFnVidmol()) &&
                condition.apply(criteria.getFnDrugi(), copy.getFnDrugi()) &&
                condition.apply(criteria.getFnDeviceid(), copy.getFnDeviceid()) &&
                condition.apply(criteria.getFnScanres(), copy.getFnScanres()) &&
                condition.apply(criteria.getFnWidth(), copy.getFnWidth()) &&
                condition.apply(criteria.getFnHeight(), copy.getFnHeight()) &&
                condition.apply(criteria.getFnPixeldepth(), copy.getFnPixeldepth()) &&
                condition.apply(criteria.getFnCompressalgo(), copy.getFnCompressalgo()) &&
                condition.apply(criteria.getFnFingerposition(), copy.getFnFingerposition()) &&
                condition.apply(criteria.getFnImagequality(), copy.getFnImagequality()) &&
                condition.apply(criteria.getFnImage(), copy.getFnImage()) &&
                condition.apply(criteria.getFnImglen(), copy.getFnImglen()) &&
                condition.apply(criteria.getFnNottakenreason(), copy.getFnNottakenreason()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
