/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon_p2p;

/**
 *
 * @author sorleto_domenico
 */
public class JPokemon {

    public JPokemon(Integer id, String name, String type1, String type2, Integer HP, String description, String sprite, String hires) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.HP = HP;
        this.description = description;
        this.sprite = sprite;
        this.hires = hires;
    }
    Integer id;
    String name;
    String type1;
    String type2;
    Integer HP;
    String description;
    String sprite;
    String hires;
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }
    
    public String toString(){
    return name+" "+HP;
    }
}
