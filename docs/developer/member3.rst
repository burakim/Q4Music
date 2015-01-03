Parts Implemented by Seref Bulbul
================================


************************************************************************
Java Side's Descriptions
************************************************************************

DataViews
#########################################################################

DataViews are used for the filling tables. They include populateItem function which puts values to the appropriate columns in the table row by row. Also in the dataviews other buttons are initialized with their onClick and onComponentTag functions like edit and delete buttons because they are also put to the every row.

Forms
#########################################################################

Forms are used for the modals. They includes text fields, radio buttons or drop down choices according to they will be used what type of database object and these components are initialized with the Javascript functions when modal is opened.

Song-Singer-Song Type Logic (Edit Song)
#########################################################################

When designing the edit song operation, it is based on that there will be no singer or song type without the song. Therefore, when updating a song there are 4 options:

#. **Existing singer, existing song type:** In this case, system checks previous singer and song type song and if any of them connected with less than 2 songs, delete it after the updating song with existing ids. 
#. **Existing singer, new song type:** In this case, system checks previous singer and song type song and if any of them connected with less than 2 songs, delete it after the adding new song type and updating song with existing and new ids.
#. **New singer, existing song type:** In this case, system checks previous singer and song type song and if any of them connected with less than 2 songs, delete it after the adding new singer and updating song with existing and new ids.
#. **New singer, new song type:** In this case, system checks previous singer's and song type's song number and if any of them connected with less than 2 songs, delete it after the adding new singer & song type and updating song with new ids.

************************************************************************
Javascript Functions' Descriptions
************************************************************************

**AdminSongOperationsEditModal():** This fuction provides songs edit modal to be opened. Also, it sets the given html ids' text fields with the tagged values and drop down choices' selected value with the tagged value.

**AdminAddNewSong():** This fuction just used to open a add song modal.

**AdminSingerOperationsEditModal():** This fuction provides singer edit modal to be opened. Also, it sets the given html ids' text fields with the tagged values and radio buttons' selected value with the tagged value.

**AdminSingerTypeSelection():** It sets the invisible text field's text according to radio buttons when save changes button is clicked in edit or add song modal.

**AdminSongTypeOperationsEditModal():** This fuction provides songs edit modal to be opened. Also, it sets the given html ids' text fields with the tagged values.


************************************************************************
Database Operation Function Descriptions
************************************************************************

public ArrayList<SongModel> FetchAllSongs()
#########################################################################
This function firstly creates a array list which includes SongModel type. Then, it executes query

.. code-block:: sql

	SELECT * from Songs

In the while loop it creates a song variable which is SongModel type and initialize song with result row ID, Name, TypeId and SingerId columns. Then add song to the created array list. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns that array list.

public SongModel GetSongBySongId(int id)
#########################################################################
This function firstly creates a song which is SongModel type. Then, it executes query 

.. code-block:: sql

	SELECT * FROM Songs WHERE ID=?


and if any result exists, it adds result row ID, Name, TypeId and SingerId columns to the song. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns that song.

public Boolean AddSong(SongModel song) 
#########################################################################
This function executes query

.. code-block:: sql

	"SELECT * FROM Songs WHERE ID=?
..
and if any result exists, it does not add the song. Otherwise, it executes the query

.. code-block:: sql

 	INSERT INTO Songs(Name,TypeId,SingerId) VALUES (?,?,?)

 and adds the given song. During this operations, if any exception is thrown, it catches the exception and print its stack trace. Finally, if song is added it returns true otherwise it returns false.

public Boolean DeleteSongById(int id) 
#########################################################################
This function executes query 

.. code-block:: sql

	DELETE FROM Songs WHERE ID = ?

which deletes the row with given id. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, if song is deleted successfully, it returns true otherwise it returns false.

public Boolean UpdateSongById(int id, String name, int typeId, int singerId) 
#########################################################################
This function executes query 

.. code-block:: sql

	UPDATE Songs SET Name=?, TypeId=?, SingerId=? WHERE ID=?

which updates the row whose ID equals to given id, with given value. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, if song is updated successfully, it returns true otherwise it returns false.

public int getSingerIdBySongId(String name) 
#########################################################################
This function firstly creates a integer which is initialized with 0. Then, it executes query 

.. code-block:: sql

	SELECT * FROM Singers WHERE NameSurname=?

and if any result exists, it equalizes result ID column to the created integer. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns the integer.

public int getSongCountBySingerId(int singerId) 
#########################################################################
This function executes query 

.. code-block:: sql

	SELECT COUNT(*) FROM Songs WHERE SingerId=?

which gives the number of songs with given SingerId and if any result exists, it return result first column. During this operation, if any exception is thrown, it catches the exception and print its stack trace.

public int getSongCountByTypeId(int typeId) 
#########################################################################
This function executes query 

.. code-block:: sql

	SELECT COUNT(*) FROM Songs WHERE TypeId=?

which gives the number of songs with given TypeId and if any result exists, it return result first column. During this operation, if any exception is thrown, it catches the exception and print its stack trace.

public int getQuestionCountByTypeId(int typeId)
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT COUNT(*) FROM Questions WHERE CategoryID=?

which gives the number of questions with given CategoryID and if any result exists, it return result first column. During this operation, if any exception is thrown, it catches the exception and print its stack trace.

