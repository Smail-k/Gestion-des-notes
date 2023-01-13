import { AfterViewInit, Component, OnInit } from '@angular/core';
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
  selector: 'app-gestion-des-ue',
  templateUrl: './gestion-des-ue.component.html',
  styleUrls: ['./gestion-des-ue.component.css']
})
export class GestionDesUEComponent implements OnInit, AfterViewInit {

  constructor() { }
  selectedValue!: string;
  selectedYearValue!: string;

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
    $('#example2').DataTable();
    console.log('ngAfterViewInit GUE');
  }

  ngOnInit(): void {
    // $('#example2').DataTable();
    // console.log('ngOnInit GUE');
  }

 
}
