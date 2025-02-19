PGDMP                   
    |            Academia_DB    16.4    16.4 �               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    50754    Academia_DB    DATABASE        CREATE DATABASE "Academia_DB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Peru.1252';
    DROP DATABASE "Academia_DB";
                postgres    false                        2615    50755    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false                       0    0    SCHEMA public    COMMENT         COMMENT ON SCHEMA public IS '';
                   postgres    false    5                       0    0    SCHEMA public    ACL     +   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
                   postgres    false    5                       1255    51203    audit_changes_estudiante()    FUNCTION     g  CREATE FUNCTION public.audit_changes_estudiante() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO estudiante_audit_log (table_name, operation_type, record_id, old_values, new_values)
    VALUES (
        TG_TABLE_NAME,
        TG_OP,
        OLD.estudiante_id,
        TO_JSONB(OLD),
        TO_JSONB(NEW)
    );
    RETURN NEW;
END;
$$;
 1   DROP FUNCTION public.audit_changes_estudiante();
       public          postgres    false    5                       1255    51133     calcular_promedio_notas(integer)    FUNCTION     �  CREATE FUNCTION public.calcular_promedio_notas(competencia_id_param integer) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
DECLARE
    promedio DECIMAL;
BEGIN
    SELECT AVG(nota) INTO promedio
    FROM academia_notas
    WHERE competencia_id = competencia_id_param;

    IF promedio IS NULL THEN
        RAISE EXCEPTION 'No hay notas para la competencia con ID %', competencia_id_param;
    END IF;

    RETURN promedio;
