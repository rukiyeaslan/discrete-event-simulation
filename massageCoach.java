
public class massageCoach {
   int id;
   boolean available;
   
   massageCoach(int id, boolean available){
	   this.id = id;
	   this.available=available;
   }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public boolean isAvailable() {
	return available;
}

public void setAvailable(boolean available) {
	this.available = available;
}
}
