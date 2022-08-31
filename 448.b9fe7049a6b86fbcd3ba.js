"use strict";(self.webpackChunk_fuse_starter=self.webpackChunk_fuse_starter||[]).push([[448],{2448:(k,u,n)=>{n.r(u),n.d(u,{AuthSignUpModule:()=>Y});var g=n(3423),c=n(1095),d=n(7539),l=n(8295),f=n(6627),h=n(9983),Z=n(4885),v=n(2255),A=n(588),x=n(4466),s=n(3679),y=n(8288),e=n(7716),T=n(8951),w=n(8583),q=n(3994);const S=["signUpNgForm"];function C(t,i){if(1&t&&(e.TgZ(0,"fuse-alert",41),e._uU(1),e.qZA()),2&t){const r=e.oxw();e.Q6J("appearance","outline")("showIcon",!1)("type",r.alert.type)("@shake","error"===r.alert.type),e.xp6(1),e.hij(" ",r.alert.message," ")}}function J(t,i){1&t&&(e.TgZ(0,"mat-error"),e._uU(1," Full name is required "),e.qZA())}function I(t,i){1&t&&(e.TgZ(0,"mat-error"),e._uU(1," Email address is required "),e.qZA())}function _(t,i){1&t&&(e.TgZ(0,"mat-error"),e._uU(1," Please enter a valid email address "),e.qZA())}function F(t,i){1&t&&e._UZ(0,"mat-icon",42),2&t&&e.Q6J("svgIcon","heroicons_solid:eye")}function N(t,i){1&t&&e._UZ(0,"mat-icon",42),2&t&&e.Q6J("svgIcon","heroicons_solid:eye-off")}function b(t,i){1&t&&(e.TgZ(0,"span"),e._uU(1," Create your free account "),e.qZA())}function Q(t,i){1&t&&e._UZ(0,"mat-progress-spinner",43),2&t&&e.Q6J("diameter",24)("mode","indeterminate")}const j=function(){return["/sign-in"]},U=function(){return["./"]},M=[{path:"",component:(()=>{class t{constructor(r,o,a){this._authService=r,this._formBuilder=o,this._router=a,this.alert={type:"success",message:""},this.showAlert=!1}ngOnInit(){this.signUpForm=this._formBuilder.group({name:["",s.kI.required],email:["",[s.kI.required,s.kI.email]],password:["",s.kI.required],agreements:["",s.kI.requiredTrue]})}signUp(){if(!this.signUpForm.invalid){var r={name:""+this.signUpForm.value.name.trim(),login_id:""+this.signUpForm.value.email.trim(),password:""+this.signUpForm.value.password.trim(),device_type:"W",device_os:"angular webapp",device_token:"YxueNFEN1lqhXNO6JelqZ"};JSON.stringify(r),this.signUpForm.disable(),this.showAlert=!1,this._authService.signUp(r,{"Content-Type":"application/json"}).subscribe(m=>{console.log(m),"Success"===m.STATUS?this._router.navigateByUrl("/confirmation-required"):(this.signUpForm.enable(),this.alert={type:"error",message:m.MESSAGE},this.showAlert=!0)},m=>{console.log(m),this.signUpForm.enable(),this.signUpNgForm.resetForm(),this.alert={type:"error",message:"Something went wrong, please try again."},this.showAlert=!0})}}}return t.\u0275fac=function(r){return new(r||t)(e.Y36(T.e),e.Y36(s.qu),e.Y36(g.F0))},t.\u0275cmp=e.Xpm({type:t,selectors:[["auth-sign-up"]],viewQuery:function(r,o){if(1&r&&e.Gf(S,5),2&r){let a;e.iGM(a=e.CRH())&&(o.signUpNgForm=a.first)}},decls:73,vars:22,consts:[[1,"flex","flex-col","sm:flex-row","items-center","md:items-start","sm:justify-center","md:justify-start","flex-auto","min-w-0"],[1,"md:flex","md:items-center","md:justify-end","w-full","sm:w-auto","md:h-full","md:w-1/2","py-8","px-4","sm:p-12","md:p-16","sm:rounded-2xl","md:rounded-none","sm:shadow","md:shadow-none","sm:bg-card"],[1,"w-full","max-w-80","sm:w-80","mx-auto","sm:mx-0"],[1,"w-12"],["src","assets/images/logo/logo.png"],[1,"mt-8","text-4xl","font-extrabold","tracking-tight","leading-tight"],[1,"flex","items-baseline","mt-0.5","font-medium"],[1,"ml-1","text-primary-500","hover:underline",3,"routerLink"],["class","mt-8 -mb-4",3,"appearance","showIcon","type",4,"ngIf"],[1,"mt-8",3,"formGroup"],[1,"w-full"],["id","name","matInput","",3,"formControlName"],[4,"ngIf"],["id","email","matInput","",3,"formControlName"],["id","password","matInput","","type","password",3,"formControlName"],["passwordField",""],["mat-icon-button","","type","button","matSuffix","",3,"click"],["class","icon-size-5",3,"svgIcon",4,"ngIf"],[1,"inline-flex","items-end","w-full","mt-1.5"],[3,"color","formControlName"],["mat-flat-button","",1,"fuse-mat-button-large","w-full","mt-6",3,"color","disabled","click"],[3,"diameter","mode",4,"ngIf"],[1,"relative","hidden","md:flex","flex-auto","items-center","justify-center","w-1/2","h-full","p-16","lg:px-28","overflow-hidden","bg-gray-800","dark:border-l"],["viewBox","0 0 960 540","width","100%","height","100%","preserveAspectRatio","xMidYMax slice","xmlns","http://www.w3.org/2000/svg",1,"absolute","inset-0","pointer-events-none"],["fill","none","stroke","currentColor","stroke-width","100",1,"text-gray-700","opacity-25"],["r","234","cx","196","cy","23"],["r","234","cx","790","cy","491"],["viewBox","0 0 220 192","width","220","height","192","fill","none",1,"absolute","-top-16","-right-16","text-gray-700"],["id","837c3e70-6c3a-44e6-8854-cc48c737b659","x","0","y","0","width","20","height","20","patternUnits","userSpaceOnUse"],["x","0","y","0","width","4","height","4","fill","currentColor"],["width","220","height","192","fill","url(#837c3e70-6c3a-44e6-8854-cc48c737b659)"],[1,"z-10","relative","w-full","max-w-2xl"],[1,"text-7xl","font-bold","leading-none","text-gray-100"],[1,"mt-6","text-lg","tracking-tight","leading-6","text-gray-400"],[1,"flex","items-center","mt-8"],[1,"flex","flex-0","items-center","-space-x-1.5"],["src","assets/images/avatars/female-18.jpg",1,"flex-0","w-10","h-10","rounded-full","ring-4","ring-offset-1","ring-gray-800","ring-offset-gray-800","object-cover"],["src","assets/images/avatars/female-11.jpg",1,"flex-0","w-10","h-10","rounded-full","ring-4","ring-offset-1","ring-gray-800","ring-offset-gray-800","object-cover"],["src","assets/images/avatars/male-09.jpg",1,"flex-0","w-10","h-10","rounded-full","ring-4","ring-offset-1","ring-gray-800","ring-offset-gray-800","object-cover"],["src","assets/images/avatars/male-16.jpg",1,"flex-0","w-10","h-10","rounded-full","ring-4","ring-offset-1","ring-gray-800","ring-offset-gray-800","object-cover"],[1,"ml-4","font-medium","tracking-tight","text-gray-400"],[1,"mt-8","-mb-4",3,"appearance","showIcon","type"],[1,"icon-size-5",3,"svgIcon"],[3,"diameter","mode"]],template:function(r,o){if(1&r){const a=e.EpF();e.TgZ(0,"div",0),e.TgZ(1,"div",1),e.TgZ(2,"div",2),e.TgZ(3,"div",3),e._UZ(4,"img",4),e.qZA(),e.TgZ(5,"div",5),e._uU(6,"Sign up"),e.qZA(),e.TgZ(7,"div",6),e.TgZ(8,"div"),e._uU(9,"Already have an account?"),e.qZA(),e.TgZ(10,"a",7),e._uU(11,"Sign in "),e.qZA(),e.qZA(),e.YNc(12,C,2,5,"fuse-alert",8),e.TgZ(13,"form",9),e.TgZ(14,"mat-form-field",10),e.TgZ(15,"mat-label"),e._uU(16,"Full name"),e.qZA(),e._UZ(17,"input",11),e.YNc(18,J,2,0,"mat-error",12),e.qZA(),e.TgZ(19,"mat-form-field",10),e.TgZ(20,"mat-label"),e._uU(21,"Email address"),e.qZA(),e._UZ(22,"input",13),e.YNc(23,I,2,0,"mat-error",12),e.YNc(24,_,2,0,"mat-error",12),e.qZA(),e.TgZ(25,"mat-form-field",10),e.TgZ(26,"mat-label"),e._uU(27,"Password"),e.qZA(),e._UZ(28,"input",14,15),e.TgZ(30,"button",16),e.NdJ("click",function(){e.CHM(a);const p=e.MAs(29);return p.type="password"===p.type?"text":"password"}),e.YNc(31,F,1,1,"mat-icon",17),e.YNc(32,N,1,1,"mat-icon",17),e.qZA(),e.TgZ(33,"mat-error"),e._uU(34," Password is required "),e.qZA(),e.qZA(),e.TgZ(35,"div",18),e.TgZ(36,"mat-checkbox",19),e.TgZ(37,"span"),e._uU(38,"I agree to the"),e.qZA(),e.TgZ(39,"a",7),e._uU(40,"Terms of Service "),e.qZA(),e.TgZ(41,"span"),e._uU(42,"and"),e.qZA(),e.TgZ(43,"a",7),e._uU(44,"Privacy Policy "),e.qZA(),e.qZA(),e.qZA(),e.TgZ(45,"button",20),e.NdJ("click",function(){return o.signUp()}),e.YNc(46,b,2,0,"span",12),e.YNc(47,Q,1,2,"mat-progress-spinner",21),e.qZA(),e.qZA(),e.qZA(),e.qZA(),e.TgZ(48,"div",22),e.O4$(),e.TgZ(49,"svg",23),e.TgZ(50,"g",24),e._UZ(51,"circle",25),e._UZ(52,"circle",26),e.qZA(),e.qZA(),e.TgZ(53,"svg",27),e.TgZ(54,"defs"),e.TgZ(55,"pattern",28),e._UZ(56,"rect",29),e.qZA(),e.qZA(),e._UZ(57,"rect",30),e.qZA(),e.kcU(),e.TgZ(58,"div",31),e.TgZ(59,"div",32),e.TgZ(60,"div"),e._uU(61,"Welcome to"),e.qZA(),e.TgZ(62,"div"),e._uU(63,"LMAO"),e.qZA(),e.qZA(),e.TgZ(64,"div",33),e._uU(65," Learning Management and Organiser App "),e.qZA(),e.TgZ(66,"div",34),e.TgZ(67,"div",35),e._UZ(68,"img",36),e._UZ(69,"img",37),e._UZ(70,"img",38),e._UZ(71,"img",39),e.qZA(),e._UZ(72,"div",40),e.qZA(),e.qZA(),e.qZA(),e.qZA()}if(2&r){const a=e.MAs(29);e.xp6(10),e.Q6J("routerLink",e.DdM(19,j)),e.xp6(2),e.Q6J("ngIf",o.showAlert),e.xp6(1),e.Q6J("formGroup",o.signUpForm),e.xp6(4),e.Q6J("formControlName","name"),e.xp6(1),e.Q6J("ngIf",o.signUpForm.get("name").hasError("required")),e.xp6(4),e.Q6J("formControlName","email"),e.xp6(1),e.Q6J("ngIf",o.signUpForm.get("email").hasError("required")),e.xp6(1),e.Q6J("ngIf",o.signUpForm.get("email").hasError("email")),e.xp6(4),e.Q6J("formControlName","password"),e.xp6(3),e.Q6J("ngIf","password"===a.type),e.xp6(1),e.Q6J("ngIf","text"===a.type),e.xp6(4),e.Q6J("color","primary")("formControlName","agreements"),e.xp6(3),e.Q6J("routerLink",e.DdM(20,U)),e.xp6(4),e.Q6J("routerLink",e.DdM(21,U)),e.xp6(2),e.Q6J("color","primary")("disabled",o.signUpForm.invalid),e.xp6(1),e.Q6J("ngIf",!o.signUpForm.disabled),e.xp6(1),e.Q6J("ngIf",o.signUpForm.disabled)}},directives:[g.yS,w.O5,s._Y,s.JL,s.sg,l.KE,l.hX,h.Nt,s.Fj,s.JJ,s.u,c.lW,l.R9,l.TO,d.oG,q.W,f.Hw,Z.Ou],encapsulation:2,data:{animation:y.L}}),t})()}];let Y=(()=>{class t{}return t.\u0275fac=function(r){return new(r||t)},t.\u0275mod=e.oAB({type:t}),t.\u0275inj=e.cJS({imports:[[g.Bz.forChild(M),c.ot,d.p9,l.lN,f.Ps,h.c,Z.Cq,v.J,A.fC,x.m]]}),t})()}}]);