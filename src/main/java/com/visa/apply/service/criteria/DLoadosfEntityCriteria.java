package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DLoadosfEntity} entity. This class is used
 * in {@link com.visa.apply.web.rest.DLoadosfEntityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-loadosf-entities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLoadosfEntityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter version;

    private LongFilter msgheaderId;

    private LongFilter lcuzId;

    private LongFilter lcdopId;

    private LongFilter bastaEntityId;

    private LongFilter maikaId;

    private LongFilter saprugaId;

    private LongFilter molbaId;

    private LongFilter domakinId;

    private LongFilter eurodaId;

    private LongFilter voitId;

    private LongFilter imagesId;

    private LongFilter fingersId;

    private Boolean distinct;

    public DLoadosfEntityCriteria() {}

    public DLoadosfEntityCriteria(DLoadosfEntityCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.version = other.optionalVersion().map(DoubleFilter::copy).orElse(null);
        this.msgheaderId = other.optionalMsgheaderId().map(LongFilter::copy).orElse(null);
        this.lcuzId = other.optionalLcuzId().map(LongFilter::copy).orElse(null);
        this.lcdopId = other.optionalLcdopId().map(LongFilter::copy).orElse(null);
        this.bastaEntityId = other.optionalBastaEntityId().map(LongFilter::copy).orElse(null);
        this.maikaId = other.optionalMaikaId().map(LongFilter::copy).orElse(null);
        this.saprugaId = other.optionalSaprugaId().map(LongFilter::copy).orElse(null);
        this.molbaId = other.optionalMolbaId().map(LongFilter::copy).orElse(null);
        this.domakinId = other.optionalDomakinId().map(LongFilter::copy).orElse(null);
        this.eurodaId = other.optionalEurodaId().map(LongFilter::copy).orElse(null);
        this.voitId = other.optionalVoitId().map(LongFilter::copy).orElse(null);
        this.imagesId = other.optionalImagesId().map(LongFilter::copy).orElse(null);
        this.fingersId = other.optionalFingersId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DLoadosfEntityCriteria copy() {
        return new DLoadosfEntityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getVersion() {
        return version;
    }

    public Optional<DoubleFilter> optionalVersion() {
        return Optional.ofNullable(version);
    }

    public DoubleFilter version() {
        if (version == null) {
            setVersion(new DoubleFilter());
        }
        return version;
    }

    public void setVersion(DoubleFilter version) {
        this.version = version;
    }

    public LongFilter getMsgheaderId() {
        return msgheaderId;
    }

    public Optional<LongFilter> optionalMsgheaderId() {
        return Optional.ofNullable(msgheaderId);
    }

    public LongFilter msgheaderId() {
        if (msgheaderId == null) {
            setMsgheaderId(new LongFilter());
        }
        return msgheaderId;
    }

    public void setMsgheaderId(LongFilter msgheaderId) {
        this.msgheaderId = msgheaderId;
    }

    public LongFilter getLcuzId() {
        return lcuzId;
    }

    public Optional<LongFilter> optionalLcuzId() {
        return Optional.ofNullable(lcuzId);
    }

    public LongFilter lcuzId() {
        if (lcuzId == null) {
            setLcuzId(new LongFilter());
        }
        return lcuzId;
    }

    public void setLcuzId(LongFilter lcuzId) {
        this.lcuzId = lcuzId;
    }

    public LongFilter getLcdopId() {
        return lcdopId;
    }

    public Optional<LongFilter> optionalLcdopId() {
        return Optional.ofNullable(lcdopId);
    }

    public LongFilter lcdopId() {
        if (lcdopId == null) {
            setLcdopId(new LongFilter());
        }
        return lcdopId;
    }

    public void setLcdopId(LongFilter lcdopId) {
        this.lcdopId = lcdopId;
    }

    public LongFilter getBastaEntityId() {
        return bastaEntityId;
    }

    public Optional<LongFilter> optionalBastaEntityId() {
        return Optional.ofNullable(bastaEntityId);
    }

    public LongFilter bastaEntityId() {
        if (bastaEntityId == null) {
            setBastaEntityId(new LongFilter());
        }
        return bastaEntityId;
    }

    public void setBastaEntityId(LongFilter bastaEntityId) {
        this.bastaEntityId = bastaEntityId;
    }

    public LongFilter getMaikaId() {
        return maikaId;
    }

    public Optional<LongFilter> optionalMaikaId() {
        return Optional.ofNullable(maikaId);
    }

    public LongFilter maikaId() {
        if (maikaId == null) {
            setMaikaId(new LongFilter());
        }
        return maikaId;
    }

    public void setMaikaId(LongFilter maikaId) {
        this.maikaId = maikaId;
    }

    public LongFilter getSaprugaId() {
        return saprugaId;
    }

    public Optional<LongFilter> optionalSaprugaId() {
        return Optional.ofNullable(saprugaId);
    }

    public LongFilter saprugaId() {
        if (saprugaId == null) {
            setSaprugaId(new LongFilter());
        }
        return saprugaId;
    }

    public void setSaprugaId(LongFilter saprugaId) {
        this.saprugaId = saprugaId;
    }

    public LongFilter getMolbaId() {
        return molbaId;
    }

    public Optional<LongFilter> optionalMolbaId() {
        return Optional.ofNullable(molbaId);
    }

    public LongFilter molbaId() {
        if (molbaId == null) {
            setMolbaId(new LongFilter());
        }
        return molbaId;
    }

    public void setMolbaId(LongFilter molbaId) {
        this.molbaId = molbaId;
    }

    public LongFilter getDomakinId() {
        return domakinId;
    }

    public Optional<LongFilter> optionalDomakinId() {
        return Optional.ofNullable(domakinId);
    }

    public LongFilter domakinId() {
        if (domakinId == null) {
            setDomakinId(new LongFilter());
        }
        return domakinId;
    }

    public void setDomakinId(LongFilter domakinId) {
        this.domakinId = domakinId;
    }

    public LongFilter getEurodaId() {
        return eurodaId;
    }

    public Optional<LongFilter> optionalEurodaId() {
        return Optional.ofNullable(eurodaId);
    }

    public LongFilter eurodaId() {
        if (eurodaId == null) {
            setEurodaId(new LongFilter());
        }
        return eurodaId;
    }

    public void setEurodaId(LongFilter eurodaId) {
        this.eurodaId = eurodaId;
    }

    public LongFilter getVoitId() {
        return voitId;
    }

    public Optional<LongFilter> optionalVoitId() {
        return Optional.ofNullable(voitId);
    }

    public LongFilter voitId() {
        if (voitId == null) {
            setVoitId(new LongFilter());
        }
        return voitId;
    }

    public void setVoitId(LongFilter voitId) {
        this.voitId = voitId;
    }

    public LongFilter getImagesId() {
        return imagesId;
    }

    public Optional<LongFilter> optionalImagesId() {
        return Optional.ofNullable(imagesId);
    }

    public LongFilter imagesId() {
        if (imagesId == null) {
            setImagesId(new LongFilter());
        }
        return imagesId;
    }

    public void setImagesId(LongFilter imagesId) {
        this.imagesId = imagesId;
    }

    public LongFilter getFingersId() {
        return fingersId;
    }

    public Optional<LongFilter> optionalFingersId() {
        return Optional.ofNullable(fingersId);
    }

    public LongFilter fingersId() {
        if (fingersId == null) {
            setFingersId(new LongFilter());
        }
        return fingersId;
    }

    public void setFingersId(LongFilter fingersId) {
        this.fingersId = fingersId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DLoadosfEntityCriteria that = (DLoadosfEntityCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(version, that.version) &&
            Objects.equals(msgheaderId, that.msgheaderId) &&
            Objects.equals(lcuzId, that.lcuzId) &&
            Objects.equals(lcdopId, that.lcdopId) &&
            Objects.equals(bastaEntityId, that.bastaEntityId) &&
            Objects.equals(maikaId, that.maikaId) &&
            Objects.equals(saprugaId, that.saprugaId) &&
            Objects.equals(molbaId, that.molbaId) &&
            Objects.equals(domakinId, that.domakinId) &&
            Objects.equals(eurodaId, that.eurodaId) &&
            Objects.equals(voitId, that.voitId) &&
            Objects.equals(imagesId, that.imagesId) &&
            Objects.equals(fingersId, that.fingersId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            version,
            msgheaderId,
            lcuzId,
            lcdopId,
            bastaEntityId,
            maikaId,
            saprugaId,
            molbaId,
            domakinId,
            eurodaId,
            voitId,
            imagesId,
            fingersId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLoadosfEntityCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalVersion().map(f -> "version=" + f + ", ").orElse("") +
            optionalMsgheaderId().map(f -> "msgheaderId=" + f + ", ").orElse("") +
            optionalLcuzId().map(f -> "lcuzId=" + f + ", ").orElse("") +
            optionalLcdopId().map(f -> "lcdopId=" + f + ", ").orElse("") +
            optionalBastaEntityId().map(f -> "bastaEntityId=" + f + ", ").orElse("") +
            optionalMaikaId().map(f -> "maikaId=" + f + ", ").orElse("") +
            optionalSaprugaId().map(f -> "saprugaId=" + f + ", ").orElse("") +
            optionalMolbaId().map(f -> "molbaId=" + f + ", ").orElse("") +
            optionalDomakinId().map(f -> "domakinId=" + f + ", ").orElse("") +
            optionalEurodaId().map(f -> "eurodaId=" + f + ", ").orElse("") +
            optionalVoitId().map(f -> "voitId=" + f + ", ").orElse("") +
            optionalImagesId().map(f -> "imagesId=" + f + ", ").orElse("") +
            optionalFingersId().map(f -> "fingersId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
