import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {switchMap} from "rxjs";
import {RouterLink, RouterOutlet} from "@angular/router";
import {MaterialModule} from "../../material/material.module";
import { Carga } from '../../modelo/Carga';
import { CargaService } from '../../servicio/carga.service';
import { FormCargaComponent } from './form-carga/form-carga.component';

@Component({
  selector: 'app-main-carga',
  standalone: true,
  imports: [MaterialModule, RouterOutlet, RouterLink],
  templateUrl: './main-carga.component.html',
  styleUrl: './main-carga.component.css'
})
export class MainCargaComponent implements OnInit {
  dataSource: MatTableDataSource<Carga>;
  columnsDefinitions = [
    { def: 'idCarga', label: 'idCarga', hide: true},
    { def: 'periodo', label: 'periodo', hide: false},
    { def: 'trabajador', label: 'trabajador', hide: false},
    { def: 'curso', label: 'curso', hide: false},
    { def: 'seccion', label: 'seccion', hide: false}
  ];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private krervice: CargaService,
    private _dialog: MatDialog,
    private _snackBar: MatSnackBar
  ){}
  ngOnInit(): void {
    this.krervice.findAll().subscribe(data => this.createTable(data));

    this.krervice.getCargaChange().subscribe(data => this.createTable(data));
    this.krervice.getMessageChange().subscribe(data => this._snackBar.open(data, 'INFO', {duration: 2000}))
  }
  createTable(data: Carga[]){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  getDisplayedColumns(){
    return this.columnsDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }
  openDialog(krentidad?: Carga){
    this._dialog.open(FormCargaComponent, {
      width: '750px',
      data: krentidad,
      disableClose: true
    });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  delete(idMedic: number){
    this.krervice.delete(idMedic)
      .pipe(switchMap( ()=> this.krervice.findAll()))
      .subscribe(data => {
        this.krervice.setCargaChange(data);
        this.krervice.setMessageChange('DELETED!');
      });
  }
}
