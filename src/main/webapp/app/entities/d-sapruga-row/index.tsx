import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DSaprugaRow from './d-sapruga-row';
import DSaprugaRowDetail from './d-sapruga-row-detail';
import DSaprugaRowUpdate from './d-sapruga-row-update';
import DSaprugaRowDeleteDialog from './d-sapruga-row-delete-dialog';

const DSaprugaRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DSaprugaRow />} />
    <Route path="new" element={<DSaprugaRowUpdate />} />
    <Route path=":id">
      <Route index element={<DSaprugaRowDetail />} />
      <Route path="edit" element={<DSaprugaRowUpdate />} />
      <Route path="delete" element={<DSaprugaRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DSaprugaRowRoutes;
