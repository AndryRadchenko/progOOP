import java.util.ArrayList;
import java.util.List;

public class Flats {
    private List<Flat> flatsList = new ArrayList<>();

    public Flats(List<Flat> flatsList) {
        this.flatsList = flatsList;
    }

    public Flats() {
    }

    public List<Flat> getFlatsList() {
        return flatsList;
    }

    public void setFlatsList(List<Flat> flatsList) {
        this.flatsList = flatsList;
    }
}
