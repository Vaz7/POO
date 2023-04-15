import java.util.Iterator;
import java.util.Set;
import java.util.Map;
public class SetDeepClone {
    public static boolean isDeepCloneSet(Set<Artigo> set1, Set<Artigo> set2) {
        if (set1 == null || set2 == null) {
            return set1 == set2;
        }

        if (set1.size() != set2.size()) {
            return false;
        }
        Iterator<Artigo> it = set1.iterator();
        while (it.hasNext()) {
            Artigo obj1 = it.next();
            boolean found = false;
            for (Artigo obj2 : set2) {
                if (obj2.equals(obj1)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }


public static boolean isDeepCloneMap(Map<String, Artigo> map1, Map<String, Artigo> map2) {

    if (map1 == null || map2 == null) {
            return map1 == map2;
        }

        if (map1.size() != map2.size()) {
            return false;
        }

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                return false;
            }

            Artigo obj1 = map1.get(key);
            Artigo obj2 = map2.get(key);

            if (!obj1.equals(obj2)) {
                return false;
            }
        }

        return true;
    }

    public static boolean userManDeepClone(Map<String,Utilizador> b, Map<String,Utilizador> a){
        if(b.size() != a.size()) return false;

        for (Map.Entry<String, Utilizador> entry : b.entrySet()) {
            String key = entry.getKey();
            Utilizador value1 = entry.getValue();
            Utilizador value2 = a.get(key);
            if (!a.containsKey(key) || !value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean ArtigoUserManagerDeepClone(Map<String, Integer> b, Map<String, Integer> a){
        if(b.size() != a.size()) return false;

        for (Map.Entry<String, Integer> entry : b.entrySet()) {
            String key = entry.getKey();
            Integer value1 = entry.getValue();
            Integer value2 = a.get(key);
            if (!a.containsKey(key) || value1 != value2) {
                return false;
            }
        }
        return true;
    }

}



