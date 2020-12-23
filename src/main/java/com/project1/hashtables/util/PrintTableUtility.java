package com.project1.hashtables.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class helps to print the 2D array data in a table format.
 */
public class PrintTableUtility {

    /**
     * Prints the data to a file.
     *
     * @param table             the data which is to be printed in table format.
     * @param leftJustifiedRows if the data in the table is to be printed in the left justified format.
     * @param sort              if the input data is to be formatted.
     * @param skip              the columns to be skipped while sorting the table.
     * @param stream            the stream to which the data is to be printed.
     */
    public static void printTableToStream(String[][] table, boolean leftJustifiedRows, boolean sort, Set<Integer> skip, final PrintStream stream) {
        final Map<Integer, Integer> columnLengths = getColumnLengths(table);
        final String formatString = getTableFormat(leftJustifiedRows, columnLengths);

        final String borderFormat = getBorderFormat(columnLengths);

        stream.print(borderFormat);
        Arrays.stream(table).limit(1).forEach(a -> stream.printf(formatString, a));
        stream.print(borderFormat);

        if (sort) {
            table = sortTable(table, skip);
        }

        String[][] finalTable = table;
        Stream.iterate(1,
                (i -> i < finalTable.length),
                (i -> ++i))
                .forEach(a -> stream.printf(formatString,
                        (Object[]) finalTable[a]));
        stream.print(borderFormat);
    }

    /**
     * Sorts the input 2D array.
     *
     * @param table the 2D array to be sorted.
     * @param skip  the column numbers to be skipped while sorting the 2D array.
     * @return the sorted tables.
     */
    private static String[][] sortTable(String[][] table, Set<Integer> skip) {
        String[][] temp = new String[table.length - 1][table[0].length];
        System.arraycopy(table, 1, temp, 0, table.length - 1);
        Arrays.sort(temp, (o1, o2) -> {
            for (int i = 0; i < o1.length; i++) {
                if (!skip.contains(i)) {
                    int temp1 = Integer.parseInt(o1[i]);
                    int temp2 = Integer.parseInt(o2[i]);
                    int val = temp1 - temp2;
                    if (val != 0) {
                        return val;
                    }
                }
            }
            return 0;
        });
        table = temp;
        return table;
    }

    /**
     * Get the borders of the table.
     *
     * @param columnLengths the column lengths of the table.
     * @return the border format.
     */
    private static String getBorderFormat(Map<Integer, Integer> columnLengths) {
        String line = columnLengths.entrySet().stream().reduce("", (tempLine, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-", (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return tempLine + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        return line;
    }

    /**
     * Calculates the length for each column.
     *
     * @param table the input data for which the column lengths is to be found out.
     * @return the Map containing the lengths for each column.
     */
    private static Map<Integer, Integer> getColumnLengths(final String[][] table) {
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            columnLengths.putIfAbsent(i, 0);
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        return columnLengths;
    }

    /**
     * The format in which the data is to be printed.
     *
     * @param leftJustifiedRows true if the data is to be printed in left justified format.
     * @param columnLengths     the length of each column.
     * @return the format in which the table is to be printed.
     */
    private static String getTableFormat(boolean leftJustifiedRows, final Map<Integer, Integer> columnLengths) {
        final StringBuilder formatString = new StringBuilder();
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.forEach((key, value) -> formatString.append("| %").append(flag).append(value).append("s "));
        formatString.append("|\n");
        return formatString.toString();
    }
}
