package filesprocessing;

import filesprocessing.Exceptions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class of the project, this class process the directory and the
 * command file according to the instructions.
 * this class reads the files and divide them to the different section, than
 * passing it to the section class.
 */
public class DirectoryProcessor {

    /* Error prints*/
    private final static String NO_FILTER = "ERROR: FILTER sub-section missing \n";
    private final static String NO_ORDER = "ERROR: ORDER sub-section missing \n";
    private final static String BAD_FORMAT = "ERROR: bad format \n";
    private final static String IO_MSG = "ERROR: IO exception \n";

    /* file sections name*/
    private final static String FIRST_SUB_SECTION = "FILTER";
    private final static String SECOND_SUB_SECTION = "ORDER";

    /* the default order to do is abs*/
    private final static String DEFAULT_ORDER = "abs";
    /* a random empty line*/
    private final static String RANDOM_LINE = "";
    /* the expected num or args from the user*/
    private final static int NUM_OF_ARGS = 2;
    /* a String Arraylist which is the command file list of commands*/
    private ArrayList<String> commandFileList;
    /* a File arraylist which is the directory list of files.*/
    private ArrayList<File> directoryList;

    /**
     * the constructor of the class, creating the directoryList arraylist
     * and the CommandFileList arraylist.
     */
    private DirectoryProcessor() {
        directoryList = new ArrayList<>();
        commandFileList = new ArrayList<>();
    }

    /**
     * This method separates the different sections in the command file, by reading
     * the file and each line in it.
     * @return a arraylist of the different sections.
     */
    private ArrayList<Section> sectionSeparator() {
        ArrayList<Section> sections = new ArrayList<>();
        String currentFilter;
        String currentOrder;
        int curFilterLine;
        int curOrderLine;
        int index = 1;
        /* running on the commandfilelist */
        while (index < commandFileList.size()) {
            currentFilter = commandFileList.get(index);
            curFilterLine = index + 1;
            /* checks if the next 2 indexes is not a filter, and that there are 2 more lines
            in the file.
             */
            if (index + 2 < commandFileList.size() && !commandFileList.get(index + 2).equals(FIRST_SUB_SECTION)) {
                currentOrder = commandFileList.get(index + 2);
                curOrderLine = index + 3;
                index = index + 4;

            }
            /* there is no order, so it uses the default and changes indexes.*/
            else {
                currentOrder = DEFAULT_ORDER;
                curOrderLine = index + 2;
                index = index + 3;
            }
            sections.add(new Section(currentFilter, currentOrder, curFilterLine, curOrderLine));
        }
        return sections;
    }

    /** A method that detects the type II Errors in the commands file.
     * @throws SubSectionException in case of an
     * sub section that is incomplete.
     */
    private void commandsProcessing() throws SubSectionException {
        if (!commandFileList.get(0).equals(FIRST_SUB_SECTION)) {
            throw new SubSectionException(NO_FILTER);
        }
        String twoLinesBefore = null;
        String oneLineBefore = commandFileList.get(0);
        for (int i = 1; i < commandFileList.size(); i++) {
            if (twoLinesBefore != null && twoLinesBefore.equals(FIRST_SUB_SECTION)) {
                if (!commandFileList.get(i).equals(SECOND_SUB_SECTION)) {
                    throw new SubSectionException(NO_ORDER);
                }
                twoLinesBefore = RANDOM_LINE;
                oneLineBefore = commandFileList.get(i);
            } else if (twoLinesBefore != null && twoLinesBefore.equals(SECOND_SUB_SECTION)
                    && !oneLineBefore.equals(FIRST_SUB_SECTION)
                    && !commandFileList.get(i).equals(FIRST_SUB_SECTION)) {
                throw new SubSectionException(NO_FILTER);
            } else {
                twoLinesBefore = oneLineBefore;
                oneLineBefore = commandFileList.get(i);
            }
        }
        if (twoLinesBefore != null
                && (!(twoLinesBefore.equals(SECOND_SUB_SECTION) || oneLineBefore.equals(SECOND_SUB_SECTION))
                || (twoLinesBefore.equals(FIRST_SUB_SECTION) || oneLineBefore.equals(FIRST_SUB_SECTION)))) {
            throw new SubSectionException(NO_ORDER);
        }
    }

    /**
     * creates the arraylist of the directorylist on the given
     * directory as a parameter.
     * @param directoryPath the path to the directory to create
     *                      the list to.
     * @return true upon success, false otherwise.
     */
    private boolean directoryReader(String directoryPath) {
        File tempDirectory = new File(directoryPath);
        File[] filesArr = tempDirectory.listFiles();
        if (filesArr != null) {
            for (File file : filesArr) {
                if (file != null && file.isFile()) {
                    directoryList.add(file);
                }
            }
        }
        return !(directoryList == null || directoryList.size() == 0);
    }

    /**
     * Creating a String arraylist from the given commandFilePath
     * @param commandFilePath The given path to create the list accordingly.
     * @return true upon success, false otherwise.
     * @throws IOException return IOException if the given path is Invalid.
     * @throws SubSectionException in case of an
     * sub section that is incomplete.
     */
    private boolean fileReader(String commandFilePath) throws IOException, SubSectionException {
        FileReader reader = new FileReader(commandFilePath);
        BufferedReader buffReader = new BufferedReader(reader);
        String currentLine = buffReader.readLine();
        while (currentLine != null) {
            commandFileList.add(currentLine);
            currentLine = buffReader.readLine();
        }
        if (commandFileList.size() == 1) {
            throw new SubSectionException(BAD_FORMAT);
        }
        return !(commandFileList == null || commandFileList.size() == 0);
    }

    /**
     * the main function, receiving the directory and command file paths
     * and filter the directory according to the commands in the file, this
     * method also catches all the other thrown Exceptions and deals with them.
     * this method is encharge of running all the methods to filter and
     * order the directory.
     * @param args a DirectoryPath and a CommandFilePath.
     */
    public static void main(String[] args) {
        if (args.length != NUM_OF_ARGS) {
            InvalidUsageException e = new InvalidUsageException();
            System.err.println(e.getMessage());
            return;
        }
        String directoryPath = args[0];
        String commandPath = args[1];
        DirectoryProcessor processor = new DirectoryProcessor();
        try {
            boolean notEmpty = processor.fileReader(commandPath);
            if (!notEmpty) {
                return;
            }
        }
        catch (SubSectionException e) {
            System.err.println(e.getMessage());
            return;
        }
        catch (IOException e) {
            System.err.println(IO_MSG);
            return;
        }
        try {
            processor.commandsProcessing();
        }
        /*if there is a missing sub sections*/
        catch (SubSectionException e) {
            System.err.println(e.getMessage());
            return;
        }
        boolean directoryNotEmpty = processor.directoryReader(directoryPath);
        if (!directoryNotEmpty) {
            return;
        }
        ArrayList<Section> sections = processor.sectionSeparator();
        if (sections == null) {
            return;
        }
        /* now it is running on all the sections and starting to
        filter and order them.
         */
        for (Section section : sections) {
            section.runSection(processor.directoryList);
            for (File file : section.GetFilesArr()) {
                System.out.println(file.getName());
            }
        }
    }
}

