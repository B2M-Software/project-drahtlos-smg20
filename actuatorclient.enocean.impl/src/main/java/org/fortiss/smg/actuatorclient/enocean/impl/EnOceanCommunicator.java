package org.fortiss.smg.actuatorclient.enocean.impl;

import org.fortiss.smg.actuatorclient.enocean.impl.telegrams.UniversalTelegram;

public interface EnOceanCommunicator {
	
	/**
	 * Send Telegram is called whenever a new Telegram shall be sent to a actor
	 * @param telegram The Telegram with its content wrapped in an Object of type DataTelegramOut
	 */
	public void sendTelegram(UniversalTelegram telegram);
	
	/**
	 * Each Thermokon STC has an ID
	 * @return the String of HEXchars with the ID of the Thermokon connected to this Communicator 
	 */
	public String getSenderdeviceId();
	
	/**
	 * Disconnects from the Thermokon
	 */
	public void disconnect();
		
	/**
	 * @return The state of the Communicator.
	 */
	public boolean isActive();
}
