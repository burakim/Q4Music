Parts Implemented by Emre TEOMAN
================================

*******************
Desinging Pages
*******************

Default Page
############

There is a base class for our application and it is called DefaultPage.  This page has our main theme (header panel and left panel). Html side of this class has two wicket panel for header and left side and java side add this panel on the page.  All pages derived from DefaultPage class. Therefore, our theme get automatically in all pages. Constructor of this class is like that 

.. code-block:: java

	public DefaultPage() {
    	  add(new MasterPageContentHeaderPanel("panel1"));
    	  add(new MasterPageContentBodyPanel("panel2"));
	}
..

Login Page
##############

In login page, there are two different mission actually. Sign in panel and sign up panel is change with each other by javascript. Sign up panel has some client side validation controls. For example, email part has to be filled like xx@xx.xx . Another validation is password and confirm password text fields. If they are not equal, form will not submit. This methodology saved time. Beside of these, any text fields can be empty and this is also controlled with javascript.  All of these operation are performed on client-side.  
There is a server-side control. It is used to guarantee that any two user cannot has same username or email address. Therefore, username or email address will be unique. 
There two important classes in here. One of these classes is UserModel class. This class has all of the information about users and their getter and setter methods. The most important part of this class is password and its set method. This set method get a password which is given by user and hash it with SHA-1 algorithm. All of the user operations perform on this class. UserModel class has ObjectID, UserName,Password, EMailAddress, FullName, Gender, UserType, IsApproved, IsActive variables and their getter and setter functions. In order to SHA-1, SetPassword method is important and it is in below:

.. code-block:: java

	public void SetPassword(String Password) {
	  MessageDigest mDigest = MessageDigest.getInstance("SHA1");
          byte[] result = mDigest.digest(Password.getBytes());
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < result.length; i++) {
          	  sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
          }
          this.Password = new String(sb);
          System.out.println("Exception: " + e);
        }
..

This class is also used while users try to login because of hashing algorithm. If the user who login the web site has “user” permission, it will see only user pages, otherwise it will see admin pages. 
Another important class is UserSession class. It is extended by WebSession. Session information is used in all of the application. For example, if a user login the page, session is created and until the logout or timeout, this session stay alive. When a user opened the new tab on its browser, it is not see login page and directly pass the application. Another example, if a user who has user type user try to login admin pages with url, it cannot open admin pages. This control is in Default class. Last important usage for UserSession is in quiz part. Quiz results and scores have to be associated with users. This relation is setted with user information in UserSession. UserSession class have UserModel and its getter-setter methods. Log out operation makes session invalid.

Myprofile Page
###############

This page has two form such as edit account and change password. Users can switch these forms with tab. This switch operations perform with the javascript. In edit accunt tab, user can update full name or email. In change password tab, user can update own password. This password update operation perform with UserModel because of SHA-1 hashing mechanisim. The username field cannot update by user.

Search User
############

Search user part of this project is running as search panel. It can search by full name and list the results. This results are shown with dataview object. This object creates a link button called details and this link redirects the user to user detail page with the parameter (id). While this page is loading, the sql query is runnig to get information about the user.

User Statistics
###############

This part is same the user details page actually beacuse this statistics are shown in same page. In user statistics page, id of logined user is get from the user session and sent to user details page as parameter. Therefore, I get rid of write the code.

Quik look Statistics
#######################

This part is located in header panel of the web site and it can open with js. I write a modal for this part and I read the user information from UserStatistics table and list in this part. Firstly, I found the user and which categories s/he has points. There is a repeatingview in here and it repeats a modal with category and score information.

Top List
#########

It is located in left panel under the menu. This part like statistic (quik look statistic) part because of repeatingview and a modal. There is a modal for this part and it has full name of users and their scores but only top 5. 

Admin User Management
#####################

In this part, there is a list with edit and delete features. Edit button show a modal with javascript and this modal contains information about the user. While this list loading to the dataview, detailed information is add to edit button as html attribute. This is peformed in componentTag method in populatedItem method. It provide that this model is filled with javascript only. In the modal, save changes button update the user's information. Delete button delete the user information both register information and score informations.

Logout 
#######

Logout button make the session invalidate and redirect to login page.


Database Operations
###################

There is a UserModel class for user operations. This class contains all fields of Users table and its getter-setter functions. Login page is used this class for getting arguments from user with UserModel object. After the filling UserModel object necessary operations are called. The most important part of using UserModal class is that passwords of the users are encrypted by SHA-1. 

**public UserModel TryLogin(UserModel aSignUp)** function is used for login operations. It gets the parameter with type UserModel and it returns a UserModel. This function used this query : 

.. code-block:: sql

	SELECT * FROM Users WHERE Username=? and IsApproved=? and IsActive=?

..
After that, If the password which came from database and the password which is entered by user are equal, this function returns UserModel of logined User. This return modal will used for UserSession and it will be mentioned further. 

**public Boolean AddUser(UserModel aSignUp)** function is used for sign up operation. It gets the parameter with type UserModel and it returns a bool value that means successfull signing or failed signing. First query is 

.. code-block:: sql

	SELECT * FROM Users WHERE Username=? or EMail=?
..
Firstly, I will check the username and email address of the newcomer. If it does not exist in database, I add it to the database with this command :

.. code-block:: sql

	INSERT INTO Users(Username,Password,EMail,FullName,Gender,UserType,IsApproved,IsActive) VALUES (?,?,?,?,?,?,?,?)
..
and return true. This bool return value will be used on SignUp part of Login page, if it is true, the new user will redirect to homepage.


**public ArrayList<UserModel> FetchAllUser()** function is used for fetching all users without any condition. It uses this query: 

.. code-block:: sql

	SELECT * FROM Users

..

This function return an UserModel arraylist. 

