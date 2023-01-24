package pac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * The DataSet class represents a group of Patterns
 * 
 * @author Carlos Simões
 * @author Catarina T. Fonseca
 */
public class DataSet implements Iterable<IPattern> {
	ArrayList<IPattern> patterns = new ArrayList<IPattern>();

	/**
	 * Loads to memory a file containing a Pattern in each line
	 * 
	 * @param a file
	 * @requires each line of the used file two have a fish's measurements and it's label 
	 * @ensures a new DataSet is created (with the Patterns in the file) and
	 *          returned
	 * @throws FileNotFoundException
	 */
	public static DataSet load(File data) throws FileNotFoundException {
		Scanner file = new Scanner(new BufferedReader(new FileReader(data)));
		DataSet tempDst = new DataSet();

		while (file.hasNextLine() && file.hasNext()) {
			String[] lineVals = file.nextLine().trim().split("\\s+");
			ArrayList<Double> tempCoordinates = new ArrayList<Double>();
			int label = 0;

			for (String s : lineVals) {
				Double val = Double.valueOf(s);
				
				//check if can not be integer (dimension)
				if (val != Math.floor(val)) {
					tempCoordinates.add(val);
				} else {
					label = (int) Math.round(val);
				}
			}
						
			tempDst.patterns.add(new Pattern(tempCoordinates, label));
		}

		file.close();

		return tempDst;
	}

	/**
	 * Returns the Pattern stored in index i of the DataSet
	 * 
	 * @param int i, the index
	 * @requires i to be inferior to the size of the DataSet - 1 (to be in bounds)
	 * @ensures the Pattern in index i is returned
	 */
	public IPattern get(int i) throws IndexOutOfBoundsException {
		return this.patterns.get(i);
	}

	/**
	 * Returns all the different classes (labels) in a DataSet
	 * 
	 * @ensures all the labels are returned without repetitions
	 */
	public Set<Integer> getClasses() {
		Set<Integer> labels = new HashSet<Integer>();

		for (IPattern p : this.patterns) {
			labels.add(p.getLabel());
		}

		return labels;
	}

	/**
	 * Overrides the iterator method by calling the iterator of the ArrayList of
	 * Patterns
	 */
	@Override
	public Iterator<IPattern> iterator() {
		return this.patterns.iterator();
	}
}
