public class ParentingAgeException extends Exception{
    ParentingAgeException(Object object){
        super("Parenting age exception --> "+object);
    }
}
