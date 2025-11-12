import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DVoitRow from './d-voit-row';
import DVoitRowDetail from './d-voit-row-detail';
import DVoitRowUpdate from './d-voit-row-update';
import DVoitRowDeleteDialog from './d-voit-row-delete-dialog';

const DVoitRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DVoitRow />} />
    <Route path="new" element={<DVoitRowUpdate />} />
    <Route path=":id">
      <Route index element={<DVoitRowDetail />} />
      <Route path="edit" element={<DVoitRowUpdate />} />
      <Route path="delete" element={<DVoitRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DVoitRowRoutes;
