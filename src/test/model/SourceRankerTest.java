package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SourceRankerTest {
    private SourceRanker sourceRankerTest;
    private Source myc;
    private Source aml;
    private Source photosynth;
    private Source flowering;
    private Source ras;

    @BeforeEach
    public void setUp() {
        sourceRankerTest = new SourceRanker();
        myc = new Source("myc",
                "MYC-induced human acute myeloid leukemia requires a continuing IL-3/GM-CSF costimulus",
                "Hematopoietic clones with leukemogenic mutations arise in healthy people as they age, but " +
                        "progression to acute myeloid leukemia (AML) is rare. Recent evidence suggests that the " +
                        "microenvironment may play an important role in modulating human AML population dynamics.",
                0);
        aml = new Source("aml",
                "Novel Gene Signature Reveals Prognostic Model in Acute Myeloid Leukemia",
                "Acute myeloid leukemia (AML) is a clonal malignant disease with poor prognosis and a low overall" +
                        " survival rate. Although many studies on the treatment and detection of AML have been " +
                        "conducted, the molecular mechanism of AML development and progression has not been fully " +
                        "elucidated. The present study was designed to pursuit the molecular mechanism of AML using a " +
                        "comprehensive bioinformatics analysis, and build an applicable model to predict the survival" +
                        " probability of AML patients in clinical use.",
                0);
        photosynth = new Source("photosynth",
                "Photosynthetic Research in Plant Science",
                "Photosynthesis is a highly regulated, multistep process. It encompasses the harvest of solar" +
                        " energy, transfer of excitation energy, energy conversion, electron transfer from water to " +
                        "NADP+, ATP generation and a series of enzymatic reactions that assimilate carbon dioxide " +
                        "and synthesize carbohydrate.",
                0);
        flowering = new Source("flowering",
                "Growth and photosynthesis of Japanese flowering cherry under simulated microgravity conditions",
                "The photosynthetic rate, the leaf characteristics related to photosynthesis, such as the " +
                        "chlorophyll content, chlorophyll a/b ratio and density of the stomata, the leaf area and the " +
                        "dry weight in seedlings of Japanese flowering cherry grown under normal gravity and simulated" +
                        " microgravity conditions were examined. No significant differences were found in the" +
                        " photosynthetic rates between the two conditions.",
                0);
        ras = new Source("ras",
                "RAS and MYC: Co-conspirators in Cancer",
                "Aberrant activation of RAS influences a variety of cellular pathways, the majority of which have" +
                        " been highlighted in RAS pathway v2.0. Despite this knowledge, efforts to attenuate RAS " +
                        "activity through the therapeutic inhibition of downstream signaling molecules have been " +
                        "relatively unsuccessful, in large part due to the ability of RAS signaling pathways to " +
                        "“rewire”, increasing therapeutic resistance1,2. Given our current inability to directly " +
                        "inhibit RAS, identification of an essential downstream target in RAS-driven cancers would be " +
                        "highly valuable. Here, we discuss RAS-mediated posttranslational modifications (PTMs) of " +
                        "c-MYC (MYC) that increase MYC activity and position MYC as a critical effector of " +
                        "RAS-driven cancer.",
                0);
    }

    @Test
    public void addSourceTestNoDup() {
        assertEquals(sourceRankerTest.sourceListSize(), 0);
        assertTrue(sourceRankerTest.addSource(myc));
        assertEquals(sourceRankerTest.sourceListSize(), 1);
        assertTrue(sourceRankerTest.addSource(aml));
        assertEquals(sourceRankerTest.sourceListSize(), 2);

    }

    @Test
    public void addSourceTestWithDup() {
        assertEquals(sourceRankerTest.sourceListSize(), 0);
        assertTrue(sourceRankerTest.addSource(myc));
        assertEquals(sourceRankerTest.sourceListSize(), 1);
        assertFalse(sourceRankerTest.addSource(myc));
        assertEquals(sourceRankerTest.sourceListSize(), 1);

    }

    @Test
    public void rankingSystemForCourseTest() {
        assertEquals(sourceRankerTest.sourceListSize(), 0);
        assertTrue(sourceRankerTest.addSource(myc));
        assertEquals(sourceRankerTest.sourceListSize(), 1);
        assertTrue(sourceRankerTest.addSource(aml));
        assertEquals(sourceRankerTest.sourceListSize(), 2);
        assertTrue(sourceRankerTest.addSource(photosynth));
        assertEquals(sourceRankerTest.sourceListSize(), 3);
        sourceRankerTest.rankingSystemForCourse("AML");
        assertEquals(sourceRankerTest.getIndividualSourceRank("myc"), 1);
        assertEquals(sourceRankerTest.getIndividualSourceRank("aml"), 1);
        sourceRankerTest.rankingSystemForCourse("AMLASLKDJ");
        assertEquals(sourceRankerTest.getIndividualSourceRank("myc"), 1);
        sourceRankerTest.rankingSystemForCourse("leukemia");
        assertEquals(sourceRankerTest.getIndividualSourceRank("myc"), 2);
        assertEquals(sourceRankerTest.getIndividualSourceRank("ras"), -999);
        sourceRankerTest.rankingSystemForCourse("human");
        assertEquals(sourceRankerTest.getIndividualSourceRank("myc"), 3);
    }
}