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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
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
    private JPeer pNoi;
    private JPeer pAvversario;
    public ArrayList<JPokemon> listapokemon;

    public ArrayList<String> listapokemoninList;
    private Boolean c;  //connessione

    public JDatiCondivisiConnessione() {

        c = false;
        pNoi = new JPeer();
        pAvversario = new JPeer();
        pNoi.setPorte(666);

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
        }

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

    //parte gioco
    public void caricadaJson() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("pokemon.json"));
    

        for (Object obj : a) {
            // obj = parser.parse(new FileReader("pokemon.json"));
            JSONObject Pokemon = (JSONObject) obj;
           
            Integer id = Integer.parseInt((String) Pokemon.get("id"));
            String name = (String) Pokemon.get("name");
            String type1 = (String) Pokemon.get("type1");
            String type2 = (String) Pokemon.get("type2");
            Integer hp = Integer.parseInt((String) Pokemon.get("HP"));
            String description = (String) Pokemon.get("description");
            String sprite = (String) Pokemon.get("sprite");
            String hires = (String) Pokemon.get("hires");
            
        }
           
        listapokemon = a;
       
    }

    public ArrayList<JPokemon> getListapokemon() {
        return listapokemon;
    }
      public ArrayList<String> getListapokemoninList() {
        return listapokemoninList;
    }

    

}