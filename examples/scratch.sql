select * from main.customers
where SupportRepId=3 AND
      CustomerId IN (SELECT CustomerId from invoices
          JOIN invoice_items ii on invoices.InvoiceId = ii.InvoiceId
          JOIN tracks t on ii.TrackId = t.TrackId
          JOIN  genres g on t.GenreId = g.GenreId
          WHERE g.Name == "Rock");

/*
* Create a view tracksPlus to display the artist, song title, album, and genre for all tracks.
*/

CREATE VIEW tracksPlus AS SELECT artists.Name as ArtistName, tracks.name as SongTitle,
                    albums.title as AlbumTitle, genres.Name FROM tracks, artists, albums, genres;
