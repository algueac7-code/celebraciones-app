/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Entidad.Celebracion;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Uned Alajuela
 * Case para gardar y manipular los datos en la clase Arraylist
 * CelebracionDatos.java Fecha de creacion 9/2/2026
 */
public class CelebracionDatos {
    
    private final ArrayList<Celebracion> lista;
    private int nextId;
    private boolean persistencia;
    private final Path archivo;
    
    public CelebracionDatos(boolean persistencia){
        
        this.persistencia = persistencia;
        this.lista = new ArrayList<>();
        this.nextId = 1;
        this.archivo = Paths.get(System.getProperty("user.home"), "celebraciones.txt");
        
        if (persistencia){
            
            cargarDesdeArchivo();
        }
    }
    
    //Genera un numero de Id de forma permanente para cada Array de lista
    public int generarId(){
        return nextId;
    }
    
    //Agrega arrays a la lista
    public void agregar(Celebracion c){
        lista.add(c);
        nextId++;

        if (persistencia){            
            guardarEnArchivo();
        }
    }
    
    //Sobre escribe el constructor de la clase Celebracion y añade iteracion de id automatica
    //usa el metodo agregar para colocar en la lista.
    public Celebracion crear(LocalDate fecha, String descripcion, String pais){
        int id = generarId();
        Celebracion c = new Celebracion(id,fecha,descripcion,pais);
        agregar(c);
        return c;
    }
    
    public ArrayList<Celebracion> obtenerRegistros(){
        
        return lista;
    }
    
    public int cantidad(){
        
        return lista.size();
    }
    
    //---------------------------------------------------------------------------------------------
    //-------------------------Metodos para el uso de persistencia---------------------------------
    //---------------------------------------------------------------------------------------------
    
    public void cargarDesdeArchivo(){
        
        if (!Files.exists(archivo)){
            return;
        }
        
        try{
            
            List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
            
            int maxId = 0;
            lista.clear();
            //lectura del archivo
            for (String linea : lineas){
                linea = linea.trim();
                if (linea.isEmpty())continue;
                
                //ajusta los datos del .txt al formato de la entidad y lo carga.
                String[] partes = linea.split(";", -1);
                if(partes.length < 4)continue;
                
                int id = Integer.parseInt(partes[0]);
                LocalDate fecha = LocalDate.parse(partes[1]);
                String descripcion = desEscapar(partes[2]);
                String pais = desEscapar(partes[3]);
                
                Celebracion c = new Celebracion(id,fecha,descripcion,pais);
                lista.add(c);
                
                if(id > maxId)maxId = id;
            }
            
            nextId = maxId +1;
            
        }catch(Exception e){
            
           System.err.println("Error en lectura del archivo: " + e.getMessage());
           e.printStackTrace();
        }
        
    }
    
    private void guardarEnArchivo(){
        
        try{
            
        Path parent = archivo.getParent();
        if(parent != null && !Files.exists(parent)){
            Files.createDirectories(parent);
        }
        
        //Instancia y crea un ArrayList para cargarlo al archivo .txt
        List<String> lineas = new ArrayList<>();
        
        for (Celebracion c : lista){
            
            String linea = c.getId()+";"+c.getFecha()+";"+c.getDescripcion()+";"+c.getPais()+";";
            lineas.add(linea);
        }
        
        //Guarda los datos en el .txt
        
        Files.write(archivo, lineas, StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        
        }catch(Exception e){
            
            System.err.println("Error al guardar archivo: " + e.getMessage());
            e.printStackTrace();
            
        }
        
    }
    
    public void guardarCambios(){
        if(persistencia){
            guardarEnArchivo();
        }
    }
    
    private String desEscapar(String s) {
        if (s == null) return "";
        return s.replace("\\;", ";");
    }
    
    private String escapar(String s) {
        if (s == null) return "";
        return s.replace(";", "\\;");
    }
}
