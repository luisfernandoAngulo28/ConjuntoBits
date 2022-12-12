/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapadeNegocio;

import java.util.Arrays;

/**
 *
 * @author ferna
 */
public class ConjuntoBits {
    //Atributos
    public VectorNbits VNumeros;
    public VectorNbits Vsignos;
    int cant;//Cantidad de elementos del conjuntos
    
    // VNumeros [0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0]
    // VSignos [0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0]
    //Constructor
    public ConjuntoBits() {
        this.VNumeros = new VectorNbits(20, 7);
        this.Vsignos = new VectorNbits(20, 1);
        this.cant = 0;
        
       // System.out.println(VNumeros.toString());
      //  System.out.println(Vsignos.toString());
        
    }
    // VNumeros [0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0]
    // VSignos [0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0]
                             //-3
    public void insertar(int elemento){
        if (!pertenece(elemento)) {  //el elemento no pertenece al conjunto
           int cantidadelementosdelVNumeros=VNumeros.getDim();//20
            if (cant<cantidadelementosdelVNumeros) {
                cant++;//1
                int signo;
                if (elemento>0) {//-3>0
                    signo=0;//+
                }else{
                    signo=1;//-
                }
                VNumeros.Insertar(Math.abs(elemento), cant);
                Vsignos.Insertar(signo, cant);
                //System.out.println(VNumeros.toString());
                 //System.out.println(Vsignos.toString());
            }
        }
    }
                                  //-3
    public boolean pertenece(int elemento) {
        int signo;
        if (elemento>0) {
            signo=0;  //+
        }else{
            signo=1; //-
        }
        elemento=Math.abs(elemento);
                          //1
        for (int i = 0; i <=cant; i++) {
             if (elemento==VNumeros.Sacar(i+1) && signo==Vsignos.Sacar(i+1)) {  //3==3 && 1==1
                 
                 return true;
            }
 
        }
        return  false;
    }
    
    
    public String toString(){
        String Salida="C={";
        for (int i = 0; i < cant; i++) {
            String signo;  // signo=0;//+   signo=1;//-
            if (Vsignos.Sacar(i+1)==0) {
                signo="+";
            }else{
                signo="-";
            }
            Salida=Salida+" "+signo+VNumeros.Sacar(i+1);
            
            if (i<cant-1) {
                Salida=Salida+",";
            }
        }
        return Salida+"}";
    }
   
    public static void main(String[] args) {
        ConjuntoBits C=new ConjuntoBits();
        C.insertar(-3);
        System.out.println(C.toString());
      
    }

   


    
}
