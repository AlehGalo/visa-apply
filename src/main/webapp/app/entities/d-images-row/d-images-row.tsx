import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './d-images-row.reducer';

export const DImagesRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dImagesRowList = useAppSelector(state => state.dImagesRow.entities);
  const loading = useAppSelector(state => state.dImagesRow.loading);
  const totalItems = useAppSelector(state => state.dImagesRow.totalItems);

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
      <h2 id="d-images-row-heading" data-cy="DImagesRowHeading">
        <Translate contentKey="visaApplyApp.dImagesRow.home.title">D Images Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dImagesRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-images-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dImagesRow.home.createLabel">Create new D Images Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dImagesRowList && dImagesRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dImagesRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('imDevicetype')}>
                  <Translate contentKey="visaApplyApp.dImagesRow.imDevicetype">Im Devicetype</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imDevicetype')} />
                </th>
                <th className="hand" onClick={sort('imWidth')}>
                  <Translate contentKey="visaApplyApp.dImagesRow.imWidth">Im Width</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imWidth')} />
                </th>
                <th className="hand" onClick={sort('imHeight')}>
                  <Translate contentKey="visaApplyApp.dImagesRow.imHeight">Im Height</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imHeight')} />
                </th>
                <th className="hand" onClick={sort('imImglen')}>
                  <Translate contentKey="visaApplyApp.dImagesRow.imImglen">Im Imglen</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imImglen')} />
                </th>
                <th className="hand" onClick={sort('imImage')}>
                  <Translate contentKey="visaApplyApp.dImagesRow.imImage">Im Image</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imImage')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dImagesRowList.map((dImagesRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-images-row/${dImagesRow.id}`} color="link" size="sm">
                      {dImagesRow.id}
                    </Button>
                  </td>
                  <td>{dImagesRow.imDevicetype}</td>
                  <td>{dImagesRow.imWidth}</td>
                  <td>{dImagesRow.imHeight}</td>
                  <td>{dImagesRow.imImglen}</td>
                  <td>{dImagesRow.imImage}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-images-row/${dImagesRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-images-row/${dImagesRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/d-images-row/${dImagesRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.dImagesRow.home.notFound">No D Images Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dImagesRowList && dImagesRowList.length > 0 ? '' : 'd-none'}>
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

export default DImagesRow;
