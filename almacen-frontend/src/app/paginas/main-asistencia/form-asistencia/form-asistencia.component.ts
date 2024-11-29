import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {MaterialModule} from "../../../material/material.module";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {switchMap} from "rxjs";
import { EstudianteService } from '../../../servicio/estudiante.service';
import { CursoService } from '../../../servicio/curso.service';
import { Curso } from '../../../modelo/Curso';
import { Estudiante } from '../../../modelo/Estudiante';
import { Asistencia } from '../../../modelo/Asistencia';
import { AsistenciaService } from '../../../servicio/asistencia.service';
import { PeriodoService } from '../../../servicio/periodo.service';
import { Periodo } from '../../../modelo/Periodo';

@Component({
  selector: 'app-form-asistencia',
  standalone: true,
  imports: [MaterialModule, FormsModule, NgIf, ReactiveFormsModule, NgForOf],
  templateUrl: './form-asistencia.component.html',
  styleUrl: './form-asistencia.component.css'
})
export class FormAsistenciaComponent implements OnInit {


  @ViewChild('AsistenciaForm') AsistenciaForm!: NgForm ;
  form: FormGroup;

  estudiantes: Estudiante[] = [];
  cursos: Curso[] = [];
  periodos: Periodo[]=[];


  constructor(
    @Inject(MAT_DIALOG_DATA) private data: Asistencia,
    private krService: AsistenciaService,
    private _dialogRef: MatDialogRef<FormAsistenciaComponent>,
    private servicioPeriodo: PeriodoService,
    private servicioEstudiante: EstudianteService,
    private servicioCurso: CursoService,
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
    // AQUI terniba para  las foranikeys

    // Apartir de aqui para las foranikeys
    this.servicioEstudiante.findAll().subscribe(data=>{
      this.servicioEstudiante.setEstudianteChange(data);
    });
    this.servicioEstudiante.getEstudianteChange().subscribe(data=>{
      console.log(data);
      this.estudiantes = data;
    })
    // AQUI terniba para  las foranikeys

    // Apartir de aqui para las foranikeys
    this.servicioCurso.findAll().subscribe(data=>{
      this.servicioCurso.setCursoChange(data);
    });
    this.servicioCurso.getCursoChange().subscribe(data=>{
      console.log(data);
      this.cursos = data;
    })
// AQUI terniba para  las foranikeys
    if(this.data!==undefined){
      console.log(this.data['fecharegistro']);
      console.log(this.data['estadotencia']);
      console.log(this.data['periodo']);
      console.log(this.data['estudiante']);
      console.log(this.data['curso']);

      this.form = new FormGroup({
        idAsistencia: new FormControl(this.data['idAsistencia']),
        fecharegistro: new FormControl(this.data['fecharegistro'], [Validators.required]),
        estadotencia: new FormControl(this.data['estadotencia'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        periodo: new FormControl(this.data['periodo'], [Validators.required]),
        trabajador: new FormControl(this.data['estudiante'], [Validators.required]),
        personal: new FormControl(this.data['curso'], [Validators.required]),
      });


    }else{
      this.form = new FormGroup({
        idAsistencia: new FormControl(0),
        fecharegistro: new FormControl('', [Validators.required]),
        estadotencia: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        periodo: new FormControl(this.periodos, [Validators.required]),
        estudiante: new FormControl(this.estudiantes, [Validators.required]),
        curso: new FormControl(this.cursos, [Validators.required])
      });
    }
  }
  close(){
    this._dialogRef.close();
  }
  operate(){
    const kr: Asistencia = new Asistencia();
    kr.idAsistencia = this.form.value['idAsistencia'];
    kr.fecharegistro = this.form.value['fecharegistro'];
    kr.estadotencia = this.form.value['estadotencia'];
    kr.periodo = this.form.value['periodo'];
    kr.estudiante = this.form.value['estudiante'];
    kr.curso = this.form.value['curso'];

    if(this.AsistenciaForm.valid){
      if(kr.idAsistencia > 0){
        //UPDATE
        this.krService.update(kr.idAsistencia, kr)
          .pipe(switchMap( ()=> this.krService.findAll() ))
          .subscribe(data => {
            this.krService.setAsistenciaChange(data);
            this.krService.setMessageChange('UPDATED!');
            this.close();
          });
      }else{
        //INSERT
        this.krService.save(kr)
          .pipe(switchMap( ()=> this.krService.findAll() ))
          .subscribe(data => {
            this.krService.setAsistenciaChange(data);
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

