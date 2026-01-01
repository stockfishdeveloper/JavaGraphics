import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Mesh {
	ArrayList<Point> vertices;
	ArrayList<Triangle> triangles;
	Color color;

	public Mesh() {

	}

	public Mesh(String filename, Color c, double scalefactor) throws IOException {
		vertices = new ArrayList<>();
		triangles = new ArrayList<>();
		BufferedReader buffer = new BufferedReader(new FileReader(filename));
		color = c;
		String line;
		
		while ((line = buffer.readLine()) != null) {
			String[] values = line.split(" ");
			
			if (values[0].equals("v")) {
				vertices.add(new Point(Double.parseDouble(values[1]) * scalefactor,
						Double.parseDouble(values[2]) * scalefactor, Double.parseDouble(values[3]) * scalefactor));
			} else if (values[0].equals("f")) {
				triangles.add(new Triangle(vertices.get(Integer.parseInt(values[1]) - 1),
						vertices.get(Integer.parseInt(values[2]) - 1), vertices.get(Integer.parseInt(values[3]) - 1),
						color));
			}
		}
		buffer.close();
	}

	public void Render(BufferedImage buf) {
		for (Triangle t : triangles) {
			t.Render(buf);
		}
	}

	public void Draw_Mesh(BufferedImage buf) {
		for (Triangle t : triangles) {
			t.Draw_Mesh(buf);
		}
	}
}
