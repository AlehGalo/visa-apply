export interface IDMsgheaderRow {
  id?: number;
  mhKscreated?: string;
  mhRegnom?: number | null;
  mhVfsrefno?: string;
  mhUsera?: string;
  mhDatvav?: string;
}

export const defaultValue: Readonly<IDMsgheaderRow> = {};
