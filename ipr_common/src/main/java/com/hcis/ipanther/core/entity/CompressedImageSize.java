/**
 * 
 */
package com.hcis.ipanther.core.entity;

/**
 * @author Administrator
 *
 */
public class CompressedImageSize {
	private int width;
	
	private int height;
	public CompressedImageSize(){}
	public CompressedImageSize(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
