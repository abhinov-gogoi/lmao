"use strict";(self.webpackChunk_fuse_starter=self.webpackChunk_fuse_starter||[]).push([[41],{2255:(b,d,i)=>{i.d(d,{J:()=>p}),i(8288);var l=i(8583),u=i(7716);let p=(()=>{class a{}return a.\u0275fac=function(r){return new(r||a)},a.\u0275mod=u.oAB({type:a}),a.\u0275inj=u.cJS({imports:[[l.ez]]}),a})()},9041:(b,d,i)=>{i.r(d),i.d(d,{AuthEmailConfirmedModule:()=>a});var c=i(3423),m=i(1095),f=i(2255),h=i(4466),g=i(8288),e=i(7716),C=i(8951);const l=function(){return["/sign-in"]},p=[{path:"",component:(()=>{class t{constructor(n,s){this._route=n,this._authService=s,this.message="Please Wait",this.header="Verifying .. "}ngOnInit(){this._route.queryParams.subscribe(n=>{this._token=n.token,this._login_id=n.login_id}),this.verifyEmail(this._login_id,this._token)}verifyEmail(n,s){this._authService.verifyEmail(this._login_id,this._token).subscribe(o=>{"Success"===o.STATUS?(this.message=o.MESSAGE,this.header="Verification Success"):(this.message=o.MESSAGE,this.header="Verification Failed")},o=>{this.message=o.MESSAGE,this.header="Verification Failed"})}}return t.\u0275fac=function(n){return new(n||t)(e.Y36(c.gz),e.Y36(C.e))},t.\u0275cmp=e.Xpm({type:t,selectors:[["email-confirmed"]],decls:14,vars:4,consts:[[1,"flex","flex-col","flex-auto","items-center","sm:justify-center","min-w-0"],[1,"w-full","sm:w-auto","py-8","px-4","sm:p-12","sm:rounded-2xl","sm:shadow","sm:bg-card"],[1,"w-full","max-w-80","sm:w-80","mx-auto","sm:mx-0"],[1,"w-12"],["src","assets/images/logo/logo.png"],[1,"mt-8","text-4xl","font-extrabold","tracking-tight","leading-tight"],[1,"mt-4"],[1,"mt-8","text-md","font-medium","text-secondary"],[1,"ml-1","text-primary-500","hover:underline",3,"routerLink"]],template:function(n,s){1&n&&(e.TgZ(0,"div",0),e.TgZ(1,"div",1),e.TgZ(2,"div",2),e.TgZ(3,"div",3),e._UZ(4,"img",4),e.qZA(),e.TgZ(5,"div",5),e._uU(6),e.qZA(),e.TgZ(7,"div",6),e._uU(8),e.qZA(),e.TgZ(9,"div",7),e.TgZ(10,"span"),e._uU(11,"Return to"),e.qZA(),e.TgZ(12,"a",8),e._uU(13,"sign in "),e.qZA(),e.qZA(),e.qZA(),e.qZA(),e.qZA()),2&n&&(e.xp6(6),e.Oqu(s.header),e.xp6(2),e.hij(" ",s.message," "),e.xp6(4),e.Q6J("routerLink",e.DdM(3,l)))},directives:[c.yS],encapsulation:2,data:{animation:g.L}}),t})()}];let a=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=e.oAB({type:t}),t.\u0275inj=e.cJS({imports:[[c.Bz.forChild(p),m.ot,f.J,h.m]]}),t})()}}]);