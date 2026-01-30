package com.kow.designs.DesignInMemoryFindFilesystem;

import java.util.*;

// One class to rule them all (Bad Practice for LLD)
class File {
    String name;
    int size;
    boolean isDirectory;
    List<File> children; // Only used if isDirectory is true

    public File(String name, int size, boolean isDirectory) {
        this.name = name;
        this.size = size;
        this.isDirectory = isDirectory;
        this.children = new ArrayList<>();
    }

    public void add(File file) {
        if (this.isDirectory) {
            this.children.add(file);
        }
    }
}

class FileSearcher {

    // Requirement: Find files by Name AND Size
    public List<File> search(File root, String nameToFind, Integer minSize) {
        List<File> result = new ArrayList<>();

        // Using Queue for BFS (Standard approach)
        Queue<File> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            File current = queue.poll();

            // 1. HARDCODED FILTER LOGIC
            // If it's a file (not dir), check the criteria

            // This will be solved using SpecificationPattern in the next approaches.
            // We remove the hardcoded if (name.equals && size > ...) from the searcher. We
            // define a "Specification" for what we want.
            if (!current.isDirectory) {
                boolean matchesName = (nameToFind == null) || current.name.equals(nameToFind);
                boolean matchesSize = (minSize == null) || current.size > minSize;

                if (matchesName && matchesSize) {
                    result.add(current);
                }
            }

            // 2. TRAVERSAL LOGIC
            // If directory, add children to queue
            if (current.isDirectory) {
                for (File child : current.children) {
                    queue.add(child);
                }
            }
        }

        return result;
    }
}

public class JuniorFileSystem {

}
