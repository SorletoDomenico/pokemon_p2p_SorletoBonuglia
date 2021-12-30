/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon_p2p;

/**
 *
 * @author Domen
 */
public class JPeer {

    private String nome, indIp;
    private Integer porte;

    public JPeer() {
    }

    public JPeer(String nome, String indIp, Integer porte) {
        this.nome = nome;
        this.indIp = indIp;
        this.porte = porte;
    }

    public Integer getPorte() {
        return porte;
    }

    public void setPorte(Integer porte) {
        this.porte = porte;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getindIp() {
        return indIp;
    }

    public void setindIp(String indIp) {
        this.indIp = indIp;
    }
    

}
