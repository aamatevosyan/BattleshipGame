package hse.edu.battleship.ui;

import javafx.scene.control.TextArea;

import java.io.PrintStream;
import java.util.Objects;

/**
 * PrintStreamCapturer
 */
public class PrintStreamCapturer extends PrintStream {

    /**
     * TextArea
     */
    private final TextArea text;

    /**
     * Indentation
     */
    private final String indent;

    /**
     * True if line starts, false otherwise
     */
    private boolean atLineStart;

    /**
     * Default constructor
     *
     * @param textArea       text area
     * @param capturedStream stream to capture
     * @param indent         indentation
     */
    public PrintStreamCapturer(TextArea textArea, PrintStream capturedStream, String indent) {
        super(capturedStream);
        this.text = textArea;
        this.indent = indent;
        this.atLineStart = true;
    }

    /**
     * Default constructor
     *
     * @param textArea       text area
     * @param capturedStream stream to capture
     */
    public PrintStreamCapturer(TextArea textArea, PrintStream capturedStream) {
        this(textArea, capturedStream, "");
    }

    /**
     * Writes string to text area
     *
     * @param str string
     */
    private void writeToTextArea(String str) {
        if (text != null) {
            synchronized (text) {
                text.appendText(str);
            }
        }
    }

    /**
     * Writes string using indentation
     *
     * @param str string
     */
    private void write(String str) {
        String[] s = str.split("\n", -1);
        if (s.length == 0)
            return;
        for (int i = 0; i < s.length - 1; i++) {
            writeWithPotentialIndent(s[i]);
            writeWithPotentialIndent("\n");
            atLineStart = true;
        }
        String last = s[s.length - 1];
        if (!last.equals("")) {
            writeWithPotentialIndent(last);
        }
    }

    /**
     * Writes string using indentation
     *
     * @param s string
     */
    private void writeWithPotentialIndent(String s) {
        if (atLineStart) {
            writeToTextArea(indent + s);
            atLineStart = false;
        } else {
            writeToTextArea(s);
        }
    }

    /**
     * Writes new line
     */
    private void newLine() {
        write("\n");
    }

    /*
     * General override functions that prints to captured stream and also to text area
     */

    @Override
    public void print(boolean b) {
        synchronized (this) {
            super.print(b);
            write(String.valueOf(b));
        }
    }

    @Override
    public void print(char c) {
        synchronized (this) {
            super.print(c);
            write(String.valueOf(c));
        }
    }

    @Override
    public void print(char[] s) {
        synchronized (this) {
            super.print(s);
            write(String.valueOf(s));
        }
    }

    @Override
    public void print(double d) {
        synchronized (this) {
            super.print(d);
            write(String.valueOf(d));
        }
    }

    @Override
    public void print(float f) {
        synchronized (this) {
            super.print(f);
            write(String.valueOf(f));
        }
    }

    @Override
    public void print(int i) {
        synchronized (this) {
            super.print(i);
            write(String.valueOf(i));
        }
    }

    @Override
    public void print(long l) {
        synchronized (this) {
            super.print(l);
            write(String.valueOf(l));
        }
    }

    @Override
    public void print(Object o) {
        synchronized (this) {
            super.print(o);
            write(String.valueOf(o));
        }
    }

    @Override
    public void print(String s) {
        synchronized (this) {
            super.print(s);
            write(Objects.requireNonNullElse(s, "null"));
        }
    }

    @Override
    public void println() {
        synchronized (this) {
            newLine();
            super.println();
        }
    }

    @Override
    public void println(boolean x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(char x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(int x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(long x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(float x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(double x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(char[] x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
            super.println();
        }
    }

    @Override
    public void println(Object x) {
        String s = String.valueOf(x);
        synchronized (this) {
            print(s);
            newLine();
            super.println();
        }
    }
}
