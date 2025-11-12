import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-domakin-row.reducer';

export const DDomakinRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dDomakinRowEntity = useAppSelector(state => state.dDomakinRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dDomakinRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dDomakinRow.detail.title">DDomakinRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.id}</dd>
          <dt>
            <span id="dmVid">
              <Translate contentKey="visaApplyApp.dDomakinRow.dmVid">Dm Vid</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.dmVid}</dd>
          <dt>
            <span id="nomPok">
              <Translate contentKey="visaApplyApp.dDomakinRow.nomPok">Nom Pok</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.nomPok}</dd>
          <dt>
            <span id="domGraj">
              <Translate contentKey="visaApplyApp.dDomakinRow.domGraj">Dom Graj</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.domGraj}</dd>
          <dt>
            <span id="domFamil">
              <Translate contentKey="visaApplyApp.dDomakinRow.domFamil">Dom Famil</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.domFamil}</dd>
          <dt>
            <span id="domIme">
              <Translate contentKey="visaApplyApp.dDomakinRow.domIme">Dom Ime</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.domIme}</dd>
          <dt>
            <span id="domDarj">
              <Translate contentKey="visaApplyApp.dDomakinRow.domDarj">Dom Darj</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.domDarj}</dd>
          <dt>
            <span id="domNm">
              <Translate contentKey="visaApplyApp.dDomakinRow.domNm">Dom Nm</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.domNm}</dd>
          <dt>
            <span id="domAdres">
              <Translate contentKey="visaApplyApp.dDomakinRow.domAdres">Dom Adres</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.domAdres}</dd>
          <dt>
            <span id="vedDarj">
              <Translate contentKey="visaApplyApp.dDomakinRow.vedDarj">Ved Darj</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.vedDarj}</dd>
          <dt>
            <span id="vedNm">
              <Translate contentKey="visaApplyApp.dDomakinRow.vedNm">Ved Nm</Translate>
            </span>
          </dt>
          <dd>{dDomakinRowEntity.vedNm}</dd>
        </dl>
        <Button tag={Link} to="/d-domakin-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-domakin-row/${dDomakinRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DDomakinRowDetail;
