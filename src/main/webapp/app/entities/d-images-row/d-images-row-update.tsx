import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './d-images-row.reducer';

export const DImagesRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dImagesRowEntity = useAppSelector(state => state.dImagesRow.entity);
  const loading = useAppSelector(state => state.dImagesRow.loading);
  const updating = useAppSelector(state => state.dImagesRow.updating);
  const updateSuccess = useAppSelector(state => state.dImagesRow.updateSuccess);

  const handleClose = () => {
    navigate(`/d-images-row${location.search}`);
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
    if (values.imDevicetype !== undefined && typeof values.imDevicetype !== 'number') {
      values.imDevicetype = Number(values.imDevicetype);
    }
    if (values.imWidth !== undefined && typeof values.imWidth !== 'number') {
      values.imWidth = Number(values.imWidth);
    }
    if (values.imHeight !== undefined && typeof values.imHeight !== 'number') {
      values.imHeight = Number(values.imHeight);
    }
    if (values.imImglen !== undefined && typeof values.imImglen !== 'number') {
      values.imImglen = Number(values.imImglen);
    }

    const entity = {
      ...dImagesRowEntity,
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
          ...dImagesRowEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dImagesRow.home.createOrEditLabel" data-cy="DImagesRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dImagesRow.home.createOrEditLabel">Create or edit a DImagesRow</Translate>
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
                  id="d-images-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dImagesRow.imDevicetype')}
                id="d-images-row-imDevicetype"
                name="imDevicetype"
                data-cy="imDevicetype"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dImagesRow.imWidth')}
                id="d-images-row-imWidth"
                name="imWidth"
                data-cy="imWidth"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dImagesRow.imHeight')}
                id="d-images-row-imHeight"
                name="imHeight"
                data-cy="imHeight"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dImagesRow.imImglen')}
                id="d-images-row-imImglen"
                name="imImglen"
                data-cy="imImglen"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dImagesRow.imImage')}
                id="d-images-row-imImage"
                name="imImage"
                data-cy="imImage"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-images-row" replace color="info">
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

export default DImagesRowUpdate;
