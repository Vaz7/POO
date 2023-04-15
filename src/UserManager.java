import jdk.jshell.execution.Util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;

public class UserManager{
    public Map<String, Utilizador> users;
    public UserManager(){
        this.users = new HashMap<>();
    }

    public UserManager(Map<String, Utilizador> a){
        this.users = new HashMap<>();
        for(Map.Entry<String, Utilizador> entry: a.entrySet()){
            this.users.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public Artigo findArtigo(String alfanum){
        for(Utilizador c : this.users.values()){

            Map<String,Artigo> artigos = c.getPara_vender();

            if(artigos.containsKey(alfanum)){
                return artigos.get(alfanum);
            }

        }
        return null;
    }
    public boolean existsArtigo(String alfanum){
        for(Utilizador c : this.users.values()){

            Map<String,Artigo> artigos = c.getPara_vender();

            if(artigos.containsKey(alfanum)){
                return true;
            }

        }
        return false;
    }

    public String whoSellsArtigo(String alfanum){
        for(Utilizador c : this.users.values()){

            Map<String,Artigo> artigos = c.getPara_vender();

            if(artigos.containsKey(alfanum)){
                return c.getNome();
            }

        }
        return null;
    }

    public boolean userExists(String nome){
        if(this.users.containsKey(nome)) return true;
        else return false;
    }
    public UserManager(UserManager a){
        this.users = a.getUsers();
    }

    public Map<String, Utilizador> getUsers() {
        Map<String, Utilizador> novo = new HashMap<>();
        for(Map.Entry<String, Utilizador> c : this.users.entrySet()){
            String aux = c.getKey();
            Utilizador use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }


    public void setUsers(Map<String, Utilizador> a) {
        this.users = new HashMap<>();
        for(Map.Entry<String, Utilizador> c : a.entrySet()){
            String aux = c.getKey();
            Utilizador use = c.getValue().clone();
            this.users.put(aux,use);
        }
    }

    public void addUser(Utilizador a){
        this.users.put(a.getUsername(), a.clone());
    }

    public UserManager clone()
    {
        return new UserManager(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Utilizador> c : this.users.entrySet()){
            sb.append("Código User:: " + c.getKey() + "\n" + c.getValue().clone().toString() + "\n");
        }
        return sb.toString();
    }


    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        UserManager l = (UserManager) o;
        return SetDeepClone.userManDeepClone(this.getUsers(),l.getUsers());
    }

    public void dumpToFile() {
        try {
            FileWriter writer = new FileWriter("userDump.txt");

            // Loop through the map and write each entry to the file
            for (Map.Entry<String, Utilizador> entry : users.entrySet()) {
                String key = entry.getKey();
                Utilizador value = entry.getValue();

                // Write the key and value to the file
                writer.write(value.getUsername() +", " + value.getNome() +", " + value.getEmail() +", " + value.getNif() + "\n");
                // You can modify the format or content of the output as needed
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    public Set<Artigo> getTshirts() {
        HashSet<Artigo> tshirts = new HashSet<Artigo>();

        for (Map.Entry<String, Utilizador> entry : this.users.entrySet()) {
            Utilizador use = entry.getValue().clone();

            for (Map.Entry<String, Artigo> artigoEntry : use.getPara_vender().entrySet()) {
                Artigo artigo = artigoEntry.getValue();
                if (artigo instanceof Tshirt) {
                    tshirts.add(artigo);
                }
            }
        }
        return tshirts;
    }





    public Set<Artigo> getMalas() {
        HashSet<Artigo> malas = new HashSet<Artigo>();

        for (Utilizador utilizador : this.users.values()) {
            for (Artigo artigo : utilizador.getPara_vender().values()) {
                if (artigo instanceof Mala) {
                    malas.add(artigo);
                }
            }
        }

        return malas;
    }

    public Set<Artigo> getSapatilhas() {
        HashSet<Artigo> sapatilhas = new HashSet<Artigo>();

        for (Utilizador utilizador : this.users.values()) {
            for (Artigo artigo : utilizador.getPara_vender().values()) {
                if (artigo instanceof Sapatilha) {
                    sapatilhas.add(artigo);
                }
            }
        }

        return sapatilhas;
    }

}
