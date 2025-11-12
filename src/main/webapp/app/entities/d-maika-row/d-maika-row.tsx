import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './d-maika-row.reducer';

export const DMaikaRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dMaikaRowList = useAppSelector(state => state.dMaikaRow.entities);
  const loading = useAppSelector(state => state.dMaikaRow.loading);
  const totalItems = useAppSelector(state => state.dMaikaRow.totalItems);

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
      <h2 id="d-maika-row-heading" data-cy="DMaikaRowHeading">
        <Translate contentKey="visaApplyApp.dMaikaRow.home.title">D Maika Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dMaikaRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-maika-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dMaikaRow.home.createLabel">Create new D Maika Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dMaikaRowList && dMaikaRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('dcFamil')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.dcFamil">Dc Famil</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dcFamil')} />
                </th>
                <th className="hand" onClick={sort('dcImena')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.dcImena">Dc Imena</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dcImena')} />
                </th>
                <th className="hand" onClick={sort('dcPol')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.dcPol">Dc Pol</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dcPol')} />
                </th>
                <th className="hand" onClick={sort('dcDarj')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.dcDarj">Dc Darj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dcDarj')} />
                </th>
                <th className="hand" onClick={sort('dcGrad')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.dcGrad">Dc Grad</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dcGrad')} />
                </th>
                <th className="hand" onClick={sort('dcUlica')}>
                  <Translate contentKey="visaApplyApp.dMaikaRow.dcUlica">Dc Ulica</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dcUlica')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dMaikaRowList.map((dMaikaRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-maika-row/${dMaikaRow.id}`} color="link" size="sm">
                      {dMaikaRow.id}
                    </Button>
                  </td>
                  <td>{dMaikaRow.dcFamil}</td>
                  <td>{dMaikaRow.dcImena}</td>
                  <td>{dMaikaRow.dcPol}</td>
                  <td>{dMaikaRow.dcDarj}</td>
                  <td>{dMaikaRow.dcGrad}</td>
                  <td>{dMaikaRow.dcUlica}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-maika-row/${dMaikaRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-maika-row/${dMaikaRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/d-maika-row/${dMaikaRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.dMaikaRow.home.notFound">No D Maika Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dMaikaRowList && dMaikaRowList.length > 0 ? '' : 'd-none'}>
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

export default DMaikaRow;
