import dayjs from 'dayjs/esm';

import { IStudent, NewStudent } from './student.model';

export const sampleWithRequiredData: IStudent = {
  id: 23105,
  firstname: 'Franc Division Regional',
  lastname: 'Refined Auto',
  address: 'Handmade Ngultrum',
  phoneNumber: 'orchid ivory',
  emergencyContactNumber: 'Path e-markets',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  childIdProof: '../fake-data/blob/hipster.png',
  childIdProofContentType: 'unknown',
  parentIdProof: '../fake-data/blob/hipster.png',
  parentIdProofContentType: 'unknown',
};

export const sampleWithPartialData: IStudent = {
  id: 62737,
  firstname: 'streamline',
  lastname: 'Front-line CSS',
  address: 'GB Money multi-state',
  phoneNumber: 'Buckinghamshire Nuevo Direct',
  emergencyContactNumber: 'Loti',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  childIdProof: '../fake-data/blob/hipster.png',
  childIdProofContentType: 'unknown',
  parentIdProof: '../fake-data/blob/hipster.png',
  parentIdProofContentType: 'unknown',
  anyOtherDocument: '../fake-data/blob/hipster.png',
  anyOtherDocumentContentType: 'unknown',
  familyDoctorName: 'state Awesome',
};

export const sampleWithFullData: IStudent = {
  id: 59739,
  firstname: 'compress',
  lastname: 'seamless',
  address: 'wireless',
  phoneNumber: 'cutting-edge bypass Dinar',
  emergencyContactNumber: 'Franc',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  childIdProof: '../fake-data/blob/hipster.png',
  childIdProofContentType: 'unknown',
  parentIdProof: '../fake-data/blob/hipster.png',
  parentIdProofContentType: 'unknown',
  anyOtherDocument: '../fake-data/blob/hipster.png',
  anyOtherDocumentContentType: 'unknown',
  anyDisease: 'hack',
  familyDoctorName: 'RAM calculate Bike',
  familyDoctorNumber: 'e-enable bus',
  admissionDate: dayjs('2022-12-14'),
  tcDate: dayjs('2022-12-14'),
};

export const sampleWithNewData: NewStudent = {
  firstname: 'deliverables homogeneous Group',
  lastname: 'Pants Supervisor',
  address: 'payment SSL',
  phoneNumber: 'Industrial Village Credit',
  emergencyContactNumber: 'Sleek',
  photo: '../fake-data/blob/hipster.png',
  photoContentType: 'unknown',
  childIdProof: '../fake-data/blob/hipster.png',
  childIdProofContentType: 'unknown',
  parentIdProof: '../fake-data/blob/hipster.png',
  parentIdProofContentType: 'unknown',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
