import dayjs from 'dayjs/esm';

import { ITeacher, NewTeacher } from './teacher.model';

export const sampleWithRequiredData: ITeacher = {
  id: 42798,
  firstName: 'Fiona',
  lastName: 'Lueilwitz',
  address: 'Borders Ohio',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  idProof: '../fake-data/blob/hipster.png',
  idProofContentType: 'unknown',
  phoneNumber: 'sky',
  emergencyContactNumber: 'web-readiness Cloned District',
};

export const sampleWithPartialData: ITeacher = {
  id: 2220,
  firstName: 'Peyton',
  lastName: 'Wilderman',
  address: 'Soft',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  idProof: '../fake-data/blob/hipster.png',
  idProofContentType: 'unknown',
  phoneNumber: 'Borders RAM Associate',
  emergencyContactNumber: 'input Station supply-chains',
  joiningDate: dayjs('2022-12-13'),
};

export const sampleWithFullData: ITeacher = {
  id: 70404,
  firstName: 'Georgette',
  lastName: 'Will',
  address: 'Practical Berkshire',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  idProof: '../fake-data/blob/hipster.png',
  idProofContentType: 'unknown',
  phoneNumber: 'Drives Architect Greens',
  emergencyContactNumber: 'viral rich',
  joiningDate: dayjs('2022-12-13'),
};

export const sampleWithNewData: NewTeacher = {
  firstName: 'Wellington',
  lastName: 'Jacobson',
  address: 'bypass set',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  idProof: '../fake-data/blob/hipster.png',
  idProofContentType: 'unknown',
  phoneNumber: 'Lilangeni generate lime',
  emergencyContactNumber: 'partnerships Dong Global',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
