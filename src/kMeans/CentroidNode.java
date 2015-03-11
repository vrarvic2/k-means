package kMeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.nio.file.Path;

/**
 * @author vravic2 this class implements all the methods for calculating
 *         centroid point.
 */
public class CentroidNode {
	public static HashMap<String, String> map = new HashMap<>();
	private static CentroidNode instance = new CentroidNode();
	final static String FILE_NAME = "/Users/vravic2/Documents/workspace/Assignment_2/centroid/file.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	public static int k;

	public CentroidNode() {

	}

	//
	/**
	 * @return Returns instance
	 */
	public static CentroidNode getInstance() {
		return instance;
	}

	/**
	 * @param key
	 * @Returns Value for the key
	 */
	public static String getValue(String key) {
		return map.get(key);
	}

	/**
	 * @param pairs
	 *            Adds keys value of 2 dimensional arrays
	 */
	public static void add(String[][] pairs) {
		for (String[] pair : pairs) {
			map.put(pair[0], pair[1]);
		}
	}

	/**
	 * @param key
	 * @param value
	 *            Adds one key value pair to hash map
	 */
	public static void addonemap(String key, String value) {
		map.put(key, value);
	}

	/**
	 * @param keys
	 * @param values
	 *            Adds an array of keys and values to hash map
	 */
	public static void add(String[] keys, String[] values) {
		for (int i = 0; i < keys.length; ++i) {
			map.put(keys[i], values[i]);
		}
	}

	/**
	 * @throws IOException
	 *             Read the files
	 */
	public static void getCentroid() throws IOException {

		// Check if the hash map is null
		if (CentroidNode.map.isEmpty()) {
			Path path = Paths.get(FILE_NAME);
			try (BufferedReader reader = Files.newBufferedReader(path,
					CentroidNode.ENCODING)) {
				String line = null;
				while ((line = reader.readLine()) != null) {

					String[] val = line.split("\t");
					CentroidNode.addonemap(val[0], val[1]);
					k++;
				}
				if(k<3 || k>8){
					System.err.println("K Must be in range from 3 and 8");
					System.exit(0);
				}
			}
		}
	}

	/**
	 * Compare 2 centroids.
	 * 
	 * @param key
	 * @param Value
	 * @return
	 */
	public static boolean compareCentroid(String key, String Value) {
		DataPoint dp = new DataPoint();
		String[] values = Value.split(",");
		double[] newCent = dp.convertDoubleArray(values);
		double[] oldCent = dp.convertDoubleArray(CentroidNode.map.get(key)
				.split(","));
		double differ = EuclidianDistance.calcEuclidianDistance(oldCent,
				newCent);

		if (differ < 0.001) {
			MyDriver.centroidCount++;
			if (MyDriver.centroidCount == k)
				return true;
			return false;
		} else {
			CentroidNode.map.replace(key, CentroidNode.map.get(key), Value);
			return false;
		}
	}

}
