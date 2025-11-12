import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './molba-row.reducer';

export const MolbaRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const molbaRowList = useAppSelector(state => state.molbaRow.entities);
  const loading = useAppSelector(state => state.molbaRow.loading);
  const totalItems = useAppSelector(state => state.molbaRow.totalItems);

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
      <h2 id="molba-row-heading" data-cy="MolbaRowHeading">
        <Translate contentKey="visaApplyApp.molbaRow.home.title">Molba Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.molbaRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/molba-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.molbaRow.home.createLabel">Create new Molba Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {molbaRowList && molbaRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.molbaRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('datVli')}>
                  <Translate contentKey="visaApplyApp.molbaRow.datVli">Dat Vli</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('datVli')} />
                </th>
                <th className="hand" onClick={sort('datIzl')}>
                  <Translate contentKey="visaApplyApp.molbaRow.datIzl">Dat Izl</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('datIzl')} />
                </th>
                <th className="hand" onClick={sort('vidvis')}>
                  <Translate contentKey="visaApplyApp.molbaRow.vidvis">Vidvis</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vidvis')} />
                </th>
                <th className="hand" onClick={sort('brvl')}>
                  <Translate contentKey="visaApplyApp.molbaRow.brvl">Brvl</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('brvl')} />
                </th>
                <th className="hand" onClick={sort('vidus')}>
                  <Translate contentKey="visaApplyApp.molbaRow.vidus">Vidus</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vidus')} />
                </th>
                <th className="hand" onClick={sort('valvis')}>
                  <Translate contentKey="visaApplyApp.molbaRow.valvis">Valvis</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('valvis')} />
                </th>
                <th className="hand" onClick={sort('brdni')}>
                  <Translate contentKey="visaApplyApp.molbaRow.brdni">Brdni</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('brdni')} />
                </th>
                <th className="hand" onClick={sort('cel')}>
                  <Translate contentKey="visaApplyApp.molbaRow.cel">Cel</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('cel')} />
                </th>
                <th className="hand" onClick={sort('molDatVav')}>
                  <Translate contentKey="visaApplyApp.molbaRow.molDatVav">Mol Dat Vav</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('molDatVav')} />
                </th>
                <th className="hand" onClick={sort('gratis')}>
                  <Translate contentKey="visaApplyApp.molbaRow.gratis">Gratis</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('gratis')} />
                </th>
                <th className="hand" onClick={sort('imavisa')}>
                  <Translate contentKey="visaApplyApp.molbaRow.imavisa">Imavisa</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imavisa')} />
                </th>
                <th className="hand" onClick={sort('cenamol')}>
                  <Translate contentKey="visaApplyApp.molbaRow.cenamol">Cenamol</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cenamol')} />
                </th>
                <th className="hand" onClick={sort('cenacurr')}>
                  <Translate contentKey="visaApplyApp.molbaRow.cenacurr">Cenacurr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cenacurr')} />
                </th>
                <th className="hand" onClick={sort('maindest')}>
                  <Translate contentKey="visaApplyApp.molbaRow.maindest">Maindest</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('maindest')} />
                </th>
                <th className="hand" onClick={sort('maindestnm')}>
                  <Translate contentKey="visaApplyApp.molbaRow.maindestnm">Maindestnm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('maindestnm')} />
                </th>
                <th className="hand" onClick={sort('gkppDarj')}>
                  <Translate contentKey="visaApplyApp.molbaRow.gkppDarj">Gkpp Darj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('gkppDarj')} />
                </th>
                <th className="hand" onClick={sort('gkppText')}>
                  <Translate contentKey="visaApplyApp.molbaRow.gkppText">Gkpp Text</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('gkppText')} />
                </th>
                <th className="hand" onClick={sort('textIni')}>
                  <Translate contentKey="visaApplyApp.molbaRow.textIni">Text Ini</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('textIni')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {molbaRowList.map((molbaRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/molba-row/${molbaRow.id}`} color="link" size="sm">
                      {molbaRow.id}
                    </Button>
                  </td>
                  <td>{molbaRow.datVli ? <TextFormat type="date" value={molbaRow.datVli} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{molbaRow.datIzl ? <TextFormat type="date" value={molbaRow.datIzl} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{molbaRow.vidvis}</td>
                  <td>{molbaRow.brvl}</td>
                  <td>{molbaRow.vidus}</td>
                  <td>{molbaRow.valvis}</td>
                  <td>{molbaRow.brdni}</td>
                  <td>{molbaRow.cel}</td>
                  <td>{molbaRow.molDatVav ? <TextFormat type="date" value={molbaRow.molDatVav} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{molbaRow.gratis}</td>
                  <td>{molbaRow.imavisa}</td>
                  <td>{molbaRow.cenamol}</td>
                  <td>{molbaRow.cenacurr}</td>
                  <td>{molbaRow.maindest}</td>
                  <td>{molbaRow.maindestnm}</td>
                  <td>{molbaRow.gkppDarj}</td>
                  <td>{molbaRow.gkppText}</td>
                  <td>{molbaRow.textIni}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/molba-row/${molbaRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/molba-row/${molbaRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/molba-row/${molbaRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.molbaRow.home.notFound">No Molba Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={molbaRowList && molbaRowList.length > 0 ? '' : 'd-none'}>
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

export default MolbaRow;
