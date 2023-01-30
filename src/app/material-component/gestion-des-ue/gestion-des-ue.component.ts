import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Unite } from 'src/app/models/unite';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { PromotionService } from 'src/app/services/promotion.service';

@Component({
selector: 'app-gestion-des-ue',
templateUrl: './gestion-des-ue.component.html',
styleUrls: ['./gestion-des-ue.component.css']
})
export class GestionDesUEComponent implements OnInit, AfterViewInit {

constructor(private ps:PromotionService, private fb:FormBuilder,private toastr:ToastrService,private es:EtudiantService) { this.initForm();}
selectedValue!: string;
selectedYearValue!: string;
promotions: any[]=[];
years: any[]=[];
form! :FormGroup;
selectedFile!: File;
file!: File;
getvalue?: any;
searchKey!:any;
listData! : MatTableDataSource<any>;
displayedColumns : string[] = ['code', 'libelle','coeffecient','semestre' ];
@ViewChild(MatSort) sort! : MatSort;
@ViewChild (MatPaginator) paginator! : MatPaginator;
dataSource!: MatTableDataSource<Unite>;




ngAfterViewInit(): void {

}

ngOnInit(): void {
this.getAnnees(); 
this.getPromotions();
console.log(this.f.promotion)
}

initForm(): void {
this.form = this.fb.group({
promotion: new FormControl('',Validators.required),
annee: new FormControl('',Validators.required),
});
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
    this.toastr.success('Importation avec Succées', 'La liste des modules est bien importée'); 
  }

}
