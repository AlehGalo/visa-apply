import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './d-domakin-row.reducer';

export const DDomakinRowUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dDomakinRowEntity = useAppSelector(state => state.dDomakinRow.entity);
  const loading = useAppSelector(state => state.dDomakinRow.loading);
  const updating = useAppSelector(state => state.dDomakinRow.updating);
  const updateSuccess = useAppSelector(state => state.dDomakinRow.updateSuccess);

  const handleClose = () => {
    navigate(`/d-domakin-row${location.search}`);
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
    if (values.domNm !== undefined && typeof values.domNm !== 'number') {
      values.domNm = Number(values.domNm);
    }

    const entity = {
      ...dDomakinRowEntity,
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
          ...dDomakinRowEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="visaApplyApp.dDomakinRow.home.createOrEditLabel" data-cy="DDomakinRowCreateUpdateHeading">
            <Translate contentKey="visaApplyApp.dDomakinRow.home.createOrEditLabel">Create or edit a DDomakinRow</Translate>
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
                  id="d-domakin-row-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.dmVid')}
                id="d-domakin-row-dmVid"
                name="dmVid"
                data-cy="dmVid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.nomPok')}
                id="d-domakin-row-nomPok"
                name="nomPok"
                data-cy="nomPok"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.domGraj')}
                id="d-domakin-row-domGraj"
                name="domGraj"
                data-cy="domGraj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.domFamil')}
                id="d-domakin-row-domFamil"
                name="domFamil"
                data-cy="domFamil"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.domIme')}
                id="d-domakin-row-domIme"
                name="domIme"
                data-cy="domIme"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.domDarj')}
                id="d-domakin-row-domDarj"
                name="domDarj"
                data-cy="domDarj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.domNm')}
                id="d-domakin-row-domNm"
                name="domNm"
                data-cy="domNm"
                type="text"
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.domAdres')}
                id="d-domakin-row-domAdres"
                name="domAdres"
                data-cy="domAdres"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.vedDarj')}
                id="d-domakin-row-vedDarj"
                name="vedDarj"
                data-cy="vedDarj"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('visaApplyApp.dDomakinRow.vedNm')}
                id="d-domakin-row-vedNm"
                name="vedNm"
                data-cy="vedNm"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-domakin-row" replace color="info">
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

export default DDomakinRowUpdate;
