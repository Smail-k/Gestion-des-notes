import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Unite } from 'src/app/models/unite';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { PromotionService } from 'src/app/services/promotion.service';
import { UniteService } from 'src/app/services/unite.service';

@Component({
selector: 'app-gestion-des-ue',
templateUrl: './gestion-des-ue.component.html',
styleUrls: ['./gestion-des-ue.component.css']
})
export class GestionDesUEComponent implements OnInit, AfterViewInit {

constructor(private ps:PromotionService, private fb:FormBuilder,private toastr:ToastrService,private es:EtudiantService,private us:UniteService) { 

}
selectedValue!: string;
selectedYearValue!: string;
promotions: any[]=[];
years: any[]=[];
form! :FormGroup;
unites:Unite[]=[];
selectedFile!: File;
file!: File;
getvalue?: any;
searchKey!:any;
listData! : MatTableDataSource<any>;
displayedColumns : string[] = ['code', 'libelle','coefficient','semestre' ];
dataSource!: MatTableDataSource<Unite>;
@ViewChild(MatSort) sort! : MatSort;
@ViewChild (MatPaginator) paginator! : MatPaginator;




ngAfterViewInit(): void {

}

ngOnInit(): void {
this.getAnnees(); 
this.getPromotions();
this.getUnites();
}


applyFilter(){this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase(); }
  
onSearchClear(){
  this.searchKey="";
  this.applyFilter();
}

get f(){return this.form.controls}
onSubmit() {console.warn(this.form.value);}


/**
 * Retourner les promotions
 */
getPromotions(){
  this.ps.getpromotions().subscribe(
    data => {this.promotions=data;
    }, err => { console.log(err); });
}

/**
 * Retourner les années 
 */
getAnnees(){
  this.ps.getannees().subscribe(
    data => { this.years=data; } , 
    err => { console.log(err);}
  )
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
    this.es.importModules(fd).subscribe(data => {
    }, err => { console.log(err); });
    if (this.file.name =='exemple_maquette_1.xlsx')
    this.toastr.success('La liste des modules est bien importée', ' Importation avec Succées'); 
    else 
    this.toastr.error(" Veuillez choisir le fichier convenable","Importation imopssible");
  }

  /**
   * Retourner toutes les unites
   */
  getUnites():void{
    this.us.listeUnite().subscribe(
      data =>{ 
        this.unites= data;
        console.log(this.unites[0])

        this.dataSource = new MatTableDataSource(this.unites);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
       }
    , err=> { }
    )
  }
}
