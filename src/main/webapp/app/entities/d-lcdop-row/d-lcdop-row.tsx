import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './d-lcdop-row.reducer';

export const DLcdopRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dLcdopRowList = useAppSelector(state => state.dLcdopRow.entities);
  const loading = useAppSelector(state => state.dLcdopRow.loading);
  const totalItems = useAppSelector(state => state.dLcdopRow.totalItems);

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
      <h2 id="d-lcdop-row-heading" data-cy="DLcdopRowHeading">
        <Translate contentKey="visaApplyApp.dLcdopRow.home.title">D Lcdop Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dLcdopRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-lcdop-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dLcdopRow.home.createLabel">Create new D Lcdop Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dLcdopRowList && dLcdopRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('ldMrjdarj')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldMrjdarj">Ld Mrjdarj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldMrjdarj')} />
                </th>
                <th className="hand" onClick={sort('ldMrjnm')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldMrjnm">Ld Mrjnm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldMrjnm')} />
                </th>
                <th className="hand" onClick={sort('ldMrjgraj')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldMrjgraj">Ld Mrjgraj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldMrjgraj')} />
                </th>
                <th className="hand" onClick={sort('ldZenen')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldZenen">Ld Zenen</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldZenen')} />
                </th>
                <th className="hand" onClick={sort('ldJitDarj')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldJitDarj">Ld Jit Darj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldJitDarj')} />
                </th>
                <th className="hand" onClick={sort('ldJitNm')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldJitNm">Ld Jit Nm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldJitNm')} />
                </th>
                <th className="hand" onClick={sort('ldJitUl')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldJitUl">Ld Jit Ul</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldJitUl')} />
                </th>
                <th className="hand" onClick={sort('ldTel')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldTel">Ld Tel</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldTel')} />
                </th>
                <th className="hand" onClick={sort('ldRabota')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldRabota">Ld Rabota</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldRabota')} />
                </th>
                <th className="hand" onClick={sort('ldProfkod')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldProfkod">Ld Profkod</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldProfkod')} />
                </th>
                <th className="hand" onClick={sort('ldProfesia')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldProfesia">Ld Profesia</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldProfesia')} />
                </th>
                <th className="hand" onClick={sort('ldSlDarj')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldSlDarj">Ld Sl Darj</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldSlDarj')} />
                </th>
                <th className="hand" onClick={sort('ldSlNm')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldSlNm">Ld Sl Nm</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldSlNm')} />
                </th>
                <th className="hand" onClick={sort('ldSlUl')}>
                  <Translate contentKey="visaApplyApp.dLcdopRow.ldSlUl">Ld Sl Ul</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ldSlUl')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dLcdopRowList.map((dLcdopRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-lcdop-row/${dLcdopRow.id}`} color="link" size="sm">
                      {dLcdopRow.id}
                    </Button>
                  </td>
                  <td>{dLcdopRow.ldMrjdarj}</td>
                  <td>{dLcdopRow.ldMrjnm}</td>
                  <td>{dLcdopRow.ldMrjgraj}</td>
                  <td>{dLcdopRow.ldZenen}</td>
                  <td>{dLcdopRow.ldJitDarj}</td>
                  <td>{dLcdopRow.ldJitNm}</td>
                  <td>{dLcdopRow.ldJitUl}</td>
                  <td>{dLcdopRow.ldTel}</td>
                  <td>{dLcdopRow.ldRabota}</td>
                  <td>{dLcdopRow.ldProfkod}</td>
                  <td>{dLcdopRow.ldProfesia}</td>
                  <td>{dLcdopRow.ldSlDarj}</td>
                  <td>{dLcdopRow.ldSlNm}</td>
                  <td>{dLcdopRow.ldSlUl}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-lcdop-row/${dLcdopRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-lcdop-row/${dLcdopRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/d-lcdop-row/${dLcdopRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.dLcdopRow.home.notFound">No D Lcdop Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dLcdopRowList && dLcdopRowList.length > 0 ? '' : 'd-none'}>
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

export default DLcdopRow;
