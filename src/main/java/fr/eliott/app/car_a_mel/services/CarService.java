package fr.eliott.app.car_a_mel.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import fr.eliott.app.car_a_mel.models.entities.Car;
import fr.eliott.app.car_a_mel.models.enums.BrandEnum;

public class CarService {

	public void showCarList(ArrayList<Car> carList) {
		System.out.println(
				"\tMarque | Modèle | puissance | plaque d’immatriculation | date d’entrée dans le parc.\r\n" + "");
		for (Car car : carList) {
			System.out.println(car.toString());
		}
	}

	public Car addCar(Scanner scanner) {
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
		Gson gson = new Gson();
		try {
			JsonReader reader = new JsonReader(new FileReader("src/main/resources/cars.json"));
			Type targetClassType = new TypeToken<ArrayList<Car>>() { }.getType();
			cars = gson.fromJson(reader, targetClassType);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cars;

	}

}
