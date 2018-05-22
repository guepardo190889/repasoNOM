package com.blackdeath.repasoNOM;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.blackdeath.repasoNOM.model.NOM;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Main {

	private static final String FILENAME = "/normas.xls";

	private static final String SIGUIENTE = "s";
	private static final String MOSTRAR = "m";
	private static final String SALIR = "0";

	private NOM[] normas;

	private Scanner scanner = new Scanner(System.in);

	private void leerExcel() {
		final int LIBRO = 0;
		final int COLUMNA_A = 0;
		final int COLUMNA_B = 1;

		ArrayList<NOM> normasList = new ArrayList<NOM>();
		Workbook workbook = null;
		try {
			InputStream archivo = Main.class.getResourceAsStream(FILENAME);
			workbook = Workbook.getWorkbook(archivo);
			Sheet sheet = workbook.getSheet(LIBRO);

			int FILAS = sheet.getRows();

			for (int i = 0; i < FILAS; i++) {
				Cell celdaNorma = sheet.getCell(COLUMNA_A, i);
				Cell celdaDefinicion = sheet.getCell(COLUMNA_B, i);

				NOM norma = new NOM(celdaNorma.getContents(), celdaDefinicion.getContents());
				normasList.add(norma);
			}

			this.normas = new NOM[normasList.size()];
			this.normas = normasList.toArray(normas);
			
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}
	}

	private int generarNumeroAleatorio(int minimo, int maximo) {
		return (int) (Math.floor(Math.random() * (maximo - minimo + 1)) + minimo);
	}

	private void mostrarMenu() {
		System.out.println("s = Siguiente NOM");
		System.out.println("m = Mostrar NOM");
		System.out.println("0 = Salir");
		System.out.println("");
	}

	private String leerRespuesta() {
		return scanner.nextLine();
	}

	private NOM obtenerNorma() {
		int posicion = generarNumeroAleatorio(0, normas.length - 1);
		return normas[posicion];
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.leerExcel();
		main.mostrarMenu();

		String respuesta = SIGUIENTE;
		NOM norma = main.obtenerNorma();

		while (true) {
			if (SIGUIENTE.equals(respuesta)) {
				norma = main.obtenerNorma();
				System.out.println(norma.getNorma());
			} else if (MOSTRAR.equals(respuesta)) {
				System.out.println(norma.getDefinicion());
			} else if (SALIR.equals(respuesta)) {
				System.out.println("Adiós");
				break;
			}

			respuesta = main.leerRespuesta();
		}
	}
}
