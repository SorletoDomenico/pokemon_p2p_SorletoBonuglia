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

/**
 *
 * @author sorleto_domenico
 */
public class JDatiCondivisi {
    private Integer portaPeer;
    private String ipPeer;
    //private String ip;
   // private Integer port;
    
     ArrayList<List> listapokemon = new <List>ArrayList();
    ArrayList<List> listamosse = new <List>ArrayList();
    
    public JDatiCondivisi(){
    
    }
    public void manda_connessione(String ip, Integer port) throws SocketException, IOException{
        
        byte[] data = new byte[1500];
        data = ("c;" + ip + ";" + port + ";").getBytes();
        DatagramPacket p = new DatagramPacket(data, data.length);
        DatagramSocket s = new DatagramSocket();
        p.setAddress(InetAddress.getByName(ip));
        p.setPort(port);
        s.send(p);
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
