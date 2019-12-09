
public class MyHashTable {
	private final static int TABLE_SIZE = 43;
	HashEntry[] table;

	MyHashTable() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	public int get(int key) {
		int hash = (key % TABLE_SIZE);
		int originalHash = hash;
		int counter = 0;
		if (null != table[hash])
			System.out.println("hash=" + table[hash] + "--table[hash]=" + table[hash].getKey() + "--key=" + key);
		while (table[hash] != null && table[hash].getKey() != key) {
			counter++;
			hash = (hash + 1) % TABLE_SIZE;
		}
		System.out.println("originalHash=" + originalHash + " - current hash=" + hash + " - Counter for linear probes=" + counter + " for " + key);
		System.out.println(this.toString());
		if (table[hash] == null)
			return -1;
		else
			return table[hash].getValue();
	}

	public void put(int key, int value) {
		int hash = (key % TABLE_SIZE);
		int originalHash = hash;
		int counter = 0;
		// if the table[hash] location is already occupied, 
		// we need to find the next available slot.
		// This is Linear probing in hashing https://en.wikipedia.org/wiki/Linear_probing
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + 1) % TABLE_SIZE;
			counter++;
		}
		if (key == 59 || key == 436 || key == 95) {
			System.out.println("originalHash=" + originalHash + " - counter for linear probes=" + counter + " for " + key);
		}
		table[hash] = new HashEntry(key, value);
	}
	
	public void remove(int key) {
		int hash = (key % TABLE_SIZE);
		int originalHash = hash;
		int counter = 0;
		// if the table[hash] location is already occupied, 
		// we need to find the next available slot.
		// This is Linear probing in hashing https://en.wikipedia.org/wiki/Linear_probing
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + 1) % TABLE_SIZE;
			counter++;
		}
		table[hash] = null;
		System.out.println("originalHash=" + originalHash + " - counter for linear probes=" + counter + " for " + key);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int countNotNull = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
			if (null != table[i]) {
				sb.append("[" + table[i].getValue() + "]");
				countNotNull++;
			}
			else 
				sb.append("[null]");
			if (i != TABLE_SIZE - 1) 
				sb.append("\t");
			if (i % 10 == 9) 
				sb.append("\n");
		}
		return sb.toString() + "\nSize not null=" + countNotNull;
	}

	public static void main(String[] args) {
		int[] arr = {905, 159, 521, 607, 394, 783, 95, 787, 170, 144, 231, 963, 664, 408, 495, 237, 875, 714, 499, 930,
				115, 460, 194, 720, 936, 550, 22, 202, 599, 643, 84, 59, 436};
		
		MyHashTable mht = new MyHashTable();
		for (int i = 0; i < arr.length; i++) {
			mht.put(arr[i], arr[i]);
		}
		System.out.println(mht.toString());
		System.out.println("132 in " + mht.get(132));
		System.out.println("Remove 783");
		mht.remove(783);
		System.out.println("95 in " + mht.get(95));
	}
}

class HashEntry {
	private int key;
	private int value;

	HashEntry(int key, int value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}
}
