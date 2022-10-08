
public class Physiotherapist {

	int ID;
	double serviceTime;
	boolean available;
	
	public Physiotherapist( int ID, double serviceTime, boolean available){
		this.ID= ID;
		this.serviceTime = serviceTime;
		this.available= available;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
