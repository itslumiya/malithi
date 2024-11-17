package malithi;

public class Tree {

  Node root;

  public Tree() {
  }

  public Tree(Node root) {
    this.root = root;
  }

  public void setRoot(Node node) {
    root = node;
  }

  public void preOrder() {
    preOrder(root);
    System.out.println("");
  }

  public String printCode() {
    return printCode(root);
  }

  public void preOrder(Node node) {
    System.out.print(node);
    for (Node n : node.nodes) {
      preOrder(n);
    }
  }

  public String printCode(Node node) {
    String retorno = "";
    retorno += node.enter;
    if (node.nodes.isEmpty())
      retorno += node;
    for (Node n : node.nodes) {
      printCode(n);
    }
    retorno += node.exit;
    return retorno;
  }

  public String printTree() {
    return root.getTree();
  }
}

