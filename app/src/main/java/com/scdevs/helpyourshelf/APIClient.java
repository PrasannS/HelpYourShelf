package com.scdevs.helpyourshelf;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.scdevs.helpyourshelf.BooksAPI.BooksResult;
import com.scdevs.helpyourshelf.BooksAPI.Item;
import com.scdevs.helpyourshelf.BooksAPI.VolumeInfo;
import com.scdevs.helpyourshelf.DBModels.Book;
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
		Book b;
		for(String s: titles){
			b= new Book(null,null,bks.getID(),null, 0,false, "s");
			daoSession.getBookDao().insert(b);
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
		if (volInfo.getAllowAnonLogging() != null)
		vol.setAllowAnonLogging(volInfo.getAllowAnonLogging());
		if (volInfo.getAuthors() != null)
		vol.setAuthors2(volInfo.getAuthors());
		if (volInfo.getAverageRating() != null)
		vol.setAverageRating(volInfo.getAverageRating());
		if (volInfo.getCanonicalVolumeLink() != null)
		vol.setCanonicalVolumeLink(volInfo.getCanonicalVolumeLink());
		if (volInfo.getCategories() != null)
		vol.setCategories2(volInfo.getCategories());
		if (volInfo.getContentVersion() != null)
		vol.setContentVersion(volInfo.getContentVersion());
		if (volInfo.getDescription() != null)
		vol.setDescription(volInfo.getDescription());
		if (volInfo.getImageLinks() != null)
		vol.setImageLinks2(volInfo.getImageLinks());
		if (volInfo.getIndustryIdentifiers() != null)
		vol.setIndustryIdentifiers2(volInfo.getIndustryIdentifiers());
		if (volInfo.getInfoLink() != null)
		vol.setInfoLink(volInfo.getInfoLink());
		if (volInfo.getLanguage() != null)
		vol.setLanguage(volInfo.getLanguage());
		if (volInfo.getMaturityRating() != null)
		vol.setMaturityRating(volInfo.getMaturityRating());
		if (volInfo.getPageCount() != null)
		vol.setPageCount(volInfo.getPageCount());
		if (volInfo.getPanelizationSummary() != null)
		vol.setPanelizationSummary2(volInfo.getPanelizationSummary());
		if (volInfo.getPreviewLink() != null)
		vol.setPreviewLink(volInfo.getPreviewLink());
		if (volInfo.getPrintType() != null)
		vol.setPrintType(volInfo.getPrintType());
		if (volInfo.getPublishedDate() != null)
		vol.setPublishedDate(volInfo.getPublishedDate());
		if (volInfo.getPublisher() != null)
		vol.setPublisher(volInfo.getPublisher());
		if (volInfo.getRatingsCount() != null)
		vol.setRatingsCount(volInfo.getRatingsCount());
		if (volInfo.getReadingModes() != null)
		vol.setReadingModes2(volInfo.getReadingModes());
		if (volInfo.getSubtitle() != null)
		vol.setSubtitle(volInfo.getSubtitle());
		if (volInfo.getTitle() != null)
		vol.setTitle(volInfo.getTitle());
		return vol;
	}

	public void getRecommendations(ArrayList<Volume> allVolumes) throws Exception
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
