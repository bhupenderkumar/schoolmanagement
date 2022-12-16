import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITeacher, NewTeacher } from '../teacher.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITeacher for edit and NewTeacherFormGroupInput for create.
 */
type TeacherFormGroupInput = ITeacher | PartialWithRequiredKeyOf<NewTeacher>;

type TeacherFormDefaults = Pick<NewTeacher, 'id'>;

type TeacherFormGroupContent = {
  id: FormControl<ITeacher['id'] | NewTeacher['id']>;
  firstName: FormControl<ITeacher['firstName']>;
  lastName: FormControl<ITeacher['lastName']>;
  address: FormControl<ITeacher['address']>;
  photo: FormControl<ITeacher['photo']>;
  photoContentType: FormControl<ITeacher['photoContentType']>;
  idProof: FormControl<ITeacher['idProof']>;
  idProofContentType: FormControl<ITeacher['idProofContentType']>;
  phoneNumber: FormControl<ITeacher['phoneNumber']>;
  emergencyContactNumber: FormControl<ITeacher['emergencyContactNumber']>;
  joiningDate: FormControl<ITeacher['joiningDate']>;
};

export type TeacherFormGroup = FormGroup<TeacherFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TeacherFormService {
  createTeacherFormGroup(teacher: TeacherFormGroupInput = { id: null }): TeacherFormGroup {
    const teacherRawValue = {
      ...this.getFormDefaults(),
      ...teacher,
    };
    return new FormGroup<TeacherFormGroupContent>({
      id: new FormControl(
        { value: teacherRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstName: new FormControl(teacherRawValue.firstName, {
        validators: [Validators.required],
      }),
      lastName: new FormControl(teacherRawValue.lastName, {
        validators: [Validators.required],
      }),
      address: new FormControl(teacherRawValue.address, {
        validators: [Validators.required],
      }),
      photo: new FormControl(teacherRawValue.photo, {
        validators: [Validators.required],
      }),
      photoContentType: new FormControl(teacherRawValue.photoContentType),
      idProof: new FormControl(teacherRawValue.idProof, {
        validators: [Validators.required],
      }),
      idProofContentType: new FormControl(teacherRawValue.idProofContentType),
      phoneNumber: new FormControl(teacherRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
      emergencyContactNumber: new FormControl(teacherRawValue.emergencyContactNumber, {
        validators: [Validators.required],
      }),
      joiningDate: new FormControl(teacherRawValue.joiningDate),
    });
  }

  getTeacher(form: TeacherFormGroup): ITeacher | NewTeacher {
    return form.getRawValue() as ITeacher | NewTeacher;
  }

  resetForm(form: TeacherFormGroup, teacher: TeacherFormGroupInput): void {
    const teacherRawValue = { ...this.getFormDefaults(), ...teacher };
    form.reset(
      {
        ...teacherRawValue,
        id: { value: teacherRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TeacherFormDefaults {
    return {
      id: null,
    };
  }
}
