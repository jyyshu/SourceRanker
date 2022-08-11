# CPSC 210 Project

## A literature ranking tool for research

### What will the application *do*?
- This application will be used a tool to sort through various forms of scientific literature and information sources 
 available online. Source literature that this tool is intended for is primarily peer reviewed journal articles, reviews,
books, newspaper publications, and theses' to name a few. 

- The software itself will use various parameters (that can be included or not based on user 
preference) in order to filter and rank these sources. 
- *Examples of such parameters include:*
    - range of time from which source was published
    - authors of interest 
    - specific journals to be included
    - specific methods, genes etc used in study or review

- **Ultimately, this tool will comb through a pool of source material (submitted by the user), and determine how overrepresented the parameters 
included are (as defined by the user) and produce a ranking to facilitate one's literature search.**

### *Who* will use it?
- This application is intended for researchers, students, and or casual users to assist their pursuit for the most
important and/or relevant sources to use that are reflective of their research interests and/or goals.

### *Why* is this project of interest to you?
- Based on prior experience from school work as well as my experience as a bioinformatics 
research student in acute myeloid leukemia research, literature searches can often be a very time-consuming 
and arduous process. Often times, I had hoped for a tool or application that would aid me in this venture, as existing
methods online would either be too general or too specific for my needs. Therefore, I hope to create a literature ranking
search application that would offer enough personalization and custom settings that would not only cater towards the needs of the many while 
also being precise enough for the needs of specific individuals or groups. In general, I aim to
expedite and simplify the literature searching process and hopefully help others who may encounter 
similar difficulties that I have addressed already

## User Stories
- As a user, I want to input multiple parameters/keywords to assess the rank score for each source (one after another)
- As a user, I would like to view the list of sources already imported in the tool showing each source title and rank score (with a larger rank score indicating higher importance)
- As a user, I want to add multiple sources to be ranked within the application
- As a user, I want to remove a source from the list of sources based on its position within the list

- As a user, I want to have the ability to save the list of sources that I have imported at any point
- As a user, I want to be able to choose to re-load my source list that I previously saved from file

## Instructions for Grader
- You can generate the first required event by clicking "Add Source" or pressing Alt+A on the keyboard. This will create several consecutive pop-up windows to allow for one to add one or multiple sources to the ranking application. To add this will also retrieve the title of the best source within the source list (highest rankscore, or the first in the list if the scores are equal between 2 or more sources)
- You can generate the second required event by clicking "Delete Source" or pressing Alt+D on the keyboard. This will create a pop-up window that will allow one to remove a source(s) of their choosing based on the 1-based index position of the list (1 is the top of the list)
- You can locate the visual component by clicking "Rank List", "Save Sources" or "Load Sources" (or entering keyboard inputs: Alt + (R or S or L respectively)). After execution of each, a window will appear displaying a message and a gif of the melon mascot for the application.
- You can save the state of my application by clicking the "Save Sources" button or pressing Alt+S on the keyboard
- You can reload the state of my application by clicking the "Load Sources" button or pressing Alt+L on the keyboard

## Phase 4: Task 2
-Sample of events being logged *(Reminder: first 5 events logged are due to import/initialization of the 5 sources to be included when program starts )* 

Thu Aug 11 11:32:54 PDT 2022<br />
Added a new source to be ranked: myc<br />
Thu Aug 11 11:32:54 PDT 2022<br />
Added a new source to be ranked: aml<br />
Thu Aug 11 11:32:54 PDT 2022<br />
Added a new source to be ranked: photosynth<br />
Thu Aug 11 11:32:54 PDT 2022<br />
Added a new source to be ranked: flowering<br />
Thu Aug 11 11:32:54 PDT 2022<br />
Added a new source to be ranked: ras<br />
Thu Aug 11 11:33:12 PDT 2022<br />
Added a new source to be ranked: Cherry Blossom<br />
Thu Aug 11 11:33:16 PDT 2022<br />
Added a new source to be ranked: Melon<br />
Thu Aug 11 11:33:22 PDT 2022<br />
Removed the source: ras from the list of sources<br />
Thu Aug 11 11:33:27 PDT 2022<br />
Added a new source to be ranked: Jeremy<br />
Thu Aug 11 11:33:36 PDT 2022<br />
Removed the source: Jeremy from the list of sources<br />
Thu Aug 11 11:33:45 PDT 2022<br />
Added a new source to be ranked: Honeydew<br />

Process finished with exit code 0
## Phase 4: Task 3
If more time was provided for me to work on this project: 
- I would likely alter the way I would add and remove sources using the addSource and removeSource methods in my SourceRanker class. Given they are essentially opposing methods I believe I would refactor both classes such that they both incorporate/operate upon the Source's identifier rather than independently looking at the Source title for addSource and the index of the Source list in removeSource. 
- I would also like to change the source list in SourceRanker to be a Collection rather than an ArrayList, as it would allow me to use a broader variety of implementations.
- I would also include more exception handling in my methods within the RankingUI class in order to cover more cases where my application my run into unintended issues. 
- I would also try to refactor by adding another class that handles the button panel as the RankingGUI classes is handling nearly the functionality. To add, I would remove duplication among the buttons by extracting similarities between each of the button methods (by creating a new method) and then calling the new method within each of the different button function methods. 

