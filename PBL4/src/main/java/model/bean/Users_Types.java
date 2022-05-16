package model.bean;

public class Users_Types {
	private int IDUser;
	private int IDNew;

	public Users_Types(int IDUser, int IDNew) {
		this.IDUser = IDUser;
		this.IDNew = IDNew;
	}

	public int getIDUser() {
		return IDUser;
	}

	public void setIDUser(int IDUser) {
		this.IDUser = IDUser;
	}

	public int getIDNew() {
		return IDNew;
	}

	public void setIDNew(int IDNew) {
		this.IDNew = IDNew;
	}
}