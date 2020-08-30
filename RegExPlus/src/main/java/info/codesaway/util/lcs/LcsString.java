package info.codesaway.util.lcs;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Implementation of the LCS algorithm for Strings
 * </p>
 *
 * <p>
 * Slightly modified from its original version located at<br>
 * <a href=
 * "http://en.wikibooks.org/wiki/Algorithm_implementation/Strings/Longest_common_subsequence#Java"
 * >http://en.wikibooks.org/wiki/Algorithm_implementation/Strings/
 * Longest_common_subsequence#Java</a>
 * </p>
 */
public class LcsString extends LongestCommonSubsequence<Character> {
	/**
	 * The "before" string in the diff process
	 */
	private final String before;

	/**
	 * The "after" string in the diff process
	 */
	private final String after;

	/**
	 * @param before the "before" string in the diff process
	 * @param after  the "after" string in the diff process
	 */
	public LcsString(final String before, final String after) {
		this.before = before;
		this.after = after;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int lengthOfBefore() {
		return this.before.length();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int lengthOfAfter() {
		return this.after.length();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Character valueOfBefore(final int index) {
		return this.before.charAt(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Character valueOfAfter(final int index) {
		return this.after.charAt(index);
	}

	/**
	 * Returns the characters in the longest common subsequence.
	 *
	 * @return the characters in the longest common subsequence
	 */
	public String getBacktrack() {
		return this.getBacktrack("");
	}

	/**
	 * Returns the characters in the longest common subsequence separating each with
	 * the specified delimiter.
	 *
	 * @param delimiter string used to delimit each character
	 * @return the characters in the longest common subsequence separating each with
	 *         the specified delimiter
	 */
	public String getBacktrack(final String delimiter) {
		List<Character> backtrack = this.backtrack();

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < backtrack.size(); i++) {
			if (i != 0) {
				result.append(delimiter);
			}

			result.append(backtrack.get(i));
		}

		return result.toString();
	}

	/**
	 * Returns the "before" string in the diff process
	 *
	 * @return the "before" string in the diff process
	 */
	// public String before()
	// {
	// return before;
	// }

	/**
	 * Returns the "after" string in the diff process
	 *
	 * @return the "after" string in the diff process
	 */
	// public String after()
	// {
	// return after;
	// }

	/**
	 * add javadoc comments
	 *
	 * @return a list of the differences
	 */
	public List<String> getDiff() {
		return this.getDiff(this.diff());
	}

	/**
	 * add javadoc comments
	 *
	 * @return a list of the differences
	 */
	public List<String> getDiff0() {
		return this.getDiff(this.diff0());
	}

	private List<String> getDiff(final List<LcsDiffEntry<Character>> diff) {
		LcsDiffType type = null;
		StringBuffer buf = new StringBuffer();
		List<String> changes = new ArrayList<>();

		for (LcsDiffEntry<Character> entry : diff) {
			if (type != entry.getType()) {
				if (type != null) {
					changes.add(buf.toString());
					buf.setLength(0);
				}

				buf.append(entry.getType());
				type = entry.getType();
			}
			buf.append(entry.getValue());
		}

		changes.add(buf.toString());
		return changes;
	}

	// EXAMPLE. Here's how you use it.
	// public static void main(String[] args)
	// {
	// // LcsString seq = new LcsString("abcdfghjqz", "abcdefgijkrxyz");
	// // LcsString seq = new LcsString("(", "(?:()(");
	// LcsString seq = new LcsString("ABC", "ACB");
	//
	// System.out.println("backtrack: " + seq.backtrack());
	//
	// System.out.println("diff: " + seq.diff());
	// System.out.println("getDiff: " + seq.getDiff());
	// System.out.println("getDiff0: " + seq.getDiff0());
	// }
}