import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {switchMap} from "rxjs";
import {RouterLink, RouterOutlet} from "@angular/router";
import {MaterialModule} from "../../material/material.module";
import { Matricula } from '../../modelo/Matricula';
import { FormMatriculaComponent } from './form-matricula/form-matricula.component';
import { MatriculaService } from '../../servicio/matricula.service';

@Component({
  selector: 'app-main-matricula',
  standalone: true,
  imports: [MaterialModule, RouterOutlet, RouterLink],
  templateUrl: './main-matricula.component.html',
  styleUrl: './main-matricula.component.css'
})
export class MainMatriculaComponent implements OnInit {

  dataSource: MatTableDataSource<Matricula>;
  columnsDefinitions = [
    { def: 'idMatricula', label: 'idMatricula', hide: true},
    { def: 'ieEstudio', label: 'ieEstudio', hide: false},
    { def: 'escuelaPostula', label: 'escuelaPostula', hide: false},
    { def: 'antePatoPsico', label: 'antePatoPsico', hide: false},
    { def: 'antePoliJudi', label: 'antePoliJudi', hide: false},
    { def: 'declaracionJurada', label: 'declaracionJurada', hide: false},
    { def: 'familiarMilitarPolicial', label: 'familiarMilitarPolicial', hide: false},
    { def: 'fechaIncorporacion', label: 'fechaIncorporacion', hide: false},
    { def: 'lugarNatural', label: 'lugarNatural', hide: false},
    { def: 'natacion', label: 'natacion', hide: false},
    { def: 'otros', label: 'otros', hide: false},
    { def: 'peso', label: 'peso', hide: false},
    { def: 'talla', label: 'talla', hide: false},
    { def: 'estudiante', label: 'estudiante', hide: false},
    { def: 'apoderado', label: 'apoderado', hide: false},
    { def: 'medio', label: 'medio', hide: false},
    { def: 'plan', label: 'plan', hide: false},
    { def: 'institucion', label: 'institucion', hide: false},
    { def: 'grupo', label: 'grupo', hide: false},
    { def: 'acciones', label: 'acciones', hide: false}
  ];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private krervice: MatriculaService,
    private _dialog: MatDialog,
    private _snackBar: MatSnackBar
  ){}
  ngOnInit(): void {
    this.krervice.findAll().subscribe(data => this.createTable(data));

    this.krervice.getMatriculaChange().subscribe(data => this.createTable(data));
    this.krervice.getMessageChange().subscribe(data => this._snackBar.open(data, 'INFO', {duration: 2000}))
  }
  createTable(data: Matricula[]){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  getDisplayedColumns(){
    return this.columnsDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }
  openDialog(krentidad?: Matricula){
    this._dialog.open(FormMatriculaComponent, {
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
        this.krervice.setMatriculaChange(data);
        this.krervice.setMessageChange('DELETED!');
      });
  }
}
