import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './d-lcdop-row.reducer';

export const DLcdopRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dLcdopRowEntity = useAppSelector(state => state.dLcdopRow.entity);
  const loading = useAppSelector(state => state.dLcdopRow.loading);
  const updating = useAppSelector(state => state.dLcdopRow.updating);
  const updateSuccess = useAppSelector(state => state.dLcdopRow.updateSuccess);

  const handleClose = () => {
    navigate(`/d-lcdop-row${location.search}`);
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
    if (values.ldTel !== undefined && typeof values.ldTel !== 'number') {
      values.ldTel = Number(values.ldTel);
    }

    const entity = {
      ...dLcdopRowEntity,
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
      ? {}
      : {
          ...dLcdopRowEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dLcdopRow.home.createOrEditLabel" data-cy="DLcdopRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dLcdopRow.home.createOrEditLabel">Create or edit a DLcdopRow</Translate>
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
                  id="d-lcdop-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldMrjdarj')}
                id="d-lcdop-row-ldMrjdarj"
                name="ldMrjdarj"
                data-cy="ldMrjdarj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldMrjnm')}
                id="d-lcdop-row-ldMrjnm"
                name="ldMrjnm"
                data-cy="ldMrjnm"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldMrjgraj')}
                id="d-lcdop-row-ldMrjgraj"
                name="ldMrjgraj"
                data-cy="ldMrjgraj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldZenen')}
                id="d-lcdop-row-ldZenen"
                name="ldZenen"
                data-cy="ldZenen"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldJitDarj')}
                id="d-lcdop-row-ldJitDarj"
                name="ldJitDarj"
                data-cy="ldJitDarj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldJitNm')}
                id="d-lcdop-row-ldJitNm"
                name="ldJitNm"
                data-cy="ldJitNm"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldJitUl')}
                id="d-lcdop-row-ldJitUl"
                name="ldJitUl"
                data-cy="ldJitUl"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldTel')}
                id="d-lcdop-row-ldTel"
                name="ldTel"
                data-cy="ldTel"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldRabota')}
                id="d-lcdop-row-ldRabota"
                name="ldRabota"
                data-cy="ldRabota"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldProfkod')}
                id="d-lcdop-row-ldProfkod"
                name="ldProfkod"
                data-cy="ldProfkod"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldProfesia')}
                id="d-lcdop-row-ldProfesia"
                name="ldProfesia"
                data-cy="ldProfesia"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldSlDarj')}
                id="d-lcdop-row-ldSlDarj"
                name="ldSlDarj"
                data-cy="ldSlDarj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldSlNm')}
                id="d-lcdop-row-ldSlNm"
                name="ldSlNm"
                data-cy="ldSlNm"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcdopRow.ldSlUl')}
                id="d-lcdop-row-ldSlUl"
                name="ldSlUl"
                data-cy="ldSlUl"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-lcdop-row" replace color="info">
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

export default DLcdopRowUpdate;
