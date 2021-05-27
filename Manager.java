
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

public class Manager {

	//private Vehiculo[] vehiculos;
	private Set<Vehiculo> vehiculos;
	private Scanner scanner;
	private boolean exitToPrincipalMenu;
	private boolean exitMenuVehicle;
	private int positionAdd;

	public Manager() {
		//this.vehiculos = new Vehiculo[10];
		this.vehiculos =  new HashSet<Vehiculo>();
		scanner = new Scanner(System.in);
		exitToPrincipalMenu = false;
		exitMenuVehicle = false;
		positionAdd = 0;
	}

	private void AddData(Vehiculo vehiculo) {
		//vehiculos[positionAdd] = vehiculo;
		vehiculos.add(vehiculo);
		positionAdd++;
	}

	private void showGaraje() {
        if (vehiculos.size() == 0) {
            System.out.println("\n\nGaraje vacio");
            return;
        } 
        Iterator<Vehiculo> i = vehiculos.iterator();
        while(i.hasNext()) 
        	System.out.println(i.next().toString());
        
		/*for (int i = 0; i < vehiculos.length; i++) {
			if (vehiculos[i] != null) {
				System.out.println((i + 1) + " " + vehiculos[i].toString());
			}
        }*/
        System.out.println("\n\nPresione Enter para volver al menu principal");
        scanner.nextLine();
	}
	private void ordenarPorVelocidad() {
		if(vehiculos.size() > 0) {
			int i;
			ArrayList<Vehiculo> ordenados = new ArrayList<Vehiculo>(vehiculos);
			/*for(i = 0; i < ordenados.size();i++) 
				System.out.println(ordenados.get(i).toString());*/
			
			
			
			Collections.sort(ordenados,new Comparator<Vehiculo>() {
			@Override
			public int compare(Vehiculo v1,Vehiculo v2) {
				return v1.getVelocidadMaxima() - v2.getVelocidadMaxima();
			}
			
			});
			
			for(i = 0; i < ordenados.size();i++) 
				System.out.println(ordenados.get(i).toString());
			
			
		}
	}
	private void maxVelocidadGaraje() {
		if(vehiculos.size() > 0) {
			Vehiculo vehiculo = Collections.max(vehiculos,new Comparator<Vehiculo>() {
				@Override
				public int compare(Vehiculo v1,Vehiculo v2) {
					int res = 0;
					if(v1.getVelocidadMaxima() > v2.getVelocidadMaxima())
						res = 1;
					else if(v1.getVelocidadMaxima() < v2.getVelocidadMaxima())
						res = -1;
					return res;
					
				}
			});
			
			System.out.println(vehiculo.toString());
			
			
		}
		
		
	}
	
	private void minVelocidadGaraje() {
		if(vehiculos.size() > 0) {
			Vehiculo vehiculo = Collections.max(vehiculos,new Comparator<Vehiculo>() {
				@Override
				public int compare(Vehiculo v1,Vehiculo v2) {
					int res = 0;
					if(v1.getVelocidadMaxima() > v2.getVelocidadMaxima())
						res = -1;
					else if(v1.getVelocidadMaxima() < v2.getVelocidadMaxima())
						res = 1;
					return res;
					
				}
			});
			
			System.out.println(vehiculo.toString());
			
			
		}
		
	}
	private void vehiculozAzulNegro() {
		if(vehiculos.size() > 0) {
			int i;
			List<Vehiculo> vColores = vehiculos.stream().filter(v -> (v.getColor() == ColorEnum.NEGRO || v.getColor() == ColorEnum.AZUL)).collect(Collectors.toList());
			i = 0;
			for(i = 0; i < vColores.size();i++)
				System.out.println(vColores.get(i).toString());
			
		}
	}
	private void concatenarString() {
		if(vehiculos.size() > 0) {
			int i;
			String s = "";
			List<Vehiculo> vColores = vehiculos.stream().filter(v -> (v.getColor() == ColorEnum.BLANCO || v.getColor() == ColorEnum.VERDE)).collect(Collectors.toList());
			
			for(i = 0; i < vColores.size();i++)
				s += vColores.get(i).toString()+"\n";
			
			System.out.println(s);
			
		}
		
	}
	public void menuPrincipal() {
		while (!exitToPrincipalMenu) {
            int op = obtenerNumero("Bienvenido \n Dijita una opcion:  \n 1.Agregar vehiculo \n 2.mostrar garaje\n 3.Mostrar vehiculo de maxima velocidad\n 4.Mostrar vehiculo de menor velocidad\n 5.Mostrar ordenados por velocidad\n 6.Mostrar vehiculos de color negro o azul\n 7.Concatenar en un string las referencias de vehiculos blancos o verdes\n 8.salir");
			switch (op) {
			case 1:
				if (positionAdd < 10) {
					//System.out.println("entro aca");
					menuVehiculo();
				} else {
					System.out.println("El garaje esta lleno");
				}
                break;
                
			case 2:
				showGaraje();
                break;
               
			case 3:
				maxVelocidadGaraje();
				break;
			case 4:
				minVelocidadGaraje();
				break;
				
			case 5:
				ordenarPorVelocidad();
				break;
             
			case 6:
				vehiculozAzulNegro();
				break;
			case 7:
				concatenarString();
				break;
			case 8:
				System.out.println("hasta pronto");
				exitToPrincipalMenu = true;
                break;

            default:
                System.out.println("INVALIDO");
                System.out.println("[Opcion inexistente]");
                break;
			}
		}
	}

