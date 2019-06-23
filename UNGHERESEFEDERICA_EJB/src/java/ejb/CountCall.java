
package ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Federica
 */
@Interceptor
public class CountCall {
    /*
    -Modifica: le seguenti variabili sono state dichiarate statiche.
    -E' stata aggiunta l'inizializzazione e la dichiarazione di countMax.
    -E' stata aggiunta la dichiarazione e l'inizializzazione della variabile metodo.
    -E' stato aggiunto un metodo per aggiornare il nome ed il conteggio del metodo chiamato più volte 
    */
    private static String max="";
    private static int countID=0, countReg=0, countAll=0, countCrea=0, countCanc=0, countCambia=0, countMax=0;
    private static String metodo;
    @AroundInvoke
    public Object conta(InvocationContext ic){
        metodo = ic.getMethod().getName();
        System.out.println("Metodo invocato: "+metodo+"N.invocazioni: ");
        switch(metodo){
            case "creaCircolo":
                countCrea++;
                System.out.println(countCrea);
                cambiaMax(countCrea);
                break;
            case "cancellaCircolo":
                countCanc++;
                System.out.println(countCanc);
                cambiaMax(countCanc);
                break;
            case "cambiaCircolo":
                countCambia++;
                System.out.println(countCambia);
                cambiaMax(countCambia);
                break;
            case "getAllCircoli":
                countAll++;
                System.out.println(countAll);
                cambiaMax(countAll);
                break;
            case "getCircoliByRegione":
                countReg++;
                System.out.println(countReg);
                cambiaMax(countReg);
                break;
            case "getCircoloById":
                countID++;
                System.out.println(countID);
                cambiaMax(countID);
                break;    
        }
        //Fine dello switch per incrementare il conto delle chiamate e trovare il metodo col massimo numero
         
        try{
            //Stampa metodo col massimo numero di chiamate
            System.out.println("\n"+max+" e' il metodo chiamato piu' volte: "+countMax);
            return ic.proceed();
        }
        catch(Exception e){
            return null;
        }
    }
    
    private void cambiaMax(int count){
        if(countMax < count){
            countMax = count;
            max = metodo;
        }
    }
}