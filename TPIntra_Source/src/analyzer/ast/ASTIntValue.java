/* Generated By:JJTree: Do not edit this line. ASTIntValue.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package analyzer.ast;

public
class ASTIntValue extends SimpleNode {
  private String value;

  public ASTIntValue(int id) {
    super(id);
  }

  public ASTIntValue(Parser p, int id) {
    super(p, id);
  }

  public void setValue(final String value) {
    this.value = value;
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=84eb87d6c1f09e6b2311b77685c3fc35 (do not edit this line) */
