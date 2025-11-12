package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DLcuzRowCriteriaTest {

    @Test
    void newDLcuzRowCriteriaHasAllFiltersNullTest() {
        var dLcuzRowCriteria = new DLcuzRowCriteria();
        assertThat(dLcuzRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dLcuzRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dLcuzRowCriteria = new DLcuzRowCriteria();

        setAllFilters(dLcuzRowCriteria);

        assertThat(dLcuzRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dLcuzRowCriteriaCopyCreatesNullFilterTest() {
        var dLcuzRowCriteria = new DLcuzRowCriteria();
        var copy = dLcuzRowCriteria.copy();

        assertThat(dLcuzRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dLcuzRowCriteria)
        );
    }

    @Test
    void dLcuzRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dLcuzRowCriteria = new DLcuzRowCriteria();
        setAllFilters(dLcuzRowCriteria);

        var copy = dLcuzRowCriteria.copy();

        assertThat(dLcuzRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dLcuzRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dLcuzRowCriteria = new DLcuzRowCriteria();

        assertThat(dLcuzRowCriteria).hasToString("DLcuzRowCriteria{}");
    }

    private static void setAllFilters(DLcuzRowCriteria dLcuzRowCriteria) {
        dLcuzRowCriteria.id();
        dLcuzRowCriteria.vidZp();
        dLcuzRowCriteria.nacBel();
        dLcuzRowCriteria.nacPasp();
        dLcuzRowCriteria.paspVal();
        dLcuzRowCriteria.graj();
        dLcuzRowCriteria.famil();
        dLcuzRowCriteria.imena();
        dLcuzRowCriteria.datRaj();
        dLcuzRowCriteria.pol();
        dLcuzRowCriteria.datIzd();
        dLcuzRowCriteria.distinct();
    }

    private static Condition<DLcuzRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getVidZp()) &&
                condition.apply(criteria.getNacBel()) &&
                condition.apply(criteria.getNacPasp()) &&
                condition.apply(criteria.getPaspVal()) &&
                condition.apply(criteria.getGraj()) &&
                condition.apply(criteria.getFamil()) &&
                condition.apply(criteria.getImena()) &&
                condition.apply(criteria.getDatRaj()) &&
                condition.apply(criteria.getPol()) &&
                condition.apply(criteria.getDatIzd()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DLcuzRowCriteria> copyFiltersAre(DLcuzRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getVidZp(), copy.getVidZp()) &&
                condition.apply(criteria.getNacBel(), copy.getNacBel()) &&
                condition.apply(criteria.getNacPasp(), copy.getNacPasp()) &&
                condition.apply(criteria.getPaspVal(), copy.getPaspVal()) &&
                condition.apply(criteria.getGraj(), copy.getGraj()) &&
                condition.apply(criteria.getFamil(), copy.getFamil()) &&
                condition.apply(criteria.getImena(), copy.getImena()) &&
                condition.apply(criteria.getDatRaj(), copy.getDatRaj()) &&
                condition.apply(criteria.getPol(), copy.getPol()) &&
                condition.apply(criteria.getDatIzd(), copy.getDatIzd()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
