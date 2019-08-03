package com.scdevs.helpyourshelf;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.scdevs.helpyourshelf.BooksAPI.BooksResult;
import com.scdevs.helpyourshelf.BooksAPI.Item;
import com.scdevs.helpyourshelf.BooksAPI.VolumeInfo;
import com.scdevs.helpyourshelf.DBModels.BookDao;
import com.scdevs.helpyourshelf.DBModels.BookShelf;
import com.scdevs.helpyourshelf.DBModels.DaoSession;
import com.scdevs.helpyourshelf.DBModels.Volume;
import com.scdevs.helpyourshelf.DBModels.VolumeDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

	responseCallbackListener responseListener;
	BooksInterface booksInterface;
	public DaoSession daoSession;
	VolumeDao volDao;

	public APIClient(Context context)
	{
		Retrofit builder = new Retrofit.Builder()
				.baseUrl("https://www.googleapis.com/books/v1/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		booksInterface = builder.create(BooksInterface.class);
		daoSession = ((App) context).daoSession;
		volDao = daoSession.getVolumeDao();
	}


	public APIClient(responseCallbackListener r, Context context)
	{
		Retrofit builder = new Retrofit.Builder()
				.baseUrl("https://www.googleapis.com/books/v1/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		booksInterface = builder.create(BooksInterface.class);
		responseListener = r;
		daoSession = ((App) context).daoSession;
		volDao = daoSession.getVolumeDao();
	}

	public void getShelfByTitles(List<String>titles){
		BookShelf bks = new BookShelf(null, "Bookshelf", "default bookshelf");
		daoSession.getBookShelfDao().insert(bks);
		for(String s: titles){
			getBookByTitle(s);
		}
	}

	public void getBookByTitle(String title)
	{
		Call<BooksResult> call = booksInterface.getBooks(title, "AIzaSyBjz1Zdri5qruEOwT3-uRvg613pXtFzFwM");
		call.enqueue(new Callback<BooksResult>() {
			@Override
			public void onResponse(Call<BooksResult> call, Response<BooksResult> response) {
				if (response.isSuccessful())
				{
					if (response.body().getItems().size() > 0) {
						volDao.insert(volumeInfoToVolume(response.body().getItems().get(0).getVolumeInfo()));
					}
					responseListener.onCallback(response.body().getItems().get(0).getVolumeInfo(),daoSession.getBookShelfDao().loadAll().get(daoSession.getBookShelfDao().loadAll().size()-1).getID());
				}
				else{
					System.out.println("Unsuccessful");
				}
			}
			@Override
			public void onFailure(Call<BooksResult> call, Throwable t) {
				System.out.println("Failure");
			}
		});
		System.out.println("done");
	}

	public Volume volumeInfoToVolume(VolumeInfo volInfo)
	{
		Volume vol = new Volume();
		vol.setAllowAnonLogging(volInfo.getAllowAnonLogging());
		vol.setAuthors2(volInfo.getAuthors());
		vol.setAverageRating(volInfo.getAverageRating());
		vol.setCanonicalVolumeLink(volInfo.getCanonicalVolumeLink());
		vol.setCategories2(volInfo.getCategories());
		vol.setContentVersion(volInfo.getContentVersion());
		vol.setDescription(volInfo.getDescription());
		vol.setImageLinks2(volInfo.getImageLinks());
		vol.setIndustryIdentifiers2(volInfo.getIndustryIdentifiers());
		vol.setInfoLink(volInfo.getInfoLink());
		vol.setLanguage(volInfo.getLanguage());
		vol.setMaturityRating(volInfo.getMaturityRating());
		vol.setPageCount(volInfo.getPageCount());
		vol.setPanelizationSummary2(volInfo.getPanelizationSummary());
		vol.setPreviewLink(volInfo.getPreviewLink());
		vol.setPrintType(volInfo.getPrintType());
		vol.setPublishedDate(volInfo.getPublishedDate());
		vol.setPublisher(volInfo.getPublisher());
		vol.setRatingsCount(volInfo.getRatingsCount());
		vol.setReadingModes2(volInfo.getReadingModes());
		vol.setSubtitle(volInfo.getSubtitle());
		vol.setTitle(volInfo.getTitle());
		return vol;
	}

	public void getRecommendations(ArrayList<Volume> allVolumes)
	{
		HashSet<String> authors = new HashSet<>();
		for (int i = 0; i < allVolumes.size(); i++)
		{
			List<String> authorName = allVolumes.get(i).getAuthors2();
			for (int j = 0; j < authorName.size(); j++)
			{
				if (authors.contains(authorName.get(j)))
					continue;
				Call<BooksResult> call = booksInterface.getBooks(authorName.get(j), "AIzaSyBjz1Zdri5qruEOwT3-uRvg613pXtFzFwM");
				call.enqueue(new Callback<BooksResult>() {
					@Override
					public void onResponse(Call<BooksResult> call, Response<BooksResult> response) {
						if (response.isSuccessful())
						{
							List<Item> list = response.body().getItems();
							ArrayList<BookHolder> volList = new ArrayList<>();
							for (int i = 0; i < Math.min(list.size(), 25); i++)
								volList.add(new BookHolder(volumeInfoToVolume(list.get(i).getVolumeInfo())));
							Collections.sort(volList);
							responseListener.onCallback(volList);
						}
					}

					@Override
					public void onFailure(Call<BooksResult> call, Throwable t) {

					}
				});
				authorName.add(authorName.get(j));
			}
		}
	}

	public interface responseCallbackListener
	{
		public void onCallback(ArrayList<BookHolder> response);
		public void onCallback(VolumeInfo s, Long bksid);

		void onCallback(String s);
	}

}

class BookHolder implements Comparable<BookHolder>
{
	Volume vol;
	public BookHolder(Volume vol)
	{
		this.vol = vol;
	}

	@Override
	public int compareTo(BookHolder o) {
		if (vol.getAverageRating() != o.vol.getAverageRating())
			return (int) (o.vol.getAverageRating() * 100 - vol.getAverageRating() * 100);
		else
			return vol.getAuthors().compareTo(o.vol.getAuthors());
	}
}
