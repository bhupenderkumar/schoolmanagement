import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITeacher } from '../teacher.model';
import { TeacherService } from '../service/teacher.service';

@Injectable({ providedIn: 'root' })
export class TeacherRoutingResolveService implements Resolve<ITeacher | null> {
  constructor(protected service: TeacherService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITeacher | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((teacher: HttpResponse<ITeacher>) => {
          if (teacher.body) {
            return of(teacher.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
