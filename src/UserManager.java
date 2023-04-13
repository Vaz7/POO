import jdk.jshell.execution.Util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserManager{
    public Map<Integer, Utilizador> users;
    public UserManager(){
        this.users = new HashMap<>();
    }

    public UserManager(Map<Integer, Utilizador> a){
        this.users = new HashMap<>();
        for(Map.Entry<Integer, Utilizador> entry: a.entrySet()){
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

    public UserManager(UserManager a){
        this.users = a.getUsers();
    }

    public Map<Integer, Utilizador> getUsers() {
        Map<Integer, Utilizador> novo = new HashMap<>();
        for(Map.Entry<Integer, Utilizador> c : this.users.entrySet()){
            Integer aux = c.getKey();
            Utilizador use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setUsers(Map<Integer, Utilizador> a) {
        this.users = new HashMap<>();
        for(Map.Entry<Integer, Utilizador> c : a.entrySet()){
            Integer aux = c.getKey();
            Utilizador use = c.getValue().clone();
            this.users.put(aux,use);
        }
    }

    public void addUser(Utilizador a){
        this.users.put(a.getCode(), a.clone());
    }

    public UserManager clone()
    {
        return new UserManager(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Utilizador> c : this.users.entrySet()){
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

}
