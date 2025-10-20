/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import java.io.Serializable;

/**
 *
 * @author ahmet
 */
public class SessionUser implements Serializable  {
    private String benutzerkennung;
    private String rolle;

    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
    
    public boolean berechtigung(String page){
        
     return false;    
    }    
    

}
