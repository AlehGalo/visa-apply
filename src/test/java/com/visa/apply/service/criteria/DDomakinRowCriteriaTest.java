package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DDomakinRowCriteriaTest {

    @Test
    void newDDomakinRowCriteriaHasAllFiltersNullTest() {
        var dDomakinRowCriteria = new DDomakinRowCriteria();
        assertThat(dDomakinRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dDomakinRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dDomakinRowCriteria = new DDomakinRowCriteria();

        setAllFilters(dDomakinRowCriteria);

        assertThat(dDomakinRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dDomakinRowCriteriaCopyCreatesNullFilterTest() {
        var dDomakinRowCriteria = new DDomakinRowCriteria();
        var copy = dDomakinRowCriteria.copy();

        assertThat(dDomakinRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dDomakinRowCriteria)
        );
    }

    @Test
    void dDomakinRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dDomakinRowCriteria = new DDomakinRowCriteria();
        setAllFilters(dDomakinRowCriteria);

        var copy = dDomakinRowCriteria.copy();

        assertThat(dDomakinRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dDomakinRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dDomakinRowCriteria = new DDomakinRowCriteria();

        assertThat(dDomakinRowCriteria).hasToString("DDomakinRowCriteria{}");
    }

    private static void setAllFilters(DDomakinRowCriteria dDomakinRowCriteria) {
        dDomakinRowCriteria.id();
        dDomakinRowCriteria.dmVid();
        dDomakinRowCriteria.nomPok();
        dDomakinRowCriteria.domGraj();
        dDomakinRowCriteria.domFamil();
        dDomakinRowCriteria.domIme();
        dDomakinRowCriteria.domDarj();
        dDomakinRowCriteria.domNm();
        dDomakinRowCriteria.domAdres();
        dDomakinRowCriteria.vedDarj();
        dDomakinRowCriteria.vedNm();
        dDomakinRowCriteria.distinct();
    }

    private static Condition<DDomakinRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getDmVid()) &&
                condition.apply(criteria.getNomPok()) &&
                condition.apply(criteria.getDomGraj()) &&
                condition.apply(criteria.getDomFamil()) &&
                condition.apply(criteria.getDomIme()) &&
                condition.apply(criteria.getDomDarj()) &&
                condition.apply(criteria.getDomNm()) &&
                condition.apply(criteria.getDomAdres()) &&
                condition.apply(criteria.getVedDarj()) &&
                condition.apply(criteria.getVedNm()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DDomakinRowCriteria> copyFiltersAre(DDomakinRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getDmVid(), copy.getDmVid()) &&
                condition.apply(criteria.getNomPok(), copy.getNomPok()) &&
                condition.apply(criteria.getDomGraj(), copy.getDomGraj()) &&
                condition.apply(criteria.getDomFamil(), copy.getDomFamil()) &&
                condition.apply(criteria.getDomIme(), copy.getDomIme()) &&
                condition.apply(criteria.getDomDarj(), copy.getDomDarj()) &&
                condition.apply(criteria.getDomNm(), copy.getDomNm()) &&
                condition.apply(criteria.getDomAdres(), copy.getDomAdres()) &&
                condition.apply(criteria.getVedDarj(), copy.getVedDarj()) &&
                condition.apply(criteria.getVedNm(), copy.getVedNm()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
