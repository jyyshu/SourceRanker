package ui;

import model.Source;
import model.SourceRanker;

import java.util.*;


// some features based on Teller app; link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// A representation of a literature source ranking application
public class RankingApp {
    private Scanner input;
    private SourceRanker rankList;

    // EFFECTS: initializes the ranking application
    public RankingApp() {
        startRanking();
    }

    // MODIFIES: this
    // EFFECTS: starts ranking application and handles any inputs from user
    private void startRanking() {
        System.out.println("Please input parameter to perform ranking or 'view' list?");
        boolean keepGoing = true;
        String command = null;

        importSources();

        while (keepGoing) {
            showOptions();
            command = input.next();

            if (command.equals("Quit")) {
                keepGoing = false;
            } else {
                completeCommand(command);
            }
        }
        System.out.println("\nThank you!");
    }

    // MODIFIES: this
    // EFFECTS: executes commands provided by user
    private void completeCommand(String command) {
        if (command.equals("View")) {
            listRankings();
        } else if (command.equals("Import")) {
            createSource();
        } else if (command.equals("Remove")) {
            removeSource();
        } else {
            completeRanking();
        }
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
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: shows all possible options for input commands to user
    private void showOptions() {
        System.out.println("\tRank -> enter any parameter/keyword");
        System.out.println("\tImport -> add new source");
        System.out.println("\tView -> view list");
        System.out.println("\tRemove -> remove a source from list");
        System.out.println("\tQuit -> exit program");
    }

    // EFFECTS: allows viewers to view list of sources with rank score and title
    private void listRankings() {
        for (Source s : rankList.getSourceList()) {
            System.out.println("RANK: " + s.getRank() + " " + s.getTitle());
        }
    }

    // MODIFIES: this
    // EFFECTS: executes the ranking of sources based on the parameter/keyword the
    //          user had specified and displays the ranked list
    private void completeRanking() {
        System.out.println("Enter keyword/parameter to a perform ranking");
        String keyword = input.next();
        rankList.rankingSystemForCourse(keyword);
        listRankings();
    }

    // MODIFIES: this
    // EFFECTS: imports a new source based on user entered information (identifier, title and text body of source),
    //          and initializes rank score of newly imported source to value of 0.
    private void createSource() {
        System.out.println("Please enter an identifier for the source");
        String ident = input.next();
        System.out.println("Please enter the title of the source");
        String title = input.next();
        System.out.println("Please enter the text body of the source");
        String body = input.next();

        Source newSource = new Source(ident, title, body, 0);

        rankList.addSource(newSource);
    }

    // MODIFIES: this
    // EFFECTS: removes a source based on user input of position within list (index 1), input + 1 must be greater than 0
    private void removeSource() {
        System.out.println("Please enter number to remove source from list");
        int index = input.nextInt() - 1;
        if (index + 1 > 0) {
            rankList.removeSource(index);
            listRankings();
        } else {
            System.out.println("Please enter a number larger than 0");
        }
    }
}
