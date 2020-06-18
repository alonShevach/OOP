/**
 * this class represent a patron that has book intrests,
 * the patron has firstname lastname and book preferences.
 * also he has an EnjoymentThreshold.
 */
public class Patron {
    /**
     * The first name of the patron.
     */
    final String FirstName;

    /**
     * The name name of the patron.
     */
    final String LastName;

    /**
     * The weight the patron assigns to the comic aspects of books.
     */
    final int patroncomicTendency;

    /**
     * The weight the patron assigns to the dramatic aspects of books.
     */
    final int patrondramaticTendency;

    /**
     * The weight the patron assigns to the educational aspects of books.
     */
    final int patroneducationalTendency;
    /**
     * The minimal literary value a book must have for this patron to enjoy it.
     */
    final int EnjoymentThreshold;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new patron with the given characteristics.
     *
     * @param patronFirstName The first name of the patron.
     * @param patronLastName The last name of the patron.
     * @param comicTendency The weight the patron assigns to the comic aspects of books.
     * @param dramaticTendency The weight the patron assigns to the dramatic aspects of books.
     * @param educationalTendency The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold The minimal literary value a book must have for this patron to enjoy.
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency,
           int dramaticTendency, int educationalTendency, int patronEnjoymentThreshold){
        FirstName = patronFirstName;
        LastName = patronLastName;
        patroncomicTendency = comicTendency;
        patrondramaticTendency = dramaticTendency;
        patroneducationalTendency = educationalTendency;
        EnjoymentThreshold = patronEnjoymentThreshold;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Returns the literary value this patron assigns to the given book.
     * @param book The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book){
        return (book.comicValue*patroncomicTendency + book.educationalValue*patroneducationalTendency
                + book.dramaticValue*patrondramaticTendency);
    }

    /**
     * Returns a string representation of the patron,
     * which is a sequence of its first and last name, separated by a single white space.
     * For example, if the patron's first name is "Ricky" and his last name is "Bobby",
     * this method will return the String "Ricky Bobby".

     * @return the String representation of this patron.
     */
    String stringRepresentation(){
        return (FirstName + ' ' + LastName);
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) {
        return (getBookScore(book) >= EnjoymentThreshold);

    }
}
