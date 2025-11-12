import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-maika-row.reducer';

export const DMaikaRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dMaikaRowEntity = useAppSelector(state => state.dMaikaRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dMaikaRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dMaikaRow.detail.title">DMaikaRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.id}</dd>
          <dt>
            <span id="dcFamil">
              <Translate contentKey="visaApplyApp.dMaikaRow.dcFamil">Dc Famil</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.dcFamil}</dd>
          <dt>
            <span id="dcImena">
              <Translate contentKey="visaApplyApp.dMaikaRow.dcImena">Dc Imena</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.dcImena}</dd>
          <dt>
            <span id="dcPol">
              <Translate contentKey="visaApplyApp.dMaikaRow.dcPol">Dc Pol</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.dcPol}</dd>
          <dt>
            <span id="dcDarj">
              <Translate contentKey="visaApplyApp.dMaikaRow.dcDarj">Dc Darj</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.dcDarj}</dd>
          <dt>
            <span id="dcGrad">
              <Translate contentKey="visaApplyApp.dMaikaRow.dcGrad">Dc Grad</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.dcGrad}</dd>
          <dt>
            <span id="dcUlica">
              <Translate contentKey="visaApplyApp.dMaikaRow.dcUlica">Dc Ulica</Translate>
            </span>
          </dt>
          <dd>{dMaikaRowEntity.dcUlica}</dd>
        </dl>
        <Button tag={Link} to="/d-maika-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-maika-row/${dMaikaRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DMaikaRowDetail;
