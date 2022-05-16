package model.bean;

public class Users {
	private int IDUser;
	private String Mail;
	private String Pass;

	public Users(int ID, String mail, String pass) {
		this.IDUser = ID;
		this.Mail = mail;
		this.Pass = pass;
	}

	public int getIDUser() {
		return IDUser;
	}

	public void setIDUser(int ID) {
		this.IDUser = ID;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		this.Mail = mail;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		this.Pass = pass;
	}
}