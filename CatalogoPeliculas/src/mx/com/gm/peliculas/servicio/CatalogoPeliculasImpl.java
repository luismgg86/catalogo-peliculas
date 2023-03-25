package mx.com.gm.peliculas.servicio;

import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.datos.IAccesoDatos;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final IAccesoDatos datos;

    //para especificar la implementacion que vamos a usar
    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for(var pelicula : peliculas){
                System.out.println("pelicula = " + pelicula.getNombre());
            }
        } catch (LecturaDatosEx ex) {
            System.out.println("Error al listar peliculas");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String nombrePelicula) {
        try {
            var resultado = this.datos.buscar(NOMBRE_RECURSO, nombrePelicula);
            if (resultado != null){
                System.out.println("resultado = " + resultado);
            }
            else{
                System.out.println("Película no encontrada en el catálogo");
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if (this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }
            else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar catalogo de peliculas");
            ex.printStackTrace();
        }
    }

}
