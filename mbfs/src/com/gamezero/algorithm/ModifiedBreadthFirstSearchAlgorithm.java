package com.gamezero.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of the breadth first search algorithm. It is modified to
 * retrieve all paths from a start to an end with a given length in a
 * rectangular grid
 * 
 * @author funshinho
 */
public class ModifiedBreadthFirstSearchAlgorithm {

	/**
	 * The list of possible directions
	 * 
	 * @author funshinho
	 *
	 */
	private enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	/** The start position (list {x,y}) */
	private int[] start = new int[2];
	/** The end position (list {x,y}) */
	private int[] end = new int[2];

	/** The length of the path */
	private int length;

	/** The grid width */
	private int gridWidth;
	/** The grid height */
	private int gridHeight;

	/**
	 * Default constructor
	 * 
	 * @param startX
	 *            The x start position
	 * @param startY
	 *            The y start position
	 * @param endX
	 *            The x end position
	 * @param endY
	 *            The y end position
	 * @param length
	 *            The length of the path
	 * @param gridWidth
	 *            The grid width
	 * @param gridHeight
	 *            The grid height
	 */
	public ModifiedBreadthFirstSearchAlgorithm(int startX, int startY,
			int endX, int endY, int length, int gridWidth, int gridHeight) {

		this.start = new int[] { startX, startY };
		this.end = new int[] { endX, endY };
		this.length = length;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
	}

	/**
	 * Get all possible solutions from the grid
	 * 
	 * @return The list of all possible solutions
	 */
	public List<List<int[]>> solve() {

		List<List<int[]>> solutions = new ArrayList<List<int[]>>();
		// Start Breadth-first search algorithm
		bfs(start, end, length, solutions);

		return solutions;
	}

	/**
	 * The breadth-first search algorithm
	 * 
	 * @param start
	 *            the start position
	 * @param end
	 *            the end position
	 * @param length
	 *            the desired length
	 * @param solutions
	 *            the list of possible solutions
	 */
	private void bfs(int[] start, int[] end, int length,
			List<List<int[]>> solutions) {

		// Initialize queue. The queue will contain all possible paths from
		// start to end position
		Queue<List<int[]>> queue = new LinkedList<List<int[]>>();

		// Add the initial path (only containing the start position) in the
		// queue
		List<int[]> path = new ArrayList<int[]>();
		path.add(start);
		queue.add(path);

		// Stop the algorithm when the queue is empty
		while (!queue.isEmpty()) {

			// Extract the first path in the queue and get the last cell in this
			// path
			List<int[]> currentPath = queue.poll();
			int[] lastCell = currentPath.get(currentPath.size() - 1);

			// The path is a solution if its size equals the desired length and
			// if the last cell is the end position
			if (lastCell[0] == end[0] && lastCell[1] == end[1]
					&& currentPath.size() == length) {
				solutions.add(currentPath);
			} else {

				// Get all possible neighbors from the last cell of the current
				// path
				int newX = 0;
				int newY = 0;
				for (Direction dir : Direction.values()) {

					int x = lastCell[0];
					int y = lastCell[1];

					switch (dir) {
					case UP:
						newX = x;
						newY = y - 1;
						break;
					case DOWN:
						newX = x;
						newY = y + 1;
						break;
					case LEFT:
						newX = x - 1;
						newY = y;
						break;
					case RIGHT:
						newX = x + 1;
						newY = y;
					}

					int[] newCell = new int[] { newX, newY };

					// Check that the new cell is within the grid and that the
					// current path does
					// not already contain it (to avoid loop)
					if (newX >= 0 && newX < this.gridWidth && newY >= 0
							&& newY < this.gridHeight
							&& !contains(currentPath, newCell)) {

						// Add the neighbor to the current path and then update
						// the queue with this new path
						List<int[]> newPath = new ArrayList<int[]>(currentPath);
						newPath.add(newCell);
						queue.add(newPath);
					}
				}
			}
		}
	}

	/**
	 * Useful method to test if a list contains a list of integer
	 * 
	 * @param list
	 *            the list
	 * @param intList
	 *            the list of integer
	 * @return true if the list contains it, false otherwise
	 */
	public boolean contains(List<int[]> list, int[] intList) {
		boolean ret = false;
		int i = 0;
		while (!ret && i < list.size()) {
			if (list.get(i)[0] == intList[0] && list.get(i)[1] == intList[1])
				ret = true;
			i++;
		}
		return ret;
	}

}
