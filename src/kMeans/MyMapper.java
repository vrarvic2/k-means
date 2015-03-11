package kMeans;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MyMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, Text> {

	/**
	 * @param key
	 * @param values
	 * @param output
	 * @param reporter
	 * @throws IOException
	 *             Mapper method of map reduce
	 */
	public void map(LongWritable key, Writable values,
			OutputCollector<?, ?> output, Reporter reporter) throws IOException {
	}

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter r) throws IOException {

		/**
		 * Passing to Data point representation class
		 */
		DataPoint dp = new DataPoint(value.toString());

		/**
		 * Read the centroid inp file and add values to hash map
		 */
		CentroidNode.getCentroid();
		double shortDist = Double.MAX_VALUE;
		String centNode = null;

		/**
		 * Calculate Euclidian Distance and generate the mapper
		 */
		for (String s : CentroidNode.map.keySet()) {
			String val = CentroidNode.map.get(s);
			String[] values = val.split(",");
			double[] dpVal = dp.convertDoubleArray(values);

			/**
			 * Send the 2 arrays to calc the euc dist
			 */
			double currDist = EuclidianDistance.calcEuclidianDistance(dpVal,
					dp.getDatas());
			if (currDist < shortDist) {
				shortDist = currDist;
				centNode = s;
			}
		}
		output.collect(new Text(centNode), new Text(dp.getDataValue()));

	}

}
