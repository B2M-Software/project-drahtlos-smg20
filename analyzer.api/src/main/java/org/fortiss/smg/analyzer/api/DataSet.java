/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.analyzer.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.apache.commons.math3.exception.NullArgumentException;

import org.fortiss.smg.containermanager.api.devices.DeviceId;
import org.fortiss.smg.informationbroker.api.DoublePoint;

/**
 * @author Ann Katrin Gibtner (annkatrin.gibtner@tum.de)
 *
 */
public class DataSet {

	/**
	 * time where the data collection in the database starts
	 */
	private Calendar startDate = Calendar.getInstance();
	/**
	 * time where the data collection in the database ends
	 */
	private Calendar stopDate = Calendar.getInstance();
	/**
	 * includes the single data points which were found in the database
	 */
	private List<DoublePoint> dataList = new ArrayList<DoublePoint>();
	/**
	 * the sensor device, to whom the data belong
	 */
	private DeviceId device;

	/**
	 * represents a data set, which includes data from the database; <br>
	 * NOTE: If startDate and stopDate have the same value the data set includes
	 * the newest element found in the database
	 * 
	 * @param startDate
	 *            time where the data collection in the database starts
	 * @param stopDate
	 *            time where the data collection in the database ends
	 * @param dev
	 *            defines the sensor device, of whom data should be collected
	 * @param dataList
	 *            includes the single data points which were found in the
	 *            database
	 * @throws NullArgumentException
	 *             if either {@code stopDate, startDate or dev} is set null
	 */
	public DataSet(Calendar startDate, Calendar stopDate, DeviceId dev,
			List<DoublePoint> dataList) throws NullArgumentException {
		if (startDate == null) {
			throw new NullArgumentException();
		}
		if (stopDate == null) {
			throw new NullArgumentException();
		}
		if (dev == null) {
			throw new NullArgumentException();
		}
		this.startDate.setTimeInMillis(startDate.getTimeInMillis());
		this.stopDate.setTimeInMillis(stopDate.getTimeInMillis());
		this.device = dev;
		this.dataList = dataList;
	}

	/**
	 * represents a data set, which includes data from the database
	 * 
	 * @param startDate
	 *            time where the data collection in the database starts
	 * @param stopDate
	 *            time where the data collection in the database ends
	 * @param dev
	 *            defines the sensor device, of whom data should be collected
	 * @throws NullArgumentException
	 *             if either {@code stopDate, startDate or device} is set null
	 */
	public DataSet(Calendar startDate, Calendar stopDate, DeviceId device)
			throws NullArgumentException {
		if (startDate == null) {
			throw new NullArgumentException();
		}
		if (stopDate == null) {
			throw new NullArgumentException();
		}
		if (device == null) {
			throw new NullArgumentException();
		}
		this.startDate.setTimeInMillis(startDate.getTimeInMillis());
		this.stopDate.setTimeInMillis(stopDate.getTimeInMillis());
		this.device = device;
		this.dataList = null;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getStopDate() {
		return stopDate;
	}

	public void setStopDate(Calendar stopDate) {
		this.stopDate = stopDate;
	}

	public DeviceId getDeviceId() {
		return device;
	}

	public void setDeviceId(DeviceId device) {
		this.device = device;
	}

	public List<DoublePoint> getDataList() {
		return dataList;
	}

	public void setDataList(List<DoublePoint> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return DataSet.class.toString() + "[startDate=" + startDate.getTime()
				+ ", stopDate=" + stopDate.getTime() + ", DeviceId=" + device
				+ "dataList=" + dataList + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DataSet other = (DataSet) obj;
		if (!this.startDate.equals(other.startDate)) {
			return false;
		}
		if (!this.stopDate.equals(other.stopDate)) {
			return false;
		}
		if (!this.device.equals(other.device)) {
			return false;
		}
		if (!this.dataList.equals(other.dataList)) {
			return false;
		}
		return true;
	}
}
