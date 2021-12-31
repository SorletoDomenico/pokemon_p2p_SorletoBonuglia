/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_p2p;

/**
 *
 * @author gabri
 */
public class JPokemonLotta {
    JPokemon pokemon;
    JMoves mossa1;
    JMoves mossa2;
    JMoves mossa3;
    JMoves mossa4;

    public JPokemonLotta(JPokemon pokemon, JMoves mossa1, JMoves mossa2, JMoves mossa3, JMoves mossa4) {
        this.pokemon = pokemon;
        this.mossa1 = mossa1;
        this.mossa2 = mossa2;
        this.mossa3 = mossa3;
        this.mossa4 = mossa4;
    }
    
    
    
    public JPokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(JPokemon pokemon) {
        this.pokemon = pokemon;
    }

    public JMoves getMossa1() {
        return mossa1;
    }

    public void setMossa1(JMoves mossa1) {
        this.mossa1 = mossa1;
    }

    public JMoves getMossa2() {
        return mossa2;
    }

    public void setMossa2(JMoves mossa2) {
        this.mossa2 = mossa2;
    }

    public JMoves getMossa3() {
        return mossa3;
    }

    public void setMossa3(JMoves mossa3) {
        this.mossa3 = mossa3;
    }

    public JMoves getMossa4() {
        return mossa4;
    }

    public void setMossa4(JMoves mossa4) {
        this.mossa4 = mossa4;
    }
}
