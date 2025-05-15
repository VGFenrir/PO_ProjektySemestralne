public class AmbiguousPersonException extends Exception{
    public AmbiguousPersonException(Person person){
        super("Integrity error\nDetected multiple people with the same name and surname --> "+person.toString());
    }
}
