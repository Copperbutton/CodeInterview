package interviewquestions.medallia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlattenIterator {

		private static final String TO = "to";
		private static final String EMPTY = "empty";
		private static final String LOOP = "loop";
		private static final String NULL = "null";
		private static final String INCREASING = "increasing";
		private static final int MAX = 10000;
		
		public static void main(String args[]) throws Exception {
				List<Iterator<String>> lists = generateIterators(args);
				Iterator<Iterator<String>> iters = lists.iterator();
				Iterator<String> flattened = flatten(iters);
				int counter = 0;
				while (flattened.hasNext() && ++counter < MAX) {
					System.out.println(flattened.next());
				}
		}
		
		// crazy hack to generate the iterators
		private static List<Iterator<String>> generateIterators(String[] args) {
			List<Iterator<String>> topLvlList = new ArrayList<Iterator<String>>();
			String[] stringLists = args[1].split("#");
			if (args[0].equals("list")) {
				for (String stringList : stringLists) {
					String[] split = stringList.split("%");
					if (split.length == 1 && split[0].isEmpty()){
						split = new String[0];
					}
					topLvlList.add(Arrays.asList(split).iterator());
				}
			} else if (args[0].equals("function")) {
				for (String function : stringLists) {
					if (function.startsWith(INCREASING)) {
						final String[] numbers = function.substring(INCREASING.length()).split(TO);
						topLvlList.add(new Iterator<String>() {
							int min = Integer.parseInt(numbers[0]);
							int max = Integer.parseInt(numbers[1]); 
							public boolean hasNext() {
								return min < max;
							}
							public String next() {
								return String.valueOf(min++);
							}
							public void remove() {
								throw new UnsupportedOperationException();
							}
						});
					} else if (function.startsWith(LOOP)) {
						final String[] numbers = function.substring(LOOP.length()).split(TO);
						topLvlList.add(new Iterator<String>(){
							int min = Integer.parseInt(numbers[0]);
							int max = Integer.parseInt(numbers[1]);
							int x = min;
							public boolean hasNext() {
								return true;
							}

							public String next() {
								String output = String.valueOf(x);
								x++;
								if (x >= max) {
									x = min;
								}
								return output;
							}

							public void remove() {
								throw new UnsupportedOperationException();
							}});
					} else if (function.startsWith(NULL)) {
						topLvlList.add(null);
					} else if (function.startsWith(EMPTY)) {
						topLvlList.add(new Iterator<String>() {
							public boolean hasNext() {
								return false;
							}
							public String next() {
								throw new NoSuchElementException();
							}
							public void remove() {
								throw new UnsupportedOperationException();
							}
						});
					}
				}
			}
			return topLvlList;
		}
		/*
		you can try using this code to start and run some of your own tests
		make sure that you do NOT submit this code otherwise your tests will fail.
		also beware that iterators do not necessarily have to come from a backing list.
		take time with your corner cases.
		anonymous inner classes might help although you don't have to use it. 
		NO need to support delete operation

		import java.util.ArrayList;
		import java.util.Arrays;
		import java.util.Iterator;
		import java.util.List;


		public class Solution {
			public static void main(String args[]) throws Exception {
				List<Iterator<String>> lists = new ArrayList<>();
				lists.add(Arrays.asList("a", "b", "c").iterator());
				lists.add(null);
				lists.add(Arrays.<String>asList().iterator());
				lists.add(Arrays.asList("d", "e").iterator());
				lists.add(Arrays.<String>asList().iterator());
				
				Iterator<Iterator<String>> iters = lists.iterator();
				Iterator<String> flattened = flatten(iters);
				while (flattened.hasNext()) {
					System.out.println(flattened.next());
				}
			}

			public static Iterator<String> flatten(Iterator<Iterator<String>> iterators) {
				return null;
			}
		}

		*/

		/** @return Iterator which flattens the given arguments */
		public static Iterator<String> flatten(Iterator<Iterator<String>> iterators) {
            final List<String> list = new ArrayList<String> ();
            while (iterators != null && iterators.hasNext()) {
                Iterator<String> iter = iterators.next();
                while (iter != null && iter.hasNext())
                    list.add(iter.next());
            }
            
		    return new Iterator<String>() { 
                private int index;
		        private List<String> iterList = new ArrayList<String> (list);
                
                public boolean hasNext() {
                    return index < iterList.size();
                }

                public String next() {
                    if (hasNext())
                        return iterList.get(index++);
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
	}
}
