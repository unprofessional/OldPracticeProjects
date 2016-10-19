import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeepestPit {

	static int[] nums = { 0, 1, 3, -2, 0, 1, 0, -3, 2, 3 };
	static int[] intArray = null;
	static ArrayList<Integer> arNums = null;

	public static void main(String[] args) throws IOException {

		// Pre-process source file
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = "";
		arNums = new ArrayList<Integer>();
		while ((line = br.readLine()) != null) {
			arNums.add(Integer.parseInt(line));
		}
		br.close();

		// Convert ArrayList to array
		intArray = new int[arNums.size()];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = arNums.get(i).intValue();
		}

		// Execute search
		System.out.println("mySolution: " + findDeepestPit(intArray));
		System.out.println("stackOverflowSolution: " + stackOverflowSolution(intArray));
		// System.out.println("findDeepestPit: " + findDeepestPit(nums));
	}

	private static int findDeepestPit(int[] arr) {

		// P > Q < R
		// Q is the bottom of the pit

		int p = 0, q = 0, r = 0;
		int currentDeepest = 0;
		int iteration = 0;

		boolean goingDown = false;

		ArrayList<Integer> storeIterations = new ArrayList<Integer>();

		// TODO:
		for (int i = 1; i < arr.length; i++) {

			// Down case
			if (arr[i] < arr[i - 1]) {
				// We know Q is i since it is less than the previous iteration
				// of i
				q = i;

				if (!goingDown) {
					// If it was going up and now it is going down, we know P is
					// the previous iteration of i
					p = i - 1;
					goingDown = true;
					// XXX: Technically, this ends the previous pit cycle and
					// starts a new one
				}

			}

			// Up case
			if (arr[i] > arr[i - 1]) {
				// We know R is i... we don't worry about Q since it could be
				// from earlier in the array
				r = i;

				if (goingDown) {
					// If it was going down and now it is going up, we know Q is
					// the previous iteration of i
					q = i - 1;
					goingDown = false;

				}

				iteration = findMin(arr, p, q, r);
				// System.out.println("p:" + p + " q:" + q + " r:" + r);
				// System.out.println("iteration: " + iteration);

				// Add each min iteration value to list
				// Unfortunately, doing it this way adds a bunch of unnecessary
				// iterations
				// (i.e. plot-line iterations that continue to move up (or
				// continue to move down)
				storeIterations.add(iteration);

			}

		}

		int max = 0;

		for (int i = 0; i < storeIterations.size(); i++) {
			if (max < storeIterations.get(i))
				max = storeIterations.get(i);
			// System.out.println("max: " + max);
		}

		return max;

	}

	private static int findMin(int[] arr, int p, int q, int r) {
		int min = 0;

		// System.out.println("P: " + arr[p]);
		// System.out.println("Q: " + arr[q]);
		// System.out.println("R: " + arr[r]);

		int pqDiff = arr[p] - arr[q];
		int rqDiff = arr[r] - arr[q];

		if (pqDiff < rqDiff)
			min = pqDiff;
		else
			min = rqDiff;

		return min;
	}

	private static int stackOverflowSolution(int[] arr) {

		// I wish I could write something elegant like this
		// Taken from http://stackoverflow.com/questions/12329711/interview-test-deepest-pit

		int depth = 0;

		int P = 0, Q = -1, R = -1;

		for (int i = 1; i < arr.length; i++) {
			if (Q < 0 && arr[i] >= arr[i - 1])
				Q = i - 1;

			if ((Q >= 0 && R < 0)
					&& (arr[i] <= arr[i - 1] || i + 1 == arr.length)) {
				if (arr[i] <= arr[i - 1])
					R = i - 1;
				else
					R = i;
				// System.out.println(P+"  "+Q+"  "+R);
				depth = Math.max(depth,
						Math.min(arr[P] - arr[Q], arr[R] - arr[Q]));
				P = i - 1;
				Q = R = -1;
			}
		}
		if (depth == 0)
			depth = -1;
		// System.out.println("Depth: "+depth);
		return depth;
	}

}
