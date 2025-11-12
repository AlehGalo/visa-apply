import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DFingersRow from './d-fingers-row';
import DFingersRowDetail from './d-fingers-row-detail';
import DFingersRowUpdate from './d-fingers-row-update';
import DFingersRowDeleteDialog from './d-fingers-row-delete-dialog';

const DFingersRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DFingersRow />} />
    <Route path="new" element={<DFingersRowUpdate />} />
    <Route path=":id">
      <Route index element={<DFingersRowDetail />} />
      <Route path="edit" element={<DFingersRowUpdate />} />
      <Route path="delete" element={<DFingersRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DFingersRowRoutes;
