import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, NegativeLifespanException,ParentingAgeException {
        Person Ozjasz = new Person("Ozjasz", "Goldberg", LocalDate.parse("1942-07-27"));
        Person Adam = new Person("Adam", "Goldberg", LocalDate.parse("1956-07-27"));
        Person Grzegorz = new Person("Grzegorz", "Goldberg", LocalDate.parse("1957-07-27"));
        Person Karol = new Person("Karol", "Goldberg", LocalDate.parse("1982-07-27"));
        Person Mateo = new Person("Mateo", "Goldberg", LocalDate.parse("2012-07-27"));
        Person Kacper = new Person("Kacper", "Goldberg", LocalDate.parse("2001-07-27"));

        Ozjasz.adopt(Adam);
        Ozjasz.adopt(Grzegorz);
        Ozjasz.adopt(Karol);
        Ozjasz.adopt(Kacper);
        Ozjasz.adopt(Mateo);

        List<Person> people = Person.fromCsv("family.csv");

        people.forEach((e)->{
            System.out.println(e.getChildren());
        });
    }
}
