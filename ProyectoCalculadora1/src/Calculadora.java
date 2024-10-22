
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Esta es la clase de la calculadora, que incluye los metodos necesarios para transformar una cadena y regresar el resultado de una operación matemática
 * @author Alejandro Salmón, Lorenzo Pazos, Sebastián Velasco, Luciano Moctezuma, Luca Boschetti
 */
public class Calculadora {


/**
 *Constuctor vacio de Calculadora
 *
 */
    public Calculadora() {
    }

    /**
     * Método que recibe un caracter, es decir signo, y determina la prioridad para las operaciones
     * @param c
     * @return <ul>
     *          <il> -1 si no cumple ninguna condicion,
     *          <il> 3 si '^',
     *          <il> 2 si '*', '/',
     *          <il> 1 si '+', '-',
     *          <il> 0 si '(', ')',
     *          <ul>
     */
    public  int prioridades(char c) {
        int ans = -1;
        //Regresa respuesta -1 si no cumple ninguno de los casos,
        switch (c) {
            case '^' -> ans = 3;
            case '*', '/' -> ans = 2;
            case '+', '-' -> ans = 1;
            case '(', ')' -> ans = 0;
        }

        return ans;
    }

    /**
     * Método que recibe una Cadena y la traduce a posfija en forma de ArrayList
     * @param cadena
     * @return double ArrayList de tipo String
     */
    public ArrayList<String> creadorPosfija(String cadena) {
        ArrayList<String> aux = new ArrayList<>();
        ArrayList<String> resp = new ArrayList<>();
        PilaA<Character> pila = new PilaA<>(); // Cambiado a pila genérica
        ArrayList<Character> num= new ArrayList<>(Arrays.asList('1','2','3','4','5','6','7','8','9','0','.','-'));
        StringBuilder sb;
        int i;
        sb = new StringBuilder();
        //bucle para extraer los numeros y signos de una cadena y agregarlos en ese mismo orden a un ArrayList
        for (i = 0; i < cadena.length(); i++) {
               if(i==cadena.length()-1 && num.contains(cadena.charAt(i))){
                   sb.append(cadena.charAt(i));
                   aux.add(sb.toString());
               }
               else{
                   if(i==0 && cadena.charAt(i)=='-'){
                       sb.append(cadena.charAt(i));
                       if (num.contains(cadena.charAt(i+1))){
                           sb.append(cadena.charAt(i+1));
                           i++;
                           aux.add(sb.toString());
                           sb = new StringBuilder();
                           continue;
                       }
                   }
                   if(num.contains(cadena.charAt(i))){
                       if(cadena.charAt(i)=='-'){
                           if (num.contains(cadena.charAt(i+1))){
                               sb.append(cadena.charAt(i));
                               sb.append(cadena.charAt(i+1));
                               if (cadena.charAt(i-1)!='('){
                                   aux.add("+");
                               }
                               i++;
                               aux.add(sb.toString());
                               sb = new StringBuilder();
                           }else {
                               if(aux.size()>1 && aux.get(aux.size() - 1).equals("(")){
                                   sb.append(cadena.charAt(i));
                               }
                           }

                       }
                       else{
                           if(sb.isEmpty())
                               aux.add(cadena.charAt(i) + "");
                           else{
                               aux.add(sb.toString());
                               sb = new StringBuilder();
                               aux.add(cadena.charAt(i)+"");
                           }
                       }
                   }
                   else{
                       if(sb.isEmpty())
                           aux.add(cadena.charAt(i) + "");
                       else{
                           aux.add(sb.toString());
                           sb=new StringBuilder();
                           aux.add(cadena.charAt(i) + "");
                       }

                   }
               }
        }
        // bucle para agregar los valores del ArrayList auxiliar pero ya en forma posfija
        for (i = 0; i < aux.size(); i++) {
            //si el tamaño de la cadena que se encuentra en determinada posicion del array es 1 agrega a la pila el signo que se ecuentra  si es ')' y el signo que esta en el tope de la pila no es '(' agrega al arreglo y quita el numero en el tope
            if (aux.get(i).length() == 1) {
                if (aux.get(i).equals("(")) {
                    pila.push('(');
                }
                else{
                    if (aux.get(i).equals(")")) {
                        while (!pila.isEmpty() && pila.peek() != '(') {
                            if (pila.peek() != '(') {
                                resp.add(pila.pop() + "");
                            } else {
                                pila.push(aux.get(i).charAt(0));
                            }
                        }
                        if (!pila.isEmpty()) {
                            if (pila.peek() == '(')
                                pila.pop();
                        }
                    }
                    else {
                        //bucle para determinar si el signo que se va a meter al Array tenga mayor prioridad que el que se encuentra en la pila
                        while (!pila.isEmpty() && prioridades(aux.get(i).charAt(0)) > prioridades(pila.peek())+1) {
                            if(pila.peek()=='('){
                                pila.pop();
                            }
                            else{
                                //cualquier otro signo lo quita de la pila y lo agrega al Array de respuesta
                                resp.add(pila.pop() + "");
                            }
                        }
                        pila.push(aux.get(i).charAt(0));
                    }
                }
            } else{
                resp.add(aux.get(i));
            }
        }
        while (!pila.isEmpty()) {
           resp.add(pila.pop() + "");
        }
        return resp;
    }



     /**
     * Método que devuelve un double
     * @param a ArrayList de cadenas que el metodo analisa para convertirlo a numeros con decimal y realizar las respectivas operaciones interpretando la forma pos-fija.
     * @return double Devuelve el resultado de la operación
     */
    public double evaluaPosFija(ArrayList a){
        double resp, x, y;
        PilaA pila= new PilaA();
        int i;
        char d;

        System.out.println(pila);
        for(i=0; i<a.size(); i++){
            //Ciclo para convertir la cadena en numero o en caracter
            try{
                System.out.println(a.get(i));
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
                        pila.push(y-x);
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

}
