import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Family {
    private Map<String, List<Person>> map;

    public Family(){
        this.map = new HashMap<>();
    }

    public void add(List<Person> people){
        people.forEach((e)->{
            this.map.putIfAbsent(e.getName()+" "+e.getSurname(),new ArrayList<Person>(people));
        });
    }
    public Person[] get(String key){
        if(this.map.isEmpty()) return null;
        List<Person> result = this.map.get(key);
        result.sort((e1,e2)->Person.comparator(e1,e2));
        return result.toArray(new Person[1]);
    }
}
