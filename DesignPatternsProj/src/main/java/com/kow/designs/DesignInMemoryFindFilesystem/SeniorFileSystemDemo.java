package com.kow.designs.DesignInMemoryFindFilesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// ==========================================
// 1. DATA MODEL (Composite Pattern)
// ==========================================

// The Abstract Component
abstract class Entry {
    protected String name;
    protected int size;

    public Entry(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    // Polymorphic method: Files return empty list, Dirs return children
    // This avoids casting in the search loop!
    public abstract List<Entry> getChildren();

    // Optional helper to check type
    public abstract boolean isDirectory();
}

// The Leaf Node
class File extends Entry {
    public File(String name, int size) {
        super(name, size);
    }

    @Override
    public List<Entry> getChildren() {
        // A file has no children, return empty list to be safe
        // return new ArrayList<>();

        // 1. Returns a highly optimized, shared, immutable list.
        // 2. Throws exception if anyone tries to add() to it.
        return Collections.emptyList();
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}

// The Composite Node
class Directory extends Entry {
    private List<Entry> children;

    public Directory(String name) {
        super(name, 0); // Directory size is 0 or sum of children (omitted for simplicity)
        this.children = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        this.children.add(entry);
    }

    @Override
    public List<Entry> getChildren() {
        return children;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}

// ==========================================
// 2. FILTER LOGIC (Specification Pattern)
// ==========================================

// The Strategy Interface
interface Filter {
    boolean isValid(Entry entry);
}

// --- Concrete Specs ---

class NameFilter implements Filter {
    private String name;

    public NameFilter(String name) {
        this.name = name;
    }

    @Override
    public boolean isValid(Entry entry) {
        return entry.getName().equals(name);
    }
}

class MinSizeFilter implements Filter {
    private int minSize;

    public MinSizeFilter(int minSize) {
        this.minSize = minSize;
    }

    @Override
    public boolean isValid(Entry entry) {
        return entry.getSize() >= minSize;
    }
}

class ExtensionFilter implements Filter {
    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean isValid(Entry entry) {
        return entry.getName().endsWith(extension);
    }
}

// --- Logical Combinators (The Magic) ---

class AndFilter implements Filter {
    private List<Filter> filters;

    public AndFilter(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean isValid(Entry entry) {
        for (Filter filter : filters) {
            // Short-circuit: if any fail, the whole AND fails
            if (!filter.isValid(entry))
                return false;
        }
        return true;
    }
}

class OrFilter implements Filter {
    private List<Filter> filters;

    public OrFilter(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean isValid(Entry entry) {
        for (Filter filter : filters) {
            // Short-circuit: if any succeed, the whole OR succeeds
            if (filter.isValid(entry))
                return true;
        }
        return false;
    }
}

// ==========================================
// 3. SEARCH ENGINE (Traversal Logic)
// ==========================================

class FileSearcher {
    public List<Entry> search(Entry root, Filter searchCriteria) {
        List<Entry> result = new ArrayList<>();
        Queue<Entry> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry current = queue.poll();

            // 1. Check Business Logic (Decoupled from traversal)
            if (searchCriteria.isValid(current)) {
                result.add(current);
            }

            // 2. Traversal (Polymorphic - no casting needed!)
            // Files return empty list, Dirs return actual children
            for (Entry child : current.getChildren()) {
                queue.add(child);
            }
        }
        return result;
    }
}

// ==========================================
// 4. DRIVER CODE (Demonstration)
// ==========================================

public class SeniorFileSystemDemo {
    public static void main(String[] args) {
        // --- A. Setup the File System ---
        Directory root = new Directory("root");
        Directory subDir1 = new Directory("subDir1");
        Directory subDir2 = new Directory("subDir2");

        // Files for testing the Logic
        // Case 1: X and Y (Name=target AND Size>10)
        File fileXY = new File("target.txt", 50);

        // Case 2: Z (Name=special_log)
        File fileZ = new File("special_log", 5);

        // Case 3: K and A (Ext=.java AND Size>500)
        File fileKA = new File("BigCode.java", 1000);

        // Case 4: Junk file (Should be ignored)
        File junk = new File("junk.txt", 1);

        // Build Tree
        subDir1.addEntry(fileXY);
        subDir1.addEntry(junk);
        subDir2.addEntry(fileZ);
        subDir2.addEntry(fileKA);
        root.addEntry(subDir1);
        root.addEntry(subDir2);

        System.out.println("--- File System Structure ---");
        System.out.println("root/subDir1: target.txt(50), junk.txt(1)");
        System.out.println("root/subDir2: special_log(5), BigCode.java(1000)");
        System.out.println("-----------------------------");

        // --- B. Construct the Complex Filter ---
        // Logic: (Name="target.txt" AND Size>10) OR (Name="special_log") OR
        // (Ext=".java" AND Size>500)

        // 1. Group 1 (X AND Y)
        Filter group1 = new AndFilter(Arrays.asList(
                new NameFilter("target.txt"),
                new MinSizeFilter(10)));

        // 2. Group 2 (Z)
        Filter group2 = new NameFilter("special_log");

        // 3. Group 3 (K AND A)
        Filter group3 = new AndFilter(Arrays.asList(
                new ExtensionFilter(".java"),
                new MinSizeFilter(500)));

        // 4. Combine with OR
        Filter complexQuery = new OrFilter(Arrays.asList(group1, group2, group3));

        // --- C. Execute Search ---
        FileSearcher searcher = new FileSearcher();
        List<Entry> results = searcher.search(root, complexQuery);

        // --- D. Output Results ---
        System.out.println(
                "\nSearching for: (Name='target.txt' AND Size>10) OR (Name='special_log') OR (Ext='.java' AND Size>500)");
        System.out.println("Expected: target.txt, special_log, BigCode.java");
        System.out.println("Actual Results:");
        for (Entry e : results) {
            System.out.println(" -> Found: " + e.getName() + " (Size: " + e.getSize() + ")");
        }
    }
}