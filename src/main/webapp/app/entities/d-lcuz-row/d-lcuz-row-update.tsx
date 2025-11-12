import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './d-lcuz-row.reducer';

export const DLcuzRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dLcuzRowEntity = useAppSelector(state => state.dLcuzRow.entity);
  const loading = useAppSelector(state => state.dLcuzRow.loading);
  const updating = useAppSelector(state => state.dLcuzRow.updating);
  const updateSuccess = useAppSelector(state => state.dLcuzRow.updateSuccess);

  const handleClose = () => {
    navigate(`/d-lcuz-row${location.search}`);
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
    if (values.nacPasp !== undefined && typeof values.nacPasp !== 'number') {
      values.nacPasp = Number(values.nacPasp);
    }

    const entity = {
      ...dLcuzRowEntity,
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
          ...dLcuzRowEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dLcuzRow.home.createOrEditLabel" data-cy="DLcuzRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dLcuzRow.home.createOrEditLabel">Create or edit a DLcuzRow</Translate>
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
                  id="d-lcuz-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.vidZp')}
                id="d-lcuz-row-vidZp"
                name="vidZp"
                data-cy="vidZp"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.nacBel')}
                id="d-lcuz-row-nacBel"
                name="nacBel"
                data-cy="nacBel"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.nacPasp')}
                id="d-lcuz-row-nacPasp"
                name="nacPasp"
                data-cy="nacPasp"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.paspVal')}
                id="d-lcuz-row-paspVal"
                name="paspVal"
                data-cy="paspVal"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.graj')}
                id="d-lcuz-row-graj"
                name="graj"
                data-cy="graj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.famil')}
                id="d-lcuz-row-famil"
                name="famil"
                data-cy="famil"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.imena')}
                id="d-lcuz-row-imena"
                name="imena"
                data-cy="imena"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.datRaj')}
                id="d-lcuz-row-datRaj"
                name="datRaj"
                data-cy="datRaj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.pol')}
                id="d-lcuz-row-pol"
                name="pol"
                data-cy="pol"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dLcuzRow.datIzd')}
                id="d-lcuz-row-datIzd"
                name="datIzd"
                data-cy="datIzd"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-lcuz-row" replace color="info">
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

export default DLcuzRowUpdate;
