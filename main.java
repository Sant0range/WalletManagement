
import java.util.ArrayList; /* clase importada de java.util sirve para crear listados capaces de cambiar de tamaño */
import java.util.Scanner; /* clase importada de java.util sirve para leer la entrada del usuario */

/* Clase Moneda */
class Moneda {

    private String nombre;
    private char simbolo;

    public Moneda(String nombre, char simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }
}

/* Clase Billetera */
class Billetera {
    private Moneda moneda;
    private double saldo;

    public Billetera(Moneda moneda) {
        this.moneda = moneda;
        this.saldo = 0.0;
    }

    public void cargarSaldo(double cantidad) {
        saldo += cantidad;
    }

    public void transferirSaldo(Billetera otraBilletera, double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            otraBilletera.cargarSaldo(cantidad);
            System.out.println("Transferencia exitosa.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public Moneda getMoneda() {
        return moneda;
    }
}

/* Clase Usuario */
class Usuario {
    private String nombreUsuario;
    private String contraseña;
    private ArrayList<Billetera> billeteras;

    public Usuario(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.billeteras = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void crearBilletera(Moneda moneda) {
        billeteras.add(new Billetera(moneda));
        System.out.println("Billetera creada con éxito.");
    }

    public ArrayList<Billetera> getBilleteras() {
        return billeteras;
    }
}

/* Clase Admin */
class Admin {
    private final String adminUser = "admin";
    private final String adminPass = "admin123";

    public boolean validarAdmin(String usuario, String contraseña) {
        return adminUser.equals(usuario) && adminPass.equals(contraseña);
    }
}

/* Clase Sistema */
class Sistema {
    private ArrayList<Moneda> monedas;
    private ArrayList<Usuario> usuarios;
    private Admin admin;
    private Scanner scanner;

    public Sistema() {
        monedas = new ArrayList<>();
        usuarios = new ArrayList<>();
        admin = new Admin();
        scanner = new Scanner(System.in);
    }


