package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DVoitRowCriteriaTest {

    @Test
    void newDVoitRowCriteriaHasAllFiltersNullTest() {
        var dVoitRowCriteria = new DVoitRowCriteria();
        assertThat(dVoitRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dVoitRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dVoitRowCriteria = new DVoitRowCriteria();

        setAllFilters(dVoitRowCriteria);

        assertThat(dVoitRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dVoitRowCriteriaCopyCreatesNullFilterTest() {
        var dVoitRowCriteria = new DVoitRowCriteria();
        var copy = dVoitRowCriteria.copy();

        assertThat(dVoitRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dVoitRowCriteria)
        );
    }

    @Test
    void dVoitRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dVoitRowCriteria = new DVoitRowCriteria();
        setAllFilters(dVoitRowCriteria);

        var copy = dVoitRowCriteria.copy();

        assertThat(dVoitRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dVoitRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dVoitRowCriteria = new DVoitRowCriteria();

        assertThat(dVoitRowCriteria).hasToString("DVoitRowCriteria{}");
    }

    private static void setAllFilters(DVoitRowCriteria dVoitRowCriteria) {
        dVoitRowCriteria.id();
        dVoitRowCriteria.vnom();
        dVoitRowCriteria.vime();
        dVoitRowCriteria.bgime();
        dVoitRowCriteria.bgadres();
        dVoitRowCriteria.distinct();
    }

    private static Condition<DVoitRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getVnom()) &&
                condition.apply(criteria.getVime()) &&
                condition.apply(criteria.getBgime()) &&
                condition.apply(criteria.getBgadres()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DVoitRowCriteria> copyFiltersAre(DVoitRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getVnom(), copy.getVnom()) &&
                condition.apply(criteria.getVime(), copy.getVime()) &&
                condition.apply(criteria.getBgime(), copy.getBgime()) &&
                condition.apply(criteria.getBgadres(), copy.getBgadres()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
