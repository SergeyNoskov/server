package com.exscudo.peer.eon.services;

/**
 * Attributes of the node distributed during the exchange.
 *
 */
public class SalientAttributes {

	private String announcedAddress;
	private String application;
	private long peerID;
	private String version;
	private String networkID;
	private int fork;

	public String getAnnouncedAddress() {
		return announcedAddress;
	}

	public void setAnnouncedAddress(String announcedAddress) {
		this.announcedAddress = announcedAddress;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public long getPeerId() {
		return peerID;
	}

	public void setPeerId(long peerID) {
		this.peerID = peerID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNetworkID() {
		return networkID;
	}

	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}

	public int getFork() {
		return fork;
	}

	public void setFork(int fork) {
		this.fork = fork;
	}
}
