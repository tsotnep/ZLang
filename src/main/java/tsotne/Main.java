package tsotne;

/**
 * Created by tsotne.putkaradze on 22-Dec-15.
 */

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import visitor.ZLangMainVisitor;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static final String INPUT_FILE = "C:\\git\\ZLang\\ZLang\\src\\test\\java\\input1.txt";
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream(INPUT_FILE);
            ZLangLexer lexer;

            try {
                lexer = new ZLangLexer(new ANTLRInputStream(fileInputStream));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ZLangParser parser = new ZLangParser(tokens);
                ParserRuleContext ruleContext = parser.program();

                //draw a tree
                Trees.inspect(ruleContext, parser);

                //visitor
                ZLangMainVisitor visitor = new ZLangMainVisitor();
                visitor.visit(ruleContext);


//                //listener
//                ZlangMainListener listener = new ZlangMainListener();
//                ParseTreeWalker treeWalker = new ParseTreeWalker();
//                treeWalker.walk(listener, ruleContext);

                //TODO: implement beautiful file writer

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found.");
            return;
        }
    }
}