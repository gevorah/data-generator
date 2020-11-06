package model;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;
import datastructure.*;
import javafx.scene.image.Image;

public class Generator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AVL<String, Person> people;
	public static HashMap<String, Image> profiles;
	private ArrayList<Person> tmp;

	public Generator() {
		people = new AVL<String, Person>();
		tmp = new ArrayList<Person>();
	}

	public void add( String name, String lastName, String gender, String birthdate, String height,
			String nationality) {

		String key= codeGenerator();
		people.put(key, new Person(key, name, lastName, gender, birthdate, height, nationality));
	}

	public void addNumberOfUsers(Integer toAdd) throws FileNotFoundException, IOException {
		List<String> list = nationalityGenerator(toAdd);
		int k=0;
		for (int i = 1; i<tmp.size() && k<toAdd; i++) {
			String[] country = list.get(i).split(",");
			int percent = Integer.parseInt(country[1]);
			int j=0;
			while(j++<percent) {
				String key = codeGenerator();
				String[] method = nameGenerator().split(" ");
				String name = method[0];
				String lastName = method[1];
				people.put(key,new Person(key, name, lastName, genderGenerator(), birthDateGenerator(), heightGenerator(), country[0]));
				k++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Generator g = new Generator();
		g.addNumberOfUsers(1000);
	}
	public static String codeGenerator() {

		int code = (int) (Math.random() * 1000000000);

		return String.valueOf(code);
	}

	public void imageGenerator(String name) {
		try {
			URL url = new URL("https://thispersondoesnotexist.com/image");
			URLConnection urlc = url.openConnection();
			urlc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			urlc.connect();
			BufferedInputStream is = new BufferedInputStream(urlc.getInputStream());
			FileOutputStream fos = new FileOutputStream("data" + File.separator + "image.jpeg");
			byte[] array = new byte[1000];
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}
			is.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		profiles.put(name, new Image("data" + File.separator + "image.jpeg"));
	}

	public final static int NL = 6781;
	public final static int SNL = 162252;
	public final static String NAMES = "data" + File.separator + "names.csv";
	public final static String SURNAMES = "data" + File.separator + "surnames.csv";

	public static String nameGenerator() throws IOException, FileNotFoundException {
		BufferedReader brn = new BufferedReader(new FileReader(NAMES));
		BufferedReader brsn = new BufferedReader(new FileReader(SURNAMES));
		String name = null;
		String surname = null;
		int i = 0;
		int n = (int) (Math.random() * NL + 1);
		int sn = (int) (Math.random() * SNL + 2);
		while (brn.readLine()!= null) {
			if (i++ == n - 1) {
				name = brn.readLine().split(",")[0];
				break;
			}
		}
		i = 0;
		while (brsn.readLine()!= null) {
			if (i++ == sn - 1) {
				surname = brsn.readLine().split(",")[0];
				break;
			}
		}
		brn.close();
		brsn.close();
		return name + " " + surname.substring(0, 1) + surname.substring(1).toLowerCase();
	}

	public static String birthDateGenerator() {
		GregorianCalendar gc = new GregorianCalendar();
		int year = randBetween(1930, LocalDate.now().getYear());
		gc.set(Calendar.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public static String heightGenerator() {
		int num = (int) (Math.random() * ((200 - 30) + 30));
		return num + " cm";
	}


	public static String genderGenerator() {
		String gender = "";
		int num = (int) (Math.random() * ((2 - 1) + 1));
		if (num == 0) {
			gender = "Female";
		} else if (num == 1) {
			gender = "Male";

		}
		return gender;
	}

	public final static String POPULATION = "data" + File.separator + "population.csv";

	public static List<String> nationalityGenerator(Integer num) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(POPULATION));
		List<String> list = new ArrayList<String>();
		String country = br.readLine();
		while (country != null) {
			String[] tmp = country.split(",");
			int population = (int)(num*Double.parseDouble(tmp[10].substring(0,2)));	
			list.add(tmp[0] + "," + (population==0?1:population));
			br.readLine();
		}
		br.close();
		return list;
	}

	public void delete(Person node) {
		people.remove(node.getCode());
	}

	public void search(String character) {

	}

	public void edit(Person toEdit, String name, String lastName, String gender, String birthdate, String height,
			String nationality) {

		if (!name.equals(toEdit.getName())) {
			toEdit.setName(name);
		}
		if (!lastName.equals(toEdit.getLastName())) {
			toEdit.setLastName(lastName);
		}
		if (!gender.equals(toEdit.getGender())) {
			toEdit.setGender(gender);;
		}
		if (!birthdate.equals(toEdit.getBirthdate())) {
			toEdit.setBirthdate(birthdate);
		}
		if (!height.equals(toEdit.getHeight())) {
			toEdit.setHeight(height);
		}
		if (!nationality.equals(toEdit.getNationality())) {
			toEdit.setNationality(nationality);
		}
	}

	public void suggestions(String parameter, String mode) {

		try {

			String[] tittles = { "code", "name", "lastName", "gender", "birthdate", "height", "nationality" };

		}

		catch (Exception e) {
		}
	}
}
