/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author Raphael Queiroz
 */
public class SerialRxtx implements SerialPortEventListener {  //vai ficar atenta as informações vindas da serial
    
    SerialPort serialPort = null;
    
    private Protocolo protocolo = new Protocolo(); //objeto gestao protocolo
    private String appName; //Nome da aplicação
    /**
     *
     * @param spe
     */
    
    private BufferedReader input; //leitura da serial
    private OutputStream output; //objeto escrita serial

    private static final int TIME_OUT = 1000;
    private static int DATA_RATE = 9600;
    
    private String serialPortName = "COM9";
    //vai nos retornar se temos dados entrando na serial, ou não
    public boolean iniciarSerial() {
        boolean status = false; 
        
        try {
            //obtem portas seriais do sistema
            CommPortIdentifier portID = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
            while (portID == null && portEnum.hasMoreElements()){
                CommPortIdentifier currPortID = (CommPortIdentifier) portEnum.nextElement();
                if(currPortID.getName().equals(serialPortName)){
                    serialPort = (SerialPort) currPortID.open(appName, TIME_OUT);
                    portID = currPortID;
                    System.out.println("Conectado em: " + currPortID.getName());
                    break; 
                }
            }
            
            if(portID == null || serialPort == null){
                return false;
            }
            
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, 
                                            SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            status = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }
    //metodo que envia dados para o arduino
    public void sendData(String data){
        try {
            output = serialPort.getOutputStream();
            output.write(data.getBytes());
        } catch (Exception e) {
            System.err.println(e.toString());
            //podemos exibir uma mensagem de erro
        }
    }
    //metodo para fechar porta serial
    public synchronized void close(){
        if(serialPort != null){
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
    
    @Override
    public void serialEvent(SerialPortEvent spe) {
        //metodo que administra a chegada de dados pela serial
        try {
            switch(spe.getEventType()){
                case SerialPortEvent.DATA_AVAILABLE:
                    if(input == null){
                        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                        }
                    if(input.ready()){
                    protocolo.setLeituraComando(input.readLine()); 
                    System.out.println("Chegou = " + protocolo.getLeituraComando());
                    }
                    break;
                default: 
                    break;
            }
        } catch (Exception e) {
        }
        
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public static int getDATA_RATE() {
        return DATA_RATE;
    }

    public static void setDATA_RATE(int DATA_RATE) {
        SerialRxtx.DATA_RATE = DATA_RATE;
    }

    public String getSerialPortName() {
        return serialPortName;
    }

    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }
    
}
