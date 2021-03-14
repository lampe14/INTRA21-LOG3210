package analyzer.visitors;

import analyzer.ast.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created: 28-02-21
 * Author: Esther Guerrier
 * <p>
 * Description: Ce visiteur explorer l'AST de EstheRust et génère son équivalent en Python
 */

public class EstheRustConvertorVisitor implements ParserVisitor {

    private final PrintWriter m_writer;

    private int indentationLevel = 0;
    private static final String INDENT = "\t";

    /*
        ceci n'est pas nécessaire à être utilisée pour imprimer le code. Utilisez-le si cela vous intéresse, sinon,
        vous pouvez imprimer au fur et à mesure avec m_writer. Pour ajouter une valeure (string, int, etc) dans le StringBuilder, utilisez
        la méthode append. Pour afficher avec m_writer, utiliser la méthode toString()
     */
    private StringBuilder code = new StringBuilder();


    public EstheRustConvertorVisitor(PrintWriter writer) {
        m_writer = writer;
    }


    /**
     * Permet de mettre la première lettre d'un string en CAPS. Exemple: allo -> Allo
     * @param str: la string qu'on veut que sa première lettre soit en majuscule
     * @return la string avec la première lettre en majuscule
     */
    private String capitalize(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTStmt node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        m_writer.print(node.getValue());
        return null;
    }

    @Override
    public Object visit(ASTPrimitiveExpr node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTStringValue node, Object data) {
        m_writer.print(node.getValue());
        return null;
    }

    @Override
    public Object visit(ASTExpr node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }


    private void printWhile(SimpleNode node, Object data) {
        m_writer.print("while ");
        node.jjtGetChild(0).jjtAccept(this,data);
        m_writer.println(":");
        indentationLevel++;
        if(node.jjtGetChild(1).jjtGetNumChildren() == 0)
            m_writer.println(INDENT + "pass");
        else {
            node.jjtGetChild(1).jjtAccept(this,data);
            indentationLevel--;
        }
    }

    @Override
    public Object visit(ASTWhileStmt node, Object data) {
        if(indentationLevel >= 1) {
            for(int i = 0; i < indentationLevel; i++) {
                m_writer.print(INDENT);
                printWhile(node, data);
            }
        } else
            printWhile(node, data);

        return null;
    }

    @Override
    public Object visit(ASTCoeurStmt node, Object data) {
        if(indentationLevel >= 1) {
            for(int i = 0; i < indentationLevel; i++)
                m_writer.println(INDENT + "print(\"Compilateurs est le meilleur cours au monde\")");
        } else
            m_writer.println("print(\"Compilateurs est le meilleur cours au monde\")");

        return null;
    }

    @Override
    public Object visit(ASTImportStmt node, Object data) {
        if (node.jjtGetNumChildren() >= 1) {
            for (int i = 0; i < node.jjtGetNumChildren(); ++i) {
                m_writer.print("import ");
                node.jjtGetChild(i).jjtAccept(this, data);
                m_writer.println("");
            }
        }
        return null;
    }

    private void printDeclare(SimpleNode node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        m_writer.print(" = ");
        node.jjtGetChild(1).jjtAccept(this, data);
        m_writer.println("");
    }

    @Override
    public Object visit(ASTDeclareStmt node, Object data) {
        if(indentationLevel >= 1) {
            for(int i = 0; i < indentationLevel; i++) {
                m_writer.print(INDENT);
                printDeclare(node, data);
            }
        } else
            printDeclare(node, data);
        return null;
    }



    @Override
    public Object visit(ASTAssignStmt node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        return null;
    }

    private String getOp(String opValue) {
        switch (opValue) {
            case "&&":
                opValue = "and";
                break;
            case "||":
                opValue = "or";
                break;
            case "!":
                opValue = "not ";
                break;
        }
        return opValue;
    }

    @Override
    public Object visit(ASTCompExpr node, Object data) {

        node.jjtGetChild(0).jjtAccept(this, data);
        String opValue = node.getValue();

        if(node.getValue() != null)
            m_writer.print(" " + getOp(opValue) + " ");
        if(node.jjtGetNumChildren() > 1)
            node.jjtGetChild(1).jjtAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAddExpr node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        if(node.getValue() != null)
            m_writer.print(" " + node.getValue() + " ");
        if(node.jjtGetNumChildren() > 1)
            node.jjtGetChild(1).jjtAccept(this, data);


        return null;
    }

    @Override
    public Object visit(ASTUnaExpr node, Object data) {
        String opValue = node.getValue();
        if(node.getValue() != null)
            m_writer.print(getOp(opValue));
        node.jjtGetChild(0).jjtAccept(this, data);
        if(node.jjtGetNumChildren() > 1)
            node.jjtGetChild(1).jjtAccept(this, data);

        return null;
    }

    @Override
    public Object visit(ASTParExpr node, Object data) {

        if(node.jjtGetChild(0) instanceof ASTExpr) {
            m_writer.print("(");
            node.jjtGetChild(0).jjtAccept(this, data);
            m_writer.print(")");
        } else
            node.jjtGetChild(0).jjtAccept(this, data);

        return null;
    }

    @Override
    public Object visit(ASTFunctionStmt node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        indentationLevel++;
        if(node.jjtGetChild(1).jjtGetNumChildren() == 0)
            m_writer.println(INDENT + "pass");
        else
            node.jjtGetChild(1).jjtAccept(this, data);
        if(node.jjtGetNumChildren() == 3) {
            m_writer.print("\t" + "return ");
            node.jjtGetChild(2).jjtAccept(this, data);
            m_writer.println("");
        }
        indentationLevel--;
        m_writer.println("\n");
        return null;
    }

    @Override
    public Object visit(ASTFunctionParam node, Object data) {
        m_writer.print("def ");
        node.jjtGetChild(0).jjtAccept(this, data);
        m_writer.print("(");
        for(int i = 1; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
            if(i < node.jjtGetNumChildren() - 1)
                m_writer.print(", ");
        }

        m_writer.println("):");

        return null;
    }

    @Override
    public Object visit(ASTCallFunction node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data); //Identifier (nom de fct)
        m_writer.print("(");
        if(node.jjtGetNumChildren() > 1)
        for(int i = 1; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
            if(i < node.jjtGetNumChildren() - 1)
                m_writer.print(", ");
        }
        m_writer.print(")");

        return null;
    }

    private void printIf(SimpleNode node, Object data) {
        m_writer.print("if ");
        node.jjtGetChild(0).jjtAccept(this,data);
        m_writer.println(":");
        indentationLevel++;
        if(node.jjtGetChild(1).jjtGetNumChildren() == 0)
            m_writer.println(INDENT + "pass");
        else
            node.jjtGetChild(1).jjtAccept(this,data);
    }

    @Override
    public Object visit(ASTIfStmt node, Object data) {
        if(indentationLevel >= 1) {
            for(int i = 0; i < indentationLevel; i++) {
                m_writer.print(INDENT);
                printIf(node, data);
                indentationLevel--;
            }
        } else {
            printIf(node, data);
            indentationLevel--;
        }

        return null;
    }

    @Override
    public Object visit(ASTBoolValue node, Object data) {
        m_writer.print(capitalize(node.getValue()));
        return null;
    }

    @Override
    public Object visit(ASTIntValue node, Object data) {
        m_writer.print(node.getValue());
        return null;
    }

    @Override
    public Object visit(ASTRealValue node, Object data) {
        m_writer.print(node.getValue());
        return null;
    }

}
