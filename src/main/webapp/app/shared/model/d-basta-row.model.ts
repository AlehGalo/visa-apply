export interface IDBastaRow {
  id?: number;
  dcFamil?: string;
  dcImena?: string;
  dcPol?: string;
  dcGrad?: string;
  dcUlica?: string;
}

export const defaultValue: Readonly<IDBastaRow> = {};
