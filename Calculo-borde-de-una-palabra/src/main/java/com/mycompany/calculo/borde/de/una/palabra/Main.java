
package com.mycompany.calculo.borde.de.una.palabra;


import java.util.Scanner;

public class Main {
    
    private ListaEnlazada<String> aux;
    private ListaEnlazada<String> lista;
    
    public Main(){
        this.aux = new ListaEnlazada();
        this.lista = new ListaEnlazada();
    }
   
    
    public void subcadena(ListaEnlazada<String> input){
        int ini = 0;
        int fin = 0;
        //ListaEnlazada<Character> copia = new ListaEnlazada();
        while(fin<input.size()){           
            if( comparar(input,ini,fin) ){
                String auxStr = listaToString(aux);
                this.lista.pushBack(auxStr);
            }
            fin ++;
        
        }
        this.aux.clean();
       
    }
    
    /* Vamos a ir comparando subcadenas, pero sin sacar esta subcadena directamente del
    array de char. Este metodo nos dice si una subcadena del principio es igual que una subcadena
    del final, además, si si es el caso guarda esta subcadena en el atributo aux.
    ¿Como sabemos de donde a donde va la subcadena? para eso son los parametros ini y fin.*/
    public boolean comparar(ListaEnlazada<String> input, int ini, int fin){
        int n = input.size() -1;
        while(ini<=fin && input.pop(ini).equals( input.pop(n-fin+ini) ) ){
            this.aux.pushBack(input.pop(ini));
            ini ++;
        }
        //Osea, si no era subcadena
        if(ini<=fin){
            this.aux.clean(); //limpiamos aux
        }
        return ini>fin;
    }
    
    
    public ListaEnlazada<String> procesar(String input){
        ListaEnlazada<String> list = new ListaEnlazada();
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) != '[' && input.charAt(i) != ']' && input.charAt(i) != ','){
                list.pushBack(String.valueOf(input.charAt(i)) );
            }
        }
        return list;
    }
    public String listaToString(ListaEnlazada<String> lista){
        String cad = "[";
        if(lista.empty()){
            cad += "]";
        }else{
            Node n = lista.getHead(); 
            while(n.getNext() != null){
                cad += n.getData() + ",";
                n = n.getNext();
            }
            cad += n.getData() + "]";
        }
        return cad;
    }
    
    public static void main(String[] args) {
        Main xd = new Main();
        Scanner sc = new Scanner(System.in);                  
        String input = sc.nextLine();
        //xd.procesar(in).StringTo();
        xd.subcadena(xd.procesar(input));
        int n = xd.lista.size();
        for(int i=0; i<n-1;i++){
            //System.out.println(n);
            System.out.println(xd.lista.popFront());
        }
        System.out.print(xd.lista.popFront());

    }
}

class ListaEnlazada <T> {
    private Node head;
    private Node tail;
    private int size;
    
    public ListaEnlazada(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public ListaEnlazada<T> clean(){
        this.head = null;
        this.tail = null;
        this.size =0;
        return this;
    }
    
    
    public void setTail(Node tail){
        this.tail = tail;
    }
    
    public Node getTail(){
        return this.tail;
    }
    
    public void setHead(Node head){
        this.head = head;
    }
    
    public Node getHead(){
        return this.head;
    }
    
   public int size(){
       return this.size;
   }
   public ListaEnlazada unir(ListaEnlazada list2){
       this.size = this.size + list2.size();
        //Si esta lista esta vacio, la union es la lista 2
        if(empty()){
            this.head = list2.getHead();
            this.tail = list2.getTail();
        }else{
            // obtenemos la cola de lista 1 y que esta apunte a la cabeza de lista 2
            this.tail.setNext(list2.getHead());
            // Verificamos que la lista que vamos a unir no esté vacía
            if(list2.getHead()!=null){
                // la cola de lista 1 ahora va a hacer la de lista 2
                this.tail = list2.getTail();
            }            
            
        }
        return this;
        
    }
    
    
    public boolean empty(){
        return this.head == null;
    }
    
    
    public void pushFront(T el){
        Node n = new Node(el);  
        n.setNext(this.head); 
        this.head = n;
        if(this.tail == null){
            this.tail = head;
        }
        this.size += 1;   
        
    }
    
    public void pushBack(T el){
        Node n = new Node(el);
        if(empty()){
            this.head = n;
            this.tail = this.head;
        }else{
            this.tail.setNext(n);
            this.tail = n;
        }
        this.size += 1;
    }
    
    public T popBack(){       
        T cad = null;
        if(!empty()){
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            }else{
                Node aux = this.head;
                while(aux.getNext() != this.tail){
                    aux = aux.getNext();
                }
                cad =  (T) aux.getNext().getData();
                aux.setNext(null);
                this.tail = aux;
            }
            this.size -= 1;
        }
        return cad;
    }
    
    public T popFront(){
        T cad = null;
        if(!empty()){
            cad = (T) this.head.getData();
            this.head = this.head.getNext();
            if(this.head == null){
                this.tail = null;
            }
            
        this.size -= 1;
        }
        
        return cad;
    }    
    
    public T pop(int index){
        T res = null;
        if(!empty() && index < this.size){
            int i = 0;
            Node aux = this.head;
            while(i < index){
                aux = aux.getNext();
                i+=1;
            }
            res = (T) aux.getData();
        }
        return res;
    }
    
    public void StringTo(){
        String cad = "[";
        if(empty()){
            cad += "]";
        }else{
            Node n = this.head;
            while(n.getNext() != null){
                cad += n.getData() + ",";
                n = n.getNext();
            }
            cad += n.getData() + "]";
        }     
        System.out.println(cad);
    }


}

class Node<T>{
    private T data;
    private Node next;
    
    public Node(T data){
        this.data = data;
        this.next = null;
    }
    
    public void setNext(Node n){
        this.next =n;
    }
    
    public Node getNext(){
        return this.next;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return this.data;
    }
}
