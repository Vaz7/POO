import java.util.Iterator;
import java.util.Set;

public class SetDeepClone {
    public static boolean isDeepClone(Set<Artigo> set1, Set<Artigo> set2) {
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
}
