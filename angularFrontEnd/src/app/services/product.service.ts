import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: Http) { }

  public productsBS = new BehaviorSubject<string>(null);

  getProducts(){
    return this.http.get('http://localhost:9000/allproducts').pipe(map(res => res.json())) 
  }

}
