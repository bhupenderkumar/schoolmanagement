import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'fees',
        data: { pageTitle: 'schoolApp.fees.home.title' },
        loadChildren: () => import('./fees/fees.module').then(m => m.FeesModule),
      },
      {
        path: 'attendance',
        data: { pageTitle: 'schoolApp.attendance.home.title' },
        loadChildren: () => import('./attendance/attendance.module').then(m => m.AttendanceModule),
      },
      {
        path: 'parent',
        data: { pageTitle: 'schoolApp.parent.home.title' },
        loadChildren: () => import('./parent/parent.module').then(m => m.ParentModule),
      },
      {
        path: 'student',
        data: { pageTitle: 'schoolApp.student.home.title' },
        loadChildren: () => import('./student/student.module').then(m => m.StudentModule),
      },
      {
        path: 'teacher',
        data: { pageTitle: 'schoolApp.teacher.home.title' },
        loadChildren: () => import('./teacher/teacher.module').then(m => m.TeacherModule),
      },
      {
        path: 'notification',
        data: { pageTitle: 'schoolApp.notification.home.title' },
        loadChildren: () => import('./notification/notification.module').then(m => m.NotificationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
