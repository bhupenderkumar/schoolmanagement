import dayjs from 'dayjs/esm';

export interface INotification {
  id: number;
  subject?: string | null;
  description?: string | null;
  notificationDate?: dayjs.Dayjs | null;
}

export type NewNotification = Omit<INotification, 'id'> & { id: null };
