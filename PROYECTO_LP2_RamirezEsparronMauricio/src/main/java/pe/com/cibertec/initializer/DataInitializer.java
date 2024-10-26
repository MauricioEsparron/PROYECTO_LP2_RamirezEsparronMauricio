package pe.com.cibertec.initializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cibertec.model.entity.CatetgoriaProductoEntity;
import pe.com.cibertec.model.entity.CuentaEntity;
import pe.com.cibertec.model.entity.EstadoCuentaEntity;
import pe.com.cibertec.model.entity.EstadoTarjetaEntity;
import pe.com.cibertec.model.entity.OperacionEntity;
import pe.com.cibertec.model.entity.ProductoEntity;
import pe.com.cibertec.model.entity.TarjetaEntity;
import pe.com.cibertec.model.entity.TipoCuentaEntity;
import pe.com.cibertec.model.entity.TipoOperacionEntity;
import pe.com.cibertec.model.entity.TipoTarjetaEntity;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.repository.CategoriaProductoRepository;
import pe.com.cibertec.repository.CuentaRepository;
import pe.com.cibertec.repository.EstadoCuentaRepository;
import pe.com.cibertec.repository.EstadoTarjetaRepository;
import pe.com.cibertec.repository.OperacionRepository;
import pe.com.cibertec.repository.ProductoRepository;
import pe.com.cibertec.repository.TarjetaRepository;
import pe.com.cibertec.repository.TipoCuentaRepository;
import pe.com.cibertec.repository.TipoOperacionRepository;
import pe.com.cibertec.repository.TipoTarjetaRepository;
import pe.com.cibertec.repository.UsuarioRepository;
import pe.com.cibertec.utils.Utilitarios;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaProductoRepository categoriaProductoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private TipoTarjetaRepository tipoTarjetaRepository;

	@Autowired
	private EstadoTarjetaRepository estadoTarjetaRepository;

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private TipoCuentaRepository tipoCuentaRepository;

	@Autowired
	private EstadoCuentaRepository estadoCuentaRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private TipoOperacionRepository tipoOperacionRepository;

	@Autowired
	private OperacionRepository operacionRepository;

	@Override
	public void run(String... args) throws Exception {
		insertarDatosIniciales();

	}

	private void insertarDatosIniciales() {

		LocalDate fechaActual = LocalDate.now();

		UsuarioEntity user1 = new UsuarioEntity();
		user1.setCorreo("mau@gmail.com");
		user1.setPassword(Utilitarios.extraerHash("12345")); // Hashear la contraseña
		user1.setNombre("Mauricio");
		user1.setApellido("Ramirez");
		user1.setFechaNacimiento("02/07/2003");
		user1.setUrlImagen("https://mx.pinterest.com/pin/632474341424086584/");

		UsuarioEntity user2 = new UsuarioEntity();
		user2.setCorreo("sofi@gmail.com");
		user2.setPassword(Utilitarios.extraerHash("12345")); // Hashear la contraseña
		user2.setNombre("Sofi");
		user2.setApellido("Castro");
		user2.setFechaNacimiento("02/07/2003");
		user2.setUrlImagen("https://mx.pinterest.com/pin/31666003621401263/");

		UsuarioEntity user3 = new UsuarioEntity();
		user3.setCorreo("user@gmail.com");
		user3.setPassword(Utilitarios.extraerHash("54321")); // Hashear la contraseña
		user3.setNombre("Alexa");
		user3.setApellido("Diaz");
		user3.setFechaNacimiento("02/07/2003");
		user3.setUrlImagen("https://es.pinterest.com/pin/144467100542637874/");

		usuarioRepository.save(user1);
		usuarioRepository.save(user2);
		usuarioRepository.save(user3);

		CatetgoriaProductoEntity cat1 = new CatetgoriaProductoEntity();
		cat1.setNombre("MEDICINA");
		CatetgoriaProductoEntity cat2 = new CatetgoriaProductoEntity();
		cat2.setNombre("LACTEOS");
		CatetgoriaProductoEntity cat3 = new CatetgoriaProductoEntity();
		cat3.setNombre("ENLATADO");
		CatetgoriaProductoEntity cat4 = new CatetgoriaProductoEntity();
		cat4.setNombre("GOLOSINA");

		categoriaProductoRepository.save(cat1);
		categoriaProductoRepository.save(cat2);
		categoriaProductoRepository.save(cat3);
		categoriaProductoRepository.save(cat4);

		ProductoEntity prod1 = new ProductoEntity();
		prod1.setNombre("LECHE GLORIA");
		prod1.setPrecio(23.50);
		prod1.setStock(40);
		prod1.setCategoriaProducto(cat2);

		ProductoEntity prod2 = new ProductoEntity();
		prod2.setNombre("ATÚN FLORIDA");
		prod2.setPrecio(7.50);
		prod2.setStock(60);
		prod2.setCategoriaProducto(cat3);

		ProductoEntity prod3 = new ProductoEntity();
		prod3.setNombre("CHOCOLATE");
		prod3.setPrecio(20.30);
		prod3.setStock(12);
		prod3.setCategoriaProducto(cat4);

		ProductoEntity prod4 = new ProductoEntity();
		prod4.setNombre("PARACETAMOL");
		prod4.setPrecio(12.30);
		prod4.setStock(100);
		prod4.setCategoriaProducto(cat1);

		productoRepository.save(prod1);
		productoRepository.save(prod2);
		productoRepository.save(prod3);
		productoRepository.save(prod4);

		// Crear e insertar tipos de tarjeta
		TipoTarjetaEntity tarjetaCredito = new TipoTarjetaEntity();
		tarjetaCredito.setDescripcion("Tarjeta de Crédito Básica");
		tarjetaCredito.setLimiteCredito(1000.0);
		tarjetaCredito.setIntereses(15.0); // 15% de interés

		TipoTarjetaEntity tarjetaPremium = new TipoTarjetaEntity();
		tarjetaPremium.setDescripcion("Tarjeta de Crédito Premium");
		tarjetaPremium.setLimiteCredito(5000.0);
		tarjetaPremium.setIntereses(10.0); // 10% de interés

		TipoTarjetaEntity tarjetaDebito = new TipoTarjetaEntity();
		tarjetaDebito.setDescripcion("Tarjeta de Débito");
		tarjetaDebito.setLimiteCredito(null); // No aplica
		tarjetaDebito.setIntereses(null); // No aplica

		// Guardar tipos de tarjeta en el repositorio
		tipoTarjetaRepository.save(tarjetaCredito);
		tipoTarjetaRepository.save(tarjetaPremium);
		tipoTarjetaRepository.save(tarjetaDebito);

		EstadoTarjetaEntity tarjetaActiva = new EstadoTarjetaEntity();
		tarjetaActiva.setDescripcion("Activa");

		EstadoTarjetaEntity tarjetaInactiva = new EstadoTarjetaEntity();
		tarjetaInactiva.setDescripcion("Inactiva");

		EstadoTarjetaEntity tarjetaBloqueada = new EstadoTarjetaEntity();
		tarjetaBloqueada.setDescripcion("Bloqueada");

		estadoTarjetaRepository.save(tarjetaActiva);
		estadoTarjetaRepository.save(tarjetaInactiva);
		estadoTarjetaRepository.save(tarjetaBloqueada);

		Random random = new Random();
		int cvv = 100 + random.nextInt(900);

		Random random2 = new Random();
		long numTarjeta = (long) (Math.pow(10, 9)) + (long) (random2.nextDouble() * Math.pow(10, 9));
		System.out.println(numTarjeta);

		TarjetaEntity tarjeta1 = new TarjetaEntity();
		tarjeta1.setNumeroTarjeta(String.valueOf(numTarjeta));
		tarjeta1.setFechaActivacion(fechaActual);
		tarjeta1.setFechaVencimiento(fechaActual.plusYears(6));
		tarjeta1.setCvv(String.valueOf(cvv));
		tarjeta1.setUsuario(user1);
		tarjeta1.setTipoTarjeta(tarjetaPremium);
		tarjeta1.setEstadoTarjeta(tarjetaActiva);

		TarjetaEntity tarjeta2 = new TarjetaEntity();
		tarjeta2.setNumeroTarjeta(String.valueOf(numTarjeta));
		tarjeta2.setFechaActivacion(fechaActual);
		tarjeta2.setFechaVencimiento(fechaActual.plusYears(6));
		tarjeta2.setCvv(String.valueOf(cvv));
		tarjeta2.setUsuario(user2);
		tarjeta2.setTipoTarjeta(tarjetaPremium);
		tarjeta2.setEstadoTarjeta(tarjetaInactiva);

		tarjetaRepository.save(tarjeta1);
		tarjetaRepository.save(tarjeta2);

		// Crear e insertar tipos de cuenta
		TipoCuentaEntity cuentaAhorros = new TipoCuentaEntity();
		cuentaAhorros.setDescripcion("Cuenta de Ahorros");
		cuentaAhorros.setInteresAnual(2.5);
		cuentaAhorros.setComision(null); // No hay comisión

		TipoCuentaEntity cuentaCorriente = new TipoCuentaEntity();
		cuentaCorriente.setDescripcion("Cuenta Corriente");
		cuentaCorriente.setInteresAnual(null); // No hay interés
		cuentaCorriente.setComision(5.0);

		TipoCuentaEntity cuentaPlazoFijo = new TipoCuentaEntity();
		cuentaPlazoFijo.setDescripcion("Cuenta a Plazo Fijo");
		cuentaPlazoFijo.setInteresAnual(3.0);
		cuentaPlazoFijo.setComision(null); // No hay comisión

		TipoCuentaEntity cuentaEmpresarial = new TipoCuentaEntity();
		cuentaEmpresarial.setDescripcion("Cuenta Empresarial");
		cuentaEmpresarial.setInteresAnual(null); // No hay interés
		cuentaEmpresarial.setComision(10.0);

		// Guardar tipos de cuenta en el repositorio
		tipoCuentaRepository.save(cuentaAhorros);
		tipoCuentaRepository.save(cuentaCorriente);
		tipoCuentaRepository.save(cuentaPlazoFijo);
		tipoCuentaRepository.save(cuentaEmpresarial);

		EstadoCuentaEntity estCuenta1 = new EstadoCuentaEntity();
		estCuenta1.setDescripcion("Activo");

		EstadoCuentaEntity estCuenta2 = new EstadoCuentaEntity();
		estCuenta2.setDescripcion("Desactivada");

		EstadoCuentaEntity estCuenta3 = new EstadoCuentaEntity();
		estCuenta3.setDescripcion("Bloqueada");

		EstadoCuentaEntity estCuenta4 = new EstadoCuentaEntity();
		estCuenta4.setDescripcion("Vencida");

		estadoCuentaRepository.save(estCuenta1);
		estadoCuentaRepository.save(estCuenta2);
		estadoCuentaRepository.save(estCuenta3);
		estadoCuentaRepository.save(estCuenta4);

		Random random3 = new Random();
		long numCuenta = (long) (Math.pow(10, 14)) + (long) (random3.nextDouble() * Math.pow(10, 14));
		System.out.println(numCuenta);

		CuentaEntity cuenta1 = new CuentaEntity();
		cuenta1.setFechaApertura(fechaActual);
		cuenta1.setNumeroCuenta(String.valueOf(numCuenta));
		cuenta1.setSaldo(200.50);
		cuenta1.setUsuario(user1);
		cuenta1.setEstadoCuenta(estCuenta1);
		cuenta1.setTipoCuenta(cuentaEmpresarial);

		CuentaEntity cuenta2 = new CuentaEntity();
		cuenta2.setFechaApertura(fechaActual);
		cuenta2.setNumeroCuenta(String.valueOf(numCuenta));
		cuenta2.setSaldo(5000.20);
		cuenta2.setUsuario(user2);
		cuenta2.setEstadoCuenta(estCuenta1);
		cuenta2.setTipoCuenta(cuentaAhorros);

		CuentaEntity cuenta3 = new CuentaEntity();
		cuenta3.setFechaApertura(fechaActual);
		cuenta3.setNumeroCuenta(String.valueOf(numCuenta));
		cuenta3.setSaldo(2500.00);
		cuenta3.setUsuario(user3);
		cuenta3.setEstadoCuenta(estCuenta1);
		cuenta3.setTipoCuenta(cuentaPlazoFijo);

		cuentaRepository.save(cuenta1);
		cuentaRepository.save(cuenta2);
		cuentaRepository.save(cuenta3);

		TipoOperacionEntity tipoOpeDeposito = new TipoOperacionEntity();
		tipoOpeDeposito.setDescripcion("Deposito");

		TipoOperacionEntity tipoOpeRetiro = new TipoOperacionEntity();
		tipoOpeRetiro.setDescripcion("Retiro");

		TipoOperacionEntity tipoOpePago = new TipoOperacionEntity();
		tipoOpePago.setDescripcion("Pago de servicios");

		TipoOperacionEntity tipoOpeTransferencia = new TipoOperacionEntity();
		tipoOpeTransferencia.setDescripcion("Transferencia");

		tipoOperacionRepository.save(tipoOpeDeposito);
		tipoOperacionRepository.save(tipoOpeRetiro);
		tipoOperacionRepository.save(tipoOpePago);
		tipoOperacionRepository.save(tipoOpeTransferencia);

		LocalDateTime fechaHoraActual = LocalDateTime.now();

		OperacionEntity ope1 = new OperacionEntity();
		ope1.setTipoOperacion(tipoOpeDeposito);
		ope1.setMonto(200.25);
		ope1.setFechaHora(fechaHoraActual);
		ope1.setCuentaOrigen(cuenta1);
		ope1.setCuentaDestino(cuenta1);
		ope1.setUsuario(user1);
		ope1.setEstado("Completada");

		OperacionEntity ope2 = new OperacionEntity();
		ope2.setTipoOperacion(tipoOpeTransferencia);
		ope2.setMonto(259.80);
		ope2.setFechaHora(fechaHoraActual);
		ope2.setCuentaOrigen(cuenta2);
		ope2.setCuentaDestino(cuenta3);
		ope2.setUsuario(user2);
		ope2.setEstado("Completada");

		OperacionEntity ope3 = new OperacionEntity();
		ope3.setTipoOperacion(tipoOpeDeposito);
		ope3.setMonto(1000.99);
		ope3.setFechaHora(fechaHoraActual);
		ope3.setCuentaOrigen(cuenta3);
		ope3.setCuentaDestino(cuenta1);
		ope3.setUsuario(user3);
		ope3.setEstado("Completada");

		operacionRepository.save(ope1);
		operacionRepository.save(ope2);
		operacionRepository.save(ope3);
	}

}
