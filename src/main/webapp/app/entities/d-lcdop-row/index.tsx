import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DLcdopRow from './d-lcdop-row';
import DLcdopRowDetail from './d-lcdop-row-detail';
import DLcdopRowUpdate from './d-lcdop-row-update';
import DLcdopRowDeleteDialog from './d-lcdop-row-delete-dialog';

const DLcdopRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DLcdopRow />} />
    <Route path="new" element={<DLcdopRowUpdate />} />
    <Route path=":id">
      <Route index element={<DLcdopRowDetail />} />
      <Route path="edit" element={<DLcdopRowUpdate />} />
      <Route path="delete" element={<DLcdopRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DLcdopRowRoutes;
