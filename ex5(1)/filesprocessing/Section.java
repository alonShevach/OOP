package filesprocessing;

/*from package imports*/

import filesprocessing.Exceptions.InvalidInputException;
import filesprocessing.Filters.AllFilter;
import filesprocessing.Filters.Filter;
import filesprocessing.Filters.FilterFactory;
import filesprocessing.Orders.*;
/* imports*/
import java.io.File;
import java.util.ArrayList;

/**
 * a class represents a section in the command file, and deals with that
 * section according to the instructions.
 */
public class Section {
    /* the filer's command from the command file.*/
    private String filterCommand;
    /* the order's command from the command file.*/
    private String orderCommand;
    /* the number of line the filter is in, from the file's lines.*/
    private int filterLineNum;
    /* the number of line the order is in, from the file's lines.*/
    private int orderLineNum;
    /* a Filter type filer to filter the files from.*/
    private Filter filter;
    /* and order type order to order the filtered files according it*/
    private Order order;
    /* the array of the final files received after filtering and ordering.*/
    private File[] FinalFilesArr;

    /**
     * The constructor of the class
     *
     * @param filterCommandLine the filer's command from the command file.
     * @param orderCommandLine  the order's command from the command file.
     * @param filterLine        the number of line the filter is in, from the file's lines.
     * @param orderLine         the number of line the order is in, from the file's lines.
     */
    public Section(String filterCommandLine, String orderCommandLine, int filterLine, int orderLine) {
        filterLineNum = filterLine;
        orderLineNum = orderLine;
        filterCommand = filterCommandLine;
        orderCommand = orderCommandLine;
    }

    /**
     * a method that runs the commands in the specipic section, filtering and ordering
     * the given input files.
     * @param inputFiles files to filter and order according to the section's command.
     */
    public void runSection(ArrayList<File> inputFiles) {
        createFilter(filterCommand);
        createOrder(orderCommand);
        filterAndOrderFiles(inputFiles);
    }

    /**
     * a method creating a filter according to the command in this section.
     * @param filterCommand the filter's command in the file.
     */
    private void createFilter(String filterCommand) {
        try {
            filter = FilterFactory.filterCreator(filterCommand);
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage() + filterLineNum);
            filter = new AllFilter();
        }
    }

    /**
     * a method creating a order according to the command in this section.
     * @param orderCommand the order's command in the file.
     */
    private void createOrder(String orderCommand) {
        try {
            order = OrderFactory.OrderCreator(orderCommand);
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage() + orderLineNum);
            order = new AbsOrder(false);
        }
    }

    /**
     * This method filters and orders the input files according to the
     * filter's commands and the order's command's, and adds them to the
     * FinalFilesArr.
     * @param inputFiles the files to filter and order.
     */
    private void filterAndOrderFiles(ArrayList<File> inputFiles) {
        ArrayList<File> finalFiles;
        finalFiles = new ArrayList<>();
        for (File file : inputFiles) {
            /* adds the filtered files 1 by 1.*/
            if (filter.accept(file)) {
                finalFiles.add(file);
            }
        }
        FinalFilesArr = new File[finalFiles.size()];
        finalFiles.toArray(FinalFilesArr);
        /*sorts the array according to the order.*/
        order.quickSort(FinalFilesArr, 0, FinalFilesArr.length - 1);
    }

    /**
     * a getter, getting the array of the ordered and filtered files.
     * @return the array of the ordered and filtered files.
     */
    public File[] GetFilesArr() {
        return FinalFilesArr;
    }
}
