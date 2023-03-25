package mx.com.gm.peliculas.datos;

import java.util.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;
//contiene los métodos a implementar pero de manera muy generica para que pueda tener diversas implementaciones
//las clases de los diferentes paquetes del proyecto se comunican indirectamente por medio de las interfaces
//de esta manera implementamos bajo acoplamiento - alta cohesion
public interface IAccesoDatos {
    
    boolean existe(String nombreRecurso) throws AccesoDatosEx;
    
    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;
    
    void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx;
    
    String buscar(String nombreArchivo, String nombre) throws LecturaDatosEx;
    
    void crear(String nombreArchivo) throws AccesoDatosEx;
    
    void borrar(String nombreArchivo) throws AccesoDatosEx;
    
}
