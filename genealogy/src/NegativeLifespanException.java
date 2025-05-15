public class NegativeLifespanException extends Exception {
    public NegativeLifespanException(Person person) {
        super("Integrity error\nDetected a person with negative lifespan --> "+person.toString());
    }
}
