import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { UtilisateurService } from 'src/app/services/user.service';
declare const $:any;

interface Promotion {
  value: string;
  viewValue: string;
}

interface UnivYear {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-gestion-des-etudiants',
  templateUrl: './gestion-des-etudiants.component.html',
  styleUrls: ['./gestion-des-etudiants.component.css']
})
export class GestionDesEtudiantsComponent implements OnInit, AfterViewInit {

  
  constructor(private HttpClient:HttpClient , private us:UtilisateurService) { }
  selectedValue!: string;
  selectedYearValue!: string;
  selectedFile!: File;


  promotions: Promotion[] = [
    {value: 'steak-0', viewValue: '3A INFO'},
    {value: 'pizza-1', viewValue: '4A INFO'},
    {value: 'tacos-2', viewValue: '5A INFO'},
  ];

  years: UnivYear[] = [
    {value: 'year-0', viewValue: '2020-2021'},
    {value: 'year-1', viewValue: '2021-2022'},
    {value: 'year-2', viewValue: '2022-2023'},
  ];
  ngAfterViewInit(): void {
    $('#example1').DataTable();
  }

  ngOnInit(): void {
    
  }
  

/**
 * 
 * @param event 
 */
  onFileChanged(event :any) {
    this.selectedFile = event.target.files[0];
    this.onUpload(event);
  }

  onUpload(event:any) {
 // Récupération du fichier Excel
 let file = event.target.files[0];
 // Création d'un formulaire pour envoyer le fichier
 console.log(file)
 // Envoi de la requête POST
     this.us.importEtudiants(file).subscribe(data => {
      console.log("data"+data);  }, err => {console.log("err"+err);});
  }
}
