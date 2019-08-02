package com.scdevs.helpyourshelf;

import com.scdevs.helpyourshelf.BooksAPI.BooksResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksInterface {

	@GET("volumes")
	Call<BooksResult> getBooks(@Query("q") String title, @Query("key") String apiKey);
}
