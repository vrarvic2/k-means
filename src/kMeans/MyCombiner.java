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
 * Combiner Class calculates sum of all values for the same key and calls reducer
 */
public class MyCombiner extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterator<Text> value,
			OutputCollector<Text, Text> output, Reporter r) throws IOException {

		int counter = 0;

		double[] allSum = new double[10];

		/*
		 * Calculating the sum of all with same key
		 */
		while (value.hasNext()) {
			String val = value.next().toString();
			DataPoint dp = new DataPoint(key.toString() + "\t" + val);

			String[] s = dp.getDataValue().split(",");
			double[] values = dp.convertDoubleArray(s);
			counter++;
			for (int i = 0; i < 10; i++)
				allSum[i] += values[i];
		}
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < 10; i++) {

			strbuf.append(String.valueOf(allSum[i]) + ",");
		}

		/*
		 * To find the counter's value in Reducer.
		 */
		String redvalue = strbuf.toString().substring(0,
				strbuf.toString().length() - 1)
				+ "~" + counter;

		output.collect(new Text(key.toString()), new Text(redvalue));

	}
}
