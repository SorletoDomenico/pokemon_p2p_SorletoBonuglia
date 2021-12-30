/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon_p2p;

import java.awt.List;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sorleto_domenico
 */
public class JDatiCondivisiConnessione {

    private DatagramSocket s;
    private JPeer pNoi;
    private JPeer pAvversario;

    ArrayList<List> listapokemon = new <List>ArrayList();
    ArrayList<List> listamosse = new <List>ArrayList();

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
}

//    
//    
//
//    //metodo leggi da file json
//    public JDatiCondivisi() {
//
//    }
//    public void readFromJson() {
//        JSONArray a = (JSONArray) parser.parse(new FileReader("c:\\exer4-courses.json"));
//        
//        for (Object o : a) {
//            JSONObject person = (JSONObject) o;
//
//            String name = (String) person.get("name");
//            System.out.println(name);
//
//            String city = (String) person.get("city");
//            System.out.println(city);
//
//            String job = (String) person.get("job");
//            System.out.println(job);
//
//            JSONArray cars = (JSONArray) person.get("cars");
//
//            for (Object c : cars) {
//                System.out.println(c + "");
//            }
//        }

//    }
