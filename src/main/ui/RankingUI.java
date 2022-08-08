package ui;

import model.Source;
import model.SourceRanker;
import persistence.SourceRankerReader;
import persistence.SourceRankerWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;

// persistence features largely based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Some GUI features based on AlarmController UI; link below
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

// Some GUI features also based on various demo Projects from:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Specific projects include:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/IconDemoProject/src/components/IconDemoApp.java
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java

// A representation of the Source Ranking Application's GUI/ main window
public class RankingUI extends JFrame implements ActionListener {
    private static final int WIDTH = 550;
    private static final int HEIGHT = 250;
    private static final String JSON_SAVE = "./data/sourceranking.json";
    private SourceRanker rankList;
    private SourceRankerReader sourceRankerReader;
    private SourceRankerWriter sourceRankerWriter;
    protected JButton viewButton;
    protected JButton rankButton;
    protected JButton addButton;
    protected JButton removeButton;
    protected JButton saveButton;
    protected JButton loadButton;
    protected JButton quitButton;
    protected JList sources;
    private DefaultListModel sourcesModel;
    JPanel window;

    // MODIFIES: this
    // EFFECTS: Initializes the main window, options panel (buttons), displays app logo, and panel of sources added
    public RankingUI() {
        rankList = new SourceRanker();
        window = new JPanel();
        sourceRankerReader = new SourceRankerReader(JSON_SAVE);
        sourceRankerWriter = new SourceRankerWriter(JSON_SAVE);
        importSources();
        window.setBackground(new Color(255, 206, 254));
        setContentPane(window);
        setTitle("Source Ranker");
        setSize(WIDTH, HEIGHT);
        addSourceRankerLogo();
        addButtonOptions();
        addSourcePanel();
        window.setVisible(true);
        setVisible(true);
    }

    // EFFECTS: helper used to import and add the Source Ranker application logo
    private void addSourceRankerLogo() {
        ImageIcon melon = new ImageIcon("sourcerankerlogo.png");
        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(melon);
        window.add(photoLabel);
    }

    // EFFECTS: helper used to add the sources already added (identifier) to the application
    private void addSourcePanel() {
        sourcesModel = new DefaultListModel();
        for (Source s : rankList.getSourceList()) {
            sourcesModel.addElement("Source: " + s.getIdentifier());
        }
        sources = new JList(sourcesModel);
        sources.setBackground(new Color(176, 249, 212));
        window.add(sources, BorderLayout.EAST);
        setVisible(true);
    }

