package visitor;

import tsotne.ZLangBaseVisitor;
import tsotne.ZLangParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tsotne.putkaradze on 22-Dec-15.
 */
public class ZLangMainVisitor extends ZLangBaseVisitor {

    HashMap<String, Object> varmap = new HashMap<String, Object>();

    @Override
    public Object visitProgram(ZLangParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public Object visitMethod(ZLangParser.MethodContext ctx) {
        return super.visitMethod(ctx);
    }

    @Override
    public Object visitStmnt(ZLangParser.StmntContext ctx) {
        return super.visitStmnt(ctx);
    }

    @Override
    public Object visitIfStmt(ZLangParser.IfStmtContext ctx) {
        return super.visitIfStmt(ctx);
    }

    @Override
    public Object visitWhileStmt(ZLangParser.WhileStmtContext ctx) {
        return super.visitWhileStmt(ctx);
    }

    @Override
    public Object visitForStmt(ZLangParser.ForStmtContext ctx) {
        return super.visitForStmt(ctx);
    }

    @Override
    public Object visitAssignStmt(ZLangParser.AssignStmtContext ctx) {
        for (int i = 0; i < ctx.q.size(); i++) {
            varmap.put(ctx.variable(i).getText().toString(), Integer.parseInt(ctx.expression().getText()));
        }
        return super.visitAssignStmt(ctx);
    }

    @Override
    public Object visitPrintStmt(ZLangParser.PrintStmtContext ctx) {

        if (ctx.q.size() == 0) {
            System.out.println("<<<START PRINTING ALL VARIABLES WITH VALUES>>>");
            for (Map.Entry<String, Object> entry : varmap.entrySet()) {
                String key = entry.getKey().toString();
                Object value = entry.getValue();
                System.out.println("KEY: " + key + "\t VALUE: " + value);
            }
            System.out.println("<<<END PRINTING ALL VARIABLES WITH VALUES>>>");
        } else {
            for (int i = 0; i < ctx.q.size(); i++) {
                String str = ctx.expression(i).getChild(0).getText();
                System.out.println(str + "\t : " + varmap.get(str));
            }
        }
        return super.visitPrintStmt(ctx);
    }

    @Override
    public Object visitNString(ZLangParser.NStringContext ctx) {
        return super.visitNString(ctx);
    }

    @Override
    public Object visitOpExpression(ZLangParser.OpExpressionContext ctx) {

//        int left = Integer.parseInt(visit (ctx.expression(0)).toString());
//        int right = Integer.parseInt(visit (ctx.expression(1)).toString());

//        switch (ctx.op.getType()){
//            case ZLangParser.ADD : {
//                return left + right;
//            }
//            case ZLangParser.SUB : {
//                return left - right;
//            }
//        }

        return super.visitOpExpression(ctx);
    }

    @Override
    public Object visitNVariable(ZLangParser.NVariableContext ctx) {
        return varmap.get(ctx.variable().WORD().getText());
    }

    @Override
    public Object visitNNUMBER(ZLangParser.NNUMBERContext ctx) {
        return super.visitNNUMBER(ctx);
    }

    @Override
    public Object visitString(ZLangParser.StringContext ctx) {
        return super.visitString(ctx);
    }

    @Override
    public Object visitVariable(ZLangParser.VariableContext ctx) {
        return super.visitVariable(ctx);
    }
}
