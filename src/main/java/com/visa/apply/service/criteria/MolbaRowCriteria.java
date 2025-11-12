package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.MolbaRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.MolbaRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /molba-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MolbaRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter datVli;

    private LocalDateFilter datIzl;

    private StringFilter vidvis;

    private IntegerFilter brvl;

    private StringFilter vidus;

    private StringFilter valvis;

    private IntegerFilter brdni;

    private IntegerFilter cel;

    private InstantFilter molDatVav;

    private StringFilter gratis;

    private StringFilter imavisa;

    private IntegerFilter cenamol;

    private StringFilter cenacurr;

    private StringFilter maindest;

    private StringFilter maindestnm;

    private StringFilter gkppDarj;

    private StringFilter gkppText;

    private StringFilter textIni;

    private Boolean distinct;

    public MolbaRowCriteria() {}

    public MolbaRowCriteria(MolbaRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.datVli = other.optionalDatVli().map(LocalDateFilter::copy).orElse(null);
        this.datIzl = other.optionalDatIzl().map(LocalDateFilter::copy).orElse(null);
        this.vidvis = other.optionalVidvis().map(StringFilter::copy).orElse(null);
        this.brvl = other.optionalBrvl().map(IntegerFilter::copy).orElse(null);
        this.vidus = other.optionalVidus().map(StringFilter::copy).orElse(null);
        this.valvis = other.optionalValvis().map(StringFilter::copy).orElse(null);
        this.brdni = other.optionalBrdni().map(IntegerFilter::copy).orElse(null);
        this.cel = other.optionalCel().map(IntegerFilter::copy).orElse(null);
        this.molDatVav = other.optionalMolDatVav().map(InstantFilter::copy).orElse(null);
        this.gratis = other.optionalGratis().map(StringFilter::copy).orElse(null);
        this.imavisa = other.optionalImavisa().map(StringFilter::copy).orElse(null);
        this.cenamol = other.optionalCenamol().map(IntegerFilter::copy).orElse(null);
        this.cenacurr = other.optionalCenacurr().map(StringFilter::copy).orElse(null);
        this.maindest = other.optionalMaindest().map(StringFilter::copy).orElse(null);
        this.maindestnm = other.optionalMaindestnm().map(StringFilter::copy).orElse(null);
        this.gkppDarj = other.optionalGkppDarj().map(StringFilter::copy).orElse(null);
        this.gkppText = other.optionalGkppText().map(StringFilter::copy).orElse(null);
        this.textIni = other.optionalTextIni().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public MolbaRowCriteria copy() {
        return new MolbaRowCriteria(this);
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

    public LocalDateFilter getDatVli() {
        return datVli;
    }

    public Optional<LocalDateFilter> optionalDatVli() {
        return Optional.ofNullable(datVli);
    }

    public LocalDateFilter datVli() {
        if (datVli == null) {
            setDatVli(new LocalDateFilter());
        }
        return datVli;
    }

    public void setDatVli(LocalDateFilter datVli) {
        this.datVli = datVli;
    }

    public LocalDateFilter getDatIzl() {
        return datIzl;
    }

    public Optional<LocalDateFilter> optionalDatIzl() {
        return Optional.ofNullable(datIzl);
    }

    public LocalDateFilter datIzl() {
        if (datIzl == null) {
            setDatIzl(new LocalDateFilter());
        }
        return datIzl;
    }

    public void setDatIzl(LocalDateFilter datIzl) {
        this.datIzl = datIzl;
    }

    public StringFilter getVidvis() {
        return vidvis;
    }

    public Optional<StringFilter> optionalVidvis() {
        return Optional.ofNullable(vidvis);
    }

    public StringFilter vidvis() {
        if (vidvis == null) {
            setVidvis(new StringFilter());
        }
        return vidvis;
    }

    public void setVidvis(StringFilter vidvis) {
        this.vidvis = vidvis;
    }

    public IntegerFilter getBrvl() {
        return brvl;
    }

    public Optional<IntegerFilter> optionalBrvl() {
        return Optional.ofNullable(brvl);
    }

    public IntegerFilter brvl() {
        if (brvl == null) {
            setBrvl(new IntegerFilter());
        }
        return brvl;
    }

    public void setBrvl(IntegerFilter brvl) {
        this.brvl = brvl;
    }

    public StringFilter getVidus() {
        return vidus;
    }

    public Optional<StringFilter> optionalVidus() {
        return Optional.ofNullable(vidus);
    }

    public StringFilter vidus() {
        if (vidus == null) {
            setVidus(new StringFilter());
        }
        return vidus;
    }

    public void setVidus(StringFilter vidus) {
        this.vidus = vidus;
    }

    public StringFilter getValvis() {
        return valvis;
    }

    public Optional<StringFilter> optionalValvis() {
        return Optional.ofNullable(valvis);
    }

    public StringFilter valvis() {
        if (valvis == null) {
            setValvis(new StringFilter());
        }
        return valvis;
    }

    public void setValvis(StringFilter valvis) {
        this.valvis = valvis;
    }

    public IntegerFilter getBrdni() {
        return brdni;
    }

    public Optional<IntegerFilter> optionalBrdni() {
        return Optional.ofNullable(brdni);
    }

    public IntegerFilter brdni() {
        if (brdni == null) {
            setBrdni(new IntegerFilter());
        }
        return brdni;
    }

    public void setBrdni(IntegerFilter brdni) {
        this.brdni = brdni;
    }

    public IntegerFilter getCel() {
        return cel;
    }

    public Optional<IntegerFilter> optionalCel() {
        return Optional.ofNullable(cel);
    }

    public IntegerFilter cel() {
        if (cel == null) {
            setCel(new IntegerFilter());
        }
        return cel;
    }

    public void setCel(IntegerFilter cel) {
        this.cel = cel;
    }

    public InstantFilter getMolDatVav() {
        return molDatVav;
    }

    public Optional<InstantFilter> optionalMolDatVav() {
        return Optional.ofNullable(molDatVav);
    }

    public InstantFilter molDatVav() {
        if (molDatVav == null) {
            setMolDatVav(new InstantFilter());
        }
        return molDatVav;
    }

    public void setMolDatVav(InstantFilter molDatVav) {
        this.molDatVav = molDatVav;
    }

    public StringFilter getGratis() {
        return gratis;
    }

    public Optional<StringFilter> optionalGratis() {
        return Optional.ofNullable(gratis);
    }

    public StringFilter gratis() {
        if (gratis == null) {
            setGratis(new StringFilter());
        }
        return gratis;
    }

    public void setGratis(StringFilter gratis) {
        this.gratis = gratis;
    }

    public StringFilter getImavisa() {
        return imavisa;
    }

    public Optional<StringFilter> optionalImavisa() {
        return Optional.ofNullable(imavisa);
    }

    public StringFilter imavisa() {
        if (imavisa == null) {
            setImavisa(new StringFilter());
        }
        return imavisa;
    }

    public void setImavisa(StringFilter imavisa) {
        this.imavisa = imavisa;
    }

    public IntegerFilter getCenamol() {
        return cenamol;
    }

    public Optional<IntegerFilter> optionalCenamol() {
        return Optional.ofNullable(cenamol);
    }

    public IntegerFilter cenamol() {
        if (cenamol == null) {
            setCenamol(new IntegerFilter());
        }
        return cenamol;
    }

    public void setCenamol(IntegerFilter cenamol) {
        this.cenamol = cenamol;
    }

    public StringFilter getCenacurr() {
        return cenacurr;
    }

    public Optional<StringFilter> optionalCenacurr() {
        return Optional.ofNullable(cenacurr);
    }

    public StringFilter cenacurr() {
        if (cenacurr == null) {
            setCenacurr(new StringFilter());
        }
        return cenacurr;
    }

    public void setCenacurr(StringFilter cenacurr) {
        this.cenacurr = cenacurr;
    }

    public StringFilter getMaindest() {
        return maindest;
    }

    public Optional<StringFilter> optionalMaindest() {
        return Optional.ofNullable(maindest);
    }

    public StringFilter maindest() {
        if (maindest == null) {
            setMaindest(new StringFilter());
        }
        return maindest;
    }

    public void setMaindest(StringFilter maindest) {
        this.maindest = maindest;
    }

    public StringFilter getMaindestnm() {
        return maindestnm;
    }

    public Optional<StringFilter> optionalMaindestnm() {
        return Optional.ofNullable(maindestnm);
    }

    public StringFilter maindestnm() {
        if (maindestnm == null) {
            setMaindestnm(new StringFilter());
        }
        return maindestnm;
    }

    public void setMaindestnm(StringFilter maindestnm) {
        this.maindestnm = maindestnm;
    }

    public StringFilter getGkppDarj() {
        return gkppDarj;
    }

    public Optional<StringFilter> optionalGkppDarj() {
        return Optional.ofNullable(gkppDarj);
    }

    public StringFilter gkppDarj() {
        if (gkppDarj == null) {
            setGkppDarj(new StringFilter());
        }
        return gkppDarj;
    }

    public void setGkppDarj(StringFilter gkppDarj) {
        this.gkppDarj = gkppDarj;
    }

    public StringFilter getGkppText() {
        return gkppText;
    }

    public Optional<StringFilter> optionalGkppText() {
        return Optional.ofNullable(gkppText);
    }

    public StringFilter gkppText() {
        if (gkppText == null) {
            setGkppText(new StringFilter());
        }
        return gkppText;
    }

    public void setGkppText(StringFilter gkppText) {
        this.gkppText = gkppText;
    }

    public StringFilter getTextIni() {
        return textIni;
    }

    public Optional<StringFilter> optionalTextIni() {
        return Optional.ofNullable(textIni);
    }

    public StringFilter textIni() {
        if (textIni == null) {
            setTextIni(new StringFilter());
        }
        return textIni;
    }

    public void setTextIni(StringFilter textIni) {
        this.textIni = textIni;
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
        final MolbaRowCriteria that = (MolbaRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(datVli, that.datVli) &&
            Objects.equals(datIzl, that.datIzl) &&
            Objects.equals(vidvis, that.vidvis) &&
            Objects.equals(brvl, that.brvl) &&
            Objects.equals(vidus, that.vidus) &&
            Objects.equals(valvis, that.valvis) &&
            Objects.equals(brdni, that.brdni) &&
            Objects.equals(cel, that.cel) &&
            Objects.equals(molDatVav, that.molDatVav) &&
            Objects.equals(gratis, that.gratis) &&
            Objects.equals(imavisa, that.imavisa) &&
            Objects.equals(cenamol, that.cenamol) &&
            Objects.equals(cenacurr, that.cenacurr) &&
            Objects.equals(maindest, that.maindest) &&
            Objects.equals(maindestnm, that.maindestnm) &&
            Objects.equals(gkppDarj, that.gkppDarj) &&
            Objects.equals(gkppText, that.gkppText) &&
            Objects.equals(textIni, that.textIni) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            datVli,
            datIzl,
            vidvis,
            brvl,
            vidus,
            valvis,
            brdni,
            cel,
            molDatVav,
            gratis,
            imavisa,
            cenamol,
            cenacurr,
            maindest,
            maindestnm,
            gkppDarj,
            gkppText,
            textIni,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MolbaRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalDatVli().map(f -> "datVli=" + f + ", ").orElse("") +
            optionalDatIzl().map(f -> "datIzl=" + f + ", ").orElse("") +
            optionalVidvis().map(f -> "vidvis=" + f + ", ").orElse("") +
            optionalBrvl().map(f -> "brvl=" + f + ", ").orElse("") +
            optionalVidus().map(f -> "vidus=" + f + ", ").orElse("") +
            optionalValvis().map(f -> "valvis=" + f + ", ").orElse("") +
            optionalBrdni().map(f -> "brdni=" + f + ", ").orElse("") +
            optionalCel().map(f -> "cel=" + f + ", ").orElse("") +
            optionalMolDatVav().map(f -> "molDatVav=" + f + ", ").orElse("") +
            optionalGratis().map(f -> "gratis=" + f + ", ").orElse("") +
            optionalImavisa().map(f -> "imavisa=" + f + ", ").orElse("") +
            optionalCenamol().map(f -> "cenamol=" + f + ", ").orElse("") +
            optionalCenacurr().map(f -> "cenacurr=" + f + ", ").orElse("") +
            optionalMaindest().map(f -> "maindest=" + f + ", ").orElse("") +
            optionalMaindestnm().map(f -> "maindestnm=" + f + ", ").orElse("") +
            optionalGkppDarj().map(f -> "gkppDarj=" + f + ", ").orElse("") +
            optionalGkppText().map(f -> "gkppText=" + f + ", ").orElse("") +
            optionalTextIni().map(f -> "textIni=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
