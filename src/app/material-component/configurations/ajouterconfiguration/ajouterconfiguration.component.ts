import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Configuration } from 'src/app/models/configuration';
import { ConfigurationService } from 'src/app/services/configuration.service';

@Component({
  selector: 'app-ajouterconfiguration',
  templateUrl: './ajouterconfiguration.component.html',
  styleUrls: ['./ajouterconfiguration.component.css']
})
export class AjouterconfigurationComponent implements OnInit {

  constructor(private fb: FormBuilder ,private toastr:ToastrService, private es:ConfigurationService) { }

  form! :FormGroup;
  seuil! : number;
  x!:number;
  
  ngOnInit(): void {
    this.initForm();
    //this.ongetNumero();
  }

  initForm(): void {
    this.form = this.fb.group({
    nom:new FormControl('' ,[Validators.required,  Validators.pattern('^[a-zA-Z_]+'),Validators.minLength(3)]),
    prenom:new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z_]+'),Validators.minLength(3)]),
  })}

  onAdd():void{
    // this.es.addEtudiant().subscribe
    let configure:Configuration={};
    configure.seuil = this.seuil; 
    configure.libelle = this.f.li.value;
    configure.description = this.f.description.value;
   
    this.es.addConfiguration(configure).subscribe(
     data=> {
     } , err => { console.log(err);
     }
    )

    this.toastr.success('Nouvelle configuration ',"L'ajout à été effectuée avec succés"); 

  }


  get f (){ return this.form.controls }
  onSubmit() {console.warn(this.form.value);}

}
