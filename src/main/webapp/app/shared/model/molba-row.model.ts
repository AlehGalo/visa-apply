import dayjs from 'dayjs';

export interface IMolbaRow {
  id?: number;
  datVli?: dayjs.Dayjs;
  datIzl?: dayjs.Dayjs;
  vidvis?: string;
  brvl?: number | null;
  vidus?: string;
  valvis?: string;
  brdni?: number | null;
  cel?: number | null;
  molDatVav?: dayjs.Dayjs;
  gratis?: string;
  imavisa?: string;
  cenamol?: number | null;
  cenacurr?: string;
  maindest?: string;
  maindestnm?: string;
  gkppDarj?: string;
  gkppText?: string;
  textIni?: string;
}

export const defaultValue: Readonly<IMolbaRow> = {};
