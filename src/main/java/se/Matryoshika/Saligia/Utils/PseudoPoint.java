/**
 * 
 */
package se.Matryoshika.Saligia.Utils;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadablePoint;
import org.lwjgl.util.WritablePoint;

/**
 * This class was created by Matryoshika Nov 5, 2016
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class PseudoPoint {
	
	/**
	 * Copied code of org.lwjgl.util.Point
	 * Changed to make use of doubles instead of integers
	 */
	
	private double x, y;

	public PseudoPoint() {
		super();
	}

	public PseudoPoint(double x, double y) {
		setLocation(x, y);
	}

	public PseudoPoint(ReadablePoint p) {
		setLocation(p);
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setLocation(ReadablePoint p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void translate(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	public void translate(ReadablePoint p) {
		this.x += p.getX();
		this.y += p.getY();
	}

	public void untranslate(ReadablePoint p) {
		this.x -= p.getX();
		this.y -= p.getY();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			PseudoPoint pt = (PseudoPoint) obj;
			return (x == pt.x) && (y == pt.y);
		}
		return super.equals(obj);
	}

	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	}

	public int hashCode() {
		double sum = x + y;
		return (int) (sum * (sum + 1) / 2 + x);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
