import java.util.*;




public class event{
      String event_type;
      int id;
      double entr_time;
      double duration;
      
      event(String event_type, int id, double entr_time, double duration){
    	  this.event_type = event_type;
    	  this.id = id;
    	  this.entr_time= entr_time;
    	  this.duration=duration;
      }
      
      event (String event_type, int id, double entr_time){
    	  this.event_type = event_type;
    	  this.id = id;
    	  this.entr_time= entr_time;
      }

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getEntr_time() {
		return entr_time;
	}

	public void setEntr_time(double entr_time) {
		this.entr_time = entr_time;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
}
