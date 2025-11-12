import dayjs from 'dayjs';

export interface IDLcuzRow {
  id?: number;
  vidZp?: string;
  nacBel?: string;
  nacPasp?: number | null;
  paspVal?: dayjs.Dayjs;
  graj?: string;
  famil?: string;
  imena?: string;
  datRaj?: string;
  pol?: string;
  datIzd?: dayjs.Dayjs;
}

export const defaultValue: Readonly<IDLcuzRow> = {};
