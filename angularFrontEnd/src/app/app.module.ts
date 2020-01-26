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
import { AllAndWithCatProductsComponent } from './components/all-and-with-cat-products/all-and-with-cat-products.component';
import { ProductsByCategoryComponent } from './components/products-by-category/products-by-category.component';
import { HelloComponentComponent } from './components/hello-component/hello-component.component';
import { AdminProductsComponent } from './components/admin-products/admin-products.component';
import { AdminAddProductComponent } from './components/admin-add-product/admin-add-product.component';
import { AdminCategoriesComponent } from './components/admin-categories/admin-categories.component';
import { AdminAddCategoryComponent } from './components/admin-add-category/admin-add-category.component';
import { AdminEditCategoryComponent } from './components/admin-edit-category/admin-edit-category.component';
import { AdminEditProductComponent } from './components/admin-edit-product/admin-edit-product.component';

const appRoutes: Routes = [
  {path: 'categories', component: CategoriesComponent},
  {path: 'admin', component: AdminProfileComponent},
  {path: 'allProducts', component: ProductComponent},
  {path: 'productsByCategory/:id', component: ProductsByCategoryComponent},
  {path: 'admin/products', component: AdminProductsComponent},
  {path: 'admin/add-product', component: AdminAddProductComponent},
  {path: 'admin/edit-product/:prodId', component: AdminEditProductComponent},
  {path: 'admin/categories', component: AdminCategoriesComponent},
  {path: 'admin/add-category', component: AdminAddCategoryComponent},
  {path: 'admin/edit-category/:catId', component: AdminEditCategoryComponent},
  {path: '', component: HelloComponentComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    NavbarComponent,
    AdminProfileComponent,
    CategoriesComponent,
    AllAndWithCatProductsComponent,
    ProductsByCategoryComponent,
    HelloComponentComponent,
    AdminProductsComponent,
    AdminAddProductComponent,
    AdminCategoriesComponent,
    AdminAddCategoryComponent,
    AdminEditCategoryComponent,
    AdminEditProductComponent,    
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
