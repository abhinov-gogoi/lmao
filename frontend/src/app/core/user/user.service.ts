import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, ReplaySubject } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from 'app/core/user/user.types';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private _user: ReplaySubject<User> = new ReplaySubject<User>(1);

    /**
     * Constructor
     */
    constructor(private _httpClient: HttpClient) {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    /**
     * Setter & getter for user
     *
     * @param value
     */
    set user(value: User) {
        // Store the value
        this._user.next(value);
    }

    get user$(): Observable<User> {
        return this._user.asObservable();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Get the current logged in user data
     */
    get(): Observable<any> {
        console.log("Get the current logged in user data")
        let options = {
            headers: this.getHttpHeaders()
        };
        return this._httpClient.get<User>('https://lmao-backend.herokuapp.com/api/v1/common/user', options)
            .pipe(
                tap((user) => {
                    console.log(user)
                    this._user.next(user);
                }),
                catchError(this.handleError('Get the current logged in user data'))
            )
    }

    /**
     * Update the user
     *
     * @param user
     */
    update(user: User): Observable<any> {
        return this._httpClient.patch<User>('api/common/user', { user }).pipe(
            map((response) => {
                this._user.next(response);
            })
        );
    }

    /**
     * Upload the profile pic
     */
    uploadProfilePic(image: string): boolean {
        console.log("uploadProfilePic user service")
        let options = {
            headers: this.getHttpHeaders()
        };

        this._httpClient.post<any>('https://lmao-backend.herokuapp.com/api/v1/common/user/profilePic', { image }, options).subscribe(
            response => {
                console.log(response)
                if (response.STATUS === 'Success') {
                    return true;
                } else if (response.STATUS === 'ERROR') {
                    return false;
                }
            }
        );
        return true;
    }

    getHttpHeaders() {
        // set the headers
        return new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Cache-Control', 'no-cache')
            .set('accessToken', btoa(localStorage.getItem('accessToken')))
            .set('username', btoa(localStorage.getItem('username')));
    }

    private handleError(message: String) {

        localStorage.removeItem('username')
        localStorage.removeItem('token')

        return (err: any) => {
            let errMsg = `error in ${message}()`;
            console.log(`${errMsg}:`, err)
            if (err instanceof HttpErrorResponse) {
                // you could extract more info about the error if you want, e.g.:
                console.log(`status: ${err.status}, ${err.statusText}`);
                // errMsg = ...
            }
            return Observable.throw(errMsg);
        }
    }
}


