package Editor;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class AutoComplete implements DocumentListener {

    private ArrayList<String> brackets = new ArrayList<>();
    private ArrayList<String> bracketCompletions = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();

    SupportedKeywords kw;

    private enum Mode {
        INSERT, COMPLETION
    };

    private Mode mode = Mode.INSERT;
    private JTextArea textArea;
    private boolean isKeyword;
    private int pos;
    private String content;
    private Trie trie;
    private int TAB=0;

    public AutoComplete(JTextArea textArea , ArrayList<String> al) {

        words = al;
        kw = new SupportedKeywords();
        brackets = kw.getbrackets();
        bracketCompletions = kw.getbracketCompletions();

        this.textArea = textArea;

        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "Action");
        am.put("Action", new CommitAction());

        trieSetUp();

    }

    private void trieSetUp() {
        trie = new Trie();

        for (String X: words) {
            trie.addWord(X);
        }

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        pos = e.getOffset();
        content = null;

        try {
            content = textArea.getText(0, pos + 1);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        if (e.getLength() != 1) {
            return;
        }

        checkForBracket();

        int start;
        for (start = pos; start >= 0; start--) {
            if (!Character.isLetter(content.charAt(start))) {
                break;
            }
        }

        if (pos - start < 2) {
            return;
        }

        String prefix = content.substring(start + 1);
        int n = Collections.binarySearch(words, prefix);

        String[] got = trie.wordsByPrefix(prefix);
//        System.out.println(got.length);

        if (got == null){
            return;
        }

        if (got.length > 0) {
            String match = got[0];
            if (match.equals(String.valueOf(prefix))){
                return;
            }
            System.out.println(match);
            if (match.startsWith(prefix)) {
                String completion = match.substring(pos - start);
                System.out.println(pos-start);
                isKeyword = true;
                SwingUtilities.invokeLater(
                        new CompletionTask(completion, pos + 1));
            } else {
                mode = Mode.INSERT;
            }
        }
    }


    private void checkForBracket() {
        //String of the last typed character
        char c = content.charAt(pos);
        String s = String.valueOf(c);

        for (int i = 0; i < brackets.size(); i++) {
            if (brackets.get(i).equals(s)) {
                isKeyword = false;
                if (s.charAt(0) == '{') {
                    TAB++;
                }else if (s.charAt(0) =='}'){
                    TAB--;
                }
                SwingUtilities.invokeLater(
                        new CompletionTask(bracketCompletions.get(i), pos + 1));
            }
        }
    }

    private ArrayList<String> getKeywords() {
        return words;
    }

    private void setKeywords(String keyword) {
        words.add(keyword);
    }

    private class CompletionTask implements Runnable {

        private final String completion;
        private final int position;

        public CompletionTask(String completion, int position) {
            this.completion = completion;
            this.position = position;
        }

        @Override
        public void run() {
            textArea.insert(completion, position);

            textArea.setCaretPosition(position + completion.length());
            textArea.moveCaretPosition(position);
            mode = Mode.COMPLETION;
            if (!isKeyword) {
                textArea.addKeyListener(new HandleBracketEvent());
            }
        }
    }

    private class CommitAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (mode == Mode.COMPLETION) {
                int pos = textArea.getSelectionEnd();

                if (isKeyword) {
                    textArea.insert(" ", pos);
                    textArea.setCaretPosition(pos + 1);
                    mode = Mode.INSERT;
                }
            } else {
                mode = Mode.INSERT;
                textArea.replaceSelection("\n");
                int x=0;
                String str = textArea.getText();
                for(int i=0;i<(int)textArea.getCaretPosition();i++){
                    if(str.charAt(i) == '{'){
                        x++;
                    }if(str.charAt(i) == '}'){
                        x--;
                    }
                }
                System.out.println(x);
                for (int i=0;i<x;i++)
                textArea.insert("            ",textArea.getCaretPosition());
            }
        }
    }

    private class HandleBracketEvent implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            //Bracket auto complete needs special attention.
            //Multiple possible responses are needed.
            String keyEvent = String.valueOf(e.getKeyChar());
            for (String bracketCompletion : bracketCompletions) {
                if (keyEvent.equals(bracketCompletion)) {
                    textArea.replaceRange("", pos, pos + 1);
                    mode = Mode.INSERT;
                    textArea.removeKeyListener(this);
                }
            }
            int currentPosition = textArea.getCaretPosition();
            switch (e.getKeyChar()) {
                case '\n':
                    textArea.insert("\n\n", currentPosition);
                    textArea.setCaretPosition(currentPosition + 1);
                    mode = Mode.INSERT;
                    textArea.removeKeyListener(this);
                    break;
                default:
                    textArea.setCaretPosition(pos);
                    mode = Mode.INSERT;
                    textArea.removeKeyListener(this);
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}
