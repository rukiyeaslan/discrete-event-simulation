import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

//comparator for events
class comparing implements Comparator<event>{
	public int compare(event e1, event e2) {
		 if (Math.abs(e1.entr_time - e2.entr_time) < 0.0000000001 ) {
			if (e1.getId()< e2.getId()) {
				return -1;
			}
			else if(e1.getId()> e2.getId()) {
				return 1;
			}	
			return 0;
		}	
		else if (e1.entr_time < e2.entr_time)
		    return -1;
		else if (e1.entr_time > e2.entr_time)
		    return 1;
		else return 0;        
	}
}
//comparator for physiotherapy queue
class physCompare implements Comparator<Player>{
	public int compare(Player p1, Player p2) {
		if(Math.abs(p1.getTraining_time() - p2.getTraining_time()) < 0.0000000001) {
			if(Math.abs(p1.getEnter_phys_que() - p2.getEnter_phys_que()) < 0.0000000001) {
				if(p1.getID()< p2.getID()) {
					return -1;
				}
				else if(p1.getID()> p2.getID()) {
					return 1;        
			}
			else if (p1.getEnter_phys_que() < p2.getEnter_phys_que()) {
				return -1;
			}
			else if(p1.getEnter_phys_que() > p2.getEnter_phys_que()) {
				return 1;
			}      	         
		}
		else if (p1.getTraining_time() < p2.getTraining_time())
		    return 1;
		else if (p1.getTraining_time() > p2.getTraining_time())
		    return -1;
	       return 0;   
	}
}
//comparator for massage queue
class massCompare implements Comparator<Player>{
	public int compare(Player p1, Player p2) {
		if (p1.getSkill_level() < p2.getSkill_level()) {
			return 1;
		}
		else if(p1.getSkill_level()> p2.getSkill_level()) {
			return -1;
		}
		else {
			if (Math.abs(p1.getEnter_massage_que()-p2.getEnter_massage_que()) < 0.0000000001){
				if(p1.getID()< p2.getID()) {
        			return -1;
        			}
        		else if(p1.getID()> p2.getID()) {
        			return 1;
				}
        		}			
			else if (p1.getEnter_massage_que()<p2.getEnter_massage_que()) {
				return -1;
				}
			else if(p1.getEnter_massage_que()>p2.getEnter_massage_que()) {
				return 1;
			}             
		}
		return 0;
	}
}
//comparator for training queue
class trCompare implements Comparator<Player>{
	public int compare(Player p1, Player p2) {
		if(Math.abs(p1.getEnter_tr_que()-p2.getEnter_tr_que())<0.0000000001) {
			if(p1.getID()< p2.getID()) {
    			return -1;
    		}
    		else if(p1.getID()> p2.getID()) {
    			return 1;}
			
		}
		else if (p1.getEnter_tr_que()<p2.getEnter_tr_que()) {
			return -1;
		}
		else if(p1.getEnter_tr_que()>p2.getEnter_tr_que()) {
			return 1;
		}
		return 0;
	}
}

public class project2main {
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Physiotherapist> physiotherapists = new ArrayList<Physiotherapist>();
		ArrayList<trainingCoach> trainers = new ArrayList<trainingCoach>();
		ArrayList<massageCoach> massagers = new ArrayList<massageCoach>();
		
		PriorityQueue<Player> trainingque = new PriorityQueue<Player>(new trCompare());
		PriorityQueue<Player> physque = new PriorityQueue<Player>(new physCompare());
		PriorityQueue<Player> massageque = new PriorityQueue<Player>(new massCompare());
		
		double current_time=0;  //current time

		int invalid_attempts=0;  //number of invalid attempts
		int cancelled_attempts =0;  //number of cancelled attempts
		int max_tr_que_len = 0;     //max length of training queue
		int max_phys_que_len = 0;   //max length of physiotherapy queue 
		int max_mas_que_len =0;     //max length of massage que queue 
		
