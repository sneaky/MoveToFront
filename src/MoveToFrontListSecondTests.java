import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.rules.Timeout;


public class MoveToFrontListSecondTests {

	@Rule
	public Timeout globalTimeout = new Timeout(1000);

	@Test
	public void testSimpleRank() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("the");
		l.incrementCount("cheese");
		assertEquals(0, l.rank("cheese"));
		assertEquals(1, l.rank("the"));
		assertEquals(2, l.rank("bacon"));
	}
	@Test
	public void testSimpleMoveToFront() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("the");
		l.incrementCount("cheese");
		assertEquals(0, l.rank("cheese"));
		assertEquals(1, l.rank("the"));
		assertEquals(2, l.rank("bacon"));
		l.incrementCount("the");
		assertEquals(0, l.rank("the"));
		assertEquals(1, l.rank("cheese"));
		assertEquals(2, l.rank("bacon"));
	}
	@Test
	public void testFindSimpleMoveToFront() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("the");
		l.incrementCount("cheese");
		assertNotNull( l.find("cheese"));
		assertNotNull( l.find("the"));
		assertNull( l.find("bacon"));
	}

	@Test
	public void testSpliceOut() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("the");
		l.incrementCount("cheese");
		assertNotNull( l.find("cheese"));
		assertNotNull( l.find("the"));
		assertNull( l.find("bacon"));
		assertEquals(2, l.size());
		l.incrementCount("the");
		StringCountElement s = l.find("cheese");
		l.spliceOut(s);
		assertNull( l.find("cheese"));
		assertNotNull( l.find("the"));
		assertNull( l.find("bacon"));
		assertEquals(1, l.size());
	}
	@Test
	public void testSpliceOutMiddle() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("the");
		l.incrementCount("cheese");
		l.incrementCount("cat");
		assertNotNull( l.find("cheese"));
		assertNotNull( l.find("the"));
		assertNotNull( l.find("cat"));
		assertNull( l.find("bacon"));
		assertEquals(3, l.size());
		l.incrementCount("cheese");
		StringCountElement s = l.find("cheese");
		l.spliceOut(s);
		assertNull( l.find("cheese"));
		assertNotNull( l.find("the"));
		assertNotNull( l.find("cat"));
		assertNull( l.find("bacon"));
		assertEquals(2, l.size());
	}
	@Test
	public void testSpliceOutTail() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("the");
		l.incrementCount("cheese");
		l.incrementCount("cat");
		assertNotNull( l.find("cheese"));
		assertNotNull( l.find("the"));
		assertNotNull( l.find("cat"));
		assertNull( l.find("bacon"));
		assertEquals(3, l.size());
		StringCountElement s = l.find("the");
		l.spliceOut(s);
		assertNotNull( l.find("cheese"));
		assertNull( l.find("the"));
		assertNotNull( l.find("cat"));
		assertNull( l.find("bacon"));
		assertEquals(2, l.size());
	}



	@Test
	public void testSpliceHead() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("cheese");
		l.incrementCount("potato");
		assertEquals(2, l.size());

		assertNull( l.find("the"));
		StringCountElement s = new StringCountElement();
		s.key = "the";
		s.count = 1;
		l.spliceIn(s, 0);
		StringCountElement one = l.find("the");
		StringCountElement two = l.find("potato");
		StringCountElement three = l.find("cheese");

		assertNotNull(one);
		assertNotNull(two);
		assertNotNull(three);
		// assertNull(one.prev); // don't check -- if using sentinels, this may not be true
		// assertNull(three.next); // don't check -- if using sentinels, this may not be true
		assertEquals(two, one.next);
		assertEquals(three, two.next);
		assertEquals(two, three.prev);
		assertEquals(one, two.prev);

		assertEquals(3, l.size());

	}
	@Test
	public void testSpliceMiddle() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("cheese");
		l.incrementCount("potato");
		assertEquals(2, l.size());

		assertNull( l.find("the"));
		StringCountElement s = new StringCountElement();
		s.key = "the";
		s.count = 1;
		l.spliceIn(s, 1);
		StringCountElement one = l.find("potato");
		StringCountElement two = l.find("the");
		StringCountElement three = l.find("cheese");

		assertNotNull(one);
		assertNotNull(two);
		assertNotNull(three);
		// assertNull(one.prev); // don't check -- if using sentinels, this may not be true
		// assertNull(three.next); // don't check -- if using sentinels, this may not be true
		assertEquals(two, one.next);
		assertEquals(three, two.next);
		assertEquals(two, three.prev);
		assertEquals(one, two.prev);

		assertEquals(3, l.size());

	}


}
