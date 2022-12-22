package pac;

import java.util.ArrayList;
import java.util.List;


/**
 * The Pattern class contains the coordinates and the label as well as methods to interact with them
 * 
 * @author Carlos Simões
 * @author Catarina T. Fonseca
 */
public class Pattern implements IPattern {
	ArrayList<Double> coordinates = new ArrayList<Double>();
	int label;
	
	public Pattern(double d1, double d2, int label) {
		this.addCoordinate(d1);
		this.addCoordinate(d2);
		this.setLabel(label);
	}

	/**
	 * Returns the coordinates from an instance of the class
	 * 
	 * @ensures the list of coordinates is returned
	 */
	@Override
	public List<Double> getCoordinates() {
		return this.coordinates;
	}

	/**
     * Computes the Euclidean distance between the instance and another pattern.
     * 
     * @param other
     * @ensures the correct Euclidean distance between two Patterns is returned
     */
	@Override
	public double distance(IPattern other) {
		double x1 = this.getCoordinates().get(0);
		double y1 = this.getCoordinates().get(1);
		double x2 = other.getCoordinates().get(0);
		double y2 = other.getCoordinates().get(1);
		
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	/**
     * Adds a coordinate to the Pattern
     * 
     * @param d
     * @ensures the coordinate is added to the Pattern
     */
	@Override
	public void addCoordinate(double d) {
		this.coordinates.add(d);
	}

	/**
     * Adds a label to the Pattern
     * 
     * @param label
     * @ensures the label is added to the Pattern
     */
	@Override
	public void setLabel(int label) {
		this.label = label;
	}

	/**
     * Gets the labels of a Pattern
     * 
     * @return the label of a Pattern
     */
	@Override
	public int getLabel() {
		return this.label;
	}
	
	
	/**
     * Overrides the toString method to print the coordinates of a Pattern
     */
	@Override
	public String toString() {
		return "[" + this.coordinates.get(0) + ", " + this.coordinates.get(1) + "]";
	}
	
}
