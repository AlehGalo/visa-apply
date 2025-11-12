import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-lcdop-row.reducer';

export const DLcdopRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dLcdopRowEntity = useAppSelector(state => state.dLcdopRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dLcdopRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dLcdopRow.detail.title">DLcdopRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.id}</dd>
          <dt>
            <span id="ldMrjdarj">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldMrjdarj">Ld Mrjdarj</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldMrjdarj}</dd>
          <dt>
            <span id="ldMrjnm">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldMrjnm">Ld Mrjnm</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldMrjnm}</dd>
          <dt>
            <span id="ldMrjgraj">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldMrjgraj">Ld Mrjgraj</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldMrjgraj}</dd>
          <dt>
            <span id="ldZenen">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldZenen">Ld Zenen</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldZenen}</dd>
          <dt>
            <span id="ldJitDarj">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldJitDarj">Ld Jit Darj</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldJitDarj}</dd>
          <dt>
            <span id="ldJitNm">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldJitNm">Ld Jit Nm</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldJitNm}</dd>
          <dt>
            <span id="ldJitUl">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldJitUl">Ld Jit Ul</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldJitUl}</dd>
          <dt>
            <span id="ldTel">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldTel">Ld Tel</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldTel}</dd>
          <dt>
            <span id="ldRabota">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldRabota">Ld Rabota</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldRabota}</dd>
          <dt>
            <span id="ldProfkod">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldProfkod">Ld Profkod</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldProfkod}</dd>
          <dt>
            <span id="ldProfesia">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldProfesia">Ld Profesia</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldProfesia}</dd>
          <dt>
            <span id="ldSlDarj">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldSlDarj">Ld Sl Darj</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldSlDarj}</dd>
          <dt>
            <span id="ldSlNm">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldSlNm">Ld Sl Nm</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldSlNm}</dd>
          <dt>
            <span id="ldSlUl">
              <Translate contentKey="visaApplyApp.dLcdopRow.ldSlUl">Ld Sl Ul</Translate>
            </span>
          </dt>
          <dd>{dLcdopRowEntity.ldSlUl}</dd>
        </dl>
        <Button tag={Link} to="/d-lcdop-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-lcdop-row/${dLcdopRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DLcdopRowDetail;
