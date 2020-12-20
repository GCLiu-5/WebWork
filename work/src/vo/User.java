package vo;

public class User {
	private String phone;
	private String passWord;
	
	public User(String phone, String passWord) {
		super();
		this.phone = phone;
		this.passWord = passWord;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [phone=" + phone + ", passWord=" + passWord + "]";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
