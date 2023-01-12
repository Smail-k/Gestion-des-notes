import { AfterViewInit, Component, OnInit } from '@angular/core';
declare const $:any;

@Component({
  selector: 'app-gestion-des-administrateurs',
  templateUrl: './gestion-des-administrateurs.component.html',
  styleUrls: ['./gestion-des-administrateurs.component.css']
})
export class GestionDesAdministrateursComponent implements OnInit, AfterViewInit {

  constructor() { }
  ngAfterViewInit(): void {
    $('#user-table-id').DataTable();
  }

  ngOnInit(): void {
  }

}