	private void menuVehiculo() {
        //System.out.println("holiwi");
        String tipo;
        BuilderAvion tempA;
        BuilderYate tempY;
        BuilderCarro tempC;

		while (!exitMenuVehicle) {
			System.out.println("Menu registrar vehiculo");
			System.out.println("a.- avion");
			System.out.println("b.- yate");
			System.out.println("c.- carro");
			System.out.println("d.- salir");

			String opciones2 = scanner.nextLine().toLowerCase().trim();
			switch (opciones2) {
                case "a":
                    tipo = "avion";

                    tempA = new BuilderAvion();

                    tempA.colorVehiculo(menuColor());

                    System.out.print("Referencia del " + tipo + ": ");
                    tempA.refVehiculo(scanner.nextLine());

                    tempA.velMaxVehiculo(obtenerNumero("Digite velocidad del " + tipo));

                    tempA.altitudVehiculo(obtenerNumero("Digita altura maxima del " + tipo));

                    AddData(tempA.build());
                    //String tipo = "avion";

                    //BuilderAvion temp = (BuilderAvion)new BuilderVehiculo();


                    /*
                    temp.colorVehiculo(menuColor());

                    System.out.print("Referencia del " + tipo + ": ");
                    temp.refVehiculo(scanner.nextLine());

                    temp.velMaxVehiculo(obtenerNumero("Digte velocidad del " + tipo));*/

                    //temp.altitudVehiculo(obtenerNumero("Digita altura maxima del Avion"));
                    /*BuilderAvion v = (BuilderAvion)crearVehiculo("Avion");

                    v.altitudVehiculo(obtenerNumero("Digita altura maxima del Avion"));
                    
                    AddData(v.build());*/
                    break;

                case "b":
                    tipo = "yate";

                    tempY = new BuilderYate();

                    tempY.colorVehiculo(menuColor());

                    System.out.print("Referencia del " + tipo + ": ");
                    tempY.refVehiculo(scanner.nextLine());

                    tempY.velMaxVehiculo(obtenerNumero("Digite velocidad del " + tipo));

                    tempY.pesoMaximo(obtenerNumero("Digite peso maximo del " + tipo));

                    AddData(tempY.build());
                    break;

                case "c":
                    tipo = "carro";

                    tempC = new BuilderCarro();

                    tempC.colorVehiculo(menuColor());

                    System.out.print("Referencia del " + tipo + ": ");
                    tempC.refVehiculo(scanner.nextLine());

                    tempC.velMaxVehiculo(obtenerNumero("Digite velocidad del " + tipo));

                    tempC.numeroPuertas(obtenerNumero("Digite numero de puertas"));

                    AddData(tempC.build());
                    break;

                case "d":
                    exitMenuVehicle = true;
                    break;
                
                default:
                    System.out.println("INVALIDO");
                    System.out.println("[Opcion no valida]");
                    break;
			}
		}
	}

	private ColorEnum menuColor() {
        int op;
        ColorEnum colorEnum = null;
        do {
            op = obtenerNumero("Seleccione color del vehiculo:\n1.- Azul\n2.- Blanco\n3.- Gris\n4.- Negro\n5.- Verde");
            switch (op) {
                case 1:
                    colorEnum = ColorEnum.AZUL;
                    break;
                case 2:
                    colorEnum = ColorEnum.BLANCO;
                    break;
                case 3:
                    colorEnum = ColorEnum.GRIS;
                    break;
                case 4:
                    colorEnum = ColorEnum.NEGRO;
                    break;
                case 5:
                    colorEnum = ColorEnum.VERDE;
                    break;
                default:
                    System.out.println("INVALIDO");
                    System.out.println("[Opcion no valida]");
                    break;
            }
        } while (op < 1 || op > 5);
		return colorEnum;
    }

    public int obtenerNumero(String mensaje) {
        while (true) {
            limpiarConsola(1);
            System.out.print("\n" + mensaje + "\n> ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception error) {
                System.out.println("INVALIDO");
                System.out.println("[No se puedo leer numero entero]");
            }
        }
    }

    public static void limpiarConsola(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception error) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.menuPrincipal();
	}
}