END;
$$;
 L   DROP FUNCTION public.calcular_promedio_notas(competencia_id_param integer);
       public          postgres    false    5                       1255    51137 !   listar_cursos_trabajador(integer)    FUNCTION     {  CREATE FUNCTION public.listar_cursos_trabajador(trabajador_id_param integer) RETURNS TABLE(curso_id integer, curso_nombre character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY
    SELECT c.id, c.nombre
    FROM cargaacademica ca
    INNER JOIN academia_curso c ON ca.academia_curso_id = c.id
    WHERE ca.academia_trabajador_id = trabajador_id_param;
END;
$$;
 L   DROP FUNCTION public.listar_cursos_trabajador(trabajador_id_param integer);
       public          postgres    false    5                       1255    51134 +   obtener_nombre_completo_estudiante(integer)    FUNCTION       CREATE FUNCTION public.obtener_nombre_completo_estudiante(estudiante_id_param integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
DECLARE
    nombre_completo VARCHAR;
BEGIN
    SELECT CONCAT(nombres, ' ', apellidoPaterno, ' ', apellidoMaterno)
    INTO nombre_completo
    FROM estudiante
    WHERE idestudiante = estudiante_id_param;

    IF nombre_completo IS NULL THEN
        RAISE EXCEPTION 'No se encontró un estudiante con ID %', estudiante_id_param;
    END IF;

    RETURN nombre_completo;
END;
$$;
 V   DROP FUNCTION public.obtener_nombre_completo_estudiante(estudiante_id_param integer);
       public          postgres    false    5                       1255    51132 !   ontenercelularestudiante(integer)    FUNCTION     �   CREATE FUNCTION public.ontenercelularestudiante(ides integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare celu integer;

BEGIN
	 select celular into celu from estudiante where idestudiante=ides;
	 return celu;

end;
	
$$;
 =   DROP FUNCTION public.ontenercelularestudiante(ides integer);
       public          postgres    false    5                       1255    51135 R   registrar_incidencia(character varying, character varying, date, integer, integer)    FUNCTION     �  CREATE FUNCTION public.registrar_incidencia(p_castigo character varying, p_descripcion character varying, p_fecha date, p_curso_id integer, p_estudiante_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO academia_insidencias (castigo, descripcion, fecha, curso_id, estudiante_idestudiante)
    VALUES (p_castigo, p_descripcion, p_fecha, p_curso_id, p_estudiante_id);

    RAISE NOTICE 'Incidencia registrada exitosamente para el estudiante %', p_estudiante_id;
END;
$$;
 �   DROP FUNCTION public.registrar_incidencia(p_castigo character varying, p_descripcion character varying, p_fecha date, p_curso_id integer, p_estudiante_id integer);
       public          postgres    false    5                        1255    51208    update_curso_timestamp()    FUNCTION     �   CREATE FUNCTION public.update_curso_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$;
 /   DROP FUNCTION public.update_curso_timestamp();
       public          postgres    false    5                       1255    51205    validate_promedio_general()    FUNCTION       CREATE FUNCTION public.validate_promedio_general() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF NEW.promedio_general < 0 OR NEW.promedio_general > 20 THEN
        RAISE EXCEPTION 'El promedio general debe estar entre 0 y 20.';
    END IF;
    RETURN NEW;
END;
$$;
 2   DROP FUNCTION public.validate_promedio_general();
       public          postgres    false    5                       1255    51136 *   verificar_comunicados_institucion(integer)    FUNCTION     S  CREATE FUNCTION public.verificar_comunicados_institucion(institucion_id_param integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
    existe BOOLEAN;
BEGIN
    SELECT EXISTS (
        SELECT 1
        FROM academia_comunicados
        WHERE institucion_id = institucion_id_param
    ) INTO existe;

    RETURN existe;
END;
$$;
 V   DROP FUNCTION public.verificar_comunicados_institucion(institucion_id_param integer);
       public          postgres    false    5            �            1259    50764    academia_competencia    TABLE     �   CREATE TABLE public.academia_competencia (
    id integer NOT NULL,
    nombre_competencia character varying(30) NOT NULL,
    subpromedio numeric(20,2) NOT NULL,
    curso_id integer NOT NULL
);
 (   DROP TABLE public.academia_competencia;
       public         heap    postgres    false    5            �            1259    50763    academia_competencia_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_competencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.academia_competencia_id_seq;
       public          postgres    false    218    5                       0    0    academia_competencia_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.academia_competencia_id_seq OWNED BY public.academia_competencia.id;
          public          postgres    false    217            �            1259    50797    academia_comunicados    TABLE     �   CREATE TABLE public.academia_comunicados (
    id integer NOT NULL,
    asunto character varying(100) NOT NULL,
    descripcion character varying(500) NOT NULL,
    fecha date NOT NULL,
    institucion_id integer NOT NULL
);
 (   DROP TABLE public.academia_comunicados;
       public         heap    postgres    false    5            �            1259    50796    academia_comunicados_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_comunicados_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.academia_comunicados_id_seq;
       public          postgres    false    5    224                       0    0    academia_comunicados_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.academia_comunicados_id_seq OWNED BY public.academia_comunicados.id;
          public          postgres    false    223            �            1259    50757    academia_curso    TABLE     �   CREATE TABLE public.academia_curso (
    id integer NOT NULL,
    nombre character varying(45) NOT NULL,
    promediogeneral numeric(20,2) NOT NULL
);
 "   DROP TABLE public.academia_curso;
       public         heap    postgres    false    5            �            1259    50756    academia_curso_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_curso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.academia_curso_id_seq;
       public          postgres    false    216    5                        0    0    academia_curso_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.academia_curso_id_seq OWNED BY public.academia_curso.id;
          public          postgres    false    215            �            1259    50926    academia_grupo    TABLE     �   CREATE TABLE public.academia_grupo (
    id integer NOT NULL,
    nombre character varying(45) NOT NULL,
    cupos integer NOT NULL,
    seccion_id integer NOT NULL
);
 "   DROP TABLE public.academia_grupo;
       public         heap    postgres    false    5            �            1259    50925    academia_grupo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_grupo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.academia_grupo_id_seq;
       public          postgres    false    5    246            !           0    0    academia_grupo_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.academia_grupo_id_seq OWNED BY public.academia_grupo.id;
          public          postgres    false    245            �            1259    50977    academia_insidencias    TABLE     	  CREATE TABLE public.academia_insidencias (
    id integer NOT NULL,
    castigo character varying(45) NOT NULL,
    descripcion character varying(200) NOT NULL,
    fecha date NOT NULL,
    curso_id integer NOT NULL,
    estudiante_idestudiante integer NOT NULL
);
 (   DROP TABLE public.academia_insidencias;
       public         heap    postgres    false    5            �            1259    50976    academia_insidencias_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_insidencias_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.academia_insidencias_id_seq;
       public          postgres    false    250    5            "           0    0    academia_insidencias_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.academia_insidencias_id_seq OWNED BY public.academia_insidencias.id;
          public          postgres    false    249            �            1259    50783    academia_institucion    TABLE     A  CREATE TABLE public.academia_institucion (
    id integer NOT NULL,
    lugar character varying(100) NOT NULL,
    imagen_logo bytea,
    mision character varying(500) NOT NULL,
    nombre_institucion character varying(45) NOT NULL,
    vicion character varying(500) NOT NULL,
    sede_institucion_id integer NOT NULL
);
 (   DROP TABLE public.academia_institucion;
       public         heap    postgres    false    5            �            1259    50782    academia_institucion_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_institucion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.academia_institucion_id_seq;
       public          postgres    false    5    222            #           0    0    academia_institucion_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.academia_institucion_id_seq OWNED BY public.academia_institucion.id;
          public          postgres    false    221            �            1259    50994    academia_logros    TABLE     �   CREATE TABLE public.academia_logros (
    id integer NOT NULL,
    descripcion character varying(500) NOT NULL,
    fecha date NOT NULL,
    imagen bytea,
    titulo character varying(100) NOT NULL,
    institucion_id integer NOT NULL
);
 #   DROP TABLE public.academia_logros;
       public         heap    postgres    false    5            �            1259    50993    academia_logros_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_logros_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.academia_logros_id_seq;
       public          postgres    false    5    252            $           0    0    academia_logros_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.academia_logros_id_seq OWNED BY public.academia_logros.id;
          public          postgres    false    251            �            1259    50837    academia_medio_interes    TABLE     z   CREATE TABLE public.academia_medio_interes (
    id integer NOT NULL,
    nombre_medio character varying(100) NOT NULL
);
 *   DROP TABLE public.academia_medio_interes;
       public         heap    postgres    false    5            �            1259    50836    academia_medio_interes_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_medio_interes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.academia_medio_interes_id_seq;
       public          postgres    false    232    5            %           0    0    academia_medio_interes_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.academia_medio_interes_id_seq OWNED BY public.academia_medio_interes.id;
          public          postgres    false    231            �            1259    51008    academia_notas    TABLE     �   CREATE TABLE public.academia_notas (
    id integer NOT NULL,
    nota numeric(5,2) NOT NULL,
    competencia_id integer NOT NULL
);
 "   DROP TABLE public.academia_notas;
       public         heap    postgres    false    5            �            1259    51007    academia_notas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_notas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.academia_notas_id_seq;
       public          postgres    false    5    254            &           0    0    academia_notas_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.academia_notas_id_seq OWNED BY public.academia_notas.id;
          public          postgres    false    253            �            1259    50844    academia_periodo    TABLE     u   CREATE TABLE public.academia_periodo (
    id integer NOT NULL,
    nombre_periodo character varying(20) NOT NULL
);
 $   DROP TABLE public.academia_periodo;
       public         heap    postgres    false    5            �            1259    50843    academia_periodo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_periodo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.academia_periodo_id_seq;
       public          postgres    false    5    234            '           0    0    academia_periodo_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.academia_periodo_id_seq OWNED BY public.academia_periodo.id;
          public          postgres    false    233            �            1259    50875    academia_seccion    TABLE     �   CREATE TABLE public.academia_seccion (
    id integer NOT NULL,
    nombre character varying(45) NOT NULL,
    nivel_idnivel integer NOT NULL
);
 $   DROP TABLE public.academia_seccion;
       public         heap    postgres    false    5            �            1259    50874    academia_seccion_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_seccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.academia_seccion_id_seq;
       public          postgres    false    240    5            (           0    0    academia_seccion_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.academia_seccion_id_seq OWNED BY public.academia_seccion.id;
          public          postgres    false    239            �            1259    50776    academia_sedeinstitucion    TABLE     {   CREATE TABLE public.academia_sedeinstitucion (
    id integer NOT NULL,
    nombre_sede character varying(100) NOT NULL
);
 ,   DROP TABLE public.academia_sedeinstitucion;
       public         heap    postgres    false    5            �            1259    50775    academia_sedeinstitucion_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_sedeinstitucion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.academia_sedeinstitucion_id_seq;
       public          postgres    false    5    220            )           0    0    academia_sedeinstitucion_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.academia_sedeinstitucion_id_seq OWNED BY public.academia_sedeinstitucion.id;
          public          postgres    false    219            �            1259    50851    academia_trabajador    TABLE     �  CREATE TABLE public.academia_trabajador (
    id integer NOT NULL,
    apellido_materno character varying(45) NOT NULL,
    apellido_paterno character varying(45) NOT NULL,
    celular character varying(9) NOT NULL,
    correo character varying(45) NOT NULL,
    nombre_completo character varying(45) NOT NULL,
    academia_institucion_id integer NOT NULL,
    persona_idpersona integer NOT NULL
);
 '   DROP TABLE public.academia_trabajador;
       public         heap    postgres    false    5            �            1259    50850    academia_trabajador_id_seq    SEQUENCE     �   CREATE SEQUENCE public.academia_trabajador_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.academia_trabajador_id_seq;
       public          postgres    false    5    236            *           0    0    academia_trabajador_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.academia_trabajador_id_seq OWNED BY public.academia_trabajador.id;
          public          postgres    false    235                       1259    51054    accesos    TABLE     �   CREATE TABLE public.accesos (
    idaccesos integer NOT NULL,
    nombre character varying(60),
    url character varying(100),
    icono character varying(100)
);
    DROP TABLE public.accesos;
       public         heap    postgres    false    5                       1259    51053    accesos_idaccesos_seq    SEQUENCE     �   CREATE SEQUENCE public.accesos_idaccesos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.accesos_idaccesos_seq;
       public          postgres    false    260    5            +           0    0    accesos_idaccesos_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.accesos_idaccesos_seq OWNED BY public.accesos.idaccesos;
          public          postgres    false    259                       1259    51067    accesos_rol    TABLE     l   CREATE TABLE public.accesos_rol (
    accesos_idaccesos integer NOT NULL,
    rol_idrol integer NOT NULL
);
    DROP TABLE public.accesos_rol;
       public         heap    postgres    false    5            �            1259    50830 	   apoderado    TABLE     P  CREATE TABLE public.apoderado (
    idapoderado integer NOT NULL,
    nombrecompleto character varying(45),
    apellidopaterno character varying(45),
    apellidomaterno character varying(45),
    celular character varying(9),
    celularrespaldo character varying(9),
    dni character varying(8),
    correo character varying(45)
);
    DROP TABLE public.apoderado;
       public         heap    postgres    false    5            �            1259    50829    apoderado_idapoderado_seq    SEQUENCE     �   CREATE SEQUENCE public.apoderado_idapoderado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.apoderado_idapoderado_seq;
       public          postgres    false    230    5            ,           0    0    apoderado_idapoderado_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.apoderado_idapoderado_seq OWNED BY public.apoderado.idapoderado;
          public          postgres    false    229                       1259    51027 
   asistencia    TABLE     1  CREATE TABLE public.asistencia (
    idasistencia integer NOT NULL,
    fechaderegistro timestamp without time zone,
    estadoasistencia_idestadoasistencia integer NOT NULL,
    academia_periodo_id integer NOT NULL,
    estudiante_idestudiante integer NOT NULL,
    academia_curso_id integer NOT NULL
);
    DROP TABLE public.asistencia;
       public         heap    postgres    false    5                       1259    51026    asistencia_idasistencia_seq    SEQUENCE     �   CREATE SEQUENCE public.asistencia_idasistencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.asistencia_idasistencia_seq;
       public          postgres    false    258    5            -           0    0    asistencia_idasistencia_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.asistencia_idasistencia_seq OWNED BY public.asistencia.idasistencia;
          public          postgres    false    257            �            1259    50887    cargaacademica    TABLE     �   CREATE TABLE public.cargaacademica (
    idcargaacademica integer NOT NULL,
    academia_periodo_id integer NOT NULL,
    academia_trabajador_id integer NOT NULL,
    academia_curso_id integer NOT NULL,
    academia_seccion_id integer NOT NULL
);
 "   DROP TABLE public.cargaacademica;
       public         heap    postgres    false    5            �            1259    50886 #   cargaacademica_idcargaacademica_seq    SEQUENCE     �   CREATE SEQUENCE public.cargaacademica_idcargaacademica_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.cargaacademica_idcargaacademica_seq;
       public          postgres    false    242    5            .           0    0 #   cargaacademica_idcargaacademica_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.cargaacademica_idcargaacademica_seq OWNED BY public.cargaacademica.idcargaacademica;
          public          postgres    false    241                        1259    51020    estadoasistencia    TABLE     t   CREATE TABLE public.estadoasistencia (
    idestadoasistencia integer NOT NULL,
    nombre character varying(30)
);
 $   DROP TABLE public.estadoasistencia;
       public         heap    postgres    false    5            �            1259    51019 '   estadoasistencia_idestadoasistencia_seq    SEQUENCE     �   CREATE SEQUENCE public.estadoasistencia_idestadoasistencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.estadoasistencia_idestadoasistencia_seq;
       public          postgres    false    5    256            /           0    0 '   estadoasistencia_idestadoasistencia_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.estadoasistencia_idestadoasistencia_seq OWNED BY public.estadoasistencia.idestadoasistencia;
          public          postgres    false    255            �            1259    50818 
   estudiante    TABLE     E  CREATE TABLE public.estudiante (
    idestudiante integer NOT NULL,
    correo character varying(45),
    celular character varying(9),
    fechanacimiento date,
    nombres character varying(45),
    apellidomaterno character varying(45),
    apellidopaterno character varying(45),
    persona_idpersona integer NOT NULL
);
    DROP TABLE public.estudiante;
       public         heap    postgres    false    5                       1259    51194    estudiante_audit_log    TABLE        CREATE TABLE public.estudiante_audit_log (
    id integer NOT NULL,
    table_name character varying(100),
    operation_type character varying(20),
    record_id integer,
    old_values jsonb,
    new_values jsonb,
    changed_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
 (   DROP TABLE public.estudiante_audit_log;
       public         heap    postgres    false    5                       1259    51193    estudiante_audit_log_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_audit_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.estudiante_audit_log_id_seq;
       public          postgres    false    5    268            0           0    0    estudiante_audit_log_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.estudiante_audit_log_id_seq OWNED BY public.estudiante_audit_log.id;
          public          postgres    false    267            �            1259    50817    estudiante_idestudiante_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_idestudiante_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.estudiante_idestudiante_seq;
       public          postgres    false    5    228            1           0    0    estudiante_idestudiante_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.estudiante_idestudiante_seq OWNED BY public.estudiante.idestudiante;
          public          postgres    false    227            �            1259    50938 	   matricula    TABLE     �  CREATE TABLE public.matricula (
    idmatricula integer NOT NULL,
    ieque_estudio character varying(100),
    a_que_escuela_postula character varying(100) NOT NULL,
    ante_pato_psico character varying(100) NOT NULL,
    ante_poli_judi character varying(100) NOT NULL,
    declaracion_jurada character(1) NOT NULL,
    direccion character varying(100) NOT NULL,
    familiar_militar_policial character varying(45) NOT NULL,
    fecha_incorporacion date NOT NULL,
    lugar_natural character varying(100) NOT NULL,
    natacion character(1) NOT NULL,
    otros character varying(45) NOT NULL,
    peso numeric(5,2) NOT NULL,
    talla numeric(5,2) NOT NULL,
    estudiante_idestudiante integer NOT NULL,
    apoderado_idapoderado integer NOT NULL,
    academia_medio_interes_id integer NOT NULL,
    planacademico_idplanacademico integer NOT NULL,
    academia_institucion_id integer NOT NULL,
    academia_grupo_id integer NOT NULL
);
    DROP TABLE public.matricula;
       public         heap    postgres    false    5            �            1259    50937    matricula_idmatricula_seq    SEQUENCE     �   CREATE SEQUENCE public.matricula_idmatricula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.matricula_idmatricula_seq;
       public          postgres    false    248    5            2           0    0    matricula_idmatricula_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.matricula_idmatricula_seq OWNED BY public.matricula.idmatricula;
          public          postgres    false    247            	           1259    51101    matricula_particionado    TABLE     5  CREATE TABLE public.matricula_particionado (
    idmatricula integer,
    ieque_estudio character varying(100),
    aque_escuala_postula character varying(100),
    ante_patp_psico character varying(100),
    ante_polo_judi character varying(100),
    declaracion_jurada character(1),
    direccion character varying(100),
    familiar_militar_policial character varying(2),
    fecha_incorporacion date,
    lugar_natural character varying(100),
    natacion character(1),
    otros character varying(100),
    peso numeric(20,0),
    talla numeric(20,0),
    estudiante_idestudiante integer,
    apoderado_idapoderado integer,
    academia_medio_interes_id integer,
    planadacemico_idplanacademico integer,
    academia_istitucion_id integer,
    academia_grupo_id integer
)
PARTITION BY RANGE (fecha_incorporacion);
 *   DROP TABLE public.matricula_particionado;
       public            postgres    false    5            �            1259    50868    nivel    TABLE     ^   CREATE TABLE public.nivel (
    idnivel integer NOT NULL,
    nombre character varying(45)
);
    DROP TABLE public.nivel;
       public         heap    postgres    false    5            �            1259    50867    nivel_idnivel_seq    SEQUENCE     �   CREATE SEQUENCE public.nivel_idnivel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.nivel_idnivel_seq;
       public          postgres    false    238    5            3           0    0    nivel_idnivel_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.nivel_idnivel_seq OWNED BY public.nivel.idnivel;
          public          postgres    false    237            
           1259    51104    notas_praticion    TABLE     �   CREATE TABLE public.notas_praticion (
    id integer,
    nota numeric(20,0),
    competencias_id character varying(45)
)
PARTITION BY RANGE (nota);
 #   DROP TABLE public.notas_praticion;
       public            postgres    false    5            �            1259    50811    persona    TABLE     �   CREATE TABLE public.persona (
    idpersona integer NOT NULL,
    dni character varying(8),
    contrasena character varying(255),
    estado character varying(20)
);
    DROP TABLE public.persona;
       public         heap    postgres    false    5            �            1259    50810    persona_idpersona_seq    SEQUENCE     �   CREATE SEQUENCE public.persona_idpersona_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.persona_idpersona_seq;
       public          postgres    false    5    226            4           0    0    persona_idpersona_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.persona_idpersona_seq OWNED BY public.persona.idpersona;
          public          postgres    false    225                       1259    51082    persona_rol    TABLE     l   CREATE TABLE public.persona_rol (
    rol_idrol integer NOT NULL,
    persona_idpersona integer NOT NULL
);
    DROP TABLE public.persona_rol;
       public         heap    postgres    false    5            �            1259    50914    planacademico    TABLE     �   CREATE TABLE public.planacademico (
    idplanacademico integer NOT NULL,
    nombre character varying(45),
    cargaacademica_idcargaacademica integer NOT NULL
);
 !   DROP TABLE public.planacademico;
       public         heap    postgres    false    5            �            1259    50913 !   planacademico_idplanacademico_seq    SEQUENCE     �   CREATE SEQUENCE public.planacademico_idplanacademico_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.planacademico_idplanacademico_seq;
       public          postgres    false    244    5            5           0    0 !   planacademico_idplanacademico_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.planacademico_idplanacademico_seq OWNED BY public.planacademico.idplanacademico;
          public          postgres    false    243                       1259    51061    rol    TABLE     �   CREATE TABLE public.rol (
    idrol integer NOT NULL,
    nombre character varying(60),
    descripcion character varying(100)
);
    DROP TABLE public.rol;
       public         heap    postgres    false    5                       1259    51060    rol_idrol_seq    SEQUENCE     �   CREATE SEQUENCE public.rol_idrol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.rol_idrol_seq;
       public          postgres    false    5    262            6           0    0    rol_idrol_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.rol_idrol_seq OWNED BY public.rol.idrol;
          public          postgres    false    261            �           2604    50767    academia_competencia id    DEFAULT     �   ALTER TABLE ONLY public.academia_competencia ALTER COLUMN id SET DEFAULT nextval('public.academia_competencia_id_seq'::regclass);
 F   ALTER TABLE public.academia_competencia ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    50800    academia_comunicados id    DEFAULT     �   ALTER TABLE ONLY public.academia_comunicados ALTER COLUMN id SET DEFAULT nextval('public.academia_comunicados_id_seq'::regclass);
 F   ALTER TABLE public.academia_comunicados ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    50760    academia_curso id    DEFAULT     v   ALTER TABLE ONLY public.academia_curso ALTER COLUMN id SET DEFAULT nextval('public.academia_curso_id_seq'::regclass);
 @   ALTER TABLE public.academia_curso ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    50929    academia_grupo id    DEFAULT     v   ALTER TABLE ONLY public.academia_grupo ALTER COLUMN id SET DEFAULT nextval('public.academia_grupo_id_seq'::regclass);
 @   ALTER TABLE public.academia_grupo ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    246    245    246            �           2604    50980    academia_insidencias id    DEFAULT     �   ALTER TABLE ONLY public.academia_insidencias ALTER COLUMN id SET DEFAULT nextval('public.academia_insidencias_id_seq'::regclass);
 F   ALTER TABLE public.academia_insidencias ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    250    249    250            �           2604    50786    academia_institucion id    DEFAULT     �   ALTER TABLE ONLY public.academia_institucion ALTER COLUMN id SET DEFAULT nextval('public.academia_institucion_id_seq'::regclass);
 F   ALTER TABLE public.academia_institucion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    50997    academia_logros id    DEFAULT     x   ALTER TABLE ONLY public.academia_logros ALTER COLUMN id SET DEFAULT nextval('public.academia_logros_id_seq'::regclass);
 A   ALTER TABLE public.academia_logros ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    252    251    252            �           2604    50840    academia_medio_interes id    DEFAULT     �   ALTER TABLE ONLY public.academia_medio_interes ALTER COLUMN id SET DEFAULT nextval('public.academia_medio_interes_id_seq'::regclass);
 H   ALTER TABLE public.academia_medio_interes ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    232    231    232            �           2604    51011    academia_notas id    DEFAULT     v   ALTER TABLE ONLY public.academia_notas ALTER COLUMN id SET DEFAULT nextval('public.academia_notas_id_seq'::regclass);
 @   ALTER TABLE public.academia_notas ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    254    253    254            �           2604    50847    academia_periodo id    DEFAULT     z   ALTER TABLE ONLY public.academia_periodo ALTER COLUMN id SET DEFAULT nextval('public.academia_periodo_id_seq'::regclass);
 B   ALTER TABLE public.academia_periodo ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    234    233    234            �           2604    50878    academia_seccion id    DEFAULT     z   ALTER TABLE ONLY public.academia_seccion ALTER COLUMN id SET DEFAULT nextval('public.academia_seccion_id_seq'::regclass);
 B   ALTER TABLE public.academia_seccion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    240    239    240            �           2604    50779    academia_sedeinstitucion id    DEFAULT     �   ALTER TABLE ONLY public.academia_sedeinstitucion ALTER COLUMN id SET DEFAULT nextval('public.academia_sedeinstitucion_id_seq'::regclass);
 J   ALTER TABLE public.academia_sedeinstitucion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    50854    academia_trabajador id    DEFAULT     �   ALTER TABLE ONLY public.academia_trabajador ALTER COLUMN id SET DEFAULT nextval('public.academia_trabajador_id_seq'::regclass);
 E   ALTER TABLE public.academia_trabajador ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    236    236            �           2604    51057    accesos idaccesos    DEFAULT     v   ALTER TABLE ONLY public.accesos ALTER COLUMN idaccesos SET DEFAULT nextval('public.accesos_idaccesos_seq'::regclass);
 @   ALTER TABLE public.accesos ALTER COLUMN idaccesos DROP DEFAULT;
       public          postgres    false    260    259    260            �           2604    50833    apoderado idapoderado    DEFAULT     ~   ALTER TABLE ONLY public.apoderado ALTER COLUMN idapoderado SET DEFAULT nextval('public.apoderado_idapoderado_seq'::regclass);
 D   ALTER TABLE public.apoderado ALTER COLUMN idapoderado DROP DEFAULT;
       public          postgres    false    229    230    230            �           2604    51030    asistencia idasistencia    DEFAULT     �   ALTER TABLE ONLY public.asistencia ALTER COLUMN idasistencia SET DEFAULT nextval('public.asistencia_idasistencia_seq'::regclass);
 F   ALTER TABLE public.asistencia ALTER COLUMN idasistencia DROP DEFAULT;
       public          postgres    false    258    257    258            �           2604    50890    cargaacademica idcargaacademica    DEFAULT     �   ALTER TABLE ONLY public.cargaacademica ALTER COLUMN idcargaacademica SET DEFAULT nextval('public.cargaacademica_idcargaacademica_seq'::regclass);
 N   ALTER TABLE public.cargaacademica ALTER COLUMN idcargaacademica DROP DEFAULT;
       public          postgres    false    241    242    242            �           2604    51023 #   estadoasistencia idestadoasistencia    DEFAULT     �   ALTER TABLE ONLY public.estadoasistencia ALTER COLUMN idestadoasistencia SET DEFAULT nextval('public.estadoasistencia_idestadoasistencia_seq'::regclass);
 R   ALTER TABLE public.estadoasistencia ALTER COLUMN idestadoasistencia DROP DEFAULT;
       public          postgres    false    255    256    256            �           2604    50821    estudiante idestudiante    DEFAULT     �   ALTER TABLE ONLY public.estudiante ALTER COLUMN idestudiante SET DEFAULT nextval('public.estudiante_idestudiante_seq'::regclass);
 F   ALTER TABLE public.estudiante ALTER COLUMN idestudiante DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    51197    estudiante_audit_log id    DEFAULT     �   ALTER TABLE ONLY public.estudiante_audit_log ALTER COLUMN id SET DEFAULT nextval('public.estudiante_audit_log_id_seq'::regclass);
 F   ALTER TABLE public.estudiante_audit_log ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    268    267    268            �           2604    50941    matricula idmatricula    DEFAULT     ~   ALTER TABLE ONLY public.matricula ALTER COLUMN idmatricula SET DEFAULT nextval('public.matricula_idmatricula_seq'::regclass);
 D   ALTER TABLE public.matricula ALTER COLUMN idmatricula DROP DEFAULT;
       public          postgres    false    248    247    248            �           2604    50871    nivel idnivel    DEFAULT     n   ALTER TABLE ONLY public.nivel ALTER COLUMN idnivel SET DEFAULT nextval('public.nivel_idnivel_seq'::regclass);
 <   ALTER TABLE public.nivel ALTER COLUMN idnivel DROP DEFAULT;
       public          postgres    false    237    238    238            �           2604    50814    persona idpersona    DEFAULT     v   ALTER TABLE ONLY public.persona ALTER COLUMN idpersona SET DEFAULT nextval('public.persona_idpersona_seq'::regclass);
 @   ALTER TABLE public.persona ALTER COLUMN idpersona DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    50917    planacademico idplanacademico    DEFAULT     �   ALTER TABLE ONLY public.planacademico ALTER COLUMN idplanacademico SET DEFAULT nextval('public.planacademico_idplanacademico_seq'::regclass);
 L   ALTER TABLE public.planacademico ALTER COLUMN idplanacademico DROP DEFAULT;
       public          postgres    false    243    244    244            �           2604    51064 	   rol idrol    DEFAULT     f   ALTER TABLE ONLY public.rol ALTER COLUMN idrol SET DEFAULT nextval('public.rol_idrol_seq'::regclass);
 8   ALTER TABLE public.rol ALTER COLUMN idrol DROP DEFAULT;
       public          postgres    false    261    262    262            �          0    50764    academia_competencia 
   TABLE DATA           ]   COPY public.academia_competencia (id, nombre_competencia, subpromedio, curso_id) FROM stdin;
    public          postgres    false    218   �<      �          0    50797    academia_comunicados 
   TABLE DATA           ^   COPY public.academia_comunicados (id, asunto, descripcion, fecha, institucion_id) FROM stdin;
    public          postgres    false    224   �<      �          0    50757    academia_curso 
   TABLE DATA           E   COPY public.academia_curso (id, nombre, promediogeneral) FROM stdin;
    public          postgres    false    216   �=                0    50926    academia_grupo 
   TABLE DATA           G   COPY public.academia_grupo (id, nombre, cupos, seccion_id) FROM stdin;
    public          postgres    false    246   �>                0    50977    academia_insidencias 
   TABLE DATA           r   COPY public.academia_insidencias (id, castigo, descripcion, fecha, curso_id, estudiante_idestudiante) FROM stdin;
    public          postgres    false    250   %?      �          0    50783    academia_institucion 
   TABLE DATA              COPY public.academia_institucion (id, lugar, imagen_logo, mision, nombre_institucion, vicion, sede_institucion_id) FROM stdin;
    public          postgres    false    222   �?                0    50994    academia_logros 
   TABLE DATA           a   COPY public.academia_logros (id, descripcion, fecha, imagen, titulo, institucion_id) FROM stdin;
    public          postgres    false    252   �@      �          0    50837    academia_medio_interes 
   TABLE DATA           B   COPY public.academia_medio_interes (id, nombre_medio) FROM stdin;
    public          postgres    false    232   �A      	          0    51008    academia_notas 
   TABLE DATA           B   COPY public.academia_notas (id, nota, competencia_id) FROM stdin;
    public          postgres    false    254   KB      �          0    50844    academia_periodo 
   TABLE DATA           >   COPY public.academia_periodo (id, nombre_periodo) FROM stdin;
    public          postgres    false    234   �B      �          0    50875    academia_seccion 
   TABLE DATA           E   COPY public.academia_seccion (id, nombre, nivel_idnivel) FROM stdin;
    public          postgres    false    240   �B      �          0    50776    academia_sedeinstitucion 
   TABLE DATA           C   COPY public.academia_sedeinstitucion (id, nombre_sede) FROM stdin;
    public          postgres    false    220   OC      �          0    50851    academia_trabajador 
   TABLE DATA           �   COPY public.academia_trabajador (id, apellido_materno, apellido_paterno, celular, correo, nombre_completo, academia_institucion_id, persona_idpersona) FROM stdin;
    public          postgres    false    236   �C                0    51054    accesos 
   TABLE DATA           @   COPY public.accesos (idaccesos, nombre, url, icono) FROM stdin;
    public          postgres    false    260   LD                0    51067    accesos_rol 
   TABLE DATA           C   COPY public.accesos_rol (accesos_idaccesos, rol_idrol) FROM stdin;
    public          postgres    false    263   -E      �          0    50830 	   apoderado 
   TABLE DATA           �   COPY public.apoderado (idapoderado, nombrecompleto, apellidopaterno, apellidomaterno, celular, celularrespaldo, dni, correo) FROM stdin;
    public          postgres    false    230   jE                0    51027 
   asistencia 
   TABLE DATA           �   COPY public.asistencia (idasistencia, fechaderegistro, estadoasistencia_idestadoasistencia, academia_periodo_id, estudiante_idestudiante, academia_curso_id) FROM stdin;
    public          postgres    false    258   iF      �          0    50887    cargaacademica 
   TABLE DATA           �   COPY public.cargaacademica (idcargaacademica, academia_periodo_id, academia_trabajador_id, academia_curso_id, academia_seccion_id) FROM stdin;
    public          postgres    false    242   �F                0    51020    estadoasistencia 
   TABLE DATA           F   COPY public.estadoasistencia (idestadoasistencia, nombre) FROM stdin;
    public          postgres    false    256   �F      �          0    50818 
   estudiante 
   TABLE DATA           �   COPY public.estudiante (idestudiante, correo, celular, fechanacimiento, nombres, apellidomaterno, apellidopaterno, persona_idpersona) FROM stdin;
    public          postgres    false    228   :G                0    51194    estudiante_audit_log 
   TABLE DATA           }   COPY public.estudiante_audit_log (id, table_name, operation_type, record_id, old_values, new_values, changed_at) FROM stdin;
    public          postgres    false    268   H                0    50938 	   matricula 
   TABLE DATA           �  COPY public.matricula (idmatricula, ieque_estudio, a_que_escuela_postula, ante_pato_psico, ante_poli_judi, declaracion_jurada, direccion, familiar_militar_policial, fecha_incorporacion, lugar_natural, natacion, otros, peso, talla, estudiante_idestudiante, apoderado_idapoderado, academia_medio_interes_id, planacademico_idplanacademico, academia_institucion_id, academia_grupo_id) FROM stdin;
    public          postgres    false    248   5H      �          0    50868    nivel 
   TABLE DATA           0   COPY public.nivel (idnivel, nombre) FROM stdin;
    public          postgres    false    238   +I      �          0    50811    persona 
   TABLE DATA           E   COPY public.persona (idpersona, dni, contrasena, estado) FROM stdin;
    public          postgres    false    226   SI                0    51082    persona_rol 
   TABLE DATA           C   COPY public.persona_rol (rol_idrol, persona_idpersona) FROM stdin;
    public          postgres    false    264   �I      �          0    50914    planacademico 
   TABLE DATA           a   COPY public.planacademico (idplanacademico, nombre, cargaacademica_idcargaacademica) FROM stdin;
    public          postgres    false    244   J                0    51061    rol 
   TABLE DATA           9   COPY public.rol (idrol, nombre, descripcion) FROM stdin;
    public          postgres    false    262   �J      7           0    0    academia_competencia_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.academia_competencia_id_seq', 3, true);
          public          postgres    false    217            8           0    0    academia_comunicados_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.academia_comunicados_id_seq', 6, true);
          public          postgres    false    223            9           0    0    academia_curso_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.academia_curso_id_seq', 11, true);
          public          postgres    false    215            :           0    0    academia_grupo_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.academia_grupo_id_seq', 70, true);
          public          postgres    false    245            ;           0    0    academia_insidencias_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.academia_insidencias_id_seq', 8, true);
          public          postgres    false    249            <           0    0    academia_institucion_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.academia_institucion_id_seq', 2, true);
          public          postgres    false    221            =           0    0    academia_logros_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.academia_logros_id_seq', 5, true);
          public          postgres    false    251            >           0    0    academia_medio_interes_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.academia_medio_interes_id_seq', 10, true);
          public          postgres    false    231            ?           0    0    academia_notas_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.academia_notas_id_seq', 3, true);
          public          postgres    false    253            @           0    0    academia_periodo_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.academia_periodo_id_seq', 10, true);
          public          postgres    false    233            A           0    0    academia_seccion_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.academia_seccion_id_seq', 40, true);
          public          postgres    false    239            B           0    0    academia_sedeinstitucion_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.academia_sedeinstitucion_id_seq', 2, true);
          public          postgres    false    219            C           0    0    academia_trabajador_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.academia_trabajador_id_seq', 5, true);
          public          postgres    false    235            D           0    0    accesos_idaccesos_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.accesos_idaccesos_seq', 10, true);
          public          postgres    false    259            E           0    0    apoderado_idapoderado_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.apoderado_idapoderado_seq', 5, true);
          public          postgres    false    229            F           0    0    asistencia_idasistencia_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.asistencia_idasistencia_seq', 3, true);
          public          postgres    false    257            G           0    0 #   cargaacademica_idcargaacademica_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.cargaacademica_idcargaacademica_seq', 11, true);
          public          postgres    false    241            H           0    0 '   estadoasistencia_idestadoasistencia_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.estadoasistencia_idestadoasistencia_seq', 6, true);
          public          postgres    false    255            I           0    0    estudiante_audit_log_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.estudiante_audit_log_id_seq', 1, false);
          public          postgres    false    267            J           0    0    estudiante_idestudiante_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.estudiante_idestudiante_seq', 5, true);
          public          postgres    false    227            K           0    0    matricula_idmatricula_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.matricula_idmatricula_seq', 19, true);
          public          postgres    false    247            L           0    0    nivel_idnivel_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.nivel_idnivel_seq', 1, true);
          public          postgres    false    237            M           0    0    persona_idpersona_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.persona_idpersona_seq', 10, true);
          public          postgres    false    225            N           0    0 !   planacademico_idplanacademico_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.planacademico_idplanacademico_seq', 11, true);
          public          postgres    false    243            O           0    0    rol_idrol_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.rol_idrol_seq', 5, true);
          public          postgres    false    261            �           2606    50769 .   academia_competencia academia_competencia_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.academia_competencia
    ADD CONSTRAINT academia_competencia_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.academia_competencia DROP CONSTRAINT academia_competencia_pkey;
       public            postgres    false    218                       2606    50804 .   academia_comunicados academia_comunicados_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.academia_comunicados
    ADD CONSTRAINT academia_comunicados_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.academia_comunicados DROP CONSTRAINT academia_comunicados_pkey;
       public            postgres    false    224            �           2606    50762 "   academia_curso academia_curso_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.academia_curso
    ADD CONSTRAINT academia_curso_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.academia_curso DROP CONSTRAINT academia_curso_pkey;
       public            postgres    false    216                       2606    50931 "   academia_grupo academia_grupo_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.academia_grupo
    ADD CONSTRAINT academia_grupo_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.academia_grupo DROP CONSTRAINT academia_grupo_pkey;
       public            postgres    false    246                       2606    50982 .   academia_insidencias academia_insidencias_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.academia_insidencias
    ADD CONSTRAINT academia_insidencias_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.academia_insidencias DROP CONSTRAINT academia_insidencias_pkey;
       public            postgres    false    250                       2606    50790 .   academia_institucion academia_institucion_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.academia_institucion
    ADD CONSTRAINT academia_institucion_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.academia_institucion DROP CONSTRAINT academia_institucion_pkey;
       public            postgres    false    222                        2606    51001 $   academia_logros academia_logros_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.academia_logros
    ADD CONSTRAINT academia_logros_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.academia_logros DROP CONSTRAINT academia_logros_pkey;
       public            postgres    false    252                       2606    50842 2   academia_medio_interes academia_medio_interes_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.academia_medio_interes
    ADD CONSTRAINT academia_medio_interes_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.academia_medio_interes DROP CONSTRAINT academia_medio_interes_pkey;
       public            postgres    false    232            "           2606    51013 "   academia_notas academia_notas_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.academia_notas
    ADD CONSTRAINT academia_notas_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.academia_notas DROP CONSTRAINT academia_notas_pkey;
       public            postgres    false    254                       2606    50849 &   academia_periodo academia_periodo_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.academia_periodo
    ADD CONSTRAINT academia_periodo_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.academia_periodo DROP CONSTRAINT academia_periodo_pkey;
       public            postgres    false    234                       2606    50880 &   academia_seccion academia_seccion_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.academia_seccion
    ADD CONSTRAINT academia_seccion_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.academia_seccion DROP CONSTRAINT academia_seccion_pkey;
       public            postgres    false    240                        2606    50781 6   academia_sedeinstitucion academia_sedeinstitucion_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.academia_sedeinstitucion
    ADD CONSTRAINT academia_sedeinstitucion_pkey PRIMARY KEY (id);
 `   ALTER TABLE ONLY public.academia_sedeinstitucion DROP CONSTRAINT academia_sedeinstitucion_pkey;
       public            postgres    false    220                       2606    50856 ,   academia_trabajador academia_trabajador_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.academia_trabajador
    ADD CONSTRAINT academia_trabajador_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.academia_trabajador DROP CONSTRAINT academia_trabajador_pkey;
       public            postgres    false    236            (           2606    51059    accesos accesos_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.accesos
    ADD CONSTRAINT accesos_pkey PRIMARY KEY (idaccesos);
 >   ALTER TABLE ONLY public.accesos DROP CONSTRAINT accesos_pkey;
       public            postgres    false    260            ,           2606    51071    accesos_rol accesos_rol_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.accesos_rol
    ADD CONSTRAINT accesos_rol_pkey PRIMARY KEY (accesos_idaccesos, rol_idrol);
 F   ALTER TABLE ONLY public.accesos_rol DROP CONSTRAINT accesos_rol_pkey;
       public            postgres    false    263    263            
           2606    50835    apoderado apoderado_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.apoderado
    ADD CONSTRAINT apoderado_pkey PRIMARY KEY (idapoderado);
 B   ALTER TABLE ONLY public.apoderado DROP CONSTRAINT apoderado_pkey;
       public            postgres    false    230            &           2606    51032    asistencia asistencia_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.asistencia
    ADD CONSTRAINT asistencia_pkey PRIMARY KEY (idasistencia);
 D   ALTER TABLE ONLY public.asistencia DROP CONSTRAINT asistencia_pkey;
       public            postgres    false    258                       2606    50892 "   cargaacademica cargaacademica_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.cargaacademica
    ADD CONSTRAINT cargaacademica_pkey PRIMARY KEY (idcargaacademica);
 L   ALTER TABLE ONLY public.cargaacademica DROP CONSTRAINT cargaacademica_pkey;
       public            postgres    false    242            $           2606    51025 &   estadoasistencia estadoasistencia_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.estadoasistencia
    ADD CONSTRAINT estadoasistencia_pkey PRIMARY KEY (idestadoasistencia);
 P   ALTER TABLE ONLY public.estadoasistencia DROP CONSTRAINT estadoasistencia_pkey;
       public            postgres    false    256            0           2606    51202 .   estudiante_audit_log estudiante_audit_log_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.estudiante_audit_log
    ADD CONSTRAINT estudiante_audit_log_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.estudiante_audit_log DROP CONSTRAINT estudiante_audit_log_pkey;
       public            postgres    false    268                       2606    50823    estudiante estudiante_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (idestudiante);
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public            postgres    false    228                       2606    50945    matricula matricula_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_pkey PRIMARY KEY (idmatricula);
 B   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_pkey;
       public            postgres    false    248                       2606    50873    nivel nivel_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.nivel
    ADD CONSTRAINT nivel_pkey PRIMARY KEY (idnivel);
 :   ALTER TABLE ONLY public.nivel DROP CONSTRAINT nivel_pkey;
       public            postgres    false    238                       2606    50816    persona persona_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (idpersona);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    226            .           2606    51086    persona_rol persona_rol_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.persona_rol
    ADD CONSTRAINT persona_rol_pkey PRIMARY KEY (rol_idrol, persona_idpersona);
 F   ALTER TABLE ONLY public.persona_rol DROP CONSTRAINT persona_rol_pkey;
       public            postgres    false    264    264                       2606    50919     planacademico planacademico_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.planacademico
    ADD CONSTRAINT planacademico_pkey PRIMARY KEY (idplanacademico);
 J   ALTER TABLE ONLY public.planacademico DROP CONSTRAINT planacademico_pkey;
       public            postgres    false    244            *           2606    51066    rol rol_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public            postgres    false    262            Q           2620    51204    estudiante estudiante_audit    TRIGGER     �   CREATE TRIGGER estudiante_audit AFTER DELETE OR UPDATE ON public.estudiante FOR EACH ROW EXECUTE FUNCTION public.audit_changes_estudiante();
 4   DROP TRIGGER estudiante_audit ON public.estudiante;
       public          postgres    false    286    228            P           2620    51209 -   academia_curso update_curso_timestamp_trigger    TRIGGER     �   CREATE TRIGGER update_curso_timestamp_trigger BEFORE UPDATE ON public.academia_curso FOR EACH ROW EXECUTE FUNCTION public.update_curso_timestamp();
 F   DROP TRIGGER update_curso_timestamp_trigger ON public.academia_curso;
       public          postgres    false    288    216            R           2620    51206 ,   estudiante validate_promedio_general_trigger    TRIGGER     �   CREATE TRIGGER validate_promedio_general_trigger BEFORE INSERT OR UPDATE ON public.estudiante FOR EACH ROW EXECUTE FUNCTION public.validate_promedio_general();
 E   DROP TRIGGER validate_promedio_general_trigger ON public.estudiante;
       public          postgres    false    228    287            1           2606    50770 7   academia_competencia academia_competencia_curso_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_competencia
    ADD CONSTRAINT academia_competencia_curso_id_fkey FOREIGN KEY (curso_id) REFERENCES public.academia_curso(id);
 a   ALTER TABLE ONLY public.academia_competencia DROP CONSTRAINT academia_competencia_curso_id_fkey;
       public          postgres    false    216    218    4860            3           2606    50805 =   academia_comunicados academia_comunicados_institucion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_comunicados
    ADD CONSTRAINT academia_comunicados_institucion_id_fkey FOREIGN KEY (institucion_id) REFERENCES public.academia_institucion(id);
 g   ALTER TABLE ONLY public.academia_comunicados DROP CONSTRAINT academia_comunicados_institucion_id_fkey;
       public          postgres    false    224    222    4866            =           2606    50932 -   academia_grupo academia_grupo_seccion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_grupo
    ADD CONSTRAINT academia_grupo_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES public.academia_seccion(id);
 W   ALTER TABLE ONLY public.academia_grupo DROP CONSTRAINT academia_grupo_seccion_id_fkey;
       public          postgres    false    246    240    4884            D           2606    50983 7   academia_insidencias academia_insidencias_curso_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_insidencias
    ADD CONSTRAINT academia_insidencias_curso_id_fkey FOREIGN KEY (curso_id) REFERENCES public.academia_curso(id);
 a   ALTER TABLE ONLY public.academia_insidencias DROP CONSTRAINT academia_insidencias_curso_id_fkey;
       public          postgres    false    216    4860    250            E           2606    50988 F   academia_insidencias academia_insidencias_estudiante_idestudiante_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_insidencias
    ADD CONSTRAINT academia_insidencias_estudiante_idestudiante_fkey FOREIGN KEY (estudiante_idestudiante) REFERENCES public.estudiante(idestudiante);
 p   ALTER TABLE ONLY public.academia_insidencias DROP CONSTRAINT academia_insidencias_estudiante_idestudiante_fkey;
       public          postgres    false    228    250    4872            2           2606    50791 B   academia_institucion academia_institucion_sede_institucion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_institucion
    ADD CONSTRAINT academia_institucion_sede_institucion_id_fkey FOREIGN KEY (sede_institucion_id) REFERENCES public.academia_sedeinstitucion(id);
 l   ALTER TABLE ONLY public.academia_institucion DROP CONSTRAINT academia_institucion_sede_institucion_id_fkey;
       public          postgres    false    220    4864    222            F           2606    51002 3   academia_logros academia_logros_institucion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_logros
    ADD CONSTRAINT academia_logros_institucion_id_fkey FOREIGN KEY (institucion_id) REFERENCES public.academia_institucion(id);
 ]   ALTER TABLE ONLY public.academia_logros DROP CONSTRAINT academia_logros_institucion_id_fkey;
       public          postgres    false    252    4866    222            G           2606    51014 1   academia_notas academia_notas_competencia_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_notas
    ADD CONSTRAINT academia_notas_competencia_id_fkey FOREIGN KEY (competencia_id) REFERENCES public.academia_competencia(id);
 [   ALTER TABLE ONLY public.academia_notas DROP CONSTRAINT academia_notas_competencia_id_fkey;
       public          postgres    false    4862    254    218            7           2606    50881 4   academia_seccion academia_seccion_nivel_idnivel_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_seccion
    ADD CONSTRAINT academia_seccion_nivel_idnivel_fkey FOREIGN KEY (nivel_idnivel) REFERENCES public.nivel(idnivel);
 ^   ALTER TABLE ONLY public.academia_seccion DROP CONSTRAINT academia_seccion_nivel_idnivel_fkey;
       public          postgres    false    4882    238    240            5           2606    50857 D   academia_trabajador academia_trabajador_academia_institucion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_trabajador
    ADD CONSTRAINT academia_trabajador_academia_institucion_id_fkey FOREIGN KEY (academia_institucion_id) REFERENCES public.academia_institucion(id);
 n   ALTER TABLE ONLY public.academia_trabajador DROP CONSTRAINT academia_trabajador_academia_institucion_id_fkey;
       public          postgres    false    222    4866    236            6           2606    50862 >   academia_trabajador academia_trabajador_persona_idpersona_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.academia_trabajador
    ADD CONSTRAINT academia_trabajador_persona_idpersona_fkey FOREIGN KEY (persona_idpersona) REFERENCES public.persona(idpersona);
 h   ALTER TABLE ONLY public.academia_trabajador DROP CONSTRAINT academia_trabajador_persona_idpersona_fkey;
       public          postgres    false    4870    236    226            L           2606    51072 .   accesos_rol accesos_rol_accesos_idaccesos_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.accesos_rol
    ADD CONSTRAINT accesos_rol_accesos_idaccesos_fkey FOREIGN KEY (accesos_idaccesos) REFERENCES public.accesos(idaccesos);
 X   ALTER TABLE ONLY public.accesos_rol DROP CONSTRAINT accesos_rol_accesos_idaccesos_fkey;
       public          postgres    false    4904    263    260            M           2606    51077 &   accesos_rol accesos_rol_rol_idrol_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.accesos_rol
    ADD CONSTRAINT accesos_rol_rol_idrol_fkey FOREIGN KEY (rol_idrol) REFERENCES public.rol(idrol);
 P   ALTER TABLE ONLY public.accesos_rol DROP CONSTRAINT accesos_rol_rol_idrol_fkey;
       public          postgres    false    262    4906    263            H           2606    51048 ,   asistencia asistencia_academia_curso_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.asistencia
    ADD CONSTRAINT asistencia_academia_curso_id_fkey FOREIGN KEY (academia_curso_id) REFERENCES public.academia_curso(id);
 V   ALTER TABLE ONLY public.asistencia DROP CONSTRAINT asistencia_academia_curso_id_fkey;
       public          postgres    false    4860    258    216            I           2606    51038 .   asistencia asistencia_academia_periodo_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.asistencia
    ADD CONSTRAINT asistencia_academia_periodo_id_fkey FOREIGN KEY (academia_periodo_id) REFERENCES public.academia_periodo(id);
 X   ALTER TABLE ONLY public.asistencia DROP CONSTRAINT asistencia_academia_periodo_id_fkey;
       public          postgres    false    234    4878    258            J           2606    51033 >   asistencia asistencia_estadoasistencia_idestadoasistencia_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.asistencia
    ADD CONSTRAINT asistencia_estadoasistencia_idestadoasistencia_fkey FOREIGN KEY (estadoasistencia_idestadoasistencia) REFERENCES public.estadoasistencia(idestadoasistencia);
 h   ALTER TABLE ONLY public.asistencia DROP CONSTRAINT asistencia_estadoasistencia_idestadoasistencia_fkey;
       public          postgres    false    4900    258    256            K           2606    51043 2   asistencia asistencia_estudiante_idestudiante_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.asistencia
    ADD CONSTRAINT asistencia_estudiante_idestudiante_fkey FOREIGN KEY (estudiante_idestudiante) REFERENCES public.estudiante(idestudiante);
 \   ALTER TABLE ONLY public.asistencia DROP CONSTRAINT asistencia_estudiante_idestudiante_fkey;
       public          postgres    false    4872    258    228            8           2606    50903 4   cargaacademica cargaacademica_academia_curso_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cargaacademica
    ADD CONSTRAINT cargaacademica_academia_curso_id_fkey FOREIGN KEY (academia_curso_id) REFERENCES public.academia_curso(id);
 ^   ALTER TABLE ONLY public.cargaacademica DROP CONSTRAINT cargaacademica_academia_curso_id_fkey;
       public          postgres    false    242    216    4860            9           2606    50893 6   cargaacademica cargaacademica_academia_periodo_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cargaacademica
    ADD CONSTRAINT cargaacademica_academia_periodo_id_fkey FOREIGN KEY (academia_periodo_id) REFERENCES public.academia_periodo(id);
 `   ALTER TABLE ONLY public.cargaacademica DROP CONSTRAINT cargaacademica_academia_periodo_id_fkey;
       public          postgres    false    4878    242    234            :           2606    50908 6   cargaacademica cargaacademica_academia_seccion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cargaacademica
    ADD CONSTRAINT cargaacademica_academia_seccion_id_fkey FOREIGN KEY (academia_seccion_id) REFERENCES public.academia_seccion(id);
 `   ALTER TABLE ONLY public.cargaacademica DROP CONSTRAINT cargaacademica_academia_seccion_id_fkey;
       public          postgres    false    4884    240    242            ;           2606    50898 9   cargaacademica cargaacademica_academia_trabajador_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cargaacademica
    ADD CONSTRAINT cargaacademica_academia_trabajador_id_fkey FOREIGN KEY (academia_trabajador_id) REFERENCES public.academia_trabajador(id);
 c   ALTER TABLE ONLY public.cargaacademica DROP CONSTRAINT cargaacademica_academia_trabajador_id_fkey;
       public          postgres    false    242    236    4880            4           2606    50824 ,   estudiante estudiante_persona_idpersona_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_persona_idpersona_fkey FOREIGN KEY (persona_idpersona) REFERENCES public.persona(idpersona);
 V   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_persona_idpersona_fkey;
       public          postgres    false    228    4870    226            >           2606    50971 *   matricula matricula_academia_grupo_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_academia_grupo_id_fkey FOREIGN KEY (academia_grupo_id) REFERENCES public.academia_grupo(id);
 T   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_academia_grupo_id_fkey;
       public          postgres    false    248    4890    246            ?           2606    50966 0   matricula matricula_academia_institucion_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_academia_institucion_id_fkey FOREIGN KEY (academia_institucion_id) REFERENCES public.academia_institucion(id);
 Z   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_academia_institucion_id_fkey;
       public          postgres    false    222    4866    248            @           2606    50956 2   matricula matricula_academia_medio_interes_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_academia_medio_interes_id_fkey FOREIGN KEY (academia_medio_interes_id) REFERENCES public.academia_medio_interes(id);
 \   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_academia_medio_interes_id_fkey;
       public          postgres    false    232    248    4876            A           2606    50951 .   matricula matricula_apoderado_idapoderado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_apoderado_idapoderado_fkey FOREIGN KEY (apoderado_idapoderado) REFERENCES public.apoderado(idapoderado);
 X   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_apoderado_idapoderado_fkey;
       public          postgres    false    230    4874    248            B           2606    50946 0   matricula matricula_estudiante_idestudiante_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_estudiante_idestudiante_fkey FOREIGN KEY (estudiante_idestudiante) REFERENCES public.estudiante(idestudiante);
 Z   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_estudiante_idestudiante_fkey;
       public          postgres    false    248    4872    228            C           2606    50961 6   matricula matricula_planacademico_idplanacademico_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_planacademico_idplanacademico_fkey FOREIGN KEY (planacademico_idplanacademico) REFERENCES public.planacademico(idplanacademico);
 `   ALTER TABLE ONLY public.matricula DROP CONSTRAINT matricula_planacademico_idplanacademico_fkey;
       public          postgres    false    244    248    4888            N           2606    51092 .   persona_rol persona_rol_persona_idpersona_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.persona_rol
    ADD CONSTRAINT persona_rol_persona_idpersona_fkey FOREIGN KEY (persona_idpersona) REFERENCES public.persona(idpersona);
 X   ALTER TABLE ONLY public.persona_rol DROP CONSTRAINT persona_rol_persona_idpersona_fkey;
       public          postgres    false    226    4870    264            O           2606    51087 &   persona_rol persona_rol_rol_idrol_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.persona_rol
    ADD CONSTRAINT persona_rol_rol_idrol_fkey FOREIGN KEY (rol_idrol) REFERENCES public.rol(idrol);
 P   ALTER TABLE ONLY public.persona_rol DROP CONSTRAINT persona_rol_rol_idrol_fkey;
       public          postgres    false    264    262    4906            <           2606    50920 @   planacademico planacademico_cargaacademica_idcargaacademica_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.planacademico
    ADD CONSTRAINT planacademico_cargaacademica_idcargaacademica_fkey FOREIGN KEY (cargaacademica_idcargaacademica) REFERENCES public.cargaacademica(idcargaacademica);
 j   ALTER TABLE ONLY public.planacademico DROP CONSTRAINT planacademico_cargaacademica_idcargaacademica_fkey;
       public          postgres    false    244    242    4886            �   N   x�3�t��-H-I�K�LTp;��839���B�ԀӐ�E6 (��sxs:X������1����+���Җ ic�=... ��N      �   �   x�U�Mj1���)t��q��%�R���Ql�<� ��"��Yz�ʓ�ҍ~��{O;wƈ��P�Dp&E)�+,����e ����(C�K�����v��&�M\���i)p�(��P�9qq����	��dR�
�ub]O�����?�޽�p啞(���jFe�<b�L�fL-�	��@��,}�~x��Cg/\޼��O�]�      �   �   x�E�MNC!�ǗU�}�m�I��:t���&<n<�Kp]@G.��	������`r$���'pͽ�w�� �6P�3��Ѡ��F����{��N�:��1$���d=��8��w[N������l�C��EJ�z�D��@uM4VB�Y\Ѕ\���c�c����Y})Y���V��'�K6W쫒�K|G�0�:��dW�[�ծ�>έz1K�d�g2���ɽ���M^)UV����L�ԋ`����g�         V   x�-η�0 ��? p(�f`��G_^w��罯f0�/��G'2�g�C/.d�(�dD+��ō�(�N8+���l|�"�         �   x�]�1�0��>�/PDRhY`cBl,V�T���q/���(0 ������X�$����X���5�0Ǟ��E�viW�1�Y��=l�I%vz�8'����
�Ձ��ݧ�k���E�K�Q(���C=���y��/f�3�      �   �   x�}�A
1�u{��`@o �ܺ��
�v24��q�bV�33#��U!$��/̎"���l8EL�'�x!�0x����X�w��{<Sf�9�#�Y9l}$��{��)���诜�:ɔ���Ԡ�8�t�O��bvi�EǓi�=:�#k\�
8� �*G�F�?�w��C���Q�e,<�t����UJ�a-���־ �{n�         �   x�m�=N1�k�s�����PB��8�d5��Y�^	q� EZڽ��d�����<J�p0�a�c�`H�n<&2Z�M�\(�h���M�G����B�V�0fڕ����,F���y������Ls	�Vފ5l����s,x��6��l�����_ϵ?ƃǀ��Z���A���'Z�Ly��驚�i���N<S����O����Џ_[w})�Tՙ�m9��9��/�67R�o�w�v      �   �   x�]�M
�0�יS�b�w���T���4a �)I��;���)��=��{�j�a�W�d<FX�zh=Yr�id]�h`��H`�n�1S��ͰVG񘈋��O	�I�%�F��R�Vx`K'�[ؕM+�3v�h�_���^�2r��s�A5���^�`8��~�LP�1�/UH*      	   +   x�3�4��35�4�2�44�32�4�2�4��30�4����� _�      �   [   x�3�4202�(��M,K-J�2���y�\��I���\&�g^YfjP��7E�l�j6��- <�fK�I��D�;F��� �.'      �   N   x�MΫ !Q�FA˟8�8����.1��j5�Y���mը2���`"������`#����><�t"r '/U      �   "   x�3�NMIU���M�2���Js2��b���� �wO      �   �   x�Uα�0����}bK+�iL�H��	j�5�^���r��/��d{5Ai��,�l+E�ox��:��;�kLZ���þ�Z1`�C�ܼW�m�� �1�7H׊')4���:+g�t�"@���h�d��__�	���+y���)qtH�%A$T�s4���F 2Fj�9K}�@�kBy��lH         �   x�EO�r�0<+���*�N��S�\<F�f)c�S~���'�c(�.޵w%��᛼��;��x�T}@����q+�du���CB5��f6�bX]��8�8��H�o_�H��8j+@�W�50\|���V(7��=�8�#ˤ�j=�g��^�����Cیm�P3]|��u�q'���3�`������h[��Zn8Ŭ��UU=j�w         -   x�3�4�2bc 6bS 6�4�2bNc.K 64�4����� ���      �   �   x�]нn�0���)�vH�V"UEB�v�
�d�T�6��x��XI�s��]�>�h�&ܼ�@�ƚK����j�,�\
��̋r�� �#-�S�xS�]�xku&a�>Ⱦ�ݑ�	w=ek�`}�������ޞ���>�	�T��3��Q���/�{v���D�Y����Ø[�a��H���
@��@�gd	�����մ?���yD˄J������3��gY� {���         2   x�3�4202�54�50T0��20 "NC�2�*k�\�8�� W� �      �   3   x�3�4�@.SN#�2�4�@.sN��4�@.K�C�CC��=... ��
w         <   x�3�(J-N�+I�2�t,���9�J�K2�2�S�L8C�R��L���c���� F�0      �   �   x�u��j�0Dף���Ǯ����@��U7G�*�����_�ɲ��9̌A;�Qw2\S��4r�]�����*xg,32��Ǯ��Ã�Q�m?�s�4�j�,��/��8dɧ�8VV9�2�l���U�%^��Z"	^���WNyH=Jn~��*�l�s|�x�
�q��U��$Yש�KB�x�m��%�q'�/���f�            x������ � �         �   x�m�Aj�0���)t�I�me�
�J[��+�%�*���J�`9f3�����ڏc��6O-<�69o���]�h/gC�x4�#F�B.4���ip�'C�,@�\�WB����m�C�Ԝ��K9D-�_mm �枡�5�;��6�EC�e��nۙQqƯ�J��!�c��2���$<-�I�������V��/
���ř�B��	�f��_awl      �      x�3�tLNLI��L����� %��      �   k   x�E�;�0�z|���K��&���if��U�`)�����^�o?_�e���{H M�$� E�,+ �[�U��[�5��[�u��[���ېM��۔Y���(}>B��Qy         /   x�ȹ  ���T�?�^��Ik2\N(H%��^m��`�m6y����      �   v   x����I�SpLNL9�2739_����DH�r�p`�6I�q�rb�6I�s�qAeSR��r�3o�Sp:��839h�6iϼ�Ԣ�Ԕ�D���T8�%�U%�$����� ��<�           x���=j1�k�>�2��,lH�6�b�A`K�e/ln�e��b�g���ٖ����+�O���UHR�j��C�9$�A��v�8�~FN2�X�ښ0t�PI4�(��[��Q'�T16C<��^3�����32�;�!B�B`�vkR`FRF8\��2Scܸ�D��#��Ȋ�;T�YA�v��5������D�h�B�K�o�>����6�tS����JP�79A���<�;���]ymq���`�؋���~�� #��r���O>������Ԫ4     