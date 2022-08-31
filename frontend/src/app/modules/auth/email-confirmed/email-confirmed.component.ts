import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { AuthService } from 'app/core/auth/auth.service';

@Component({
    selector: 'email-confirmed',
    templateUrl: './email-confirmed.component.html',
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})

export class AuthEmailConfirmedComponent implements OnInit {
    _token: string;
    _login_id: string;
    message: string = "Please Wait";
    header: string = "Verifying .. "
    /**
     * Constructor
     */
    constructor(private _route: ActivatedRoute, private _authService: AuthService,) {
    }

    ngOnInit(): void {
        this._route.queryParams
            .subscribe(params => {
                this._token = params.token;
                this._login_id = params.login_id
            });
        this.verifyEmail(this._login_id, this._token)
    }

    verifyEmail(login_id: string, token: string) {
        this._authService.verifyEmail(this._login_id, this._token)
            .subscribe(
                (response) => {
                    if (response.STATUS === 'Success') {
                        // Set the message
                        this.message = response.MESSAGE
                        this.header = 'Verification Success'
                    } else {
                        // Set the message
                        this.message = response.MESSAGE
                        this.header = 'Verification Failed'
                    }
                },
                (response) => {
                    // Set the message
                    this.message = response.MESSAGE
                    this.header = 'Verification Failed'
                }
            );
    }

}
