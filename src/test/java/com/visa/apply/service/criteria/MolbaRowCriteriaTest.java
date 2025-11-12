package com.visa.apply.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class MolbaRowCriteriaTest {

    @Test
    void newMolbaRowCriteriaHasAllFiltersNullTest() {
        var molbaRowCriteria = new MolbaRowCriteria();
        assertThat(molbaRowCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void molbaRowCriteriaFluentMethodsCreatesFiltersTest() {
        var molbaRowCriteria = new MolbaRowCriteria();

        setAllFilters(molbaRowCriteria);

        assertThat(molbaRowCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void molbaRowCriteriaCopyCreatesNullFilterTest() {
        var molbaRowCriteria = new MolbaRowCriteria();
        var copy = molbaRowCriteria.copy();

        assertThat(molbaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(molbaRowCriteria)
        );
    }

    @Test
    void molbaRowCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var molbaRowCriteria = new MolbaRowCriteria();
        setAllFilters(molbaRowCriteria);

        var copy = molbaRowCriteria.copy();

        assertThat(molbaRowCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(molbaRowCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var molbaRowCriteria = new MolbaRowCriteria();

        assertThat(molbaRowCriteria).hasToString("MolbaRowCriteria{}");
    }

    private static void setAllFilters(MolbaRowCriteria molbaRowCriteria) {
        molbaRowCriteria.id();
        molbaRowCriteria.datVli();
        molbaRowCriteria.datIzl();
        molbaRowCriteria.vidvis();
        molbaRowCriteria.brvl();
        molbaRowCriteria.vidus();
        molbaRowCriteria.valvis();
        molbaRowCriteria.brdni();
        molbaRowCriteria.cel();
        molbaRowCriteria.molDatVav();
        molbaRowCriteria.gratis();
        molbaRowCriteria.imavisa();
        molbaRowCriteria.cenamol();
        molbaRowCriteria.cenacurr();
        molbaRowCriteria.maindest();
        molbaRowCriteria.maindestnm();
        molbaRowCriteria.gkppDarj();
        molbaRowCriteria.gkppText();
        molbaRowCriteria.textIni();
        molbaRowCriteria.distinct();
    }

    private static Condition<MolbaRowCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getDatVli()) &&
                condition.apply(criteria.getDatIzl()) &&
                condition.apply(criteria.getVidvis()) &&
                condition.apply(criteria.getBrvl()) &&
                condition.apply(criteria.getVidus()) &&
                condition.apply(criteria.getValvis()) &&
                condition.apply(criteria.getBrdni()) &&
                condition.apply(criteria.getCel()) &&
                condition.apply(criteria.getMolDatVav()) &&
                condition.apply(criteria.getGratis()) &&
                condition.apply(criteria.getImavisa()) &&
                condition.apply(criteria.getCenamol()) &&
                condition.apply(criteria.getCenacurr()) &&
                condition.apply(criteria.getMaindest()) &&
                condition.apply(criteria.getMaindestnm()) &&
                condition.apply(criteria.getGkppDarj()) &&
                condition.apply(criteria.getGkppText()) &&
                condition.apply(criteria.getTextIni()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<MolbaRowCriteria> copyFiltersAre(MolbaRowCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getDatVli(), copy.getDatVli()) &&
                condition.apply(criteria.getDatIzl(), copy.getDatIzl()) &&
                condition.apply(criteria.getVidvis(), copy.getVidvis()) &&
                condition.apply(criteria.getBrvl(), copy.getBrvl()) &&
                condition.apply(criteria.getVidus(), copy.getVidus()) &&
                condition.apply(criteria.getValvis(), copy.getValvis()) &&
                condition.apply(criteria.getBrdni(), copy.getBrdni()) &&
                condition.apply(criteria.getCel(), copy.getCel()) &&
                condition.apply(criteria.getMolDatVav(), copy.getMolDatVav()) &&
                condition.apply(criteria.getGratis(), copy.getGratis()) &&
                condition.apply(criteria.getImavisa(), copy.getImavisa()) &&
                condition.apply(criteria.getCenamol(), copy.getCenamol()) &&
                condition.apply(criteria.getCenacurr(), copy.getCenacurr()) &&
                condition.apply(criteria.getMaindest(), copy.getMaindest()) &&
                condition.apply(criteria.getMaindestnm(), copy.getMaindestnm()) &&
                condition.apply(criteria.getGkppDarj(), copy.getGkppDarj()) &&
                condition.apply(criteria.getGkppText(), copy.getGkppText()) &&
                condition.apply(criteria.getTextIni(), copy.getTextIni()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
