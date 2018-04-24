package carbon.zeroevents.ListCards;

/**
 * Created by mariopalmissolano on 10/03/2018.
 */

public class Item {


    int userID;
    int movieID;

    String movieTitle;
    String realeaseDate;
    String releaseDateMillis;


    public String getReleaseDateMillis() {
        return releaseDateMillis;
    }

    public void setReleaseDateMillis(String releaseDateMillis) {
        this.releaseDateMillis = releaseDateMillis;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getRealeaseDate() {
        return realeaseDate;
    }
    public void setRealeaseDate(String realeaseDate) {
        this.realeaseDate = realeaseDate;
    }


}
