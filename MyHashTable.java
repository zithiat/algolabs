
public class MyHashTable {
	private final static int TABLE_SIZE = 43;
	HashEntry[] table;

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
	
	MyHashTable() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	public Integer getLinearProbing(int key) {
		int hash = (key % TABLE_SIZE);
		int originalHash = hash;
		int counter = 0;
		while (table[hash] != null && table[hash].getKey() != key) {
			counter++;
			hash = (hash + 1) % TABLE_SIZE;
		}
		System.out.println("GET originalHash=" + originalHash + " - new hash=" + hash + " - Counter for linear probes=" + counter + " for " + key);
		System.out.println(this.toString());
		if (table[hash] == null)
			return null;
		else
			return table[hash].getValue();
	}

	public void putLinearProbing(int key, int value) {
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
//		if (key == 59 || key == 436 || key == 95) {
//			System.out.println("originalHash=" + originalHash + " - counter for linear probes=" + counter + " for " + key);
//		}
		System.out.println("PUT originalHash=" + originalHash + " - new hash=" + hash + " - counter for linear probes=" + counter + " for " + key);
		table[hash] = new HashEntry(key, value);
	}
	
	public void removeLinearProbing(int key, boolean rehash) {
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
		System.out.println("REMOVE originalHash=" + originalHash + " - counter for linear probes=" + counter + " for " + key);
		if (rehash) {
			for (hash = (hash + 1) % TABLE_SIZE; table[hash] != null; hash = (hash + 1) % TABLE_SIZE) {
				HashEntry tmp = table[hash];
				table[hash] = null;
				putLinearProbing(tmp.getKey(), tmp.getValue());
			}
		}
	}
	
	public Integer getQuadricProbing(int key) {
		int hash = (key % TABLE_SIZE);
		int h = 1;
		int originalHash = hash;
		int counter = 0;
		while (table[hash] != null && table[hash].getKey() != key) {
			counter++;
			hash = (hash + h * h + 1) % TABLE_SIZE;
		}
		System.out.println("GET originalHash=" + originalHash + " - new hash=" + hash + " - Counter for linear probes=" + counter + " for " + key);
		System.out.println(this.toString());
		if (table[hash] == null)
			return null;
		else
			return table[hash].getValue();
	}
	
	public void putQuadricProbing(int key, int value) {
		int hash = (key % TABLE_SIZE);
		int h = 1;
		int originalHash = hash;
		int counter = 0;
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + h * h + 1) % TABLE_SIZE;
			counter++;
		}
		System.out.println("PUT originalHash=" + originalHash + " - new hash=" + hash + " - counter for linear probes=" + counter + " for " + key);
		table[hash] = new HashEntry(key, value);
	}
	
	public void removeQuadricProbing(int key) {
		int hash = (key % TABLE_SIZE);
		int h = 1;
		int originalHash = hash;
		int counter = 0;
		// similar as Linear Probing
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + h * h + 1) % TABLE_SIZE;
			counter++;
		}
		table[hash] = null;
		System.out.println("REMOVE originalHash=" + originalHash + " - counter for linear probes=" + counter + " for " + key);
		// Now we need to rehash all keys and re-put the items,
		// which has the same hash
		System.out.println("REHASHING all keys");        
        for (hash = (hash + h * h + 1) % TABLE_SIZE; table[hash] != null; hash = (hash + h * h + 1) % TABLE_SIZE) {
            HashEntry tmp = table[hash];
            table[hash] = null;
            putQuadricProbing(tmp.getKey(), tmp.getValue());     
        }
	}
		
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int countNotNull = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
			if (null != table[i]) {
				sb.append("[" + i + " - " + table[i].getValue() + "]");
				countNotNull++;
			}
			else 
				sb.append("[" + i + " - null]");
			if (i % 10 == 9)
				sb.append("\n");
			else 
				sb.append("\t");
		}
		return sb.toString() + "\nSize of not-null hashtable=" + countNotNull;
	}

	public static void main(String[] args) {
		int[] arr = {905, 159, 521, 607, 394, 783, 95, 787, 170, 144, 231, 963, 664, 408, 495, 237, 875, 714, 499, 930,
				115, 460, 194, 720, 936, 550, 22, 202, 599, 643, 84};
//		int [] arr = {1, 44, 87, 130, 173, 64, 783, 95, 21};
		
		System.out.println("Testing with Linear Probing");
		MyHashTable mht = new MyHashTable();
		for (int i = 0; i < arr.length; i++) {
			mht.putLinearProbing(arr[i], arr[i]);
		}
		System.out.println(mht.toString());
		mht.putLinearProbing(59, 59);
		System.out.println(mht.toString());
		mht.putLinearProbing(436, 436);
		System.out.println(mht.toString());
		mht.removeLinearProbing(783, false);
		System.out.println(mht.toString());
		mht.putLinearProbing(783, 783);
		System.out.println(mht.toString());
		mht.removeLinearProbing(783, true);
		mht.getLinearProbing(95);
		
//		System.out.println("\n==================================\n");
//		
//		System.out.println("Testing with Quadric Probing");
//		MyHashTable mht1 = new MyHashTable();
//		for (int i = 0; i < arr.length; i++) {
//			mht1.putQuadricProbing(arr[i], arr[i]);
//		}
//		System.out.println(mht1.toString());
//		System.out.println("Get 21: " + mht1.getQuadricProbing(21));
//		mht1.removeQuadricProbing(87);
//		System.out.println(mht1.toString());
//		System.out.println("Get 173: " + mht1.getQuadricProbing(173));
	}
}
