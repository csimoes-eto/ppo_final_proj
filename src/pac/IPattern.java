package pac;

import java.util.List;

public interface IPattern {
    
    /**
     * Returns a list of double that corresponds to the pattern's coordinates
     * @return
     */
    List<Double> getCoordinates();
    /**
     * Computes the Euclidean distance between the instance and another pattern.
     * @param other
     * @return 
     */
    double distance(IPattern other);
    /**
     * Adds a coordinate to the pattern. 
     * @param d
     */
    void addCoordinate(double d);
    /**
     * Sets the pattern's label.
     * @param label
     */
    void setLabel(int label);
    /**
     * Returns the pattern's label.
     * @return
     */
    int getLabel();
}
