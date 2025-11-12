import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DEurodaRow from './d-euroda-row';
import DEurodaRowDetail from './d-euroda-row-detail';
import DEurodaRowUpdate from './d-euroda-row-update';
import DEurodaRowDeleteDialog from './d-euroda-row-delete-dialog';

const DEurodaRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DEurodaRow />} />
    <Route path="new" element={<DEurodaRowUpdate />} />
    <Route path=":id">
      <Route index element={<DEurodaRowDetail />} />
      <Route path="edit" element={<DEurodaRowUpdate />} />
      <Route path="delete" element={<DEurodaRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DEurodaRowRoutes;
