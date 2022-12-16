import dayjs from 'dayjs/esm';

import { INotification, NewNotification } from './notification.model';

export const sampleWithRequiredData: INotification = {
  id: 30621,
  subject: 'model',
  description: 'withdrawal Brooks Towels',
  notificationDate: dayjs('2022-12-16'),
};

export const sampleWithPartialData: INotification = {
  id: 51163,
  subject: 'Garden quantify',
  description: 'Swedish',
  notificationDate: dayjs('2022-12-16'),
};

export const sampleWithFullData: INotification = {
  id: 32316,
  subject: 'invoice Object-based',
  description: 'XSS',
  notificationDate: dayjs('2022-12-16'),
};

export const sampleWithNewData: NewNotification = {
  subject: 'hacking Wyoming',
  description: 'tan',
  notificationDate: dayjs('2022-12-15'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
