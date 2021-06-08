public class MoveToFrontList {

	private StringCountElement head; // the head reference
	private StringCountElement tail; // the tail reference
	private int size; // the size of the list (number of valid items)

	/**
	 * _Part 1: Implement this constructor._
	 * 
	 * Creates a new, initially empty MoveToFrontList. This list should be a
	 * linked data structure.
	 */
	public MoveToFrontList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * This method increments the count associated with the specified string
	 * key. If no corresponding key currently exists in the list, a new list
	 * element is created for that key with the count of 1. When this method
	 * returns, the key will have rank 0 (i.e., the list element associated with
	 * the key will be at the front of the list)
	 * 
	 * @param key
	 *            the string whose count should be incremented
	 * @return the new count associated with the key
	 */
	public int incrementCount(String key) {
		StringCountElement s = find(key);
		if (s != null) {
			// found the key, splice it out and increment the count
			spliceOut(s);
			s.count++;
		} else {
			// need to create a new element
			s = new StringCountElement();
			s.key = key;
			s.count = 1;
		}
		// move it to the front
		spliceIn(s, 0);
		return s.count;
	}

	/**
	 * 
	 * @return the number of items in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find the list element associated with the specified string. That is, find
	 * the StringCountElement that a key equal to the one specified
	 * 
	 * @param key
	 *            the key to look for
	 * @return a StringCountElement in the list with the specified key or null
	 *         if no such element exists.
	 */
	public StringCountElement find(String key) {
		StringCountElement x = head;
		if (x != null) {
			if (x.key == null) { x = null; }
			while (x != null && !x.key.equals(key)) {
				x = x.next;
			}
		}
		return x;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Compute the rank of the specified key. Rank is similar to position, so
	 * the first element in the list will have rank 0, the second element will
	 * have rank 1 and so on. However, an item that does not exist in the list
	 * also has a well defined rank, which is equal to the size of the list. So,
	 * the rank of any item in an empty list is 0.
	 * 
	 * @param key
	 *            the key to look for
	 * @return the rank of that item in the rank 0...size() inclusive.
	 */
	public int rank(String key) {
		if (size == 0) { return 0; }
		if (head != null && head.key.equals(key)) { return 0; }
		if (tail != null && tail.key.equals(key)) { return size - 1; }
		StringCountElement x = head;
		int r = 0;
		if (x != null) {
			while (x != null && !x.key.equals(key)) {
				r++;
				x = x.next;
			}
		}
		return r;
	}

	/**
	 * _Part 4: Implement this method._
	 * 
	 * Splice an element into the list at a position such that it will obtain
	 * the desired rank. The element should either be new, or have been spliced
	 * out of the list prior to being spliced in. That is, it should be the case
	 * that: s.next == null && s.prev == null
	 * 
	 * @param s
	 *            the element to be spliced in to the list
	 * @param desiredRank
	 *            the desired rank of the element
	 */
	public void spliceIn(StringCountElement s, int desiredRank) {
		StringCountElement x = head;
		// case 1: empty list
		if (x == null) {
			head = s;
			head.prev = null;
		}
		//case 2: populated list
		if (x != null) {
			if (desiredRank == 0) { // move to front
				s.next = head;
				head.prev = s;
				s.prev = null;
				head = s;
			}
			if (desiredRank >= size) { // move to back
				tail.next = s;
				s.prev = tail;
				tail = s;
			}
			if (desiredRank > 0 && desiredRank < size) {
				for (int i = 0; i < desiredRank; i++) { // move to desiredRank
					x = x.next;
				}
				x.prev.next = s;
				s.prev = x.prev;
				s.next = x;
				x.prev = s;
			}
		}
		size++;
	}

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Splice an element out of the list. When the element is spliced out, its
	 * next and prev references should be set to null so that it can safely be
	 * splicedIn later. Splicing an element out of the list should simply remove
	 * that element while maintaining the integrity of the list.
	 * 
	 * @param s
	 *            the element to be spliced out of the list
	 */
	public void spliceOut(StringCountElement s) {
		if (size == 0) { throw new IllegalStateException("Error.\tAttempted to spliceOut from an empty list."); }
		if (s == null) { throw new IllegalStateException("Error.\tInvalid parameter."); }

		if (s.next != null && s.prev != null) {
			s.next.prev = s.prev;
			s.prev.next = s.next;
			s.next = null;
			s.prev = null;
		}
		if (s.next == null && s.prev != null) { // at the tail
			s.prev.next = null;
			tail = s.prev;
			s.prev = null;
		}
		if (s.next != null) { // at the head
			s.next.prev = null;
			head = s.next;
			s.next = null;
		}
		size--;
	}
}
