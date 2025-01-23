package pw.telm.telmbackend.exception;

import org.aspectj.weaver.ast.Not;

public class NotTextException extends RuntimeException{
    public NotTextException(String message){
        super(message);
    }
}
