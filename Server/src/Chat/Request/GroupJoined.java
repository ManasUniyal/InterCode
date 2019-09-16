package Chat.Request;

import Chat.Constant.Request;

import java.io.Serializable;

public class GroupJoined implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroupJoined(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.valueOf(Request.GROUPJOINED);
	}

}
