package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DLcdopRowCriteriaTest {

    @Test
    void newDLcdopRowCriteriaHasAllFiltersNullTest() {
        var dLcdopRowCriteria = new DLcdopRowCriteria();
        assertThat(dLcdopRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dLcdopRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dLcdopRowCriteria = new DLcdopRowCriteria();

        setAllFilters(dLcdopRowCriteria);

        assertThat(dLcdopRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dLcdopRowCriteriaCopyCreatesNullFilterTest() {
        var dLcdopRowCriteria = new DLcdopRowCriteria();
        var copy = dLcdopRowCriteria.copy();

        assertThat(dLcdopRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dLcdopRowCriteria)
        );
    }

    @Test
    void dLcdopRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dLcdopRowCriteria = new DLcdopRowCriteria();
        setAllFilters(dLcdopRowCriteria);

        var copy = dLcdopRowCriteria.copy();

        assertThat(dLcdopRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dLcdopRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dLcdopRowCriteria = new DLcdopRowCriteria();

        assertThat(dLcdopRowCriteria).hasToString("DLcdopRowCriteria{}");
    }

    private static void setAllFilters(DLcdopRowCriteria dLcdopRowCriteria) {
        dLcdopRowCriteria.id();
        dLcdopRowCriteria.ldMrjdarj();
        dLcdopRowCriteria.ldMrjnm();
        dLcdopRowCriteria.ldMrjgraj();
        dLcdopRowCriteria.ldZenen();
        dLcdopRowCriteria.ldJitDarj();
        dLcdopRowCriteria.ldJitNm();
        dLcdopRowCriteria.ldJitUl();
        dLcdopRowCriteria.ldTel();
        dLcdopRowCriteria.ldRabota();
        dLcdopRowCriteria.ldProfkod();
        dLcdopRowCriteria.ldProfesia();
        dLcdopRowCriteria.ldSlDarj();
        dLcdopRowCriteria.ldSlNm();
        dLcdopRowCriteria.ldSlUl();
        dLcdopRowCriteria.distinct();
    }

    private static Condition<DLcdopRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getLdMrjdarj()) &&
                condition.apply(criteria.getLdMrjnm()) &&
                condition.apply(criteria.getLdMrjgraj()) &&
                condition.apply(criteria.getLdZenen()) &&
                condition.apply(criteria.getLdJitDarj()) &&
                condition.apply(criteria.getLdJitNm()) &&
                condition.apply(criteria.getLdJitUl()) &&
                condition.apply(criteria.getLdTel()) &&
                condition.apply(criteria.getLdRabota()) &&
                condition.apply(criteria.getLdProfkod()) &&
                condition.apply(criteria.getLdProfesia()) &&
                condition.apply(criteria.getLdSlDarj()) &&
                condition.apply(criteria.getLdSlNm()) &&
                condition.apply(criteria.getLdSlUl()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DLcdopRowCriteria> copyFiltersAre(DLcdopRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getLdMrjdarj(), copy.getLdMrjdarj()) &&
                condition.apply(criteria.getLdMrjnm(), copy.getLdMrjnm()) &&
                condition.apply(criteria.getLdMrjgraj(), copy.getLdMrjgraj()) &&
                condition.apply(criteria.getLdZenen(), copy.getLdZenen()) &&
                condition.apply(criteria.getLdJitDarj(), copy.getLdJitDarj()) &&
                condition.apply(criteria.getLdJitNm(), copy.getLdJitNm()) &&
                condition.apply(criteria.getLdJitUl(), copy.getLdJitUl()) &&
                condition.apply(criteria.getLdTel(), copy.getLdTel()) &&
                condition.apply(criteria.getLdRabota(), copy.getLdRabota()) &&
                condition.apply(criteria.getLdProfkod(), copy.getLdProfkod()) &&
                condition.apply(criteria.getLdProfesia(), copy.getLdProfesia()) &&
                condition.apply(criteria.getLdSlDarj(), copy.getLdSlDarj()) &&
                condition.apply(criteria.getLdSlNm(), copy.getLdSlNm()) &&
                condition.apply(criteria.getLdSlUl(), copy.getLdSlUl()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
