import dayjs from 'dayjs/esm';

export interface ITeacher {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  address?: string | null;
  photo?: string | null;
  photoContentType?: string | null;
  idProof?: string | null;
  idProofContentType?: string | null;
  phoneNumber?: string | null;
  emergencyContactNumber?: string | null;
  joiningDate?: dayjs.Dayjs | null;
}

export type NewTeacher = Omit<ITeacher, 'id'> & { id: null };
