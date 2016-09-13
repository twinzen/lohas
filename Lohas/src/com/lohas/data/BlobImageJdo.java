package com.lohas.data;

public class BlobImageJdo {

	/*
	 * Blob Key
	 * The key to get the original blob
	 */
	private String blobKey;
	
	/*
	 * Image URL
	 * The static image url
	 */
	private String imageUrl;

	public String getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
