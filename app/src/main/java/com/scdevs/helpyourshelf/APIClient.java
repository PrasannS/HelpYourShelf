package com.scdevs.helpyourshelf;

import com.scdevs.helpyourshelf.BooksAPI.BooksResult;
import com.scdevs.helpyourshelf.BooksAPI.Item;
import com.scdevs.helpyourshelf.BooksAPI.VolumeInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

	BooksInterface booksInterface;

	public APIClient()
	{
		Retrofit builder = new Retrofit.Builder()
				.baseUrl("https://www.googleapis.com/books/v1/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		booksInterface = builder.create(BooksInterface.class);
	}

	public void getBookByTitle(String title)
	{
		Call<BooksResult> call = booksInterface.getBooks(title, "AIzaSyBjz1Zdri5qruEOwT3-uRvg613pXtFzFwM");
		call.enqueue(new Callback<BooksResult>() {
			@Override
			public void onResponse(Call<BooksResult> call, Response<BooksResult> response) {
				if (response.isSuccessful())
				{
					System.out.println(response.body().getItems().get(0).getVolumeInfo().getTitle());
					System.out.println(response.body().getItems().get(0).getVolumeInfo().getAuthors());
					System.out.println(response.body().getItems().get(0).getVolumeInfo().getDescription());
					System.out.println(response.body().getItems().get(0).getVolumeInfo().getAverageRating());
				}
			}

			@Override
			public void onFailure(Call<BooksResult> call, Throwable t) {

			}
		});
	}

	public static void main(String[] args)
	{
		APIClient client = new APIClient();
		client.getBookByTitle("Frederick Douglass");
	}

}
