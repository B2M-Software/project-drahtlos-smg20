/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.remoteframework.lib;

import java.io.IOException;

public class RabbitRPCServer<T> extends GenericServer<T> {

	private ServerThread thread;

	public RabbitRPCServer(Class<T> klass, T impl, String queue) {
		super(klass, impl, queue);
	}

	@Override
	public void init() throws IOException {

		thread = new ServerThread(klass, impl, queue);
	}

	@Override
	public void destroy() throws IOException {
		thread.stop();
		
	}

}
