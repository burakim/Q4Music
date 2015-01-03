Parts Implemented by Halim Burak Yesilyurt
================================


Designing Pages
***************

Quiz Category Select Page - QuestionCategories.java
###################################################

This pages comes before starting quiz and it represents categories with fetching category names from SongTypes table. It has 2 **WebMarkupContainer** which are warn message that shows to provide error message to user such as "There is no question at his category", categories that consists of category panel which is CategoryPanel.java This categories WebMarkupContainer consists of one image and one link which represent category selection box. With using dynamic WebMarkupContainer I made dynamic content that can be added or deleted by adding or deleting category names from administration page.


Quiz Page - QuestionsView.java
##############################

This page is main part of question model that consists of accordion html view that stores question hint and right tabbed view that stores questions and also  ajax link choices buttons. Ajax choice link button communicates server without any refreshing page and it sends changes user choice to jetty web server. Also question views are dynamic and its viewing size can be adjusted by changing return ArrayList of fetchlimitedquestions database function beacuse it adjust number of question to show according to size of  ArrayList which is returned by fetchlimitedquestions database function. Also this page also consists of 2 wicket panel which are QuestionAsk and QuestionTab table which represents respectively choices of questions and questions texts of questions. It duplicates panel according to my data array list size by using RepatingView which is wicket native object that repeats panels. Moreover this page has simple wicket button that named as 'sendbttn' that is used  send users choices to server. After sending choices at onclick event of sendbttn, it calls CalculateScore function which is at this java file. CalculateScore takes questions and user choices which is modified by user by using ajax choice button and it calculates user choice and creates wrong answer question links that also contains true answer of wrong questions. After calculating question result it sends QuestionResult.java page to show up.

Quiz Result Page - QuizResultPage.java
######################################
This page consists of multiple labels and "Challenge Your Friends" accordion view that is used to send challenge request to challenge completed quiz with same question. Multiple labels represent users total score, number of correct and wrong question numbers and correct answers of wrong questions. Challenge Your Friends is an accordian view that can be active clicking title of accordian view and after activating it shows 2 text input fields which are opponent name and email address to save challenge request to table and send email to opponent. After sending successfuly chalenge request it redirects main page with showing succesful message that represent sending operation successfuly finished.
In this page challenge request is sent to opponent by using Java Mail Send Library which named 'javax.mail' library at MailSend.java file. Also it generates uniques key to use challenge requests, this key is constructed by generating random number and its length 20 numbers.

MailSend.java
#############

This page sends email to specified recevier mail addresses. It use SMTP Server of Gmail by using q4music gmail account. It has sendCode function to send unique challenge code to user.

