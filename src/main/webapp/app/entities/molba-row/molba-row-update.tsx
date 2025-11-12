import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './molba-row.reducer';

export const MolbaRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const molbaRowEntity = useAppSelector(state => state.molbaRow.entity);
  const loading = useAppSelector(state => state.molbaRow.loading);
  const updating = useAppSelector(state => state.molbaRow.updating);
  const updateSuccess = useAppSelector(state => state.molbaRow.updateSuccess);

  const handleClose = () => {
    navigate(`/molba-row${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
    if (values.brvl !== undefined && typeof values.brvl !== 'number') {
      values.brvl = Number(values.brvl);
    }
    if (values.brdni !== undefined && typeof values.brdni !== 'number') {
      values.brdni = Number(values.brdni);
    }
    if (values.cel !== undefined && typeof values.cel !== 'number') {
      values.cel = Number(values.cel);
    }
    values.molDatVav = convertDateTimeToServer(values.molDatVav);
    if (values.cenamol !== undefined && typeof values.cenamol !== 'number') {
      values.cenamol = Number(values.cenamol);
    }

    const entity = {
      ...molbaRowEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          molDatVav: displayDefaultDateTime(),
        }
      : {
          ...molbaRowEntity,
          molDatVav: convertDateTimeFromServer(molbaRowEntity.molDatVav),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.molbaRow.home.createOrEditLabel" data-cy="MolbaRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.molbaRow.home.createOrEditLabel">Create or edit a MolbaRow</Translate>
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
                  id="molba-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.datVli')}
                id="molba-row-datVli"
                name="datVli"
                data-cy="datVli"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.datIzl')}
                id="molba-row-datIzl"
                name="datIzl"
                data-cy="datIzl"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.vidvis')}
                id="molba-row-vidvis"
                name="vidvis"
                data-cy="vidvis"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField label={translate('visaApplyApp.molbaRow.brvl')} id="molba-row-brvl" name="brvl" data-cy="brvl" type="text" />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.vidus')}
                id="molba-row-vidus"
                name="vidus"
                data-cy="vidus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.valvis')}
                id="molba-row-valvis"
                name="valvis"
                data-cy="valvis"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.brdni')}
                id="molba-row-brdni"
                name="brdni"
                data-cy="brdni"
                type="text"
              />
              <ValidatedField label={translate('visaApplyApp.molbaRow.cel')} id="molba-row-cel" name="cel" data-cy="cel" type="text" />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.molDatVav')}
                id="molba-row-molDatVav"
                name="molDatVav"
                data-cy="molDatVav"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.gratis')}
                id="molba-row-gratis"
                name="gratis"
                data-cy="gratis"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.imavisa')}
                id="molba-row-imavisa"
                name="imavisa"
                data-cy="imavisa"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.cenamol')}
                id="molba-row-cenamol"
                name="cenamol"
                data-cy="cenamol"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.cenacurr')}
                id="molba-row-cenacurr"
                name="cenacurr"
                data-cy="cenacurr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.maindest')}
                id="molba-row-maindest"
                name="maindest"
                data-cy="maindest"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.maindestnm')}
                id="molba-row-maindestnm"
                name="maindestnm"
                data-cy="maindestnm"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.gkppDarj')}
                id="molba-row-gkppDarj"
                name="gkppDarj"
                data-cy="gkppDarj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.gkppText')}
                id="molba-row-gkppText"
                name="gkppText"
                data-cy="gkppText"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.molbaRow.textIni')}
                id="molba-row-textIni"
                name="textIni"
                data-cy="textIni"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/molba-row" replace color="info">
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

export default MolbaRowUpdate;
