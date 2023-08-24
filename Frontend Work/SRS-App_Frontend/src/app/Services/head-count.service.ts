import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HeadCount } from '../Interfaces/head-count';

@Injectable({
  providedIn: 'root'
})
export class HeadCountService {

  constructor(public http: HttpClient) { }  // DI for HttpClient

  private apiUrlhc = "http://localhost:8585/head_count";

// ====================================================================================================

  getAll_HeadCount() : Observable<HeadCount[]> {
    return this.http.get<HeadCount[]>(this.apiUrlhc+"/getAll");
  }


  get_HeadCount_By_BookingId(bookingId: string) : Observable<HeadCount[]> {
    return this.http.get<HeadCount[]>(this.apiUrlhc+"/get_HeadCount_By_BookingId/"+bookingId);
  } 


  store_HeadCount(bookingId: string, headCount: HeadCount) {
    return this.http.post(this.apiUrlhc+"/store/"+bookingId, headCount);
  }


  delete_HeadCount(headCountId: number):Observable<string> {
    return this.http.delete(this.apiUrlhc+"/delete/"+headCountId,{responseType:"text"});
  }
  

  update_HeadCount(headCountId: any, HeadCountDetails:any):Observable<string> {
    return this.http.put(this.apiUrlhc+"/update/"+headCountId, HeadCountDetails,{responseType:"text"})
  }









}
