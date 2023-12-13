package Util;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Points {
	
	private Map<Point, Point> pointsMap;
	private Map<String, String> unicodeMap;
	
	public Points() {
		this.pointsMap = new HashMap<>();
		this.unicodeMap =  new HashMap<>();
		initializeunicodeMap();
	}
	
	
	public void addPoint(Point keyPoint, Point valuePoint) {
		pointsMap.put(keyPoint, valuePoint);
	}
	
	
	public void buildPoints(int length, int breadth, int x, int y) {
		
		Point starting_point = new Point(x,y);
		for(int i = 0; i <= length - 1; i++) {
			for(int j = 0; j <= breadth - 1; j++) {
				Point current_point = new Point(x + i, y + j);
				this.addPoint(current_point, starting_point);
			}
		}
	}
	
	public Point getPoint(Point keyPoint) {
		return pointsMap.get(keyPoint);
	}
	
	public void initializeunicodeMap() {
		unicodeMap.put("0x1F33F", "Park");
		unicodeMap.put("0x0043", "Commercial Building");
		unicodeMap.put("0x0052", "Residential Building");
		unicodeMap.put("0x004C", "Industrial Building");
		unicodeMap.put("0x2301", "Power Generator");
		unicodeMap.put("0x1F3EB", "School");
		unicodeMap.put("0x0046", "Fire Department");
		unicodeMap.put("0x0050", "Police Department");
		unicodeMap.put("0x0048", "Hospital");
	}
	
	public String getBuildingName(String unicode) {
		return unicodeMap.get(unicode);
	}
}