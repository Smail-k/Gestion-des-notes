import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AfterViewInit, OnDestroy, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import DataTables from 'datatables.net';
import { Subject } from 'rxjs';

import { Etudiant } from 'src/app/models/etudiant';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { UtilisateurService } from 'src/app/services/user.service';

declare const $: any;
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
  ExcelData: any;
  searchKey!:any;
  etudiants?: Etudiant[];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  selectedValue!: string;
  selectedYearValue!: string;
  selectedFile!: File;
  file!: File;
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
  
  listData! : MatTableDataSource<any>;
  displayedColumns : string[] = ['numero' , 'nom', 'prenom','actions' ];
  dataSource!: MatTableDataSource<Etudiant>;
  @ViewChild(MatSort) sort! : MatSort;
  @ViewChild (MatPaginator) paginator! : MatPaginator;


  constructor(private HttpClient: HttpClient, private us: UtilisateurService, private etuService: EtudiantService) { }

  ngAfterViewInit(): void {
    $('#dt-mat-id').DataTable();
  }

  ngOnInit(): void {
    this.ListerEtudiants();
  }

  
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  /**
   * 
   * @param event 
   */
  onFileChanged(event: any) {
    this.selectedFile = event.target.files[0];
    // Récupération du fichier Excel
    this.file = event.target.files[0];
    const fd = new FormData();
    fd.append('file', this.file);
    // Envoi de la requête POST
    this.etuService.importEtudiants(fd).subscribe(data => {
      console.log(data);
    }, err => { console.log(err); });
  }

  /**
   * Lister tous les etudiants sans filtre
   */
  ListerEtudiants():void{
    this.etuService.listeEtudiant().subscribe(etuds => {
      console.log(etuds);
      this.etudiants = etuds;
      this.dataSource = new MatTableDataSource(this.etudiants);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      //this.dtTrigger.next(null);
    });
  }

  applyFilter(){this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase(); }
  
  onSearchClear(){
    this.searchKey="";
    this.applyFilter();
  }

  

}
