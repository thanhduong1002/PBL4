package model.bean;

public class News {
	private int ID;
	private String Title;
	private String DateP;
	private String Link;
	private String LinkImg;
	private String Type;
	private int Group;
	private String Label;

	public News(int iD, String title, String date, String link, String linkImg, String type, int group) {
		ID = iD;
		Title = title;
		DateP = date;
		Link = link;
		LinkImg = linkImg;
		Type = type;
		Group = group;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDateP() {
		return DateP;
	}

	public void setDateP(String dateP) {
		DateP = dateP;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public String getLinkImg() {
		return LinkImg;
	}

	public void setLinkImg(String linkImg) {
		LinkImg = linkImg;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		this.Type = type;
	}

	public int getGroup() {
		return Group;
	}

	public void setGroup(int group) {
		Group = group;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	@Override
	public String toString() {
		return "News [ID=" + ID + ", Title=" + Title + ", DateP=" + DateP + ", Link=" + Link + ", LinkImg=" + LinkImg
				+ ", Type=" + Type + ", Group=" + Group + ", Label=" + Label + "]";
	}
}
