import dayjs from 'dayjs/esm';

export interface IParent {
  id: number;
  firstname?: string | null;
  lastname?: string | null;
  phoneNumber?: string | null;
  accountActive?: boolean | null;
  address?: string | null;
  emaildId?: string | null;
  photo?: string | null;
  photoContentType?: string | null;
  accountStartingDate?: dayjs.Dayjs | null;
}

export type NewParent = Omit<IParent, 'id'> & { id: null };
