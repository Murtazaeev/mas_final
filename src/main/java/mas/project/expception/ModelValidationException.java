package mas.project.expception;

public class ModelValidationException extends RuntimeException{
    public ModelValidationException(String message) {
        super(message);
    }
}
