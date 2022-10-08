import java.util.PriorityQueue;

public class Player {

	private int ID;
	int skill_level;
	int num_of_massage_ser=0;
	
	double enter_tr_que =0;  //time for entering tr. queue
	double enter_tr_service =0;  // time for entering tr. service
	double enter_phys_que =0;    //time for entering phys. queue
	double enter_phys_service=0;   //time for entering phys service
	double enter_massage_que;      //time for entering tr. queue
	double enter_massage_service =0;
	double training_time =0;
	double massage_time=0;
	int trainer_id=0;
	int phys_id=0;
	int mas_id = 0; 
	double tr_que_waiting_time=0;
	double phys_que_waiting_time=0;
	double mas_que_waiting_time = 0;
	double turnaround_time=0;
	double total_mque_waiting=0;
	
	boolean inService= false;
	
	
	public double getMas_que_waiting_time() {
		return mas_que_waiting_time;
	}


	public void setMas_que_waiting_time(double mas_que_waiting_time) {
		this.mas_que_waiting_time = mas_que_waiting_time;
	}


	public int getMas_id() {
		return mas_id;
	}


	public void setMas_id(int mas_id) {
		this.mas_id = mas_id;
	}


	public double getPhys_que_waiting_time() {
		return phys_que_waiting_time;
	}


	public void set_Phys_que_waiting_time(double phys_que_waiting_time) {
		this.phys_que_waiting_time = phys_que_waiting_time;
	}


	public double getTurnaround_time() {
		return turnaround_time;
	}


	public void setTurnaround_time(int turnaround_time) {
		this.turnaround_time = turnaround_time;
	}


	public int getPhys_id() {
		return phys_id;
	}


	public void setPhys_id(int phys_id) {
		this.phys_id = phys_id;
	}


	
	
	

	
	public double getTr_que_waiting_time() {
		return tr_que_waiting_time;
	}


	public void setTr_que_waiting_time(double tr_que_waiting_time) {
		this.tr_que_waiting_time = tr_que_waiting_time;
	}


	public int getTrainer_id() {
		return trainer_id;
	}


	public void setTrainer_id(int trainer_id) {
		this.trainer_id = trainer_id;
	}


	public double getTraining_time() {
		return training_time;
	}


	public void setTraining_time(double training_time) {
		this.training_time = training_time;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getSkill_level() {
		return skill_level;
	}


	public void setSkill_level(int skill_level) {
		this.skill_level = skill_level;
	}


	public double getEnter_tr_que() {
		return enter_tr_que;
	}


	public void setEnter_tr_que(double enter_tr_que) {
		this.enter_tr_que = enter_tr_que;
	}


	public double getEnter_tr_service() {
		return enter_tr_service;
	}


	public void setEnter_tr_service(double enter_tr_service) {
		this.enter_tr_service = enter_tr_service;
	}


	public double getEnter_phys_que() {
		return enter_phys_que;
	}


	public void setEnter_phys_que(double enter_phys_que) {
		this.enter_phys_que = enter_phys_que;
	}


	public double getEnter_phys_service() {
		return enter_phys_service;
	}


	public void setEnter_phys_service(double enter_phys_service) {
		this.enter_phys_service = enter_phys_service;
	}


	public double getEnter_massage_que() {
		return enter_massage_que;
	}


	public void setEnter_massage_que(double enter_massage_que) {
		this.enter_massage_que = enter_massage_que;
	}


	public double getEnter_massage_service() {
		return enter_massage_service;
	}


	public void setEnter_massage_service(double enter_massage_service) {
		this.enter_massage_service = enter_massage_service;
	}


	public Player(int ID,  int skill_level) {
		this.ID=ID;
		this.skill_level= skill_level;
	}
	
	public int getNum_of_massage_ser() {
		return num_of_massage_ser;
	}


	public void setNum_of_massage_ser(int num_of_massage_ser) {
		this.num_of_massage_ser = num_of_massage_ser;
	}
}
