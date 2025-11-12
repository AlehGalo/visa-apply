import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DDomakinRow from './d-domakin-row';
import DDomakinRowDetail from './d-domakin-row-detail';
import DDomakinRowUpdate from './d-domakin-row-update';
import DDomakinRowDeleteDialog from './d-domakin-row-delete-dialog';

const DDomakinRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DDomakinRow />} />
    <Route path="new" element={<DDomakinRowUpdate />} />
    <Route path=":id">
      <Route index element={<DDomakinRowDetail />} />
      <Route path="edit" element={<DDomakinRowUpdate />} />
      <Route path="delete" element={<DDomakinRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DDomakinRowRoutes;
