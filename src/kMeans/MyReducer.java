package kMeans;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * @author vravic2
 *
 */
public class MyReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	@Override
	/**
	 * Reducer method of hadoop map reduce
	 */
	public void reduce(Text key, Iterator<Text> value,
			OutputCollector<Text, Text> output, Reporter r) throws IOException {
		double[] allSum = new double[10];
		int counter = 0;

		while (value.hasNext()) {
			String val = value.next().toString();
			String[] reducedValues = val.split("~");
			counter += Integer.parseInt(reducedValues[1]);
			DataPoint dp = new DataPoint(key.toString() + "\t"
					+ reducedValues[0]);

			String[] s = dp.getDataValue().split(",");
			double[] values = dp.convertDoubleArray(s);

			for (int i = 0; i < 10; i++)
				allSum[i] = values[i];
		}

		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			allSum[i] = allSum[i] / counter;
			strbuf.append(String.valueOf(allSum[i]) + ",");
		}

		String redvalue = strbuf.toString().substring(0,
				strbuf.toString().length() - 1);

		/**
		 * Updating the centroid node
		 */
		if (CentroidNode.compareCentroid(key.toString(), redvalue))
			Counters.counters--;

		output.collect(new Text(key.toString()), new Text(redvalue));

	}
}
