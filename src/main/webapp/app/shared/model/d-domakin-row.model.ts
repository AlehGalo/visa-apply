export interface IDDomakinRow {
  id?: number;
  dmVid?: string;
  nomPok?: string;
  domGraj?: string;
  domFamil?: string;
  domIme?: string;
  domDarj?: string;
  domNm?: number | null;
  domAdres?: string;
  vedDarj?: string;
  vedNm?: string;
}

export const defaultValue: Readonly<IDDomakinRow> = {};
