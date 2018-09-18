import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';
import {forkJoin} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  constructor(private http: Http) { }

  public productsBS = new BehaviorSubject<string>(null);

  getProducts(){
    return this.http.get('http://localhost:9000/allproducts').pipe(map(res => res.json())) 
  }

  getProductsByCategory(id){
    return this.http.get('http://localhost:9000/productsByCategory/' + id).pipe(map(res => res.json()))
  }

  postAddProduct(value){
    return this.http.post('http://localhost:9000/newproduct' , value).pipe(map(res => res.json())) 
  }

  getEditProduct(id){
    return this.http.get('http://localhost:9000/product/' + id ).pipe(map(res => res.json()));
  }

  postEditProduct(value){
    return this.http.post('http://localhost:9000/editProduct/'+value.prodId, value ).pipe(map(res => res.json()))
  }


 
}
