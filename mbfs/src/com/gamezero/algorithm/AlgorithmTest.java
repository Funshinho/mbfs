package com.gamezero.algorithm;

import java.util.List;

/**
 * Test class for the algorithm
 * 
 * @author funshinho
 *
 */
public class AlgorithmTest {

	/**
	 * Main method
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		// The test grid is 5*5. We are looking for all paths with a length of 9
		// from the top left corner to the bottom down corner
		ModifiedBreadthFirstSearchAlgorithm algo = new ModifiedBreadthFirstSearchAlgorithm(
				0, 0, 4, 4, 9, 5, 5);

		List<List<int[]>> solutions = algo.solve();

		// Print the list of solutions
		for (List<int[]> possibleSol : solutions) {
			for (int[] c : possibleSol) {
				System.out.print("(" + c[0] + "," + c[1] + ") ");
			}
			System.out.println();
		}
		System.out.println("Number of solutions : " + solutions.size());

	}

}
