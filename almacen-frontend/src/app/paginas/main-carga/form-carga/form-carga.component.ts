import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {MaterialModule} from "../../../material/material.module";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {switchMap} from "rxjs";
import { Carga } from '../../../modelo/Carga';
import { CargaService } from '../../../servicio/carga.service';
import { CursoService } from '../../../servicio/curso.service';
import { SeccionService } from '../../../servicio/seccion.service';
import { Curso } from '../../../modelo/Curso';
import { Seccion } from '../../../modelo/Seccion';
import { PeriodoService } from '../../../servicio/periodo.service';
import { TrabajadorService } from '../../../servicio/trabajador.service';
import { Periodo } from '../../../modelo/Periodo';
import { Trabajador } from '../../../modelo/Trabajador';

@Component({
  selector: 'app-form-carga',
  standalone: true,
  imports: [MaterialModule, FormsModule, NgIf, ReactiveFormsModule, NgForOf],  
  templateUrl: './form-carga.component.html',
  styleUrl: './form-carga.component.css'
})
export class FormCargaComponent implements OnInit {


  @ViewChild('CargaForm') CargaForm!: NgForm ;
  form: FormGroup;

  periodos: Periodo[] = [];
  trabajadores: Trabajador[] = [];
  cursos: Curso[] = [];
  secciones: Seccion[] = [];


  constructor(
    @Inject(MAT_DIALOG_DATA) private data: Carga,
    private krService: CargaService,
    private _dialogRef: MatDialogRef<FormCargaComponent>,
    private servicioPeriodo: PeriodoService,
    private servicioTrabajador: TrabajadorService,
    private servicioCurso: CursoService,
    private servicioSeccion: SeccionService,

  ){}
  ngOnInit(): void {
    // Apartir de aqui para las foranikeys
    this.servicioPeriodo.findAll().subscribe(data=>{
      this.servicioPeriodo.setPeriodoChange(data);
    });
    this.servicioPeriodo.getPeriodoChange().subscribe(data=>{
      console.log(data);
      this.periodos = data;
    })
    // Apartir de aqui para las foranikeys
    this.servicioTrabajador.findAll().subscribe(data=>{
      this.servicioTrabajador.setTrabajadorChange(data);
    });
    this.servicioTrabajador.getTrabajadorChange().subscribe(data=>{
      console.log(data);
      this.trabajadores = data;
    })

    // Apartir de aqui para las foranikeys
    this.servicioCurso.findAll().subscribe(data=>{
      this.servicioCurso.setCursoChange(data);
    });
    this.servicioCurso.getCursoChange().subscribe(data=>{
      console.log(data);
      this.cursos = data;
    })
    // AQUI terniba para  las foranikeys

    // Apartir de aqui para las foranikeys
    this.servicioSeccion.findAll().subscribe(data=>{
      this.servicioSeccion.setSeccionChange(data);
    });
    this.servicioSeccion.getSeccionChange().subscribe(data=>{
      console.log(data);
      this.secciones = data;
    })
// AQUI terniba para  las foranikeys
    if(this.data!==undefined){
      console.log(this.data['periodo']);
      console.log(this.data['trabajador']);
      console.log(this.data['curso']);
      console.log(this.data['seccion']);

      this.form = new FormGroup({
        idCarga: new FormControl(this.data['idCarga']),
        periodo: new FormControl(this.data['periodo'], [Validators.required]),
        trabajador: new FormControl(this.data['trabajador'], [Validators.required]),
        curso: new FormControl(this.data['curso'], [Validators.required]),
        seccion: new FormControl(this.data['seccion'], [Validators.required]),
      });


    }else{
      this.form = new FormGroup({
        idCarga: new FormControl(0),
        periodo: new FormControl(this.periodos, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        trabajador: new FormControl(this.trabajadores, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        curso: new FormControl(this.cursos, [Validators.required]),
        seccion: new FormControl(this.secciones, [Validators.required])
      });
    }
  }
  close(){
    this._dialogRef.close();
  }
  operate(){
    const kr: Carga = new Carga();
    kr.idCarga = this.form.value['idCarga'];
    kr.periodo = this.form.value['periodo'];
    kr.trabajador = this.form.value['trabajador'];
    kr.curso = this.form.value['curso'];
    kr.seccion = this.form.value['seccion'];

    if(this.CargaForm.valid){
      if(kr.idCarga > 0){
        //UPDATE
        this.krService.update(kr.idCarga, kr)
          .pipe(switchMap( ()=> this.krService.findAll() ))
          .subscribe(data => {
            this.krService.setCargaChange(data);
            this.krService.setMessageChange('UPDATED!');
            this.close();
          });
      }else{
        //INSERT
        this.krService.save(kr)
          .pipe(switchMap( ()=> this.krService.findAll() ))
          .subscribe(data => {
            this.krService.setCargaChange(data);
            this.krService.setMessageChange('CREATED!');
            this.close();
          });
      }
    }else{
      console.log("Error....")
    }
  }
  get f(){
    return this.form.controls;
  }
}

