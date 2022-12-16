import dayjs from 'dayjs/esm';
import { IStudent } from 'app/entities/student/student.model';

export interface IAttendance {
  id: number;
  date?: dayjs.Dayjs | null;
  students?: Pick<IStudent, 'id'>[] | null;
}

export type NewAttendance = Omit<IAttendance, 'id'> & { id: null };
