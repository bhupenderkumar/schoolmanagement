import dayjs from 'dayjs/esm';
import { IParent } from 'app/entities/parent/parent.model';
import { IAttendance } from 'app/entities/attendance/attendance.model';

export interface IStudent {
  id: number;
  firstname?: string | null;
  lastname?: string | null;
  address?: string | null;
  phoneNumber?: string | null;
  emergencyContactNumber?: string | null;
  photo?: string | null;
  photoContentType?: string | null;
  childIdProof?: string | null;
  childIdProofContentType?: string | null;
  parentIdProof?: string | null;
  parentIdProofContentType?: string | null;
  anyOtherDocument?: string | null;
  anyOtherDocumentContentType?: string | null;
  anyDisease?: string | null;
  familyDoctorName?: string | null;
  familyDoctorNumber?: string | null;
  admissionDate?: dayjs.Dayjs | null;
  tcDate?: dayjs.Dayjs | null;
  parentId?: Pick<IParent, 'id'> | null;
  attendances?: Pick<IAttendance, 'id'>[] | null;
}

export type NewStudent = Omit<IStudent, 'id'> & { id: null };
