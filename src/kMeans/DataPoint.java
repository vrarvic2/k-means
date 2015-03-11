package kMeans;

/**
 * @author vravic2
 * Representation of Data Point class.
 */
public class DataPoint {

	private String dataName;
	private String dataValue;
	private double[] datas;

	/**
	 * @param dataString
	 */
	public DataPoint(String dataString) {
		String[] data;
		data = dataString.split("\t");
		this.setDataName(data[0]);
		this.setDataValue(data[1]);
		String[] val = data[1].split(",");

		this.setDatas(this.convertDoubleArray(val));

		/*
		 * Type casting array String to array Long
		 */
		double[] dataval = new double[val.length];
		for (int i = 0; i < val.length; i++) {
			dataval[i] = Double.parseDouble(val[i]);
		}
		this.setDatas(dataval);

	}

	/**
	 * Default Constructor
	 */
	public DataPoint() {

	}

	/**
	 * @param s
	 * @return
	 */
	public double[] convertDoubleArray(String[] s) {

		double[] dataval = new double[s.length];
		for (int i = 0; i < s.length; i++) {
			dataval[i] = Double.parseDouble(s[i]);
		}
		return dataval;
	}

	/**
	 * @return
	 */
	public String getDataName() {
		return dataName;
	}

	/**
	 * @param dataName
	 */
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	/**
	 * @return
	 */
	public String getDataValue() {
		return dataValue;
	}

	/**
	 * @param dataValue
	 */
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	/**
	 * @return
	 */
	public double[] getDatas() {
		return datas;
	}

	/**
	 * @param dataval
	 */
	public void setDatas(double[] dataval) {
		this.datas = dataval;
	}

}
