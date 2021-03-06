package edu.montana.csci.csci440.homework;

import edu.montana.csci.csci440.DBTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class






Homework1 extends DBTest {


    @Test
    /*
     * Write a query in the string below that returns all artists that have an 'A' in their name
     */
    void selectArtistsWhoseNameHasAnAInIt(){
        List<Map<String, Object>> results = executeSQL("SELECT * FROM artists WHERE name LIKE \"%A%\"");
        assertEquals(211, results.size());
    }

    @Test
    /*
     * Write a query in the string below that returns all artists that have more than one album
     */
    void selectAllArtistsWithMoreThanOneAlbum(){
        List<Map<String, Object>> results = executeSQL(
                "SELECT artists.Name, albums.Title\n" +
                        "FROM artists\n" +
                        "         JOIN albums on artists.ArtistId = albums.ArtistId\n" +
                        "GROUP BY albums.ArtistId\n" +
                        "HAVING COUNT(albums.AlbumId) > 1;");

        assertEquals(56, results.size());
        assertEquals("AC/DC", results.get(0).get("Name"));
    }

    @Test
        /*
         * Write a query in the string below that returns all tracks longer than six minutes along with the
         * album and artist name
         */
    void selectTheTrackAndAlbumAndArtistForAllTracksLongerThanSixMinutes() {
        List<Map<String, Object>> results = executeSQL(
                "SELECT tracks.Name as TrackName,\n" +
                        "       --albums.Title as AlbumTitle,\n" +
                        "       --artists.Name as ArtistsName,\n" +
                        "       tracks.Milliseconds\n" +
                        "FROM tracks\n" +
                        "--JOIN albums a on artists.ArtistId = a.ArtistId\n" +
                        "--JOIN artists a2 on a2.ArtistId = a.ArtistId\n" +
                        "--JOIN tracks t on a.AlbumId = t.AlbumId\n" +
                        "--GROUP BY albums.Title, tracks.Name, artists.Name\n" +
                        "WHERE tracks.Milliseconds > 1000 * 60 * 6;" +
                        "-- NEED TO DO SOME JOINS HERE KIDS");

        assertEquals(623, results.size());

        // For now just get the count right, we'll do more elaborate stuff when we get
        // to ORDER BY
        //
        //
//        assertEquals("Princess of the Dawn", results.get(0).get("TrackName"));
//        assertEquals("Restless and Wild", results.get(0).get("AlbumTitle"));
//        assertEquals("Accept", results.get(0).get("ArtistsName"));
//
//        assertEquals("Snoopy's search-Red baron", results.get(10).get("TrackName"));
//        assertEquals("The Best Of Billy Cobham", results.get(10).get("AlbumTitle"));
//        assertEquals("Billy Cobham", results.get(10).get("ArtistsName"));

    }

}
