import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-lcuz-row.reducer';

export const DLcuzRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dLcuzRowEntity = useAppSelector(state => state.dLcuzRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dLcuzRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dLcuzRow.detail.title">DLcuzRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.id}</dd>
          <dt>
            <span id="vidZp">
              <Translate contentKey="visaApplyApp.dLcuzRow.vidZp">Vid Zp</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.vidZp}</dd>
          <dt>
            <span id="nacBel">
              <Translate contentKey="visaApplyApp.dLcuzRow.nacBel">Nac Bel</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.nacBel}</dd>
          <dt>
            <span id="nacPasp">
              <Translate contentKey="visaApplyApp.dLcuzRow.nacPasp">Nac Pasp</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.nacPasp}</dd>
          <dt>
            <span id="paspVal">
              <Translate contentKey="visaApplyApp.dLcuzRow.paspVal">Pasp Val</Translate>
            </span>
          </dt>
          <dd>
            {dLcuzRowEntity.paspVal ? <TextFormat value={dLcuzRowEntity.paspVal} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="graj">
              <Translate contentKey="visaApplyApp.dLcuzRow.graj">Graj</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.graj}</dd>
          <dt>
            <span id="famil">
              <Translate contentKey="visaApplyApp.dLcuzRow.famil">Famil</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.famil}</dd>
          <dt>
            <span id="imena">
              <Translate contentKey="visaApplyApp.dLcuzRow.imena">Imena</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.imena}</dd>
          <dt>
            <span id="datRaj">
              <Translate contentKey="visaApplyApp.dLcuzRow.datRaj">Dat Raj</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.datRaj}</dd>
          <dt>
            <span id="pol">
              <Translate contentKey="visaApplyApp.dLcuzRow.pol">Pol</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.pol}</dd>
          <dt>
            <span id="datIzd">
              <Translate contentKey="visaApplyApp.dLcuzRow.datIzd">Dat Izd</Translate>
            </span>
          </dt>
          <dd>{dLcuzRowEntity.datIzd ? <TextFormat value={dLcuzRowEntity.datIzd} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/d-lcuz-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-lcuz-row/${dLcuzRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DLcuzRowDetail;
