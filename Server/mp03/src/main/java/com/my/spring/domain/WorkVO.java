package com.my.spring.domain;

public class WorkVO {
	int id, id_workerInfo, payment;
	String timeStart, timeEnd, hoursOfWork, dateWork;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_workerInfo() {
		return id_workerInfo;
	}
	public void setId_workerInfo(int id_workerInfo) {
		this.id_workerInfo = id_workerInfo;
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
	public String getHoursOfWork() {
		return hoursOfWork;
	}
	public void setHoursOfWork(String hoursOfWork) {
		this.hoursOfWork = hoursOfWork;
	}
	public String getDateWork() {
		return dateWork;
	}
	public void setDateWork(String dateWork) {
		this.dateWork = dateWork;
	}
}
