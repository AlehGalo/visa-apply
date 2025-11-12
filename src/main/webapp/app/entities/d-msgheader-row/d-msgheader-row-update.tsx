import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './d-msgheader-row.reducer';

export const DMsgheaderRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dMsgheaderRowEntity = useAppSelector(state => state.dMsgheaderRow.entity);
  const loading = useAppSelector(state => state.dMsgheaderRow.loading);
  const updating = useAppSelector(state => state.dMsgheaderRow.updating);
  const updateSuccess = useAppSelector(state => state.dMsgheaderRow.updateSuccess);

  const handleClose = () => {
    navigate(`/d-msgheader-row${location.search}`);
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
    if (values.mhRegnom !== undefined && typeof values.mhRegnom !== 'number') {
      values.mhRegnom = Number(values.mhRegnom);
    }

    const entity = {
      ...dMsgheaderRowEntity,
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
          ...dMsgheaderRowEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dMsgheaderRow.home.createOrEditLabel" data-cy="DMsgheaderRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dMsgheaderRow.home.createOrEditLabel">Create or edit a DMsgheaderRow</Translate>
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
                  id="d-msgheader-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dMsgheaderRow.mhKscreated')}
                id="d-msgheader-row-mhKscreated"
                name="mhKscreated"
                data-cy="mhKscreated"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dMsgheaderRow.mhRegnom')}
                id="d-msgheader-row-mhRegnom"
                name="mhRegnom"
                data-cy="mhRegnom"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dMsgheaderRow.mhVfsrefno')}
                id="d-msgheader-row-mhVfsrefno"
                name="mhVfsrefno"
                data-cy="mhVfsrefno"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dMsgheaderRow.mhUsera')}
                id="d-msgheader-row-mhUsera"
                name="mhUsera"
                data-cy="mhUsera"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dMsgheaderRow.mhDatvav')}
                id="d-msgheader-row-mhDatvav"
                name="mhDatvav"
                data-cy="mhDatvav"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-msgheader-row" replace color="info">
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

export default DMsgheaderRowUpdate;
