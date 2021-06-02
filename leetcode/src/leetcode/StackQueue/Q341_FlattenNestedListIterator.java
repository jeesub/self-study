package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Q341. Flatten Nested List Iterator.
 * [Deque]
 * Add NestedIntegers into a deque.
 * If the first is integer, it is the next.
 * If the first is a list, unpack and put everything into the deque.
 * TC:
 *   constructor: O(n), where n is the length of the input list.
 *   next(): O(k), where k is the depth of the list.
 *   hasNext(): O(k), where k is the depth of the list.
 * SC: O(n + l), where l is the length of unpacked lists.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q341_FlattenNestedListIterator {

    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    private static class MyNestedInteger implements NestedInteger, Iterable<Integer> {
        private Object object;

        private MyNestedInteger(Object newObject) {
            object = newObject;
        }

        @Override
        public boolean isInteger() {
            return object instanceof Integer;
        }

        @Override
        public Integer getInteger() {
            return (Integer) object;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<NestedInteger> getList() {
            return (List<NestedInteger>) object;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new NestedIterator(getList());
        }
    }

    private static class NestedIterator implements Iterator<Integer> {
        private Deque<NestedInteger> deque;

        public NestedIterator(List<NestedInteger> nestedList) {
            deque = new ArrayDeque<>(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return deque.remove().getInteger();
        }

        @Override
        public boolean hasNext() {
            unpackNext();
            return !deque.isEmpty();
        }

        private void unpackNext() {
            while (!deque.isEmpty() && !deque.peekFirst().isInteger()) {
                List<NestedInteger> list = deque.remove().getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    deque.addFirst(list.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new MyNestedInteger(1));
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new MyNestedInteger(2));
        nestedList.add(new MyNestedInteger(3));
        list.add(new MyNestedInteger(nestedList));
        list.add(new MyNestedInteger(4));
        MyNestedInteger myNestedInteger = new MyNestedInteger(list);
        Iterator<Integer> itr = myNestedInteger.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
            System.out.print(" ");
        }
        System.out.println("");
    }

}
