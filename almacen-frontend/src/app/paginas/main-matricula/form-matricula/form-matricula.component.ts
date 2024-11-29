import {NgForOf, NgIf} from "@angular/common";
import {MaterialModule} from "../../../material/material.module";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {switchMap} from "rxjs";
import { FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from "@angular/forms";
import { Component, Inject, OnInit, ViewChild } from "@angular/core";
import { Estudiante } from "../../../modelo/Estudiante";
import { Apoderado } from "../../../modelo/Apoderado";
import { Medio } from "../../../modelo/Medio";
import { Plan } from "../../../modelo/Plan";
import { Institucion } from "../../../modelo/Institucion";
import { Grupo } from "../../../modelo/Grupo";
import { Matricula } from "../../../modelo/Matricula";
import { MatriculaService } from "../../../servicio/matricula.service";
import { GrupoService } from "../../../servicio/grupo.service";
import { InstitucionService } from "../../../servicio/institucion.service";
import { PlanService } from "../../../servicio/plan.service";
import { MedioService } from "../../../servicio/medio.service";
import { ApoderadoService } from "../../../servicio/apoderado.service";
import { EstudianteService } from "../../../servicio/estudiante.service";

@Component({
  selector: 'app-form-matricula',
  standalone: true,
  imports: [MaterialModule, FormsModule, NgIf, ReactiveFormsModule, NgForOf],
  templateUrl: './form-matricula.component.html',
  styleUrl: './form-matricula.component.css'
})
export class FormMatriculaComponent implements OnInit {


  @ViewChild('MatriculaForm') MatriculaForm!: NgForm ;
  form: FormGroup;

  estudiantes: Estudiante[] = [];
  apoderados: Apoderado[] = [];
  medios: Medio[] = [];
  planes: Plan[] = [];
  instituciones: Institucion[] = [];
  grupos: Grupo[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: Matricula,
    private krService: MatriculaService,
    private _dialogRef: MatDialogRef<FormMatriculaComponent>,

    private fk1: EstudianteService,
    private fk2: ApoderadoService,
    private fk3: MedioService,
    private fk4: PlanService,
    private fk5: InstitucionService,
    private fk6: GrupoService,
  ){}
  ngOnInit(): void {

    // Apartir de aqui para las foranikeys
    this.fk1.findAll().subscribe(data=>{
      this.fk1.setEstudianteChange(data);
    });
    this.fk1.getEstudianteChange().subscribe(data=>{
      console.log(data);
      this.estudiantes = data;
    })
    // Apartir de aqui para las foranikeys
    this.fk2.findAll().subscribe(data=>{
      this.fk2.setApoderadoChange(data);
    });
    this.fk2.getApoderadoChange().subscribe(data=>{
      console.log(data);
      this.apoderados = data;
    })
    // Apartir de aqui para las foranikeys
    this.fk3.findAll().subscribe(data=>{
      this.fk3.setMedioChange(data);
    });
    this.fk3.getMedioChange().subscribe(data=>{
      console.log(data);
      this.medios = data;
    })
    // Apartir de aqui para las foranikeys
    this.fk4.findAll().subscribe(data=>{
      this.fk4.setPlanChange(data);
    });
    this.fk4.getPlanChange().subscribe(data=>{
      console.log(data);
      this.planes = data;
    })
    // Apartir de aqui para las foranikeys
    this.fk5.findAll().subscribe(data=>{
      this.fk5.setInstitucionChange(data);
    });
    this.fk5.getInstitucionChange().subscribe(data=>{
      console.log(data);
      this.instituciones = data;
    })
    // Apartir de aqui para las foranikeys
    this.fk6.findAll().subscribe(data=>{
      this.fk6.setGrupoChange(data);
    });
    this.fk6.getGrupoChange().subscribe(data=>{
      console.log(data);
      this.grupos = data;
    })
// AQUI terniba para  las foranikeys
    if(this.data!==undefined){
      console.log(this.data['ieEstudio']);
      console.log(this.data['escuelaPostula']);
      console.log(this.data['antePatoPsico']);
      console.log(this.data['antePoliJudi']);
      console.log(this.data['declaracionJurada']);
      console.log(this.data['direccion']);
      console.log(this.data['familiarMilitarPolicial']);
      console.log(this.data['fechaIncorporacion']);
      console.log(this.data['lugarNatural']);
      console.log(this.data['natacion']);
      console.log(this.data['otros']);
      console.log(this.data['peso']);
      console.log(this.data['talla']);

      console.log(this.data['estudiante']);
      console.log(this.data['apoderado']);
      console.log(this.data['medio']);
      console.log(this.data['plan']);
      console.log(this.data['institucion']);
      console.log(this.data['grupo']);


      this.form = new FormGroup({
        idMatricula: new FormControl(this.data['idMatricula']),        
        ieEstudio: new FormControl(this.data['ieEstudio'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        escuelaPostula: new FormControl(this.data['escuelaPostula'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        antePatoPsico: new FormControl(this.data['antePatoPsico'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        antePoliJudi: new FormControl(this.data['antePoliJudi'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        declaracionJurada: new FormControl(this.data['declaracionJurada'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        direccion: new FormControl(this.data['direccion'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        familiarMilitarPolicial: new FormControl(this.data['familiarMilitarPolicial'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        fechaIncorporacion: new FormControl(this.data['fechaIncorporacion'], [Validators.required]),
        lugarNatural: new FormControl(this.data['lugarNatural'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        natacion: new FormControl(this.data['natacion'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        otros: new FormControl(this.data['otros'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        peso: new FormControl(this.data['peso'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        talla: new FormControl(this.data['talla'], [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        
        estudiante: new FormControl(this.data['estudiante'], [Validators.required]),
        apoderado: new FormControl(this.data['apoderado'], [Validators.required]),
        medio: new FormControl(this.data['medio'], [Validators.required]),
        plan: new FormControl(this.data['plan'], [Validators.required]),
        institucion: new FormControl(this.data['institucion'], [Validators.required]),
        grupo: new FormControl(this.data['grupo'], [Validators.required]),
      });


    }else{
      this.form = new FormGroup({
        idMatricula: new FormControl(0),

        ieEstudio: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        escuelaPostula: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        antePatoPsico: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        antePoliJudi: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        declaracionJurada: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        direccion: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        familiarMilitarPolicial: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        fechaIncorporacion: new FormControl('', [Validators.required]),
        lugarNatural: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        natacion: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        otros: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        peso: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
        talla: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),

        
        estudiante: new FormControl(this.estudiantes, [Validators.required]),
        apoderado: new FormControl(this.apoderados, [Validators.required]),
        medio: new FormControl(this.medios, [Validators.required]),
        plan: new FormControl(this.planes, [Validators.required]),
        institucion: new FormControl(this.instituciones, [Validators.required]),
        grupo: new FormControl(this.grupos, [Validators.required]),
      });
    }
  }
  close(){
    this._dialogRef.close();
  }
  operate(){
    const kr: Matricula = new Matricula();
    kr.idMatricula = this.form.value['idMatricula'];
    
    kr.ieEstudio = this.form.value['ieEstudio'];
    kr.escuelaPostula = this.form.value['escuelaPostula'];
    kr.antePatoPsico = this.form.value['antePatoPsico'];
    kr.antePoliJudi = this.form.value['antePoliJudi'];
    kr.declaracionJurada = this.form.value['declaracionJurada'];
    kr.direccion = this.form.value['direccion'];
    kr.familiarMilitarPolicial = this.form.value['familiarMilitarPolicial'];
    kr.fechaIncorporacion = this.form.value['fechaIncorporacion'];
    kr.lugarNatural = this.form.value['lugarNatural'];
    kr.natacion = this.form.value['natacion'];
    kr.otros = this.form.value['otros'];
    kr.peso = this.form.value['peso'];
    kr.talla = this.form.value['talla'];

    kr.estudiante = this.form.value['estudiante'];
    kr.apoderado = this.form.value['apoderado'];
    kr.medio = this.form.value['medio'];
    kr.plan = this.form.value['plan'];
    kr.institucion = this.form.value['institucion'];
    kr.grupo = this.form.value['grupo'];
    

    if(this.MatriculaForm.valid){
      if(kr.idMatricula > 0){
        //UPDATE
        this.krService.update(kr.idMatricula, kr)
          .pipe(switchMap( ()=> this.krService.findAll() ))
          .subscribe(data => {
            this.krService.setMatriculaChange(data);
            this.krService.setMessageChange('UPDATED!');
            this.close();
          });
      }else{
        //INSERT
        this.krService.save(kr)
          .pipe(switchMap( ()=> this.krService.findAll() ))
          .subscribe(data => {
            this.krService.setMatriculaChange(data);
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