.. code-block:: java
	
	    public Boolean sendCode(String username, String code, String email,
            String to) {
        try {

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Q4Music Challenge Request From " + to);
            message.setText("Hey My Friend, "
                    + to
                    + "\n\n Hey I am "
                    + username
                    + " and I have just completed Music quiz that is provided by Q4Music.
                     I think, I am better than you about music knowledge and I challenge you \n"
                    + "I send this link, just click and challenge \n"
                    + "Challenge Link \n"
                    + " http://q4music.mybluemix.net?code=" + code);

            Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {
            System.out.println(e.toString());
            return false;
        }
    }

Choice Management Page - AdminChoicePage.java
#############################################

This page consists of dynamic table that has custom number of viewing, dynamic search box, edit button to edit choices. After clicking edit choice button javascript powered choice edit modal occurs at middle of page. This modal consits of text input to receive user changes to send database for achiving edit operation of choices. This page dont have choice delete or add operation because, choice and hint add delete operations invokes automatically by adding question. Therefore question add modal constructed with question, hint, choices information.

Hint Management Page - AdminHintsPage.java
##########################################

This page consists of dynamic table that has custom number of viewing, dynamic search box, edit button to edit hints. After clicking edit hint button javascript powered hint edit modal occurs at middle of page. This modal consists of text input to receive user changes to send database for archiving edit operation of hints. This page don't have choices delete or add operations because, choices and hint add delete operations invokes automatically by adding question. Therefore question add modal constructed with question, hint, choices information.

Question Management Page - AdminQuestionPage.java
#################################################

This page consists of dynamic table that has custom number of viewing, dynamic search box, edit button to edit question. After clicking edit question button javascript powered question edit modal occurs at middle of page. This modal consists of text input which is related Questions table to receive user changes to send database for achiving edit operation of questions. Also main difference between hint and choice management page this page has delete question,choice,hint operations capability and adding question,hint,choice capability. This support massively addition and deletion operation. After clicking delete button javascript powered delete operation confirmation alert view occurs and it deletes question, hint and choices of question. Also It has add question button and it shows up question add modal that is javascript powered modal. This modal consists html input elements that is fields of Questions, Hints, Choices tables. Therefore there is only question, choice, hint add modal and it adds all together at same modal.



JavaScript Pages
***********************************************************************

These functions locate under WebContent folder and names MyCodes.js file. It is used to communicate javascript and wicket to use javascript modal view at question,hint,choice edit and add stages.

AdminChoiceManagementTablePager()
#################################
This function is used at choice edit modal and it fills related fields of choice from getting wicket side by getting values form HTML tag attributes.

ChoiceEvents()
##############

This functions includes implements click event of choice save changes button at choice edit modal. It saves changes and transmits changed form values to wicket side from javascript sides.

AdminQuestionManagementTablePager()
###################################
This function is used at question edit modal and it fills related fields of question from getting wicket side by getting values form HTML tag attributes. Also, It includes add question,hint, choices button onclik event implementation. Moreover, edit question button implementation is included at this function.

AdminAddQuestion()
###################
This function includes implementation of add new question button to show add question modal.

AdminHintOperationEditModal()
############################
This function is used at hint edit modal and it fills related fields of hint from getting wicket side by getting values form HTML tag attributes.
Also it contains hint edit save changes button implementation.



Database Functions
*************************************************************************

GetUserIdForChallenge
#####################

.. code-block:: java

     public int[] GetUserIdForChallenge(String code) 


This function is for getting user id, challenge id, challenge category id,and also challenge owner score from database to make game invitation and get game invitation. It basically takes String data type argument which names code and it return integer array tha includes respectively challenge id, challenge owner user id, challenge category id, also owner  user quiz score.


*Sql Query For GetUserIdForChallenge functions*

.. code-block:: sql

	SELECT DISTINCT * FROM Challenge Where Code =?

========================================================================

GetQuestionIdsByChallengeId
###########################

.. code-block:: java

     public ArrayList<ChallengeQuestionModel> GetQuestionIdsByChallengeId(int challengeId)

This function is for getting question by providing challenge id. It is called at response user want to fetch challenge question. It has simply ArrayList which stores ChallengeQuestionModel and takes only one integer argument which names challengeId. Moreover, It creates new ChallengeQuestionModel instance and stores to send user.

*Sql Query For GetQuestionIdsByChallengeId functions*

.. code-block:: sql

	SELECT DISTINCT * FROM ChallengeQuestions Where ChallengeID =?

========================================================================

GetChallenge
############

.. code-block:: java

	 public ChallengeModel GetChallenge(String code)

This function was written for getting user from code that is sent to rival's mail account. If user clicks rival URL and enters credential correctely, this function is automatically triggered. It fetch challenge information by filling the ChallengeModel Class

It contains more database functions; therefore there is not any SQL query at thos funtion directly.


=============================

GetCategoryId
#############

.. code-block:: java

	public int getCategoryId(String categoryName)

This function takes String argument that represents category name and it returns integer value that represent category id. It basically, search desired value by category name and it fetch only id integer value from Songtypes database table.


.. code-block:: sql

	Select ID FROM SongTypes Where Name=?

=============================


AddChallengeRequest
###################

.. code-block:: java

	public Boolean AddChallengeRequest(QuizStatistic quizdata, String code)

This function triggered after pressing send challenge request button at quiz result page. It simply adds challenger user id, challenge code, category id, challenger quiz result score. It handle *SQLException* and prints error description to console.

*Sql Query For AddChallengeRequest functions*

.. code-block:: sql

	INSERT INTO Challenge (FromID,Code,CategoryID,OwnerScore) 
	VALUES (?,?,?,?)

===============================

GetChallengeId
##############

.. code-block:: java

     public int GetChallengeId(String code)

This function simply gets challenge id by taking code argument. 

*Sql Query For GetChallengeId function*

.. code-block:: sql

	SELECT ID FROM Challenge WHERE Code = ?

========================

AddChallengeQuestions
#####################

.. code-block:: java

	 public Boolean AddChallengeQuestions(ArrayList<Question> wrongquestion,
	 ArrayList<Question> truequestion, int ChallengeId)

This function takes wrong and true question data that is stored at Java ArrayList. It firstly checks wrongquestion ArrayList to save database. After handling wrong questions, true question starts storage process to database. It stores challenge id, question id, integer information about question true or not. It return boolean type data to represent insert operated succesfull or not for taking precatious at other classes.

*Sql Query For AddChallengeQuestions function*

.. code-block:: sql

	INSERT INTO ChallengeQuestions (ChallengeID,QuestionID,isTrue) VALUES (?,?,?)

======================

AddQuestionHintChoices
######################

.. code-block:: java

	public Boolean AddQuestionHintChoices(Question question, int categoryId)

This function is invoked at Question Add Admin Page and it basically add question and questions other sub fields that are choice, hint. It checks whether any Forgein Key Constraint while adding question, hint,choice. It also invokes AddQuestion, AddHint, AddChoices database functions. It returns boolean value that indicates all question, hint, choice adding process succesfully finish or not.

It invokes add question, hint, choice functions therefore it does not have SQL query.

===================

AddChoices
##########

.. code-block:: java

	public Boolean AddChoices(String choiceText, int ChoiceOrder, int questionid)

This function is invoked by Add QuestionHintChoices function and It simply adds Choices to database with taking choice text, choice order that represent order of choice, questionid for forgein key to bind Questions table.

*Sql Query For AddChoices function*

.. code-block:: sql

	INSERT INTO Choices(Choice,ChoiceOrder,QuestionId) VALUES (?,?,?)

=======================

AddQuestion
###########

.. code-block:: java

	public int AddQuestion(Question question, int categoryId) 

This function firstly check whether same question is in Question table or not. If there is no same question in Question table, It simply add Question model class's field to Question table. Besides, it invokes GetQuestionID database function to returnr integer question id.

*Sql Query For AddQuestion function*

For checking to avoid same question

.. code-block:: sql

	SELECT * FROM Questions WHERE Name = ?

For adding question to Question Table

.. code-block:: sql

	INSERT INTO Questions(Name,CorrectAnswer,PositiveScore,NegativeScore,CategoryID) 
	VALUES (?,?,?,?,?)

=======================

GetQuestionID
#############

.. code-block:: java

	public int GetQuestionID(String questionText)

This function take question text and return question id.

*Sql Query For GetQuestionID function*


.. code-block:: sql

	Select ID FROM Questions Where Name=?

=============================

AddHint
#######

.. code-block:: java

	public Boolean AddHint(String hint, int questionId)

This function take hint text and question id for forgein key binding and it basicallt adds hint to question.  It is invoked by Add QuestionHintChoices function

*Sql Query For AddHint function*

For checking to avoid same hint

.. code-block:: sql

	SELECT * FROM Hints WHERE Name = ?

*For adding hint to Hints Table*

.. code-block:: sql

	INSERT INTO Hints(Name,QuestionID) VALUES (?,?)

==================

DeleteAllEntries
################

.. code-block:: java

	public Boolean DeleteAllEntries(int questionId)

This function take question id argument and it invokes 3 database functions respectively DeleteChoicesByQeustionID, DeleteByQuestionID, DeleteQueston function to avoid forgein key constraint fails. After invoking functions it return true or false to provide information about database operation.

It invokes delete question, hint, choice functions therefore it does not have SQL query.

===================

DeleteChoicesByQuestionId
#########################

.. code-block:: java

	public Boolean DeleteChoicesByQuestionId(int questionId)

This function take question id argument and it deletes choices according to question id. It use it's forgein key that binds from Choices table to Question table. After it deletes, it return boolean value that represent success or failure of delete operation

*Sql Query For DeleteChoicesByQuestionId function*

.. code-block:: sql

	DELETE  FROM Choices WHERE QuestionId = ?

===================

DeleteHintByQuestionId
######################

.. code-block:: java

	public Boolean DeleteHintByQuestionId(int questionId)

This function take question id argument and it deletes hint according to question id. It use it's forgein key that binds from Hints table to Question table. After it deletes, it return boolean value that represent success or failure of delete operation

*Sql Query For DeleteChoicesByQuestionId function*

.. code-block:: sql

	DELETE  FROM Hints WHERE QuestionID = ?

===================

DeleteQuestion
##############

.. code-block:: java

	public Boolean DeleteQuestion(int questionid) 

This function take question id argument and it deletes question according to question id. After it deletes, it return boolean value that represent success or failure of delete operation

*Sql Query For DeleteHQuestion function*

.. code-block:: sql

	DELETE  FROM Questions WHERE ID = ?

===================

FetchMusicAllSubjectName
#########################

.. code-block:: java

	public ArrayList<String> FetchMusicAllSubjectName()

This function take no arguments and it fetchs category names from SongTypes table. It stores result in ArrayList that stores String type and it returns ArrayList.

*Sql Query For FetchMusicAllSubjectName function*

.. code-block:: sql

	SELECT DISTINCT Name from SongTypes

===================

GetCategoryNameByQuestionId
###########################

.. code-block:: java

	public String GetCategoryNameByQuestionId(int questionid)

This function take one integer argument which names questionid and it gets category name from question id. It simply returns String value that is category name.

*Sql Query For GetCategoryNameByQuestionId function*

.. code-block:: sql

	SELECT Name FROM SongTypes Where ID = (Select CategoryID FROM Questions WHERE Questions.ID = ?)

===================

FetchAllSongTypeNames
#####################

.. code-block:: java

	public List<String> FetchAllSongTypeNames()

This function don't take any argument and it simply, fetch all fields form Song Types and it adds Category Name string to list which is created at this function.

*Sql Query For FetchAllSongTypeNames*

.. code-block:: sql

	SELECT * FROM SongTypes

====================

FetchLimitedQuestions
#####################

.. code-block:: java

	public ArrayList<Question> FetchLimitedQuestions(int[] questionids,String categoryName)

This function takes 2 argument these are integer array that contains id of questions and String that represents category name. It does not invoke sql queries. It invokes 2 Sql function these are FetchSingleQuestionandHintById and FetchChoicesBeyQuestionId.

*It does not execute sql functions.*

====================

FetchSingleQuestionandHintById
##############################

.. code-block:: java

	public Question FetchSingleQuestionandHintById(int questionid)

This function takes question id argument that is integer type argument and it fetchs question and questions's hint. It fills question model object with fetching field that is taken from Questions and Hints table.

*Sql Query For FetchSingleQuestionandHintById function*

.. code-block:: sql

	Select Questions.ID as ID, Questions.Name as Question, CorrectAnswer, PositiveScore,
	NegativeScore,Hint.Name AS Hint From Questions, 
	(SELECT * FROM Hints WHERE QuestionID = ?) AS Hint
	WHERE Questions.ID = ?
	
====================

FetchChoicesByQuestionId
########################

.. code-block:: java

	public String[] FetchChoicesByQuestionId(int questionId)

This function takes question id argument and it return String array that contains choice strings. It adds choice strings according to choice order as index of string array.

*Sql Query For FetchChoicesByQuestionId function*

.. code-block:: sql

	SELECT Choice, ChoiceOrder FROM Choices WHERE QuestionId =?

========================

FetchAllHints
#############

.. code-block:: java

	public ArrayList<Hint> FetchAllHints()

This function take no arguments and It creates ArrayList which stores Hint data model. It simply fetch all values of hint from Hints table and  question text from  Questions table. Also this Hint data mode includes question id which is also forgein key of Hints table to bind Questions table. It returns ArrayList which consists of Hint data model class.

*Sql Query For FetchAllHints function*

.. code-block:: sql

	SELECT hint.ID,hint.Name, hint.QuestionID, questions.Name as QName FROM Hints as hint,
	(SELECT Questions.Name, Questions.ID FROM Questions  AS questions 
	WHERE questions.ID = hint.QuestionID

===================

UpdateChoicesWithChoiceIdandQuestionID
######################################

.. code-block:: java

	public void UpdateChoicesWithChoiceIdandQuestionID(Choices choiceset)

This function take one argument that is Choice data model class and it updates all choices of single questions according to changes. However it has 2 sql queries and first of them simply updates choices, second of them updates CorrectAnswer field of Questions table that represent which of choice correct. CorrectAnswer field should start with 0 index. 

*Sql Query For UpdateChoicesWithChoiceIdandQuestionID function*

For updating choices from Choices table

.. code-block:: sql

	UPDATE Choices SET Choice=? WHERE ChoiceId=?

For updating CorrectAnswer field form Question table.

.. code-block:: sql

	UPDATE Questions SET CorrectAnswer=? WHERE ID = (Select Distinct 
	QuestionID FROM Choices WHERE ChoiceId=?)

===================

FetchAllQuestionsandChoices
###########################

.. code-block:: java

	ArrayList<Question> FetchAllQuestionsandChoices()

This function take no argument and It fetchs Questions table field but also It invokes FetchChoicesByQuestion(int) functions to fetch choices and it simply fills Question data model class and it adds ArrayList. After it finish job, it stores Question to ArrayList and it returns. 

*Sql Query For FetchAllQuestionsandChoices function*


.. code-block:: sql

	SELECT * FROM Questions

===================

FetchCategoryIdandName
#########################

.. code-block:: java

	public Map<String, Integer> FetchCategoryIdandName()

This function take no argument and It fetchs Name and ID fields of SongTypes tables. Major difference from other database operations it consists of Map data structure and it returns Map data structure which key is String that stores category names and other field is integer that stores category id. 

*Sql Query For FetchCategoryIdandName function*


.. code-block:: sql

	SELECT * FROM SongTypes

============================

FetchAllChoices
###############

.. code-block:: java
	
	public List<Choices> FetchAllChoices()

This function don't take argument and It fetch ID, CorrectAnswer, Name fields from Questions table and it copies to suitable attributes of Choices model object. After fetching question information, it fetches choice informations from Choices table and it fills to Choice model object.

*Sql Query For FetchAllChoices function*

For fetching information from Questions table

.. code-block:: sql

	Select ID,CorrectAnswer,Name FROM Questions

For fetching choice information from Choices table

.. code-block:: sql

	Select Distinct ChoiceID, Choice, ChoiceOrder FROM Choices WHERE 
	QuestionID = ? ORDER BY ChoiceOrder ASC
 
===========================

UpdateHintsWithHintId
######################

.. code-block:: java
	public Boolean UpdateHintsWithHintId(Hint hint)

This function take Hint data model argument and it simply updates hint data structure and it returns boolean value that represents state of update process.

*Sql Query For UpdateHintsWithHintId function*

.. code-block:: sql

	UPDATE Hints SET Name=?, QuestionID =? WHERE Id=?

===========================

UpdateHintsWithHintId
######################

.. code-block:: java
	public Boolean UpdateHintsWithHintId(Hint hint)

This function take Hint data model argument and it simply updates hint data structure and it returns boolean value that represents state of update process.

*Sql Query For UpdateHintsWithHintId function*

.. code-block:: sql

	UPDATE Hints SET Name=?, QuestionID =? WHERE Id=?

===========================

UpdateQuestionWithQuestionModal
################################

.. code-block:: java
	   public Boolean UpdateQuestionWithQuestionModal(Question question,int categoryId)

This function take Question data model class and integer that represent category id arguments. It simply updates fields of specific question rows according to question id. It return boolean value to indicate state of update process.

.. code-block:: sql
	
	UPDATE Questions SET Name=?,  CorrectAnswer=?, PositiveScore=?, 
	NegativeScore=?, CategoryID=?  WHERE ID=?







	





