import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { FuseAlertType } from '@fuse/components/alert';
import { FuseValidators } from '@fuse/validators';

@Component({
    selector: 'reset-password-classic',
    templateUrl: './reset-password.component.html',
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})
export class ResetPasswordClassicComponent implements OnInit {
    alert: { type: FuseAlertType; message: string } = {
        type: 'success',
        message: ''
    };
    resetPasswordForm: FormGroup;
    showAlert: boolean = false;
    token: string

    /**
     * Constructor
     */
    constructor(
        private _formBuilder: FormBuilder,
        private _route: ActivatedRoute
    ) {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        this.token = this._route.snapshot.paramMap.get('token')
        console.log('token :: ' + this.token)
        // Create the form
        this.resetPasswordForm = this._formBuilder.group({
            password: ['', Validators.required],
            passwordConfirm: ['', Validators.required]
        },
            {
                validators: FuseValidators.mustMatch('password', 'passwordConfirm')
            }
        );
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Reset password
     */
    resetPassword(): void {
        console.log(this.token)
    }
}
