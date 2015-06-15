/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.actuatorclient.siemens.impl;import java.util.ArrayList;import java.util.List;import java.util.concurrent.TimeoutException;import org.fortiss.smg.actuatormaster.api.AbstractClient;import org.fortiss.smg.actuatormaster.api.ActuatorMasterQueueNames;import org.fortiss.smg.actuatormaster.api.IActuatorMaster;import org.fortiss.smg.config.lib.WrapperConfig;import org.fortiss.smg.remoteframework.lib.DefaultProxy;import org.osgi.framework.BundleActivator;import org.osgi.framework.BundleContext;import org.slf4j.LoggerFactory;public class ActuatorClientActivator extends AbstractClient implements		BundleActivator {	// Logger from sl4j	private static org.slf4j.Logger logger = LoggerFactory			.getLogger(ActuatorClientActivator.class);	private ActuatorClientImpl implClient;	private List<ActuatorClientImpl> clients = new ArrayList<ActuatorClientImpl>();	IActuatorMaster master4config = null;	@Override	public void start(BundleContext context) throws Exception {		// register here your services etc.		// DO NOT start heavy operations here - use threads		/*		 * Try to connect to Master to get the wrapper's config file		 */		ArrayList<WrapperConfig> configList = new ArrayList<WrapperConfig>();		DefaultProxy<IActuatorMaster> proxyMaster = new DefaultProxy<IActuatorMaster>(				IActuatorMaster.class,				ActuatorMasterQueueNames.getActuatorMasterInterfaceQueue(),				5000);		try {			master4config = proxyMaster.init();		} catch (TimeoutException e) {			logger.error("ActuatorClient: Unable to connect to master (Timeout).");		}		/*		 * If we have connection try to get the wrapper's config file		 */		if (master4config != null) {			try {				configList = master4config.getWrapperConfig("siemens");			} catch (TimeoutException e) {				logger.error("ActuatorClient: Unable to connect to master (Timeout).");			}			proxyMaster.destroy();			/*			 * For each received wrapper config instance (possibly the same			 * wrapper is used for multiple (physical) devices			 */			if (configList.size() > 0) {				for (WrapperConfig config : configList) {					final String clientKey = config.getKey();					final String clientIDextension = config.getHost();					implClient = new ActuatorClientImpl(config.getHost(),							config.getPort(), config.getWrapperID(),							config.getPollingfrequency(), config.getUsername(),							config.getPassword());					registerAsClientAtServer(implClient,							config.getWrapperName(), new IOnConnectListener() {								@Override								public void onSuccessFullConnection() {									implClient.setMaster(master);									implClient.setClientId(clientId);									implClient.activate();									logger.info("ActuatorClient[" + clientKey											+ "-" + clientIDextension											+ "] is alive");									clients.add(implClient);								}							});					int timeout = 0;					while ((!implClient.isComponentAlive() || timeout > 120)) {						logger.debug("waiting "								+ timeout								+ "/120 "								+ ((!implClient.isComponentAlive() && timeout < 120)));						try {							Thread.sleep(1000);						} catch (InterruptedException e) {							e.printStackTrace();						}						timeout++;					}				}				logger.info("Siemens Wrapper started");			} else {				logger.info("No Configuration available");			}		} else {			proxyMaster.destroy();			logger.debug("Siemens Wrapper could not read config from Master");			this.stop(context);		}	}	@Override	public void stop(BundleContext context) throws Exception {		// REMEMBER to destroy all resources, threads and do cleanup		for (ActuatorClientImpl client : clients) {			client.deactivate();		}		this.destroy();		logger.info("ActuatorClient[Siemens] is dead");	}}