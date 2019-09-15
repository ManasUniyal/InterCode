package Chat.Request;

import Chat.Constant.Request;

import java.io.Serializable;

public class CreateGroup implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateGroup(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.CREATEGROUP);
	}

}
