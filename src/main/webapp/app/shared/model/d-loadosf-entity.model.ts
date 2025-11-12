import { IDMsgheaderRow } from 'app/shared/model/d-msgheader-row.model';
import { IDLcuzRow } from 'app/shared/model/d-lcuz-row.model';
import { IDLcdopRow } from 'app/shared/model/d-lcdop-row.model';
import { IDBastaRow } from 'app/shared/model/d-basta-row.model';
import { IDMaikaRow } from 'app/shared/model/d-maika-row.model';
import { IDSaprugaRow } from 'app/shared/model/d-sapruga-row.model';
import { IMolbaRow } from 'app/shared/model/molba-row.model';
import { IDDomakinRow } from 'app/shared/model/d-domakin-row.model';
import { IDEurodaRow } from 'app/shared/model/d-euroda-row.model';
import { IDVoitRow } from 'app/shared/model/d-voit-row.model';
import { IDImagesRow } from 'app/shared/model/d-images-row.model';
import { IDFingersRow } from 'app/shared/model/d-fingers-row.model';

export interface IDLoadosfEntity {
  id?: number;
  version?: number | null;
  msgheader?: IDMsgheaderRow | null;
  lcuz?: IDLcuzRow | null;
  lcdop?: IDLcdopRow | null;
  bastaEntity?: IDBastaRow | null;
  maika?: IDMaikaRow | null;
  sapruga?: IDSaprugaRow | null;
  molba?: IMolbaRow | null;
  domakin?: IDDomakinRow | null;
  euroda?: IDEurodaRow | null;
  voit?: IDVoitRow | null;
  images?: IDImagesRow | null;
  fingers?: IDFingersRow | null;
}

export const defaultValue: Readonly<IDLoadosfEntity> = {};
