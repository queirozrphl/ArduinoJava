/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaard;

import Serial.SerialRxtx;

/**
 *
 * @author Raphael Queiroz
 */
public class JavaArd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SerialRxtx serial = new SerialRxtx();
        
        if(serial.iniciarSerial()){
        while(true){
        
        }
        }else{}
        
        // TODO code application logic here
    }
    
}
