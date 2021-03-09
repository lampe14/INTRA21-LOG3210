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
        return null;
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPrimitiveExpr node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTStringValue node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTOperateurComparaison node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAdditionSoustraction node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTNegation node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTParenthese node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTWhileStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTCoeurStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTImportStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDeclareStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAssignStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionParam node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTCallFunction node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIfStmt node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTBoolValue node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIntValue node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTRealValue node, Object data) {
        return null;
    }
}
