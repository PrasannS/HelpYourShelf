
package com.scdevs.helpyourshelf.DBModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scdevs.helpyourshelf.BooksAPI.ImageLinks;
import com.scdevs.helpyourshelf.BooksAPI.IndustryIdentifier;
import com.scdevs.helpyourshelf.BooksAPI.PanelizationSummary;
import com.scdevs.helpyourshelf.BooksAPI.ReadingModes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "volume")
public class Volume {
	
	@Id(autoincrement = true)
	Long ID;
	
	@Property(nameInDb="title")
	
	private String title;
	@Property(nameInDb="authors")
	
	private String authors = null;
	@Property(nameInDb="publisher")
	
	private String publisher;
	@Property(nameInDb="publishedDate")
	
	private String publishedDate;
	@Property(nameInDb="description")
	
	private String description;
	@Property(nameInDb="industryIdentifiers")
	
	private String industryIdentifiers = null;
	@Property(nameInDb="readingModes")
	
	private String readingModes;
	@Property(nameInDb="pageCount")
	
	private int pageCount;
	@Property(nameInDb="printType")
	
	private String printType;
	@Property(nameInDb="categories")
	
	private String categories = null;
	@Property(nameInDb="maturityRating")
	
	private String maturityRating;
	@Property(nameInDb="allowAnonLogging")
	
	private boolean allowAnonLogging;
	@Property(nameInDb="contentVersion")
	
	private String contentVersion;
	@Property(nameInDb="panelizationSummary")
	
	private String panelizationSummary;
	@Property(nameInDb="imageLinks")
	
	private String imageLinks;
	@Property(nameInDb="language")
	
	private String language;
	@Property(nameInDb="previewLink")
	
	private String previewLink;
	@Property(nameInDb="infoLink")
	
	private String infoLink;
	@Property(nameInDb="canonicalVolumeLink")
	
	private String canonicalVolumeLink;
	@Property(nameInDb="subtitle")
	
	private String subtitle;
	@Property(nameInDb="averageRating")
	
	private double averageRating;
	@Property(nameInDb="ratingsCount")
	
	private int ratingsCount;

	@Generated(hash = 1319087571)
	public Volume(Long ID, String title, String authors, String publisher, String publishedDate, String description, String industryIdentifiers,
			String readingModes, int pageCount, String printType, String categories, String maturityRating, boolean allowAnonLogging,
			String contentVersion, String panelizationSummary, String imageLinks, String language, String previewLink, String infoLink,
			String canonicalVolumeLink, String subtitle, double averageRating, int ratingsCount) {
		this.ID = ID;
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.description = description;
		this.industryIdentifiers = industryIdentifiers;
		this.readingModes = readingModes;
		this.pageCount = pageCount;
		this.printType = printType;
		this.categories = categories;
		this.maturityRating = maturityRating;
		this.allowAnonLogging = allowAnonLogging;
		this.contentVersion = contentVersion;
		this.panelizationSummary = panelizationSummary;
		this.imageLinks = imageLinks;
		this.language = language;
		this.previewLink = previewLink;
		this.infoLink = infoLink;
		this.canonicalVolumeLink = canonicalVolumeLink;
		this.subtitle = subtitle;
		this.averageRating = averageRating;
		this.ratingsCount = ratingsCount;
	}

	@Generated(hash = 108289630)
	public Volume() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors2() {
		return Arrays.asList(authors.split(","));
	}

