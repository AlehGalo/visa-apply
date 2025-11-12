import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DImagesRow from './d-images-row';
import DImagesRowDetail from './d-images-row-detail';
import DImagesRowUpdate from './d-images-row-update';
import DImagesRowDeleteDialog from './d-images-row-delete-dialog';

const DImagesRowRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DImagesRow />} />
    <Route path="new" element={<DImagesRowUpdate />} />
    <Route path=":id">
      <Route index element={<DImagesRowDetail />} />
      <Route path="edit" element={<DImagesRowUpdate />} />
      <Route path="delete" element={<DImagesRowDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DImagesRowRoutes;
