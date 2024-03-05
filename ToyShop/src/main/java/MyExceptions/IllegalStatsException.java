package MyExceptions;

public class IllegalStatsException extends NonInterrupt{
    public IllegalStatsException(int size1, int size2) {
        super(String.format("Размер инициализации = %d, а количество toyTank = %d", size1, size2));
    }
    public IllegalStatsException(double sum){
        super(String.format("Сумма статов = %2f, а должна быть 1",sum));
    }
}
