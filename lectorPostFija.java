/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas_g2;

import java.util.ArrayList;
import static pilas_g2.Pilas_g2.evaluaPosFija;

/**
 *Clase con el metodo para evaluar la expresión pos-fija, con su ejecutable y pruebas variadas
 * @author alejandrosalmon
 */
public class lectorPostFija {
    /**
     * Metodo que devuelve un double
     * @param a ArrayList de cadenas que el metodo analisa para convertirlo a numeros con decimal y realizar las respectivas operaciones interpretando la forma pos-fija. 
     * @return double Devuelve el resultado de la operación 
     */
    public static double evaluaPosFija(ArrayList a){
        double resp, x, y;
        PilaA pila= new PilaA();
        int i;
        char d;
        for(i=0; i<a.size(); i++){
           //Ciclo para convertir la cadena en numero o en caracter
        try{
            pila.push(Double.parseDouble((String) a.get(i)));
        }catch(Exception error){  
        d= (Character) a.get(i).toString().charAt(0);
        
        switch(d){
            //Si recibe el caracter '+' suma los dos valores anteriormente guardados en la pila
            case '+':
                x=(double) pila.pop();
                y=(double) pila.pop();
                pila.push(x+y);
                break;
            //Si recibe el caracter '-' resta los dos valores anteriormente guardados en la pila
            case '-':
                x=(double) pila.pop();
                y=(double) pila.pop();
                pila.push(x-y);
                break;
            case '*':
            //Si recibe el caracter '*' multiplica los dos valores anteriormente guardados en la pila
                x=(double) pila.pop();
                y=(double) pila.pop();
                pila.push(x*y);
                break;
            case '/':
            //Si recibe el caracter '/' divide los dos valores anteriormente guardados en la pila como denominador el valor guardado en el tope de la pila
                x=(double) pila.pop();
                y=(double) pila.pop();
                pila.push(y/x);
                break;
            case '^':
            //Si recibe el caracter '^' eleva a la potencia del numero que este guardado en el tope al otro numero que se encuentre en la pila
                x=(double) pila.pop();
                y=(double) pila.pop();
                pila.push(Math.pow(y,x));
                break;       
        }   
        }
        } 
        resp=(double) pila.pop();
        return resp;
    }
    
    public static void main(String[] args) {
        double resp;
        ArrayList<String>  a= new ArrayList<String>();
        
        a.add("4");
        a.add("9");
        a.add("+");
        
        
        resp= evaluaPosFija(a);
        
        System.out.println(resp);
        
        a.clear();
        
        a.add("7");
        a.add("2");
        a.add("^");
        
         resp= evaluaPosFija(a);
        
        System.out.println(resp);
        
        a.add("7");
        a.add("2");
        a.add("^");
        a.add("4");
        a.add("+");
        a.add("2");
        a.add("/");

        
        resp= evaluaPosFija(a);
        
        System.out.println(resp);
        
        a.clear();
        
        a.add("5");
        a.add("0");
        a.add("/");
        
        resp= evaluaPosFija(a);
        
        System.out.println(resp);
    }
    
}
