import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './d-lcuz-row.reducer';

export const DLcuzRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dLcuzRowList = useAppSelector(state => state.dLcuzRow.entities);
  const loading = useAppSelector(state => state.dLcuzRow.loading);
  const totalItems = useAppSelector(state => state.dLcuzRow.totalItems);

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
      <h2 id="d-lcuz-row-heading" data-cy="DLcuzRowHeading">
        <Translate contentKey="visaApplyApp.dLcuzRow.home.title">D Lcuz Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dLcuzRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-lcuz-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dLcuzRow.home.createLabel">Create new D Lcuz Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dLcuzRowList && dLcuzRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('vidZp')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.vidZp">Vid Zp</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vidZp')} />
                </th>
                <th className="hand" onClick={sort('nacBel')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.nacBel">Nac Bel</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nacBel')} />
                </th>
                <th className="hand" onClick={sort('nacPasp')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.nacPasp">Nac Pasp</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nacPasp')} />
                </th>
                <th className="hand" onClick={sort('paspVal')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.paspVal">Pasp Val</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paspVal')} />
                </th>
                <th className="hand" onClick={sort('graj')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.graj">Graj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('graj')} />
                </th>
                <th className="hand" onClick={sort('famil')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.famil">Famil</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('famil')} />
                </th>
                <th className="hand" onClick={sort('imena')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.imena">Imena</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imena')} />
                </th>
                <th className="hand" onClick={sort('datRaj')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.datRaj">Dat Raj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('datRaj')} />
                </th>
                <th className="hand" onClick={sort('pol')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.pol">Pol</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('pol')} />
                </th>
                <th className="hand" onClick={sort('datIzd')}>
                  <Translate contentKey="visaApplyApp.dLcuzRow.datIzd">Dat Izd</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('datIzd')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dLcuzRowList.map((dLcuzRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-lcuz-row/${dLcuzRow.id}`} color="link" size="sm">
                      {dLcuzRow.id}
                    </Button>
                  </td>
                  <td>{dLcuzRow.vidZp}</td>
                  <td>{dLcuzRow.nacBel}</td>
                  <td>{dLcuzRow.nacPasp}</td>
                  <td>{dLcuzRow.paspVal ? <TextFormat type="date" value={dLcuzRow.paspVal} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{dLcuzRow.graj}</td>
                  <td>{dLcuzRow.famil}</td>
                  <td>{dLcuzRow.imena}</td>
                  <td>{dLcuzRow.datRaj}</td>
                  <td>{dLcuzRow.pol}</td>
                  <td>{dLcuzRow.datIzd ? <TextFormat type="date" value={dLcuzRow.datIzd} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-lcuz-row/${dLcuzRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-lcuz-row/${dLcuzRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/d-lcuz-row/${dLcuzRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.dLcuzRow.home.notFound">No D Lcuz Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dLcuzRowList && dLcuzRowList.length > 0 ? '' : 'd-none'}>
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

export default DLcuzRow;
