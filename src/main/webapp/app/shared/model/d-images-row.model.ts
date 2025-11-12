export interface IDImagesRow {
  id?: number;
  imDevicetype?: number | null;
  imWidth?: number | null;
  imHeight?: number | null;
  imImglen?: number | null;
  imImage?: string;
}

export const defaultValue: Readonly<IDImagesRow> = {};
