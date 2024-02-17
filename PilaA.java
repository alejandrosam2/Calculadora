/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas_g2;

/**
 *
 * @author alejandrosalmon
 */
public class PilaA <T> implements PilaADT<T>{
    private T[] pila;
    private int tope;
    private final int MAX=100;

    public PilaA() {
        pila= (T[]) new Object[MAX];
        tope=-1; //pila vacia
    }
    
     public PilaA(int max) {
        pila= (T[]) new Object[max];
        tope=-1; //pila vacia
    }

    @Override
    public void push(T dato) {
        if(tope+1==pila.length)// Pila llena
                expande();
        tope++;
        pila[tope]=dato;
            
    }
    
    
    private void expande(){
       
        T[] masGrande=(T[]) new Object[pila.length*2];
        for(int i=0;i<=tope;i++) //i< pila.length
            masGrande[i]=pila[i];
        pila=masGrande;
            
    }
    
    

    @Override
    public T pop() {
        if(this.isEmpty())
            throw new ExcepcionColeccionVacia("La pila esta vacia"); //lanza excepcion
        T eliminado;
        eliminado=pila[tope];
        pila[tope]=null;
        tope--;
        return eliminado;
    }

    @Override
    public boolean isEmpty() {
        return tope==-1;
    }

    @Override
    public T peek() {
        if(this.isEmpty())
            throw new ExcepcionColeccionVacia("La pila está vacía");
        return pila[tope];
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder("\nPila de 0 a tope\n");
        for(int i=0;i<=tope;i++)
            sb.append(pila[i]).append(" ");
        return sb.toString();
    }

    
    
}
