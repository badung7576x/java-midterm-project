package datastructure.hashtable;

import java.util.ArrayList;
import datastructure.hashtable.HashNode;

public class HashTable<Key, Value> {

	ArrayList<HashNode<Key, Value>> bucket = new ArrayList<>();
	HashFunction<Key> hF = new HashFunction<Key>();
	private int numBuckets = 10;
	private int size;


	public HashTable() {
		for (int i = 0; i < this.numBuckets; i++) {
			this.bucket.add(null);
		}
	}

	public int getSize() {
		return this.size;
	}
	
	public int getNumBuckets() {
		return numBuckets;
	}


	public ArrayList<HashNode<Key, Value>> getBucket() {
		return this.bucket;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		bucket.clear();
	}
	public boolean get(Key key) {
		int index = hF.hashFunction(key);
		HashNode<Key, Value> head = bucket.get(index);

		while (head != null) {
			if (head.key.equals(key))
//				return head.value;
				return true;
			head = head.next;
		}
//		return null;
		return false;
	}

	public Value remove(Key key) {
		int index = hF.hashFunction(key);
		HashNode<Key, Value> head = bucket.get(index);

		if (head == null)
			return null;
		if (head.key.equals(key)) {
			Value val = head.value;
			head = head.next;
			bucket.set(index, head);
			size--;
			return val;
		} else {
			HashNode<Key, Value> prev = null;
			while (head != null) {
				if (head.key.equals(key)) {
					prev.next = head.next;
					size--;
					return head.value;
				}
				prev = head;
				head = head.next;
			}
			size--;
			return null;
		}
	}

	public void add(Key key,Value value) {
		
		int index = hF.hashFunction(key);
		HashNode<Key, Value> head = bucket.get(index);
		HashNode<Key, Value> newNode = new HashNode<>();
		newNode.key = key;
		newNode.value = value;
		if(head == null) {
			bucket.set(index, newNode);
			size++;
		} else {
			while(head != null) {
				if(head.key.equals(key)) {
					head.value = value;
					size++;
					break;
				}
				head=head.next;
			}
			if(head==null){
				head = bucket.get(index);
				newNode.next = head;
				bucket.set(index, newNode);
				size++;
			}
		}
//		if( (1.0 * size) / numBuckets > 0.9) {
//			ArrayList<HashNode<Key, Value>> tmp = bucket;
//			bucket = new ArrayList<>();
//			numBuckets = 2 * numBuckets;
//			for(int i = 0; i < numBuckets; i++) {
//				bucket.add(null);
//			}
//			for(HashNode<Key, Value> headNode : tmp) {
//				while(headNode != null) {
//					add(headNode.key, headNode.value);
//					headNode=headNode.next;
//				}
//			}
//		}
	}
}
