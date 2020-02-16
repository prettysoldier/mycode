package algorithm.string.trie;

/**
 * @author heshuaijun
 * @date 2020/2/16 17:33
 */
public class TrieNode {
    public int path;
    public int end;
    public TrieNode[] map;

    public TrieNode () {
        this.path = 0;
        this.end = 0;
        this.map = new TrieNode[26];
    }
}
