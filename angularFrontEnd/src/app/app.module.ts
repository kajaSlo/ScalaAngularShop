import { CategoryService } from './services/category.service';
import { ProductService } from './services/product.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';


import { AppComponent } from './app.component';
import { ProductComponent } from './components/product/product.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { CategoriesComponent } from './components/categories/categories.component';

const appRoutes: Routes = [
 // {path: ':product', component: ProductComponent},
  {path: 'categories', component: CategoriesComponent},
  {path: 'admin', component: AdminProfileComponent},
  {path: '', component: ProductComponent},


]

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    NavbarComponent,
    AdminProfileComponent,
    CategoriesComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)


  ],
  providers: [
    ProductService,
    CategoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
