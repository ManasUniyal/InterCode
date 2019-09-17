package Editor;

public class TrieNode {
	TrieNode[] children;
	char label;
	boolean terminal;
	String word;

	private static int ALPHABET_SIZE = 256;

	public TrieNode() {
		this.children = new TrieNode[ALPHABET_SIZE];
	}

	public TrieNode(char l) {
		this();
		this.label = l;
	}
}