		int num_of_tr_event =0;
		int num_of_p_event =0;
		int num_of_m_event =0;
		double total_t_time=0;     //total training time
		double total_p_time=0;     //total physiotherapy time 
		double total_m_time=0;     //total massage time
		double total_turnaround_time=0;    //total turnaround time
		double max_pque_waiting_time =0;
		int max_p_id=0;
		double min_mque_waiting_time=0;
		int min_m_id=0;
		int pNum= in.nextInt();
		in.nextLine();
				
	    	for (int i=0; i<pNum; i++) {
			String line = in.nextLine();
			String[] list = line.split(" ");
			Player player = new Player(Integer.parseInt(list[0]), Integer.parseInt(list[1]));
			players.add(player);
		}
	  	//number of arrivals
	    	int arrivals =in.nextInt();
		in.nextLine();
		
		PriorityQueue<event> events = new PriorityQueue<event>(new comparing());
		for (int i=0; i<arrivals; i++) {
			String line = in.nextLine();
	    		String[] list = line.split(" ");
			event event = new event(list[0],Integer.parseInt(list[1]), Double.parseDouble(list[2]), Double.parseDouble(list[3]));
			events.add(event);
		}
		
		int phys_num = in.nextInt();
		for (int i=0; i<phys_num; i++) {
			Physiotherapist ph = new Physiotherapist(i, in.nextDouble(), true);
			physiotherapists.add(ph);
		}
		int num_coach = in.nextInt();
		for (int i=0; i< num_coach; i++) {
			trainingCoach tr = new trainingCoach(i, true);
			trainers.add(tr);
		}
		int masseur = in.nextInt();
		for (int i=0; i< masseur; i++) {
			massageCoach mas = new massageCoach(i, true);
			massagers.add(mas);
		}

