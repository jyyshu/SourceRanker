package ui;

import model.Source;
import model.SourceRanker;

import javax.swing.*;

// A representation of the user interface for displaying the list of source in the ranking application
public class ViewListWindow extends JFrame {
    private JList sourceList;
    private DefaultListModel sourceListModel;

    // EFFECTS: initializes the window for viewing the list of sources
    public ViewListWindow(SourceRanker rankList) {
        super("List Of Sources");
        sourceListModel = new DefaultListModel();
        sourceList = new JList(sourceListModel);
        for (Source s : rankList.getSourceList()) {
            sourceListModel.addElement("RANK: " + s.getRank() + " " + s.getTitle());
        }
        add(sourceList);
        setVisible(true);
    }
}