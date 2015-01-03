Welcome to Q4Music's documentation!
===================================

:Team: itucsdb - 1424

:Members:

   * Emre Teoman
   * Halim Burak Yeşilyurt
   * Şeref Bülbül


Q4Music is a web based game which provides users to make quizes and invite their friends to the same quizes. Also, it has a archive about songs and singers. Users can look at this archive to improve themselves. Java with Wicket framework, Html, Css, Javascript, Ajax, MySql on an ubuntu server, JazzHub as a version control system and Bluemix for the deployment are the technologies that are used in this project.

In quiz part, user firstly selects a music type which will specify the questions' category and then he/she starts the quiz. Quiz includes 5 questions. When quiz is finished, user can see his/her score, true answer number, wrong answer number and their true answers. Finally, he/she can invite friends. Invited friend will receive an e-mail which includes a link to the same quiz. 

In archive part, user can look at songs with names, singers and song types; singers with name surnames, birth dates and types; song types with names to improve themselves. 

In admin side, admin can edit all type of objects such as user, song, singer, questions, hint and so on. In addition, admin can add these objects from related pages.

Work Sharings:

**Emre Teoman** 
Session control, user management, user statistics, user scores

**Halim Burak Yeşilyurt** 
Question, hint, choices management, music quiz module, challenge mode

**Şeref Bülbül** 
Song, song types, singer archive.




.. toctree::
   :maxdepth: 1
   
   user/index
   developer/index
