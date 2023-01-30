import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AfterViewInit, OnDestroy, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import DataTables from 'datatables.net';
import { ToastrService } from 'ngx-toastr';
import { Subject } from 'rxjs';

import { Etudiant } from 'src/app/models/etudiant';
import { Promotion } from 'src/app/models/promotion';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { PromotionService } from 'src/app/services/promotion.service';
import { UtilisateurService } from 'src/app/services/user.service';
import { AjouteretudiantComponent } from './ajouteretudiant/ajouteretudiant.component';
import { SupprimerEtudiantComponent } from './supprimer-etudiant/supprimer-etudiant.component';

@Component({
  selector: 'app-gestion-des-etudiants',
  templateUrl: './gestion-des-etudiants.component.html',
  styleUrls: ['./gestion-des-etudiants.component.css']
})
export class GestionDesEtudiantsComponent implements OnInit {
  ExcelData: any;
  searchKey!:any;
  etudiants?: Etudiant[];
  selectedValue!: string;
  selectedYearValue!: string;
  selectedFile!: File;
  file!: File;
  promotions: any[]=[];
  years: any[]=[];
  promo?:any;
  annee?:any;
  listData! : MatTableDataSource<any>;
  displayedColumns : string[] = ['numero' , 'nom', 'prenom','actions' ];
  dataSource!: MatTableDataSource<Etudiant>;
  @ViewChild(MatSort) sort! : MatSort;
  @ViewChild (MatPaginator) paginator! : MatPaginator;


  constructor(private HttpClient: HttpClient,private dialog: MatDialog,
     private us: UtilisateurService, private etuService: EtudiantService,private promService:PromotionService, 
     private toastr:ToastrService) { }


  ngOnInit(): void {
   this.getAnnees(); 
   this.getPromotions();
   this.ListerEtudiants("Annee4","2021/2022")
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
    }, err => { console.log(err); });
    this.toastr.success('Importation avec Succées', 'La liste des Etudiant est bien importée'); 
  }

  /**
   * Lister tous les etudiants sans filtre
   */
  ListerEtudiants(promo:any,annee:any):void{
    if (promo=='Annee4') promo='4A';
    if (promo=='Annee3') promo='3A';
    if (promo=='Annee5') promo='5A';

    this.etuService.listeEtudiant(promo,annee).subscribe(etuds => {
      this.etudiants = etuds;
      this.dataSource = new MatTableDataSource(this.etudiants);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(){this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase(); }
  
  onSearchClear(){
    this.searchKey="";
    this.applyFilter();
  }

  /**
   * Retourner les promotions
   */
  getPromotions(){
    this.promService.getpromotions().subscribe(
      data => {this.promotions=data;
      }, err => { console.log(err); });
  }

  /**
   * Retourner les années 
   */
  getAnnees(){
    this.promService.getannees().subscribe(
      data => { this.years=data;
      } , 
      err => { console.log(err);}
    )
  }

  /**
   * 
   * @param etudiant : represente l'etudiant quand va supprimer
   */
delete(etudiant:any) 
{
  const DialogConfig = new MatDialogConfig();
    DialogConfig.autoFocus=true;
    const dialogRef= this.dialog.open(SupprimerEtudiantComponent,
      {
      width:'20%',
      height:'20%',
      panelClass:'custom-dialog',
      data:{etudiant}    })
    dialogRef.afterClosed().subscribe(res=>
      {      this.ListerEtudiants("Annee4","2021/2022") })
}

 
ajouter() {

  const DialogConfig = new MatDialogConfig();
  DialogConfig.autoFocus=true;
  // DialogConfig.width="60%";
  const dialogRef= this.dialog.open(AjouteretudiantComponent,{
    width:'40%',
    height:'80%',
    panelClass:'custom-dialog',
    data:{
      
    }
  })
  dialogRef.afterClosed().subscribe(res=>{
    this.ListerEtudiants("Annee4","2021/2022")
  })
}
  


  

}
