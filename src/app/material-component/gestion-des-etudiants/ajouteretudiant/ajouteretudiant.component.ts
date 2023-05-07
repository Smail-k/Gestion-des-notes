import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Etudiant } from 'src/app/models/etudiant';
import { EtudiantService } from 'src/app/services/etudiant.service';

@Component({
  selector: 'app-ajouteretudiant',
  templateUrl: './ajouteretudiant.component.html',
  styleUrls: ['./ajouteretudiant.component.css']
})
export class AjouteretudiantComponent implements OnInit {

  constructor(  private fb: FormBuilder,private es:EtudiantService ,private toastr:ToastrService  ) { }
  form! :FormGroup;
  numero! : string;
  x!:number;


  ngOnInit(): void {
    this.initForm();
    this.ongetNumero();
  }

  initForm(): void {
    this.form = this.fb.group({
    nom:new FormControl('' ,[Validators.required,  Validators.pattern('^[a-zA-Z_]+'),Validators.minLength(3)]),
    prenom:new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z_]+'),Validators.minLength(3)]),
    selectedYear: new FormControl('', Validators.required),
    Filiere: new FormControl('', Validators.required),
    AU: new FormControl('', Validators.required)


  })}

  onAdd():void{
   // this.es.addEtudiant().subscribe
   let etudiant:Etudiant={};
   etudiant.numero = this.numero; 
   etudiant.nom = this.f.nom.value;
   etudiant.prenom = this.f.prenom.value;
   etudiant.promotion =1; 
   etudiant.annee="2021/2022"
   this.es.addEtudiant(etudiant).subscribe(
    data=> {
    } , err => { console.log(err);
    }
   )

   this.toastr.success('Ajouter etudiant',"L'ajout à été fait avec succés"); 


  }

  ongetNumero(){
    this.es.GetEtudiantMaxNumero().subscribe(
      data => {
        this.numero =data;
      } ,  err => { console.log(err); });
  }

  

get f (){ return this.form.controls }
onSubmit() {console.warn(this.form.value);}
}









