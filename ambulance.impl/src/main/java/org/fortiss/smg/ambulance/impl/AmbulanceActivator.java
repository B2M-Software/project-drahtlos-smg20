/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.ambulance.impl;import org.fortiss.smg.ambulance.api.AmbulanceInterface;import org.fortiss.smg.ambulance.api.AmbulanceQueueNames;import org.fortiss.smg.remoteframework.lib.DefaultServer;import org.osgi.framework.BundleActivator;import org.osgi.framework.BundleContext;import org.slf4j.Logger;import org.slf4j.LoggerFactory;public class AmbulanceActivator implements BundleActivator {    DefaultServer<AmbulanceInterface> server;    AmbulanceImpl impl;        private static Logger logger = LoggerFactory.getLogger(AmbulanceActivator.class);    @Override    public void start(BundleContext context) throws Exception {        // register here your services etc.        // DO NOT start heavy operations here - use threads            	logger.info("Ambulance is alive");    	        impl = new AmbulanceImpl();        impl.activate();        server = new DefaultServer<AmbulanceInterface>(AmbulanceInterface.class, impl, AmbulanceQueueNames.getAmbulanceQueue());        server.init();    }    @Override    public void stop(BundleContext context) throws Exception {        // REMEMBER to destroy all resources, threads and do cleanup        server.destroy();    }}