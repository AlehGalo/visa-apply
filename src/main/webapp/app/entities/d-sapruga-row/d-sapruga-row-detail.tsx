import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-sapruga-row.reducer';

export const DSaprugaRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dSaprugaRowEntity = useAppSelector(state => state.dSaprugaRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dSaprugaRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dSaprugaRow.detail.title">DSaprugaRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dSaprugaRowEntity.id}</dd>
          <dt>
            <span id="spMrjdarj">
              <Translate contentKey="visaApplyApp.dSaprugaRow.spMrjdarj">Sp Mrjdarj</Translate>
            </span>
          </dt>
          <dd>{dSaprugaRowEntity.spMrjdarj}</dd>
        </dl>
        <Button tag={Link} to="/d-sapruga-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-sapruga-row/${dSaprugaRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DSaprugaRowDetail;
