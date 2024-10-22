/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Clase para revisar la sintaxis correcta de la cadena en la calculadora
 * @author lucianomoctezuma
 */
public class Revisores {
    private String operaciones; //Vamos a crear una clase calculadora y su único atributo va a ser la cadena.
    
    /**
     * Constructor vacio de la clase Revisores
     */
    public Revisores(){
        
    }
    
    public Revisores(String operaciones){
        this.operaciones=operaciones;
    }
    
    public void setOperaciones(String operaciones){
        this.operaciones=operaciones;
    }
    
    public String getOperaciones(){
        return operaciones;
    }
    
    
    /**
     * Método para determinar si el char es un operador
     * @param ch
     * @return <ul>
     *          <il> true si ch es operador,
     *          <il> false si ch es numero o punto 
     *          <ul>

     */
    private boolean esOperador (Character ch){
        boolean resp=false;
        
        if(ch == '+' || ch == '*' || ch == '/' || ch == '^' || ch == '-')
            resp=true;
        return resp;
    }
    
    
    /**
     * Método para determinar si el char es un parentesis
     * @param ch
     * @return <ul>
     *          <il> true si ch es parentesis,
     *          <il> false si ch es cualquier otro caracter
     *          <ul>
     */
    private boolean esParentesis (Character ch){
        boolean resp = false;
        if(ch == '(' || ch == ')')
            resp = true;
        return resp;
    }
    
   
    /**
     * Método que revise que no hayan parentesis vacios
     * @return <ul>
     *          <il> true si no están vacios,
     *          <il> false si están vacios
     *          <ul>
     */
    public boolean revParentesisVacios(){
        boolean bandera = true; 
        int i = 0;
        while(i < operaciones.length() && bandera){
            if(operaciones.charAt(i)== '('){
                if(operaciones.charAt(i + 1) == ')')
                    bandera = false;
            }
            i++;
        }
        return bandera;
    }
   
    /**
     * Este  método va a revisar la puntuación, es decir, evitar que el usuario ponga 1..0, ya que esto no es un número.
     * @return <ul>
     *          <il> true si esta bien la puntuación,
     *          <il> false si esta mal la puntuación
     *          <ul>
     */
    public boolean revisadorPuntos(){
        boolean bandera = true;
        int i=0;
        
        while(i < operaciones.length() - 1 && bandera){ //Se utiliza un while porque hay una bandera.
            //Si hay puntos, se van a contar todos los puntos que existen antes de un operador.
            if (operaciones.charAt(i) == '.'){
                if(operaciones.charAt(i + 1) == '.'){
                    bandera = false;
                }
            }
            i++;
        }
        return bandera;
    }
    
    /**
     * /Método que checa si hay dos signos seguidos
     * @return <ul>
     *          <il> true si no hay signos seguidos,
     *          <il> false si hay signos seguidos
     *          <ul>
     */
    public boolean revisadorSignos(){ 
        boolean bandera = true;        ///Inicializamos una bandera en true
        int i = 0;
        Character ch = operaciones.charAt(i);
        
        if(ch == '+' || ch == '*' || ch == '/' || ch == '^')  //Tenemos que poner esta primera condicion porque el primer signo no puede ser un operando excepto ('!' o '-')
            bandera = false;
        else {
            i++;
            if(operaciones.length() > 0){     //Para que se haga una lectura de signos, por lo menos tiene que haber dos chars
                while(i < operaciones.length() - 1 && bandera){  //el while se condiciona con el tamano de la cadena y que la bandera siga siendo true
                    if(esOperador(operaciones.charAt(i))){   //checa para ver si el char es operando
                        if(esOperador(operaciones.charAt(i + 1))){  //si el siguiente char a el es operando, cambia la bandera
                            bandera = false;
                        }
                    }
                    if(operaciones.charAt(i) == '!'){  //Lo mismo pero ahora con la posibilidad de un numero negativo para eviatar dos dignos negativos seguidos
                        if(operaciones.charAt(i + 1) == '!'){  //solo lo compara con otro negativo por que si puede haber un negativo y despues un operando ejemplo: 4 * !8
                            bandera = false;
                        }
                    }
                    i++;   
                }
            }
            else
                bandera = false;
        }
        if(esOperador(operaciones.charAt(i)) || operaciones.charAt(i) == '!'){ //Esto significa que el ultimo char acabo siendo un operador o un '!', por lo que tampoco es valido 
            bandera = false;
        }
        return bandera;    
    }
    
    /**
     * Método que revisa si los parentesis abren y cierra de forma correcta
     * @return <ul>
     *          <il> true si esta escritos de forma correcta,
     *          <il> false si no estan escritos en el orden y cantidad correcta
     *          <ul>
     */
    public boolean revisadorParentesis() {
       PilaA<Character> pila = new PilaA<>();
       boolean bandera = true;
       int i = 0;
       while (i < operaciones.length() && bandera) {
           char aux = operaciones.charAt(i);
           if (aux == '(') {
               pila.push(aux);
           } else if (aux == ')') {
               if (pila.isEmpty()) {
                    return false; // No hay un '(' correspondiente, paréntesis no balanceados.
               } else {
                    pila.pop(); // Sacamos el '(' correspondiente.
                }
              }
           i++;
        }
    return pila.isEmpty() && bandera; // Si la pila está vacía, los paréntesis están balanceados.
     
    }
    
    
    /**
     * Método que revisa que nunca haya una división entre cero
     * @return <ul>
     *          <il> true si no hay división entre cero,
     *          <il> false si hay división entre cero
     *          <ul>
     */
    public boolean revisadorDivisionCero(){ 
        boolean resp = true;
        int i = 0;
        
        if(operaciones.length() > 0){
            while (i < operaciones.length() - 1 && resp){
                if(operaciones.charAt(i) == '/') {
                    if(operaciones.charAt(i + 1) == '0')
                    resp = false;
                }
                i++;
            }
        }
        return resp;        
    }
    
    /**Metodo booleano que revise que todos los metodos esten correctos 
     * @return <ul>
     *          <il> true si la cadena cumple con todas las características correctas,
     *          <il> false si la cadena no cumple con al menos una característica correcta
     *          <ul>
     */
    public boolean revSintaxis(){
        boolean resp = true;
        boolean Vacios = revParentesisVacios();
        boolean Puntos = revisadorPuntos();
        boolean Signos = revisadorSignos();
        boolean Parentesis = revisadorParentesis();
        boolean Cero = revisadorDivisionCero();
        
        if (!Puntos || !Signos || !Parentesis || !Cero || !Vacios){
            resp = false;
        }
        return resp;
    }  
}
