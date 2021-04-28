# MiniTwitter
→ Implements a Twitter-like messaging service using Spring Boot. 

→ Provides a Rest API with all endpoints under Basic HTTP Authentication which is implemented using Spring Security. 

→ Data stored in H2, an in-memory SQL database.

## Given below are the functionalities:

### 1. Get the current user's followers:

    curl -u "<username>:<password>" "http://localhost:8080/followers"

### 2. Get people which the current user follows:

    curl -u "<username>:<password>" "http://localhost:8080/following"
    
### 3. Follow a particular user:

    curl -u "<username>:<password>" -X PUT "http://localhost:8080/follow" --data "name=Xandra"
    
### 4. Unfollow a particular user:

    curl -u "<username>:<password>" -X PUT "http://localhost:8080/unfollow" --data "name=Xandra"
    
### 5. Get user feed:
a) This is used to get tweets of the user himself and of all the users he follows.

    curl -u "<username>:<password>" "http://localhost:8080/feed"
    
b) Supports a "?search=" functionality which filters tweets depending on keyword given.  

    curl -u "<username>:<password>" "http://localhost:8080/feed?search=lorem"
    
### 6. Gets a popular follower for every user.

    curl -u "<username>:<password>" "http://localhost:8080/popularfollower"
    

The username and password has been stored in `application.properties` file. We make an assumption that the user to which the username belongs, is one of the 10 users which we create at the start of the application.      
    
    
