import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-images-row.reducer';

export const DImagesRowDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dImagesRowEntity = useAppSelector(state => state.dImagesRow.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dImagesRowDetailsHeading">
          <Translate contentKey="visaApplyApp.dImagesRow.detail.title">DImagesRow</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dImagesRowEntity.id}</dd>
          <dt>
            <span id="imDevicetype">
              <Translate contentKey="visaApplyApp.dImagesRow.imDevicetype">Im Devicetype</Translate>
            </span>
          </dt>
          <dd>{dImagesRowEntity.imDevicetype}</dd>
          <dt>
            <span id="imWidth">
              <Translate contentKey="visaApplyApp.dImagesRow.imWidth">Im Width</Translate>
            </span>
          </dt>
          <dd>{dImagesRowEntity.imWidth}</dd>
          <dt>
            <span id="imHeight">
              <Translate contentKey="visaApplyApp.dImagesRow.imHeight">Im Height</Translate>
            </span>
          </dt>
          <dd>{dImagesRowEntity.imHeight}</dd>
          <dt>
            <span id="imImglen">
              <Translate contentKey="visaApplyApp.dImagesRow.imImglen">Im Imglen</Translate>
            </span>
          </dt>
          <dd>{dImagesRowEntity.imImglen}</dd>
          <dt>
            <span id="imImage">
              <Translate contentKey="visaApplyApp.dImagesRow.imImage">Im Image</Translate>
            </span>
          </dt>
          <dd>{dImagesRowEntity.imImage}</dd>
        </dl>
        <Button tag={Link} to="/d-images-row" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-images-row/${dImagesRowEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DImagesRowDetail;
