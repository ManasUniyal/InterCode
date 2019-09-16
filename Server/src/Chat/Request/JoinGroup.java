package Chat.Request;

import Chat.Constant.Request;

import java.io.Serializable;

public class JoinGroup implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JoinGroup(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.JOINGROUP);
	}

}
