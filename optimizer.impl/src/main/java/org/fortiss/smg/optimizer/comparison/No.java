/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.optimizer.comparison;

import org.fortiss.smg.optimizer.data.Interval;

/**
 * Utilities of an advisor without optimization
 * 
 * @author Cheng Zhang
 * @version 1.0
 */
public class No {

	/***
	 * Generate a schedule with given intervals
	 * 
	 * @param intervals
	 * @return
	 */
	public static void update(Interval[] intervals) {
		// Log
		// log.finest(Arrays.toString(intervals));
		// log.finer(Specification.getSpecification().toString());

		for (Interval interval : intervals) {
			interval.setBatteryExchange(0);
		}
	}
}
