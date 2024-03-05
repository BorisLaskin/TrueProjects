package View;

import model.Toy;
import java.io.File;
import java.util.List;

public abstract class AbstractView {
    File file;
    public abstract void printLotteryResults();
    public abstract void writeResults();
    public abstract void getResults(List<Toy> list);
    public abstract int getCountToPrise();

}
