import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';
import DBastaRow from './d-basta-row';
import DDomakinRow from './d-domakin-row';
import DEurodaRow from './d-euroda-row';
import DFingersRow from './d-fingers-row';
import DImagesRow from './d-images-row';
import DLcdopRow from './d-lcdop-row';
import DLcuzRow from './d-lcuz-row';
import DLoadosfEntity from './d-loadosf-entity';
import DMaikaRow from './d-maika-row';
import DMsgheaderRow from './d-msgheader-row';
import DSaprugaRow from './d-sapruga-row';
import DVoitRow from './d-voit-row';
import MolbaRow from './molba-row';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="d-basta-row/*" element={<DBastaRow/>}/>
        <Route path="d-domakin-row/*" element={<DDomakinRow />} />
        <Route path="d-euroda-row/*" element={<DEurodaRow />} />
        <Route path="d-fingers-row/*" element={<DFingersRow />} />
        <Route path="d-images-row/*" element={<DImagesRow />} />
        <Route path="d-lcdop-row/*" element={<DLcdopRow />} />
        <Route path="d-lcuz-row/*" element={<DLcuzRow />} />
        <Route path="d-loadosf-entity/*" element={<DLoadosfEntity />} />
        <Route path="d-maika-row/*" element={<DMaikaRow />} />
        <Route path="d-msgheader-row/*" element={<DMsgheaderRow />} />
        <Route path="d-sapruga-row/*" element={<DSaprugaRow />} />
        <Route path="d-voit-row/*" element={<DVoitRow />} />
        <Route path="molba-row/*" element={<MolbaRow />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
