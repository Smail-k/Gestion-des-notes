import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/models/user';
import { UtilisateurService } from 'src/app/services/user.service';
declare const $:any;

@Component({
  selector: 'app-gestion-des-administrateurs',
  templateUrl: './gestion-des-administrateurs.component.html',
  styleUrls: ['./gestion-des-administrateurs.component.css']
})
export class GestionDesAdministrateursComponent implements OnInit, AfterViewInit {

  listData! : MatTableDataSource<any>;
  displayedColumns : string[] = ['id' , 'nom', 'nomutilisateur' ];
  dataSource!: MatTableDataSource<User>;
  @ViewChild(MatSort) sort! : MatSort;
  @ViewChild (MatPaginator) paginator! : MatPaginator;
  utilisateurs? : User[];
  constructor(private us: UtilisateurService) { }

  ngAfterViewInit(): void {
  }

  ngOnInit(): void {
    this.listerUtilisateurs();
  }

  listerUtilisateurs(){
    this.us.listeUtilisateurs().subscribe(data=> {
      this.utilisateurs=data;
      this.dataSource = new MatTableDataSource(this.utilisateurs);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    
    });
  }

}
