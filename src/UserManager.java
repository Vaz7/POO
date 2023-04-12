import jdk.jshell.execution.Util;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
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

    public UserManager clone(){
        return new UserManager(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Utilizador> c : this.users.entrySet()){
            sb.append("Código User:: " + c.getKey() + "\n" + c.getValue().clone().toString() + "\n");
        }
        return sb.toString();
    }

    private boolean userManDeepClone(Map<Integer,Utilizador> a){
        if(this.users.size() != a.size()) return false;

        for (Map.Entry<Integer, Utilizador> entry : this.users.entrySet()) {
            Integer key = entry.getKey();
            Utilizador value1 = entry.getValue();
            Utilizador value2 = a.get(key);
            if (!a.containsKey(key) || !value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        UserManager l = (UserManager) o;
        return userManDeepClone(l.getUsers());
    }

}