		while (!events.isEmpty()) {       	
		    event curr = events.poll();            
		    Player player = players.get(curr.getId());        
		    current_time = curr.getEntr_time();

		    //event is training
		    if(curr.event_type.equals("t")) {

			//checking if there is available trainer
			boolean isAvailable=false;
			int trainer_id=-1;
			for(int i=0; i<trainers.size();i++) {
				if (trainers.get(i).available==true) {
					isAvailable=true;
					trainer_id = i;
					break;
				}
			}
			if(!physque.contains(player) && !trainingque.contains(player) && !massageque.contains(player) && !player.inService) {
				player.training_time = curr.getDuration();
				player.setEnter_tr_que(current_time);

				if(!isAvailable) {
					trainingque.add(player);	
				if (trainingque.size()>max_tr_que_len) {
					max_tr_que_len = trainingque.size();  // max size of tr queue
				}	
			}
			else {
				player.setEnter_tr_service(current_time);
				trainers.get(trainer_id).setAvailable(false);
				player.setTrainer_id(trainer_id);
				player.inService=true;

				player.tr_que_waiting_time+= current_time-player.getEnter_tr_que();
				event leave_training = new event("leave_t", player.getID(), current_time + player.getTraining_time());

				events.add(leave_training);
				total_t_time+= player.training_time;
				}
			}
			else {
				cancelled_attempts += 1;
			}	
		    }
		    //event is leave training
		    else if(curr.event_type.equals("leave_t")) {

			num_of_tr_event+=1;
			int trainer_id= player.getTrainer_id();

			event enter_phys = new event("p", player.getID(), current_time);
			events.add(enter_phys);

			if (!trainingque.isEmpty()) {  //there are some players in que so put them in service, and create leave event for them.
				Player temp = trainingque.peek();
				trainingque.poll();
				Player p1 = players.get(temp.getID());
				player.inService=false;
				p1.tr_que_waiting_time+= current_time-p1.getEnter_tr_que();
				p1.setTrainer_id(trainer_id);
				p1.inService=true;
				event leave_tr = new event("leave_t", p1.getID(), current_time + p1.getTraining_time());
				events.add(leave_tr);
				total_t_time+=p1.getTraining_time();
			}
			else {
				trainers.get(trainer_id).setAvailable(true);  //no one in the que just go:)
				player.inService = false;
			}
		    }           
		    //event is physiotherapy
		    else if(curr.event_type.equals("p")) {
			player.setEnter_phys_que(current_time);
			int phys_id= -1;  
			boolean isPhysAvailable = false;
			for(int i=0; i<physiotherapists.size(); i++) {
				if (physiotherapists.get(i).isAvailable()==true) {
					phys_id = i;
					isPhysAvailable = true;
					break;
				}
			}
			if (!isPhysAvailable) {

				  physque.add(player);
				if (physque.size()>max_phys_que_len) {
					max_phys_que_len = physque.size();
				}
			}         	
			else { //there are some available desks.
				player.setEnter_phys_service(current_time);
				physiotherapists.get(phys_id).setAvailable(false);
				player.setPhys_id(phys_id);
				player.inService=true;
				player.phys_que_waiting_time+=current_time-player.getEnter_phys_que();
				event leave_phys = new event("leave_p", player.getID(), current_time + physiotherapists.get(phys_id).getServiceTime());
				events.add(leave_phys);
				total_p_time+= physiotherapists.get(phys_id).getServiceTime();           		
			}            		
		    }
		    //event is leave physiotherapy
		    else if(curr.getEvent_type().equals("leave_p")) {
			num_of_p_event+=1;
			total_turnaround_time += current_time - player.getEnter_tr_que();

			int phys_id = player.getPhys_id();
			if(!physque.isEmpty()) {
				Player temp = physque.peek();
				physque.poll();

				Player p1= players.get(temp.getID());

			    boolean isPhysAvailable = false;
			    for(int i=0; i<physiotherapists.size(); i++) {
				if (physiotherapists.get(i).isAvailable()==true) {
					phys_id = i;
					isPhysAvailable = true;
					break;
				}
			    }

			    player.inService=false;
			    p1.phys_que_waiting_time+=current_time-p1.getEnter_phys_que();
			    p1.setPhys_id(phys_id);
			    p1.inService=true;
				
			    event leave_p = new event("leave_p", p1.getID(),current_time+ physiotherapists.get(phys_id).getServiceTime());
			    events.add(leave_p);
			    total_p_time+= physiotherapists.get(phys_id).getServiceTime();
			    if(Math.abs(p1.getPhys_que_waiting_time() -max_pque_waiting_time)>0.0000000001 && p1.getPhys_que_waiting_time() >max_pque_waiting_time) {
				max_pque_waiting_time=p1.getPhys_que_waiting_time();
				max_p_id=p1.getID();
			    }
			}
			else {
				physiotherapists.get(phys_id).setAvailable(true);
				player.inService = false;
			}            	
		    }            
		    //event is massage
		    else if (curr.getEvent_type().equals("m")) {
			if(player.num_of_massage_ser>=3) {
				invalid_attempts+=1;
				continue;
			}            	
			int mas_id = 0;
			boolean isAvailable=false;
			for(int i = 0; i<massagers.size();i++) {
			    if(massagers.get(i).isAvailable() == true) {
				isAvailable = true;
				mas_id = i;
				break;
			    }
			}
			if(!physque.contains(player) && !trainingque.contains(player)  && !massageque.contains(player)&& !player.inService) {
				player.setEnter_massage_que(current_time);
				player.num_of_massage_ser+=1;
				player.massage_time = curr.getDuration(); 
			    if(isAvailable==false) 
				massageque.add(player);
				if(massageque.size()>max_mas_que_len) {
					max_mas_que_len = massageque.size();
				}
			    }
			    else {  
				massagers.get(mas_id).setAvailable(false);
				player.setMas_id(mas_id);
				player.inService = true;
				player.mas_que_waiting_time+= current_time- player.getEnter_massage_que();
				event leave_m = new event("leave_m", player.getID(), current_time + player.massage_time);
				events.add(leave_m);
				total_m_time+= player.massage_time;

			    } 
			}
			else {
				cancelled_attempts+=1;	
			}	
		    }
		    //event is leave massage
		    else if(curr.getEvent_type().equals("leave_m")) {
			num_of_m_event+=1;
			int mas_id = player.getMas_id();
			if (!massageque.isEmpty()){
				Player temp = massageque.peek();
				massageque.poll();
				Player p1 = players.get(temp.getID());
				p1.mas_que_waiting_time+= current_time- p1.getEnter_massage_que();
				p1.total_mque_waiting+=current_time- p1.getEnter_massage_que();
				player.inService=false;
				p1.setMas_id(mas_id);
				p1.inService=true;
				event leave_m = new event("leave_m", p1.getID(), current_time + p1.massage_time);
				events.add(leave_m);
				total_m_time+=p1.massage_time;	
			}
			else {
				massagers.get(mas_id).setAvailable(true);
				player.inService = false;
			}
		    } 
		}
		out.println(max_tr_que_len);
		out.println(max_phys_que_len);
		out.println(max_mas_que_len);

