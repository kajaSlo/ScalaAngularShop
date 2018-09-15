import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: Http) { }

  public categoriesBS = new BehaviorSubject<string>(null);


  getCategories(){
    return this.http.get('http://localhost:9000/allcategories').pipe(map(res => res.json())) 
  }

  postAddCategory(value){
    return this.http.post('http://localhost:9000/newCategory ', value ).pipe(map(res => res.json()))
  }

}
