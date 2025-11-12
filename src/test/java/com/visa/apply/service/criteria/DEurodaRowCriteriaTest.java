package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DEurodaRowCriteriaTest {

    @Test
    void newDEurodaRowCriteriaHasAllFiltersNullTest() {
        var dEurodaRowCriteria = new DEurodaRowCriteria();
        assertThat(dEurodaRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dEurodaRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dEurodaRowCriteria = new DEurodaRowCriteria();

        setAllFilters(dEurodaRowCriteria);

        assertThat(dEurodaRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dEurodaRowCriteriaCopyCreatesNullFilterTest() {
        var dEurodaRowCriteria = new DEurodaRowCriteria();
        var copy = dEurodaRowCriteria.copy();

        assertThat(dEurodaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dEurodaRowCriteria)
        );
    }

    @Test
    void dEurodaRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dEurodaRowCriteria = new DEurodaRowCriteria();
        setAllFilters(dEurodaRowCriteria);

        var copy = dEurodaRowCriteria.copy();

        assertThat(dEurodaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dEurodaRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dEurodaRowCriteria = new DEurodaRowCriteria();

        assertThat(dEurodaRowCriteria).hasToString("DEurodaRowCriteria{}");
    }

    private static void setAllFilters(DEurodaRowCriteria dEurodaRowCriteria) {
        dEurodaRowCriteria.id();
        dEurodaRowCriteria.euFamil();
        dEurodaRowCriteria.euImena();
        dEurodaRowCriteria.euNacBel();
        dEurodaRowCriteria.euRodstvo();
        dEurodaRowCriteria.distinct();
    }

    private static Condition<DEurodaRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getEuFamil()) &&
                condition.apply(criteria.getEuImena()) &&
                condition.apply(criteria.getEuNacBel()) &&
                condition.apply(criteria.getEuRodstvo()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DEurodaRowCriteria> copyFiltersAre(DEurodaRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getEuFamil(), copy.getEuFamil()) &&
                condition.apply(criteria.getEuImena(), copy.getEuImena()) &&
                condition.apply(criteria.getEuNacBel(), copy.getEuNacBel()) &&
                condition.apply(criteria.getEuRodstvo(), copy.getEuRodstvo()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
