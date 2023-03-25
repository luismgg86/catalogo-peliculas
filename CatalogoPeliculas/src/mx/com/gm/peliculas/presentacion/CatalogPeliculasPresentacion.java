package mx.com.gm.peliculas.presentacion;

import mx.com.gm.peliculas.servicio.CatalogoPeliculasImpl;
import mx.com.gm.peliculas.servicio.ICatalogoPeliculas;
import java.util.Scanner;
//esta es la capa de presentacion, la cual se comunica con la capa de negocio, y la de negocio a su vez con la de datos
//bajo acomplamiento: utilizamos la menor cantidad de relaciones entre las clases
//alta cohesion: cada clase se encarga de realizar su tarea y no mas

//la capa de datos se comunica con el archivo
//la capa de negocio se encarga de realizar las operaciones de nuestra aplicacion
//la capa de presentacion se encarga de mostrar la informacion al usuario y de capturar la info del usuario
public class CatalogPeliculasPresentacion {

    public static void main(String[] args) {
        
        var opcion = -1;
        var scanner = new Scanner(System.in);
        
        //como estamos dentro de un métido estático(main) no se va a utlizar el constructor de esta clase
        //solo estamos iniciando unavariable local
        ICatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
        
        while(opcion != 0){
            System.out.println("\nElige una opcion: \n"
                + "1. Iniciar catálogo de peliculas \n"
                + "2. Agregar película\n"
                + "3. Listar películas\n"
                + "4. Buscar película\n"
                + "0. Salir");
            
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch(opcion){
                case 1 -> {
                    catalogo.iniciarCatalogoPeliculas();
                    System.out.println("Catálogo de peliculas iniciado");
                    break;
                }
                
                case 2 -> {
                    String nombrePelicula;
                    System.out.println("Introduce el nombre de la pelicula: ");
                    nombrePelicula = scanner.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    System.out.println("Película agregada");
                    break;
                }
                
                case 3 -> {
                    System.out.println("Lista de peliculas: \n");
                    catalogo.listarPeliculas();         
                    break;
                }
                
                case 4 -> {
                    String nombrePelicula;
                    System.out.println("Introduzca el nombre de la pelicula: ");
                    nombrePelicula = scanner.nextLine();
                    catalogo.buscarPelicula(nombrePelicula);
                    break;
                }
                
                case 0 -> {
                    System.out.println("Fin del programa, Hasta pronto!");
                }
                
                default -> {
                    System.out.println("Opción invalida");
                }
            }
        }
        
    }
}
