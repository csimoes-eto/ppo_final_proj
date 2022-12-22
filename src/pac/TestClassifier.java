package pac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class TestClassifier {

    private static final String ref = "data/peixes-referencia.dat";
    private static final String aval = "data/peixes-aval.dat";

    public static void main(String[] args) throws FileNotFoundException {

        DataSet as = DataSet.load(new File(aval));
        Classifier fishClassifier = new Classifier(ref);
        List<Integer> k1 = fishClassifier.firstNearestNeighbor(as);
        double k1Error = fishClassifier.errorPercentage(as, k1);
        int i = 0;
        for (IPattern p : as) {
            System.out.println(p + " -> " + k1.get(i) + " {" + p.getLabel() + "}");
            i++;
        }
        System.out.println("k = 1. Error: " + k1Error);
        List<Integer> k3 = fishClassifier.kNearestNeighbors(3, as);
        double k3Error = fishClassifier.errorPercentage(as, k3);
        System.out.println("k = 3. Error: " + k3Error);
        List<Integer> k5 = fishClassifier.kNearestNeighbors(5, as);
        double k5Error = fishClassifier.errorPercentage(as, k5);
        System.out.println("k = 5. Error: " + k5Error);
        List<Integer> k7 = fishClassifier.kNearestNeighbors(7, as);
        double k7Error = fishClassifier.errorPercentage(as, k7);
        System.out.println("k = 7. Error: " + k7Error);
    }
}
