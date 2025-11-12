import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './molba-row.reducer';

export const MolbaRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const molbaRowEntity = useAppSelector(state => state.molbaRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="molbaRowDetailsHeading">
          <Translate contentKey="visaApplyApp.molbaRow.detail.title">MolbaRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.id}</dd>
          <dt>
            <span id="datVli">
              <Translate contentKey="visaApplyApp.molbaRow.datVli">Dat Vli</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.datVli ? <TextFormat value={molbaRowEntity.datVli} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="datIzl">
              <Translate contentKey="visaApplyApp.molbaRow.datIzl">Dat Izl</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.datIzl ? <TextFormat value={molbaRowEntity.datIzl} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="vidvis">
              <Translate contentKey="visaApplyApp.molbaRow.vidvis">Vidvis</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.vidvis}</dd>
          <dt>
            <span id="brvl">
              <Translate contentKey="visaApplyApp.molbaRow.brvl">Brvl</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.brvl}</dd>
          <dt>
            <span id="vidus">
              <Translate contentKey="visaApplyApp.molbaRow.vidus">Vidus</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.vidus}</dd>
          <dt>
            <span id="valvis">
              <Translate contentKey="visaApplyApp.molbaRow.valvis">Valvis</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.valvis}</dd>
          <dt>
            <span id="brdni">
              <Translate contentKey="visaApplyApp.molbaRow.brdni">Brdni</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.brdni}</dd>
          <dt>
            <span id="cel">
              <Translate contentKey="visaApplyApp.molbaRow.cel">Cel</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.cel}</dd>
          <dt>
            <span id="molDatVav">
              <Translate contentKey="visaApplyApp.molbaRow.molDatVav">Mol Dat Vav</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.molDatVav ? <TextFormat value={molbaRowEntity.molDatVav} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="gratis">
              <Translate contentKey="visaApplyApp.molbaRow.gratis">Gratis</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.gratis}</dd>
          <dt>
            <span id="imavisa">
              <Translate contentKey="visaApplyApp.molbaRow.imavisa">Imavisa</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.imavisa}</dd>
          <dt>
            <span id="cenamol">
              <Translate contentKey="visaApplyApp.molbaRow.cenamol">Cenamol</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.cenamol}</dd>
          <dt>
            <span id="cenacurr">
              <Translate contentKey="visaApplyApp.molbaRow.cenacurr">Cenacurr</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.cenacurr}</dd>
          <dt>
            <span id="maindest">
              <Translate contentKey="visaApplyApp.molbaRow.maindest">Maindest</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.maindest}</dd>
          <dt>
            <span id="maindestnm">
              <Translate contentKey="visaApplyApp.molbaRow.maindestnm">Maindestnm</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.maindestnm}</dd>
          <dt>
            <span id="gkppDarj">
              <Translate contentKey="visaApplyApp.molbaRow.gkppDarj">Gkpp Darj</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.gkppDarj}</dd>
          <dt>
            <span id="gkppText">
              <Translate contentKey="visaApplyApp.molbaRow.gkppText">Gkpp Text</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.gkppText}</dd>
          <dt>
            <span id="textIni">
              <Translate contentKey="visaApplyApp.molbaRow.textIni">Text Ini</Translate>
            </span>
          </dt>
          <dd>{molbaRowEntity.textIni}</dd>
        </dl>
        <Button tag={Link} to="/molba-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/molba-row/${molbaRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MolbaRowDetail;