	public void setAuthors2(List<String> authors) {
		String authorString = authors.toString();
		authorString = authorString.trim().substring(1, authorString.length() - 1);
		authorString.replaceAll(", ", ",");
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<IndustryIdentifier> getIndustryIdentifiers2() {
		ArrayList<IndustryIdentifier> list = new ArrayList<>();
		String[] arr = this.industryIdentifiers.split(", ");
		for (int i = 0; i < arr.length; i++)
		{
			IndustryIdentifier put = new IndustryIdentifier();
			String[] split = arr[i].split("=-=-=-=-=-=-=");
			put.setType(split[0]);
			put.setIdentifier(split[1]);
			list.add(put);
		}
		return list;
	}

	public void setIndustryIdentifiers2(List<IndustryIdentifier> industryIdentifiers) {
		ArrayList<String> indIdentifiers = new ArrayList<>();
		for (int i = 0; i < industryIdentifiers.size(); i++)
		{
			indIdentifiers.add(industryIdentifiers.get(i).getType() + "=-=-=-=-=-=-=" + industryIdentifiers.get(i).getIdentifier());
		}
		this.industryIdentifiers = indIdentifiers.toString().substring(1, indIdentifiers.toString().length() - 1);
	}

	public ReadingModes getReadingModes2() {
		ReadingModes readingModes = new ReadingModes();
		if (this.readingModes.indexOf("true") == 0)
		{
			readingModes.setText(true);
			if (this.readingModes.indexOf("false") > 0)
				readingModes.setImage(false);
			else
				readingModes.setImage(true);
		}
		else
		{
			readingModes.setText(false);
			if (this.readingModes.indexOf("true") > 0)
				readingModes.setImage(true);
			else
				readingModes.setImage(false);
		}
		return readingModes;
	}

	public void setReadingModes2(ReadingModes readingModes) {
		this.readingModes = readingModes.getText().toString() + readingModes.getImage().toString();
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public List<String> getCategories2() {
		return Arrays.asList(categories.split(","));
	}

	public void setCategories2(List<String> categories) {
		String categoryString = categories.toString();
		categoryString = categoryString.trim().substring(1, categoryString.length() - 1);
		categoryString.replaceAll(", ", ",");
	}

	public String getMaturityRating() {
		return maturityRating;
	}

	public void setMaturityRating(String maturityRating) {
		this.maturityRating = maturityRating;
	}

	public Boolean getAllowAnonLogging() {
		return allowAnonLogging;
	}

	public void setAllowAnonLogging(Boolean allowAnonLogging) {
		this.allowAnonLogging = allowAnonLogging;
	}

	public String getContentVersion() {
		return contentVersion;
	}

	public void setContentVersion(String contentVersion) {
		this.contentVersion = contentVersion;
	}

	public PanelizationSummary getPanelizationSummary2() {
		PanelizationSummary panelizationSummary = new PanelizationSummary();
		if (this.panelizationSummary.indexOf("true") == 0)
		{
			panelizationSummary.setContainsEpubBubbles(true);
			if (this.panelizationSummary.indexOf("false") > 0)
				panelizationSummary.setContainsImageBubbles(false);
			else
				panelizationSummary.setContainsImageBubbles(true);
		}
		else
		{
			panelizationSummary.setContainsEpubBubbles(false);
			if (this.panelizationSummary.indexOf("true") > 0)
				panelizationSummary.setContainsImageBubbles(true);
			else
				panelizationSummary.setContainsImageBubbles(false);
		}
		return panelizationSummary;
	}

	public void setPanelizationSummary2(PanelizationSummary panelizationSummary) {
		if(panelizationSummary!=null);
		this.panelizationSummary = panelizationSummary.getContainsEpubBubbles().toString() + panelizationSummary.getContainsImageBubbles().toString();
	}

	public ImageLinks getImageLinks2() {
		ImageLinks imageLinks = new ImageLinks();
		String[] imageLinksArray = this.imageLinks.split("=-=-=-=-=-=-=");
		imageLinks.setSmallThumbnail(imageLinksArray[0]);
		imageLinks.setThumbnail(imageLinksArray[1]);
		return imageLinks;
	}

	public void setImageLinks2(ImageLinks imageLinks) {
		this.imageLinks = imageLinks.getSmallThumbnail() + "=-=-=-=-=-=-=" + imageLinks.getThumbnail();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPreviewLink() {
		return previewLink;
	}

	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}

	public String getInfoLink() {
		return infoLink;
	}

	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}

	public String getCanonicalVolumeLink() {
		return canonicalVolumeLink;
	}

	public void setCanonicalVolumeLink(String canonicalVolumeLink) {
		this.canonicalVolumeLink = canonicalVolumeLink;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Integer ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Long getID() {
		return this.ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setIndustryIdentifiers(String industryIdentifiers) {
		this.industryIdentifiers = industryIdentifiers;
	}

	public void setReadingModes(String readingModes) {
		this.readingModes = readingModes;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public void setAllowAnonLogging(boolean allowAnonLogging) {
		this.allowAnonLogging = allowAnonLogging;
	}

	public void setPanelizationSummary(String panelizationSummary) {
		this.panelizationSummary = panelizationSummary;
	}

	public void setImageLinks(String imageLinks) {
		this.imageLinks = imageLinks;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public String getAuthors() {
		return this.authors;
	}

	public String getIndustryIdentifiers() {
		return this.industryIdentifiers;
	}

	public String getReadingModes() {
		return this.readingModes;
	}

	public String getCategories() {
		return this.categories;
	}

	public String getPanelizationSummary() {
		return this.panelizationSummary;
	}

	public String getImageLinks() {
		return this.imageLinks;
	}

}
