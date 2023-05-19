import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Etudiant } from 'src/app/models/etudiant';
import { Promotion } from 'src/app/models/promotion';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { PromotionService } from 'src/app/services/promotion.service';

@Component({
  selector: 'app-ajouteretudiant',
  templateUrl: './ajouteretudiant.component.html',
  styleUrls: ['./ajouteretudiant.component.css']
})
export class AjouteretudiantComponent implements OnInit {

  constructor(  private fb: FormBuilder,private es:EtudiantService ,private toastr:ToastrService ,private ps:PromotionService ) { }
  form! :FormGroup;
  numero! : string;
  x!:number;
  promotions : Promotion[]=[];


  ngOnInit(): void {
    this.initForm();
    this.ongetNumero();
    this.getPromotions();
  }

  initForm(): void {
    this.form = this.fb.group({
    nom:new FormControl('' ,[Validators.required,  Validators.pattern('^[a-zA-Z_]+'),Validators.minLength(3)]),
    prenom:new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z_]+'),Validators.minLength(3)]),
    selectedYear: new FormControl(''),
    AU: new FormControl('',),
    AE: new FormControl('',[Validators.required, Validators.pattern('^[0-9]{4}')])

  })}

  get f (){ return this.form.controls }

  onAdd():void{
   // this.es.addEtudiant().subscribe
   let etudiant:Etudiant={};
   etudiant.numero = this.numero; 
   etudiant.nom = this.f.nom.value;
   etudiant.prenom = this.f.prenom.value;
   etudiant.annee=this.f.AE.value;
   //etudiant.promotion_id.id =1; 
   etudiant.annee=this.f.AU.value;
   //etudiant.promotion_id=this.f.selectedYear.value
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

  
  getPromotions(){
    
    this.ps.getpromotions().subscribe(
      data => {this.promotions=data;
        console.log(data)
      }, err => { console.log(err); });
  }

onSubmit() {console.warn(this.form.value);}
}









