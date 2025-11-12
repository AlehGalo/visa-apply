import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/d-basta-row">
        <Translate contentKey="global.menu.entities.dBastaRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-domakin-row">
        <Translate contentKey="global.menu.entities.dDomakinRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-euroda-row">
        <Translate contentKey="global.menu.entities.dEurodaRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-fingers-row">
        <Translate contentKey="global.menu.entities.dFingersRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-images-row">
        <Translate contentKey="global.menu.entities.dImagesRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-lcdop-row">
        <Translate contentKey="global.menu.entities.dLcdopRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-lcuz-row">
        <Translate contentKey="global.menu.entities.dLcuzRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-loadosf-entity">
        <Translate contentKey="global.menu.entities.dLoadosfEntity" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-maika-row">
        <Translate contentKey="global.menu.entities.dMaikaRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-msgheader-row">
        <Translate contentKey="global.menu.entities.dMsgheaderRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-sapruga-row">
        <Translate contentKey="global.menu.entities.dSaprugaRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-voit-row">
        <Translate contentKey="global.menu.entities.dVoitRow" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/molba-row">
        <Translate contentKey="global.menu.entities.molbaRow" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
