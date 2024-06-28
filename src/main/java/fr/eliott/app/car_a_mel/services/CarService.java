package fr.eliott.app.car_a_mel.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import fr.eliott.app.car_a_mel.models.entities.Car;
import fr.eliott.app.car_a_mel.models.enums.BrandEnum;

public class CarService {

	private static final String CARS_JSON_PATH = "src/main/resources/cars.json";
	private static final Gson GSON = new GsonBuilder().setDateFormat("MM dd yyyy").create();

	public void showCarList(ArrayList<Car> carList) {
		System.out.println(
				"\tID | Marque | Modèle | puissance | plaque d’immatriculation | date d’entrée dans le parc.\r\n" + "");
		for (Car car : carList) {
			System.out.println(car.toString());
		}
	}

	public List<Car> addCarAndSaveFile(Scanner scanner, List<Car> cars) {
		Car newCar = addCar(scanner);
		newCar.setid(cars.size() + 1);
		cars.add(newCar);
		saveListToFile(cars);
		return cars;
	}

	private Car addCar(Scanner scanner) {
		BrandEnum brand = null;
		while (brand == null) {
			System.out.println("La marque:");
			String BrandName = scanner.nextLine();
			brand = BrandEnum.findByValue(BrandName);
			if (brand == null) {
				System.out.println("Cette marque de voiture n'est pas accepté dans notre parc automobile");
			}
		}
		System.out.println("Le modèle:");
		String model = scanner.nextLine();

		System.out.println("La puissance:");
		int horsePower = Integer.parseInt(scanner.nextLine());

		System.out.println("La plaque d'immatriculation: ");
		String registration = scanner.nextLine();

		return new Car(brand, model, horsePower, registration, new Date());
	}

	public List<Car> retrieveCarsFromFile() {
		ArrayList<Car> cars = new ArrayList<>();
		try {
			JsonReader reader = new JsonReader(new FileReader(CARS_JSON_PATH));
			Type targetClassType = new TypeToken<ArrayList<Car>>() {
			}.getType();
			cars = GSON.fromJson(reader, targetClassType);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cars == null ? new ArrayList<>() : cars;
	}

	private void saveListToFile(List<Car> cars) {

		String json = GSON.toJson(cars);

		try (FileWriter writer = new FileWriter(CARS_JSON_PATH)) {
			writer.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Car> deleteCar(Scanner scanner, List<Car> cars) {
		System.out.println("Entrez l'id de la voiture que vous voulez supprimer :");
		int id = Integer.parseInt(scanner.nextLine());
		Car carToDelete = null;
		for (Car i : cars) {
			if (i.getid() == id) {
				carToDelete = i;
				break;
			}
		}

		if (carToDelete != null) {
			cars.remove(carToDelete);
		}

		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			car.setid(i + 1);
		}

		saveListToFile(cars);
		return cars;
	}

	public List<Car> modifCar(Scanner scanner, List<Car> cars) {
		System.out.println("Entrez l'id de la voiture que vous voulez modifier:");
		int id = Integer.parseInt(scanner.nextLine());
		Car carToModif = null;
		int index = foundID(cars, id) ;
		
		carToModif = cars.get(index);
	
		if (carToModif != null) {
			carToModif = modifBrand(scanner, carToModif);
			carToModif = modifModel(scanner, carToModif);
			carToModif = modifHorsePower(scanner, carToModif);
			carToModif = modifRegistration(scanner, carToModif);
	}
		cars.set(index,carToModif);
		saveListToFile(cars);
		return cars;
	}	
	


	private Car modifBrand(Scanner scanner, Car carToModif) {
		System.out.println("Voulez vous modifier la marque ?" + "\nvous devez repondre par yes ou par no");
		String userInput = scanner.nextLine();
		if (userInput.equals("yes")) {

			BrandEnum brand = null;
			while (brand == null) {
				System.out.println("La marque:");
				String BrandName = scanner.nextLine();
				brand = BrandEnum.findByValue(BrandName);
				carToModif.setBrand(brand);
				if (brand == null) {
					System.out.println("Cette marque de voiture n'est pas acceptée dans notre parc automobile");
				}
			}
		}return carToModif; 
	}

	private Car modifModel(Scanner scanner, Car carToModif) {
		System.out.println("Voulez vous modifier le modèle ?" + "\nvous devez répondre par yes ou par no");
		String userInput = scanner.nextLine();
		if (userInput.equals("yes")) {

			System.out.println("Le modèle:");
			String model = scanner.nextLine();
			carToModif.setModel(model);
		}
		return carToModif;
	}

	private Car modifHorsePower(Scanner scanner, Car carToModif) {
		System.out.println("Voulez vous modifier la puissance ?" + "\nvous devez répondre par yes ou par no");
		String userInput = scanner.nextLine();
		if (userInput.equals("yes")) {
			System.out.println("La puissance:");
			int horsePower = Integer.parseInt(scanner.nextLine());
			carToModif.setHorsePower(horsePower);
		}
		return carToModif;
	}

	private Car modifRegistration(Scanner scanner, Car carToModif) {
		System.out.println(
				"Voulez vous modifier la plaque d'immatriculation ?" + "\nvous devez répondre par yes ou par no");
		String userInput = scanner.nextLine();
		if (userInput.equals("yes")) {
			System.out.println("La plaque d'immatriculation: ");
			String registration = scanner.nextLine();
			carToModif.setRegistration(registration);
		}
		return carToModif;
	}

	private int foundID(List<Car> cars, int id) {
		int index = -1;
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getid() == id) {
				index = i;
				break;
			}
		}
		return index;
	}
}
