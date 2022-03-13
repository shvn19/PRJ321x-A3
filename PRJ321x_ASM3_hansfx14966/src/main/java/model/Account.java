package model;

public class Account {
	private String usr, pwd;
	private int role;
	private String name, address, phone;
	private int check;

	public Account() {
	}

	public Account(String usr, String pwd, String name, String address, String phone) {
		super();
		this.usr = usr;
		this.pwd = pwd;
		this.role = 2;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public String validate() {
		String message = "";
		if (usr.trim().equals("")) {
			message += "Email cann't be empty\n";
		}

		if (pwd.trim().equals("")) {
			message += "password cant be empty\n";
		}

		if (!usr.trim().matches(
				"\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")) {
			message += "Invalid email address\n";

		}

		if (pwd.length() < 8) {
			message += "Password must be at lease 8 characters\n";
		} else if (pwd.matches("\\w*\\s+\\w*")) {
			message += "Password cannot contain space\n";
		}

		if (name.trim().length() == 0) {
			message += "Name cannot be blank\n";
		}

		if (address.trim().length() == 0) {
			message += "Address cannot be blank\n";
		}

		if (phone.trim().length() == 0) {
			message += "phone cannot be blank\n";
		}

		return message;

	}

}
