package com.enikbje.falling.objects.shapes;

import java.util.ArrayList;
import java.util.Iterator;

import com.enikbje.falling.objects.MovableObject;

import android.graphics.Canvas;
import android.graphics.Paint;

	public class LineLink {

	private ArrayList <Line> lines;
	
	/**
	 * Basic constructor which inizialize lines array
	 */
	public LineLink() {
		lines = new ArrayList<Line>();
	}
	
	/**
	 * Constructor if a collection of lines allready exists
	 * @param inLines
	 */
	public LineLink(ArrayList<Line> inLines) {
		lines = inLines;
	}
	
	/**
	 * Add line to collection
	 * @param inLine
	 */
	public void addLine(Line inLine) {
		lines.add(inLine);
	}
	
	/**
	 * Edit the last line in the collection
	 * @param inLine
	 */
	public void editLastLine(Line inLine) {
		lines.set(lines.size()-1, inLine);
	}
	
	/**
	 * Remove line specified by index in collection
	 * @param index
	 */
	public void removeLine(int index) {
		lines.remove(index);
	}
	
	/**
	 * Remove last line in the collection
	 */
	public void removeLastLine() {
		lines.remove(lines.size()-1);
	}

	/**
	 * Draw all lines in collection
	 * @param canvas
	 * @param paint
	 */
	public void draw(Canvas canvas, Paint paint) {

		Iterator <Line> liter = lines.iterator();
		while(liter.hasNext()) {
			MovableObject aLine = liter.next();
			aLine.draw(canvas, paint);
	}
		
	}
}
