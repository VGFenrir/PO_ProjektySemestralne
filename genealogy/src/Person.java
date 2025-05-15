import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Person implements Comparable{
    private String name, surname;
    private LocalDate date_of_birth;
    private Set<Person> children;
    private LocalDate date_of_decease;

    public Person(String name, String surname, LocalDate date_of_birth,LocalDate date_of_decease){
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.children = new HashSet<>();
        this.date_of_decease = date_of_decease;
    }

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
        return this.name+" "+this.surname+" "+this.date_of_birth+" "+this.date_of_decease;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateofBirth() {
        return date_of_birth;
    }

    public void setChildren(Set<Person> children) {
        this.children = children;
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
        list.sort((e1,e2) -> e1.compareTo(e2));
        return list;
    }

    public void checkIntegrity(List<Person> people) throws NegativeLifespanException, AmbiguousPersonException{
        if(this.date_of_decease != null && this.date_of_decease.isBefore(this.date_of_birth)) throw new NegativeLifespanException(this);
        String str;
        for(int i=0; i< people.size() ;i++){
            if(people.get(i).getName().equals(this.getName()) &&
                    people.get(i).getSurname().equals(this.getSurname())
            ) throw new AmbiguousPersonException(this);
        }
    }

    public void checkParentAge(Person child) throws ParentingAgeException {
        if(LocalDate.now().toEpochDay() - this.date_of_birth.toEpochDay() < 15*365 ||
                (this.date_of_decease != null && this.date_of_decease.isBefore(child.getDateofBirth()))){
            throw new ParentingAgeException(this);
        }
    }

    public static Person fromCsvLine(String CsvLine){
        String[] csv_line_split = CsvLine.split(",");
        String[] name_and_surname = csv_line_split[0].split(" ");
        LocalDate date_of_birth = LocalDate.parse(csv_line_split[1], DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        LocalDate date_of_decease = null;
        if(csv_line_split.length > 2 && !csv_line_split[2].isEmpty()) {
            date_of_decease = LocalDate.parse(csv_line_split[2], DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        }
        return new Person(name_and_surname[0],name_and_surname[1],date_of_birth,date_of_decease);
    }

    public void toBinaryFile(List<Person> people, String filename){
        //TODO
    }
    public void fromBinaryFile(String filename){
        //TODO
    }

    public static List<Person> fromCsv(String file_path) throws IOException, NegativeLifespanException,ParentingAgeException {
        try {
            BufferedReader file = new BufferedReader(new FileReader(file_path));
            List<Person> result = new ArrayList<>();
            String str;
            Person person;
            for (int i = 0; (str = file.readLine()) != null; i++) {
                if (i != 0) {
                    person = Person.fromCsvLine(str);
                    person.checkIntegrity(result);
                    result.add(person);
                    String[] str_split = str.split(",");
                    if(str_split.length > 3){
                        String[] name_and_surname = str_split[3].split(" ");
                        String[] name_and_surname2={};
                        if(str_split.length == 5) {
                            name_and_surname2 = str_split[4].split(" ");
                        }
                        for(Person parent : result){
                            if(parent.getName().equals(name_and_surname[0]) && parent.getSurname().equals(name_and_surname[1])) {
                                try{
                                    parent.checkParentAge(person);
                                }
                                catch (ParentingAgeException e){
                                    System.err.println(e);
                                    Scanner scanner = new Scanner(System.in);
                                    if(!scanner.nextLine().equals("Y")){
                                        continue;
                                    }
                                }
                                parent.adopt(person);
                            }
                            if(str_split.length == 5){
                                if(parent.getName().equals(name_and_surname2[0]) && parent.getSurname().equals(name_and_surname2[1])) {
                                    try{
                                        parent.checkParentAge(person);
                                    }
                                    catch (ParentingAgeException e){
                                        System.err.println(e);
                                        Scanner scanner = new Scanner(System.in);
                                        if(!scanner.nextLine().equals("Y")){
                                            continue;
                                        }
                                    }
                                    parent.adopt(person);
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }
        catch(IOException e){
            System.err.println("IOException --> "+e);
        }
        catch(NegativeLifespanException | AmbiguousPersonException e){
            System.err.println(e);
        }
        return null;
    }
}
