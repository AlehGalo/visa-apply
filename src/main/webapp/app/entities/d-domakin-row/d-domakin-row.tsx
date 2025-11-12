import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './d-domakin-row.reducer';

export const DDomakinRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dDomakinRowList = useAppSelector(state => state.dDomakinRow.entities);
  const loading = useAppSelector(state => state.dDomakinRow.loading);
  const totalItems = useAppSelector(state => state.dDomakinRow.totalItems);

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
      <h2 id="d-domakin-row-heading" data-cy="DDomakinRowHeading">
        <Translate contentKey="visaApplyApp.dDomakinRow.home.title">D Domakin Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dDomakinRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-domakin-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dDomakinRow.home.createLabel">Create new D Domakin Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dDomakinRowList && dDomakinRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('dmVid')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.dmVid">Dm Vid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dmVid')} />
                </th>
                <th className="hand" onClick={sort('nomPok')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.nomPok">Nom Pok</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nomPok')} />
                </th>
                <th className="hand" onClick={sort('domGraj')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.domGraj">Dom Graj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('domGraj')} />
                </th>
                <th className="hand" onClick={sort('domFamil')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.domFamil">Dom Famil</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('domFamil')} />
                </th>
                <th className="hand" onClick={sort('domIme')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.domIme">Dom Ime</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('domIme')} />
                </th>
                <th className="hand" onClick={sort('domDarj')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.domDarj">Dom Darj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('domDarj')} />
                </th>
                <th className="hand" onClick={sort('domNm')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.domNm">Dom Nm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('domNm')} />
                </th>
                <th className="hand" onClick={sort('domAdres')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.domAdres">Dom Adres</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('domAdres')} />
                </th>
                <th className="hand" onClick={sort('vedDarj')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.vedDarj">Ved Darj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vedDarj')} />
                </th>
                <th className="hand" onClick={sort('vedNm')}>
                  <Translate contentKey="visaApplyApp.dDomakinRow.vedNm">Ved Nm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vedNm')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dDomakinRowList.map((dDomakinRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-domakin-row/${dDomakinRow.id}`} color="link" size="sm">
                      {dDomakinRow.id}
                    </Button>
                  </td>
                  <td>{dDomakinRow.dmVid}</td>
                  <td>{dDomakinRow.nomPok}</td>
                  <td>{dDomakinRow.domGraj}</td>
                  <td>{dDomakinRow.domFamil}</td>
                  <td>{dDomakinRow.domIme}</td>
                  <td>{dDomakinRow.domDarj}</td>
                  <td>{dDomakinRow.domNm}</td>
                  <td>{dDomakinRow.domAdres}</td>
                  <td>{dDomakinRow.vedDarj}</td>
                  <td>{dDomakinRow.vedNm}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-domakin-row/${dDomakinRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-domakin-row/${dDomakinRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/d-domakin-row/${dDomakinRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.dDomakinRow.home.notFound">No D Domakin Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dDomakinRowList && dDomakinRowList.length > 0 ? '' : 'd-none'}>
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

export default DDomakinRow;
