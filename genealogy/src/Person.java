import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Comparable{
    private String name, surname;
    private LocalDate date_of_birth;
    private Set<Person> children;

    public Person(String name, String surname, LocalDate date_of_birth){
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.children = new HashSet<>();
    }

    @Override
    public int compareTo(Object object) {
        Person element = (Person)object;
        if(this.date_of_birth.isBefore(element.date_of_birth)){
            return -1;
        }
        if(this.date_of_birth.isAfter(element.date_of_birth)){
            return 1;
        }
        return 0;
    }
    @Override
    public String toString(){
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public static int comparator(Person element1, Person element2) {
        if (element1.date_of_birth.isBefore(element2.date_of_birth)) {
            return -1;
        }
        if (element1.date_of_birth.isAfter(element2.date_of_birth)) {
            return 1;
        }
        return 0;
    }

    public boolean adopt(Person child){
        return this.children.add(child);
    }

    public Person getYoungestChild(){
        if(this.children.isEmpty()) return null;
        Person youngest_child = null;
        for (Person element : this.children) {
            if(youngest_child == null){
                youngest_child = element;
                continue;
            }
            if(element.compareTo(youngest_child) > 0){
                youngest_child = element;
            }
        }
        return youngest_child;
    }

    public List<Person> getChildren(){
        List<Person> list = new ArrayList<Person>();
        if(this.children.isEmpty()) return null;
        list.addAll(this.children);
        list.sort((e1,e2)-> e1.compareTo(e2));
        return list;
    }
}
