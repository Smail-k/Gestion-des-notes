import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Matiere } from 'src/app/models/matiere';
import { PromotionService } from 'src/app/services/promotion.service';
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
  selector: 'app-gestion-des-matieres',
  templateUrl: './gestion-des-matieres.component.html',
  styleUrls: ['./gestion-des-matieres.component.css']
})
export class GestionDesMatieresComponent implements OnInit, AfterViewInit {

  constructor(private ps:PromotionService) { }
  ngAfterViewInit(): void {
    $('#dt-mat-id').DataTable();
  }

  selectedValue!: string;
  selectedYearValue!: string;
  promotions: any[]=[];
  years: any[]=[];
  searchKey!:any;
  listData! : MatTableDataSource<any>;
  displayedColumns : string[] = ['code', 'libelle','coeffecient','unite' ];
  @ViewChild(MatSort) sort! : MatSort;
  @ViewChild (MatPaginator) paginator! : MatPaginator;
  dataSource!: MatTableDataSource<Matiere>;



  ngOnInit(): void {
   this.getAnnees(); 
   this.getPromotions();
  }

  
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
      data => { this.years=data;
      } , 
      err => { console.log(err);}
    )
  }

  applyFilter(){this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase(); }
  
onSearchClear(){
  this.searchKey="";
  this.applyFilter();
}


}
