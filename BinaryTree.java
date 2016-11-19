/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytreemap;

/**
 *
 * @author Stephanie
 */
class BinaryTree<T> {
  private T root;
  private BinaryTree<T> left;
  private BinaryTree<T> right;
  
  public BinaryTree(T paramE, BinaryTree<T> paramBinaryTree1, BinaryTree<T> paramBinaryTree2)
  {
    this.root = paramE;
    this.left = paramBinaryTree1;
    this.right = paramBinaryTree2;
  }
  
  public BinaryTree(T paramE)
  {
    this(paramE, null, null);
  }
  
  public T getRoot()
  {
    return (T)this.root;
  }
  
  public BinaryTree<T> getLeft()
  {
    return this.left;
  }
  
  public BinaryTree<T> getRight()
  {
    return this.right;
  }
  
  public T setRoot(T paramE)
  {
    Object localObject = this.root;
    this.root = paramE;
    return (T)localObject;
  }
  
  public BinaryTree<T> setLeft(BinaryTree<T> paramBinaryTree)
  {
    BinaryTree localBinaryTree = this.left;
    this.left = paramBinaryTree;
    return localBinaryTree;
  }
  
  public BinaryTree<T> setRight(BinaryTree<T> paramBinaryTree)
  {
    BinaryTree localBinaryTree = this.right;
    this.right = paramBinaryTree;
    return localBinaryTree;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("" + this.root);
    if (!isLeaf())
    {
      localStringBuilder.append("(");
      if (this.left != null) {
        localStringBuilder.append(this.left);
      }
      if (this.right != null) {
        localStringBuilder.append("," + this.right);
      }
      localStringBuilder.append(")");
    }
    return localStringBuilder + "";
  }
  
  public boolean isLeaf()
  {
    return (this.left == null) && (this.right == null);
  }
}
