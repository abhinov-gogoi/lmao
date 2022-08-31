import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { UserService } from 'app/core/user/user.service';

@Injectable()
export class AuthService {
    private _authenticated: boolean = false;
    private BASE_URL = "https://lmao-backend.herokuapp.com";
    private username;

    /**
     * Constructor
     */
    constructor(
        private _httpClient: HttpClient,
        private _userService: UserService
    ) {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    /**
     * Setter & getter for access token
     */
    set accessToken(token: string) {
        if (token === null || token === undefined || token === '') {
            // console.log('token missing :: ' + token)
            return;
        } else {
            localStorage.setItem('accessToken', token);
            localStorage.setItem('username', this.username);
        }

    }

    get accessToken(): string {
        return localStorage.getItem('accessToken') ?? '';
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Forgot password
     *
     * @param email
     */
    forgotPassword(email: string): Observable<any> {
        return this._httpClient.get(this.BASE_URL + '/user/forgot-password?email=' + email);
    }

    /**
     * Reset password
     *
     * @param password
     */
    resetPassword(password: string, token: string, username: string): Observable<any> {
        let options = {
            headers: this.getHttpHeaders(token, username)
        };
        return this._httpClient.post(this.BASE_URL + '/user/reset-password', { password }, options);
    }

    verifyEmail(login_id: string, token: string): Observable<any> {
        console.log("verifyEmail");
        return this._httpClient.get(this.BASE_URL + '/user/verifyemail?login_id=' + btoa(login_id) + '&token=' + btoa(token));
    }

    /**
     * Sign up
     *
     * @param user
     */
    signUp(body, headers): Observable<any> {
        return this._httpClient.post(this.BASE_URL + "/user/signup", body, { headers }).pipe(
            switchMap(
                (response: any) => {
                    return of(response);
                })
        );
    }

    /**
     * Sign in
     *
     * @param credentials
     */
    signIn(body, headers): Observable<any> {
        // Throw error, if the user is already logged in
        if (this._authenticated) {
            return throwError('User is already logged in.');
        }

        return this._httpClient.post(this.BASE_URL + "/user/login", body, { headers }).pipe(
            switchMap(
                (response: any) => {
                    console.log(response)
                    if (response.STATUS === 'Success') {
                        console.log("Success")
                        console.log(response)
                        // Store the access token in the local storage

                        this.username = response.USERNAME;
                        this.accessToken = response.TOKEN;

                        // Set the authenticated flag to true
                        this._authenticated = true;

                        // Store the user on the user service
                        this._userService.user = response.user;
                        // Return a new observable with the response
                        return of(response);
                    } else {
                        return throwError(response.MESSAGE);
                    }
                })
        );
    }

    /**
     * Sign in using the access token
     */
    signInUsingToken(): Observable<any> {
        // Renew token
        return this._httpClient.post('api/auth/refresh-access-token', {
            accessToken: this.accessToken
        }).pipe(
            catchError(() =>

                // Return false
                of(false)
            ),
            switchMap((response: any) => {

                this.username = response.USERNAME;
                // Store the access token in the local storage
                this.accessToken = response.accessToken;


                // Set the authenticated flag to true
                this._authenticated = true;

                // Store the user on the user service
                this._userService.user = response.user;

                // Return true
                return of(true);
            })
        );
    }

    /**
     * Sign out
     */
    signOut(): Observable<any> {
        console.log("signout")
        // Remove the access token from the local storage
        localStorage.removeItem('accessToken');
        localStorage.removeItem('username');


        // Set the authenticated flag to false
        this._authenticated = false;

        // Return the observable
        return of(true);
    }

    /**
     * Unlock session
     *
     * @param credentials
     */
    unlockSession(credentials: { email: string; password: string }): Observable<any> {
        return this._httpClient.post('api/auth/unlock-session', credentials);
    }

    /**
     * Check the authentication status
     */
    check(): Observable<boolean> {
        // Check if the user is logged in
        if (this._authenticated) {
            return of(true);
        }

        // Check the access token availability
        if (!this.accessToken) {
            return of(false);
        }

        // If the access token exists and it didn't expire, sign in using it
        return this.signInUsingToken();
    }


    getHttpHeaders(token: string, username: string) {
        // set the headers
        return new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Cache-Control', 'no-cache')
            .set('accessToken', btoa(token))
            .set('username', btoa(username));
    }
}
