/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytreemap;

public interface MyMap<K,V> {
	public boolean containsKey(K key);
	public V put(K key, V value);
	public V get(K key);
	public V remove(K key);
	public int size();
	public String toString();
	public java.util.Set<K> keySet();
}