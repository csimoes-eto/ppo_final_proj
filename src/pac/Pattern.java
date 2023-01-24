package pac;

import java.util.ArrayList;
import java.util.List;

/**
 * The Pattern class contains the coordinates and the label as well as methods
 * to interact with them
 * 
 * @author Carlos Simões
 * @author Catarina T. Fonseca
 */
public class Pattern implements IPattern {
	ArrayList<Double> coordinates = new ArrayList<Double>();
	int label;

	public Pattern(ArrayList<Double> coordinates, int label) {
		coordinates.forEach((d) -> this.addCoordinate(d));
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
		double sum = 0;

		for (int i = 0; i < this.getCoordinates().size(); i++) {
			double d1 = this.getCoordinates().get(i);
			double d2 = other.getCoordinates().get(i);
			
			sum += Math.pow((d1 - d2), 2);
		}

		return Math.sqrt(sum);
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
		String s = "[";

		for (int i = 0; i < this.coordinates.size(); i++) {
			Double val = this.coordinates.get(i);

			if (i != 0) {
				s += " ";
			}

			if (val >= 10) {
				s += String.format("%.4f", val);
			} else {
				s += String.format("%.5f", val);
			}

			if (i != this.coordinates.size() - 1) {
				s += ",";
			}
		}

		return s + "]";
	}

}
