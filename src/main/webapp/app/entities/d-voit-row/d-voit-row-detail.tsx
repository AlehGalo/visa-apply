import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-voit-row.reducer';

export const DVoitRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dVoitRowEntity = useAppSelector(state => state.dVoitRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dVoitRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dVoitRow.detail.title">DVoitRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dVoitRowEntity.id}</dd>
          <dt>
            <span id="vnom">
              <Translate contentKey="visaApplyApp.dVoitRow.vnom">Vnom</Translate>
            </span>
          </dt>
          <dd>{dVoitRowEntity.vnom}</dd>
          <dt>
            <span id="vime">
              <Translate contentKey="visaApplyApp.dVoitRow.vime">Vime</Translate>
            </span>
          </dt>
          <dd>{dVoitRowEntity.vime}</dd>
          <dt>
            <span id="bgime">
              <Translate contentKey="visaApplyApp.dVoitRow.bgime">Bgime</Translate>
            </span>
          </dt>
          <dd>{dVoitRowEntity.bgime}</dd>
          <dt>
            <span id="bgadres">
              <Translate contentKey="visaApplyApp.dVoitRow.bgadres">Bgadres</Translate>
            </span>
          </dt>
          <dd>{dVoitRowEntity.bgadres}</dd>
        </dl>
        <Button tag={Link} to="/d-voit-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-voit-row/${dVoitRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DVoitRowDetail;
