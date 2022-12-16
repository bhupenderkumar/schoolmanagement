import dayjs from 'dayjs/esm';

import { IParent, NewParent } from './parent.model';

export const sampleWithRequiredData: IParent = {
  id: 76908,
  firstname: 'Communications',
  lastname: 'Vision-oriented',
  phoneNumber: 'Steel Avon',
};

export const sampleWithPartialData: IParent = {
  id: 70252,
  firstname: 'quantify Factors Music',
  lastname: 'synthesizing capacitor',
  phoneNumber: 'Dynamic Bedfordshire',
};

export const sampleWithFullData: IParent = {
  id: 51086,
  firstname: 'Reunion upward-trending relationships',
  lastname: 'Sausages Sausages',
  phoneNumber: 'stable',
  accountActive: true,
  address: 'open-source solutions',
  emaildId: 'Towels Digitized application',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  accountStartingDate: dayjs('2022-12-14'),
};

export const sampleWithNewData: NewParent = {
  firstname: 'Baby',
  lastname: 'transmitting Practical Customer',
  phoneNumber: 'Metrics Rubber',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
