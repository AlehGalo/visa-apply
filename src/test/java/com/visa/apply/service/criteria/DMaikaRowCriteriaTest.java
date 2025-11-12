package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DMaikaRowCriteriaTest {

    @Test
    void newDMaikaRowCriteriaHasAllFiltersNullTest() {
        var dMaikaRowCriteria = new DMaikaRowCriteria();
        assertThat(dMaikaRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dMaikaRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dMaikaRowCriteria = new DMaikaRowCriteria();

        setAllFilters(dMaikaRowCriteria);

        assertThat(dMaikaRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dMaikaRowCriteriaCopyCreatesNullFilterTest() {
        var dMaikaRowCriteria = new DMaikaRowCriteria();
        var copy = dMaikaRowCriteria.copy();

        assertThat(dMaikaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dMaikaRowCriteria)
        );
    }

    @Test
    void dMaikaRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dMaikaRowCriteria = new DMaikaRowCriteria();
        setAllFilters(dMaikaRowCriteria);

        var copy = dMaikaRowCriteria.copy();

        assertThat(dMaikaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dMaikaRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dMaikaRowCriteria = new DMaikaRowCriteria();

        assertThat(dMaikaRowCriteria).hasToString("DMaikaRowCriteria{}");
    }

    private static void setAllFilters(DMaikaRowCriteria dMaikaRowCriteria) {
        dMaikaRowCriteria.id();
        dMaikaRowCriteria.dcFamil();
        dMaikaRowCriteria.dcImena();
        dMaikaRowCriteria.dcPol();
        dMaikaRowCriteria.dcDarj();
        dMaikaRowCriteria.dcGrad();
        dMaikaRowCriteria.dcUlica();
        dMaikaRowCriteria.distinct();
    }

    private static Condition<DMaikaRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getDcFamil()) &&
                condition.apply(criteria.getDcImena()) &&
                condition.apply(criteria.getDcPol()) &&
                condition.apply(criteria.getDcDarj()) &&
                condition.apply(criteria.getDcGrad()) &&
                condition.apply(criteria.getDcUlica()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DMaikaRowCriteria> copyFiltersAre(DMaikaRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getDcFamil(), copy.getDcFamil()) &&
                condition.apply(criteria.getDcImena(), copy.getDcImena()) &&
                condition.apply(criteria.getDcPol(), copy.getDcPol()) &&
                condition.apply(criteria.getDcDarj(), copy.getDcDarj()) &&
                condition.apply(criteria.getDcGrad(), copy.getDcGrad()) &&
                condition.apply(criteria.getDcUlica(), copy.getDcUlica()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
