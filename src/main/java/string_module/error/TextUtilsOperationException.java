package string_module.error;

public class TextUtilsOperationException extends Exception {

    public TextUtilsOperationException(String message) {
        super(message);
    }

    public static TextUtilsOperationException createFailureParsingException(
            Class<? extends Number> numberType) {
        return new TextUtilsOperationException(String.format("Failure " +
                "text parsing to %s", numberType.getSimpleName()));
    }

}
