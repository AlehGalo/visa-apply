import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-fingers-row.reducer';

export const DFingersRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dFingersRowEntity = useAppSelector(state => state.dFingersRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dFingersRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dFingersRow.detail.title">DFingersRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.id}</dd>
          <dt>
            <span id="fnDatreg">
              <Translate contentKey="visaApplyApp.dFingersRow.fnDatreg">Fn Datreg</Translate>
            </span>
          </dt>
          <dd>
            {dFingersRowEntity.fnDatreg ? (
              <TextFormat value={dFingersRowEntity.fnDatreg} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="fnDatvav">
              <Translate contentKey="visaApplyApp.dFingersRow.fnDatvav">Fn Datvav</Translate>
            </span>
          </dt>
          <dd>
            {dFingersRowEntity.fnDatvav ? <TextFormat value={dFingersRowEntity.fnDatvav} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="fnUsera">
              <Translate contentKey="visaApplyApp.dFingersRow.fnUsera">Fn Usera</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnUsera}</dd>
          <dt>
            <span id="fnSid">
              <Translate contentKey="visaApplyApp.dFingersRow.fnSid">Fn Sid</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnSid}</dd>
          <dt>
            <span id="fnPnr">
              <Translate contentKey="visaApplyApp.dFingersRow.fnPnr">Fn Pnr</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnPnr}</dd>
          <dt>
            <span id="fnVidmol">
              <Translate contentKey="visaApplyApp.dFingersRow.fnVidmol">Fn Vidmol</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnVidmol}</dd>
          <dt>
            <span id="fnDrugi">
              <Translate contentKey="visaApplyApp.dFingersRow.fnDrugi">Fn Drugi</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnDrugi}</dd>
          <dt>
            <span id="fnDeviceid">
              <Translate contentKey="visaApplyApp.dFingersRow.fnDeviceid">Fn Deviceid</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnDeviceid}</dd>
          <dt>
            <span id="fnScanres">
              <Translate contentKey="visaApplyApp.dFingersRow.fnScanres">Fn Scanres</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnScanres}</dd>
          <dt>
            <span id="fnWidth">
              <Translate contentKey="visaApplyApp.dFingersRow.fnWidth">Fn Width</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnWidth}</dd>
          <dt>
            <span id="fnHeight">
              <Translate contentKey="visaApplyApp.dFingersRow.fnHeight">Fn Height</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnHeight}</dd>
          <dt>
            <span id="fnPixeldepth">
              <Translate contentKey="visaApplyApp.dFingersRow.fnPixeldepth">Fn Pixeldepth</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnPixeldepth}</dd>
          <dt>
            <span id="fnCompressalgo">
              <Translate contentKey="visaApplyApp.dFingersRow.fnCompressalgo">Fn Compressalgo</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnCompressalgo}</dd>
          <dt>
            <span id="fnFingerposition">
              <Translate contentKey="visaApplyApp.dFingersRow.fnFingerposition">Fn Fingerposition</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnFingerposition}</dd>
          <dt>
            <span id="fnImagequality">
              <Translate contentKey="visaApplyApp.dFingersRow.fnImagequality">Fn Imagequality</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnImagequality}</dd>
          <dt>
            <span id="fnImage">
              <Translate contentKey="visaApplyApp.dFingersRow.fnImage">Fn Image</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnImage}</dd>
          <dt>
            <span id="fnImglen">
              <Translate contentKey="visaApplyApp.dFingersRow.fnImglen">Fn Imglen</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnImglen}</dd>
          <dt>
            <span id="fnNottakenreason">
              <Translate contentKey="visaApplyApp.dFingersRow.fnNottakenreason">Fn Nottakenreason</Translate>
            </span>
          </dt>
          <dd>{dFingersRowEntity.fnNottakenreason}</dd>
        </dl>
        <Button tag={Link} to="/d-fingers-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-fingers-row/${dFingersRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DFingersRowDetail;
