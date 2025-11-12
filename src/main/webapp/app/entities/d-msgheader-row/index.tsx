import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DMsgheaderRow from './d-msgheader-row';
import DMsgheaderRowDetail from './d-msgheader-row-detail';
import DMsgheaderRowUpdate from './d-msgheader-row-update';
import DMsgheaderRowDeleteDialog from './d-msgheader-row-delete-dialog';

const DMsgheaderRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DMsgheaderRow />} />
    <Route path="new" element={<DMsgheaderRowUpdate />} />
    <Route path=":id">
      <Route index element={<DMsgheaderRowDetail />} />
      <Route path="edit" element={<DMsgheaderRowUpdate />} />
      <Route path="delete" element={<DMsgheaderRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DMsgheaderRowRoutes;
