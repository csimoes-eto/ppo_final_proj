package pac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains the methods for classification of the Patterns
 * 
 * @author Carlos Simões
 * @author Catarina T. Fonseca
 */
public class Classifier extends DataSet {
	DataSet referenceSet;
	
	
	/**
	 * Constructor that receives a string with the path to a file
	 * and calls the other constructor in the class passing on a 
	 * File object
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public Classifier (String path) throws FileNotFoundException {
		this(new File(path));
	}
	
	/**
	 * Constructor that receives a file and passes it on to the load 
	 * method of the super class
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public Classifier (File file) throws FileNotFoundException {
		this.referenceSet = super.load(file);
	}
	
	
	/**
	 * Returns the closest (by Euclidean distance) Pattern from the reference set to the one given
	 * 
	 * @param p - a Pattern to classify;
	 */
	public int firstNearestNeighbor(IPattern p) {
		int nearestLabel = 0;
		double auxDistance = Double.MAX_VALUE;
		
		for(IPattern pat : this.referenceSet.patterns) {
			double newDistance = pat.distance(p);
			
			if(newDistance < auxDistance) {
				auxDistance = newDistance;
				nearestLabel = pat.getLabel();
			}
		}
		
		return nearestLabel;
	}
	
	/**
	 * Returns a list of labels. Each represents the closest (by Euclidean distance)
	 * Pattern in the reference set to the one of the Patterns in the given parameter
	 * 
	 * @param ds - a DataSet whose Patterns we will classify;
	 */
	public ArrayList<Integer> firstNearestNeighbor(DataSet ds) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(IPattern pat: ds.patterns) {
			temp.add(this.firstNearestNeighbor(pat));
		}
		
		return temp;
	}
	
	/**
	 * Returns a predicted value to a Pattern's label considering
	 * its k nearest neighbors by Euclidean Distance
	 * 
	 * @param k - the number of neighbors to consider;
	 * @param p - the pattern we want to classify;
	 */
	public int kNearestNeighbors(int k, IPattern p) {
		ArrayList<Integer> labels = new ArrayList<Integer>();
		ArrayList<Double> distances = new ArrayList<Double>();
				
		for(int i = 0; i < this.referenceSet.patterns.size(); i++) {
			IPattern pat = this.referenceSet.patterns.get(i);
			double dist = pat.distance(p);
			int lab = pat.getLabel();

			if(i < k) {
				labels.add(lab);
				distances.add(dist);
			} 
			else {
				try {
					double biggestDist = Collections.max(distances);
					int iToChange = distances.indexOf(biggestDist);
					
					if(dist < biggestDist) {
						labels.set(iToChange, lab);
						distances.set(iToChange, dist);
					}
					
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}				
		}
		
		int mostFreq = labels.get(0);
		int count = 0;
		for(int i = 0; i < labels.size(); i++) {
			int cnt = 0;
			for(int j = i + 1; j < labels.size(); j++) {
				if(labels.get(i) == labels.get(j)) {
					cnt++;
				}
				if(count < cnt) {
					mostFreq = labels.get(i);
					count = cnt;
				}
			}
		}
		
		return mostFreq;
	}
	
	/**
	 * Returns a predicted value to all the labels in a DataSet's Patterns
	 * considering its k nearest neighbors by Euclidean Distance
	 * 
	 * @param k - the number of neighbors to consider;
	 * @param ds - a DataSet whose Patterns we will classify;
	 */
	public ArrayList<Integer> kNearestNeighbors(int k, DataSet ds) {
		ArrayList<Integer> temp = new ArrayList<Integer>();

		for(IPattern pat: ds.patterns) {
			temp.add(this.kNearestNeighbors(k, pat));
		}
		
		return temp;
	}
	
	/**
	 * Returns the percentage of errors in labels predicted by the KNN method
	 * 
	 * @param groundTruth - a DataSet containing the truth;
	 * @param labels - a List containing the predicted value for each label
	 * of the former;
	 */
	public double errorPercentage(DataSet groundTruth, List<Integer> labels) {
		double total = labels.size();
		double wrong = 0;
		for(int i = 0; i < total; i++) {
			if(groundTruth.patterns.get(i).getLabel() != labels.get(i)) {
				wrong++;
			}
		}
				
		return (wrong / total);		
	}
	
}
