package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DSaprugaRowCriteriaTest {

    @Test
    void newDSaprugaRowCriteriaHasAllFiltersNullTest() {
        var dSaprugaRowCriteria = new DSaprugaRowCriteria();
        assertThat(dSaprugaRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dSaprugaRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dSaprugaRowCriteria = new DSaprugaRowCriteria();

        setAllFilters(dSaprugaRowCriteria);

        assertThat(dSaprugaRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dSaprugaRowCriteriaCopyCreatesNullFilterTest() {
        var dSaprugaRowCriteria = new DSaprugaRowCriteria();
        var copy = dSaprugaRowCriteria.copy();

        assertThat(dSaprugaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dSaprugaRowCriteria)
        );
    }

    @Test
    void dSaprugaRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dSaprugaRowCriteria = new DSaprugaRowCriteria();
        setAllFilters(dSaprugaRowCriteria);

        var copy = dSaprugaRowCriteria.copy();

        assertThat(dSaprugaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dSaprugaRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dSaprugaRowCriteria = new DSaprugaRowCriteria();

        assertThat(dSaprugaRowCriteria).hasToString("DSaprugaRowCriteria{}");
    }

    private static void setAllFilters(DSaprugaRowCriteria dSaprugaRowCriteria) {
        dSaprugaRowCriteria.id();
        dSaprugaRowCriteria.spMrjdarj();
        dSaprugaRowCriteria.distinct();
    }

    private static Condition<DSaprugaRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) && condition.apply(criteria.getSpMrjdarj()) && condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DSaprugaRowCriteria> copyFiltersAre(DSaprugaRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getSpMrjdarj(), copy.getSpMrjdarj()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
