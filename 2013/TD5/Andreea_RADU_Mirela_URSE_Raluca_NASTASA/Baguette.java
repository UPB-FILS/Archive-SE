
package V1;

class Baguette {
            int etat;
            static final int ETAT_LIBRE = 0;
            static final int ETAT_PRIS = 1;
            private boolean isUsed;
            
            
            public boolean isUsed(){
            return isUsed;
            }
            
            public void setUsed(boolean usedFlag){
                    isUsed=usedFlag;
            }
            
   
}