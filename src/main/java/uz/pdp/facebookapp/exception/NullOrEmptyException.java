package uz.pdp.facebookapp.exception;

public class NullOrEmptyException extends RuntimeException {
    public NullOrEmptyException(String val){
        super(val + " null or empty");
    }
}
