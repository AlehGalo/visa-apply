import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DBastaRow from './d-basta-row';
import DBastaRowDetail from './d-basta-row-detail';
import DBastaRowUpdate from './d-basta-row-update';
import DBastaRowDeleteDialog from './d-basta-row-delete-dialog';

const DBastaRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DBastaRow />} />
    <Route path="new" element={<DBastaRowUpdate />} />
    <Route path=":id">
      <Route index element={<DBastaRowDetail />} />
      <Route path="edit" element={<DBastaRowUpdate />} />
      <Route path="delete" element={<DBastaRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DBastaRowRoutes;
