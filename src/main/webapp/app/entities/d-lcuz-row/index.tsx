import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DLcuzRow from './d-lcuz-row';
import DLcuzRowDetail from './d-lcuz-row-detail';
import DLcuzRowUpdate from './d-lcuz-row-update';
import DLcuzRowDeleteDialog from './d-lcuz-row-delete-dialog';

const DLcuzRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DLcuzRow />} />
    <Route path="new" element={<DLcuzRowUpdate />} />
    <Route path=":id">
      <Route index element={<DLcuzRowDetail />} />
      <Route path="edit" element={<DLcuzRowUpdate />} />
      <Route path="delete" element={<DLcuzRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DLcuzRowRoutes;