**public UserModel FetchAllUserByUser(int id)** function is used for fetching only one user by id if it is exist. It uses this query: 

.. code-block:: sql

	SELECT * FROM Users WHERE ObjectId=?
..

If the user exist, it will be return UserModal according to this user. If it is not exist it will return null.

**public Boolean UpdateUsersWithUserById(UserModel controlum)** function is used for updating fullname and email address of users. It is used by user in profile settings. Query of this funciton is 

.. code-block:: sql

	UPDATE Users SET EMail=?,FullName=?, WHERE ObjectId=?

..

This function take a one parameter which is a UserModel. This parameters gives to this function using session of logined user. If the update operations is successfull, this function returns true. Otherwise it returns false.

**public Boolean UpdateUserPasswordById(UserModel controlum)** function is used for updating password of the user. It is used by user in profile settings. Query of this function is 

.. code-block:: sql

	UPDATE Users SET Password=? WHERE ObjectId=?

..

If the update is successfull, it returns true. Otherwise it returns false.

**public Boolean UpdateUsersWithAdminById(String id, String username,String email, String fullname, String type, String isactive,String isapproved)** function is used for updating user information but in admin side. It take parameters such as id, username etc. This function cannot take UserModal parameter because of the design and it will explained further. This function uses the query : 

.. code-block:: sql

	UPDATE Users SET Username=?,EMail=?,FullName=?,UserType=?,IsActive=?,IsApproved=? WHERE ObjectId=?

.. 

**public Boolean DeleteUserById(int id)** function delete the user with id. It takes one parameter id and delete the user which has the id number. The query is :

.. code-block:: sql

	DELETE FROM Users WHERE ObjectId = ?

..

**public ArrayList<UserStatisticsModel> GetTop5List()** function get the 5 heighest score in the database. Results are located in Left panel on the web site. This function use this query :

.. code-block:: sql

	SELECT Users.ObjectId,Users.Fullname,UserTotalScore.TotalScore FROM UserTotalScore INNER JOIN Users ON Users.ObjectId = UserTotalScore.UserId Order BY UserTotalScore.TotalScore DESC LIMIT 5

..

Users and UserTotalScore table are used together because we want to get both user name and total score. It returns UserStatisticModel arraylist.

.. NOTE:: UserStatisticModel is modal to hold UserModal and its quiz results. It has variables such as UserModel,QuizSongType,TrueAnswerCount,FalseAnswerCount and its getter-setter methods.

**public Boolean AddQuizResults(int UserId, int QuizSongTypeId, int Score,int TrueAnswerCount, int FalseAnswerCount)** function is used to add new quiz results to UserStatistics table. It is simply used this query: 

.. code-block:: sql

	INSERT INTO UserStatistics (UserId,QuizSongTypeId,Score,TrueAnswerCount,FalseAnswerCount) VALUES (?,?,?,?,?)

..

After the insert operation, AddNewScoreOfQuizForUser function is called with parameters such as UserId and Score.

**private Boolean AddNewScoreOfQuizForUser(int UserId, int score)** funciton is used to update UserTotalScore table. If there is a record with this user id, this function perform an upadate operation. If it is not, the function perform insert operation. **private Boolean UserExistWithScore(int UserId)** function check the there is a record for this user and return a bool value. True means that there is a record and perform update operation, false means that there is no record and perform insert operation. Insert operation is simple and it use 

.. code-block:: sql

	INSERT INTO UserTotalScore (UserId,TotalScore) VALUES (?,?)

..

query. Update function needed an extra function called **GetScoreWithUserId(UserId)**. This function use 

.. code-block:: sql

 	SELECT * FROM UserTotalScore WHERE UserId=?
..

and returns score of associated user. After that new score is calculated and the UserTotalScore table updated with this query 

.. code-block:: sql

 	UPDATE UserTotalScore SET TotalScore=? WHERE UserId=?

..

and returns a boolean value for understanding operation is performed succcessfully or not.

**private Boolean UserExist(int UserId)** is another interval function for check the user exist or not. It use this parameter: 

.. code-block:: sql

	SELECT COUNT(*) FROM Users WHERE ObjectId=?

..

If count is 1, it means that there is an user with this UserId and the function returns true.

**public Boolean TheUserCheckIfExist(UserModel aUM)** function is used to check that the user is exist or not but it controled with two parameters actually such as ObjectId and Password. This function use this parameter: 

.. code-block:: sql

	SELECT * FROM Users WHERE ObjectId=? and Password=?

..
If there is a user that has this informationi, the function returns true, otherwise returns false.

**public ArrayList<UserStatisticsModel> SearchUserTotalScoreByFullName(String SearchFullName)** function is used to search other user according to their full name. It has a long query for this purpose: 

.. code-block:: sql

	select distinct Users.FullName,UserTotalScore.TotalScore,Users.EMail,Users.Username,Users.ObjectId from UserStatistics inner join Users on UserStatistics.UserId = Users.ObjectId left join UserTotalScore on UserStatistics.UserId = UserTotalScore.UserId Where Users.UserType='user' and Users.FullName like '%SearchFullName%'

..

With the help of this query, users which are searched can find easily with their score information.

**public ArrayList<UserStatisticsModel> GetUserDetailsById(int id)** function is used to get details of the user which has ObjectId as id. This function uses this query: 

.. code-block:: sql

	SELECT SongTypes.Name,SUM(UserStatistics.Score),SUM(UserStatistics.TrueAnswerCount),SUM(UserStatistics.FalseAnswerCount) FROM UserStatistics INNER JOIN SongTypes ON UserStatistics.QuizSongTypeId=SongTypes.ID WHERE UserStatistics.UserId=? GROUP BY QuizSongTypeId

..

and returns UserStatisticsModel arraylist. This list used in My Profile part of web site. 



