package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DMsgheaderRowCriteriaTest {

    @Test
    void newDMsgheaderRowCriteriaHasAllFiltersNullTest() {
        var dMsgheaderRowCriteria = new DMsgheaderRowCriteria();
        assertThat(dMsgheaderRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dMsgheaderRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dMsgheaderRowCriteria = new DMsgheaderRowCriteria();

        setAllFilters(dMsgheaderRowCriteria);

        assertThat(dMsgheaderRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dMsgheaderRowCriteriaCopyCreatesNullFilterTest() {
        var dMsgheaderRowCriteria = new DMsgheaderRowCriteria();
        var copy = dMsgheaderRowCriteria.copy();

        assertThat(dMsgheaderRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dMsgheaderRowCriteria)
        );
    }

    @Test
    void dMsgheaderRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dMsgheaderRowCriteria = new DMsgheaderRowCriteria();
        setAllFilters(dMsgheaderRowCriteria);

        var copy = dMsgheaderRowCriteria.copy();

        assertThat(dMsgheaderRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dMsgheaderRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dMsgheaderRowCriteria = new DMsgheaderRowCriteria();

        assertThat(dMsgheaderRowCriteria).hasToString("DMsgheaderRowCriteria{}");
    }

    private static void setAllFilters(DMsgheaderRowCriteria dMsgheaderRowCriteria) {
        dMsgheaderRowCriteria.id();
        dMsgheaderRowCriteria.mhKscreated();
        dMsgheaderRowCriteria.mhRegnom();
        dMsgheaderRowCriteria.mhVfsrefno();
        dMsgheaderRowCriteria.mhUsera();
        dMsgheaderRowCriteria.mhDatvav();
        dMsgheaderRowCriteria.distinct();
    }

    private static Condition<DMsgheaderRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getMhKscreated()) &&
                condition.apply(criteria.getMhRegnom()) &&
                condition.apply(criteria.getMhVfsrefno()) &&
                condition.apply(criteria.getMhUsera()) &&
                condition.apply(criteria.getMhDatvav()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DMsgheaderRowCriteria> copyFiltersAre(
        DMsgheaderRowCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getMhKscreated(), copy.getMhKscreated()) &&
                condition.apply(criteria.getMhRegnom(), copy.getMhRegnom()) &&
                condition.apply(criteria.getMhVfsrefno(), copy.getMhVfsrefno()) &&
                condition.apply(criteria.getMhUsera(), copy.getMhUsera()) &&
                condition.apply(criteria.getMhDatvav(), copy.getMhDatvav()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