    /* Se crea el menú principal o menu inicial con 3 opciones, crear moneda, administrar billetera y salir 
     Para esto se implementa un switch case para poder escoger una opción. .
    */
    public void menuPrincipal() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Crear moneda");
            System.out.println("2. Administrar billetera");
            System.out.println("3. Salir");
            System.out.print("Digite una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuAdminMoneda();
                    break;
                case 2:
                    menuAdministrarBilletera();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema... Gracias por visitarnos");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuAdminMoneda() {
        System.out.print("Usuario admin: ");
        String usuario = scanner.next();
        System.out.print("Contraseña: ");
        String contraseña = scanner.next();

        if (admin.validarAdmin(usuario, contraseña)) {
            int opcion = 0;
            while (opcion != 3) {
                System.out.println("=== Menú de Administración de Monedas ===");
                System.out.println("1. Crear nueva moneda");
                System.out.println("2. Eliminar moneda");
                System.out.println("3. Regresar");
                System.out.print("Digite una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        crearMoneda();
                        break;
                    case 2:
                        eliminarMoneda();
                        break;
                    case 3:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("Credenciales de administrador incorrectas.");
        }
    }

    /* se define el metodo crear moneda */

    private void crearMoneda() {
        System.out.print("Nombre de la nueva moneda: ");
        String nombre = scanner.next();
        System.out.print("Símbolo de la moneda: ");
        char simbolo = scanner.next().charAt(0);
        monedas.add(new Moneda(nombre, simbolo));
        System.out.println("Moneda creada exitosamente.");
    }

    /* se define el metodo eliminar moneda donde  Se utiliza un bucle for para recorrer la lista de monedas.
    Por cada moneda, imprime su posición en la lista, junto con el nombre y símbolo de la moneda. 
    En caso de digitar una moneda que no exista o que no hayan monedas se mostrará un mensaje*/

    private void eliminarMoneda() {
        if (monedas.isEmpty()) {
            System.out.println("No hay monedas para eliminar.");
            return;
        }
        System.out.println("=== Monedas disponibles ===");
        for (int i = 0; i < monedas.size(); i++) {
            System.out.println((i + 1) + ". " + monedas.get(i).getNombre() + " (" + monedas.get(i).getSimbolo() + ")");
        }
        System.out.print("Selecciona el número de la moneda a eliminar: ");
        int indice = scanner.nextInt() - 1;

        if (indice >= 0 && indice < monedas.size()) {
            monedas.remove(indice);
            System.out.println("Moneda eliminada exitosamente.");
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private void menuAdministrarBilletera() {
        System.out.println("=== Menú de Billeteras ===");
        System.out.println("1. Ingresar a billeteras");
        System.out.println("2. Registrarse");
        System.out.println("3. Regresar");

        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                ingresarBilletera();
                break;
            case 2:
                registrarse();
                break;
            case 3:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }


    /* Para ingresar a Billetera se requiere un nombre de usuario y contraseña, creados previamente, 
    en caso de poner credenciales incorrectas saldra un mensaje de error
     */

    private void ingresarBilletera() {
        System.out.print("Nombre de usuario: ");
        String usuario = scanner.next();
        System.out.print("Contraseña: ");
        String contraseña = scanner.next();

        Usuario user = validarUsuario(usuario, contraseña);
        if (user != null) {
            menuBilletera(user);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private Usuario validarUsuario(String usuario, String contraseña) {
        for (Usuario user : usuarios) {
            if (user.getNombreUsuario().equals(usuario) && user.getContraseña().equals(contraseña)) {
                return user;
            }
        }
        return null;
    }

     /* El menú registarse sirve para crear usuarios y asi poder acceder al menú billetera */

     private void registrarse() {
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.next();

        // Verificar si el nombre de usuario ya existe
        for (Usuario user : usuarios) {
            if (user.getNombreUsuario().equals(nombreUsuario)) {
                System.out.println("El nombre de usuario ya está en uso. Intente con otro.");
                return;
            }
        }

        System.out.print("Contraseña: ");
        String contraseña1 = scanner.next();
        System.out.print("Confirme la contraseña: ");
        String contraseña2 = scanner.next();

        // se verifica que la contraseña este correcta
        if (contraseña1.equals(contraseña2)) {
            usuarios.add(new Usuario(nombreUsuario, contraseña1));
            System.out.println("Registro exitoso.");
        } else {
            System.out.println("Las contraseñas no coinciden. Intente nuevamente.");
        }
    }

    private void menuBilletera(Usuario usuario) {
        int opcion = 0;
        while (true) { // Se usa un bucle infinito hasta que se elija regresar
            System.out.println("=== Billeteras de " + usuario.getNombreUsuario() + " ===");
            for (int i = 0; i < usuario.getBilleteras().size(); i++) {
                Moneda moneda = usuario.getBilleteras().get(i).getMoneda();
                System.out.println((i + 1) + ". Billetera de " + moneda.getNombre() + " (" + moneda.getSimbolo() + ")");
            }
            System.out.println((usuario.getBilleteras().size() + 1) + ". Crear nueva billetera");
            System.out.println((usuario.getBilleteras().size() + 2) + ". Regresar");
    
            opcion = scanner.nextInt();
    
            if (opcion == usuario.getBilleteras().size() + 1) {
                crearBilletera(usuario);
            } else if (opcion > 0 && opcion <= usuario.getBilleteras().size()) {
                menuOpcionesBilletera(usuario.getBilleteras().get(opcion - 1), usuario);
            } else if (opcion == usuario.getBilleteras().size() + 2) {
                System.out.println("Regresando al menú principal...");
                break; // Se rompe el bucle y se regresa al menú principal
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
    private void crearBilletera(Usuario usuario) {
        if (monedas.isEmpty()) {
            System.out.println("No hay monedas disponibles para crear una billetera.");
            return;
        }
        System.out.println("=== Monedas disponibles ===");
        for (int i = 0; i < monedas.size(); i++) {
            System.out.println((i + 1) + ". " + monedas.get(i).getNombre() + " (" + monedas.get(i).getSimbolo() + ")");
        }
        System.out.print("Selecciona el número de la moneda para crear una billetera: ");
        int indice = scanner.nextInt() - 1;

        if (indice >= 0 && indice < monedas.size()) {
            usuario.crearBilletera(monedas.get(indice));
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private void menuOpcionesBilletera(Billetera billetera, Usuario usuario) {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("=== Opciones de Billetera ===");
            System.out.println("1. Ver saldo");
            System.out.println("2. Cargar saldo");
            System.out.println("3. Transferir saldo");
            System.out.println("4. Regresar");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: " + billetera.getSaldo() + " " + billetera.getMoneda().getSimbolo());
                    break;
                case 2:
                    System.out.print("Cantidad a cargar: ");
                    double cantidad = scanner.nextDouble();
                    billetera.cargarSaldo(cantidad);
                    System.out.println("Saldo cargado con éxito.");
                    break;
                case 3:
                    if (usuario.getBilleteras().size() < 2) {
                        System.out.println("No tienes suficientes billeteras para transferir saldo.");
                    } else {
                        transferirSaldo(usuario, billetera);
                    }
                    break;
                case 4:
                    System.out.println("Regresando...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /* Este metodo sirve para tranferir saldo de una cuenta a otra, primero solicitando la cantidad a tranferir */
    private void transferirSaldo(Usuario usuario, Billetera billeteraOrigen) {
        System.out.print("Cantidad a transferir: ");
        double cantidad = scanner.nextDouble();

        if (cantidad > billeteraOrigen.getSaldo()) {
            System.out.println("Saldo insuficiente en la billetera de origen.");
            return;
        }
         
        /* Se solicita seleccionar la billetera de destino */
        System.out.println("=== Selecciona la billetera destino ===");
        for (int i = 0; i < usuario.getBilleteras().size(); i++) {
            Billetera billetera = usuario.getBilleteras().get(i);
            if (!billetera.equals(billeteraOrigen)) {
                System.out.println((i + 1) + ". Billetera de " + billetera.getMoneda().getNombre() + " (" + billetera.getMoneda().getSimbolo() + ")");
            }
        }

        /*Se le pide al usuario que seleccione la billetera destino de la lista mostrada: */
        System.out.print("Número de billetera destino: ");
        int indiceDestino = scanner.nextInt() - 1;

        /*Se verifica si la billetera seleccionada es válida (que no sea la billetera de origen, que esté en un rango válido de la lista de billeteras) */
        if (indiceDestino < 0 || indiceDestino >= usuario.getBilleteras().size() || usuario.getBilleteras().get(indiceDestino).equals(billeteraOrigen)) {
            System.out.println("Opción no válida.");
            return;
        }


        /*Si todo es válido, se obtiene la billetera destino y se realiza la transferencia usando el método transferirSaldo de la billetera origen: */
        Billetera billeteraDestino = usuario.getBilleteras().get(indiceDestino);
        billeteraOrigen.transferirSaldo(billeteraDestino, cantidad);
    }
}

/*  Se ejecuta todo el codigo Main */


public class main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.menuPrincipal();
    }
}
