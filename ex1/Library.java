/**
 * This class represents a library, which hold a collection of books.
 * each library has a maximum number of books it can hold, maximal number of books each patron can borrow
 * and maximal number of registered patrons this library can handle.
 */
public class Library {

    /**
     * The maximum number of books this library can hold.
     */
    final int LibrarymaxBookCapacity;

    /**
     * The maximum number of books each patron can borrow.
     */
    final int LibrarymaxBorrowedBooks;

    /**
     * The maximum number of registered patrons this library can handle.
     */
    final int LibrarymaxPatronCapacity;

    /**
     * a list of books that are in the library.
     */
    final Book[] Bookslist;

    /**
     * A list of patrons that are registered to the library
     */
    final Patron[] PatronRegistered;

    /**
     * An index that presents the next place of the book in the list
     */
    int nextBookIndex;

    /**
     * An index that presents the next place of the book in the list
     */
    int nextpatronindex;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new library with the given parameters.
     *
     * @param maxBookCapacity The maximum number of books this library can hold.
     * @param maxBorrowedBooks The maximum number of books each patron can borrow.
     * @param maxPatronCapacity The maximum number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        LibrarymaxBookCapacity = maxBookCapacity;
        LibrarymaxBorrowedBooks = maxBorrowedBooks;
        LibrarymaxPatronCapacity = maxPatronCapacity;
        Bookslist = new Book [LibrarymaxBookCapacity];
        PatronRegistered = new Patron [LibrarymaxPatronCapacity];
        nextBookIndex = 0;
        nextpatronindex = 0;
    }

    /**
     * Adds the given book to the books array of the library if it is possible.
     *
     * @param book The book to add to the library.
     * @return a non-negative id number for the book if the book was successfully added,
     * or if the book was already in the library; a negative number otherwise
     */
    int addBookToLibrary(Book book) {
        if (nextBookIndex == LibrarymaxBorrowedBooks) {
            return -1;
            }
        for (int i=0; i < nextBookIndex; i ++){
            if (Bookslist[i] == book) {
                return i;
                }
            }
        Bookslist[nextBookIndex] = book;
        nextBookIndex++;
        return nextBookIndex-1;
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     *
     * @param book The book for which to find the id number
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book){
        for (int i=0; nextBookIndex>i; i++) {
            if (Bookslist[i] == book) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId The id number of the book to check
     * @return true if the book with the given id is available, false otherwise
     */
    boolean isBookAvailable(int bookId){
        return Bookslist[bookId].getCurrentBorrowerId() == -1;
    }

    /**
     * Returns true if the given number is an id of a book in the library, false otherwise.
     *
     * @param bookId The id needed to be checked.
     * @return true if the given number is an id of a book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        return nextBookIndex > bookId;
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise
     *
     * @param patronId The id to check
     * @return true if the given number is an id of a patron in the library, false otherwise
     */
    boolean isPatronIdValid(int patronId) {
        return nextpatronindex > patronId;

    }

    /**
     * Returns a non-negative id number of the given patron if he is registered to this library, -1 otherwise
     *
     * @param patron The patron for which to find the id number
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise
     */
    int getPatronId(Patron patron) {
        for (int i = 0; nextpatronindex >i; i++ ) {
            if (PatronRegistered[i] == patron){
                return i;
            }
        }
        return -1;
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot
     * and the patron was successfully registered, a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        for (int i = 0; i< nextpatronindex; i++){
            if (PatronRegistered[i] == patron){
                return i;
                }
            }
        if (nextpatronindex == LibrarymaxPatronCapacity){
            return -1;
            }
        PatronRegistered[nextpatronindex] = patron;
        nextpatronindex++;
        return nextpatronindex -1;
    }

    /**
     * a function that counts the number of books a single patron borrowed
     * @param patronId The id number of the patron to count his borrowed books
     * @return int the is the number of books he borrowed.
     */
    int borrowedBooksCounter(int patronId){
        int counter; counter = 0;
        for (int i = 0; i< nextpatronindex; i++){
            if (Bookslist[i].currentBorrowerId == patronId){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available, the given patron isn't already borrowing the maximal number of books,
     * and if the patron will enjoy this book.
     *
     * @param bookId   The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        if ((!isBookIdValid(bookId)) || (!isBookAvailable(bookId)) || (!isPatronIdValid(patronId)) ||
                (borrowedBooksCounter(patronId) >= LibrarymaxPatronCapacity) ||
                (!PatronRegistered[patronId].willEnjoyBook(Bookslist[bookId]))){
            return false;
            }
        Bookslist[bookId].setBorrowerId(patronId);
        return true;
    }

    /**
     * Returns the given book.
     *
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId) {
        if (isBookIdValid(bookId)) {
            Bookslist[bookId].returnBook();
            }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist
     *
     * @param patronId The id number of the patron to suggest the book to
     * @return The available book the patron will enjoy the most. Null if no book is available
     */
    Book suggestBookToPatron(int patronId) {
        if ((nextBookIndex == 0) ||(!isPatronIdValid(patronId))){
            return null;
            }
        Book bestmatch; bestmatch = Bookslist[0];
        for (int i=1; i<nextBookIndex; i++){
            if (PatronRegistered[patronId].getBookScore(Bookslist[i])>PatronRegistered[patronId].getBookScore(bestmatch)){
                bestmatch = Bookslist[i];
                }
            }
        if (!PatronRegistered[patronId].willEnjoyBook(bestmatch)){
            return null;
            }
        return bestmatch;
    }
}
