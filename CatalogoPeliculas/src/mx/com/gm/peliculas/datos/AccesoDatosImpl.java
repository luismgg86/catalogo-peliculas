package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;
//clase para cumplir con el contrato definido en la interface
//en vez de llamarse sobreescritura, se llama implementacion (metodos)

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreRecurso){
        var archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {

        var archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();

        try {

            var entrada = new BufferedReader(new FileReader(archivo));  
            var lectura = entrada.readLine();
            while (lectura != null) {
                Pelicula pelicula = new Pelicula(lectura);
                peliculas.add(pelicula);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas" + ex.getMessage());
        }

        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);

        try {
            PrintWriter entrada = new PrintWriter(new FileWriter(archivo, anexar));
            entrada.println(pelicula.getNombre());
            entrada.close();
            System.out.println("Se ha escrito informacion al archivo" + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir pelicula" + ex.getMessage());
        }

    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx{
        
        var archivo = new File(nombreRecurso);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            var indice = 1;
            while (lectura != null) {
                if (buscar != null && buscar.equalsIgnoreCase(lectura)){
                    resultado = "Pelicula " + lectura + " encontrada en el indice " + indice;
                    break;
                }
                lectura = entrada.readLine();
                indice++;
            }
            entrada.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        }

        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx{
        var archivo = new File(nombreRecurso);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo" + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreRecurso){
        var archivo = new File(nombreRecurso);
        if (archivo.delete()) {
            System.out.println("Se ha borrado el archivo");
        } else {
            System.out.println("El archivo no existe");
        }
    }

}
