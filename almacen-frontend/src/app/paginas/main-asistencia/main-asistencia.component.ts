import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {switchMap} from "rxjs";
import {RouterLink, RouterOutlet} from "@angular/router";
import {MaterialModule} from "../../material/material.module";
import { Asistencia } from '../../modelo/Asistencia';
import { AsistenciaService } from '../../servicio/asistencia.service';
import { FormAsistenciaComponent } from './form-asistencia/form-asistencia.component';

@Component({
  selector: 'app-main-asistencia',
  standalone: true,
  imports: [MaterialModule, RouterOutlet, RouterLink],
  templateUrl: './main-asistencia.component.html',
  styleUrl: './main-asistencia.component.css'
})
export class MainAsistenciaComponent implements OnInit {
  dataSource: MatTableDataSource<Asistencia>;
  columnsDefinitions = [
    { def: 'idAsistencia', label: 'idAsistencia', hide: true},
    { def: 'fecharegistro', label: 'fecharegistro', hide: false},
    { def: 'estadotencia', label: 'estadotencia', hide: false},
    { def: 'periodo', label: 'periodo', hide: false},
    { def: 'estudiante', label: 'estudiante', hide: false},
    { def: 'curso', label: 'curso', hide: false},
    { def: 'acciones', label: 'acciones', hide: false}
  ];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private krervice: AsistenciaService,
    private _dialog: MatDialog,
    private _snackBar: MatSnackBar
  ){}
  ngOnInit(): void {
    this.krervice.findAll().subscribe(data => this.createTable(data));

    this.krervice.getAsistenciaChange().subscribe(data => this.createTable(data));
    this.krervice.getMessageChange().subscribe(data => this._snackBar.open(data, 'INFO', {duration: 2000}))
  }
  createTable(data: Asistencia[]){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  getDisplayedColumns(){
    return this.columnsDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }
  openDialog(krentidad?: Asistencia){
    this._dialog.open(FormAsistenciaComponent, {
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
        this.krervice.setAsistenciaChange(data);
        this.krervice.setMessageChange('DELETED!');
      });
  }
}
