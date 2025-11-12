import dayjs from 'dayjs';

export interface IDFingersRow {
  id?: number;
  fnDatreg?: dayjs.Dayjs;
  fnDatvav?: dayjs.Dayjs;
  fnUsera?: string;
  fnSid?: number | null;
  fnPnr?: number | null;
  fnVidmol?: string;
  fnDrugi?: string;
  fnDeviceid?: number | null;
  fnScanres?: number | null;
  fnWidth?: number | null;
  fnHeight?: number | null;
  fnPixeldepth?: number | null;
  fnCompressalgo?: number | null;
  fnFingerposition?: string;
  fnImagequality?: number | null;
  fnImage?: string | null;
  fnImglen?: number | null;
  fnNottakenreason?: string | null;
}

export const defaultValue: Readonly<IDFingersRow> = {};
