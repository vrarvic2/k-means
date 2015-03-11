package kMeans;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

/**
 * @author vravic2 Partitioner class
 */
public class MyPartitioner implements Partitioner<Text, Text> {

	@Override
	public void configure(JobConf arg0) {
	}

	/*
	 * Partitioner from mapper to reducer(non-Javadoc) 3 Customized Partitions
	 * to reducer
	 */
	@Override
	public int getPartition(Text key, Text value, int numPartitions) {

		int node = Integer.parseInt(key.toString().substring(1,
				key.toString().length()));
		if (numPartitions == 0)
			return 0;

		if (node <= 2000)
			return 0;

		if (node > 2000 && node <= 3000)
			return 1 % numPartitions;

		else
			return 2 % numPartitions;

	}
}
