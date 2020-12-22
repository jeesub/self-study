package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class StringArrayFlattenIterator implements Iterator<String> {
	private final List<List<String>> values;
	private int pos;

	public StringArrayFlattenIterator(List<List<String>> values) {
		this.values = values;
		this.pos = 0;
	}

	@Override
	public boolean hasNext() {
		int i = 0;
		int size = values.size();
		while (i < size) {
			List<String> tmpList = values.get(i);
			if (pos < tmpList.size()) {
				return true;
			}
			i++;
		}
		return false;
	}

	@Override
	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException("Cannot find Next");
		}

		StringBuilder sb = new StringBuilder();
		int i = 0;
		int size = values.size();
		while (i < size) {
			List<String> tmpList = values.remove(0);
			// 1. if cur has the first String, append it to sb
			if (tmpList.size() > pos) {
				sb.append(tmpList.get(pos));
			}
			// 2. if cur has the second String, add into values again
			if (tmpList.size() > pos + 1) {
				values.add(tmpList);
			}
			i++;
		}
		pos++;
		return sb.toString();
	}

	public static void main(String[] args) {
		List<List<String>> input = new ArrayList<>();
		input.add(Arrays.asList("a", "b", "c", "d"));
		input.add(Arrays.asList());
		input.add(Arrays.asList("k", "r", "l"));
		input.add(Arrays.asList("t"));
		StringArrayFlattenIterator itr = new StringArrayFlattenIterator(input);
		int size = input.size();
		for (int i = 0; i < size; i++) {
			System.out.println(itr.next());
		}
		// output: "akt" -> "br" -> "cl" -> "d"
	}

}
