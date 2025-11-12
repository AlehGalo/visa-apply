package com.visa.apply.service.mapper;

import com.visa.apply.domain.DBastaRow;
import com.visa.apply.domain.DDomakinRow;
import com.visa.apply.domain.DEurodaRow;
import com.visa.apply.domain.DFingersRow;
import com.visa.apply.domain.DImagesRow;
import com.visa.apply.domain.DLcdopRow;
import com.visa.apply.domain.DLcuzRow;
import com.visa.apply.domain.DLoadosfEntity;
import com.visa.apply.domain.DMaikaRow;
import com.visa.apply.domain.DMsgheaderRow;
import com.visa.apply.domain.DSaprugaRow;
import com.visa.apply.domain.DVoitRow;
import com.visa.apply.domain.MolbaRow;
import com.visa.apply.service.dto.DBastaRowDTO;
import com.visa.apply.service.dto.DDomakinRowDTO;
import com.visa.apply.service.dto.DEurodaRowDTO;
import com.visa.apply.service.dto.DFingersRowDTO;
import com.visa.apply.service.dto.DImagesRowDTO;
import com.visa.apply.service.dto.DLcdopRowDTO;
import com.visa.apply.service.dto.DLcuzRowDTO;
import com.visa.apply.service.dto.DLoadosfEntityDTO;
import com.visa.apply.service.dto.DMaikaRowDTO;
import com.visa.apply.service.dto.DMsgheaderRowDTO;
import com.visa.apply.service.dto.DSaprugaRowDTO;
import com.visa.apply.service.dto.DVoitRowDTO;
import com.visa.apply.service.dto.MolbaRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DLoadosfEntity} and its DTO {@link DLoadosfEntityDTO}.
 */
@Mapper(componentModel = "spring")
public interface DLoadosfEntityMapper extends EntityMapper<DLoadosfEntityDTO, DLoadosfEntity> {
    @Mapping(target = "msgheader", source = "msgheader", qualifiedByName = "dMsgheaderRowId")
    @Mapping(target = "lcuz", source = "lcuz", qualifiedByName = "dLcuzRowId")
    @Mapping(target = "lcdop", source = "lcdop", qualifiedByName = "dLcdopRowId")
    @Mapping(target = "bastaEntity", source = "bastaEntity", qualifiedByName = "dBastaRowId")
    @Mapping(target = "maika", source = "maika", qualifiedByName = "dMaikaRowId")
    @Mapping(target = "sapruga", source = "sapruga", qualifiedByName = "dSaprugaRowId")
    @Mapping(target = "molba", source = "molba", qualifiedByName = "molbaRowId")
    @Mapping(target = "domakin", source = "domakin", qualifiedByName = "dDomakinRowId")
    @Mapping(target = "euroda", source = "euroda", qualifiedByName = "dEurodaRowId")
    @Mapping(target = "voit", source = "voit", qualifiedByName = "dVoitRowId")
    @Mapping(target = "images", source = "images", qualifiedByName = "dImagesRowId")
    @Mapping(target = "fingers", source = "fingers", qualifiedByName = "dFingersRowId")
    DLoadosfEntityDTO toDto(DLoadosfEntity s);

    @Named("dMsgheaderRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DMsgheaderRowDTO toDtoDMsgheaderRowId(DMsgheaderRow dMsgheaderRow);

    @Named("dLcuzRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DLcuzRowDTO toDtoDLcuzRowId(DLcuzRow dLcuzRow);

    @Named("dLcdopRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DLcdopRowDTO toDtoDLcdopRowId(DLcdopRow dLcdopRow);

    @Named("dBastaRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DBastaRowDTO toDtoDBastaRowId(DBastaRow dBastaRow);

    @Named("dMaikaRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DMaikaRowDTO toDtoDMaikaRowId(DMaikaRow dMaikaRow);

    @Named("dSaprugaRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DSaprugaRowDTO toDtoDSaprugaRowId(DSaprugaRow dSaprugaRow);

    @Named("molbaRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MolbaRowDTO toDtoMolbaRowId(MolbaRow molbaRow);

    @Named("dDomakinRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DDomakinRowDTO toDtoDDomakinRowId(DDomakinRow dDomakinRow);

    @Named("dEurodaRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DEurodaRowDTO toDtoDEurodaRowId(DEurodaRow dEurodaRow);

    @Named("dVoitRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DVoitRowDTO toDtoDVoitRowId(DVoitRow dVoitRow);

    @Named("dImagesRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DImagesRowDTO toDtoDImagesRowId(DImagesRow dImagesRow);

    @Named("dFingersRowId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DFingersRowDTO toDtoDFingersRowId(DFingersRow dFingersRow);
}
