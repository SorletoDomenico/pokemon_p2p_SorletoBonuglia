/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon_p2p;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sorleto_domenico
 */
public class JDatiCondivisiConnessione {

    private DatagramSocket s;
    public JPeer pNoi;
    public JPeer pAvversario;
    public ArrayList<JPokemon> listapokemon = new ArrayList<JPokemon>();
    public ArrayList<JMoves> listamosse = new ArrayList<JMoves>();

    public ArrayList<JPokemon> listapokemonSelezionati = new ArrayList<JPokemon>();
    public ArrayList<JPokemonLotta> listapokemonlotta = new ArrayList<JPokemonLotta>();

    public Boolean c;  //connessione
    public String[] temp;

    public Boolean turno;
    
    

    public JDatiCondivisiConnessione() {

        c = false;
        pNoi = new JPeer();
        pAvversario = new JPeer();
        pNoi.setPorte(667);
        pNoi.setindIp("localhost");

        try {
            s = new DatagramSocket(pNoi.getPorte());
        } catch (SocketException ex) {
            Logger.getLogger(JDatiCondivisiConnessione.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mandaConnessione(String ip, Integer port) throws SocketException, IOException {

        //controllo se è connesso
        if (c) {
            System.out.println("peer già connesso");
        } else {
            //manda
            byte[] data = new byte[1500];
            data = ("c;" + pNoi.getindIp() + ";" + pNoi.getPorte() + ";" + pNoi.getNome()).getBytes();
            DatagramPacket p = new DatagramPacket(data, data.length);

            p.setAddress(InetAddress.getByName(ip));
            p.setPort(port);
            s.send(p);

            //ricevi
            data = new byte[1500];
            p = new DatagramPacket(data, data.length);
            s.receive(p);

            //salvo i dati
            String[] vect;
            String str = new String(data);
            vect = str.split(";"); //c;ip;porta;nome

            //salvo dati
            pAvversario.setNome(vect[3]);
            pAvversario.setPorte(Integer.parseInt(vect[2]));
            pAvversario.setindIp(vect[1]);

            c = true;
        }

    }


    public void manda(String sa) throws UnknownHostException, IOException {
        byte[] mandaDati = (sa).getBytes();
        DatagramPacket p = new DatagramPacket(mandaDati, mandaDati.length);
        p.setAddress(InetAddress.getByName(pAvversario.getindIp()));
        p.setPort(pAvversario.getPorte());
        s.send(p);
    }

    public void riceviConnessione() throws SocketException, IOException {

        //controllo 
        if (c) {
            System.out.println("peer già connesso");
        } else {
            //ricevi
            byte[] data = new byte[1500];
            DatagramPacket p;
            String[] vect;

            data = new byte[1500];
            p = new DatagramPacket(data, data.length);
            s.receive(p);
            String str = new String(data);
            vect = str.split(";"); //c;ip;porta;nome

            //salvo dati
            pAvversario.setNome(vect[3]);
            pAvversario.setPorte(Integer.parseInt(vect[2]));
            pAvversario.setindIp(vect[1]);
            c = true;
            //manda
            data = new byte[1500];
            data = ("c;" + pNoi.getindIp() + ";" + pNoi.getPorte() + ";" + pNoi.getNome()).getBytes();
            p = new DatagramPacket(data, data.length);

            p.setAddress(InetAddress.getByName(pAvversario.getindIp()));
            p.setPort(pAvversario.getPorte());
            s.send(p);

            //System.out.println("Connesso");
        }

    }


    public void ricevi() throws IOException {
        byte[] riceviDati;
        String[] vect;
        String dividi;
        Boolean check = true; //controllare se la porta è uguale, quinid continuerà il ciclo do-while
        do {
            //ricevo il pacchetto
            riceviDati = new byte[1500];
            DatagramPacket p;
            p = new DatagramPacket(riceviDati, riceviDati.length);
            s.receive(p);
            //dividiamo il nostro pacchetto
            dividi = new String(riceviDati);
            vect = dividi.split(";");
            //condizione dove se la porta è uguale alla porta avversaria inseriamo i dati dentro temp
            if (Integer.parseInt(vect[1]) == pAvversario.getPorte()) {
                temp = vect;
                check = false; //in questo caso uscirà dal do-while 
            }
        } while (check);
    }

    //parte gioco
    public void caricadaJson() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("pokemon.json"));
        JSONArray am = (JSONArray) parser.parse(new FileReader("moves.json"));
        ArrayList<JPokemon> temp1 = new ArrayList<JPokemon>();
        ArrayList<JMoves> temp2 = new ArrayList<JMoves>();

        for (Object obj : a) {
            // obj = parser.parse(new FileReader("pokemon.json"));
            JSONObject Pokemon = (JSONObject) obj;

            Integer id = Integer.parseInt((String) Pokemon.get("id"));
            String name = (String) Pokemon.get("name");
            String type1 = (String) Pokemon.get("type1");
            String type2 = (String) Pokemon.get("type2");
            Integer hp = Integer.parseInt((String) Pokemon.get("HP")) * 3;
            String description = (String) Pokemon.get("description");
            String sprite = (String) Pokemon.get("sprite");
            String hires = (String) Pokemon.get("hires");
            JPokemon p = new JPokemon(id, name, type1, type2, hp, description, sprite, hires);
            temp1.add(p);
        }

        for (Object obj : am) {
            JSONObject Move = (JSONObject) obj;

            Integer accuracy = Integer.parseInt((String) Move.get("accuracy"));
            String ename = (String) Move.get("ename");
            Integer id = Integer.parseInt((String) Move.get("id"));
            Integer power = Integer.parseInt((String) Move.get("power"));
            Integer pp = Integer.parseInt((String) Move.get("pp"));
            String type = (String) Move.get("type");
            JMoves m = new JMoves(accuracy, ename, id, power, pp, type);
            temp2.add(m);
        }

        listapokemon = temp1;
        listamosse = temp2;
    }

    public void RandomizeMoves() {
        //assegnare 4 mosse a caso ai pokemon scelti per combattere che possono essere solo o normale o del suo tipo
        Random r = new Random();
        ArrayList<JMoves> mossedelpokemon = new ArrayList<JMoves>();
        ArrayList<JMoves> temp;

        int contN = 0;  //contatore mosse normali
        int contPrimoTipo = 0; //contatore mosse primo tipo
        int contSecondoTipo = 0; //contatore mosse Secondo tipo

        for (int i = 0; i < getListapokemonSelezionati().size(); i++) {
            temp = new ArrayList<JMoves>();
            for (int x = 0; x < getListamosse().size(); x++) {
                if ((getListapokemonSelezionati().get(i).type1.equals(getListamosse().get(x).type) || getListamosse().get(x).type.equals(getListapokemonSelezionati().get(i).type2) || getListamosse().get(x).type.equals("Normal")) && !getListamosse().get(x).power.equals(0)) {
                    mossedelpokemon.add(getListamosse().get(x)); //mosse che quel pokemon può imparare
                }
            }
            for (int mossa = 0; mossa < 4; mossa++) {
                JMoves mossaPokemon = mossedelpokemon.get(r.nextInt(mossedelpokemon.size()));  //prende mossa random da mossepokemon
                Boolean aggiunto = false;
                if (getListapokemonSelezionati().get(i).type1.equals("Normal") && getListapokemonSelezionati().get(i).type2.equals("")) { //è solo di tipo normale
                    temp.add(mossaPokemon);
                    aggiunto = true;
                } else {
                    if (getListapokemonSelezionati().get(i).type1.equals("Normal") && !getListapokemonSelezionati().get(i).type2.equals("")) {//sarà un pokemon di tipo normale e un altro tipo
                        if (!temp.contains(mossaPokemon) && mossaPokemon.type.equals("Normal") && contN < 2) {
                            temp.add(mossaPokemon);
                            contN++;
                            aggiunto = true;

                        }
                        if (!temp.contains(mossaPokemon) && !mossaPokemon.type.equals("Normal") && contSecondoTipo < 2) {
                            temp.add(mossaPokemon);
                            contSecondoTipo++;
                            aggiunto = true;
                        }

                    } else {
                        if (!temp.contains(mossaPokemon) && mossaPokemon.type.equals("Normal") && contN < 2) {
                            temp.add(mossaPokemon);
                            contN++;
                            aggiunto = true;
                        }

                        if (getListapokemonSelezionati().get(i).type2.equals("") && !mossaPokemon.type.equals("Normal")) {  // il pokemon ha un solo tipo
                            if (!temp.contains(mossaPokemon) && contPrimoTipo < 2) {
                                temp.add(mossaPokemon);
                                contPrimoTipo++;
                                aggiunto = true;
                            }
                        } else {
                            //in questo caso il pokemon ha 2 tipi
                            if (!temp.contains(mossaPokemon) && !mossaPokemon.type.equals("Normal") && ((contPrimoTipo < 1 && contSecondoTipo < 1) || (contPrimoTipo == 1 && contSecondoTipo == 0 && mossaPokemon.type.equals(getListapokemonSelezionati().get(i).type2)) || (contPrimoTipo == 0 && contSecondoTipo == 1 && mossaPokemon.type.equals(getListapokemonSelezionati().get(i).type1)))) {

                                temp.add(mossaPokemon);
                                aggiunto = true;
                                if (mossaPokemon.type.equals(getListapokemonSelezionati().get(i).type1)) {
                                    contPrimoTipo++;
                                } else {
                                    contSecondoTipo++;
                                }

                            }

                        }
                    }
                }

                if (aggiunto == false) {
                    mossa--;
                }
            }
            JPokemonLotta p = new JPokemonLotta(getListapokemonSelezionati().get(i), temp.get(0), temp.get(1), temp.get(2), temp.get(3));
            listapokemonlotta.add(p);
            contN = 0;
            contPrimoTipo = 0;
            contSecondoTipo = 0;
            mossedelpokemon.clear();
        }
    }

    public JPokemon searchPokemon(int idAvversario) {
        JPokemon p = null;
        for (JPokemon p2 : listapokemon) {
            if (p2.id.equals(idAvversario)) {
                p = p2;
            }
        }
        return p;
    }

    public JMoves searchMossa(int idAvversario) {
        JMoves m = null;
        for (JMoves m2 : listamosse) {
            if (m2.id.equals(idAvversario)) {
                m = m2;
            }
        }
        return m;
    }

    public void ChiInizia() throws IOException {

        Random r = new Random();
        Integer n;
        boolean finito = false;
        do {
            n = r.nextInt(2);
            manda("t;" + pNoi.getPorte().toString() + ";" + n.toString());
            ricevi();
            if ("t".equals(temp[0])) {
                if (n == 1 && Integer.parseInt(temp[2].trim()) == 0) {
                    turno = true;
                    finito = true;
                } else if (n == 0 && Integer.parseInt(temp[2].trim()) == 1) {
                    turno = false;
                    finito = true;
                }
            }
        } while (!finito);

    }

    public ArrayList<JPokemon> getListapokemon() {
        return listapokemon;
    }

    public ArrayList<JMoves> getListamosse() {
        return listamosse;
    }

    public ArrayList<JPokemon> getListapokemonSelezionati() {
        return listapokemonSelezionati;
    }

    public ArrayList<JPokemonLotta> getListapokemonlotta() {
        return listapokemonlotta;
    }

    public JPeer getpNoi() {
        return pNoi;
    }

    public JPeer getpAvversario() {
        return pAvversario;
    }

    public Boolean getC() {
        return c;
    }

    public String[] getTemp() {
        return temp;
    }

    public void setTemp(String[] temp) {
        this.temp = temp;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }
    
    

}
