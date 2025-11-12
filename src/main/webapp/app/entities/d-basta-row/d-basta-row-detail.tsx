import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-basta-row.reducer';

export const DBastaRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dBastaRowEntity = useAppSelector(state => state.dBastaRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dBastaRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dBastaRow.detail.title">DBastaRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dBastaRowEntity.id}</dd>
          <dt>
            <span id="dcFamil">
              <Translate contentKey="visaApplyApp.dBastaRow.dcFamil">Dc Famil</Translate>
            </span>
          </dt>
          <dd>{dBastaRowEntity.dcFamil}</dd>
          <dt>
            <span id="dcImena">
              <Translate contentKey="visaApplyApp.dBastaRow.dcImena">Dc Imena</Translate>
            </span>
          </dt>
          <dd>{dBastaRowEntity.dcImena}</dd>
          <dt>
            <span id="dcPol">
              <Translate contentKey="visaApplyApp.dBastaRow.dcPol">Dc Pol</Translate>
            </span>
          </dt>
          <dd>{dBastaRowEntity.dcPol}</dd>
          <dt>
            <span id="dcGrad">
              <Translate contentKey="visaApplyApp.dBastaRow.dcGrad">Dc Grad</Translate>
            </span>
          </dt>
          <dd>{dBastaRowEntity.dcGrad}</dd>
          <dt>
            <span id="dcUlica">
              <Translate contentKey="visaApplyApp.dBastaRow.dcUlica">Dc Ulica</Translate>
            </span>
          </dt>
          <dd>{dBastaRowEntity.dcUlica}</dd>
        </dl>
        <Button tag={Link} to="/d-basta-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-basta-row/${dBastaRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DBastaRowDetail;
