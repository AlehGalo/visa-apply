package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DBastaRowCriteriaTest {

    @Test
    void newDBastaRowCriteriaHasAllFiltersNullTest() {
        var dBastaRowCriteria = new DBastaRowCriteria();
        assertThat(dBastaRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dBastaRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dBastaRowCriteria = new DBastaRowCriteria();

        setAllFilters(dBastaRowCriteria);

        assertThat(dBastaRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dBastaRowCriteriaCopyCreatesNullFilterTest() {
        var dBastaRowCriteria = new DBastaRowCriteria();
        var copy = dBastaRowCriteria.copy();

        assertThat(dBastaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dBastaRowCriteria)
        );
    }

    @Test
    void dBastaRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dBastaRowCriteria = new DBastaRowCriteria();
        setAllFilters(dBastaRowCriteria);

        var copy = dBastaRowCriteria.copy();

        assertThat(dBastaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dBastaRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dBastaRowCriteria = new DBastaRowCriteria();

        assertThat(dBastaRowCriteria).hasToString("DBastaRowCriteria{}");
    }

    private static void setAllFilters(DBastaRowCriteria dBastaRowCriteria) {
        dBastaRowCriteria.id();
        dBastaRowCriteria.dcFamil();
        dBastaRowCriteria.dcImena();
        dBastaRowCriteria.dcPol();
        dBastaRowCriteria.dcGrad();
        dBastaRowCriteria.dcUlica();
        dBastaRowCriteria.distinct();
    }

    private static Condition<DBastaRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getDcFamil()) &&
                condition.apply(criteria.getDcImena()) &&
                condition.apply(criteria.getDcPol()) &&
                condition.apply(criteria.getDcGrad()) &&
                condition.apply(criteria.getDcUlica()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DBastaRowCriteria> copyFiltersAre(DBastaRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getDcFamil(), copy.getDcFamil()) &&
                condition.apply(criteria.getDcImena(), copy.getDcImena()) &&
                condition.apply(criteria.getDcPol(), copy.getDcPol()) &&
                condition.apply(criteria.getDcGrad(), copy.getDcGrad()) &&
                condition.apply(criteria.getDcUlica(), copy.getDcUlica()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
