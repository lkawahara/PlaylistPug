package models;

public enum Tags 
{
	//provide display name for display names of more than one word or with special characters
		//all display names will be lower case
	Asian,
	AvantGarde("avant-garde"),
	Blues,
	Classical,
	Country,
	EasyListening("easy listening"),
	Electronic,
	HipHop("hip-hop"), 
	Instrumental,
	Jazz,
	Latin,
	Pop,
	Punk,
	Rap,
	RB("r & b"),
	Reggae,
	Rock,
	Ska,
	Soul;
	
	private String displayName;
	Tags(){
		this.displayName = this.toString().toLowerCase();
	}
	Tags(String displayName){
		this.displayName = displayName.toLowerCase();
	}
	public String GetDisplayName(){
		return this.displayName;
	}
}
