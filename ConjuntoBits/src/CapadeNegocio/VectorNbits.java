package CapadeNegocio;

import java.util.Arrays;

public class VectorNbits {
   int V[];
   int dim;
   int nbits;
                       
//--------------------Constructor-----------------------------------------------      
 public VectorNbits(int cant,int nbi){
   int NE=(cant*nbi)/32;
   if ((cant*nbi)%32!=0)
       NE++;
   V=new int[NE];
   dim=cant;
   nbits=nbi;
 }
                                  
 //---------------------Setters-------------------------------------------------
 public void Insertar(int ele,int pos){
     if(pos<=dim && pos>0){  
         int ele1=ele;  
         int Nbit=CalcularNbit(pos);
         int Nent=CalcularNent(pos);
         int Mask=(int)(Math.pow(2,nbits)-1);
         Mask=Mask<<Nbit;
         Mask=~Mask;        
         V[Nent]=V[Nent]& Mask; //limpiamos
         ele=ele<<Nbit;       
         V[Nent]=V[Nent] | ele;
         if(Nbit+nbits>32){
             int Mask1=(int)(Math.pow(2,nbits)-1);
             Mask1=Mask1>>>(32-Nbit);
             Mask1= ~Mask1;
             V[Nent+1]=V[Nent+1] & Mask1;
             ele1=ele1>>>(32 - Nbit);
             V[Nent+1]=V[Nent+1] | ele1;
         }
   }
 }

 public void Redimensionar(int Cant){ 
   int NE=(Cant*nbits)/32;
   if ((Cant*nbits)%32!=0)
       NE++;  
   V=Arrays.copyOf(V,NE);
   dim=Cant;
 }
 
//----------------------Getters------------------------------------------------
 public int Sacar(int pos){
    int Nbit=CalcularNbit(pos);
    int Nent=CalcularNent(pos);
    int Mask=(int)(Math.pow(2,nbits)-1);
      Mask=Mask<<Nbit;
      Mask=Mask & V[Nent];
      Mask=Mask>>>Nbit;
      if(Nbit+nbits>32){
          int Mask1=(int)(Math.pow(2,nbits)-1);
          Mask1=Mask1>>>(32-Nbit);
          Mask1=Mask1 & V[Nent+1]; 
          Mask1=Mask1<<(32-Nbit);
          Mask=Mask | Mask1;
      }
      //System.out.println("mask= "+Integer.toBinaryString(Mask));
   return Mask;   
     
 }
 
public int valormax(){
  int x=(int)(Math.pow(2,nbits)-1); 
  return x;   
} 
public int getDim(){
  return dim;
}

public int getleng(){
  return V.length;
}
 
public int ValorMax(){
  return (int)(Math.pow(2,nbits)-1);
}

public int getnbits(){
  return nbits;
}

 
//-----------------------Extras-------------------------------------------------
public int CalcularNbit(int pos){
  return (((pos-1)*nbits)%32 );
}

public int CalcularNent(int pos){
  return (((pos-1) *nbits)/32);
}
    
@Override
public String toString() {
 String S="V[";
     for (int i=1; i<=dim; i++) {
          //S=S+Sacar(i);
           S=S+Integer.toBinaryString(Sacar(i));
          if(i<dim)
             S=S+",";
     }
     S=S+"]";
 return S;
}  
   
    public static void main(String[] args) {
        VectorNbits V=new VectorNbits(20,5);
        V.Insertar(31,7);
        V.Insertar(31,20);
        System.out.println(V);
    } 
    //edad [0..120] 7bits   sexo =[0.1] 1bits uv=[0..24] 5bits 
  //int V[000000000000000000000000000001010,6,9,12,32,....]
}
