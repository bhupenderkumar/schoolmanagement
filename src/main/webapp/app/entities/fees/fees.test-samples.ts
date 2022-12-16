import dayjs from 'dayjs/esm';

import { IFees, NewFees } from './fees.model';

export const sampleWithRequiredData: IFees = {
  id: 48932,
  date: dayjs('2022-12-14'),
};

export const sampleWithPartialData: IFees = {
  id: 43642,
  date: dayjs('2022-12-14'),
};

export const sampleWithFullData: IFees = {
  id: 57754,
  date: dayjs('2022-12-13'),
};

export const sampleWithNewData: NewFees = {
  date: dayjs('2022-12-14'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