    // EFFECTS: imports sources to be ranked
    @SuppressWarnings("methodlength")
    private void importSources() {
        rankList = new SourceRanker();
        Source myc = new Source("myc",
                "MYC-induced human acute myeloid leukemia requires a continuing IL-3/GM-CSF costimulus",
                "Hematopoietic clones with leukemogenic mutations arise in healthy people as they age, but "
                        +
                        "progression to acute myeloid leukemia (AML) is rare. Recent evidence suggests that the "
                        +
                        "microenvironment may play an important role in modulating human AML population dynamics.",
                0);
        Source aml = new Source("aml",
                "Novel Gene Signature Reveals Prognostic Model in Acute Myeloid Leukemia",
                "Acute myeloid leukemia (AML) is a clonal malignant disease with poor prognosis and a low "
                        +
                        "overall survival rate. Although many studies on the treatment and detection of AML have "
                        +
                        "been conducted, the molecular mechanism of AML development and progression has not been "
                        +
                        "fully elucidated. The present study was designed to pursuit the molecular mechanism of AML "
                        +
                        "using a comprehensive bioinformatics analysis, and build an applicable model to predict "
                        +
                        "the survival probability of AML patients in clinical use.",
                0);
        Source photosynth = new Source("photosynth",
                "Photosynthetic Research in Plant Science",
                "Photosynthesis is a highly regulated, multistep process. It encompasses the harvest of solar"
                        +
                        " energy, transfer of excitation energy, energy conversion, electron transfer from water to "
                        +
                        "NADP+, ATP generation and a series of enzymatic reactions that assimilate carbon dioxide "
                        +
                        "and synthesize carbohydrate.",
                0);
        Source flowering = new Source("flowering",
                "Growth and photosynthesis of Japanese flowering cherry under simulated microgravity conditions",
                "The photosynthetic rate, the leaf characteristics related to photosynthesis, such as the "
                        +
                        "chlorophyll content, chlorophyll a/b ratio and density of the stomata, the leaf area and the "
                        +
                        "dry weight in seedlings of Japanese flowering cherry grown under normal gravity and simulated"
                        +
                        " microgravity conditions were examined. No significant differences were found in the"
                        +
                        " photosynthetic rates between the two conditions.",
                0);
        Source ras = new Source("ras",
                "RAS and MYC: Co-conspirators in Cancer",
                "Aberrant activation of RAS influences a variety of cellular pathways, the majority of which have"
                        +
                        " been highlighted in RAS pathway v2.0. Despite this knowledge, efforts to attenuate RAS "
                        +
                        "activity through the therapeutic inhibition of downstream signaling molecules have been "
                        +
                        "relatively unsuccessful, in large part due to the ability of RAS signaling pathways to "
                        +
                        "“rewire”, increasing therapeutic resistance1,2. Given our current inability to directly "
                        +
                        "inhibit RAS, identification of an essential downstream target in RAS-driven cancers would be "
                        +
                        "highly valuable. Here, we discuss RAS-mediated posttranslational modifications (PTMs) of "
                        +
                        "c-MYC (MYC) that increase MYC activity and position MYC as a critical effector of "
                        +
                        "RAS-driven cancer.",
                0);
        rankList.addSource(myc);
        rankList.addSource(aml);
        rankList.addSource(photosynth);
        rankList.addSource(flowering);
        rankList.addSource(ras);
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: helper used to add buttons for user to choose options within application
    public void addButtonOptions() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        viewButton = new JButton("View List");
        viewButton.setMnemonic(KeyEvent.VK_V);
        viewButton.addActionListener(this);
        rankButton = new JButton("Rank List");
        rankButton.setMnemonic(KeyEvent.VK_R);
        rankButton.addActionListener(this);
        addButton = new JButton("Add Source");
        addButton.setMnemonic(KeyEvent.VK_A);
        addButton.addActionListener(this);
        removeButton = new JButton("Delete Source");
        removeButton.setMnemonic(KeyEvent.VK_D);
        removeButton.addActionListener(this);
        saveButton = new JButton("Save Sources");
        saveButton.setMnemonic(KeyEvent.VK_S);
        saveButton.addActionListener(this);
        loadButton = new JButton("Load Sources");
        loadButton.setMnemonic(KeyEvent.VK_L);
        loadButton.addActionListener(this);
        quitButton = new JButton("Quit");
        quitButton.setMnemonic(KeyEvent.VK_Q);
        quitButton.addActionListener(this);
        buttons.add(viewButton);
        buttons.add(rankButton);
        buttons.add(addButton);
        buttons.add(removeButton);
        buttons.add(saveButton);
        buttons.add(loadButton);
        buttons.add(quitButton);
        buttons.setBackground(new Color(176, 249, 212));
        buttons.setVisible(true);
        window.add(buttons);
    }

    // EFFECTS: initializes a new window to display source titles and their rank scores
    public void viewListWindow() {
        JFrame sourceWindow = new ViewListWindow(rankList);
        sourceWindow.setSize(750, 250);
        sourceWindow.setVisible(true);
    }

