package com.company;
import de.vandermeer.asciitable.AsciiTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Table {

    private final String[] moves;
    private  String rend;

    public Table(String[] moves) {
        this.moves = moves;
    }

    private List<String> makeFirstRow() {
        List<String> row = new ArrayList<>(Arrays.asList(this.moves));
        row.add(0, "PC/user");
        return row;
    }

    private List<String> makeResultRows(int numberOfRow) {
        String[] row = new String[this.moves.length];
        for (int i = 0; i < this.moves.length; i++) {
            row[i] = ChooseWinner.winner(this.moves, numberOfRow, i);
        }
        List<String> result = new ArrayList<>(Arrays.asList(row));
        result.add(0, this.moves[numberOfRow]);
        return result;
    }

    private void createTable() {
        List[] rows = collectRows();
        AsciiTable at = new AsciiTable();
        for (int i = 0; i <= this.moves.length; i++) {
            at.addRule();
            at.addRow(rows[i]);
        }
        at.addRule();
        this.rend = at.render();
    }

    private List[] collectRows() {
        List[] rows = new List[this.moves.length + 1];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = (i == 0 ? this.makeFirstRow() : this.makeResultRows(i - 1));
        }
        return rows;
    }

    public void showTable() {
        this.createTable();
        System.out.println(this.rend);
    }
}
