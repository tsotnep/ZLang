package visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import tsotne.ZLangBaseVisitor;
import tsotne.ZLangParser;
import tsotne.ZLangParser.DeclarationContext;

import java.util.HashMap;

/**
 * Created by tsotne.putkaradze on 22-Dec-15.
 */


public class ZLangMainVisitor extends ZLangBaseVisitor {
    private enum Types {
        INT, DBL, STR
    }

    public void putToVarMap(TerminalNode vname, Object vvalue, TerminalNode varType) {
        Types type = Types.valueOf(varType.getText());
        switch (type) {
            case INT: {
                varMap.put(vname.getText(), Integer.parseInt(vvalue.toString()));
            }
            case DBL: {
                varMap.put(vname.getText(), Double.parseDouble(vvalue.toString()));
            }
            case STR: {
                varMap.put(vname.getText(), (vvalue.toString()));
            }
            default:
        }
    }

    public Object getFromVarMap(TerminalNode key){
        return varMap.get(key.getText());
    }

    HashMap<String, Object> varMap = new HashMap<String, Object>();


    @Override
    public Object visitDeclaration(DeclarationContext ctx) {
        putToVarMap(ctx.VNAME(), this.visit(ctx.expression()), ctx.VTYPE());
        return super.visitDeclaration(ctx);
    }

    @Override
    public Object visitAVariable(ZLangParser.AVariableContext ctx) {
        return getFromVarMap(ctx.VNAME());
    }

    @Override
    public Object visitAValue(ZLangParser.AValueContext ctx) {
        String str = ctx.VVALUE().toString();
        boolean flag = str.matches(".*[a-zA-Z]+.*"); //TODO: check if this works
        if (flag == true) return str;
        if (str.indexOf('.') == -1) return Integer.parseInt(str);
        return Double.parseDouble(str);
    }

    @Override
    public Object visitOpExpression(ZLangParser.OpExpressionContext ctx) {
        Object lhs = this.visit(ctx.expression(0));
        Object rhs = this.visit(ctx.expression(1));
        //TODO: now, i should check which type is i'm working with, and then do it, like:
        // if it's "+" i check if it's Integer, Double or String, and then perform
        // but it's not intelligent, so i won't do it.

        return super.visitOpExpression(ctx);
    }
}
