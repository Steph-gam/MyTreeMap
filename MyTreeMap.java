


package mytreemap;


public class MyTreeMap<K extends Comparable<K>,V> implements MyMap<K,V> {
	private BinaryTree<Element> map;
	java.util.Set<K> keys;  // to return keys in order
	private int size;
	//method that checks if it contants the 'key'	
	public boolean containsKey(K key){
            if (search(new Element(key, null), map) == null) {
            return false;
            }
            return true;     
        }
        //method that stores the values
	public V put(K key, V value){
            if (!containsKey(key)){
                size +=1;
            }
            Element element = insert(new Element(key, value));
            if(element != null){
                return element.value;
            }else{
                return null;
            }
        }
        //method that searches using the key, element, and map. using appropriate method
	public V get(K key){
            Element element = search(new Element(key, null), map);
            if (element != null) {
                return element.value;
            }else{
                return null;
            }        
        }
        //method that takes the map, element and key to the proper method to remove
	public V remove(K key){
            Element element = delete(map, new Element(key, null), null);
            if (element != null) {
                size-=1;
                return element.value;
            }else{
                return null;
            }          
        }
        
	public int size(){
            return size;
        }
        
	public int height(){
            return height(map);
        }
        //returns a string
	public String toString(){
            return map.toString();	
        }
        //Keeps a set of the keys in an order fashion
	public java.util.Set<K> keySet(){
            keys = new java.util.TreeSet();
            inorder(map);
            return keys;
        }
        
    public class Element{
	K key; 
        V value;
	public Element(K key, V value){
            this.key = key;
            this.value = value;
        }
	//Compares keys to eachother
        public int compareTo(Element that){
            return this.key.compareTo(that.key);           
        }
	//returns a string 
        public String toString(){
            return (key.toString());
        }
        
    }
    //search method
    private Element search(Element element, BinaryTree<Element> tree){
        if(tree == null) return null;
        Element r = tree.getRoot();
        if(element.compareTo(r) == 0) return r;
        else if(element.compareTo(r) < 0) 			
            return search(element, tree.getLeft());
        else
            return search(element, tree.getRight());        
    }
    //insert method
    private Element insert(Element element){   
        if(map == null){  
            map = new BinaryTree<>(element);
            return null;
        }    
        else
            return insert(element, map);
    }    
    //insert method that is called from the previous insert method        
    private Element insert(Element element, BinaryTree<Element> tree){        
        if(element.compareTo(tree.getRoot()) == 0){
            tree.setRoot(element);
            return tree.getRoot();
        }
        else if(element.compareTo(tree.getRoot()) < 0){
            if(tree.getLeft()==null){
                tree.setLeft(new BinaryTree<>(element));
                return null;
            }
            else{
                return insert(element, tree.getLeft());
            }
        }
        else if(element.compareTo(tree.getRoot()) > 0){
            if(tree.getRight() == null){
                tree.setRight(new BinaryTree<>(element));
                return null;
            }
            else{
                return insert(element, tree.getRight());
            }
        }   
        return null;
    }    
    //method that deletes
    private Element delete(BinaryTree<Element> tree, Element element, BinaryTree<Element> parent){
        if(tree == null) 
            return null;
        else{
            Element r = tree.getRoot();
            if(element.compareTo(tree.getRoot()) < 0){
                    return delete(tree.getLeft(), element, tree);
            }
            else if(element.compareTo(tree.getRoot()) > 0){
                    return delete(tree.getRight(), element, tree);
            }
            else{
                if(tree.isLeaf()){
                     if (element.compareTo(parent.getRoot()) < 0) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } else if (tree.getLeft() != null && tree.getRight() != null) { 
                    Element successor = successor(tree);
                    delete(tree, successor, parent);
                    tree.setRoot(successor);
                } else if (tree.getLeft() == null) { 
                    promote(tree, parent, tree.getRight());
                } else {
                    promote(tree, parent, tree.getRight()); 
                }
                return r;
            }
        }
        
    }
    //method that gives the next inorder successor
    private Element successor(BinaryTree<Element> tree) {
        while (tree.getLeft() != null) {
            tree = tree.getLeft();
        }
        return tree.getRoot();
    }
    //method that promotes  
    private void promote(BinaryTree<Element> tree, BinaryTree<Element> parent, BinaryTree<Element> newChild){
        if (newChild.getRoot().compareTo(parent.getRoot()) < 0) {
            parent.setLeft(newChild);
	}
	else{
            parent.setRight(newChild);
	}   
    }
    //method that follows the tree left root right
    private void inorder(BinaryTree<Element> tree){  
        if(tree != null){
            inorder(tree.getLeft());
            keys.add(tree.getRoot().key);
            inorder(tree.getRight());
        }    
    }
    //checks the height of the tree	
    private int height(BinaryTree<Element> tree){      
        return 1 + Math.max(height(tree.getLeft()), height(tree.getRight()));    
    }

    public static void main(String[] args){
		// create tree
		MyTreeMap<String,Integer> t=new MyTreeMap<>();

		// test put
		t.put("C",5);
		System.out.println(t);
		t.put("X",7);
		System.out.println(t);
		t.put("A",2);
		System.out.println(t);
		t.put("J",8);
		System.out.println(t);
		t.put("P",5);
		System.out.println(t);
		System.out.println("put J again: "+t.put("J",42));
		System.out.println(t);
	
		// test size
		System.out.println("size: "+t.size());

		// test containsKey
		System.out.println("containsKey(A): "+t.containsKey("A"));
		System.out.println("containsKey(B): "+t.containsKey("B"));

		// test get
		System.out.println("get(X): "+t.get("X"));		
		System.out.println("get(P): "+t.get("P"));		
		System.out.println("get(Z): "+t.get("Z"));		

		// test remove
		System.out.println("remove(A): "+t.remove("A"));
		System.out.println(t);
		System.out.println("remove(B): "+t.remove("B"));
		System.out.println(t);
		System.out.println("remove(J): "+t.remove("J"));
		System.out.println(t);
		System.out.println("size: "+t.size());

		// test keySet
		t.put("K",9);
		t.put("M",4);
		System.out.println(t);
		System.out.println("key list: ");
		for(String key:t.keySet()) System.out.print(key);
		System.out.println();
    }
}