import dayjs from 'dayjs/esm';
import { IStudent } from 'app/entities/student/student.model';

export interface IFees {
  id: number;
  date?: dayjs.Dayjs | null;
  student?: Pick<IStudent, 'id'> | null;
}

export type NewFees = Omit<IFees, 'id'> & { id: null };
