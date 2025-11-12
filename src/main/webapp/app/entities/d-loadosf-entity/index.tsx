import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DLoadosfEntity from './d-loadosf-entity';
import DLoadosfEntityDetail from './d-loadosf-entity-detail';
import DLoadosfEntityUpdate from './d-loadosf-entity-update';
import DLoadosfEntityDeleteDialog from './d-loadosf-entity-delete-dialog';

const DLoadosfEntityRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DLoadosfEntity />} />
    <Route path="new" element={<DLoadosfEntityUpdate />} />
    <Route path=":id">
      <Route index element={<DLoadosfEntityDetail />} />
      <Route path="edit" element={<DLoadosfEntityUpdate />} />
      <Route path="delete" element={<DLoadosfEntityDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DLoadosfEntityRoutes;
