package com.my.spring.domain;

public class WorkerInfoVO {
	int id, id_worker, payment;
	String timeStart, timeEnd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId_worker() {
		return id_worker;
	}

	public void setId_worker(int id_worker) {
		this.id_worker = id_worker;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

}
