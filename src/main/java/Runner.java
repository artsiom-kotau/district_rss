import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        Map<Some, String> map = new HashMap<Some, String>();
        map.put(new Some(1,"1"), "1");
        map.put(new Some(2,"2"), "2");
        map.put(new Some(3,"3"), "3");
        map.put(new Some(4,"4"), "4");
        System.out.println(map.get(new Some(3)));
    }

    static class Some {
        int id;
        String name;

        public Some(int id) {
            this.id = id;
        }

        public Some(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return id % 2;
        }

        @Override
        public String toString() {
            return "Some{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
