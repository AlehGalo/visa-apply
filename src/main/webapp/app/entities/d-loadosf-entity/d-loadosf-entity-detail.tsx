import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-loadosf-entity.reducer';

export const DLoadosfEntityDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dLoadosfEntityEntity = useAppSelector(state => state.dLoadosfEntity.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dLoadosfEntityDetailsHeading">
          <Translate contentKey="visaApplyApp.dLoadosfEntity.detail.title">DLoadosfEntity</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dLoadosfEntityEntity.id}</dd>
          <dt>
            <span id="version">
              <Translate contentKey="visaApplyApp.dLoadosfEntity.version">Version</Translate>
            </span>
          </dt>
          <dd>{dLoadosfEntityEntity.version}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.msgheader">Msgheader</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.msgheader ? dLoadosfEntityEntity.msgheader.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.lcuz">Lcuz</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.lcuz ? dLoadosfEntityEntity.lcuz.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.lcdop">Lcdop</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.lcdop ? dLoadosfEntityEntity.lcdop.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.bastaEntity">Basta Entity</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.bastaEntity ? dLoadosfEntityEntity.bastaEntity.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.maika">Maika</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.maika ? dLoadosfEntityEntity.maika.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.sapruga">Sapruga</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.sapruga ? dLoadosfEntityEntity.sapruga.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.molba">Molba</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.molba ? dLoadosfEntityEntity.molba.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.domakin">Domakin</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.domakin ? dLoadosfEntityEntity.domakin.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.euroda">Euroda</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.euroda ? dLoadosfEntityEntity.euroda.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.voit">Voit</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.voit ? dLoadosfEntityEntity.voit.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.images">Images</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.images ? dLoadosfEntityEntity.images.id : ''}</dd>
          <dt>
            <Translate contentKey="visaApplyApp.dLoadosfEntity.fingers">Fingers</Translate>
          </dt>
          <dd>{dLoadosfEntityEntity.fingers ? dLoadosfEntityEntity.fingers.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/d-loadosf-entity" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-loadosf-entity/${dLoadosfEntityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DLoadosfEntityDetail;
