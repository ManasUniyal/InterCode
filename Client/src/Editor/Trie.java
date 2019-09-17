package Editor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Trie{
	private TrieNode root;

	public Trie() {
		this.root = new TrieNode(' ');
	}

	public void addWord(String word) {
		char[] cArray = word.toLowerCase().toCharArray();
		TrieNode temp = root;
		TrieNode tn = null;
		int index = 0;

		do {
			tn = temp.children[cArray[index]];
			if (tn != null) {
				temp = tn;
				index++;
				// if the word got over, then it is already in the trie.
				if (index >= word.length()) {
					temp.terminal = true;
					temp.word = word;
					return;
				}
			}
		} while (tn != null);

		// temp is the last node and the word to add is lengthier
		System.out.println(cArray);
		for (; index < cArray.length; index++) {
			temp.children[cArray[index]] = new TrieNode(cArray[index]);
			temp = temp.children[cArray[index]];
		}

		temp.terminal = true;
		temp.word = word;

	}

	public String[] wordsByPrefix(String prefix) {
		char[] cArray = prefix.toLowerCase().toCharArray();
		TrieNode temp = root;
		TrieNode tn = null;
		int index = 0;

		do {
			tn = temp.children[cArray[index]];
			// if you reached the end of the string, then no words with this prefix
			if (tn == null) {
				return null;
			}

			index++;
			temp = tn;
		} while (index < cArray.length);

		// temp is at the node representing the last char of the prefix
		// do a traversal for all paths below this.
		List<String> words = new ArrayList<String>();
		Deque<TrieNode> DQ = new ArrayDeque<TrieNode>();
		DQ.addLast(temp);
		while (!DQ.isEmpty()) {
			TrieNode first = DQ.removeFirst();
			if(first.terminal){
				words.add(first.word);
			}

			for(TrieNode n : first.children){
				if(n != null){
					DQ.add(n);
				}
			}
		}

		return words.toArray(new String[0]);
	}
}
