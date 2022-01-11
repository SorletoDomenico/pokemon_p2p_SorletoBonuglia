/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_p2p;

import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class JType {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImmunes() {
        return immunes;
    }

    public void setImmunes(ArrayList<String> immunes) {
        this.immunes = immunes;
    }

    public ArrayList<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(ArrayList<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public ArrayList<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(ArrayList<String> strengths) {
        this.strengths = strengths;
    }

    public JType(String name, ArrayList<String> immunes, ArrayList<String> weaknesses, ArrayList<String> strengths) {
        this.name = name;
        this.immunes = immunes;
        this.weaknesses = weaknesses;
        this.strengths = strengths;
    }

   
    String name;
    ArrayList<String> immunes;
    ArrayList<String> weaknesses;
    ArrayList<String> strengths;

   
    
     
    
}
