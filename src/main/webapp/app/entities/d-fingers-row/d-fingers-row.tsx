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

import { getEntities } from './d-fingers-row.reducer';

export const DFingersRow = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const dFingersRowList = useAppSelector(state => state.dFingersRow.entities);
  const loading = useAppSelector(state => state.dFingersRow.loading);
  const totalItems = useAppSelector(state => state.dFingersRow.totalItems);

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
      <h2 id="d-fingers-row-heading" data-cy="DFingersRowHeading">
        <Translate contentKey="visaApplyApp.dFingersRow.home.title">D Fingers Rows</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="visaApplyApp.dFingersRow.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-fingers-row/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="visaApplyApp.dFingersRow.home.createLabel">Create new D Fingers Row</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dFingersRowList && dFingersRowList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('fnDatreg')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnDatreg">Fn Datreg</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnDatreg')} />
                </th>
                <th className="hand" onClick={sort('fnDatvav')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnDatvav">Fn Datvav</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnDatvav')} />
                </th>
                <th className="hand" onClick={sort('fnUsera')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnUsera">Fn Usera</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnUsera')} />
                </th>
                <th className="hand" onClick={sort('fnSid')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnSid">Fn Sid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnSid')} />
                </th>
                <th className="hand" onClick={sort('fnPnr')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnPnr">Fn Pnr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnPnr')} />
                </th>
                <th className="hand" onClick={sort('fnVidmol')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnVidmol">Fn Vidmol</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnVidmol')} />
                </th>
                <th className="hand" onClick={sort('fnDrugi')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnDrugi">Fn Drugi</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnDrugi')} />
                </th>
                <th className="hand" onClick={sort('fnDeviceid')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnDeviceid">Fn Deviceid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnDeviceid')} />
                </th>
                <th className="hand" onClick={sort('fnScanres')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnScanres">Fn Scanres</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnScanres')} />
                </th>
                <th className="hand" onClick={sort('fnWidth')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnWidth">Fn Width</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnWidth')} />
                </th>
                <th className="hand" onClick={sort('fnHeight')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnHeight">Fn Height</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnHeight')} />
                </th>
                <th className="hand" onClick={sort('fnPixeldepth')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnPixeldepth">Fn Pixeldepth</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnPixeldepth')} />
                </th>
                <th className="hand" onClick={sort('fnCompressalgo')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnCompressalgo">Fn Compressalgo</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnCompressalgo')} />
                </th>
                <th className="hand" onClick={sort('fnFingerposition')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnFingerposition">Fn Fingerposition</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnFingerposition')} />
                </th>
                <th className="hand" onClick={sort('fnImagequality')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnImagequality">Fn Imagequality</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnImagequality')} />
                </th>
                <th className="hand" onClick={sort('fnImage')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnImage">Fn Image</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnImage')} />
                </th>
                <th className="hand" onClick={sort('fnImglen')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnImglen">Fn Imglen</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnImglen')} />
                </th>
                <th className="hand" onClick={sort('fnNottakenreason')}>
                  <Translate contentKey="visaApplyApp.dFingersRow.fnNottakenreason">Fn Nottakenreason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fnNottakenreason')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dFingersRowList.map((dFingersRow, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-fingers-row/${dFingersRow.id}`} color="link" size="sm">
                      {dFingersRow.id}
                    </Button>
                  </td>
                  <td>
                    {dFingersRow.fnDatreg ? <TextFormat type="date" value={dFingersRow.fnDatreg} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{dFingersRow.fnDatvav ? <TextFormat type="date" value={dFingersRow.fnDatvav} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{dFingersRow.fnUsera}</td>
                  <td>{dFingersRow.fnSid}</td>
                  <td>{dFingersRow.fnPnr}</td>
                  <td>{dFingersRow.fnVidmol}</td>
                  <td>{dFingersRow.fnDrugi}</td>
                  <td>{dFingersRow.fnDeviceid}</td>
                  <td>{dFingersRow.fnScanres}</td>
                  <td>{dFingersRow.fnWidth}</td>
                  <td>{dFingersRow.fnHeight}</td>
                  <td>{dFingersRow.fnPixeldepth}</td>
                  <td>{dFingersRow.fnCompressalgo}</td>
                  <td>{dFingersRow.fnFingerposition}</td>
                  <td>{dFingersRow.fnImagequality}</td>
                  <td>{dFingersRow.fnImage}</td>
                  <td>{dFingersRow.fnImglen}</td>
                  <td>{dFingersRow.fnNottakenreason}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-fingers-row/${dFingersRow.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-fingers-row/${dFingersRow.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/d-fingers-row/${dFingersRow.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="visaApplyApp.dFingersRow.home.notFound">No D Fingers Rows found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={dFingersRowList && dFingersRowList.length > 0 ? '' : 'd-none'}>
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

export default DFingersRow;
