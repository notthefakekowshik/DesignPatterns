package org.example.StructualPatterns.ProxyPattern;

// Subject: Interface representing file operations
interface FileManager {
    void readFile(String fileName);
    void writeFile(String fileName, String content);
}

// RealSubject: Actual file manager that performs file operations
class RealFileManager implements FileManager {
    @Override
    public void readFile(String fileName) {
        System.out.println("Reading file: " + fileName);
    }

    @Override
    public void writeFile(String fileName, String content) {
        System.out.println("Writing to file: " + fileName + "\nContent: " + content);
    }
}

// Proxy: Proxy file manager that provides access control
class ProxyFileManager implements FileManager {
    private FileManager realFileManager;
    private String userRole;

    public ProxyFileManager(String userRole) {
        this.userRole = userRole;
    }

    private boolean hasWriteAccess() {
        return userRole.equals("admin");
    }

    @Override
    public void readFile(String fileName) {
        // Allow read access for all users
        if (realFileManager == null) {
            realFileManager = new RealFileManager();
        }
        realFileManager.readFile(fileName);
    }

    @Override
    public void writeFile(String fileName, String content) {
        if (hasWriteAccess()) {
            if (realFileManager == null) {
                realFileManager = new RealFileManager();
            }
            realFileManager.writeFile(fileName, content);
        } else {
            System.out.println("Access denied. Only admin users can write to files.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Client uses the ProxyFileManager to access files
        FileManager userFileManager = new ProxyFileManager("user");
        FileManager adminFileManager = new ProxyFileManager("admin");

        // User attempts to read and write files
        userFileManager.readFile("file1.txt"); // User can read files
        userFileManager.writeFile("file1.txt", "Content for user"); // Access denied

        // Admin reads and writes files
        adminFileManager.readFile("file1.txt"); // Admin can read files
        adminFileManager.writeFile("file1.txt", "Content for admin"); // Admin can write files
    }
}

