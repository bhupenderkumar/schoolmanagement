import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IParent, NewParent } from '../parent.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IParent for edit and NewParentFormGroupInput for create.
 */
type ParentFormGroupInput = IParent | PartialWithRequiredKeyOf<NewParent>;

type ParentFormDefaults = Pick<NewParent, 'id' | 'accountActive'>;

type ParentFormGroupContent = {
  id: FormControl<IParent['id'] | NewParent['id']>;
  firstname: FormControl<IParent['firstname']>;
  lastname: FormControl<IParent['lastname']>;
  phoneNumber: FormControl<IParent['phoneNumber']>;
  accountActive: FormControl<IParent['accountActive']>;
  address: FormControl<IParent['address']>;
  emaildId: FormControl<IParent['emaildId']>;
  photo: FormControl<IParent['photo']>;
  photoContentType: FormControl<IParent['photoContentType']>;
  accountStartingDate: FormControl<IParent['accountStartingDate']>;
};

export type ParentFormGroup = FormGroup<ParentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ParentFormService {
  createParentFormGroup(parent: ParentFormGroupInput = { id: null }): ParentFormGroup {
    const parentRawValue = {
      ...this.getFormDefaults(),
      ...parent,
    };
    return new FormGroup<ParentFormGroupContent>({
      id: new FormControl(
        { value: parentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstname: new FormControl(parentRawValue.firstname, {
        validators: [Validators.required],
      }),
      lastname: new FormControl(parentRawValue.lastname, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(parentRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
      accountActive: new FormControl(parentRawValue.accountActive),
      address: new FormControl(parentRawValue.address),
      emaildId: new FormControl(parentRawValue.emaildId),
      photo: new FormControl(parentRawValue.photo),
      photoContentType: new FormControl(parentRawValue.photoContentType),
      accountStartingDate: new FormControl(parentRawValue.accountStartingDate),
    });
  }

  getParent(form: ParentFormGroup): IParent | NewParent {
    return form.getRawValue() as IParent | NewParent;
  }

  resetForm(form: ParentFormGroup, parent: ParentFormGroupInput): void {
    const parentRawValue = { ...this.getFormDefaults(), ...parent };
    form.reset(
      {
        ...parentRawValue,
        id: { value: parentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ParentFormDefaults {
    return {
      id: null,
      accountActive: false,
    };
  }
}
