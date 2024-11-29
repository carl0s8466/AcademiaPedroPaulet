package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.modelo.Acceso;

import java.util.List;

public interface IAccesoService {
    List<Acceso> getAccesoByUser(String username);
}
