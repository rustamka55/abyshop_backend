package kz.iitu.abyshop.exception;

public class AuthenticationFailedException extends IllegalArgumentException{
    public AuthenticationFailedException(String s) {
        super(s);
    }
}
