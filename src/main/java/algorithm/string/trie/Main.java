package algorithm.string.trie;

/**
 * @author heshuaijun
 * @date 2020/2/16 17:32
 */
public class Main {

    public static void main (String[] args) {
        Trie trie = new Trie();
        trie.insert("abcd");
        trie.insert("bcd");
        trie.insert("abd");
        trie.insert("efg");

        System.out.println(trie.search("abc"));
        System.out.println(trie.search("abcd"));

        System.out.println(trie.prefixNumber("a"));

        trie.delete("abcd");
        System.out.println(trie.search("abcd"));
        System.out.println(trie.prefixNumber("a"));
    }


}