		double avg_tr_waiting=0;
		for (int i=0 ; i<players.size();i++) {
			avg_tr_waiting += players.get(i).getTr_que_waiting_time();
		}
		if(num_of_tr_event!=0) {
		out.println(String.format("%.3f",avg_tr_waiting/(double)num_of_tr_event));}
		else {
			out.println(String.format("%.3f",0.0));
		}

		double avg_p_waiting = 0;
		for (int i=0 ; i<players.size();i++) {
			avg_p_waiting += players.get(i).getPhys_que_waiting_time();
		}
		if(num_of_p_event!=0) {
		out.println(String.format("%.3f",avg_p_waiting/(double) num_of_p_event));}
		else {
			out.println(String.format("%.3f",0.0));
		}

		double avg_m_waiting = 0;
		for (int i=0 ; i<players.size();i++) {
			avg_m_waiting += players.get(i).getMas_que_waiting_time();
		}
		if(num_of_m_event!=0) {
		out.println(String.format("%.3f",avg_m_waiting/( double) num_of_m_event));}
		else {
			out.println(String.format("%.3f",0.0));
		}

		if (num_of_tr_event!=0) {
		out.println(String.format("%.3f",total_t_time/(double) num_of_tr_event)); }//7
		else{
			out.println(String.format("%.3f",0.0));
		}

		if(num_of_p_event!=0) {
		out.println(String.format("%.3f",total_p_time/(double) num_of_p_event)); }  //8
		else{
			out.println(String.format("%.3f",0.0));
		}

		if(num_of_m_event!=0) {
		out.println(String.format("%.3f",total_m_time/(double) num_of_m_event));  } //9
		else{
			out.println(String.format("%.3f",0.0));
		}

		if(num_of_p_event!=0) {
		out.println(String.format("%.3f",total_turnaround_time/(double) (num_of_p_event))); }//10
		else{
			out.println(String.format("%.3f",0.0));
		}
		out.println(max_p_id + " " + String.format("%.3f",max_pque_waiting_time)); //11

		for(int i=0;i<players.size();i++) {
			if (players.get(i).num_of_massage_ser==3) {
				min_m_id=i;
				min_mque_waiting_time = players.get(i).total_mque_waiting;
				break;
			}
		}
		 boolean player_exists = false;
		for(int i=0;i<players.size();i++) {
			if (players.get(i).num_of_massage_ser==3 && (Math.abs(players.get(i).total_mque_waiting - min_mque_waiting_time) > 0.0000000001) && players.get(i).total_mque_waiting < min_mque_waiting_time) {
				min_m_id=i;
				min_mque_waiting_time = players.get(i).total_mque_waiting;
			}
			if(players.get(i).num_of_massage_ser==3) {
				player_exists= true;
			}
		}
		if (player_exists) {
			out.println(min_m_id + " " + String.format("%.3f",min_mque_waiting_time)); //12
		}
		else {
		out.println("-1" + " " + "-1");
		} //12
		out.println(invalid_attempts); //13
		out.println(cancelled_attempts);  //14
		out.println(String.format("%.3f",current_time));  //15
}
     }
