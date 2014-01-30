package hello;

 
public class Hello{
   
    public static void main(String args[]){
       
        
       
        /* String to be split */        
        String str = "Dragos Gheorghe Vasile";
        String[] temp;
       
        /* Delimiter */
        String delimiter = " ";
       
        /* given string will be split by the argument delimiter provided. */
        temp = str.split(delimiter);
       
        /* print split substrings */        
        for(int i =0; i < temp.length ; i++)        
            System.out.println("Bonjour " +temp[i]);
       
        
       
        
       
  }
 
}
 
/*
OUTPUT of the above given Java String split Example would be:
Bonjour Dragos
Bonjour Gheorghe
Bonjour Vasile

*/