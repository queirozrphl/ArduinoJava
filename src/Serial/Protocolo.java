/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

/**
 *
 * @author Raphael Queiroz
 */
public class Protocolo {
   private String tipoDado;
   private String Temperatura;
   private String leituraComando;
   
   private void interpretaComando(){
       
       //separa a string de comando em substrings, caso eu receba mais informações
       String aux[] = leituraComando.split("");
       if(aux.length == 1){

       Temperatura = aux[0];
   }}

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String Temperatura) {
        this.Temperatura = Temperatura;
    }

    public String getLeituraComando() {
        return leituraComando;
     
    }

    public void setLeituraComando(String leituraComando) {
        this.leituraComando = leituraComando; //string de dados
        this.interpretaComando(); //interpretamos a string
    }
   
   
}
