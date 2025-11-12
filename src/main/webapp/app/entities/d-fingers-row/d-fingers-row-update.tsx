import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './d-fingers-row.reducer';

export const DFingersRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dFingersRowEntity = useAppSelector(state => state.dFingersRow.entity);
  const loading = useAppSelector(state => state.dFingersRow.loading);
  const updating = useAppSelector(state => state.dFingersRow.updating);
  const updateSuccess = useAppSelector(state => state.dFingersRow.updateSuccess);

  const handleClose = () => {
    navigate(`/d-fingers-row${location.search}`);
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
    values.fnDatvav = convertDateTimeToServer(values.fnDatvav);
    if (values.fnSid !== undefined && typeof values.fnSid !== 'number') {
      values.fnSid = Number(values.fnSid);
    }
    if (values.fnPnr !== undefined && typeof values.fnPnr !== 'number') {
      values.fnPnr = Number(values.fnPnr);
    }
    if (values.fnDeviceid !== undefined && typeof values.fnDeviceid !== 'number') {
      values.fnDeviceid = Number(values.fnDeviceid);
    }
    if (values.fnScanres !== undefined && typeof values.fnScanres !== 'number') {
      values.fnScanres = Number(values.fnScanres);
    }
    if (values.fnWidth !== undefined && typeof values.fnWidth !== 'number') {
      values.fnWidth = Number(values.fnWidth);
    }
    if (values.fnHeight !== undefined && typeof values.fnHeight !== 'number') {
      values.fnHeight = Number(values.fnHeight);
    }
    if (values.fnPixeldepth !== undefined && typeof values.fnPixeldepth !== 'number') {
      values.fnPixeldepth = Number(values.fnPixeldepth);
    }
    if (values.fnCompressalgo !== undefined && typeof values.fnCompressalgo !== 'number') {
      values.fnCompressalgo = Number(values.fnCompressalgo);
    }
    if (values.fnImagequality !== undefined && typeof values.fnImagequality !== 'number') {
      values.fnImagequality = Number(values.fnImagequality);
    }
    if (values.fnImglen !== undefined && typeof values.fnImglen !== 'number') {
      values.fnImglen = Number(values.fnImglen);
    }

    const entity = {
      ...dFingersRowEntity,
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
          fnDatvav: displayDefaultDateTime(),
        }
      : {
          ...dFingersRowEntity,
          fnDatvav: convertDateTimeFromServer(dFingersRowEntity.fnDatvav),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dFingersRow.home.createOrEditLabel" data-cy="DFingersRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dFingersRow.home.createOrEditLabel">Create or edit a DFingersRow</Translate>
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
                  id="d-fingers-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnDatreg')}
                id="d-fingers-row-fnDatreg"
                name="fnDatreg"
                data-cy="fnDatreg"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnDatvav')}
                id="d-fingers-row-fnDatvav"
                name="fnDatvav"
                data-cy="fnDatvav"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnUsera')}
                id="d-fingers-row-fnUsera"
                name="fnUsera"
                data-cy="fnUsera"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnSid')}
                id="d-fingers-row-fnSid"
                name="fnSid"
                data-cy="fnSid"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnPnr')}
                id="d-fingers-row-fnPnr"
                name="fnPnr"
                data-cy="fnPnr"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnVidmol')}
                id="d-fingers-row-fnVidmol"
                name="fnVidmol"
                data-cy="fnVidmol"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnDrugi')}
                id="d-fingers-row-fnDrugi"
                name="fnDrugi"
                data-cy="fnDrugi"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnDeviceid')}
                id="d-fingers-row-fnDeviceid"
                name="fnDeviceid"
                data-cy="fnDeviceid"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnScanres')}
                id="d-fingers-row-fnScanres"
                name="fnScanres"
                data-cy="fnScanres"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnWidth')}
                id="d-fingers-row-fnWidth"
                name="fnWidth"
                data-cy="fnWidth"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnHeight')}
                id="d-fingers-row-fnHeight"
                name="fnHeight"
                data-cy="fnHeight"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnPixeldepth')}
                id="d-fingers-row-fnPixeldepth"
                name="fnPixeldepth"
                data-cy="fnPixeldepth"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnCompressalgo')}
                id="d-fingers-row-fnCompressalgo"
                name="fnCompressalgo"
                data-cy="fnCompressalgo"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnFingerposition')}
                id="d-fingers-row-fnFingerposition"
                name="fnFingerposition"
                data-cy="fnFingerposition"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnImagequality')}
                id="d-fingers-row-fnImagequality"
                name="fnImagequality"
                data-cy="fnImagequality"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnImage')}
                id="d-fingers-row-fnImage"
                name="fnImage"
                data-cy="fnImage"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnImglen')}
                id="d-fingers-row-fnImglen"
                name="fnImglen"
                data-cy="fnImglen"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dFingersRow.fnNottakenreason')}
                id="d-fingers-row-fnNottakenreason"
                name="fnNottakenreason"
                data-cy="fnNottakenreason"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-fingers-row" replace color="info">
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

export default DFingersRowUpdate;
