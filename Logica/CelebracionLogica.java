/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Data.CelebracionDatos;
import Entidad.Celebracion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Uned Alajuela
 * Clase para efectuar la logica requerida, segun los metodos solictados
 * CelebracionLogica.java Fecha de creacion 16/2/2026
 */
public class CelebracionLogica {
    
    private CelebracionDatos datos;
    
    public CelebracionLogica(CelebracionDatos datos){
        
        this.datos = datos;
    }
    
    //devuelve toda la lista sin filtrar
    public List<Celebracion> obtenerTodas(){
        return datos.obtenerRegistros();
    }
    
    public List<Celebracion> filtrarPorPais(String texto){
        
        String filtro = texto.trim().toLowerCase();
        List<Celebracion> resultado = new ArrayList<>();

        Iterator<Celebracion> it = datos.obtenerRegistros().iterator();

        while (it.hasNext()) {

            Celebracion c = it.next();

            String pais = (c.getPais() == null) ? "" : c.getPais().toLowerCase();

            if (pais.contains(filtro)) {
                resultado.add(c);
            }
        }
            

        return resultado;
    }
    
    public boolean actualizarCelebracion(int id, java.time.LocalDate fecha, String descripcion, String pais ){
        
        if (fecha == null) return false;
        
        for (Entidad.Celebracion c : datos.obtenerRegistros()){
            
                if(c.getId()== id){
                c.setFecha(fecha);
                c.setDescripcion(descripcion == null ? "" : descripcion.trim());
                c.setPais(pais == null ? "" : pais.trim());

                datos.guardarCambios();

                return true;
            }
        }
        
        return false;
    }
    
    //Funcion recursivas para inversion de String creando objetos
    /*
    public String invertirRec(String s){
        
        if (s == null) return "";
        //Base
        if (s.length() <= 1) return s;
        //Formula Recursivida
        return invertirRec(s.substring(1)) +s.charAt(0);
        
    }
    */
    
    //Funcion recursirva usando el indice, encapsulado interno de la recursividad
    
    public String invertirRec(String s){
        if (s == null) return "";
        return invertirRec(s,s.length() -1);
    }
    
    private String invertirRec(String s, int index){
        if (index < 0) return "";
        return s.charAt(index)+ invertirRec(s,index - 1);
    }
    
    //Funcion de comparacion usando compareTo
    
    private int comparar(Celebracion a, Celebracion b){
        
        String pa = (a.getPais() == null)? "":a.getPais().trim();
        String pb = (b.getPais()== null) ? "":b.getPais().trim();
        
        int cmpPais = pa.compareToIgnoreCase(pb);
        if(cmpPais != 0)return cmpPais;
        
        if (a.getFecha() == null && b.getFecha() == null)return 0;
        if (a.getFecha() == null) return -1;
        if (b.getFecha() == null) return 1;
        
        return a.getFecha().compareTo(b.getFecha());
        
    }
    
    public void ordenarAscPaisFecha(){
        var lista = datos.obtenerRegistros();
        
        for(int i=1; i<lista.size();i++){
            Celebracion key = lista.get(i);
            int j = i - 1;
            
            //Mover registro hacia abajo si es menor usando comparar          
            while(j >= 0 && comparar(key,lista.get(j)) < 0){
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j+1, key);
        }
    }
    
    //Metodo principal Divide la lista completa y envia los parametros de inicio para el metodo de acomodo.
    public void ordenarDescMergeSort() {
        var lista = datos.obtenerRegistros();
        if (lista.size() < 2) return;

        mergeSort(lista, 0, lista.size() - 1);
    }
    
    //Metod recursivo para acomodo de la lista
    private void mergeSort(List<Celebracion> lista, int left, int right) {
        //Base
        if (left >= right) return;
        //Calcula el centro para dividir
        int mid = (left + right) / 2;
        //Separa lado izquierdo y derecho
        mergeSort(lista, left, mid);
        mergeSort(lista, mid + 1, right);

        merge(lista, left, mid, right);
    }
    
    //Metod para el acomodo desendente de los registros.
    private void merge(List<Celebracion> lista, int left, int mid, int right) {

        List<Celebracion> temp = new ArrayList<>();

        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {

            // Compara el valor mayor
            if (comparar(lista.get(i), lista.get(j)) > 0) {
                temp.add(lista.get(i));
                i++;
            } else {
                temp.add(lista.get(j));
                j++;
            }
        }
        //Copia los elementos que no fueron comparados en lado izquierdo
        while (i <= mid) {
            temp.add(lista.get(i));
            i++;
        }
        //Copia los elementos que no fueron comparados en lado Derecho
        while (j <= right) {
            temp.add(lista.get(j));
            j++;
        }
        //Inserta en orden 
        for (int k = 0; k < temp.size(); k++) {
            lista.set(left + k, temp.get(k));
        }
    }
    
    
}
