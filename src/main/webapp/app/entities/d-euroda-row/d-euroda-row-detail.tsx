import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-euroda-row.reducer';

export const DEurodaRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dEurodaRowEntity = useAppSelector(state => state.dEurodaRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dEurodaRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dEurodaRow.detail.title">DEurodaRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dEurodaRowEntity.id}</dd>
          <dt>
            <span id="euFamil">
              <Translate contentKey="visaApplyApp.dEurodaRow.euFamil">Eu Famil</Translate>
            </span>
          </dt>
          <dd>{dEurodaRowEntity.euFamil}</dd>
          <dt>
            <span id="euImena">
              <Translate contentKey="visaApplyApp.dEurodaRow.euImena">Eu Imena</Translate>
            </span>
          </dt>
          <dd>{dEurodaRowEntity.euImena}</dd>
          <dt>
            <span id="euNacBel">
              <Translate contentKey="visaApplyApp.dEurodaRow.euNacBel">Eu Nac Bel</Translate>
            </span>
          </dt>
          <dd>{dEurodaRowEntity.euNacBel}</dd>
          <dt>
            <span id="euRodstvo">
              <Translate contentKey="visaApplyApp.dEurodaRow.euRodstvo">Eu Rodstvo</Translate>
            </span>
          </dt>
          <dd>{dEurodaRowEntity.euRodstvo}</dd>
        </dl>
        <Button tag={Link} to="/d-euroda-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-euroda-row/${dEurodaRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DEurodaRowDetail;
