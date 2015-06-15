/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.webrest.impl.jersey;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.fortiss.smg.webrest.impl.front.GamificationStatistics;
import org.fortiss.smg.webrest.impl.front.HealthOfServer;
import org.fortiss.smg.webrest.impl.front.Push;
import org.fortiss.smg.webrest.impl.front.Statistics;
import org.fortiss.smg.webrest.impl.front.UserManagement;
import org.fortiss.smg.webrest.impl.openhab.OpenhabGateway;

/**
 * handles the different request types
 */

public class JerseyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<Class<?>>();
     
        result.add(HealthOfServer.class);
        result.add(UserManagement.class);
        result.add(UserManagement.class);
        result.add(Statistics.class);
        result.add(OpenhabGateway.class);
        result.add(GamificationStatistics.class);
        
      //result.add(org.fortiss.smg.actuatorclient.hexabus.impl.ActuatorClientImpl.class);

        return result;
    }
    
    
}