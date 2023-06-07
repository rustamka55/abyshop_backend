package kz.iitu.abyshop.exception;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String s) {
        super(s);
    }
}
