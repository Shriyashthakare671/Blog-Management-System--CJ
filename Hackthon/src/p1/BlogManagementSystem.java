package p1;

import java.io.*;
import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Blog {
    private String title;
    private String content;
    private String author;
    private String category;

    public Blog(String title, String content, String author, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

public void setContent(String newContent) {
// TODO Auto-generated method stub

}
}

public class BlogManagementSystem {
    private static final String USERS_FILE = "users.txt";
    private static final String BLOGS_FILE = "blogs.txt";
    private static final String CATEGORIES_FILE = "categories.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();
    private static List<Blog> blogs = new ArrayList<>();
    private static Set<String> categories = new HashSet<>();
    private static User currentUser;

    public static void main(String[] args) {
        loadUsers();
        loadBlogs();
        loadCategories();

        while (true) {
            System.out.println("\nWelcome to Blog Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    saveUsers();
                    saveBlogs();
                    saveCategories();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }

            while (currentUser != null) {
                showMainMenu();
            }
        }
    }

    private static void register() {
        System.out.println("\nRegistration Screen");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }

    private static void login() {
        System.out.println("\nLogin Screen");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful!");
                return;
            }
        }

        System.out.println("Invalid username or password!");
    }

    private static void showMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Create Blog");
        System.out.println("2. Edit Blog");
        System.out.println("3. View Blog");
        System.out.println("4. Search Blog");
        System.out.println("5. Delete Blog");
        System.out.println("6. View Categories");
        System.out.println("7. View All Blogs");
        System.out.println("8. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createBlog();
                break;
            case 2:
                editBlog();
                break;
            case 3:
                viewBlog();
                break;
            case 4:
                searchBlog();
                break;
            case 5:
                deleteBlog();
                break;
            case 6:
                viewCategories();
                break;
            case 7:
                viewAllBlogs();
                break;
            case 8:
                currentUser = null;
                System.out.println("Logged out successfully!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void createBlog() {
        System.out.println("\nCreate Blog");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        blogs.add(new Blog(title, content, currentUser.getUsername(), category));
        categories.add(category);
        System.out.println("Blog created successfully!");
    }

    private static void editBlog() {
        System.out.println("\nEdit Blog");
        System.out.print("Enter title of the blog to edit: ");
        String title = scanner.nextLine();

        for (Blog blog : blogs) {
            if (blog.getTitle().equals(title) && blog.getAuthor().equals(currentUser.getUsername())) {
                System.out.print("Enter new content: ");
                String newContent = scanner.nextLine();
                blog.setContent(newContent);
                System.out.println("Blog edited successfully!");
                return;
            }
        }

        System.out.println("Blog not found or you don't have permission to edit!");
    }

    private static void viewBlog() {
        System.out.println("\nView Blog");
        System.out.print("Enter title of the blog to view: ");
        String title = scanner.nextLine();

        for (Blog blog : blogs) {
            if (blog.getTitle().equals(title)) {
                System.out.println("Title: " + blog.getTitle());
                System.out.println("Content: " + blog.getContent());
                System.out.println("Author: " + blog.getAuthor());
                System.out.println("Category: " + blog.getCategory());
                return;
            }
        }

        System.out.println("Blog not found!");
    }

    private static void searchBlog() {
        System.out.println("\nSearch Blog");
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        for (Blog blog : blogs) {
            if (blog.getTitle().contains(keyword) || blog.getContent().contains(keyword)) {
                System.out.println("Title: " + blog.getTitle());
                System.out.println("Content: " + blog.getContent());
                System.out.println("Author: " + blog.getAuthor());
                System.out.println("Category: " + blog.getCategory());
                System.out.println();
            }
        }
    }

    private static void deleteBlog() {
        System.out.println("\nDelete Blog");
        System.out.print("Enter title of the blog to delete: ");
        String title = scanner.nextLine();

        Iterator<Blog> iterator = blogs.iterator();
        while (iterator.hasNext()) {
            Blog blog = iterator.next();
            if (blog.getTitle().equals(title) && blog.getAuthor().equals(currentUser.getUsername())) {
                iterator.remove();
                System.out.println("Blog deleted successfully!");
                return;
            }
        }

        System.out.println("Blog not found or you don't have permission to delete!");
    }

    private static void viewCategories() {
        System.out.println("\nCategories");
        for (String category : categories) {
            System.out.println(category);
        }
    }

    private static void viewAllBlogs() {
        System.out.println("\nAll Blogs");
        for (Blog blog : blogs) {
            System.out.println("Title: " + blog.getTitle());
            System.out.println("Content: " + blog.getContent());
            System.out.println("Author: " + blog.getAuthor());
            System.out.println("Category: " + blog.getCategory());
            System.out.println();
        }
    }

    private static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing users file: " + e.getMessage());
        }
    }

    private static void loadBlogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BLOGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                blogs.add(new Blog(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.out.println("Error reading blogs file: " + e.getMessage());
        }
    }

    private static void saveBlogs() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BLOGS_FILE))) {
            for (Blog blog : blogs) {
                writer.write(blog.getTitle() + "," + blog.getContent() + "," + blog.getAuthor() + "," + blog.getCategory() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing blogs file: " + e.getMessage());
        }
    }

    private static void loadCategories() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CATEGORIES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                categories.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading categories file: " + e.getMessage());
        }
    }

    private static void saveCategories() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CATEGORIES_FILE))) {
            for (String category : categories) {
                writer.write(category + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing categories file: " + e.getMessage());
        }
    }
}