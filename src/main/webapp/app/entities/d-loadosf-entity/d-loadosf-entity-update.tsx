import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getDMsgheaderRows } from 'app/entities/d-msgheader-row/d-msgheader-row.reducer';
import { getEntities as getDLcuzRows } from 'app/entities/d-lcuz-row/d-lcuz-row.reducer';
import { getEntities as getDLcdopRows } from 'app/entities/d-lcdop-row/d-lcdop-row.reducer';
import { getEntities as getDBastaRows } from 'app/entities/d-basta-row/d-basta-row.reducer';
import { getEntities as getDMaikaRows } from 'app/entities/d-maika-row/d-maika-row.reducer';
import { getEntities as getDSaprugaRows } from 'app/entities/d-sapruga-row/d-sapruga-row.reducer';
import { getEntities as getMolbaRows } from 'app/entities/molba-row/molba-row.reducer';
import { getEntities as getDDomakinRows } from 'app/entities/d-domakin-row/d-domakin-row.reducer';
import { getEntities as getDEurodaRows } from 'app/entities/d-euroda-row/d-euroda-row.reducer';
import { getEntities as getDVoitRows } from 'app/entities/d-voit-row/d-voit-row.reducer';
import { getEntities as getDImagesRows } from 'app/entities/d-images-row/d-images-row.reducer';
import { getEntities as getDFingersRows } from 'app/entities/d-fingers-row/d-fingers-row.reducer';
import { createEntity, getEntity, reset, updateEntity } from './d-loadosf-entity.reducer';

