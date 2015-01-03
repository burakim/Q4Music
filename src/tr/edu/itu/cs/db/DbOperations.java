package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import tr.edu.itu.cs.db.models.QuizStatistic;


public class DbOperations implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedstatement;
    private ResultSet resultset;

    public DbOperations() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Constants.databaseconn
                    + "/" + Constants.DbName + "?" + "user=" + Constants.DbUser
                    + "&" + "password=" + Constants.DbPassword);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public int[] GetUserIdForChallenge(String code) {
        int challengeId = -1;
        int ownerId = -1;
        int categoryId = -1;
        int ownerScore = -1;
        int[] returnedval = new int[4];
        try {
            String query = "SELECT DISTINCT * FROM Challenge Where Code =?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, code);
            resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
                challengeId = resultset.getInt("ID");
                ownerId = resultset.getInt("FromID");
                categoryId = resultset.getInt("CategoryID");
                ownerScore = resultset.getInt("OwnerScore");
            }
            if (challengeId == -1 || ownerId == -1)
                System.out.println("GetUserIdForChallenge return empty !!");
            returnedval[0] = challengeId;
            returnedval[1] = ownerId;
            returnedval[2] = categoryId;
            returnedval[3] = ownerScore;

        } catch (SQLException ex) {
            System.out.println("GetUserIdForChallenge return empty !! -> "
                    + ex.toString());
            returnedval[0] = -1;
            returnedval[1] = -1;
            returnedval[2] = -1;
            returnedval[3] = -1;

            return returnedval;
        }
        return returnedval;

    }

    public ArrayList<ChallengeQuestionModel> GetQuestionIdsByChallengeId(
            int challengeId) {
        // First value questionId second value istrue or not information.
        ArrayList<ChallengeQuestionModel> data = new ArrayList<ChallengeQuestionModel>();

        int questionId = -1;
        int isTrue = -1;

        try {
            System.out.println("Challenge ID for fetch => " + challengeId);
            String query = "SELECT DISTINCT * FROM ChallengeQuestions Where ChallengeID =?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, challengeId);
            resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
                questionId = resultset.getInt("QuestionID");
                isTrue = resultset.getInt("isTrue");
                if (questionId == -1 || isTrue == -1)
                    System.out
                            .println("GetQuestionIdsByChallengeId return empty !!");
                else {
                    ChallengeQuestionModel qmodel = new ChallengeQuestionModel(
                            questionId, isTrue);
                    data.add(qmodel);
                }
            }

        } catch (SQLException ex) {
            System.out
                    .println("GetQuestionIdsByChallengeId return empty !! -> "
                            + ex.toString());

        }

        if (data.size() == 0) {
            System.out
                    .println("ArrayList<ChallengeQuestionModel>  return empty check immediately!! -> ");
        }
        return data;

    }

    public ChallengeModel GetChallenge(String code) {
        int data[] = GetUserIdForChallenge(code);

        ChallengeModel challengeModel = new ChallengeModel(data[1], data[3],
                data[0], data[2], GetQuestionIdsByChallengeId(data[0]));
        if (challengeModel == null) {
            System.out
                    .println("challengemodel null check GetChallenge function at DbOp.");
            return null;
        } else
            return challengeModel;

    }

    public Boolean AddQuestionHintChoices(Question question, int categoryId) {

        int qid = AddQuestion(question, categoryId);
        if (qid != -1) {

            if (AddHint(question.getHint(), qid)) {
                for (int i = 0; i < question.getChoices().length; i++) {
                    if (!(AddChoices(question.getChoices()[i], i, qid))) {
                        System.out.println("Choice Add Failed"
                                + question.getChoices()[i] + " - " + qid
                                + " - " + question.getCorrectanw());

                        return false;
                    }
                }

            } else {
                System.out.println("Hint Add Failed");
            }
        } else {
            System.out.println("Question Add Failed");
            return false;
        }
        return true;
    }

    public Boolean AddChoices(String choiceText, int ChoiceOrder, int questionid) {
        try {
            String query2 = "INSERT INTO Choices(Choice,ChoiceOrder,QuestionId) VALUES (?,?,?)";

            preparedstatement.clearParameters();
            preparedstatement = this.connection.prepareStatement(query2);

            preparedstatement.setString(1, choiceText);

            preparedstatement.setInt(2, ChoiceOrder);
            preparedstatement.setInt(3, questionid);

            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Adding Choice Failed => " + ex.toString());
            return false;
        }

        return true;
    }

    public int AddQuestion(Question question, int categoryId) {

        try {
            String query = "SELECT * FROM Questions WHERE Name = ?";
            if (preparedstatement != null) {
                preparedstatement.clearParameters();
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setString(1, question.getQuestiontext());
                ResultSet resultset = preparedstatement.executeQuery();
                int size = 0;
                if (resultset.last()) {
                    size = resultset.getRow();

                    size--; // Always result have one dummy element.
                    resultset.beforeFirst(); // not rs.first() because the
                                             // rs.next() below will move on,
                                             // missing the first element
                }
                if (size == 0) {

                    String query2 = "INSERT INTO Questions(Name,CorrectAnswer,PositiveScore,NegativeScore,CategoryID) VALUES (?,?,?,?,?)";
                    preparedstatement.clearParameters();
                    preparedstatement = this.connection
                            .prepareStatement(query2);
                    preparedstatement.setString(1, question.getQuestiontext());

                    preparedstatement.setInt(2, question.getCorrectanw());

                    preparedstatement.setInt(3, question.getScore()[0]);

                    preparedstatement.setInt(4, question.getScore()[1]);

                    preparedstatement.setInt(5, categoryId);

                    preparedstatement.executeUpdate();

                }
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.out
                    .print("Error occured while adding question to database. Error Message: "
                            + ex.toString());
            return -1;
        } catch (Exception ex) {
            System.out
                    .print("Error occured while adding question to database. Error Message: "
                            + ex.toString());
            return -1;
        }

        return GetQuestionID(question.getQuestiontext());
    }

    public int GetQuestionID(String questionText) {
        String query = "Select ID FROM Questions Where Name=? ";
        int returned = -1;
        try {
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, questionText);
            ResultSet resultset2 = preparedstatement.executeQuery();
            while (resultset2.next()) {
                returned = resultset2.getInt("ID");
            }

        } catch (SQLException ex) {
            System.out.println("getCategoryId hatali calismiyor -> "
                    + ex.toString());

        }
        if (returned == -1) {
            System.out.println("CategoryID bulunamadi....");
            return -1;
        } else
            return returned;
    }

    public Boolean AddHint(String hint, int questionId) {
        try {
            String query = "SELECT * FROM Hints WHERE Name = ?";
            if (preparedstatement != null) {
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setString(1, hint);
                ResultSet resultset = preparedstatement.executeQuery();
                if (!resultset.next()) {
                    query = "INSERT INTO Hints(Name,QuestionID) VALUES (?,?)";
                    preparedstatement.clearParameters();
                    preparedstatement = connection.prepareStatement(query);
                    preparedstatement.setString(1, hint);
                    preparedstatement.setInt(2, questionId);

                    preparedstatement.executeUpdate();
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out
                    .print("Error occured while deleting hint to database. Error Message: "
                            + ex.toString());
            return false;
        }
        return true;
    }

    public Boolean DeleteAllEntries(int questionId) {
        if (DeleteChoicesByQuestionId(questionId)) {

            if (DeleteHintByQuestionId(questionId)) {

                if (DeleteQuestion(questionId)) {

                } else {
                    System.out.println("Error occured while deleting question");
                }
            } else {
                System.out.println("Error occured while deleting hints");
            }
        } else {
            System.out.println("Error occured while deleting choices");
        }

        return true;
    }

    public Boolean DeleteChoicesByQuestionId(int questionId) {
        try {
            String query = "DELETE  FROM Choices WHERE QuestionId = ?";
            if (preparedstatement != null) {
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setInt(1, questionId);
                preparedstatement.executeUpdate();

            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out
                    .print("Error occured while deleting choice to database. Error Message: "
                            + ex.toString());
            return false;
        }
        return true;
    }

    public Boolean DeleteHintByQuestionId(int questionId) {
        try {
            String query = "DELETE  FROM Hints WHERE QuestionID = ?";
            if (preparedstatement != null) {
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setInt(1, questionId);
                preparedstatement.executeUpdate();

            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out
                    .print("Error occured while deleting hint to database. Error Message: "
                            + ex.toString());
            return false;
        }
        return true;
    }

    public Boolean DeleteQuestion(int questionid) {
        try {
            String query = "DELETE  FROM Questions WHERE ID = ?";
            if (preparedstatement != null) {
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setInt(1, questionid);
                preparedstatement.executeUpdate();

            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out
                    .print("Error occured while deleting question to database. Error Message: "
                            + ex.toString());
            return false;
        }
        return true;
    }

    public boolean DeleteChoicesById(int id) {
        try {
            String query = "DELETE FROM Choices WHERE ID= ?";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
            return true;

        } catch (Exception ex) {
            System.out
                    .print("Error occured while adding question to databse. Error Message "
                            + ex.toString());
            return false;
        }
    }

    public Boolean DeleteHint(int id) {

        try {

            String query = "DELETE  FROM Hints WHERE Id = ?";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);

            preparedstatement.executeUpdate();
            return true;
        }

        catch (Exception ex) {
            System.out
                    .print("Error occured while adding question to database. Error Message: "
                            + ex.toString());
            return false;
        }
    }

    public ArrayList<String> FetchMusicAllSubjectName() {
        ArrayList<String> returnvalue = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * from SongTypes");
            while (resultset.next()) {
                String songtype = resultset.getString("Name");
                returnvalue.add(songtype);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnvalue;
    }

    public String GetCategoryNameByQuestionId(int questionid) {
        String returnvalue = "";
        try {
            String query = "SELECT Name FROM SongTypes Where ID = (Select CategoryID FROM Questions WHERE Questions.ID = ?)";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, questionid);
            resultset = preparedstatement.executeQuery();
            resultset.next();
            returnvalue = resultset.getString("Name");
            if (returnvalue == "")
                System.out
                        .println("GetCategoryNameByQuestionId Name return empty !!");
        } catch (SQLException ex) {
        }

        return returnvalue;
    }

    public ArrayList<Hint> FetchAllHints() {
        ArrayList<Hint> returnvalue = new ArrayList<Hint>();
        try {
            statement = connection.createStatement();
            resultset = statement
                    .executeQuery("SELECT hint.ID,hint.Name, hint.QuestionID, questions.Name as QName FROM Hints as hint, (SELECT Questions.Name, Questions.ID FROM Questions ) AS questions WHERE questions.ID = hint.QuestionID");
            while (resultset.next()) {

                Hint hint = new Hint(resultset.getInt("ID"),
                        resultset.getString("Name"),
                        resultset.getInt("QuestionID"),
                        resultset.getString("QName"));

                returnvalue.add(hint);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnvalue;
    }

    public ArrayList<Hint> SearchHintsByHintValue(String hintarg) {
        ArrayList<Hint> returnvalue = new ArrayList<Hint>();
        try {
            String query = "SELECT * FROM Hints Where Name LIKE " + "%"
                    + hintarg + "%";
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                Hint hint = new Hint(resultset.getInt("Id"),
                        resultset.getString("Name"));
                returnvalue.add(hint);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnvalue;
    }

    public void UpdateChoicesWithChoiceIdandQuestionID(Choices choiceset) {

        for (int i = 0; i < choiceset.getChoiceId().length; i++) {
            try {

                String query = "UPDATE Choices SET Choice=? WHERE ChoiceId=?";
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.clearParameters();
                preparedstatement.setString(1, choiceset.getChoices()[i]);
                preparedstatement.setInt(2, choiceset.getChoiceId()[i]);
                preparedstatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.toString());
                // TODO: handle exception
                e.printStackTrace();

            }
        }
        try {

            String queryForQuestion = "UPDATE Questions SET CorrectAnswer=? WHERE ID = (Select Distinct QuestionID FROM Choices WHERE ChoiceId=?)";
            preparedstatement = this.connection
                    .prepareStatement(queryForQuestion);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, choiceset.getQuestionOrder());
            preparedstatement.setInt(2, choiceset.getChoiceId()[0]);
            preparedstatement.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.toString());
            e.printStackTrace();

        }
    }

    public ArrayList<Question> FetchAllQuestionsandChoices() {
        ArrayList<Question> returnvalue = new ArrayList<Question>();
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM Questions ");
            while (resultset.next()) {
                Question temp = new Question(resultset.getInt("ID"),
                        resultset.getString("Name"),
                        FetchChoicesByQuestionId(resultset.getInt("ID")),
                        resultset.getInt("CorrectAnswer"), "",
                        resultset.getInt("PositiveScore"),
                        resultset.getInt("NegativeScore"));
                returnvalue.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnvalue;
    }

    public Map<String, Integer> FetchCategoryIdandName() {
        Map<String, Integer> returneddata = new HashMap<String, Integer>();
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM SongTypes");
            while (resultset.next()) {

                returneddata.put(resultset.getString("Name"),
                        resultset.getInt("ID"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returneddata;
    }

    public Boolean UpdateHintsWithHintId(Hint hint) {
        try {
            String query = "UPDATE Hints SET Name=?, QuestionID =? WHERE Id=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, hint.getHintText());
            preparedstatement.setInt(2, hint.getQuestionId());
            preparedstatement.setInt(3, hint.getHintId());
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean UpdateQuestionWithQuestionModal(Question question,
            int categoryId) {
        try {

            String query = "UPDATE Questions SET Name=?,  CorrectAnswer=?, PositiveScore=?, NegativeScore=?, CategoryID=?  WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, question.getQuestiontext());
            preparedstatement.setInt(2, question.getCorrectanw());
            preparedstatement.setInt(3, question.getScore()[0]);
            preparedstatement.setInt(4, question.getScore()[1]);
            preparedstatement.setInt(5, categoryId);
            preparedstatement.setInt(6, question.getQuestionId());
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Choices> FetchAllChoices() {
        List<Choices> data = new CopyOnWriteArrayList<Choices>();
        String query_id = "Select ID,CorrectAnswer,Name FROM Questions";

        try {
            preparedstatement = connection.prepareStatement(query_id);
            preparedstatement.clearParameters();

            ResultSet resultset_id = preparedstatement.executeQuery();
            while (resultset_id.next()) {
                Choices choice = new Choices();
                choice.setQuestionid(resultset_id.getInt("ID"));
                choice.setQuestionText(resultset_id.getString("Name"));
                choice.setQuestionOrder(resultset_id.getInt("CorrectAnswer"));
                data.add(choice);

            }

        } catch (SQLException ex) {
            System.out.println("Question Id fetch hatali calismiyor -> "
                    + ex.toString());

        }

        for (Choices ch : data) {
            int choice_tempid[] = new int[4];
            String choice_temptext[] = new String[4];

            String query = "Select Distinct ChoiceID, Choice, ChoiceOrder FROM Choices WHERE QuestionID = ? ORDER BY ChoiceOrder ASC";
            try {
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.clearParameters();
                preparedstatement.setInt(1, ch.getQuestionid());

                ResultSet resultset2 = preparedstatement.executeQuery();
                int i = 0;

                while (resultset2.next()) {
                    if (i == ch.getChoiceId().length - 1) { // First add
                                                            // situation
                        choice_tempid[i] = resultset2.getInt("ChoiceID");
                        choice_temptext[i] = resultset2.getString("Choice");

                        ch.setChoiceId(choice_tempid);
                        ch.setChoices(choice_temptext);
                    } else {
                        ;
                        choice_tempid[i] = resultset2.getInt("ChoiceID");
                        choice_temptext[i] = resultset2.getString("Choice");
                    }
                    i++;
                }

            } catch (SQLException ex) {
                System.out.println("getCategoryId hatali calismiyor -> "
                        + ex.toString());

            }

        }

        return data;
    }

    public Boolean AddChallengeRequest(QuizStatistic quizdata, String code) {
        try {
            String query = "INSERT INTO Challenge (FromID,Code,CategoryID,OwnerScore) VALUES (?,?,?,?) ";

            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, quizdata.getOwnerId());
            preparedstatement.setString(2, code);
            preparedstatement.setInt(3, quizdata.getCategoryId());
            preparedstatement.setInt(4, quizdata.getUserScoreDifference());

            preparedstatement.executeUpdate();
            int cid = GetChallengeId(code);

            if (AddChallengeQuestions(quizdata.getWrongQuestions(),
                    quizdata.getTrueQuestions(), cid)) {

            } else {
                System.out
                        .println("Challenge question adding failed !!!!! CHECK  ");

            }

        } catch (SQLException e) {
            System.out.println("AddChallengeRequest not work properly => "
                    + e.toString());
            return false;
        }
        return true;

    }

    public int GetChallengeId(String code) {
        int returned = -1;
        try {
            String query = "SELECT ID FROM Challenge WHERE Code = ?  ";
            if (preparedstatement == null) {
                preparedstatement = connection.prepareStatement(query);
            }

            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, code);

            ResultSet resultSet1_add = preparedstatement.executeQuery();

            while (resultSet1_add.next()) {

                returned = resultSet1_add.getInt("ID");
            }

        } catch (SQLException ex) {
            System.out.print("Challenge ID fetch is not woking.");
        }
        if (returned == -1) {
            System.out.println("Challenge Id Get Failed :(");
        }
        return returned;
    }

    public Boolean AddChallengeQuestions(ArrayList<Question> wrongquestion,
            ArrayList<Question> truequestion, int ChallengeId) {
        for (Question question : wrongquestion) {
            try {
                String query = "INSERT INTO ChallengeQuestions (ChallengeID,QuestionID,isTrue) VALUES (?,?,?) ";
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.clearParameters();
                preparedstatement.setInt(1, ChallengeId);
                preparedstatement.setInt(2, question.getQuestionId());
                preparedstatement.setInt(3, 0);
                preparedstatement.executeUpdate();

            } catch (SQLException e) {
                System.out
                        .println("WrongQuestion Adding at ChallengeRequest not work properly => "
                                + e.toString());
                return false;
            }
        }

        for (Question question : truequestion) {
            try {
                String query = "INSERT INTO ChallengeQuestions (ChallengeID,QuestionID,isTrue) VALUES (?,?,?) ";
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.clearParameters();
                preparedstatement.setInt(1, ChallengeId);
                preparedstatement.setInt(2, question.getQuestionId());
                preparedstatement.setInt(3, 1);
                preparedstatement.executeUpdate();

            } catch (SQLException e) {
                System.out
                        .println("TrueQuestion Adding at ChallengeRequest not work properly => "
                                + e.toString());
                return false;
            }
        }

        return true;

    }

    public ArrayList<Question> FetchLimitedQuestions(int[] questionids,
            String categoryName) {

        ArrayList<Question> databank = new ArrayList<Question>();
        if (questionids == null) {
            System.out.println("Category empty!");
            return null;
        }
        if (questionids.length != 0) {
            for (int i = 0; i < questionids.length; i++) {
                if (questionids[i] > 0) {
                    Question tobeAdded = FetchSingleQuestionandHintById(questionids[i]);

                    if (tobeAdded != null) {
                        tobeAdded
                                .setChoices(FetchChoicesByQuestionId(questionids[i]));

                    }

                    databank.add(tobeAdded);

                } else {
                    System.out.println("Question ID Bos !!!!!!!");
                }
            }
        }
        return databank;
    }

    public String getName(int userid) {
        String returned = "";
        try {
            String query = "SELECT FullName FROM Users WHERE ObjectId=? ";
            if (preparedstatement == null) {
                preparedstatement = connection.prepareStatement(query);
            }

            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setInt(1, userid);

            ResultSet resultset1 = preparedstatement.executeQuery();
            resultset1.next();
            returned = resultset1.getString("FullName");

        } catch (SQLException ex) {
            System.out.print("GetName fetch is not woking." + ex.toString());
        }
        return returned;
    }

    public int[] FetchQuestionIdsByCategories(String categoryName,
            int questionLimit) {
        int[] questionIdArray = null;
        ResultSet resultSet1;
        try {
            String query = "SELECT ID FROM Questions WHERE CategoryID =(Select ID FROM SongTypes Where Name= ? ) ORDER BY RAND() LIMIT ? ";
            if (preparedstatement == null) {
                preparedstatement = connection.prepareStatement(query);
            }

            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, categoryName);
            preparedstatement.setInt(2, questionLimit);

            resultSet1 = preparedstatement.executeQuery();
            int i = 0;
            if (resultSet1.last()) {
                questionIdArray = new int[resultSet1.getRow()];
                resultSet1.beforeFirst(); // not rs.first() because the
                                          // rs.next() below will move on,
                                          // missing the first element
            }
            while (resultSet1.next()) {

                questionIdArray[i] = resultSet1.getInt("ID");
                i++;
            }

        } catch (SQLException ex) {
            System.out.print("Category fetch is not woking.");
        }

        return questionIdArray;
    }

    public Question FetchSingleQuestionandHintById(int questionid) {
        Question question = null;

        try {
            String query = "Select Questions.ID as ID, Questions.Name as Question, CorrectAnswer, PositiveScore, NegativeScore, Hint.Name AS Hint From Questions,"
                    + " (SELECT * FROM Hints WHERE QuestionID = ?) AS Hint WHERE Questions.ID = ? ";
            preparedstatement = connection.prepareStatement(query);

            preparedstatement.clearParameters();

            preparedstatement.setInt(1, questionid);
            preparedstatement.setInt(2, questionid);

            ResultSet resultset1 = preparedstatement.executeQuery();
            if (resultset1 == null) {
                System.out.println("resultset null");
            }
            if (resultset1.last()) {

                resultset1.beforeFirst(); // not rs.first() because the
                                          // rs.next() below will move on,
                                          // missing the first element
            }
            while (resultset1.next()) {
                String[] temp = null;

                question = new Question(resultset1.getInt("ID"),
                        resultset1.getString("Question"), temp,
                        resultset1.getInt("CorrectAnswer"),
                        resultset1.getString("Hint"),
                        resultset1.getInt("PositiveScore"),
                        resultset1.getInt("NegativeScore"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

            System.out.println("FetchSingleQuestionandHintById Error Occured");
        }

        return question;
    }

    public String[] FetchChoicesByQuestionId(int questionId) {
        String query = "SELECT Choice, ChoiceOrder FROM Choices WHERE QuestionId =? ";
        String[] choice = new String[4];

        try {
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, questionId);
            ResultSet resultset1 = preparedstatement.executeQuery();
            while (resultset1.next()) {
                int i = resultset1.getInt("ChoiceOrder");
                choice[i] = resultset1.getString("Choice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return choice;
    }

    public int getCategoryId(String categoryName) {
        String query = "Select ID FROM SongTypes Where Name=? ";
        int returned = -1;
        try {
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, categoryName);
            ResultSet resultset2 = preparedstatement.executeQuery();
            while (resultset2.next()) {
                returned = resultset2.getInt("ID");
            }

        } catch (SQLException ex) {
            System.out.println("getCategoryId hatali calismiyor -> "
                    + ex.toString());

        }
        if (returned == -1) {
            System.out.println("CategoryID bulunamadi....");
        }
        return returned;
    }

    // End Burak Yesilyurt Functions

    // BEGIN EMRE TEOMAN FUNCTIONS

    // BURAK WILL USE THIS FUNCTION BEGIN

    // Parametreler gayet a��k ve net, bunlar� doldur yeter :D

    public Boolean AddQuizResults(int UserId, int QuizSongTypeId, int Score,
            int TrueAnswerCount, int FalseAnswerCount) {
        try {
            String query = "INSERT INTO UserStatistics (UserId,QuizSongTypeId,Score,TrueAnswerCount,FalseAnswerCount) VALUES (?,?,?,?,?) ";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, UserId);
            preparedstatement.setInt(2, QuizSongTypeId);
            preparedstatement.setInt(3, Score);
            preparedstatement.setInt(4, TrueAnswerCount);
            preparedstatement.setInt(5, FalseAnswerCount);
            preparedstatement.executeUpdate();
            if (AddNewScoreOfQuizForUser(UserId, Score)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // BURAK WILL USE THIS FUNCTION END

    // Some functions about internal operations

    private Boolean AddNewScoreOfQuizForUser(int UserId, int score) {
        // @params UserId,score
        // To use update UserTotalScore Table
        Boolean result = false;
        try {
            if (UserExist(UserId)) {

                if (UserExistWithScore(UserId)) {
                    // update i�lemi yap
                    score = score + GetScoreWithUserId(UserId);
                    System.out.println("???????????????????????----" + score);
                    String query = "UPDATE UserTotalScore SET TotalScore=? WHERE UserId=? ";
                    preparedstatement = this.connection.prepareStatement(query);
                    preparedstatement.clearParameters();
                    preparedstatement.setInt(1, score);
                    preparedstatement.setInt(2, UserId);
                    preparedstatement.executeUpdate();
                    result = true;
                } else {
                    // insert i�lemi yap
                    String query = "INSERT INTO UserTotalScore (UserId,TotalScore) VALUES (?,?)";
                    preparedstatement = this.connection.prepareStatement(query);
                    preparedstatement.clearParameters();
                    preparedstatement.setInt(1, UserId);
                    preparedstatement.setInt(2, score);
                    preparedstatement.executeUpdate();
                    result = true;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Boolean UserExist(int UserId) {
        Boolean result = false;
        try {
            String query = "SELECT COUNT(*) FROM Users WHERE ObjectId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, UserId);
            resultset = preparedstatement.executeQuery();
            if (resultset.next()) {
                if (resultset.getInt(1) == 1) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Boolean UserExistWithScore(int UserId) {
        Boolean result = false;
        try {
            String query = "SELECT COUNT(*) FROM UserTotalScore WHERE UserId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, UserId);
            resultset = preparedstatement.executeQuery();
            if (resultset.next()) {
                if (resultset.getInt(1) == 1) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int GetScoreWithUserId(int UserId) {
        int result = 0;
        try {

            String query = "SELECT * FROM UserTotalScore WHERE UserId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, UserId);
            resultset = preparedstatement.executeQuery();
            if (resultset.next()) {
                result = resultset.getInt("TotalScore");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // end interval operations

    public Boolean UpdateUsersWithUserById(UserModel controlum) {
        try {
            String query = "UPDATE Users SET EMail=?,FullName=?, WHERE ObjectId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, controlum.GetEMailAddress());
            preparedstatement.setString(2, controlum.GetFullName());
            preparedstatement.setInt(3, controlum.GetObjectId());
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }

    public Boolean UpdateUsersWithAdminById(String id, String username,
            String email, String fullname, String type, String isactive,
            String isapproved) {
        try {
            String query = "UPDATE Users SET Username=?,EMail=?,FullName=?,UserType=?,IsActive=?,IsApproved=? WHERE ObjectId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            int idconverted = Integer.parseInt(id);
            int isactiveconverted = Integer.parseInt(isactive);
            int isapprovedconverted = Integer.parseInt(isapproved);
            preparedstatement.setInt(5, isactiveconverted);
            preparedstatement.setInt(6, isapprovedconverted);
            preparedstatement.setInt(7, idconverted);
            preparedstatement.setString(1, username);
            preparedstatement.setString(2, email);
            preparedstatement.setString(3, fullname);
            preparedstatement.setString(4, type);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }

    public Boolean DeleteUserById(int id) {
        try {
            String query = "DELETE FROM Users WHERE ObjectId = ?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public Boolean UpdateUserPasswordById(UserModel controlum) {
        try {
            String query = "UPDATE Users SET Password=? WHERE ObjectId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, controlum.GetPassword());
            preparedstatement.setInt(2, controlum.GetObjectId());
            preparedstatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public Boolean TheUserCheckIfExist(UserModel aUM) {
        Boolean sonuc = false;
        try {
            String query = "SELECT * FROM Users WHERE ObjectId=? and Password=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, aUM.GetObjectId());
            preparedstatement.setString(2, aUM.GetPassword());
            resultset = preparedstatement.executeQuery();
            if (resultset.next()) {
                if (resultset.getInt("ObjectId") == aUM.GetObjectId()) {
                    sonuc = true;
                }
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return sonuc;
    }

    public UserModel FetchAllUserByUser(int id) {
        UserModel returnvalue = new UserModel();
        try {
            String query = "SELECT * FROM Users WHERE ObjectId=" + id;
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            if (resultset.next()) {
                returnvalue.SetObjectId(resultset.getInt("ObjectId"));
                returnvalue.SetUserName(resultset.getString("Username"));
                returnvalue.SetEMailAddress(resultset.getString("EMail"));
                returnvalue.SetFullName(resultset.getString("Fullname"));
                returnvalue.SetGender(resultset.getBoolean("Gender"));
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return returnvalue;
    }

    public ArrayList<UserModel> FetchAllUser() {
        ArrayList<UserModel> returnvalue = new ArrayList<UserModel>();
        try {
            String query = "select * from Users Order by Username";

            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                UserModel um = new UserModel();
                um.SetObjectId(resultset.getInt("ObjectId"));
                um.SetUserName(resultset.getString("Username"));
                um.SetEMailAddress(resultset.getString("EMail"));
                um.SetFullName(resultset.getString("Fullname"));
                um.SetUserType(resultset.getString("UserType"));
                um.SetIsActive(resultset.getBoolean("IsActive"));
                um.SetIsApproved(resultset.getBoolean("IsApproved"));
                returnvalue.add(um);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return returnvalue;
    }

    public Boolean AddUser(UserModel aSignUp) {
        try {
            String query = "SELECT * FROM Users WHERE Username=? or EMail=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, aSignUp.GetUserName());
            preparedstatement.setString(2, aSignUp.GetEMailAddress());
            ResultSet rs = preparedstatement.executeQuery();
            if (!rs.next()) {
                query = "INSERT INTO Users(Username,Password,EMail,FullName,Gender,UserType,IsApproved,IsActive) VALUES (?,?,?,?,?,?,?,?)";
                preparedstatement.clearParameters();
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.setString(1, aSignUp.GetUserName());
                preparedstatement.setString(2, aSignUp.GetPassword());
                preparedstatement.setString(3, aSignUp.GetEMailAddress());
                preparedstatement.setString(4, aSignUp.GetFullName());
                preparedstatement.setBoolean(5, aSignUp.GetGender());
                preparedstatement.setString(6, aSignUp.GetUserType());
                preparedstatement.setBoolean(7, aSignUp.GetIsAproved());
                preparedstatement.setBoolean(8, aSignUp.GetIsActive());
                preparedstatement.executeUpdate();
                return true;
            } else
                return false;

        } catch (Exception ex) {
            // TODO: handle exception
            throw new UnsupportedOperationException(ex.getMessage());
        }

    }

    @SuppressWarnings("finally")
    public UserModel TryLogin(UserModel aSignUp) {

        UserModel loginPerson = null;
        try {
            String query = "SELECT * FROM Users WHERE Username=? and IsApproved=? and IsActive=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, aSignUp.GetUserName());
            preparedstatement.setInt(2, 1);
            preparedstatement.setInt(3, 1);
            ResultSet result = preparedstatement.executeQuery();
            result.next();

            if (result.getString("Password").equals(aSignUp.GetPassword()))
                loginPerson = new UserModel(result.getInt("ObjectID"),
                        result.getString("UserName"),
                        result.getString("FullName"),
                        result.getString("EMail"), result.getString("UserType"));

        } catch (Exception ex) {
            // TODO: handle exception
            System.out.print(ex.toString());
        } finally {
            return loginPerson;
        }
    }

    public ArrayList<UserStatisticsModel> SearchUserTotalScoreByFullName(
            String SearchFullName) {
        ArrayList<UserStatisticsModel> usmList = new ArrayList<UserStatisticsModel>();

        try {
            String query = "select distinct Users.FullName,UserTotalScore.TotalScore,Users.EMail,Users.Username,Users.ObjectId from UserStatistics inner join Users on UserStatistics.UserId = Users.ObjectId left join UserTotalScore on UserStatistics.UserId = UserTotalScore.UserId Where Users.UserType='user' and Users.FullName like '%";
            query = query.concat(SearchFullName);
            query = query.concat("%' ORDER BY UserTotalScore.TotalScore Desc");

            statement = this.connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                UserStatisticsModel usm = new UserStatisticsModel();
                UserModel um = new UserModel();

                um.SetFullName(resultset.getString(1));
                um.SetEMailAddress(resultset.getString(3));
                um.SetUserName(resultset.getString(4));
                um.SetObjectId(resultset.getInt(5));

                String query2 = "SELECT SUM(TrueAnswerCount) , SUM(FalseAnswerCount) FROM UserStatistics WHERE UserId="
                        + um.GetObjectId();
                statement = this.connection.createStatement();
                ResultSet resultset2 = statement.executeQuery(query2);
                if (resultset2.next()) {
                    usm.SetTrueAnswerCount(resultset2.getInt(1));
                    usm.SetFalseAnswerCount(resultset2.getInt(2));
                }

                usm.SetUserModel(um);
                usm.SetTotalScore(resultset.getInt(2));

                usmList.add(usm);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        if (usmList.get(0).GetUserModel().GetFullName() == null)
            return null;
        return usmList;
    }

    public ArrayList<UserStatisticsModel> GetUserDetailsById(int id) {
        ArrayList<UserStatisticsModel> usmList = new ArrayList<UserStatisticsModel>();
        try {
            String query = "SELECT SongTypes.Name,SUM(UserStatistics.Score),SUM(UserStatistics.TrueAnswerCount),SUM(UserStatistics.FalseAnswerCount) FROM UserStatistics INNER JOIN SongTypes ON UserStatistics.QuizSongTypeId=SongTypes.ID WHERE UserStatistics.UserId=? GROUP BY QuizSongTypeId";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
                UserStatisticsModel usm = new UserStatisticsModel();
                usm.SetQuizSongType(resultset.getString(1));
                usm.SetTotalScore(resultset.getInt(2));
                usm.SetTrueAnswerCount(resultset.getInt(3));
                usm.SetFalseAnswerCount(resultset.getInt(4));
                usmList.add(usm);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return usmList;
    }

    public ArrayList<UserStatisticsModel> GetTop5List() {
        ArrayList<UserStatisticsModel> usmList = new ArrayList<UserStatisticsModel>();
        try {
            String query = "SELECT Users.ObjectId,Users.Fullname,UserTotalScore.TotalScore FROM UserTotalScore INNER JOIN Users ON Users.ObjectId = UserTotalScore.UserId Order BY UserTotalScore.TotalScore DESC LIMIT 5";
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                UserStatisticsModel usm = new UserStatisticsModel();
                UserModel um = new UserModel();
                um.SetObjectId(resultset.getInt(1));
                um.SetFullName(resultset.getString(2));
                usm.SetTotalScore(resultset.getInt(3));
                usm.SetUserModel(um);
                usmList.add(usm);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return usmList;
    }

    // END EMRE TEOMAN FUNCTIONS

    // BEGIN SEREF BULBUL FUNCTIONS

    public ArrayList<SongModel> FetchAllSongs() {
        ArrayList<SongModel> returnvalue = new ArrayList<SongModel>();
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * from Songs");
            while (resultset.next()) {
                SongModel song = new SongModel();
                song.setID(resultset.getInt("ID"));
                song.setName(resultset.getString("Name"));
                song.setTypeId(resultset.getInt("TypeId"));
                song.setSingerId(resultset.getInt("SingerId"));
                returnvalue.add(song);
            }
        } catch (SQLException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return returnvalue;
    }

    public SongModel GetSongBySongId(int id) {
        SongModel returnvalue = new SongModel();
        try {
            String query = "SELECT * FROM Songs WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, id);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next()) {
                returnvalue.setID(rs.getInt("ID"));
                returnvalue.setName(rs.getString("Name"));
                returnvalue.setTypeId(rs.getInt("TypeId"));
                returnvalue.setSingerId(rs.getInt("SingerId"));
            }
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }

        return returnvalue;
    }

    public Boolean AddSong(SongModel song) {
        try {
            String query = "SELECT * FROM Songs WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, song.getID());
            ResultSet rs = preparedstatement.executeQuery();
            if (!rs.next()) {
                query = "INSERT INTO Songs(Name,TypeId,SingerId) VALUES (?,?,?)";
                preparedstatement.clearParameters();
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.setString(1, song.getName());
                preparedstatement.setInt(2, song.getTypeId());
                preparedstatement.setInt(3, song.getSingerId());
                preparedstatement.executeUpdate();
                return true;
            } else
                return false;

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }

    public Boolean DeleteSongById(int id) {
        try {
            String query = "DELETE FROM Songs WHERE ID = ?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public Boolean UpdateSongById(int id, String name, int typeId, int singerId) {
        try {
            String query = "UPDATE Songs SET Name=?, TypeId=?, SingerId=? WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, name);
            preparedstatement.setInt(2, typeId);
            preparedstatement.setInt(3, singerId);
            preparedstatement.setInt(4, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public int getSingerIdBySongId(String name) {
        int returnvalue = 0;
        try {
            String query = "SELECT * FROM Singers WHERE NameSurname=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, name);
            ResultSet rs = preparedstatement.executeQuery();
            while (rs.next()) {
                returnvalue = rs.getInt("ID");
            }
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return returnvalue;
    }

    public int getSongCountBySingerId(int singerId) {
        try {
            String query = "SELECT COUNT(*) FROM Songs WHERE SingerId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, singerId);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return 0;
    }

    public int getSongCountByTypeId(int typeId) {
        try {
            String query = "SELECT COUNT(*) FROM Songs WHERE TypeId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, typeId);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return 0;
    }

    public int getQuestionCountByTypeId(int typeId) {
        try {
            String query = "SELECT COUNT(*) FROM Questions WHERE CategoryID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, typeId);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return 0;
    }

    public int getUserStatisticsCountByTypeId(int typeId) {
        try {
            String query = "SELECT COUNT(*) FROM UserStatistics WHERE QuizSongTypeId=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, typeId);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return 0;
    }

    public int getChallengeCountByTypeId(int typeId) {
        try {
            String query = "SELECT COUNT(*) FROM Challenge WHERE CategoryID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, typeId);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return 0;
    }

    public ArrayList<SingerModel> FetchAllSingers() {
        ArrayList<SingerModel> returnvalue = new ArrayList<SingerModel>();
        try {
            String query = "SELECT * FROM Singers";
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                SingerModel singer = new SingerModel();
                singer.setID(resultset.getInt("ID"));
                singer.setNameSurname(resultset.getString("NameSurname"));
                singer.setBirthDate(resultset.getString("BirthDate"));
                if (resultset.getInt("isBand") == 1) {
                    singer.setType("Band");
                } else {
                    singer.setType("Person");
                }

                returnvalue.add(singer);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return returnvalue;
    }

    public List<String> FetchAllSingerNames() {
        List<String> returnvalue = new ArrayList<String>();
        try {
            String query = "SELECT * FROM Singers";
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                returnvalue.add(resultset.getString("NameSurname"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return returnvalue;
    }

    public Boolean AddSinger(SingerModel singer) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(singer.getBirthDate());
        java.sql.Date _birthDate = new java.sql.Date(parsed.getTime());
        try {
            String query = "SELECT * FROM Singers WHERE NameSurname=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, singer.getNameSurname());
            ResultSet rs = preparedstatement.executeQuery();
            if (!rs.next()) {
                query = "INSERT INTO Singers(NameSurname,BirthDate,isBand) VALUES (?,?,?)";
                preparedstatement.clearParameters();
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.setString(1, singer.getNameSurname());
                preparedstatement.setDate(2, _birthDate);
                if (singer.getType().equals("Band")) {
                    preparedstatement.setInt(3, 1);
                } else {
                    preparedstatement.setInt(3, 0);
                }

                preparedstatement.executeUpdate();
                return true;
            } else
                return false;

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }

    public Boolean DeleteSingerById(int id) {
        try {
            String query = "DELETE FROM Singers WHERE ID = ?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public Boolean UpdateSingerById(int id, String nameSurname,
            String birthDate, String type) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(birthDate);
        java.sql.Date _birthDate = new java.sql.Date(parsed.getTime());
        try {
            String query = "UPDATE Singers SET NameSurname=?, BirthDate=?, isBand=? WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, nameSurname);
            preparedstatement.setDate(2, _birthDate);
            if (type.equals("Band")) {
                preparedstatement.setInt(3, 1);
            } else {
                preparedstatement.setInt(3, 0);
            }

            preparedstatement.setInt(4, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public String getSingerNameById(int id) {
        try {
            String query = "SELECT NameSurname FROM Singers WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                return rs.getString(1);
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return "";
    }

    public int getSingerIdByName(String name) {
        int returnvalue = 0;
        try {
            String query = "SELECT ID FROM Singers WHERE NameSurname=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, name);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                returnvalue = rs.getInt(1);
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return returnvalue;
    }

    public boolean getSingerExistenceByName(String name) {
        try {
            String query = "SELECT * FROM Singers WHERE NameSurname=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, name);
            ResultSet rs = preparedstatement.executeQuery();
            return rs.next();

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }

    public ArrayList<SongTypeModel> FetchAllSongTypes() {
        ArrayList<SongTypeModel> returnvalue = new ArrayList<SongTypeModel>();
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * from SongTypes");
            while (resultset.next()) {
                SongTypeModel songType = new SongTypeModel();
                songType.setID(resultset.getInt("ID"));
                songType.setName(resultset.getString("Name"));
                returnvalue.add(songType);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return returnvalue;
    }

    public List<String> FetchAllSongTypeNames() {
        List<String> returnvalue = new ArrayList<String>();
        try {
            String query = "SELECT * FROM SongTypes";
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                returnvalue.add(resultset.getString("Name"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return returnvalue;
    }

    public Boolean AddSongType(SongTypeModel songType) {
        try {
            String query = "SELECT * FROM SongTypes WHERE Name=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, songType.getName());
            ResultSet rs = preparedstatement.executeQuery();
            if (!rs.next()) {
                query = "INSERT INTO SongTypes(Name) VALUES (?)";
                preparedstatement.clearParameters();
                preparedstatement = this.connection.prepareStatement(query);
                preparedstatement.setString(1, songType.getName());
                preparedstatement.executeUpdate();
                return true;
            } else
                return false;

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }

    public Boolean DeleteSongTypeById(int id) {
        try {
            String query = "DELETE FROM SongTypes WHERE ID = ?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public Boolean UpdateSongTypeById(int id, String name) {
        try {
            String query = "UPDATE SongTypes SET Name=? WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.clearParameters();
            preparedstatement.setString(1, name);
            preparedstatement.setInt(2, id);
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public String getSongTypeNameById(int id) {
        try {
            String query = "SELECT Name FROM SongTypes WHERE ID=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setInt(1, id);
            ResultSet rs = preparedstatement.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }

    public int getSongTypeIdByName(String name) {
        int returnvalue = 0;
        try {
            String query = "SELECT ID FROM SongTypes WHERE Name=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, name);
            ResultSet rs = preparedstatement.executeQuery();
            if (rs.next())
                returnvalue = rs.getInt(1);
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
        return returnvalue;
    }

    public boolean getSongTypeExistenceByName(String name) {
        try {
            String query = "SELECT * FROM SongTypes WHERE Name=?";
            preparedstatement = this.connection.prepareStatement(query);
            preparedstatement.setString(1, name);
            ResultSet rs = preparedstatement.executeQuery();
            return rs.next();

        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }

    // END SEREF BULBUL FUNCTIONS

    public void CloseConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.print("Connection close error");
            e.printStackTrace();
        }
    }

    public ArrayList<Question> FetchRandomQuestions(int amount, String Subject) {
        // Its just a template.
        return null;
    }

}
