package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DLoadosfEntityCriteriaTest {

    @Test
    void newDLoadosfEntityCriteriaHasAllFiltersNullTest() {
        var dLoadosfEntityCriteria = new DLoadosfEntityCriteria();
        assertThat(dLoadosfEntityCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void dLoadosfEntityCriteriaFluentMethodsCreatesFiltersTest() {
        var dLoadosfEntityCriteria = new DLoadosfEntityCriteria();

        setAllFilters(dLoadosfEntityCriteria);

        assertThat(dLoadosfEntityCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void dLoadosfEntityCriteriaCopyCreatesNullFilterTest() {
        var dLoadosfEntityCriteria = new DLoadosfEntityCriteria();
        var copy = dLoadosfEntityCriteria.copy();

        assertThat(dLoadosfEntityCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(dLoadosfEntityCriteria)
        );
    }

    @Test
    void dLoadosfEntityCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dLoadosfEntityCriteria = new DLoadosfEntityCriteria();
        setAllFilters(dLoadosfEntityCriteria);

        var copy = dLoadosfEntityCriteria.copy();

        assertThat(dLoadosfEntityCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(dLoadosfEntityCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dLoadosfEntityCriteria = new DLoadosfEntityCriteria();

        assertThat(dLoadosfEntityCriteria).hasToString("DLoadosfEntityCriteria{}");
    }

    private static void setAllFilters(DLoadosfEntityCriteria dLoadosfEntityCriteria) {
        dLoadosfEntityCriteria.id();
        dLoadosfEntityCriteria.version();
        dLoadosfEntityCriteria.msgheaderId();
        dLoadosfEntityCriteria.lcuzId();
        dLoadosfEntityCriteria.lcdopId();
        dLoadosfEntityCriteria.bastaEntityId();
        dLoadosfEntityCriteria.maikaId();
        dLoadosfEntityCriteria.saprugaId();
        dLoadosfEntityCriteria.molbaId();
        dLoadosfEntityCriteria.domakinId();
        dLoadosfEntityCriteria.eurodaId();
        dLoadosfEntityCriteria.voitId();
        dLoadosfEntityCriteria.imagesId();
        dLoadosfEntityCriteria.fingersId();
        dLoadosfEntityCriteria.distinct();
    }

    private static Condition<DLoadosfEntityCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getVersion()) &&
                condition.apply(criteria.getMsgheaderId()) &&
                condition.apply(criteria.getLcuzId()) &&
                condition.apply(criteria.getLcdopId()) &&
                condition.apply(criteria.getBastaEntityId()) &&
                condition.apply(criteria.getMaikaId()) &&
                condition.apply(criteria.getSaprugaId()) &&
                condition.apply(criteria.getMolbaId()) &&
                condition.apply(criteria.getDomakinId()) &&
                condition.apply(criteria.getEurodaId()) &&
                condition.apply(criteria.getVoitId()) &&
                condition.apply(criteria.getImagesId()) &&
                condition.apply(criteria.getFingersId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DLoadosfEntityCriteria> copyFiltersAre(
        DLoadosfEntityCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getVersion(), copy.getVersion()) &&
                condition.apply(criteria.getMsgheaderId(), copy.getMsgheaderId()) &&
                condition.apply(criteria.getLcuzId(), copy.getLcuzId()) &&
                condition.apply(criteria.getLcdopId(), copy.getLcdopId()) &&
                condition.apply(criteria.getBastaEntityId(), copy.getBastaEntityId()) &&
                condition.apply(criteria.getMaikaId(), copy.getMaikaId()) &&
                condition.apply(criteria.getSaprugaId(), copy.getSaprugaId()) &&
                condition.apply(criteria.getMolbaId(), copy.getMolbaId()) &&
                condition.apply(criteria.getDomakinId(), copy.getDomakinId()) &&
                condition.apply(criteria.getEurodaId(), copy.getEurodaId()) &&
                condition.apply(criteria.getVoitId(), copy.getVoitId()) &&
                condition.apply(criteria.getImagesId(), copy.getImagesId()) &&
                condition.apply(criteria.getFingersId(), copy.getFingersId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
