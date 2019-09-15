package Chat.Request;

import Chat.Constant.Request;

public class JoinGroup {

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
