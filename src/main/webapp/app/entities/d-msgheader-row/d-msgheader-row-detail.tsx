import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-msgheader-row.reducer';

export const DMsgheaderRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dMsgheaderRowEntity = useAppSelector(state => state.dMsgheaderRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dMsgheaderRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dMsgheaderRow.detail.title">DMsgheaderRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dMsgheaderRowEntity.id}</dd>
          <dt>
            <span id="mhKscreated">
              <Translate contentKey="visaApplyApp.dMsgheaderRow.mhKscreated">Mh Kscreated</Translate>
            </span>
          </dt>
          <dd>{dMsgheaderRowEntity.mhKscreated}</dd>
          <dt>
            <span id="mhRegnom">
              <Translate contentKey="visaApplyApp.dMsgheaderRow.mhRegnom">Mh Regnom</Translate>
            </span>
          </dt>
          <dd>{dMsgheaderRowEntity.mhRegnom}</dd>
          <dt>
            <span id="mhVfsrefno">
              <Translate contentKey="visaApplyApp.dMsgheaderRow.mhVfsrefno">Mh Vfsrefno</Translate>
            </span>
          </dt>
          <dd>{dMsgheaderRowEntity.mhVfsrefno}</dd>
          <dt>
            <span id="mhUsera">
              <Translate contentKey="visaApplyApp.dMsgheaderRow.mhUsera">Mh Usera</Translate>
            </span>
          </dt>
          <dd>{dMsgheaderRowEntity.mhUsera}</dd>
          <dt>
            <span id="mhDatvav">
              <Translate contentKey="visaApplyApp.dMsgheaderRow.mhDatvav">Mh Datvav</Translate>
            </span>
          </dt>
          <dd>{dMsgheaderRowEntity.mhDatvav}</dd>
        </dl>
        <Button tag={Link} to="/d-msgheader-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-msgheader-row/${dMsgheaderRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DMsgheaderRowDetail;
