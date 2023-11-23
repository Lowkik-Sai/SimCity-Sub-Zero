
public class Map {

	private char[][] GameMap;
	
	public Map(int size) {
		this.GameMap = new char[size][size];
		initializeMap();
	}
	
	
	public void initializeMap() {
		
		for(int i = 0; i <= GameMap.length - 1; i++) {
			for(int j = 0; j <= GameMap[i].length - 1; j++) {
				GameMap[i][j] = ' '; // Initializing the map area to empty fields.
			}
		}
	}
	
	//Expand Map on user wish.
	public void expandMap() {
		
		
	}
	
	
	//Displays the map in the gaming terminal
	public void DisplayMap() {
		for(int i = 0; i <= GameMap.length - 1; i++) {
			for(int j = 0; j <= GameMap[i].length - 1; j++) {
				System.out.print(GameMap[i][j]);
			}
			System.out.println();
		}
	}
	
	
	//Method for checking the availability of area in the map
	public boolean isAreaAvailable(int x, int y, int area) {
		return false;
		
		
	}
	
	// Places the built object in the game map.
	public void placeObject(char[][] building, int x, int y) {
		for(int i = 0; i <= building.length - 1; i++) {
			for(int j = 0; j <= building[i].length - 1; j++) {
				if(x + i < GameMap.length && y + j < GameMap[0].length) {
					GameMap[x + i][y + j] = building[i][j];
				}
			}
		}
	}
	
	
	// Updates the game map if any object is destroyed.
	public void destroyObject(int size, int x, int y) {
		for(int i = 0; i <= size - 1; i++) {
			for(int j = 0; j <= size - 1; j++) {
				GameMap[x + i][y + j] = ' ';
			}
		}
	}
}
