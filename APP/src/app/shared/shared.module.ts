import {CourseItemComponent} from './component/course-item/course-item.component';
import {IonicModule} from '@ionic/angular';
import {FormsModule} from '@angular/forms';
import {LocalStorageService} from './services/local-storage.service';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';


@NgModule({
  declarations: [
    CourseItemComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule
  ],
  providers: [
    LocalStorageService
  ],
  exports: [
    CommonModule,
    FormsModule,  // 添加
    IonicModule,
    CourseItemComponent
  ]
})
export class SharedModule {
}
