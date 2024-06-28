package fr.eliott.app.car_a_mel;

import java.util.ArrayList;
import java.util.Scanner;

import fr.eliott.app.car_a_mel.models.entities.Car;
import fr.eliott.app.car_a_mel.services.CarService;

public class App {
	public static void main(String[] args) {
		CarService carService = new CarService();

		ArrayList<Car> carList = new ArrayList<Car>();
		carList.addAll(carService.retrieveCarsFromFile());

		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println("Tapez 1 pour afficher les voitures du parc automobile :"
						+ "\nTapez 2 pour ajouter une voiture :" + "\nTapez 3 pour modifier une voiture :"
						+ "\nTapez 4 pour supprimer une voiture:");

				int userChoice = Integer.parseInt(scanner.nextLine());
				if (userChoice == 1) {
					carService.showCarList(carList);
				} else if (userChoice == 2) {
					carList = (ArrayList<Car>) carService.addCarAndSaveFile(scanner, carList);
					System.out.println("La voiture a bien été enregistré ");
				} else if (userChoice == 3) {
					carList = (ArrayList<Car>) carService.modifCar(scanner, carList);
				} else if (userChoice == 4) {
					carList = (ArrayList<Car>) carService.deleteCar(scanner, carList);

				}

				System.out.println("\n");

			}
		}
	}
}
