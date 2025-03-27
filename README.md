# Library Management System (DLBMS)

## Objective

The Library Management System (DLBMS) is designed to help librarians efficiently manage the books in a library. The system allows librarians to perform operations such as adding, updating, searching, viewing, and deleting books, all while managing the availability status of each book. The system ensures a smooth operation for managing books within a library.

## Requirements

### 1. Add a Book
- Accept the following inputs:
  - **Book ID**: A unique identifier for the book.
  - **Title**: The title of the book.
  - **Author**: The author of the book.
  - **Genre**: The genre or category of the book.
  - **Availability Status**: Either `Available` or `Checked Out`.
- Store book details in a repository.
- The **Book ID** must be unique, and **Title** and **Author** should be non-empty strings.
  
### 2. View All Books
- Display a list of all books stored in the catalog, including the ID, title, author, genre, and availability status.

### 3. Search for a Book by ID or Title
- Allow the librarian to search for a book by its **ID** or **Title**.
- If a book is found, its details should be displayed.
- If no book is found, a `Book Not Found` message should be displayed.

### 4. Update Book Details
- Modify the following details of a book:
  - **Availability status** (Available/Checked Out).
  - **Title** or **Author**.
- Ensure that updates are reflected in the catalog.

### 5. Delete a Book Record
- Remove a book from the catalog using its **Book ID**.
- After deletion, the book should no longer be available in the system.

### 6. Exit System
- Provide an option to exit the system when the librarian is finished.

## Constraints
- **Book ID** should be **unique** for each book.
- **Title** and **Author** must be **non-empty strings**.
- **Availability status** should be either `Available` or `Checked Out`.

## System Design

### Key Components:

1. **Book Entity**: Represents a book in the library system, containing properties such as `id`, `title`, `author`, `genre`, and `availabilityStatus`.
2. **BookService**: Handles the business logic for managing books, including adding, searching, updating, and deleting books.
3. **BookRepository**: Stores the books in memory or a database. Provides methods like `findById`, `findByTitle`, `save`, `deleteById`, and `findAll`.
4. **Commands**:
   - **AddBookCommand**: Adds a new book to the system.
   - **ViewAllBooksCommand**: Displays all books in the catalog.
   - **SearchByIdCommand**: Searches a book by its ID.
   - **SearchByTitleCommand**: Searches a book by its title.
   - **UpdateBookDetailsCommand**: Updates the details of a book.
   - **DeleteBookCommand**: Deletes a book from the catalog.

5. **Exceptions**:
   - **BookNotFoundException**: Thrown when a book is not found during search or delete operations.
   - **BookAlreadyCheckedOutException**: Thrown if a book is already checked out and an attempt is made to check it out again.
