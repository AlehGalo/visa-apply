import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import MolbaRow from './molba-row';
import MolbaRowDetail from './molba-row-detail';
import MolbaRowUpdate from './molba-row-update';
import MolbaRowDeleteDialog from './molba-row-delete-dialog';

const MolbaRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<MolbaRow />} />
    <Route path="new" element={<MolbaRowUpdate />} />
    <Route path=":id">
      <Route index element={<MolbaRowDetail />} />
      <Route path="edit" element={<MolbaRowUpdate />} />
      <Route path="delete" element={<MolbaRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MolbaRowRoutes;