export const DLoadosfEntityUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dMsgheaderRows = useAppSelector(state => state.dMsgheaderRow.entities);
  const dLcuzRows = useAppSelector(state => state.dLcuzRow.entities);
  const dLcdopRows = useAppSelector(state => state.dLcdopRow.entities);
  const dBastaRows = useAppSelector(state => state.dBastaRow.entities);
  const dMaikaRows = useAppSelector(state => state.dMaikaRow.entities);
  const dSaprugaRows = useAppSelector(state => state.dSaprugaRow.entities);
  const molbaRows = useAppSelector(state => state.molbaRow.entities);
  const dDomakinRows = useAppSelector(state => state.dDomakinRow.entities);
  const dEurodaRows = useAppSelector(state => state.dEurodaRow.entities);
  const dVoitRows = useAppSelector(state => state.dVoitRow.entities);
  const dImagesRows = useAppSelector(state => state.dImagesRow.entities);
  const dFingersRows = useAppSelector(state => state.dFingersRow.entities);
  const dLoadosfEntityEntity = useAppSelector(state => state.dLoadosfEntity.entity);
  const loading = useAppSelector(state => state.dLoadosfEntity.loading);
  const updating = useAppSelector(state => state.dLoadosfEntity.updating);
  const updateSuccess = useAppSelector(state => state.dLoadosfEntity.updateSuccess);

  const handleClose = () => {
    navigate(`/d-loadosf-entity${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDMsgheaderRows({}));
    dispatch(getDLcuzRows({}));
    dispatch(getDLcdopRows({}));
    dispatch(getDBastaRows({}));
    dispatch(getDMaikaRows({}));
    dispatch(getDSaprugaRows({}));
    dispatch(getMolbaRows({}));
    dispatch(getDDomakinRows({}));
    dispatch(getDEurodaRows({}));
    dispatch(getDVoitRows({}));
    dispatch(getDImagesRows({}));
    dispatch(getDFingersRows({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.version !== undefined && typeof values.version !== 'number') {
      values.version = Number(values.version);
    }

    const entity = {
      ...dLoadosfEntityEntity,
      ...values,
      msgheader: dMsgheaderRows.find(it => it.id.toString() === values.msgheader?.toString()),
      lcuz: dLcuzRows.find(it => it.id.toString() === values.lcuz?.toString()),
      lcdop: dLcdopRows.find(it => it.id.toString() === values.lcdop?.toString()),
      bastaEntity: dBastaRows.find(it => it.id.toString() === values.bastaEntity?.toString()),
      maika: dMaikaRows.find(it => it.id.toString() === values.maika?.toString()),
      sapruga: dSaprugaRows.find(it => it.id.toString() === values.sapruga?.toString()),
      molba: molbaRows.find(it => it.id.toString() === values.molba?.toString()),
      domakin: dDomakinRows.find(it => it.id.toString() === values.domakin?.toString()),
      euroda: dEurodaRows.find(it => it.id.toString() === values.euroda?.toString()),
      voit: dVoitRows.find(it => it.id.toString() === values.voit?.toString()),
      images: dImagesRows.find(it => it.id.toString() === values.images?.toString()),
      fingers: dFingersRows.find(it => it.id.toString() === values.fingers?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...dLoadosfEntityEntity,
          msgheader: dLoadosfEntityEntity?.msgheader?.id,
          lcuz: dLoadosfEntityEntity?.lcuz?.id,
          lcdop: dLoadosfEntityEntity?.lcdop?.id,
          bastaEntity: dLoadosfEntityEntity?.bastaEntity?.id,
          maika: dLoadosfEntityEntity?.maika?.id,
          sapruga: dLoadosfEntityEntity?.sapruga?.id,
          molba: dLoadosfEntityEntity?.molba?.id,
          domakin: dLoadosfEntityEntity?.domakin?.id,
          euroda: dLoadosfEntityEntity?.euroda?.id,
          voit: dLoadosfEntityEntity?.voit?.id,
          images: dLoadosfEntityEntity?.images?.id,
          fingers: dLoadosfEntityEntity?.fingers?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dLoadosfEntity.home.createOrEditLabel" data-cy="DLoadosfEntityCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dLoadosfEntity.home.createOrEditLabel">Create or edit a DLoadosfEntity</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="d-loadosf-entity-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dLoadosfEntity.version')}
                id="d-loadosf-entity-version"
                name="version"
                data-cy="version"
                type="text"
              />
              <ValidatedField
                id="d-loadosf-entity-msgheader"
                name="msgheader"
                data-cy="msgheader"
                label={translate('visaApplyApp.dLoadosfEntity.msgheader')}
                type="select"
              >
                <option value="" key="0" />
                {dMsgheaderRows
                  ? dMsgheaderRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-lcuz"
                name="lcuz"
                data-cy="lcuz"
                label={translate('visaApplyApp.dLoadosfEntity.lcuz')}
                type="select"
              >
                <option value="" key="0" />
                {dLcuzRows
                  ? dLcuzRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-lcdop"
                name="lcdop"
                data-cy="lcdop"
                label={translate('visaApplyApp.dLoadosfEntity.lcdop')}
                type="select"
              >
                <option value="" key="0" />
                {dLcdopRows
                  ? dLcdopRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-bastaEntity"
                name="bastaEntity"
                data-cy="bastaEntity"
                label={translate('visaApplyApp.dLoadosfEntity.bastaEntity')}
                type="select"
              >
                <option value="" key="0" />
                {dBastaRows
                  ? dBastaRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-maika"
                name="maika"
                data-cy="maika"
                label={translate('visaApplyApp.dLoadosfEntity.maika')}
                type="select"
              >
                <option value="" key="0" />
                {dMaikaRows
                  ? dMaikaRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-sapruga"
                name="sapruga"
                data-cy="sapruga"
                label={translate('visaApplyApp.dLoadosfEntity.sapruga')}
                type="select"
              >
                <option value="" key="0" />
                {dSaprugaRows
                  ? dSaprugaRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-molba"
                name="molba"
                data-cy="molba"
                label={translate('visaApplyApp.dLoadosfEntity.molba')}
                type="select"
              >
                <option value="" key="0" />
                {molbaRows
                  ? molbaRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-domakin"
                name="domakin"
                data-cy="domakin"
                label={translate('visaApplyApp.dLoadosfEntity.domakin')}
                type="select"
              >
                <option value="" key="0" />
                {dDomakinRows
                  ? dDomakinRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-euroda"
                name="euroda"
                data-cy="euroda"
                label={translate('visaApplyApp.dLoadosfEntity.euroda')}
                type="select"
              >
                <option value="" key="0" />
                {dEurodaRows
                  ? dEurodaRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-voit"
                name="voit"
                data-cy="voit"
                label={translate('visaApplyApp.dLoadosfEntity.voit')}
                type="select"
              >
                <option value="" key="0" />
                {dVoitRows
                  ? dVoitRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-images"
                name="images"
                data-cy="images"
                label={translate('visaApplyApp.dLoadosfEntity.images')}
                type="select"
              >
                <option value="" key="0" />
                {dImagesRows
                  ? dImagesRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-loadosf-entity-fingers"
                name="fingers"
                data-cy="fingers"
                label={translate('visaApplyApp.dLoadosfEntity.fingers')}
                type="select"
              >
                <option value="" key="0" />
                {dFingersRows
                  ? dFingersRows.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-loadosf-entity" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DLoadosfEntityUpdate;
