package Chat.Constant;

public enum Request {

	WHOIAM("0"),
	GROUPPASS("1"),
	RESPONSE("2"),
	GROUPLIST("3"),
	CREATEGROUP("4"),
	JOINGROUP("5"),
	MESSAGE("6"),
	GROUPJOINED("7");


	Request(String s){
		s.toString();
	}
}
