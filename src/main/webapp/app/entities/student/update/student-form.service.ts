import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStudent, NewStudent } from '../student.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStudent for edit and NewStudentFormGroupInput for create.
 */
type StudentFormGroupInput = IStudent | PartialWithRequiredKeyOf<NewStudent>;

type StudentFormDefaults = Pick<NewStudent, 'id' | 'attendances'>;

type StudentFormGroupContent = {
  id: FormControl<IStudent['id'] | NewStudent['id']>;
  firstname: FormControl<IStudent['firstname']>;
  lastname: FormControl<IStudent['lastname']>;
  address: FormControl<IStudent['address']>;
  phoneNumber: FormControl<IStudent['phoneNumber']>;
  emergencyContactNumber: FormControl<IStudent['emergencyContactNumber']>;
  photo: FormControl<IStudent['photo']>;
  photoContentType: FormControl<IStudent['photoContentType']>;
  childIdProof: FormControl<IStudent['childIdProof']>;
  childIdProofContentType: FormControl<IStudent['childIdProofContentType']>;
  parentIdProof: FormControl<IStudent['parentIdProof']>;
  parentIdProofContentType: FormControl<IStudent['parentIdProofContentType']>;
  anyOtherDocument: FormControl<IStudent['anyOtherDocument']>;
  anyOtherDocumentContentType: FormControl<IStudent['anyOtherDocumentContentType']>;
  anyDisease: FormControl<IStudent['anyDisease']>;
  familyDoctorName: FormControl<IStudent['familyDoctorName']>;
  familyDoctorNumber: FormControl<IStudent['familyDoctorNumber']>;
  admissionDate: FormControl<IStudent['admissionDate']>;
  tcDate: FormControl<IStudent['tcDate']>;
  parentId: FormControl<IStudent['parentId']>;
  attendances: FormControl<IStudent['attendances']>;
};

export type StudentFormGroup = FormGroup<StudentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StudentFormService {
  createStudentFormGroup(student: StudentFormGroupInput = { id: null }): StudentFormGroup {
    const studentRawValue = {
      ...this.getFormDefaults(),
      ...student,
    };
    return new FormGroup<StudentFormGroupContent>({
      id: new FormControl(
        { value: studentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstname: new FormControl(studentRawValue.firstname, {
        validators: [Validators.required],
      }),
      lastname: new FormControl(studentRawValue.lastname, {
        validators: [Validators.required],
      }),
      address: new FormControl(studentRawValue.address, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(studentRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
      emergencyContactNumber: new FormControl(studentRawValue.emergencyContactNumber, {
        validators: [Validators.required],
      }),
      photo: new FormControl(studentRawValue.photo, {
        validators: [Validators.required],
      }),
      photoContentType: new FormControl(studentRawValue.photoContentType),
      childIdProof: new FormControl(studentRawValue.childIdProof, {
        validators: [Validators.required],
      }),
      childIdProofContentType: new FormControl(studentRawValue.childIdProofContentType),
      parentIdProof: new FormControl(studentRawValue.parentIdProof, {
        validators: [Validators.required],
      }),
      parentIdProofContentType: new FormControl(studentRawValue.parentIdProofContentType),
      anyOtherDocument: new FormControl(studentRawValue.anyOtherDocument),
      anyOtherDocumentContentType: new FormControl(studentRawValue.anyOtherDocumentContentType),
      anyDisease: new FormControl(studentRawValue.anyDisease),
      familyDoctorName: new FormControl(studentRawValue.familyDoctorName),
      familyDoctorNumber: new FormControl(studentRawValue.familyDoctorNumber),
      admissionDate: new FormControl(studentRawValue.admissionDate),
      tcDate: new FormControl(studentRawValue.tcDate),
      parentId: new FormControl(studentRawValue.parentId),
      attendances: new FormControl(studentRawValue.attendances ?? []),
    });
  }

  getStudent(form: StudentFormGroup): IStudent | NewStudent {
    return form.getRawValue() as IStudent | NewStudent;
  }

  resetForm(form: StudentFormGroup, student: StudentFormGroupInput): void {
    const studentRawValue = { ...this.getFormDefaults(), ...student };
    form.reset(
      {
        ...studentRawValue,
        id: { value: studentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): StudentFormDefaults {
    return {
      id: null,
      attendances: [],
    };
  }
}
