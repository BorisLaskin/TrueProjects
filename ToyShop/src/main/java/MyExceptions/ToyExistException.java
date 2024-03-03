package MyExceptions;

public class ToyExistException extends NonInterrupt{

    public ToyExistException(int id) {
        super("Такая игрушка уже существует в базе: id = " + id);
    }
}
