package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DImagesRowCriteriaTest {

    @Test
    void newDImagesRowCriteriaHasAllFiltersNullTest() {
        var dImagesRowCriteria = new DImagesRowCriteria();
        assertThat(dImagesRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dImagesRowCriteriaFluentMethodsCreatesFiltersTest() {
        var dImagesRowCriteria = new DImagesRowCriteria();

        setAllFilters(dImagesRowCriteria);

        assertThat(dImagesRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dImagesRowCriteriaCopyCreatesNullFilterTest() {
        var dImagesRowCriteria = new DImagesRowCriteria();
        var copy = dImagesRowCriteria.copy();

        assertThat(dImagesRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dImagesRowCriteria)
        );
    }

    @Test
    void dImagesRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dImagesRowCriteria = new DImagesRowCriteria();
        setAllFilters(dImagesRowCriteria);

        var copy = dImagesRowCriteria.copy();

        assertThat(dImagesRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dImagesRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dImagesRowCriteria = new DImagesRowCriteria();

        assertThat(dImagesRowCriteria).hasToString("DImagesRowCriteria{}");
    }

    private static void setAllFilters(DImagesRowCriteria dImagesRowCriteria) {
        dImagesRowCriteria.id();
        dImagesRowCriteria.imDevicetype();
        dImagesRowCriteria.imWidth();
        dImagesRowCriteria.imHeight();
        dImagesRowCriteria.imImglen();
        dImagesRowCriteria.imImage();
        dImagesRowCriteria.distinct();
    }

    private static Condition<DImagesRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getImDevicetype()) &&
                condition.apply(criteria.getImWidth()) &&
                condition.apply(criteria.getImHeight()) &&
                condition.apply(criteria.getImImglen()) &&
                condition.apply(criteria.getImImage()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DImagesRowCriteria> copyFiltersAre(DImagesRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getImDevicetype(), copy.getImDevicetype()) &&
                condition.apply(criteria.getImWidth(), copy.getImWidth()) &&
                condition.apply(criteria.getImHeight(), copy.getImHeight()) &&
                condition.apply(criteria.getImImglen(), copy.getImImglen()) &&
                condition.apply(criteria.getImImage(), copy.getImImage()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