    // EFFECTS: representation of actions to be executed upon specific user inputs
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == viewButton) {
            viewListWindow();
        } else if (ae.getSource() == rankButton) {
            performRanking();
        } else if (ae.getSource() == addButton) {
            addSource();
        } else if (ae.getSource() == removeButton) {
            removeSource();
        } else if (ae.getSource() == saveButton) {
            saveSources();
        } else if (ae.getSource() == loadButton) {
            loadSources();
        } else if (ae.getSource() == quitButton) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: executes the ranking of sources based on the parameter/keyword the
    //          user had specified
    public void performRanking() {
        String inputParameter = JOptionPane.showInputDialog(null,
                "Please input parameter/keyword to perform ranking",
                "Ranking",
                JOptionPane.QUESTION_MESSAGE);
        rankList.rankingSystemForCourse(inputParameter);
        ImageIcon melonGIF = new ImageIcon("melon.gif");
        JOptionPane.showMessageDialog(null, "Ranking Completed!", "Ranking", 0, melonGIF);
    }

    // MODIFIES: this
    // EFFECTS: adds a new source based on user entered information (identifier, title and text body of source),
    //          and initializes rank score of newly imported source to value of 0.
    public void addSource() {
        String ident = JOptionPane.showInputDialog(null,
                "Please input Identifier",
                "Ranking Identifier",
                JOptionPane.QUESTION_MESSAGE);
        String title = JOptionPane.showInputDialog(null,
                "Please input Title",
                "Ranking Title",
                JOptionPane.QUESTION_MESSAGE);
        String body = JOptionPane.showInputDialog(null,
                "Please input Text Body",
                "Ranking Text Body",
                JOptionPane.QUESTION_MESSAGE);


        Source newSource = new Source(ident, title, body, 0);
        rankList.addSource(newSource);

        window.removeAll();
        addSourceRankerLogo();
        addButtonOptions();
        addSourcePanel();
    }

    // REQUIRES: user input must be an Integer greater or equal to 1
    // MODIFIES: this
    // EFFECTS: removes a source based on user input of position within list (index 1), input + 1 must be greater than 0
    private void removeSource() {
        String inputParameter = JOptionPane.showInputDialog(null,
                "Please enter number to remove source from list (starting from 1 indicating top of the list)",
                "Ranking",
                JOptionPane.QUESTION_MESSAGE);
        int index = Integer.parseInt(inputParameter) - 1;
        if (index + 1 > 0) {
            rankList.removeSource(index);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a number larger than 0");
        }
        window.removeAll();
        addSourceRankerLogo();
        addButtonOptions();
        addSourcePanel();
    }

    // EFFECTS: saves the ranked list of source (sourceranker) to file
    private void saveSources() {
        try {
            sourceRankerWriter.openWriter();
            sourceRankerWriter.write(rankList);
            sourceRankerWriter.quitWriter();
            System.out.println("Saved sources to " + JSON_SAVE + "!");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot save sources to " + JSON_SAVE);
        }
        ImageIcon melonGIF = new ImageIcon("melon.gif");
        JOptionPane.showMessageDialog(null, "Sources Saved!", "Save Sources", 0, melonGIF);
    }

    // MODIFIES: this
    // EFFECTS: loads previously saved sourceranker from file
    private void loadSources() {
        try {
            rankList = sourceRankerReader.read();
            System.out.println("Loaded saved sources from " + JSON_SAVE);
            window.removeAll();
            addSourceRankerLogo();
            addButtonOptions();
            addSourcePanel();
        } catch (IOException ioe) {
            System.out.println("Cant load sources from" + JSON_SAVE);
        }
        ImageIcon melonGIF = new ImageIcon("melon.gif");
        JOptionPane.showMessageDialog(null, "Sources Loaded!", "Load Sources", 0, melonGIF);
    }


    public String getLargestScore(Source bestSource) {
        int max = 0;
        for (Source s : rankList.getSourceList()) {
            if (s.getRank() > max) {
                max = s.getRank();
                s = bestSource;
            }
        }
        return bestSource.getTitle();
    }

    // Initializes the Source Ranker Application UI
    public static void main(String[] args) {
        new RankingUI();
    }
}
