import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './d-loadosf-entity.reducer';

export const DLoadosfEntity = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dLoadosfEntityList = useAppSelector(state => state.dLoadosfEntity.entities);
  const loading = useAppSelector(state => state.dLoadosfEntity.loading);
  const totalItems = useAppSelector(state => state.dLoadosfEntity.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="d-loadosf-entity-heading" data-cy="DLoadosfEntityHeading">
        <Translate contentKey="visaApplyApp.dLoadosfEntity.home.title">D Loadosf Entities</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dLoadosfEntity.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-loadosf-entity/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dLoadosfEntity.home.createLabel">Create new D Loadosf Entity</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dLoadosfEntityList && dLoadosfEntityList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('version')}>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.version">Version</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('version')} />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.msgheader">Msgheader</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.lcuz">Lcuz</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.lcdop">Lcdop</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.bastaEntity">Basta Entity</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.maika">Maika</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.sapruga">Sapruga</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.molba">Molba</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.domakin">Domakin</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.euroda">Euroda</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.voit">Voit</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.images">Images</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="visaApplyApp.dLoadosfEntity.fingers">Fingers</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dLoadosfEntityList.map((dLoadosfEntity, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-loadosf-entity/${dLoadosfEntity.id}`} color="link" size="sm">
                      {dLoadosfEntity.id}
                    </Button>
                  </td>
                  <td>{dLoadosfEntity.version}</td>
                  <td>
                    {dLoadosfEntity.msgheader ? (
                      <Link to={`/d-msgheader-row/${dLoadosfEntity.msgheader.id}`}>{dLoadosfEntity.msgheader.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{dLoadosfEntity.lcuz ? <Link to={`/d-lcuz-row/${dLoadosfEntity.lcuz.id}`}>{dLoadosfEntity.lcuz.id}</Link> : ''}</td>
                  <td>
                    {dLoadosfEntity.lcdop ? <Link to={`/d-lcdop-row/${dLoadosfEntity.lcdop.id}`}>{dLoadosfEntity.lcdop.id}</Link> : ''}
                  </td>
                  <td>
                    {dLoadosfEntity.bastaEntity ? (
                      <Link to={`/d-basta-row/${dLoadosfEntity.bastaEntity.id}`}>{dLoadosfEntity.bastaEntity.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dLoadosfEntity.maika ? <Link to={`/d-maika-row/${dLoadosfEntity.maika.id}`}>{dLoadosfEntity.maika.id}</Link> : ''}
                  </td>
                  <td>
                    {dLoadosfEntity.sapruga ? (
                      <Link to={`/d-sapruga-row/${dLoadosfEntity.sapruga.id}`}>{dLoadosfEntity.sapruga.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{dLoadosfEntity.molba ? <Link to={`/molba-row/${dLoadosfEntity.molba.id}`}>{dLoadosfEntity.molba.id}</Link> : ''}</td>
                  <td>
                    {dLoadosfEntity.domakin ? (
                      <Link to={`/d-domakin-row/${dLoadosfEntity.domakin.id}`}>{dLoadosfEntity.domakin.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dLoadosfEntity.euroda ? <Link to={`/d-euroda-row/${dLoadosfEntity.euroda.id}`}>{dLoadosfEntity.euroda.id}</Link> : ''}
                  </td>
                  <td>{dLoadosfEntity.voit ? <Link to={`/d-voit-row/${dLoadosfEntity.voit.id}`}>{dLoadosfEntity.voit.id}</Link> : ''}</td>
                  <td>
                    {dLoadosfEntity.images ? <Link to={`/d-images-row/${dLoadosfEntity.images.id}`}>{dLoadosfEntity.images.id}</Link> : ''}
                  </td>
                  <td>
                    {dLoadosfEntity.fingers ? (
                      <Link to={`/d-fingers-row/${dLoadosfEntity.fingers.id}`}>{dLoadosfEntity.fingers.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-loadosf-entity/${dLoadosfEntity.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-loadosf-entity/${dLoadosfEntity.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/d-loadosf-entity/${dLoadosfEntity.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="visaApplyApp.dLoadosfEntity.home.notFound">No D Loadosf Entities found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dLoadosfEntityList && dLoadosfEntityList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default DLoadosfEntity;
