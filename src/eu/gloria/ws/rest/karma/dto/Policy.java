package eu.gloria.ws.rest.karma.dto;

public class Policy {

	int id;
	int policyId;
	int actionsId;
	int executorPoints;
	int ownerPoints;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getActionsId() {
		return actionsId;
	}
	public void setActionsId(int actionsId) {
		this.actionsId = actionsId;
	}
	public int getExecutorPoints() {
		return executorPoints;
	}
	public void setExecutorPoints(int executorPoints) {
		this.executorPoints = executorPoints;
	}
	public int getOwnerPoints() {
		return ownerPoints;
	}
	public void setOwnerPoints(int ownerPoints) {
		this.ownerPoints = ownerPoints;
	}
}
