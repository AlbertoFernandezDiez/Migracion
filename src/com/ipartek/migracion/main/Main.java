package com.ipartek.migracion.main;

import java.io.FileNotFoundException;

import com.ipartek.migracion.excepciones.FileReaderException;
import com.ipartek.migracion.modelo.MyFileReader;

public class Main {

	public static void main(String[] args) {

		String path = "Data/personas.txt";
		
		if (args.length > 0){
			path = args[0];
		}
		
		MyFileReader mfr = null;
		try {
			mfr = new MyFileReader(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String linea = null;
		while((linea = mfr.leerLinea()) != null){
			System.out.println(linea);
		}

	}

}
