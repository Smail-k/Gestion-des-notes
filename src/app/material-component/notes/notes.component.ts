import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Unite } from 'src/app/models/unite';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { PromotionService } from 'src/app/services/promotion.service';
import { UniteService } from 'src/app/services/unite.service';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit, AfterViewInit {

  constructor(private ps: PromotionService, private toastr:ToastrService,private es:EtudiantService,private us:UniteService) { }
  ngAfterViewInit(): void {
    $('#dt-mat-id').DataTable();
  }


  selectedValue!: string;
  selectedYearValue!: string;
  promotions: any[] = [];
  years: any[] = [];
  searchKey!: any;

  form! :FormGroup;
  unites:Unite[]=[];
  selectedFile!: File;
  file!: File;
  getvalue?: any;

  displayedColumns : string[] = ['code', 'nom','prenom','codeUE','MoyenneUE'];
  listData!: MatTableDataSource<any>;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;





  ngOnInit(): void {
    this.getAnnees();
    this.getPromotions();
    //this.getUnites();
  }


  /**
   * Retourner les promotions
   */
  getPromotions() {
    this.ps.getpromotions().subscribe(
      data => {
        this.promotions = data;
      }, err => { console.log(err); });
  }

  /**
   * Retourner les années 
   */
  getAnnees() {
    this.ps.getannees().subscribe(
      data => {
        this.years = data;
      },
      err => { console.log(err); }
    )
  }



  applyFilter() { 
    //this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase(); 
  }

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
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
    this.es.importNotes(fd).subscribe(data => {
    }, err => { console.log(err); });
    this.toastr.success('Importation avec Succées', 'La liste des notes est bien importée'); 
  }

  /**
   * Retourner toutes les unites
   */

  
}
