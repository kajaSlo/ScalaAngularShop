import { CategoryService } from './category.service';
import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';
import {forkJoin} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  servicePod: any;
  constructor(private http: Http, private categoryService: CategoryService) { }

  public productsBS = new BehaviorSubject<string>(null);

  getProducts(){
    return this.http.get('http://localhost:9000/allproducts').pipe(map(res => res.json())) 
  }

  getProductsByCategory(id){
    return this.http.get('http://localhost:9000/productsByCategory/' + id).pipe(map(res => res.json()))
  }

 
}
