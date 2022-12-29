import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  /* Les déclarations */
  
  email: any;
  password!: string;
  return: string = '';
  expiration!:any
  hide = true;
  user: any;
  canAcess:boolean=true;
  roleAs!: any;
  id!:any;
  public form: FormGroup = Object.create(null);
  
  
  constructor(
  private fb: FormBuilder,
  private router: Router,
  private route: ActivatedRoute,
  ){}
  
  
  ngOnInit(): void 
  {
  this.initForm()
  
  }
  
  
  
  
  /* Les fonctions */
  
  get f() 
  {
  return this.form.controls;
  }
  
  
  initForm()
  {
  
  this.form = this.fb.group({
  uname: [null, Validators.compose([Validators.required])],
  password: [null, Validators.compose([Validators.required])],
  });
  
  }
 
  
  onSubmit() 
  {
  
  let username=this.f.uname.value;
  let password=this.f.password.value
  
  }
  
  
  
  
  }