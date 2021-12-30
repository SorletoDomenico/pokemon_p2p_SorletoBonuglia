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
public class JMoves {
    
    Integer accurancy;
    String ename;
    Integer id;
    Integer power;
    Integer pp;
    String type;
    
  public Integer getAccurancy() {
        return accurancy;
    }

    public void setAccurancy(Integer accurancy) {
        this.accurancy = accurancy;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String toString(){
    
    return ename+power;
    }
}