public int getUserStatisticsCountByTypeId(int typeId)
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT COUNT(*) FROM UserStatistics WHERE QuizSongTypeId=?

which gives the number of user statistics with given QuizSongTypeId and if any result exists, it return result first column. During this operation, if any exception is thrown, it catches the exception and print its stack trace.

public int getChallengeCountByTypeId(int typeId)
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT COUNT(*) FROM Challenge WHERE CategoryID=?

which gives the number of challenges with given CategoryID and if any result exists, it return result first column. During this operation, if any exception is thrown, it catches the exception and print its stack trace.

public ArrayList<SingerModel> FetchAllSingers()
#####################################################################
This function firstly creates a array list which includes SingerModel type. Then, it executes query

.. code-block:: sql

	SELECT * from Singers

In the while loop it creates a singer variable which is SingerModel type and initialize singer with result row ID, NameSurname, BirthDate and isBand which refers to String Band or Person, columns. Then it adds singer to the created array list. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns that array list.

public List<String> FetchAllSingerNames()
#####################################################################
This function firstly creates a array list which includes String type. Then, it executes query 

.. code-block:: sql

	SELECT * from Singers

which gives all singer rows and add result row NameSurname column to the created array list. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns that array list.

public Boolean AddSinger(SingerModel singer) 
#####################################################################
This function firstly parsa util.Date to the sql.Date format. Then it executes query 

.. code-block:: sql

	SELECT * FROM Singers WHERE ID=?

which gives song with given singer id and if any result exists, it does not add the song. Otherwise, it executes the query 

.. code-block:: sql

	INSERT INTO Singers(NameSurname,BirthDate,isBand) VALUES (?,?,?)

and adds the given song. During this operations, if any exception is thrown, it catches the exception and print its stack trace. Finally, if singer is added it returns true otherwise it returns false.

public Boolean DeleteSingerById(int id) 
#####################################################################
This function executes query 

.. code-block:: sql

	DELETE FROM Singers WHERE ID = ?

which deletes the row with given id. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, if singer is deleted successfully, it returns true otherwise it returns false.

public Boolean UpdateSingerById(int id, String nameSurname, String birthDate, String type) 
#####################################################################
This function executes query 

.. code-block:: sql

	UPDATE Singers SET NameSurname=?, BirthDate=?, isBand=? WHERE ID=?

which updates the row whose ID equals to given id, with given value. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, if singer is updated successfully, it returns true otherwise it returns false.

public String getSingerNameById(int id) 
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT NameSurname FROM Singers WHERE ID=?

which gives the row NameSurname column whose ID equals to given id. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, it returns the NameSurname column value.

public int getSingerIdByName(String name) 
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT ID FROM Singers WHERE NameSurname=?

which gives the row ID column whose NameSurname equals to given name. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, it returns the ID column value.

public boolean getSingerExistenceByName(String name) 
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT * FROM Singers WHERE NameSurname=?

which gives the rows whose NameSurname equals to given name. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, it returns a boolean which refers whether any row exists or not.

public ArrayList<SongTypeModel> FetchAllSongTypes()
#####################################################################
This function firstly creates a array list which includes SongTypeModel type. Then, it executes query 

.. code-block:: sql

	SELECT * from SongTypes

In the while loop, it creates a songType variable which is SongTypeModel type and initialize songType with result row ID and Name columns. Then it adds songType to the created array list. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns that array list.

public List<String> FetchAllSongTypeNames()
#####################################################################
This function firstly creates a array list which includes String type. Then, it executes query 

.. code-block:: sql

	SELECT * from SongTypes

which gives all singer rows and add result row Name column to the created array list. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, returns that array list.

public Boolean AddSongType(SongTypeModel songType) 
#####################################################################
This function firstly executes query 

.. code-block:: sql

	SELECT * FROM SongTypes WHERE ID=?

which gives songType with given songType id and if any result exists, it does not add the songType. Otherwise, it executes the query 

.. code-block:: sql

	INSERT INTO SongTypes(Name) VALUES (?)

and adds the given songType. During this operations, if any exception is thrown, it catches the exception and print its stack trace. Finally, if songType is added it returns true otherwise it returns false.

public Boolean DeleteSongTypeById(int id) 
#####################################################################
This function executes query 

.. code-block:: sql

	DELETE FROM SongTypes WHERE ID = ?

which deletes the row with given id. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, if songType is deleted successfully, it returns true otherwise it returns false.

public Boolean UpdateSongTypeById(int id, String name) 
#####################################################################
This function executes query 

.. code-block:: sql

	UPDATE SongTypes SET Name=? WHERE ID=?

which updates the row whose ID equals to given id, with given value. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, if songType is updated successfully, it returns true otherwise it returns false.

public String getSongTypeNameById(int id) 
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT Name FROM SongTypes WHERE ID=?

which gives the row Name column whose ID equals to given id. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, it returns the Name column value.

public int getSongTypeIdByName(String name) 
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT ID FROM SongTypes WHERE Name=?

which gives the row ID column whose Name equals to given name. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, it returns the ID column value.

public boolean getSongTypeExistenceByName(String name) 
#####################################################################
This function executes query 

.. code-block:: sql

	SELECT * FROM SongTypes WHERE Name=?

which gives the rows whose Name equals to given name. During this operation, if any exception is thrown, it catches the exception and print its stack trace. Finally, it returns a boolean which refers whether any row exists or not.


