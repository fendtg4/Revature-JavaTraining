package comparable_comparator_interfaces_test;

import java.util.ArrayList;
import java.util.Collections;

public class Movie implements Comparable<Movie>{
	
	String movieName;
	String director;
	int releaseYear;
	long boxOffice;
	public Movie(String movieName, String director, int releaseYear, long boxOffice) {
		super();
		this.movieName = movieName;
		this.director = director;
		this.releaseYear = releaseYear;
		this.boxOffice = boxOffice;
	}
	//comparing release date
	/*
	@Override
	public int compareTo(Movie movie) {
		if (this.releaseYear == movie.releaseYear)
			return 0;
		else if (this.releaseYear>movie.releaseYear)
			return 1;
		else 
			return -1;
	}
	*/
	
	@Override
	public int compareTo(Movie movie) {
		if (this.boxOffice == movie.boxOffice)
			return 0;
		else if (this.boxOffice>movie.boxOffice)
			return 1;
		else 
			return -1;
	}
	public static void main(String[] args) {
		
		ArrayList<Movie> ml = new ArrayList<Movie>();
		
		ml.add(new Movie("Titanic", "James Cameron", 1997, 2195170133L));
		ml.add(new Movie("Avatar", "James Cameron", 2009, 2790439092L));
		ml.add(new Movie("Jurassic World", "Colin Trevorrow", 2015, 1670401444L));
		ml.add(new Movie("Avengers: Endgame", "Russo Brothers", 2019, 2797800564L)); 
		
		
		Collections.sort(ml);
		for (Movie str:ml) {
			System.out.println(str.movieName + " " + str.director + " " + str.releaseYear + " " + str.boxOffice);
		}
	}
	
	
}
