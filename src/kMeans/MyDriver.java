package kMeans;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;

/**
 * @author vravic2 Driver configuration file.
 */
public class MyDriver {

	/**
	 * Total no of centroids in the input file.
	 */
	public static int centroidCount = 0;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int iterationCount = 0; // counter to set the ordinal number of the
								// intermediate outputs
		Counters.counters = 1;
		while (Counters.counters != 0) {
			centroidCount = 0;
			JobClient client = new JobClient();
			JobConf conf = new JobConf(kMeans.MyDriver.class);
			conf.setMapperClass(kMeans.MyMapper.class);
			conf.setReducerClass(kMeans.MyReducer.class);
			conf.setPartitionerClass(kMeans.MyPartitioner.class);
			conf.setCombinerClass(kMeans.MyCombiner.class);
			conf.setMapOutputKeyClass(Text.class);
			conf.setMapOutputValueClass(Text.class);
			conf.setOutputKeyClass(Text.class);
			conf.setOutputValueClass(Text.class);
			String input, output;
			input = args[0];
			output = args[1] + (iterationCount + 1); // send the output file
			FileInputFormat.setInputPaths(conf, new Path(input));
			FileOutputFormat.setOutputPath(conf, new Path(output));
			iterationCount++;
			client.setConf(conf);

			/**
			 * running the map reduce job
			 */
			try {
				JobClient.runJob(conf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
