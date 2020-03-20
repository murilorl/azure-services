package com.mrlima.app.model;

public class BasicPublishResponse {

	private String topic;
	private int partition;
	private long offset;

	public BasicPublishResponse(String topic, int partition, long offset) {
		super();
		this.topic = topic;
		this.partition = partition;
		this.offset = offset;
	}

	public BasicPublishResponse(String topic, int partition, long offset, String message) {
		super();
		this.topic = topic;
		this.partition = partition;
		this.offset = offset;
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

	public BasicPublishResponse(String message) {
		this.message = message;
	}

}
