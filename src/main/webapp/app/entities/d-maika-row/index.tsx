import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DMaikaRow from './d-maika-row';
import DMaikaRowDetail from './d-maika-row-detail';
import DMaikaRowUpdate from './d-maika-row-update';
import DMaikaRowDeleteDialog from './d-maika-row-delete-dialog';

const DMaikaRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DMaikaRow />} />
    <Route path="new" element={<DMaikaRowUpdate />} />
    <Route path=":id">
      <Route index element={<DMaikaRowDetail />} />
      <Route path="edit" element={<DMaikaRowUpdate />} />
      <Route path="delete" element={<DMaikaRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DMaikaRowRoutes;
